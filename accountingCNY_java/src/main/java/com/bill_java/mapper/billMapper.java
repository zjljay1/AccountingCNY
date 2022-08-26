package com.bill_java.mapper;

import com.bill_java.entity.amount;
import com.bill_java.entity.category;
import com.bill_java.entity.user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    //添加页面数据查询
    @Select("select * from category")
    List<category> getCateAll();

}
