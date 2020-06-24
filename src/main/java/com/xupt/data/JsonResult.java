package com.xupt.data;

/**
 * 统一返回格式
 */
public class JsonResult<T> {
    private boolean issuccess=false;
    private String message;
    private String uuid;
    private Integer status=0;
    private T data;

    public JsonResult(boolean issuccess, T data) {
        this.issuccess = issuccess;
        this.data = data;
    }

    public JsonResult(boolean issuccess, String message, String uuid) {
        this.issuccess = issuccess;
        this.message = message;
        this.uuid = uuid;
    }

    public JsonResult(boolean issuccess, String message, String uuid, Integer status) {
        this.issuccess = issuccess;
        this.message = message;
        this.uuid = uuid;
        this.status = status;
    }
//
//    public JsonResult(boolean issuccess, String message) {
//        this.issuccess = issuccess;
//        this.message = message;
//    }

    public JsonResult() {
    }

    public JsonResult(boolean issuccess, String message, Integer status) {
        this.issuccess = issuccess;
        this.message = message;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }



    public boolean isIssuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "issuccess=" + issuccess +
                ", message='" + message + '\'' +
                ", uuid='" + uuid + '\'' +
                ", status=" + status +
                '}';
    }
}
