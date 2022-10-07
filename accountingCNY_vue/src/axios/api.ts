import router from "@/router";
import axios from "axios";
const ConfigBaseURL = "http://localhost:8089"; //默认路径
//使用create方法创建axios实例
const Service = axios.create({
  timeout: 3000, // 请求超时时间
  baseURL: ConfigBaseURL,
  headers: {
    Accept: "application/json, text/plain, */*",
    "Content-Type": "application/json",
    "X-Requested-With": "XMLHttpRequest",
  },
});

Service.interceptors.request.use(
  function (config) {
    console.log("请求拦截");
    if (localStorage.getItem("token")) {
      config.headers.Authorization = "JWT " + localStorage.getItem("token");
    }
    return config;
  },
  function (error) {
    console.log(error);
    return Promise.reject(error);
  },
);

Service.interceptors.response.use(
  function (config) {
    console.log(config.data);
    if (config.data.code == 509) {
      localStorage.removeItem("token");
      router.push({
        name: "login",
      });
    } else if (localStorage.getItem("token") == null) {
      localStorage.setItem("token", config.data.data.tokenValue);
    }
    return config;
  },
  function (error) {
    console.log(error);
    return error;
  },
);

export default Service;
