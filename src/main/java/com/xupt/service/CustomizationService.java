package com.xupt.service;


import com.xupt.pojo.HttpResult;

public interface CustomizationService {
    /**获取定制场景列表*/
    HttpResult getCustomizationList(int userId, int cusId);
    /**执行场景*/
   HttpResult execute(int userId, int cusId);
    /**删除场景*/
    HttpResult deleteCusSeene(int userId,  int deviceId);
    /**增加定制场景设备*/
    HttpResult addCusScene(int userId, int cusId, int deviceId, String operation);
    /**更改定制场景操作*/
    HttpResult updataCusScene(int userId, int cusId, int deviceId, String operation);
    /**获取所有设备*/
    HttpResult getDeviceList(int userId);


}
