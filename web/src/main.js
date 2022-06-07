import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import request from "./axios";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import apis from './apis/index'

Vue.config.productionTip = false;
Vue.prototype.$request = request;
Vue.prototype.$apis = apis;

Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");