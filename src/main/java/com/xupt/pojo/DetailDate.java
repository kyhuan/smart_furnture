package com.xupt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailDate {
    private int id;
    private  int deviceId;
    private int userId;
    private String deviceState;
    private String lampState;
    private String dateValue;

    private String programValue;
    private String curtainStatus;
    private String cleanerStatus;
    private String airValue;
}
