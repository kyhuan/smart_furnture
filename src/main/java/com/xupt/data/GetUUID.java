package com.xupt.data;



public class GetUUID {

    public static String getUUID(){
        Integer i = (int) ((Math.random() * 9 + 1) * 100000);
        String string = i.toString();
        return string;
    }

    public static void main(String[] args) {
        System.out.println((int)((Math.random()*9+1)*100000));
    }
}
