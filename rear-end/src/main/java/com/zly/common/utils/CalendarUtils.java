package com.zly.common.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取常用时间范围（本月、上月、本季度、本周、昨天、距离截止时间剩余...等）
 */
public class CalendarUtils {

    public static String now(){

        Date date = new Date();

        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        return spf.format(date);
    }

    public static String lastDay(){

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();


        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd");
        return spf.format(d);//获取昨天日期
    }

    //本周
    public static Map<String,String> thisWeek(){
        Map<String,String> map = new HashMap<>();

        Calendar cal=Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date time=cal.getTime();
        String startTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

        String endTime = CalendarUtils.now();

        map.put("startTime",startTime);
        map.put("endTime",endTime);

        return map;
    }

    public static Map<String,String> lastWeek(){

        Map<String,String> map = new HashMap<>();

        Calendar cal=Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DATE, -7);
        Date time=cal.getTime();
        String startTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

        cal.add(Calendar.DATE, 6);
        Date time1=cal.getTime();
        String endTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

        map.put("startTime",startTime);
        map.put("endTime",endTime);

        return map;
    }

    public static Map<String,String> thisMonth(){

        Map<String,String> map = new HashMap<>();

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time=cal.getTime();
        String startTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

        String endTime = CalendarUtils.now();

        map.put("startTime",startTime);
        map.put("endTime",endTime);

        return map;
    }

    public static Map<String,String> lastMonth(){

        Map<String,String> map = new HashMap<>();

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time=cal.getTime();
        String startTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time1=cal.getTime();
        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(time1);

        map.put("startTime",startTime);
        map.put("endTime",endTime);

        return map;
    }

    public static Map<String,String> lastThreeMonth(){

        Map<String,String> map = new HashMap<>();

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, -2);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time=cal.getTime();
        String startTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

        String endTime = CalendarUtils.now();

        map.put("startTime",startTime);
        map.put("endTime",endTime);

        return map;
    }

    public static Map<String,String> thisQuarter(){

        Map<String,String> map = new HashMap<>();

        Calendar cal=Calendar.getInstance();

        int month = cal.get(Calendar.MONTH)+1;
        int i = month % 3;
        if (i==2){
            cal.add(Calendar.MONTH, -1);
        }else if (i==0){
            cal.add(Calendar.MONTH, -2);
        }else{
            cal.add(Calendar.MONTH, 0);
        }
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time=cal.getTime();
        String startTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

        String endTime = CalendarUtils.now();

        map.put("startTime",startTime);
        map.put("endTime",endTime);

        return map;
    }

    public static Map<String,String> lastQuarter(){

        Map<String,String> map = new HashMap<>();

        Calendar cal=Calendar.getInstance();

        int month = cal.get(Calendar.MONTH)+1;
        if (month>3){
            int i = month % 3;
            if (i==2){
                cal.add(Calendar.MONTH, -4);
            }else if (i==0){
                cal.add(Calendar.MONTH, -5);
            }else{
                cal.add(Calendar.MONTH, -3);
            }
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date time=cal.getTime();
            String startTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

            cal=Calendar.getInstance();
            if (i==2){
                cal.add(Calendar.MONTH, -2);
            }else if (i==0){
                cal.add(Calendar.MONTH, -3);
            }else{
                cal.add(Calendar.MONTH, -1);
            }
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date time1=cal.getTime();
            String endTime = new SimpleDateFormat("yyyy-MM-dd").format(time1);

            map.put("startTime",startTime);
            map.put("endTime",endTime);

            return map;
        }else{

            cal.add(Calendar.MONTH, -month-3);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date time=cal.getTime();
            String startTime= new SimpleDateFormat("yyyy-MM-dd").format(time);

            cal=Calendar.getInstance();
            cal.add(Calendar.MONTH, -month-1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date time1=cal.getTime();
            String endTime = new SimpleDateFormat("yyyy-MM-dd").format(time1);

            map.put("startTime",startTime);
            map.put("endTime",endTime);

            return map;
        }
    }
}
