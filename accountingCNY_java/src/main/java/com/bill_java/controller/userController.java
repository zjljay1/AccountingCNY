package com.bill_java.controller;

import com.bill_java.common.lang.Result;
import com.bill_java.entity.user;
import com.bill_java.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService userService;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody user user){
        boolean flag = userService.login(user);
        if(flag){
            System.out.println("OK");
            return Result.succ("登录成功,欢迎使用");
        }else{
            System.out.println("NO");
            return Result.fail("登录失败,请重新确定账号");
        }
    }

    //根据姓名查询id
    @GetMapping("/getId/{name}")
    public Result getname_id(@PathVariable("name") String name){
        int id = userService.getId(name);
        if(id>=1){
            return Result.succ(id);
        }
        return Result.succ("查询失败");
    }



}
