<template>
  <div id="login">
    <el-card ref="formCard" class="box-card">
      <el-form :model="userForm" :rules="formRules" ref="form">
        <el-form-item>
          <div style="text-align: center; font-size: 25px;">登录</div>
        </el-form-item>
        <el-form-item label="学号：" prop="id" label-width="65px">
          <el-input v-model.trim="userForm.id" placeholder="请输入学号"></el-input>
        </el-form-item>
        <el-form-item label="密码：" prop="password" label-width="65px">
          <el-input v-model.trim="userForm.password" show-password placeholder="请输入密码"
                    @keydown.native.enter="onSubmit"></el-input>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button type="primary" @click.prevent.native="onSubmit">登录</el-button>
          <el-button type="primary" @click.prevent.native="onReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <br>
  </div>
</template>

<script>
  import apis from "@/apis";
  import { loading } from "@/util";
  import { mapMutations } from "vuex";

  export default {
    name: "Login",
    data() {
      return {
        userForm: {
          id: "",
          password: ""
        },
        formRules: {
          id: [{ required: true, validator: this.checkId, trigger: "blur" }],
          password: [{ required: true, validator: this.checkPassword, trigger: "blur" }]
        }
      };
    },
    methods: {
      checkId(rule, value, callback) {
        if (!value.length) {
          callback(new Error("学号不能为空！"));
        } else {
          callback();
        }
      },
      checkPassword(rule, value, callback) {
        if (!value.length) {
          callback(new Error("密码不能为空！"));
        } else if (value.length < 4) {
          callback(new Error("密码长度至少大于等于六！"));
        } else {
          callback();
        }
      },
      onSubmit() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            const loadingInstance = loading(document.body, "登录中...");
            this.$request(this.$apis.User.login, this.userForm).then(res => {
              this.setUserForm(res.data);
              loadingInstance.close();
              this.$router.push({
                name: "Main",
                params: res.data
              });
            }).catch(err => {
              this.$message.error("登录失败！" + err.message);
              loadingInstance.close();
            });
          }
        });
      },
      onReset() {
        this.$refs.form.resetFields();
      },
      ...mapMutations(["setUserForm"])
    },
    mounted() {
      this.setUserForm({});
    }
  };
</script>

<style scoped>
    .box-card {
        width: 600px;
        height: 400px;
        margin: 10% auto 0;
    }

    .el-form-item {
        margin: 45px;
    }
</style>