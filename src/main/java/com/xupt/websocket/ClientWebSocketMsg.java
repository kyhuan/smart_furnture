package com.xupt.websocket;

public class ClientWebSocketMsg {
        private  Integer value;
        private  String devid;

    public ClientWebSocketMsg() {
    }

    public ClientWebSocketMsg(Integer value, String devid) {
        this.value = value;
        this.devid = devid;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    @Override
    public String toString() {
        return "ClientWebSocketMsg{" +
                "value=" + value +
                ", devid='" + devid + '\'' +
                '}';
    }
}
