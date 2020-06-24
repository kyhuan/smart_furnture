package com.xupt.utils;

import com.xupt.pojo.DeviceScene;
import com.xupt.pojo.VoiceOperation;

import java.util.List;


public class VoiceRecogintionUtil {
    public static VoiceOperation recogintion(String message, List<DeviceScene> list){
        if(list == null || list.size() == 0){
            return null;
        }
        VoiceOperation voiceOperation =new VoiceOperation();
        int openIndex = message.indexOf("打开");
        int closeIndex = message.indexOf("关闭");
        if(openIndex >=0){
            message = message.substring(openIndex+2);
        }else if(closeIndex>=0){
            message = message.substring(closeIndex+2);
        }else{
            return null;
        }
        for(int i=0;i<list.size();i++){
            int detailIndex =message.indexOf(list.get(i).getClassify()+list.get(i).getDetail());
            if(detailIndex>=0){
                if(openIndex >=0){
                    voiceOperation.setOperate(1);
                }else{
                    voiceOperation.setOperate(0);
                }
                voiceOperation.setDeviceId(list.get(i).getDeviceId());
                return voiceOperation;
            }
        }
        return null;
    }
}
