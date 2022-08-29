package com.bill_java.service.impl;

import com.bill_java.entity.amount;
import com.bill_java.entity.category;
import com.bill_java.mapper.billMapper;
import com.bill_java.service.billService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class billServiceImpl implements billService {

    @Autowired
    private billMapper billMapper;

    //查询支出账单类别数量
    @Override
    public int getSpendCategory() {
       return billMapper.getSpendCategory();
    }
    //支出账单类别名称
    @Override
    public List<String> getSpendCategoryname() {
        return billMapper.getSpendCategoryname();
    }

    //查询记账类别id
    @Override
    public int getcategoryid(String name) {
        return billMapper.getcategoryid(name);
    }
    //查询用户id
    @Override
    public int getuserid(String name) {
        return billMapper.getuserid(name);
    }
    //添加一次账单信息
    @Override
    public int addbill(amount amount) {
        return billMapper.addbill(amount);
    }

    //查询用户账单信息
    @Override
    public List<amount> getAll(int id) {
        return billMapper.getAll(id);
    }

    @Override
    public int deleteid(int id) {
        return billMapper.deleteid(id);
    }

    @Override
    public List<category> getCateAll() {
         return  billMapper.getCateAll();
    }

    @Override
    public int alters(amount amount) {
        return billMapper.alters(amount);
    }

    @Override
    public int addCate(category category) {
        return billMapper.addCate(category);
    }

    @Override
    public int alterCate(category category) {
        return billMapper.alterCate(category);
    }

    @Override
    public int DeleteCate(int id) {
        return billMapper.DeleteCate(id);
    }

    @Override
    public int DeleteCatesort(category category) {
        return billMapper.DeleteCatesort(category);
    }

    @Override
    public int sort(category category) {
        return billMapper.sort(category);
    }


}
