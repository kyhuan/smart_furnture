package com.xupt.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class Humiture {

    private int id;
    private int userId;
    private int cusSceneId;

    private Double temperature;
    private Double humidity;
}

