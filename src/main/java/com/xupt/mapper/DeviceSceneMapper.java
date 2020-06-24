package com.xupt.mapper;

import com.xupt.pojo.DeviceScene;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceSceneMapper {
    /**通过userid查找*/
    List<DeviceScene> queryByUserId(int userId);
    /**添加设备场景*/
    int add(DeviceScene deviceScene);
    /**通过device_id和userId查找*/
//    DeviceScene queryDAU(@Param("user_id") int userId, @Param("device_id") int deviceId);
    DeviceScene queryDAU(@Param("userId") int userId, @Param("deviceId") int deviceId);
//    /**删除设备绑定*/
    int delete(@Param("userId") int userId, @Param("deviceId") int deviceId);
    /**修改绑定设备名称*/
    int updataDeviceSceneName(@Param("userId") int userId, @Param("deviceId") int deviceId, @Param("classify") String classify, @Param("detail") String detail);
}
