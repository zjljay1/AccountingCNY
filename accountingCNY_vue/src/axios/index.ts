import { Service } from "./api";

export const login = (data: any) => {
  return Service({
    url: "/user/login",
    data: JSON.stringify(data),
    method: "post",
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
