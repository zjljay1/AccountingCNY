package com.bill_java.mapper;

import com.bill_java.entity.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface userMapper {

    //登录账号
    @Select("select * from user where name=#{name} and password=#{password}")
    user login(user user);

    //根据name查询
    @Select("select id from user where name=#{name}")
    int getId(String name);

    //注册
    @Insert("insert into user(name,password,phone) value(#{name},#{password},#{phone}) ")
    int register(user user);

    //修改用户信息
    @Update("update user set name=#{name},password=#{password},phone=#{phone},birthday=#{birthday},summary=#{summary}" +
            " where id=#{id}")
    int updateuser(user user);

    //根据name查询
    @Select("select * from user where id=#{id}")
    user getAll(int id);
}
