import { Loading } from "element-ui";
import dayjs from "dayjs";

export function compareWithTwoObj(obj1, obj2) {
  let bool = true;
  Object.keys(obj1).forEach(key => {
    obj1[key] !== obj2[key] && (bool = false)
    if(!bool) return bool;
  });
  return bool;
}

export function setCourseEndTime(hours) {
  return (Date.now() + hours * 1 * 60 * 60 * 1000);
}

export function getFiledValidate(obj, filed) {
  let filedValidate = null;
  if (filed === "name") {
    filedValidate = obj[filed] || obj["tname"] || obj["sname"];
  } else if (filed === "age") {
    filedValidate = obj[filed] || obj["tAge"] || obj["sAge"];
  } else if (filed === "gender") {
    filedValidate = obj[filed] || obj["tGender"] || obj["sGender"];
  }
  return filedValidate;
}

// 提示消息框
export function confirmTip(text, title = "提示", type = "warning") {
  return this.$confirm(text, title, {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: type
  }).then(_ => {
    return true;
  }).catch(_ => {
    return false;
  });
}

// 身份判断
export function isIdentity(code) {
  return code === 1 ? "管理员" : code === 2 ? "教师" : "学生";
}

// 日期格式化
export function formatDate(date, formatStr = "YYYY年MM月DD日 HH:mm:ss") {
  return dayjs(date).format(formatStr);
}

// 合并多个对象
export function merge(target, ...args) {
  args.forEach(arg => Object.assign(target, arg));
}

// 加载效果
export function loading(dom, text) {
  return Loading.service({
    lock: true, //lock的修改符--默认是false
    text: text || "Loading...", //显示在加载图标下方的加载文案
    spinner: "el-icon-loading", //自定义加载图标类名
    background: "rgba(0, 0, 0, 0.6)",//遮罩层颜色
    target: dom || document.body //loadin覆盖的dom元素节点
  });
};

export function isEmptyObj(obj) {
  return JSON.stringify(obj) === "{}";
}

export function isString(str) {
  return Object.prototype.toString.call(str) === "[object String]";
}

export function isNumber(num) {
  return Object.prototype.toString.call(num) === "[object Number]";
}

export function isBoolean(bool) {
  return Object.prototype.toString.call(bool) === "[object Boolean]";
}

export function isNull(obj) {
  return Object.prototype.toString.call(bool) === "[object Null]";
}

export function isUndef(obj) {
  return Object.prototype.toString.call(bool) === "[object Undefined]";
}

export function isPrimitive(obj) {
  return (
    typeof obj === "string" ||
    typeof obj === "number" ||
    typeof obj === "boolean" ||
    typeof obj === "symbol" ||
    typeof obj === "bigint"
  );
}

export function isArray(arr) {
  return Object.prototype.toString.call(arr) === "[object Array]";
}

export function isObject(obj) {
  return Object.prototype.toString.call(obj) === "[object Object]";
}

// 深拷贝
export function deepClone(target, source) {
  if (isArray(target) && isArray(source)) {
    arrayDeepClone(target, source);
  } else if (isObject(target) && isObject(source)) {
    objectDeepClone(target, source);
  } else {
    console.error("类型不一致或不是数组、对象类型！");
    return;
  }
}

export function arrayDeepClone(target, source) {
  source = source || [];
  for (const i in source) {
    if (isArray(source[i])) {
      target.push([]);
      arrayDeepClone(target[target.length - 1], source[i]);
    } else {
      target.push(source[i]);
    }
  }
  return target;
};

export function objectDeepClone(target, source) {
  source = source || {};
  for (const sourceKey in source) {
    if (isObject(source[sourceKey])) {
      target[sourceKey] = {};
      objectDeepClone(target[sourceKey], source[sourceKey]);
    } else {
      target[sourceKey] = source[sourceKey];
    }
  }
  return target;
};

// 请求参数设置
export function requestMiddleWare(options, obj) {
  if (!options["method"] || JSON.stringify(obj) === "{}") return options;
  let str = "data";
  ["get", "delete"].includes(options["method"]) && (str = "params");
  options[str] = obj;
  return options;
}
