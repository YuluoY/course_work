<template>
  <div id="courseList">

    <el-card ref="card">
      <el-table
        :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="课程ID"></el-table-column>
        <el-table-column prop="name" label="课程名"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
        <el-table-column prop="school" label="学院"></el-table-column>
        <el-table-column prop="tname" label="授课老师"></el-table-column>
        <el-table-column prop="startTime" label="开课时间" :formatter="timeFormat"></el-table-column>
        <el-table-column prop="endTime" label="结束时间" :formatter="timeFormat"></el-table-column>
        <el-table-column label="操作" width="180">
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

  import { confirmTip, formatDate, loading } from "@/util";
  import { mapMutations } from "vuex";

  export default {
    name: "CourseList",
    data() {
      return {
        tableData: []
      };
    },
    methods: {
      async findAllCourse() {
        await this.$request(this.$apis.Course.findAllCourse, {}, "tableData");
        setTimeout(_ => this.setCurriculum(this.tableData));
      },
      onEdit(row) {
        this.setCurrEdit(row);
        this.$router.push("/aEditCourse");
      },
      async onDelete(row) {
        const res = await confirmTip.call(this, `确认删除"${row.name}"课程吗？`);
        if (res) {
          const loadingInstance = loading(this.$refs.card.$el, "更新中...");
          this.$request(this.$apis.Course.deleteById(row.id)).then(res => {
            res && this.$message.success(`"${row.name}"课程成功被删除！`);
            this.findAllCourse().then(_ => loadingInstance.close());
          }).catch(err => {
            this.$message.error(err.message);
            loadingInstance.close();
          });
        }
      },
      timeFormat(data, event) {
        return formatDate(data[event.property], "YYYY年MM月DD日");
      },
      ...mapMutations(["setCurrEdit", "setCurriculum"])
    },
    created() {
      const loadingInstance = loading(document.body, "数据加载中...");
      this.findAllCourse()
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