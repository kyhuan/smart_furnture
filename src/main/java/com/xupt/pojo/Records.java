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
public class Records {
    private int id;
    private int userId;
    private int deviceId;
    private String state;
    private String type;
    private String time;

}
