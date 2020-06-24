package com.xupt.websocket;

import com.alibaba.fastjson.JSON;
import com.xupt.mapper.DetailDateMapper;
import com.xupt.data.GetTime;

import com.xupt.response.DataReponse;

import com.xupt.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 网关的websocket
 */
@Component
@ServerEndpoint("/gatewebSocket/{devid}")
public class GateWebSocket {
    private Integer integer = 1;

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DetailDateMapper detailDateMapper;


    private static int onlineCount = 0;
    public static GateWebSocket gateWebSocket;
    public static ConcurrentHashMap<String, GateWebSocket> gatewebSocketSet = new ConcurrentHashMap<>();
    private String devid = "";
    //静态阻塞队列，完成信息接收
    private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @PostConstruct
    public void init() {
        gateWebSocket = this;
    }

    @OnOpen
    public void onOpen(@PathParam(value = "devid") String devid, Session session) throws IOException {
        System.out.println("通过websocket发送过来的用户id为" + devid);
        this.devid = devid;
        this.session = session;
        gatewebSocketSet.put(devid, this);

    }

    /**
     * 关闭方法，当某个链接关闭之后调用
     */
    @OnClose
    public void onClose() {
        gatewebSocketSet.remove(this);
        System.out.println(this.devid + "已关闭连接");
    }

    /**
     * 发生错误时调用的方法
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public static void  sendm(String message) {
//        GateWebSocketMsg gateWebSocketMsg1 = new GateWebSocketMsg(3, "", 1);
        System.out.println(message);
        for(Map.Entry<String, GateWebSocket>  map : gatewebSocketSet.entrySet()) {
//            map.getValue().sendms(JSON.toJSONString(gateWebSocketMsg1));
            map.getValue().sendms(JSON.toJSONString(message));
            System.out.println("发送给网关"+map.getKey());
        }
    }

    public void sendms(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("接收到的数据长度为：" + message.length());
//        gatewebSocketSet.get(devid).sendms(message);
        try {
            GateWebSocketMsg gateWebSocketMsg = JSON.parseObject(message, GateWebSocketMsg.class);
            if (gateWebSocketMsg.getValue() == GateWebSocketMsgType.DATA.value) {
                DataReponse dataReponse = JSON.parseObject(gateWebSocketMsg.getData(), DataReponse.class);
                System.out.println(dataReponse);
                System.out.println("普通数据Json解析之后：" + gateWebSocketMsg);
                //DataReponse dataReponse = gateWebSocketMsg.getData();
                //保存温度数据
                System.out.println(dataReponse.getTemperature());
                System.out.println("当前设备id为：" + devid);
                gateWebSocket.deviceService.instemp(dataReponse.getTemperature(), devid, GetTime.getTime());
                //保存湿度数据
                gateWebSocket.deviceService.insHum(dataReponse.getHumidity(), devid, GetTime.getTime());
                gateWebSocket.deviceService.insLight(devid, dataReponse.getLightIntensity(), GetTime.getTime());
//                gateWebSocket.deviceService.insGas(dataReponse.getGas(),devid,GetTime.getTime());
//                gatewebSocketSet.get(devid).sendms(message);
            }
//            Set<String> uuidSet = ClientWebSocket.userSocketMap.get(devid);
//            if (uuidSet == null&&uuidSet.isEmpty()) {
//                System.out.println("无客户端连接");
//                return;
//            }
//            for (String uuid : uuidSet) {
//                if (ClientWebSocket.webSocketSet.get(uuid) != null) {
//                    System.out.println("开始发送到客户端");
//                    ClientWebSocket.webSocketSet.get(uuid).sendmsg(message);
//           }
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
