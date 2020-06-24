package com.xupt.response;

public class ReturnMsg<T> {
    private T msg;

    public ReturnMsg() {
    }

    public ReturnMsg(T msg) {
        this.msg = msg;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ReturnMsg{" +
                "msg=" + msg +
                '}';
    }
}
