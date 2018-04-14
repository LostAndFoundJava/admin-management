package com.oukingtim.util;

/**
 * <br>创建日期：2018/4/1
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */

public class WDWUtil {

    //是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
