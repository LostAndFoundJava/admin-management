package com.oukingtim.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br>创建日期：2018/3/22
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public class DateUtil {


    /**
     * 获取 date 的 pattern 模式-通用
     *
     * @param pattern e.g yyyy-MM-dd
     * @param date
     * @return
     */
    public static String date2Str(String pattern,Date date){
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(date);
        return str;
    }

}
