<template>
  <div id="userList">
    <el-card ref="card">
      <el-table
        :data="tableData" border
        style="width: 100%">
        <el-table-column prop="id" label="_id" width="180"></el-table-column>
        <el-table-column prop="name" label="姓名" width="180" :formatter="isNameField"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column prop="identity" label="身份" :formatter="isIdentityFiled"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="onEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="onDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  import { confirmTip, isIdentity, loading } from "@/util";
  import { mapMutations } from "vuex";

  export default {
    name: "UserList",
    data() {
      return {
        tableData: []
      };
    },
    methods: {
      async findAllInfo() {
        return await this.$request.call(this, this.$apis.User.findAll, {}, "tableData");
      },
      isNameField(data) {
        return data["name"] || data["tname"] || data["sname"];
      },
      isIdentityFiled(data) {
        return isIdentity(data.identity);
      },
      onEdit(row) {
        this.setCurrEdit(row);
        this.$router.push("/aEditUser");
      },
      async onDelete(row) {
        const name = row["name"] || row["tname"] || row["sname"];
        const res = await confirmTip.call(this,
          `"${name}"${isIdentity(row.identity)}的所有信息都将被删除，是否执行？`,
          "警告", "error");
        if (res) {
          const loadingInstance = loading(this.$refs.card.$el, "更新中...");
          this.$request.call(this, this.$apis.User.deleteUser(row.id)).then(_ => {
            this.$message.success(`"${name}"用户已被删除！`);
            this.findAllInfo().then(_ => loadingInstance.close());
          });
        }
      },
      ...mapMutations(["setCurrEdit"])
    },
    created() {
      const loadingInstance = loading(document.body, "数据加载中...");
      this.findAllInfo()
        .then(_ => loadingInstance.close())
        .catch(_ => loadingInstance.close());
    }
  };
</script>

<style scoped>
    /deep/ .el-table th > .cell {
        text-align: center;
    }

    /deep/ .el-table .cell {
        text-align: center;
    }
</style>