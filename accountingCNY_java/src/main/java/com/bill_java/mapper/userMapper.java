package com.bill_java.mapper;

import com.bill_java.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {

    //登录账号
    @Select("select * from user where name=#{name} and password=#{password}")
    user login(user user);



}
