<template>
  <div class="sPersonalInfo">
    <el-card>
      <el-form>
        <el-divider content-position="left">个人信息表</el-divider>
        <el-form-item label="学号：" label-width="80px">
          <el-input v-model.trim.number="formData.id"></el-input>
        </el-form-item>
        <el-form-item label="姓名：" label-width="80px">
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
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年级：" label-width="80px">
          <el-input v-model.trim="formData.grade"></el-input>
        </el-form-item>
        <el-form-item label="地址：" label-width="80px">
          <el-input v-model.trim="formData.address"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click.prevent.native="onSubmit">提交修改</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import { mapMutations, mapState } from "vuex";
  import { confirmTip } from "@/util";

  export default {
    name: "sPersonalInfo",
    data() {
      return {
        formData: {
          password: ""
        }
      };
    },
    methods: {
      async onSubmit() {
        let enquireRes = false;
        let successNotify = `学生"${this.formData.name}"个人信息修改成功！`;
        let path = "/courseList";
        // 判断是否修改了密码
        if (!(this.formData.password === undefined || this.formData.password === "")) {
          enquireRes = await confirmTip.call(this, `您修改了密码，是否提交？`);
          if (!enquireRes) return;
          successNotify = `学生"${this.formData.name}"个人信息修改成功，请牢记密码！`;
          path = "/";
        }
        this.$request(this.$apis.Student.modifyPersonalInfo, this.formData).then(async _ => {
          this.$notify.success(successNotify);
          this.setUserForm(this.formData);
          this.formData = {};
          await this.$router.push(path);
        });
      },
      ...mapMutations(["setUserForm"])
    },
    computed: {
      ...mapState(["userForm"])
    },
    created() {
      this.formData = { ...this.userForm };
    }
  };
</script>

<style scoped>

</style>