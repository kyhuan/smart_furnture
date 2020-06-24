package com.xupt.response;

public class GetPastResponse {
    private Integer flag;
    private String data;
    private Integer type;

    public GetPastResponse() {
    }

    public GetPastResponse(Integer flag, String data, Integer type) {
        this.flag = flag;
        this.data = data;
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
