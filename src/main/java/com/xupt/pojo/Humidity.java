package com.xupt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Humidity {
    private int id;

    private String deviceId;

    private String  humidity;
    private Timestamp time;
}
