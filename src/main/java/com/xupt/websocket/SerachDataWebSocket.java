package com.xupt.websocket;

import com.alibaba.fastjson.JSON;

import com.xupt.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.List;

@Component
@ServerEndpoint("/SerachDataWebSocket/{devid}")
public class SerachDataWebSocket {

    @Autowired
    private DeviceService deviceService;
    private static SerachDataWebSocket serachDataWebSocket;
    private String devid;
    private Session session;
    @PostConstruct
    public void init() {
        serachDataWebSocket = this;
    }
    @OnOpen
    public void onOpen(@PathParam(value = "devid") String devid, Session session){
        this.devid=devid;
        this.session=session;
        System.out.println(session.getId()+"已打开连接");
    }

    @OnClose
    public void onClose(Session session){
        System.out.println(session.getId()+"已关闭连接");
    }
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
//    @OnMessage
//    public void onMessage(String message,Session session) throws Exception {
//        SerachDataVO serachDataVO = JSON.parseObject(message, SerachDataVO.class);
//        Integer flag = Integer.valueOf(serachDataVO.getFlag());
//        Timestamp timebegin = serachDataVO.getTimebegin();
//        Timestamp timeend = serachDataVO.getTimeend();
//        if (flag==1){
//
//            List<TempData> tempData = serachDataWebSocket.dataService.selTempByTimeScope(devid, timebegin, timeend);
//            session.getBasicRemote().sendText(JSON.toJSONString(new GateWebSocketMsg(1,JSON.toJSONString(tempData),1)));
//
//        }else if (flag==2){
//            List<HumData> humData = serachDataWebSocket.dataService.selHumByTimeScope(devid, timebegin, timeend);
//            session.getBasicRemote().sendText(JSON.toJSONString(new GateWebSocketMsg(2,JSON.toJSONString(humData),2)));
//
//        }
//    }
    private void writeFileToRemote(String url) throws Exception{
        File filename = new File(url);
        //value 1：温度  2 ：湿度  3：图像
        System.out.println("开始发送");
        GateWebSocketMsg gateWebSocketMsg = new GateWebSocketMsg(3, "", 1);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
        int length=0;
        int index=3;
        int len = in.available();
        byte[] temp = new byte[2048];
        int size = 0;
        while((size = in.read(temp)) != -1){
            if (length==0){
                gateWebSocketMsg.setFlag(1);
            }else  if ((length+size)>=len){
                gateWebSocketMsg.setFlag(2);
            }else {
                gateWebSocketMsg.setFlag(index++);
            }
            gateWebSocketMsg.setData(new String(temp,0,size,Charset.forName("ISO-8859-1")));
            session.getBasicRemote().sendText(JSON.toJSONString(gateWebSocketMsg));
            length+=size;
        }
        in.close();

    }
}
