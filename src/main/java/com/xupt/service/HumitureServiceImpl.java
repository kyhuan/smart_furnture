package com.xupt.service;


import com.xupt.common.ReturnMsg;
import com.xupt.mapper.HumitureMapper;

import com.xupt.pojo.HttpResult;
import com.xupt.pojo.Humiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumitureServiceImpl implements IHumitureService {
	@Autowired
	private HumitureMapper humitureMapper;

	@Override
	public HttpResult insert( int userId,int  cusSceneId, Double temperature, Double humidity) {
		HttpResult result = new HttpResult();
		Humiture humiture = new Humiture();

		humiture.setUserId(userId);
		humiture.setCusSceneId(cusSceneId);
		humiture.setTemperature(temperature);
		humiture.setHumidity(humidity);


		int a = humitureMapper.insert(humiture);
		if(a != 1){
			result.setCode("209");
			result.setMsg(ReturnMsg.Status_209);
			return result;
		}

		result.setCode("200");
		result.setMsg(ReturnMsg.Status_200);
		return result;
	}

	@Override
	public List<Humiture> findLastedHumiture() {
		List<Humiture> lastedHumiture = humitureMapper.findLastedHumiture();
		return lastedHumiture;
	}

	@Override
	public List<Humiture> findAllHumiture(){
		//HttpResult result=new HttpResult();


		List<Humiture> allHumiture = humitureMapper.findAllHumiture();

		return allHumiture;
	}
}
