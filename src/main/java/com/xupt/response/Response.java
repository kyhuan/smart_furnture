package com.xupt.response;


public class Response<T> {
    private Boolean aBoolean;
    private T data;

    public Response() {
    }

    public Response(Boolean aBoolean, T data) {
        this.aBoolean = aBoolean;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

}
