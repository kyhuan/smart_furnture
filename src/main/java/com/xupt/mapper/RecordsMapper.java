package com.xupt.mapper;

import com.xupt.pojo.Records;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordsMapper {
    /**获取记录*/
    List<Records> queryByUserId(int userId);
    /**添加记录*/
    int add(Records records);
}
