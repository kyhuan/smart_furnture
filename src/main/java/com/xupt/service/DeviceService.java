package com.xupt.service;


import com.xupt.pojo.DetailDate;
import com.xupt.pojo.DeviceScene;
import com.xupt.pojo.HttpResult;

import java.sql.Timestamp;

public interface DeviceService {
    /**通过userid获取设备场景列表*/
   HttpResult getScene(int userId);
   /**获取定制场景列表*/
   HttpResult getCusDeviceList(int userId, int cusId);
   /**绑定设备场景*/
   HttpResult bindScene(DeviceScene deviceScene, String deviceCode, String devicePassword);
   /**设备操作记录*/
   HttpResult records(int userId);
   /**操作设备打开关闭并记录*/
   HttpResult control(int userId, int deviceId, String deviceState);
   /**删除设备绑定*/
   HttpResult delete(int userId, int deviceId);
   /**修改设备信息*/
   HttpResult updata(DeviceScene deviceScene, String oldPassword, String newPassword);
   /**语音控制*/
   HttpResult voiceControl(int userId, String message);
   /**操作灯颜色*/
   HttpResult updateLamp(DetailDate detailDate);

   /**一键关灯*/
   HttpResult updateAllLamp(DetailDate detailDate);
   /**离家模式*/
   HttpResult updateAll(DetailDate detailDate);
   /**回家模式*/
   HttpResult onAll(DetailDate detailDate);


   /**增加音量*/
   HttpResult updateAdd(int deviceId,int userId);
   /**减少音量*/
   HttpResult updateDown(int deviceId,int userId);
   /**音量值*/
   HttpResult queryDateValue(int userId,int deviceId);
   /**所有值*/
   HttpResult queryDate(int userId);
   /**增加节目*/
   HttpResult updateProAdd(int deviceId,int userId);
   /**减少节目*/
   HttpResult updateProDown(int deviceId,int userId);

//   /**查找节目*/
//   HttpResult queryProgram(int deviceId, int userId);
//   /**查找音量*/
//   HttpResult queryValue(int deviceId, int userId);

   /**选择节目*/
   HttpResult updatePro(String programValue,int deviceId,int userId);
   /**增加节目*/
   HttpResult updateAirAdd(int deviceId,int userId);
   /**减少节目*/
   HttpResult updateAirDown(int deviceId,int userId);
   /**选择节目*/
   HttpResult updateAir(String airValue,int deviceId,int userId);
   /**操作窗帘状态*/
   HttpResult updateCurtainStatus(DetailDate detailDate);
   /**操作净化器状态*/
   HttpResult updateCleanerStatus(DetailDate detailDate);
   /**增加详细数据记录*/
   HttpResult insertDetailDate(int deviceId,int userId, String deviceState);
   /**删除详细数据记录*/
   HttpResult deleteDetailDate(int userId,int deviceId );

   /**修改详细数据记录*/
   HttpResult updateDetailDate(int deviceId,int userId, String deviceState);

   void instemp(String temperature,String deviceId, Timestamp time);

   void insHum(String humidity, String deviceId,Timestamp time);
   void insLight(String deviceId,String temperature, Timestamp time);

   void insGas(String deviceId,String humidity, Timestamp time);
   /**通过deviceid获取温湿度烟雾光照数据*/
   HttpResult queryTemp(String deviceId);
   HttpResult queryHum(String deviceId);
   HttpResult queryLight(String deviceId);
   HttpResult queryGas(String deviceId);
}
