package com.xupt.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.xupt.common.UserAccess;
import com.xupt.pojo.DetailDate;
import com.xupt.pojo.DeviceScene;
import com.xupt.pojo.HttpResult;
import com.xupt.service.DeviceService;
import com.xupt.utils.Response;

import com.xupt.websocket.GateWebSocket;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

import static com.xupt.websocket.GateWebSocket.gateWebSocket;
import static com.xupt.websocket.GateWebSocket.gatewebSocketSet;

/**
 * @author kyh
 * @email
 * @date 2020/4/7 11:09
 * Description:
 **/
@RestController
@RequestMapping("/operate/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @UserAccess
    @PostMapping("/cusScene")
    public void getCusScene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusId = request.getParameter("cusId");
        HttpResult result = deviceService.getCusDeviceList(Integer.valueOf(userId),Integer.valueOf(cusId));
        Response.responseStr(response, gson.toJson(result));
    }


        @RequestMapping(value="/test")
        public String testF2F() {
            return "login";

        }

        /***
     * 获取设备场景
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/scene")
    public void scene(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        HttpResult result = deviceService.getScene(Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }

//    /***
//     * 获取音量
//     * @param request
//     * @param response
//     */
//    @UserAccess
//    @PostMapping("/value")
//    public void queryValue(HttpServletRequest request, HttpServletResponse response){
//        Gson gson =new GsonBuilder().create();
//        String userId = request.getParameter("userId");
//        String deviceId = request.getParameter("deviceId");
//        HttpResult httpResult = deviceService.queryValue(Integer.valueOf(userId), Integer.valueOf(deviceId));
//        Response.responseStr(response, gson.toJson(httpResult));
//    }
//    /***
//     * 获取节目
//     * @param request
//     * @param response
//     */
//    @UserAccess
//    @PostMapping("/program")
//    public void queryProgram(HttpServletRequest request, HttpServletResponse response){
//        Gson gson =new GsonBuilder().create();
//        String userId = request.getParameter("userId");
//        String deviceId = request.getParameter("deviceId");
//        HttpResult httpResult = deviceService.queryProgram(Integer.valueOf(userId), Integer.valueOf(deviceId));
//        Response.responseStr(response, gson.toJson(httpResult));
//    }
    /**
     * 绑定设备场景
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/bind")
    public void bind(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DeviceScene deviceScene = new DeviceScene();
        String userId = request.getParameter("userId");
        String deviceCode = request.getParameter("deviceCode");
        String devicePassword = request.getParameter("devicePassword");
        String classify = request.getParameter("classify");
        String detail = request.getParameter("detail");
        deviceScene.setUserId(Integer.valueOf(userId));
        deviceScene.setClassify(classify);
        deviceScene.setDetail(detail);
        HttpResult result = deviceService.bindScene(deviceScene,deviceCode,devicePassword);
        Response.responseStr(response, gson.toJson(result));
    }

    /**
     * 操作记录
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/record")
    public void record(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        HttpResult result = deviceService.records(Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }

    /***
     * 操作设备
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/control")
    public void control(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        String state = request.getParameter("state");
        HttpResult result =deviceService.control(Integer.valueOf(userId),Integer.valueOf(deviceId),state);
        HttpResult httpResult = deviceService.updateDetailDate(Integer.valueOf(deviceId),Integer.valueOf(userId),  state);
        Response.responseStr(response, gson.toJson(result),gson.toJson(httpResult));
    }

    /***
     * 删除设备绑定
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        HttpResult result = deviceService.delete(Integer.valueOf(userId),Integer.valueOf(deviceId));
        Response.responseStr(response, gson.toJson(result));
    }

    /**
     * 修改设备信息
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updata")
    public void updata(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DeviceScene deviceScene = new DeviceScene();
        deviceScene.setDeviceId(Integer.valueOf(request.getParameter("deviceId")));
        deviceScene.setUserId(Integer.valueOf(request.getParameter("userId")));
        deviceScene.setClassify(request.getParameter("useScene"));
        deviceScene.setDetail(request.getParameter("detail"));
        String oldDevicePassword = request.getParameter("oldDevicePassword");
        String newDevicePassword = request.getParameter("newDevicePassword");
        HttpResult result = deviceService.updata(deviceScene,oldDevicePassword,newDevicePassword);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 修改lamp颜色
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updatelampcolor")
    public void updataLampColor(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DetailDate detailDate = new DetailDate();
        String lampState = request.getParameter("lampState");
        String deviceId1 = request.getParameter("deviceId");
        Integer deviceId = Integer.valueOf(deviceId1);
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        detailDate.setLampState(lampState);
        detailDate.setDeviceId(deviceId);
        detailDate.setUserId(userId);


        if (lampState.equals("0")){
            deviceService.updateDetailDate(deviceId,userId,lampState);
        }else {
            deviceService.updateDetailDate(deviceId,userId,"1");
        }

        HttpResult result = deviceService.updateLamp(detailDate);

        System.out.println("lamp:"+lampState);

        if (gateWebSocket!=null) {

            System.out.println("buweikong");

            gateWebSocket.sendm(lampState);
        }else {
            System.out.println("kong");
        }


        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 一键关灯
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/updatealllamp")
    public void updataAllLamp(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DetailDate detailDate = new DetailDate();
        detailDate.setUserId(Integer.valueOf(request.getParameter("userId")));

        HttpResult result = deviceService.updateAllLamp(detailDate);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 离家模式
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/updateall")
    public void updataAll(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DetailDate detailDate = new DetailDate();
        detailDate.setUserId(Integer.valueOf(request.getParameter("userId")));

        HttpResult result = deviceService.updateAll(detailDate);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 在家模式
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/onall")
    public void onAll(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DetailDate detailDate = new DetailDate();
        detailDate.setUserId(Integer.valueOf(request.getParameter("userId")));

        HttpResult result = deviceService.onAll(detailDate);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 增加音量
     * @param request
     * @param response
     */
    @UserAccess
//    @ApiOperation(value = "desc of method", notes = "")
    @PostMapping("/updateadd")
    public void updataAdd( HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");


        HttpResult result = deviceService.updateAdd(Integer.valueOf(deviceId),Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * jianshao
     * @param request
     * @param response
     */
    @UserAccess

        @PostMapping("/updatedown")
    public void updateDown(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");

        HttpResult result = deviceService.updateDown(Integer.valueOf(deviceId),Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 增加节目
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updateproadd")
    public void updateProAdd(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");

        HttpResult result = deviceService.updateProAdd(Integer.valueOf(deviceId),Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * jianshao
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updateprodown")
    public void updateProDown(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();

        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        HttpResult result = deviceService.updateProDown(Integer.valueOf(deviceId),Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }


    /**
     * 修改节目值
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updatepro")
    public void updatePro(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        String programValue = request.getParameter("programValue");

        HttpResult result = deviceService.updatePro(programValue,Integer.valueOf(deviceId),Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }

    /**
     * 增加空调温度
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updateairadd")
    public void updateAirAdd(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");

        HttpResult result = deviceService.updateAirAdd(Integer.valueOf(deviceId),Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * jianshao空调温度
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updateairdown")
    public void updateAirDown(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();

        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        HttpResult result = deviceService.updateAirDown(Integer.valueOf(deviceId),Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }


    /**
     * 修改空调温度
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updateair")
    public void updateAir(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        String airValue = request.getParameter("airValue");

        HttpResult result = deviceService.updateAir(airValue,Integer.valueOf(deviceId),Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }






    /**
     * 获取音量，节目值
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/querydatevalue")
    public void queryDateValue(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String deviceId = request.getParameter("deviceId");
        HttpResult result = deviceService.queryDateValue(Integer.valueOf(userId),Integer.valueOf(deviceId));
        Response.responseStr(response, gson.toJson(result));
    }

    /**
     * 获取所有值
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/querydate")
    public void queryDate(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");

        HttpResult result = deviceService.queryDate(Integer.valueOf(userId));
        Response.responseStr(response, gson.toJson(result));
    }

    /**
     * 获取温度
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/querytemp")
    public void queryTemp(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String deviceId = request.getParameter("deviceId");

        HttpResult result = deviceService.queryTemp(deviceId);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 获取湿度
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/queryhum")
    public void queryHum(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String deviceId = request.getParameter("deviceId");

        HttpResult result = deviceService.queryHum(deviceId);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 获取光照
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/querylight")
    public void queryLight(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String deviceId = request.getParameter("deviceId");

        HttpResult result = deviceService.queryLight(deviceId);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 获取烟雾
     * @param request
     * @param response
     */
    @UserAccess
    @GetMapping("/querygas")
    public void queryGas(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String deviceId = request.getParameter("deviceId");

        HttpResult result = deviceService.queryGas(deviceId);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 修改窗帘
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updatecur")
    public void updateCurtainStatus(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DetailDate detailDate = new DetailDate();
        String curtainStatus = request.getParameter("curtainStatus");
        Integer deviceId = Integer.valueOf(request.getParameter("deviceId"));
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        detailDate.setCurtainStatus(curtainStatus);
        detailDate.setDeviceId(deviceId);
        detailDate.setUserId(userId);


        if (curtainStatus.equals("0")){
            deviceService.updateDetailDate(deviceId,userId,curtainStatus);
        }else {
            deviceService.updateDetailDate(deviceId,userId,"1");
        }
        HttpResult result = deviceService.updateCurtainStatus(detailDate);
        Response.responseStr(response, gson.toJson(result));
    }
    /**
     * 修改净化器
     * @param request
     * @param response
     */
    @UserAccess
    @PostMapping("/updateclear")
    public void updateCleanerStatus(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        DetailDate detailDate = new DetailDate();
        String cleanerStatus = request.getParameter("cleanerStatus");

        Integer deviceId = Integer.valueOf(request.getParameter("deviceId"));
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        detailDate.setCleanerStatus(cleanerStatus);
        detailDate.setDeviceId(deviceId);
        detailDate.setUserId(userId);


        if (cleanerStatus.equals("0")){
            deviceService.updateDetailDate(deviceId,userId,cleanerStatus);
        }else {
            deviceService.updateDetailDate(deviceId,userId,"1");
        }
        HttpResult result = deviceService.updateCleanerStatus(detailDate);
        Response.responseStr(response, gson.toJson(result));
    }



}
