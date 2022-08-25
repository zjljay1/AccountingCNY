package com.bill_java.controller;

import com.bill_java.common.lang.Result;
import com.bill_java.entity.amount;
import com.bill_java.service.billService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/bill")
public class billController {

    @Autowired
    private billService billService;

    //查询支出账单类别数量和支出账单类别名称
    @GetMapping("/getSpend")
    public Result getSpendCategory(){
        Map<Integer,List<String>> m = new HashMap<>();
        int spendCategory = billService.getSpendCategory();
        List<String> spendCategoryname = billService.getSpendCategoryname();
        m.put(spendCategory,spendCategoryname);
        return Result.succ(m);
    };

    //添加一次账单数据
    @PostMapping("/add")
    public Result addBill(@RequestBody amount m){
        int addbill = billService.addbill(m);
        System.out.println(m);
        System.out.println(m.getCategory_id());
        System.out.println(m.getUser_id());
        if(addbill>0){
            return Result.succ("添加成功");
        }
        return Result.fail("添加失败");
    }

    //查询用户账单信息
    @GetMapping("/get/{id}")
    public Result getAll(@PathVariable("id") int id){
        List<amount> all = billService.getAll(id);
        if(all.size()>0){
            return Result.succ(all);
        }
        return Result.fail("查询失败");
    }


}

