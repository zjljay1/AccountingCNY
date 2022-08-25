package com.bill_java.entity;

import lombok.Data;

import java.util.Date;

@Data
public class amount {
    private int id;//支出收入id
    private int amount;//金额 正为收入负为支出
    private Date amount_time;//支出收入时间
    private int category_id;//类别id
    private int user_id;//用户id
    private String note;//备注
}
