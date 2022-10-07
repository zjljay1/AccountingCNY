package com.bill_java.service;


import com.bill_java.entity.user;

public interface userService {

    //登录
    boolean login(user user);

    // 根据name查询
    int getId(String name);

    //注册
    int register(user user);

    //修改用户信息
    int updateuser(user user);

    // 根据name查询
    user getAll(int id);
}
