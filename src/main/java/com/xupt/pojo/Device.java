package com.xupt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author kyh
 * @Email 13669259476@163.com
 * @Time 2020.4.10
 * Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
//    private int id;
//    private String code;
//    private String password;
//    private String state;
    private Integer deviceId;
    private String deviceName;
    private String deviceState;
    private String classId;
    private String deviceChName;
    private String isAdd;
    private String imgSrc;
    private String action;
    private String deviceCode;
    private String devicePassword;

}
