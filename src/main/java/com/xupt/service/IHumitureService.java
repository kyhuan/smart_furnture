package com.xupt.service;

import com.xupt.pojo.HttpResult;
import com.xupt.pojo.Humiture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHumitureService {
	List<Humiture> findAllHumiture ();
	List<Humiture> findLastedHumiture ();
	HttpResult insert( int userId,int cusSceneId, Double temperature, Double humidity);


}
