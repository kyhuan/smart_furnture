package com.xupt.websocket;

public class GateWebSocketMsg {
 private Integer value;
 private String  data;
 private Integer flag;

    public GateWebSocketMsg() {
    }

    public GateWebSocketMsg(Integer value, String data, Integer flag) {
        this.value = value;
        this.data = data;
        this.flag = flag;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GateWebSocketMsg{" +
                "value=" + value +
                ", data=" + data +
                '}';
    }
}
