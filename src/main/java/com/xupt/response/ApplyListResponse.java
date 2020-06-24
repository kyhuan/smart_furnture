package com.xupt.response;

public class  ApplyListResponse<D> {
    private Boolean aBoolean;
    private D data;

    public ApplyListResponse() {
    }

    public ApplyListResponse(Boolean aBoolean, D data) {
        this.aBoolean = aBoolean;
        this.data = data;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApplyListResponse{" +
                "aBoolean=" + aBoolean +
                ", data=" + data +
                '}';
    }
}
