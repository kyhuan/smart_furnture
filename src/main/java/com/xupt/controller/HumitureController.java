package com.xupt.controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xupt.common.UserAccess;
import com.xupt.data.GetTime;
import com.xupt.pojo.HttpResult;
import com.xupt.pojo.Humiture;
import com.xupt.service.DeviceService;
import com.xupt.service.HumitureServiceImpl;
import com.xupt.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/humiture")
public class HumitureController {
    @Autowired
    private HumitureServiceImpl humitureService;

    @Autowired
    private DeviceService deviceService;
        @GetMapping("/findAlls")
    public List<Humiture> findAll(HttpServletRequest request, HttpServletResponse response) {
        Gson gson =new GsonBuilder().create();
        List<Humiture> allHumiture = humitureService.findAllHumiture();
        return allHumiture;


    }
    @GetMapping("/findAll")
    public List<Humiture> findLastedAll(HttpServletRequest request, HttpServletResponse response) {
        Gson gson =new GsonBuilder().create();
        List<Humiture> allHumiture = humitureService.findLastedHumiture();
        return allHumiture;


    }


    @UserAccess
    @PostMapping("/insert")
    public void addHumiture(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();
        String userId = request.getParameter("userId");
        String cusSceneId = request.getParameter("cusSceneId");
        String temperature = request.getParameter("temperature");
        String humidity = request.getParameter("humidity");
        HttpResult result = humitureService.insert(Integer.valueOf(userId),Integer.valueOf(cusSceneId),Double.valueOf(temperature),Double.valueOf(humidity));
        Response.responseStr(response,gson.toJson(result));
    }
    @UserAccess
    @PostMapping("/ad")
    public void addHum(HttpServletRequest request, HttpServletResponse response){
        Gson gson =new GsonBuilder().create();

        String humidity = request.getParameter("humidity");
        String deviceId = request.getParameter("deviceId");
          deviceService.insHum(humidity,deviceId,GetTime.getTime());
          deviceService.instemp(humidity,deviceId,GetTime.getTime());
          deviceService.insLight(humidity,deviceId,GetTime.getTime());
          deviceService.insGas(humidity,deviceId,GetTime.getTime());

    }


}
