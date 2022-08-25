package com.bill_java.service;


import com.bill_java.entity.user;

public interface userService {

    //登录
    boolean login(user user);

    // 根据name查询
    int getId(String name);
}
