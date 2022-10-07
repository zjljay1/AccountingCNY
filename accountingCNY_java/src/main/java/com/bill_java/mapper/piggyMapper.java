package com.bill_java.mapper;


import com.bill_java.entity.piggy;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface piggyMapper {


    @Insert("insert into piggy(money,user_id,source) " +
            "value(#{money},#{user_id},#{source});")
    int addpiggy(piggy piggy);

    @Select("select * from piggy where user_id=#{id}")
    List<piggy> getpiggy(int id);
}
