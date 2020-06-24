package com.xupt.pojo.sub;

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
public class SceneSub {
    private int deviceId;
    private String classify;
    private String detail;
    private String state;


}
