package com.bill_java.controller;

import com.bill_java.common.lang.Result;
import com.bill_java.entity.piggy;
import com.bill_java.service.piggyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/piggy")
public class PiggyController {

    @Autowired
    private piggyService piggyService;

    //添加
    @PostMapping("/add")
    public Result addpiggy(@RequestBody piggy piggy){
        int addpiggy = piggyService.addpiggy(piggy);
        if(addpiggy>0){
            return Result.succ("添加成功");
        }
        return Result.fail("添加失败");
    }

    //查询
    @GetMapping("/getpig/{id}")
    public Result getpiggy(@PathVariable("id") int id){
        List<piggy> piggy = piggyService.getpiggy(id);
        if(piggy!=null){
            return Result.succ(piggy);
        }
        return Result.fail("查询失败");
    }

}
