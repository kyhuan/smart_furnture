package com.xupt.mapper;

import com.xupt.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceMapper {
    /**添加设备*/
    int add(Device device);
    /**通过uerId和cusId获取场景列表*/
    List<Device> queryByUAD(@Param("userId") int userId, @Param("cusId") int cusId);
    /**通过code与password查找*/
    Device queryByCAP(@Param("device_code") String deviceCode, @Param("device_password") String devicePassword);
    /**通过code查找*/
    Device queryByCode(@Param("device_code") String deviceCode);
    /**更改设备状态*/
    int updataDeviceState(@Param("deviceId") int deviceId, @Param("deviceState") String deviceState);
    /**通过id*/
    Device queryById(int deviceId);
    /**修改设备密码*/
    int updataDevicePassword(@Param("device_id") int deviceId, @Param("newPassword") String newPassword, @Param("oldPassword") String oldPassword);
}
