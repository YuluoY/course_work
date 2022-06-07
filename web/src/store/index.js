import Vue from "vue";
import Vuex from "vuex";
import { deepClone } from "@/util";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userForm: JSON.parse(localStorage.getItem("work-userForm")) || {},
    currEdit: JSON.parse(localStorage.getItem("work-currEdit")) || {},
    curriculum: {},

  },
  mutations: {
    setUserForm(state, userForm) {
      localStorage.setItem("work-userForm", JSON.stringify(userForm));
      // deepClone(state.userForm, userForm);
      state.userForm = { ...userForm };
    },
    setCurrEdit(state, formData) {
      localStorage.setItem("work-currEdit", JSON.stringify(formData));
      // deepClone(state.currEdit, formData);
      state.currEdit = { ...formData };
    },
    setCurriculum(state, courses) {
      state.curriculum = { ...courses };
    },
  },
  actions: {},
  modules: {}
});
