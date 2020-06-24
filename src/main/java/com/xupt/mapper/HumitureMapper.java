package com.xupt.mapper;

import com.xupt.pojo.HttpResult;
import com.xupt.pojo.Humiture;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface HumitureMapper {
	@Select("select * from humiture")
	List<Humiture> findAllHumiture ();

	@Select("select * from humiture ORDER  BY id desc  limit 0,1 ")
	List<Humiture> findLastedHumiture();

	@Insert(" insert into humiture (user_id,cus_scene_id,temperature,humidity) values (#{userId} ,#{cusSceneId}, #{temperature}, #{humidity})")
	int insert(Humiture humiture);
//	@Insert(" insert into humiture (user_id,device_id,temperature,humidity) values (#{userId} ,#{cusSceneId}, #{temperature}, #{humidity})")
//	int saveHum(Humiture humiture);
}
