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
public class VoiceOperation {
    private int operate;//0 关闭 1打开
    private int deviceId;


}
