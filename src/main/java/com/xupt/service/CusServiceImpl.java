package com.xupt.service;

import com.xupt.mapper.CustomizationMapper;
import com.xupt.pojo.Customization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CusServiceImpl implements CusService{


    @Autowired
    private CustomizationMapper customizationMapper;
    @Override
    public List<Customization> findAllCus(){

        List<Customization> allCus = customizationMapper.findAllCus();
        return allCus;
    }

}
