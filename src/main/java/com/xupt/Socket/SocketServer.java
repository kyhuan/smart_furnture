package com.xupt.Socket;

import com.google.gson.Gson;
import com.xupt.mapper.DeviceMapper;
import com.xupt.pojo.ArdunioDevice;
import com.xupt.pojo.Device;
import com.xupt.utils.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SocketServer {

    /**端口号*/
    private  static  final int  port = 8888;
    /** 线程池*/
    private ExecutorService mExecutorService;
    /**ServerSocket对象*/
    private ServerSocket mServerSocket;
    /**存储socket*/
    public static Map<String,Socket> socketMap =new HashMap<>();
    private Socket client;
    private DeviceMapper deviceMapper;
    public SocketServer(DeviceMapper deviceMapper) {
        this.deviceMapper =deviceMapper;
        try {
            System.out.println("socket启动");
            //设置socket端口
            mServerSocket = new ServerSocket(port);
            //创建线程池
            mExecutorService = Executors.newCachedThreadPool();
            // 用来临时保存客户端连接的Socket对象
            while(true){
                System.out.println("等待连接");
                client = mServerSocket.accept();
                System.out.println("--已连接--"+client);
                mExecutorService.execute(convertData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**处理数据*/
    public Runnable convertData = new Runnable(){

        @Override
        public void run() {
            System.out.println("convertData");
            boolean isRun =true;
            Socket clientSave =client;
            try {
                while (isRun) {
                    InputStream inputStream = clientSave.getInputStream();
                    while(inputStream.available()>0) {
                        byte[] data = new byte[inputStream.available()];
                        inputStream.read(data);
                        String resultData = new String(data);
                        resultData = StringUtil.replaceBlank(resultData);
                        System.out.println("resultData=" + resultData);
                        Gson gson = new Gson();
                        ArdunioDevice ardunioDevice =gson.fromJson(resultData, ArdunioDevice.class);
                        Device device = deviceMapper.queryByCode(ardunioDevice.getName());
                        if(device == null){
                            Device device1=new Device();
                            device1.setDeviceCode(ardunioDevice.getName());
                            device1.setDevicePassword("root");
                            device1.setDeviceState("0");
                            deviceMapper.add(device1);
                            Device device2 = deviceMapper.queryByCode(ardunioDevice.getName());
                            socketMap.put(device2.getDeviceId()+"",clientSave);
                        }else{
                            deviceMapper.updataDeviceState(device.getDeviceId(),"0");
                            socketMap.put(device.getDeviceId()+"",clientSave);
                        }
                        isRun =false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


}
