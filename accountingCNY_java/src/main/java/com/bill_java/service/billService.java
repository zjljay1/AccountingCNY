package com.bill_java.service;


import com.bill_java.entity.amount;
import com.bill_java.entity.category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface billService {

    //查询支出账单类别数量
    int getSpendCategory();

    //查询支出账单类别名称
    List<String> getSpendCategoryname();

    //查询记账类别id
    int getcategoryid(String name);
    // 查询用户id
    int getuserid(String name);
    //添加一次账单信息
     int addbill(amount amount);

     //查询用户账单信息
    List<amount> getAll(int id);

    //删除用户数据
    int deleteid(int id);

    //添加页面数据查询
    List<category> getCateAll();
}
