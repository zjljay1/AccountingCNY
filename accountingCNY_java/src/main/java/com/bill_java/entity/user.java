package com.bill_java.entity;

import lombok.Data;

@Data
public class user {
    private int id;//用户id
    private String name;//用户名称
    private String password;//用户密码
    private String portrait;//用户头像
    private String phone;//用户手机号
}
