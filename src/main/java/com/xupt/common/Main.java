//package com.xupt.common;
//
//
//import java.util.*;
//public class Main
//{
//    public static void main(String args[])
//    {
//        Scanner cin = new Scanner(System.in);
//        Double vl =0.0;
//        int all = 0;
//
//          vl = cin.nextDouble();
//
//            all= cin.nextInt();
//
//
//        Change(vl,all);
//
//    }
//    public static void Change(Double money,int all)
//    {
//        //面值
//        double[] value= {100,50,10,5,1,0.5};
//
//        Double a = all -money;
//        System.out.print(a+" ");
//
//        //对应找零张数
//        int[] count=new int[value.length];
//        for(int i=0;i<value.length;i++)
//        {
//            while(a>=value[i])
//            {
//            a-=value[i];
//            count[i]++;
//        }
//
//            //输出
//            if(count[i]!=0)
//            {
//                if (value[i]!=0.5){
//                    System.out.print((int)value[i]+":"+count[i]+";");
//                }else {
//                    System.out.print(value[i]+":"+count[i]+";");
//                }
//
//            }
//        }
//    }
//}