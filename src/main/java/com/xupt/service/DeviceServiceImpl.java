package com.xupt.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xupt.Socket.SendDevice;
import com.xupt.Socket.SocketServer;
import com.xupt.common.ReturnMsg;
import com.xupt.mapper.DetailDateMapper;
import com.xupt.mapper.DeviceMapper;
import com.xupt.mapper.DeviceSceneMapper;
import com.xupt.mapper.RecordsMapper;
import com.xupt.pojo.*;
import com.xupt.pojo.sub.RecordSub;
import com.xupt.pojo.sub.SceneSub;
import com.xupt.utils.TimeUtil;
import com.xupt.utils.VoiceRecogintionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceSceneMapper deviceSceneMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private RecordsMapper recordsMapper;
    @Autowired
    private DetailDateMapper detailDateMapper;

//    @Override
//    public HttpResult queryProgram(int deviceId, int userId) {
//        HttpResult result = new HttpResult();
//        List<DetailDate> list = detailDateMapper.queryProgram(deviceId, userId);
//        for (DetailDate detailDate : list) {
//            System.out.println(detailDate);
//        }
//
//        result.setCode("200");
//        result.setMsg(ReturnMsg.Status_200);
//        if (list != null) {
//            List<DetailDate> detailDateList = new ArrayList<>();
//            for (int i = 0; i < list.size(); i++) {
//                DetailDate detailDate = new DetailDate();
//
//                detailDate.setProgramValue(list.get(i).getProgramValue());
//
//                detailDateList.add(detailDate);
//            }
//            Gson gson = new GsonBuilder().create();
//            String data = gson.toJson(detailDateList);
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.add("list", new JsonParser().parse(data).getAsJsonArray());
//            result.setData(jsonObject);
//        }
//        return result;
//    }
//
//    @Override
//    public HttpResult queryValue(int deviceId, int userId) {
//        HttpResult result = new HttpResult();
//        List<DetailDate> list = detailDateMapper.queryValue(deviceId, userId);
//
//        result.setCode("200");
//        result.setMsg(ReturnMsg.Status_200);
//        if (list != null) {
//            List<DetailDate> detailDateList = new ArrayList<>();
//            for (int i = 0; i < list.size(); i++) {
//                DetailDate detailDate = new DetailDate();
//                detailDate.setDateValue(list.get(i).getDateValue());
//
//                detailDateList.add(detailDate);
//            }
//            Gson gson = new GsonBuilder().create();
//            String data = gson.toJson(detailDateList);
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.add("list", new JsonParser().parse(data).getAsJsonArray());
//            result.setData(jsonObject);
//        }
//        return result;
//    }




    @Override
    public void insLight(String deviceId, String lightValue, Timestamp time) {
        detailDateMapper.insLight(deviceId,lightValue,time);
    }

    @Override
    public void insGas(String deviceId, String gasValue, Timestamp time) {
        detailDateMapper.insLight(deviceId,gasValue,time);

    }

    @Override
    public HttpResult queryTemp(String deviceId) {
        HttpResult result = new HttpResult();
        List<Temperature> temperatures = detailDateMapper.queryTemp(deviceId);

        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);

        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(temperatures);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("temperatures", new JsonParser().parse(data).getAsJsonArray());
        result.setData(jsonObject);

        return result;
    }

    @Override
    public HttpResult queryHum(String deviceId) {
        HttpResult result = new HttpResult();
        List<Humidity> humidities = detailDateMapper.queryHum(deviceId);

        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);

        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(humidities);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("humidities", new JsonParser().parse(data).getAsJsonArray());
        result.setData(jsonObject);

        return result;
    }

    @Override
    public HttpResult queryLight(String deviceId) {
        HttpResult result = new HttpResult();
        List<Light> lights = detailDateMapper.queryLight(deviceId);

        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);

        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(lights);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("lights", new JsonParser().parse(data).getAsJsonArray());
        result.setData(jsonObject);

        return result;
    }

    @Override
    public HttpResult queryGas(String deviceId) {
        HttpResult result = new HttpResult();
        List<Gas> gases = detailDateMapper.queryGas(deviceId);

        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);

        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(gases);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("gases", new JsonParser().parse(data).getAsJsonArray());
        result.setData(jsonObject);

        return result;
    }

    @Override
    public HttpResult updateAirAdd(int deviceId, int userId) {
        HttpResult result = new HttpResult();

        int count = detailDateMapper.updateAirAdd(deviceId, userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateAirDown(int deviceId, int userId) {
        HttpResult result = new HttpResult();
        int count = detailDateMapper.updateAirDown(deviceId, userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateAir(String airValue, int deviceId, int userId) {
        HttpResult result = new HttpResult();
        int count = detailDateMapper.updateAir(airValue, deviceId, userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public void instemp(String deviceId, String temperature, Timestamp time) {

        detailDateMapper.instemp(deviceId,temperature,time);
    }

    @Override
    public void insHum(String deviceId, String humidity, Timestamp time) {
        detailDateMapper.insHum(deviceId,humidity,time);
    }

    //修改derail的值
    @Override
    public HttpResult updateDetailDate(int deviceId, int userId, String deviceState) {
        HttpResult result = new HttpResult();


        int count = detailDateMapper.updateDetailDate(deviceId, userId, deviceState);

        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult deleteDetailDate( int userId,int deviceId) {
        HttpResult result = new HttpResult();
//        int userId = detailDate.getUserId();
//        int deviceId = detailDate.getDeviceId();
        int count = detailDateMapper.deleteDetailDate( userId,deviceId);
        System.out.println(count);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);

        }else {
            result.setCode("210");
            result.setMsg(ReturnMsg.Status_210);

        }
        return result;
    }

    @Override
    public HttpResult insertDetailDate(int deviceId, int userId, String deviceState) {
        HttpResult result = new HttpResult();

        int count = detailDateMapper.insertDetailDate(deviceId, userId, deviceState);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("206");
        result.setMsg(ReturnMsg.Status_206);
        return result;
    }

    @Override
    public HttpResult updateCleanerStatus(DetailDate detailDate) {
        HttpResult result = new HttpResult();
        String cleanerStatus = detailDate.getCleanerStatus();
        int deviceId = detailDate.getDeviceId();
        int userId = detailDate.getUserId();
        int count = detailDateMapper.updateCleanerStatus(cleanerStatus,deviceId,userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateCurtainStatus(DetailDate detailDate) {
        HttpResult result = new HttpResult();
        String curtainStatus = detailDate.getCurtainStatus();
        int deviceId = detailDate.getDeviceId();
        int userId = detailDate.getUserId();
        int count = detailDateMapper.updateCurtainStatus(curtainStatus,deviceId,userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updatePro(String programValue, int deviceId, int userId) {
        HttpResult result = new HttpResult();
        int count = detailDateMapper.updatePro(programValue, deviceId, userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateProAdd(int deviceId, int userId) {
        HttpResult result = new HttpResult();

        int count = detailDateMapper.updateProAdd(deviceId, userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateProDown(int deviceId, int userId) {
        HttpResult result = new HttpResult();
        int count = detailDateMapper.updateProDown(deviceId, userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateAdd(int deviceId, int userId) {
        HttpResult result = new HttpResult();

        int count = detailDateMapper.updateAdd(deviceId, userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }


    @Override
    public HttpResult updateDown(int deviceId, int userId) {
        HttpResult result = new HttpResult();
        int count = detailDateMapper.updateDown(deviceId, userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateAllLamp(DetailDate detailDate) {
        HttpResult result = new HttpResult();
        int userId = detailDate.getUserId();
        int count = detailDateMapper.updateAllLamp(userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateAll(DetailDate detailDate) {
        HttpResult result = new HttpResult();
        int userId = detailDate.getUserId();
        int count = detailDateMapper.updateAll(userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult onAll(DetailDate detailDate) {
        HttpResult result = new HttpResult();
        int userId = detailDate.getUserId();
        int count = detailDateMapper.onAll(userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult updateLamp(DetailDate detailDate) {
        HttpResult result = new HttpResult();
        String lampState = detailDate.getLampState();
        int userId = detailDate.getUserId();
        int deviceId = detailDate.getDeviceId();
        int count = detailDateMapper.updateLamp(lampState,deviceId,userId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    //    @Override
//    public HttpResult queryDateValue() {
//        HttpResult result = new HttpResult();
//        List<DetailDate> detailDates = detailDateMapper.queryDateValue();
//
//        result.setCode("200");
//        result.setMsg(ReturnMsg.Status_200);
//
//        Gson gson = new GsonBuilder().create();
//        String data = gson.toJson(detailDates);
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.add("detailDates", new JsonParser().parse(data).getAsJsonArray());
//        result.setData(jsonObject);
//
//        return result;
//    }
    @Override
    public HttpResult queryDateValue(int userId,int deviceId) {
        HttpResult result = new HttpResult();
        List<DetailDate> detailDates = detailDateMapper.queryDateValue(userId,deviceId);

        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);

        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(detailDates);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("detailDates", new JsonParser().parse(data).getAsJsonArray());
        result.setData(jsonObject);

        return result;
    }

    @Override
    public HttpResult queryDate(int userId) {
        HttpResult result = new HttpResult();
        List<DetailDate> detailDates = detailDateMapper.queryDate(userId);

        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);

        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(detailDates);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("detailDates", new JsonParser().parse(data).getAsJsonArray());
        result.setData(jsonObject);

        return result;
    }

    @Override
    public HttpResult getCusDeviceList(int userId, int cusId) {
        HttpResult result = new HttpResult();
        List<Device> list = deviceMapper.queryByUAD(userId, cusId);
        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);
        if (list != null) {
            List<Device> cusSceneSubsList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Device cusSceneSub = new Device();
                cusSceneSub.setDeviceState(list.get(i).getDeviceState());
                cusSceneSub.setDeviceChName(list.get(i).getDeviceChName());
                cusSceneSub.setDeviceCode(list.get(i).getDeviceCode());
                cusSceneSub.setDeviceName(list.get(i).getDeviceName());
                cusSceneSub.setDevicePassword(list.get(i).getDevicePassword());
                cusSceneSub.setAction(list.get(i).getAction());
                cusSceneSub.setClassId(list.get(i).getClassId());
                cusSceneSub.setImgSrc(list.get(i).getImgSrc());
                cusSceneSub.setIsAdd(list.get(i).getIsAdd());
                cusSceneSub.setDeviceId(list.get(i).getDeviceId());
                cusSceneSubsList.add(cusSceneSub);
            }
            Gson gson = new GsonBuilder().create();
            String data = gson.toJson(cusSceneSubsList);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("list", new JsonParser().parse(data).getAsJsonArray());
            result.setData(jsonObject);
        }
        return result;
    }

    /***
     * 获取设备场景
     * @param userId
     * @return
     */
    @Override
    public HttpResult getScene(int userId) {
        HttpResult result = new HttpResult();
        List<DeviceScene> list = deviceSceneMapper.queryByUserId(userId);
        List<SceneSub> listSub = new ArrayList<>();
        if (list == null) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            return result;
        }

        for (int i = 0; i < list.size(); i++) {
            Device device = deviceMapper.queryById(list.get(i).getDeviceId());
            SceneSub sceneSub = new SceneSub();
            sceneSub.setDeviceId(list.get(i).getDeviceId());
            sceneSub.setDetail(list.get(i).getDetail());
            sceneSub.setState(device.getDeviceState());
            sceneSub.setClassify(list.get(i).getClassify());
            listSub.add(sceneSub);
        }
        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);
        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(listSub);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("list", new JsonParser().parse(data).getAsJsonArray());
        result.setData(jsonObject);
        return result;
    }

    /***
     * 绑定设备场景
     * @param deviceScene
     * @param code
     * @param password
     * @return
     */
    @Override
    public HttpResult bindScene(DeviceScene deviceScene, String code, String password) {
        HttpResult result = new HttpResult();
        Device device = deviceMapper.queryByCAP(code, password);
        if (device == null) {
            result.setCode("207");
            result.setMsg(ReturnMsg.Status_207);
        } else {
            deviceScene.setDeviceId(device.getDeviceId());
            DeviceScene deviceScene1 = deviceSceneMapper.queryDAU(deviceScene.getUserId(), device.getDeviceId());
            if (deviceScene1 == null) {
                int addResult = deviceSceneMapper.add(deviceScene);
                if (addResult != 1) {
                    result.setCode("206");
                    result.setMsg(ReturnMsg.Status_206);
                } else {
                    result.setCode("200");
                    result.setMsg(ReturnMsg.Status_200);
                }
            } else {
                result.setCode("211");
                result.setMsg(ReturnMsg.Status_211);
            }

        }
        return result;
    }

    /***
     * 获取操作记录
     * @param userId
     * @return
     */
    @Override
    public HttpResult records(int userId) {
        HttpResult result = new HttpResult();
        Gson gson = new GsonBuilder().create();
        List<RecordSub> recordSubList = new ArrayList<>();
        List<Records> records = recordsMapper.queryByUserId(userId);
        result.setCode("200");
        result.setMsg(ReturnMsg.Status_200);
        if (records != null) {
            for (int i = 0; i < records.size(); i++) {
                RecordSub recordSub = new RecordSub();
                recordSub.setDeviceId(records.get(i).getDeviceId() + "");
                recordSub.setType(records.get(i).getType());
                recordSub.setState(records.get(i).getState());
                recordSub.setTime(records.get(i).getTime());
                DeviceScene deviceScene = deviceSceneMapper.queryDAU(userId, records.get(i).getDeviceId());
                if (deviceScene == null) {
                    return result;
                }
                recordSub.setUseScene(deviceScene.getClassify());
                recordSub.setDetail(deviceScene.getDetail());
                recordSubList.add(recordSub);
            }
            String data = gson.toJson(recordSubList);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("list", new JsonParser().parse(data).getAsJsonArray());
            result.setData(jsonObject);
        }
        return result;
    }

    @Override
    public HttpResult control(int userId, int deviceId, String deviceState) {
        HttpResult result = new HttpResult();
        Socket client = SocketServer.socketMap.get(deviceId + "");
        Boolean isClosed = SendDevice.isServerClose(client);
//        if(isClosed){
//            result.setCode("215");
//            result.setMsg(ReturnMsg.Status_215);
//            return result;
//        }
//        boolean operateResult = SendDevice.sendArdunioDevice(deviceId,state);
//        if(!operateResult){
//            result.setCode("214");
//            result.setMsg(ReturnMsg.Status_214);
//           return result;
//        }
        int count = deviceMapper.updataDeviceState(deviceId, deviceState);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
            Records records = new Records();
            records.setUserId(userId);
            records.setDeviceId(deviceId);
            records.setState(deviceState);
            records.setType("1");
            records.setTime(TimeUtil.getTimeData());
            int isSuccess = recordsMapper.add(records);
            if (isSuccess != 1) {
                result.setCode("209");
                result.setMsg(ReturnMsg.Status_209);
            } else {
                result.setCode("200");
                result.setMsg(ReturnMsg.Status_200);
            }
        } else {
            result.setCode("208");
            result.setMsg(ReturnMsg.Status_208);
        }
        return result;
    }


    @Override
    public HttpResult delete(int userId, int deviceId) {
        HttpResult result = new HttpResult();
        int count = deviceSceneMapper.delete(userId, deviceId);
        if (count > 0) {
            result.setCode("200");
            result.setMsg(ReturnMsg.Status_200);
        } else {
            result.setCode("210");
            result.setMsg(ReturnMsg.Status_210);
        }
        return result;
    }

    @Override
    public HttpResult updata(DeviceScene deviceScene, String oldPassword, String newPassword) {
        HttpResult result = new HttpResult();
        int count = deviceMapper.updataDevicePassword(deviceScene.getDeviceId(), newPassword, oldPassword);
        if (count > 0) {
            int resultCount = deviceSceneMapper.updataDeviceSceneName(deviceScene.getUserId(), deviceScene.getDeviceId(), deviceScene.getClassify(), deviceScene.getDetail());
            if (resultCount > 0) {
                result.setCode("200");
                result.setMsg(ReturnMsg.Status_200);
                return result;
            }
            //回退
            deviceMapper.updataDevicePassword(deviceScene.getDeviceId(), oldPassword, newPassword);
        }
        result.setCode("208");
        result.setMsg(ReturnMsg.Status_208);
        return result;
    }

    @Override
    public HttpResult voiceControl(int userId, String message) {
        HttpResult result = new HttpResult();
        List<DeviceScene> list = deviceSceneMapper.queryByUserId(userId);
        VoiceOperation voiceOperation = VoiceRecogintionUtil.recogintion(message, list);
        if (voiceOperation == null) {
            result.setCode("213");
            result.setMsg(ReturnMsg.Status_213);
        } else {
            result = control(userId, voiceOperation.getDeviceId(), voiceOperation.getOperate() + "");
        }
        return result;
    }
}
