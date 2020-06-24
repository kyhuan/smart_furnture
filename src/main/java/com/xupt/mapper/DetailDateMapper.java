package com.xupt.mapper;

import com.xupt.pojo.*;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface DetailDateMapper {

    @Insert("insert into detail_date (device_id,user_id,device_state,date_value,program_value,curtain_status,cleaner_status,air_value) values (#{deviceId},#{userId},#{deviceState},1,1,0,0,1)")
    int insertDetailDate(@Param("deviceId") int deviceId, @Param("userId") int userId, @Param("deviceState") String deviceState);

    @Delete(" DELETE FROM detail_date WHERE user_id = #{userId} AND device_id = #{deviceId} ")
    int deleteDetailDate(@Param("userId") int userId, @Param("deviceId") int deviceId);

    @Update("  UPDATE detail_date SET device_state = #{deviceState} WHERE user_id = #{userId} AND device_id = #{deviceId} ")
    int updateDetailDate(@Param("deviceId") int deviceId, @Param("userId") int userId, @Param("deviceState") String deviceState);


    /**
     * 灯的控制
     *
     * @param lampState
     * @return
     */
    @Update("update detail_date set lamp_state =#{lampState} where device_id =#{deviceId} and user_id=#{userId}")
    int updateLamp(@Param("lampState") String lampState, @Param("deviceId") int deviceId, @Param("userId") int userId);

    /**
     * 一键关灯
     */
    @Update("update detail_date set lamp_state = 0*0 ,device_state = 0 where user_id=#{userId} and (device_id =1 or device_id =2 or device_id =3)  ")
    int updateAllLamp(@Param("userId") int userId);

    /**
     * 电视，音响  声音
     */
    @Update("update detail_date set date_value = date_value +1 where device_id =#{deviceId} and user_id=#{userId}")
    int updateAdd(@Param("deviceId") int deviceId, @Param("userId") int userId);

    @Update("update detail_date set date_value = date_value -1 where device_id =#{deviceId} and user_id=#{userId}")
    int updateDown(@Param("deviceId") int deviceId, @Param("userId") int userId);

    @Select("select * from detail_date where  device_id =#{deviceId} and user_id=#{userId} ")
    List<DetailDate> queryValue(@Param("deviceId") int deviceId, @Param("userId") int userId);

    /**
     * 电视，音响  节目
     */

    @Update("update detail_date set program_value = program_value +1 where device_id =#{deviceId} and user_id=#{userId}")
    int updateProAdd(@Param("deviceId") int deviceId, @Param("userId") int userId);

    @Update("update detail_date set program_value = program_value -1 where device_id =#{deviceId} and user_id=#{userId}")
    int updateProDown(@Param("deviceId") int deviceId, @Param("userId") int userId);

    @Update("update detail_date set program_value = #{programValue} where device_id =#{deviceId} and  user_id = #{userId}")
    int updatePro(@Param("programValue") String programValue, @Param("deviceId") int deviceId, @Param("userId") int userId);

    @Select("select * from detail_date where  device_id =#{deviceId} and user_i    d=#{userId} ")
    List<DetailDate> queryProgram(@Param("deviceId") int deviceId, @Param("userId") int userId);

    //    @Select("select * from detail_date")
////    List<DetailDate> queryDateValue();
    //所有状态
    @Select("select * from detail_date where user_id=#{userId} ")
    List<DetailDate> queryDate(@Param("userId") int userId);

    @Select("select * from detail_date where user_id=#{userId} and device_id =#{deviceId}")
    List<DetailDate> queryDateValue(@Param("userId") int userId, @Param("deviceId") int deviceId);

    /**
     * 窗帘
     */

    @Update("update detail_date set curtain_status =#{curtainStatus} where device_id =#{deviceId} and  user_id = #{userId}")
    int updateCurtainStatus(@Param("curtainStatus") String curtainStatus, @Param("deviceId") int deviceId, @Param("userId") int userId);


    /**
     * 净化器
     */
    @Update("update detail_date set cleaner_status =#{cleanerStatus} where  device_id =#{deviceId} and  user_id = #{userId}")
    int updateCleanerStatus(@Param("cleanerStatus") String cleanerStatus, @Param("deviceId") int deviceId, @Param("userId") int userId);

    /**
     * 空调温度
     */
    @Update("update detail_date set air_value = air_value +1 where device_id =#{deviceId} and user_id=#{userId}")
    int updateAirAdd(@Param("deviceId") int deviceId, @Param("userId") int userId);

    @Update("update detail_date set air_value = air_value -1 where device_id =#{deviceId} and user_id=#{userId}")
    int updateAirDown(@Param("deviceId") int deviceId, @Param("userId") int userId);

    @Update("update detail_date set air_value = #{airValue} where device_id =#{deviceId} and  user_id = #{userId}")
    int updateAir(@Param("airValue") String airValue, @Param("deviceId") int deviceId, @Param("userId") int userId);

    /**
     * 离家模式
     */
    @Update("update detail_date set device_state = 0 where user_id=#{userId} and (device_id =1 or device_id =2 or device_id =3 or device_id = 9 or device_id = 6  )")
    int updateAll(@Param("userId") int userId);

    /**
     * 回家模式
     */
    @Update("update detail_date set device_state = 1 where user_id=#{userId} and (device_id =1 or device_id =2 or device_id =3 or device_id = 9 or device_id = 6 ) ")
    int onAll(@Param("userId") int userId);


    /**
     * 保存温湿度数据
     */
    @Insert(" insert into temperature (temperature,device_id,time) values (#{temperature},#{deviceId},#{time})")
    void instemp(@Param("temperature") String temperature,@Param("deviceId") String deviceId, @Param("time") Timestamp time);

    @Insert(" insert into humidity (humidity,device_id,time) values (#{humidity},#{deviceId},#{time})")
    void insHum(@Param("humidity") String humidity,@Param("deviceId") String deviceId, @Param("time") Timestamp time);


    /**
     * 查询温湿度数据
     */
    @Select("select * from temperature")
    List<Temperature> queryTemp(@Param("deviceId") String deviceId);
    @Select("select * from humidity")
    List<Humidity> queryHum(@Param("deviceId") String deviceId);
    /**
     * 保存光照数据
     */
    @Insert(" insert into light (device_id,light_value,time) values (#{deviceId},#{lightValue},#{time})")
    void insLight(@Param("deviceId") String deviceId, @Param("lightValue") String lightValue, @Param("time") Timestamp time);
    @Select("select * from light")
    List<Light> queryLight(@Param("deviceId") String deviceId);
    /**
     * 保存烟雾数据
     */
    @Insert(" insert into temperature (device_id,gas_value,time) values (#{deviceId},#{gasValue},#{time})")
    void insGas(@Param("deviceId") String deviceId,@Param("gasValue") String lightValue, @Param("time") Timestamp time);
    @Select("select * from gas")
    List<Gas> queryGas(@Param("deviceId") String deviceId);
}
