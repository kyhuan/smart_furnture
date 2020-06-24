package com.xupt.mapper;

import com.xupt.pojo.Customization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomizationMapper {


    @Select("select * from customization")
    List<Customization> findAllCus ();
}
