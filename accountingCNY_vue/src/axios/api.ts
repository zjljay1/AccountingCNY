import axios from "axios";
const ConfigBaseURL = "http://localhost:8089"; //默认路径
//使用create方法创建axios实例
export const Service = axios.create({
  timeout: 3000, // 请求超时时间
  baseURL: ConfigBaseURL,
  headers: {
    Accept: "application/json, text/plain, */*",
    "Content-Type": "application/json",
    "X-Requested-With": "XMLHttpRequest",
  },
});

// axios.interceptors.request.use(
//   function (config) {
//     // 在发送请求之前做些什么
//     return config;
//   },
//   function (error) {
//     // 对请求错误做些什么
//     return Promise.reject(error);
//   },
// );
