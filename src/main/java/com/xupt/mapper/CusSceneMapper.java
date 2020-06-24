package com.xupt.mapper;

import com.xupt.pojo.CusScene;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CusSceneMapper {
    /**通过uerId和cusId获取场景列表*/
    List<CusScene> queryByUAD(@Param("userId") int userId, @Param("cusId") int cusId);
    /**通过uerId和deviceId,cusId获取场景*/
    CusScene queryByUCD(@Param("cusId") int cusId, @Param("userId") int userId, @Param("deviceId") int deviceId);
    /**增加场景设备*/
    int addCusDevice(CusScene cusScene);
    //    /**删除设备绑定*/
    int delete(@Param("userId") int userId, @Param("deviceId") int deviceId);
    /**更改定制场景操作*/
    int updataCusScene(@Param("cusId") int cusId, @Param("userId") int userId, @Param("deviceId") int deviceId, @Param("operation") String operation);
}
