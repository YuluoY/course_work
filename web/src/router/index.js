import Vue from "vue";
import VueRouter from "vue-router";
import { Message } from "element-ui";
import { isIdentity } from "@/util";

Vue.use(VueRouter);

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

const routes = [
  {
    path: "/",
    name: "Login",
    component: () => import("@/views/Login")
  },
  {
    path: "/main",
    name: "Main",
    component: () => import("@/views/Main"),
    beforeEnter: (to, form, next) => {
      let identity = to.params["identity"];
      if (identity === 1) {
        next("/userList");
      } else if (identity === 2) {
        next("/tCourseList");
      } else if (identity === 3) {
        next("/courseList");
      }
      next();
    },
    children: [
      // 学生
      { path: "/courseList", name: "CourseList", component: () => import("@/views/student/CourseLsit") },
      { path: "/courseScore", name: "CourseScore", component: () => import("@/views/student/CourseScore") },
      { path: "/courseSelect", name: "CourseSelect", component: () => import("@/views/student/CourseSelect") },
      { path: "/sPersonalInfo", name: "SPersonalInfo", component: () => import("@/views/student/sPersonalInfo") },
      // 教师
      { path: "/tCourseList", name: "tCourseList", component: () => import("@/views/teacher/tCourseList") },
      { path: "/stuManage", name: "StuManage", component: () => import("@/views/teacher/StuManage") },
      { path: "/pubCourse", name: "PubCourse", component: () => import("@/views/teacher/PubCourse") },
      { path: "/tPersonalInfo", name: "TPersonalInfo", component: () => import("@/views/teacher/tPersonalInfo") },
      // 管理员
      { path: "/userList", name: "UserList", component: () => import("@/views/admin/UserList") },
      { path: "/aCourseList", name: "aCourseList", component: () => import("@/views/admin/CourseList") },
      { path: "/aEditCourse", name: "aEditCourse", component: () => import("@/views/admin/EditCourse") },
      { path: "/aEditUser", name: "aEditUser", component: () => import("@/views/admin/EditUser") }
    ]
  }
];

const router = new VueRouter({
  routes,
  linkActiveClass: "active"
});

router.beforeEach((to, from, next) => {
  if (to.name === "Main" && from.name === "Login") {
    const userInfo = { ...to.params };
    if (userInfo.token !== "" && userInfo.token !== undefined) {
      sessionStorage.setItem("work-token", userInfo.token);
      Message.success(`${isIdentity(userInfo.identity)}"${userInfo.name}"登录成功！`);
    }else {
      Message.error('您没有访问权限！');
      next('/')
    }
  }
  if (to.name === "Login" && from.name !== null) {
    sessionStorage.removeItem('work-token');
  }
  next();
});

export default router;
