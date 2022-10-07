package com.bill_java.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.bill_java.common.lang.Result;
import com.bill_java.entity.user;
import com.bill_java.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
            int id = userService.getId(user.getName());
            StpUtil.login(id);
            return Result.succ(200,"登录成功,欢迎使用",StpUtil.getTokenInfo());
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

    //注册
    @PostMapping("register")
    public Result register(@RequestBody user user){
        int register = userService.register(user);
        if(register>0){
            int id = userService.getId(user.getName());
            return Result.succ("添加成功");
        }
        return Result.succ("添加失败");
    }

    //修改用户信息
    @PostMapping("/update")
    @SaCheckLogin
    public Result updateuser(@RequestBody user user){
        System.out.println(user);
        int updateuser = userService.updateuser(user);
        if(updateuser>0){
            return Result.succ("修改成功");
        }
        return Result.fail("修改失败");
    }

    //根据id查询信息
    @GetMapping("/getAll/{id}")
    public Result getAll(@PathVariable("id") int id){
        user userAll = userService.getAll(id);
        if(userAll!=null){
            return Result.succ(userAll);
        }
        return Result.succ("查询失败");
    }

//    //satoken测试
//    @RequestMapping("/doLogin")
//    public String doLogin(String username, String password) {
//        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
//        if("zhang".equals(username) && "123456".equals(password)) {
//            StpUtil.login(10001);
//            return "登录成功";
//        }
//        return "登录失败";
//    }
//
//    // 查询登录状态，浏览器访问： http://localhost:8089/user/isLogin
//    @GetMapping("/isLogin")
//    public String isLogin() {
//        return "当前会话是否登录：" + StpUtil.isLogin();
//    }


}
