package com.xupt.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Component
//@ServerEndpoint("/clientwebSocket")
@ServerEndpoint("/clientwebSocket/{username}/{devid}")
public class ClientWebSocket {


    private static int onlineCount = 0;
    public static ConcurrentHashMap<String, ClientWebSocket> webSocketSet = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Set<String>> userSocketMap = new ConcurrentHashMap<>();
    private String username = "";
    private  String devid="";
    private Session session;
    @OnOpen
    public void onOpen(@PathParam(value = "devid") String devid, @PathParam(value = "username") String username, Session session) throws IOException {
        System.out.println("通过websocket发送过来的设备id为"+devid);
        System.out.println(devid);
        System.out.println(username);
        this.devid=devid;
        this.username=username;
        this.session=session;
        webSocketSet.put(username,this);
        Set<String> set = userSocketMap.getOrDefault(devid, new HashSet<>());
        set.add(username);
        userSocketMap.put(devid,set);
        for (String s : userSocketMap.getOrDefault(devid, new HashSet<>())) {
            System.out.println("devid:"+devid+" username:"+s);
            System.out.println("username对应的websocket"+webSocketSet.get(s));
        }
    }

    /**
     * 关闭方法，当某个链接关闭之后调用
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        userSocketMap.remove(devid);
        System.out.println(this.username+"已关闭连接");
    }
    /**
     * 发生错误时调用的方法
     */
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 处理发来的数据的逻辑
     * 客户端发来自己是哪一个客户以及想要获得哪一个网关的数据，服务端进行查询客户端是否可以获得该网关的数据。
     * @param message  客户或者网关发来的消息或数据  devid
     * @param session
     *
     */
   @OnMessage
    public void onMessage(String message,Session session) throws IOException {
       System.out.println("发送消息长度："+message.length());

       session.getBasicRemote().sendText(message);

   }
    public void sendmsg(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
