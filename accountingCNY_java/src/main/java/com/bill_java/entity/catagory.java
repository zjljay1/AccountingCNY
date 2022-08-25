package com.bill_java.entity;

import lombok.Data;

@Data
public class catagory {

    private int id;//类别id
    private String category;//类别名称
    private int sort;//类别属性(1表示收入，0表示支出)
}
