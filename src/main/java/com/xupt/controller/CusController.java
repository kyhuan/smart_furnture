package com.xupt.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xupt.pojo.Customization;
import com.xupt.service.CusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/cus")
public class CusController {
    @Autowired
    private CusServiceImpl cusService;

    @GetMapping("/findAllCus")
    public List<Customization> findAll(HttpServletRequest request, HttpServletResponse response) {
        Gson gson =new GsonBuilder().create();
        List<Customization> allHumiture = cusService.findAllCus();
        return allHumiture;


    }

}
