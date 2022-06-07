<template>
  <div id="main">
    <el-container>
      <el-aside width="200px">
        <el-menu
          id="menu"
          :default-active="$route.path"
          :default-openeds="['1']"
          class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
          :collapse="isCollapse"
          router>
          <el-submenu index="1">
            <template slot="title">
              <span slot="title">操作</span>
            </template>
            <el-menu-item-group v-if="userForm.identity === 3">
              <el-menu-item index="/courseList">课程列表</el-menu-item>
              <el-menu-item index="/courseScore">课程成绩</el-menu-item>
              <el-menu-item index="/courseSelect">选修课程</el-menu-item>
              <el-menu-item index="/sPersonalInfo">个人信息</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group v-if="userForm.identity === 2">
              <el-menu-item index="/tCourseList">课程列表</el-menu-item>
              <el-menu-item index="/stuManage">学生管理</el-menu-item>
              <el-menu-item index="/pubCourse">发布课程</el-menu-item>
              <el-menu-item index="/tPersonalInfo">个人信息</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group v-if="userForm.identity === 1">
              <el-menu-item index="/userList">用户列表</el-menu-item>
              <el-menu-item index="/aCourseList">课程列表</el-menu-item>
              <el-menu-item index="/aEditUser">添加/编辑用户</el-menu-item>
              <el-menu-item index="/aEditCourse">添加/编辑课程</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <div style="display: flex; justify-content: space-between;">
            <el-image :src="require('../assets/img/logo.png')" style="width: 60px;border-radius: 50%"></el-image>
            <span style="padding-left: 20px;">{{ $store.state.userForm.name }}</span>
          </div>
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <el-button @click="$router.push('/')" type="primary" :disabled="onLoginDisabled">登录</el-button>
            <el-button @click="onQuit" type="danger">退出</el-button>
          </div>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import { mapState } from "vuex";
  import { isIdentity } from "@/util";

  export default {
    name: "Main",
    data() {
      return {
        isCollapse: false
      };
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      onQuit() {
        this.$message.success(`${isIdentity(this.userForm.identity)}"${this.userForm.name}"退出成功！`);
        sessionStorage.removeItem('work-token');
        this.$router.push("/");
      },

    },
    computed: {
      ...mapState(["userForm"]),
      onLoginDisabled() {
        return !!sessionStorage.getItem('work-token');
      }
    },

    mounted() {
      // this.$message.success(`${isIdentity(this.userForm.identity)}"${this.userForm.name}"登录成功！`);
    }
  };
</script>

<style scoped>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
        position: sticky;
        top: 0;
        z-index: 1000;
        display: flex;
        justify-content: space-between;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        height: 100vh;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    #menu {
        position: fixed;
        top: 0;
        z-index: 1000;
    }

</style>