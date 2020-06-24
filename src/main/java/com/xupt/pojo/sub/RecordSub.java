package com.xupt.pojo.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author szh
 * @email 754456231@qq.com
 * @date 2018/3/7 17:16
 * Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordSub {
    private String deviceId;
    private String type;
    private String state;
    private String time;
    private String useScene;
    private String detail;


}
