package com.xupt.pojo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class Temperature {
    private int id;

    private String deviceId;

    private String temperature;

    private Timestamp time;
}
