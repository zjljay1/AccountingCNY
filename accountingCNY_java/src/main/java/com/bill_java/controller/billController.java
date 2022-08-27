package com.bill_java.controller;

import com.bill_java.common.lang.Result;
import com.bill_java.entity.amount;
import com.bill_java.entity.category;
import com.bill_java.service.billService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    //根据用户id删除数据
    @DeleteMapping("/deleteId/{id}")
    public Result deleteId(@PathVariable("id") int id){
        int deleteid = billService.deleteid(id);
        if(deleteid>0){
            return Result.succ("删除成功");
        }
        return Result.succ("删除失败");
    }


    //添加页面数据查询
    @GetMapping("/getCate")
    public Result getCateAll(){
        List<category> cateAll = billService.getCateAll();
        if(cateAll.size()>0){
            return Result.succ(cateAll);
        }
        return Result.fail("查询失败");
    }

    //用户修改数据
    @PostMapping("/alters")
    public Result alters(@RequestBody amount amount){
        int alters = billService.alters(amount);
        System.out.println(amount);
        if(alters>0){
            return Result.succ("修改成功");
        }
        return Result.fail("修改失败");
    }

    //添加类别
    @PostMapping("/addCate")
    public Result addCate(@RequestBody category category){
        int i = billService.addCate(category);
        if(i>0){
            return Result.succ("添加成功");
        }
        return Result.fail("添加失败");
    }

    //修改类别
    @PostMapping("/alterCate")
    public Result alterCate(@RequestBody category category){
        System.out.println(category);
        int i = billService.alterCate(category);
        if(i>0){
            return Result.succ("修改成功");
        }
        return Result.fail("修改失败");
    }
}

