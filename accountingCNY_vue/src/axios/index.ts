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

//根据用户id删除数据
export const deleteId = (id: Number) => {
  return Service({
    url: `/bill/deleteId/${id}`,
    method: "delete",
  });
};

//添加页面数据查询
export const getCateAll = () => {
  return Service({
    url: "/bill/getCate",
    method: "get",
  });
};

//修改数据
export const alters = (data: any) => {
  return Service({
    url: "/bill/alters",
    data: JSON.stringify(data),
    method: "post",
  });
};

//添加类别
export const addCate = (data: any) => {
  return Service({
    url: "/bill/addCate",
    data: JSON.stringify(data),
    method: "post",
  });
};

//修改类别
export const alterCate = (data: any) => {
  return Service({
    url: "/bill/alterCate",
    data: JSON.stringify(data),
    method: "post",
  });
};

//删除类别
export const DeleteCate = (id: any) => {
  return Service({
    url: `bill/deleteCate/${id}`,
    method: "delete",
  });
};

//删除
export const DeleteCateSort = (data: any) => {
  return Service({
    url: "bill/deleteCateSort",
    data: JSON.stringify(data),
    method: "delete",
  });
};

// 排序
export const sort = (data: any) => {
  return Service({
    url: "bill/sort",
    method: "post",
    data: JSON.stringify(data),
  });
};
