package com.bill_java.entity;

import lombok.Data;

@Data
public class category {

    private int id;//类别id
    private String name;//类别名称
    private int sort;//类别属性(1表示收入，0表示支出)
    private int svgid;//svg的资源id
    private int user_id;//用户id
    private int sequencing;//用户id
}
