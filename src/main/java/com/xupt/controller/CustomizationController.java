package com.xupt.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.xupt.common.UserAccess;
import com.xupt.pojo.DetailDate;
import com.xupt.pojo.HttpResult;
import com.xupt.service.CustomizationService;
import com.xupt.service.DeviceService;
import com.xupt.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.xupt.websocket.GateWebSocket.gateWebSocket;


@RestController
@RequestMapping("/operate/customization")
public class CustomizationController {

    @Autowired
    CustomizationService customizationService;

    @Autowired
    DeviceService deviceService;
    /***
     * 获取定制场景列表
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/cusScene")
    public void getCusScene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        HttpResult result = customizationService.getCustomizationList(Integer.valueOf(userId),Integer.valueOf(cusId));
        Response.responseStr(response, gson.toJson(result));
    }

    /***
     * 执行场景
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/execute")
    public void execute(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        HttpResult result = customizationService.execute(Integer.valueOf(userId),Integer.valueOf(cusId));
        Response.responseStr(response,gson.toJson(result));
    }

    /***
     * 增加定制场景设备
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/cusSceneAdd")
    public void addCusScene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        String deviceId = request.getParameter("deviceId");
        String operation = request.getParameter("operation");
        HttpResult result = customizationService.addCusScene(Integer.valueOf(userId),Integer.valueOf(cusId),Integer.valueOf(deviceId),operation);
        HttpResult httpResult = deviceService.insertDetailDate(Integer.valueOf(deviceId), Integer.valueOf(userId), operation);
        Response.responseStr(response,gson.toJson(result),gson.toJson(httpResult));
    }

    /***
     * 更改定制场景操作
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/cusSceneUpdata")
    public void updataCusScene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        String deviceId = request.getParameter("deviceId");
        String operation = request.getParameter("operation");
        HttpResult control = deviceService.control(Integer.valueOf(userId), Integer.valueOf(cusId), operation);
        HttpResult result = customizationService.updataCusScene(Integer.valueOf(userId),Integer.valueOf(cusId),Integer.valueOf(deviceId),operation);
        HttpResult httpResult = deviceService.updateDetailDate(Integer.valueOf(deviceId),Integer.valueOf(userId), operation);


        if (gateWebSocket!=null) {

            System.out.println("buweikong");
            gateWebSocket.sendm(operation);
        }else {
            System.out.println("kong");
        }
        Response.responseStr(response,gson.toJson(result),gson.toJson(control),gson.toJson(httpResult));
    }


    /***
     * 删除定制场景操作
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/cusscenedelete")
    public void deleteCusScene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DetailDate detailDate = new DetailDate();
        String userId = request.getParameter("userId");

        String deviceId = request.getParameter("deviceId");
        detailDate.setUserId(Integer.valueOf(userId));
        detailDate.setDeviceId(Integer.valueOf(deviceId));

        HttpResult result = customizationService.deleteCusSeene(Integer.valueOf(userId),Integer.valueOf(deviceId));
        HttpResult httpResult = deviceService.deleteDetailDate(Integer.valueOf(userId),Integer.valueOf(deviceId));
        Response.responseStr(response,gson.toJson(result),gson.toJson(httpResult));
    }
    @UserAccess
    @PostMapping("/de")
    public void delete(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DetailDate detailDate = new DetailDate();
        String userId = request.getParameter("userId");

        String deviceId = request.getParameter("deviceId");
        detailDate.setUserId(Integer.valueOf(userId));
        detailDate.setDeviceId(Integer.valueOf(deviceId));


        HttpResult httpResult = deviceService.deleteDetailDate(Integer.valueOf(userId),Integer.valueOf(deviceId));
        Response.responseStr(response,gson.toJson(httpResult));
    }
    /**
     * 获取所有设备
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/getDeviceList")
    public void getDeviceList(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        HttpResult result = customizationService.getDeviceList(Integer.valueOf(userId));
        Response.responseStr(response,gson.toJson(result));
    }
}
