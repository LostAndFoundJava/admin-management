package com.oukingtim.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br>创建日期：2018/3/22
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public class DateUtil {

    interface DATE_FORMAT {
        public static String yyyyMMdd = "yyyyMMdd";
        public static String yyyy_MM_dd = "yyyy-MM-dd";
        public static String yyyy_MM_ddHHmmss = "yyyy-MM-dd HH:mm:ss";
    }

    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate,String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String dateToStr(Date dateDate,String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * 获取 date 的 pattern 模式-通用
     *
     * @param pattern e.g yyyy-MM-dd
     * @param date
     * @return
     */
    public static String date2Str(String pattern,Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(date);
        return str;
    }
    public static Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateF;
        try {
            dateF = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return new Date();
        }
    }

}
