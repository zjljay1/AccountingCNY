package com.bill_java.mapper;

import com.bill_java.entity.amount;
import com.bill_java.entity.category;
import com.bill_java.entity.user;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface billMapper {

    //查询支出账单类别数量
    @Select("select  count(id) from category where sort=0")
    int getSpendCategory();

    //查询支出账单类别名称
    @Select("select name from category where sort=0")
    List<String> getSpendCategoryname();

    //查询记账类别id
    @Select("select id from category where name=#{name}")
    int getcategoryid(String name);
    //查询用户id
    @Select("select id from user where name=#{name}")
    int getuserid(String name);

    //添加一次账单信息
    @Insert("insert into amount(amount,amount_time,category_id,user_id,note) " +
            "value(#{amount},#{amount_time},#{category_id},#{user_id},#{note});")
    int addbill(amount amount);

    //查询用户账单信息
    @Select("select * from amount where user_id=#{id}")
    List<amount> getAll(int id);

    //删除用户信息
    @Delete("delete from amount where id=#{id}")
    int deleteid(int id);

    //查询页面数据查询
    @Select("select * from category")
    List<category> getCateAll();

    //用户修改数据
    @Update("update amount set amount=#{amount},amount_time=#{amount_time},category_id=#{category_id},note=#{note}" +
            "where id=#{id}")
    int alters(amount amount);

    //添加类别数据
    @Insert("insert into category(name,sort,svgid) value(#{name},#{sort},#{svgid})")
    int addCate(category category);

    //修改类别数据
    @Update("update category set name=#{name},sort=#{sort},svgid=#{svgid} where id=#{id}")
    int alterCate(category category);
}
