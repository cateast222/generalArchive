package com.ebs.platform.core.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 获取当前日期时间转成年月日时分秒毫秒编号字符串
     * @return
     */
    public  static String  GetMinSecondCodeString(){
        String dateStr ="";
        Calendar c   =   Calendar.getInstance();
        c.setTime(new Date());
        int   year =   c.get(Calendar.YEAR);
        int   month =   c.get(Calendar.MONTH)+1;
        int   day   =   c.get(Calendar.DAY_OF_MONTH);
        int   hour =   c.get(Calendar.HOUR_OF_DAY);
        int   minute =   c.get(Calendar.MINUTE);
        int   second =   c.get(Calendar.SECOND);
        int   minSecond =   c.get(Calendar.MILLISECOND);
        dateStr=  String.valueOf(year);
        if(month<10){
            dateStr =dateStr +"0"+String.valueOf(month);
        }else {
            dateStr =dateStr +String.valueOf(month);
        }
        if(day<10){
            dateStr =dateStr +"0"+String.valueOf(day);
        }else
        {
            dateStr =dateStr +String.valueOf(day);
        }
        if(hour<10){
            dateStr =dateStr +"0"+String.valueOf(hour);
        }else
        {
            dateStr =dateStr +String.valueOf(hour);
        }
        if(minute<10){
            dateStr =dateStr +"0"+String.valueOf(minute);
        }else
        {
            dateStr =dateStr +String.valueOf(minute);
        }
        if(second<10){
            dateStr =dateStr +"0"+String.valueOf(second);
        }else
        {
            dateStr =dateStr +String.valueOf(second);
        }

            dateStr =dateStr +String.valueOf(minSecond);

        return dateStr;
    }
    /**
     * 获取当前日期时间转成年月日时分秒编号字符串
     * @return
     */
    public  static String  GetAllDateCodeString(){
        String dateStr ="";
        Calendar c   =   Calendar.getInstance();
        c.setTime(new Date());
        int   year =   c.get(Calendar.YEAR);
        int   month =   c.get(Calendar.MONTH)+1;
        int   day   =   c.get(Calendar.DAY_OF_MONTH);
        int   hour =   c.get(Calendar.HOUR_OF_DAY);
        int   minute =   c.get(Calendar.MINUTE);
        int   second =   c.get(Calendar.SECOND);
        dateStr=  String.valueOf(year);
        if(month<10){
            dateStr =dateStr +"0"+String.valueOf(month);
         }else {
            dateStr =dateStr +String.valueOf(month);
        }
        if(day<10){
            dateStr =dateStr +"0"+String.valueOf(day);
        }else
        {
            dateStr =dateStr +String.valueOf(day);
        }
        if(hour<10){
            dateStr =dateStr +"0"+String.valueOf(hour);
        }else
        {
            dateStr =dateStr +String.valueOf(hour);
        }
        if(minute<10){
            dateStr =dateStr +"0"+String.valueOf(minute);
        }else
        {
            dateStr =dateStr +String.valueOf(minute);
        }
        if(second<10){
            dateStr =dateStr +"0"+String.valueOf(second);
        }else
        {
            dateStr =dateStr +String.valueOf(second);
        }
        return dateStr;
    }


    /**
     * 获取当前日期时间转成年月日时分编号字符串
     * @return
     */
    public static  String  GetMinDateCodeString(){
        String dateStr ="";
        Calendar c   =   Calendar.getInstance();
        c.setTime(new Date());
        int   year =   c.get(Calendar.YEAR);
        int   month =   c.get(Calendar.MONTH)+1;
        int   day   =   c.get(Calendar.DAY_OF_MONTH);
        int   hour =   c.get(Calendar.HOUR_OF_DAY);
        int   minute =   c.get(Calendar.MINUTE);
        dateStr=  String.valueOf(year);
        if(month<10){
            dateStr =dateStr +"0"+String.valueOf(month);
        }else {
            dateStr =dateStr +String.valueOf(month);
        }
        if(day<10){
            dateStr =dateStr +"0"+String.valueOf(day);
        }else
        {
            dateStr =dateStr +String.valueOf(day);
        }
        if(hour<10){
            dateStr =dateStr +"0"+String.valueOf(hour);
        }else
        {
            dateStr =dateStr +String.valueOf(hour);
        }
        if(minute<10){
            dateStr =dateStr +"0"+String.valueOf(minute);
        }else
        {
            dateStr =dateStr +String.valueOf(minute);
        }

        return dateStr;
    }


    /**
     * 获取当前日期时间转成年月日时编号字符串
     * @return
     */
    public static String  GetHourDateCodeString(){
        String dateStr ="";
        Calendar c   =   Calendar.getInstance();
        c.setTime(new Date());
        int   year =   c.get(Calendar.YEAR);
        int   month =   c.get(Calendar.MONTH)+1;
        int   day   =   c.get(Calendar.DAY_OF_MONTH);
        int   hour =   c.get(Calendar.HOUR_OF_DAY);
        dateStr=  String.valueOf(year);
        if(month<10){
            dateStr =dateStr +"0"+String.valueOf(month);
        }else {
            dateStr =dateStr +String.valueOf(month);
        }
        if(day<10){
            dateStr =dateStr +"0"+String.valueOf(day);
        }else
        {
            dateStr =dateStr +String.valueOf(day);
        }
        if(hour<10){
            dateStr =dateStr +"0"+String.valueOf(hour);
        }else
        {
            dateStr =dateStr +String.valueOf(hour);
        }

        return dateStr;
    }

    /**
     * 获取当前日期时间转成年月日编号字符串
     * @return
     */
    public  static String  GetDayDateCodeString(Date date){
        String dateStr ="";
        Calendar c   =   Calendar.getInstance();
        c.setTime(date==null?new Date():date);
        int   year =   c.get(Calendar.YEAR);
        int   month =   c.get(Calendar.MONTH)+1;
        int   day   =   c.get(Calendar.DAY_OF_MONTH);
        dateStr=  String.valueOf(year);
        if(month<10){
            dateStr =dateStr +"0"+String.valueOf(month);
        }else {
            dateStr =dateStr +String.valueOf(month);
        }
        if(day<10){
            dateStr =dateStr +"0"+String.valueOf(day);
        }else
        {
            dateStr =dateStr +String.valueOf(day);
        }


        return dateStr;
    }

    /**
     * 获取当前日期时间转成年月日时分秒字符串，2018-12-12 12:20:22
     * @return
     */
    public  static String  GetAllDateString(){
        String dateStr ="";
        Calendar c   =   Calendar.getInstance();
        c.setTime(new Date());
        int   year =   c.get(Calendar.YEAR);
        int   month =   c.get(Calendar.MONTH)+1;
        int   day   =   c.get(Calendar.DAY_OF_MONTH);
        int   hour =   c.get(Calendar.HOUR_OF_DAY);
        int   minute =   c.get(Calendar.MINUTE);
        int   second =   c.get(Calendar.SECOND);
        dateStr=  String.valueOf(year);
        if(month<10){
            dateStr =dateStr +"-0"+String.valueOf(month);
        }else {
            dateStr =dateStr+"-" +String.valueOf(month);
        }
        if(day<10){
            dateStr =dateStr +"-0"+String.valueOf(day);
        }else
        {
            dateStr =dateStr +"-" +String.valueOf(day);
        }
        if(hour<10){
            dateStr =dateStr +" 0"+String.valueOf(hour);
        }else
        {
            dateStr =dateStr +" " +String.valueOf(hour);
        }
        if(minute<10){
            dateStr =dateStr +":0"+String.valueOf(minute);
        }else
        {
            dateStr =dateStr +":" +String.valueOf(minute);
        }
        if(second<10){
            dateStr =dateStr +":0"+String.valueOf(second);
        }else
        {
            dateStr =dateStr +":" +String.valueOf(second);
        }
        return dateStr;
    }
    /**
     * 获取当前日期时间转成年月日时字符串，2018-12-12
     * @return
     */
    public  static String  GetDayDateString(){
        String dateStr ="";
        Calendar c   =   Calendar.getInstance();
        c.setTime(new Date());
        int   year =   c.get(Calendar.YEAR);
        int   month =   c.get(Calendar.MONTH)+1;
        int   day   =   c.get(Calendar.DAY_OF_MONTH);
        dateStr=  String.valueOf(year);
        if(month<10){
            dateStr =dateStr +"-0"+String.valueOf(month);
        }else {
            dateStr =dateStr+"-" +String.valueOf(month);
        }
        if(day<10){
            dateStr =dateStr +"-0"+String.valueOf(day);
        }else
        {
            dateStr =dateStr +"-" +String.valueOf(day);
        }

        return dateStr;
    }

    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }
}
