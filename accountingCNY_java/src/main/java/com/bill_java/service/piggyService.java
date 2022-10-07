package com.bill_java.service;

import com.bill_java.entity.amount;
import com.bill_java.entity.piggy;

import java.util.List;

public interface piggyService {

    //添加
    int addpiggy(piggy piggy);

    //查询用户账单信息
    List<piggy> getpiggy(int id);
}
