package com.bill_java.service.impl;

import com.bill_java.entity.piggy;
import com.bill_java.mapper.piggyMapper;
import com.bill_java.service.piggyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class piggyServiceImpl implements piggyService {

    @Autowired
    private piggyMapper piggyMapper;

    //添加
    public int addpiggy(piggy piggy){
        return piggyMapper.addpiggy(piggy);
    }

    @Override
    public List<piggy> getpiggy(int id) {
        return piggyMapper.getpiggy(id);
    }

    ;


}
