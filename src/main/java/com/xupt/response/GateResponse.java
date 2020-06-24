package com.xupt.response;

public class GateResponse<T> {

    private Boolean aBoolean;
    private T data;

    public GateResponse() {
    }

    public GateResponse(Boolean aBoolean, T data) {
        this.aBoolean = aBoolean;
        this.data = data;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    @Override
    public String toString() {
        return "GateResponse{" +
                "aBoolean=" + aBoolean +
                ", data=" + data +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
