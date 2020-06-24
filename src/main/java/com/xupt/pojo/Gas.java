package com.xupt.pojo;



import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class Gas {
    private int id;

    private String deviceId;

    private String gasValue;

    private Timestamp time;
}
