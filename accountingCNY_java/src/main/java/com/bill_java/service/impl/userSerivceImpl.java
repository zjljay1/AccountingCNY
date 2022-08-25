package com.bill_java.service.impl;

import com.bill_java.entity.user;
import com.bill_java.mapper.userMapper;
import com.bill_java.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userSerivceImpl implements userService {

    @Autowired
    private userMapper userMapper;


    @Override
    public boolean login(user user) {
        user login = userMapper.login(user);
        return login!=null;
    }

    @Override
    public int getId(String name) {
        return userMapper.getId(name);
    }
}
