<template>
  <div>
    <el-card>
      <el-form>
        <el-divider content-position="left">{{ title }}</el-divider>
        <el-form-item label="账号：" label-width="80px">
          <el-input v-model.trim.number="formData.id"></el-input>
        </el-form-item>
        <el-form-item label="用户名：" label-width="80px">
          <el-input v-model.trim="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="密码：" label-width="80px">
          <el-input v-model.trim="formData.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="年龄：" label-width="80px">
          <el-input v-model.trim.number="formData.age"></el-input>
        </el-form-item>
        <el-form-item label="性别：" label-width="80px" style="text-align: left;">
          <el-radio v-model="formData.gender" label="男"></el-radio>
          <el-radio v-model="formData.gender" label="女"></el-radio>
        </el-form-item>
        <el-form-item label="身份：" label-width="80px" style="text-align: left">
          <el-radio-group v-model.number="formData.identity">
            <el-radio :label="3">学生</el-radio>
            <el-radio :label="2">教师</el-radio>
            <el-radio :label="1">管理员</el-radio>
          </el-radio-group>
        </el-form-item>
        <!--  教师  -->
        <div v-if="formData.identity === 2">
          <el-form-item label="职称：" label-width="80px">
            <el-input v-model.trim="formData.title"></el-input>
          </el-form-item>
          <el-form-item label="学院：" label-width="80px">
            <el-input v-model.trim="formData.school"></el-input>
          </el-form-item>
        </div>
        <!--  学生  -->
        <div v-if="formData.identity === 3">
          <el-form-item label="专业：" label-width="80px">
            <el-input v-model.trim="formData.major"></el-input>
          </el-form-item>
          <el-form-item label="年级：" label-width="80px">
            <el-input v-model.trim="formData.grade"></el-input>
          </el-form-item>
          <el-form-item label="地址：" label-width="80px">
            <el-input v-model.trim="formData.address"></el-input>
          </el-form-item>
        </div>
        <el-form-item>
          <el-button type="primary" @click.prevent.native="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import { mapMutations, mapState } from "vuex";
  import { deepClone, getFiledValidate, isEmptyObj } from "@/util";

  export default {
    data() {
      return {
        formData: {
          identity: 3,
          gender: "男"
        },
        title: "添加用户"
      };
    },
    methods: {
      async onSubmit() {
        const isCurrEdit = isEmptyObj(this.currEdit);
        let api = isCurrEdit ? this.$apis.User.addUser : this.$apis.User.updateUser;
        const res = await this.$request(api, this.formData);
        if (res) {
          this.$message.success(`用户"${this.formData.name}"${isCurrEdit ? "添加" : "编辑"}成功！`);
          this.formData = {};
          await this.$router.push("/userList");
        }
      },
      ...mapMutations(["setCurrEdit"])
    },
    computed: {
      ...mapState(["currEdit", "userForm"])
    },
    mounted() {
      if (!isEmptyObj(this.currEdit)) {
        this.formData = { ...this.currEdit };
        this.$set(this.formData, 'name', getFiledValidate(this.currEdit, 'name'));
        this.$set(this.formData, 'gender', getFiledValidate(this.currEdit, 'gender'));
        this.$set(this.formData, 'age', getFiledValidate(this.currEdit, 'age'));
        this.title = "编辑用户";
      } else {
        this.title = "添加用户";
      }
    },
    beforeDestroy() {
      this.setCurrEdit({});
    }
  };
</script>

<style scoped>

</style>