package com.xupt.data;

import java.sql.Timestamp;
import java.util.Date;

public class GetTime {
    public static Timestamp getTime(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
