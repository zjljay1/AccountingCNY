import { Service } from "./api";

//登录接口
export const login = (data: any) => {
  return Service({
    url: "/user/login",
    data: JSON.stringify(data),
    method: "post",
  });
};
//查询支出账单信息
export const billSpend = (data: any) => {
  return Service({
    url: "/bill/getSpend",
    data: JSON.stringify(data),
    method: "get",
  });
};

//添加账单信息
export const addbill = (data: any) => {
  return Service({
    url: "/bill/add",
    data: JSON.stringify(data),
    method: "post",
  });
};
//根据用户名称查询id
export const userId = (name: String) => {
  return Service({
    url: `/user/getId/${name}`,
    method: "get",
  });
};

//查询用户所有账单信息
export const getAll = (id: Number) => {
  return Service({
    url: `/bill/get/${id}`,
    method: "get",
  });
};

// export const get = () => {
//   return Service({
//     url: "/index/get",
//     method: "get",
//   });
// };

// export function getAllAndroidPlugins() {
//   return Service({
//     url: "/manager/getAndroidPlugin ",
//     method: "get",
//   });
// }
// export function post(data) {
//   return Service({
//     url: "/index/post",
//     data: JSON.stringify(data),
//     method: "post",
//   });
// }
