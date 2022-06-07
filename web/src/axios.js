import axios from "axios";
import { Message } from "element-ui";
import { requestMiddleWare, loading } from "@/util";
import id from "element-ui/src/locale/lang/id";

const Axios = axios.create();

// 公共配置项
const axiosConfigure = {
  baseURL: "http://localhost:8080/api",
  timeout: 10000
};

// 请求拦截器
Axios.interceptors.request.use(req => {
  req.headers = {
    Authorization: sessionStorage.getItem("work-token")
  };
  return req;
}, err => {
  return Promise.reject(err);
});

// 响应拦截器
Axios.interceptors.response.use(res => {
  return res;
}, err => {
  switch (err.response.status) {
    case 401:
      Message.error("未找到该用户！");
      break;
    case 402:
      Message.error("该ID已存在，请勿重复添加！");
      break;
    case 403:
      Message.error("您没有权限访问，请检查是否登录！");
      localStorage.setItem("work-userForm", "{}");
      break;
    case 404:
      Message.error("未找到服务器接口！");
      break;
    case 405:
      Message.error("该用户不存在，无法操作！");
      break;
    case 500:
      Message.error("服务器异常，您的操作有误！");
      break;
  }
  return Promise.reject(err);
});

/*
* Function : request
* Description : 封装后的axios，可以通过options配置项发送各种请求，也能通过bind来自动绑定变量赋值，
*               在获取到值后，你可以通过callback回调来取得请求数据。或者可以直接从组件自身获取（因为bind已绑定赋值）。
*               也就是说，获取数据有两种方式，一：回调函数，二：绑定值
* Params: options【必需】 --> 配置项添加。需要给请求添加配置项，例如method、url、params。
*         bind【可选】 --> 绑定变量值。给当前的组件示例this挂载变量并赋值，或存在变量仅赋值。如果不需要需要使用null占位。
*         callback【可选】 --> 回调函数。如果存在回调函数，则该回调函数的第一个参数就为请求数据。
* Return : 返回一个Promise对象，通过then可以获取数据。
* */
const request = async function(options, obj = {}, bind = null, callback = null) {
  options = requestMiddleWare(options, obj) || {};
  Object.assign(options, axiosConfigure); // 合并配置项
  const res = await Axios(options); // 发出ajax请求
  if (bind) this[bind] = res.data;  // 为this上的某个变量绑定值
  if (typeof callback === "function") callback(res); // 如果有回调，就将值传入回调并使用回调函数
  return res;
};

// 五种请求的独立封装
// const method = ["get", "post", "put", "delete", "patch"];
// for (const ele of method) {
//   request.__proto__[ele] = function(url, obj) {
//     const options = { url };
//     Object.assign(options, axiosConfigure);
//     if (["get", "delete"].includes(ele)) {
//       options["params"] = obj;
//     } else {
//       options["data"] = obj;
//     }
//     return Axios(options);
//   };
// }
export default request;