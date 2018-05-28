package com.oukingtim.util;

import com.oukingtim.domain.AdviceModel;
import com.oukingtim.domain.FlowSrcModel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * <br>创建日期：2018/5/6
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@SuppressWarnings({"deprecation"})
public class ExportExcelUtil {

    public static String exportExcel(List<FlowSrcModel> list) {
        if (list == null || list.isEmpty()) {
            throw new BizException("没有查询到数据!");
        }
        String filePath = buildExcel(list);
        return filePath;
    }

    private static String buildExcel(List<FlowSrcModel> list) {
        //                1. 公司名称2.用户姓名3.用户地址4.用户手机号5.用户qq号6.用户邮箱7.报名展会8.用户来源src
        //                9.用户uid10.创建时间
        String sheetName = "展会报名信息";
        String titleName = "用户展会报名来源表";
        String fileName = "用户展会报名来源导出";
        int columnNumber = 10;
        int[] columnWidth = {15, 10, 40, 15, 20, 20, 30, 15, 15, 20};
        String[] columnName = {"公司名称", "姓名", "地址", "手机号", "QQ", "邮箱", "报名展会", "src", "uid", "报名时间"};
        String filePath = exportWithResponse(list, sheetName, titleName,
                fileName, columnNumber, columnWidth,
                columnName, list);

        return filePath;
    }

    private static String exportWithResponse(List<FlowSrcModel> list, String sheetName, String titleName,
                                             String fileName, int columnNumber, int[] columnWidth,
                                             String[] columnName, List<FlowSrcModel> dataList) {
        if (columnNumber == columnWidth.length && columnWidth.length == columnName.length) {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            // sheet.setDefaultColumnWidth(15); //统一设置列宽
            for (int i = 0; i < columnNumber; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j) {
                        sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
                    }
                }
            }
            // 创建第0行 也就是标题
            HSSFRow row1 = sheet.createRow(0);
            row1.setHeightInPoints(50);// 设备标题的高度
            // 第三步创建标题的单元格样式style2以及字体样式headerFont1
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
            style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFFont headerFont1 = wb.createFont(); // 创建字体样式
            headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont1.setFontName("黑体"); // 设置字体类型
            headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
            style2.setFont(headerFont1); // 为标题样式设置字体样式
            HSSFCell cell1 = row1.createCell(0);// 创建标题第一列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
                    columnNumber - 1)); // 合并列标题
            cell1.setCellValue(titleName); // 设置值标题
            cell1.setCellStyle(style2); // 设置标题样式
            // 创建第1行 也就是表头
            HSSFRow row = sheet.createRow(1);
            row.setHeightInPoints(37);// 设置表头高度
            // 第四步，创建表头单元格样式 以及表头的字体样式
            HSSFCellStyle style = wb.createCellStyle();
            style.setWrapText(true);// 设置自动换行
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
            style.setBottomBorderColor(HSSFColor.BLACK.index);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont.setFontName("黑体"); // 设置字体类型
            headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
            style.setFont(headerFont); // 为标题样式设置字体样式
            // 第四.一步，创建表头的列
            for (int i = 0; i < columnNumber; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(columnName[i]);
                cell.setCellStyle(style);
            }
            // 第五步，创建单元格，并设置值
            for (int i = 0; i < dataList.size(); i++) {
                row = sheet.createRow(i + 2);
                FlowSrcModel flowSrcModel = dataList.get(i);
                // 为数据内容设置特点新单元格样式1 自动换行 上下居中
                HSSFCellStyle cellStyle = setCellStyle(wb);
                HSSFCell datacell;
                //  1. 公司名称2.用户姓名3.用户地址4.用户手机号5.用户qq号6.用户邮箱7.报名展会8.用户来源src
                // 9.用户uid10.创建时间
                createCell(0, flowSrcModel.getCompany(), cellStyle, row);
                createCell(1, flowSrcModel.getClientName(), cellStyle, row);
                createCell(2, flowSrcModel.getAddress(), cellStyle, row);
                createCell(3, flowSrcModel.getMobileNo(), cellStyle, row);
                createCell(4, flowSrcModel.getQqNo(), cellStyle, row);
                createCell(5, flowSrcModel.getEmail(), cellStyle, row);
                createCell(6, flowSrcModel.getExhibition(), cellStyle, row);
                createCell(7, flowSrcModel.getSrc(), cellStyle, row);
                createCell(8, flowSrcModel.getUid(), cellStyle, row);
                datacell = row.createCell(columnNumber - 1);
                if (flowSrcModel.getCreateTime() != null) {
                    String stringDate = DateUtil.date2Str(DateUtil.DATE_FORMAT.yyyy_MM_ddHHmmss, flowSrcModel.getCreateTime());
                    datacell.setCellValue(stringDate);
                } else {
                    datacell.setCellValue("");

                }
                datacell.setCellStyle(cellStyle);
            }
            //自定义文件存储路径
            String localPath = "C:\\Users\\xufan\\Documents"; //本地
            String prodPath = Constants.EXCEL_BASE_PATH; //服务器
            //发送响应流方法
            String excelName = "/用户来源-" + UUID.randomUUID().toString() + ".xls";
            String creteFile = localPath + excelName;//本地
            String responseFileUrl = Constants.EXCEL_SERVER_ADMIN + excelName;//服务器
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(creteFile));
                wb.write(fos);
//                fos.close();
            } catch (FileNotFoundException e) {
                throw new BizException("导出文件错误");
            } catch (IOException e) {
                throw new BizException("导出文件错误");
            } finally {
                try {
                    if(fos != null){
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return responseFileUrl;

        }
        return null;
    }

    private static HSSFCellStyle setCellStyle(HSSFWorkbook wb) {
        HSSFCellStyle zidonghuanhang = wb.createCellStyle();
        zidonghuanhang.setWrapText(true);// 设置自动换行
        zidonghuanhang.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式

        // 设置边框
        zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
        zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
        zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);

        // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
        HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
        zidonghuanhang2.setWrapText(true);// 设置自动换行
        zidonghuanhang2
                .setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
        zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

        // 设置边框
        zidonghuanhang2.setBottomBorderColor(HSSFColor.BLACK.index);
        zidonghuanhang2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        zidonghuanhang2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        zidonghuanhang2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        zidonghuanhang2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        return zidonghuanhang2;
    }

    private static void createCell(int i, String cell, HSSFCellStyle hssfCellStyle,// 创建第1行 也就是表头
                                   HSSFRow row) {
        HSSFCell datacell;
        datacell = row.createCell(i);
        datacell.setCellValue(cell);
        datacell.setCellStyle(hssfCellStyle);

    }

    //发送响应流方法
    private static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public static String exportAdviceExcel(List<AdviceModel> list) {
        if (list == null || list.isEmpty()) {
            throw new BizException("没有查询到数据!");
        }
        String filePath = buildAdviceExcel(list);
        return filePath;
    }

    private static String buildAdviceExcel(List<AdviceModel> list) {
        //                1. 公司名称2.用户姓名3.用户地址4.用户手机号5.用户qq号6.用户邮箱7.报名展会8.用户来源src
        //                9.用户uid10.创建时间
        String sheetName = "用户咨询信息";
        String titleName = "用户咨询信息表";
        String fileName = "用户咨询列表导出";
        int columnNumber = 4;
        int[] columnWidth = {15, 10, 40, 30};
        String[] columnName = { "姓名", "手机号", "公司名称","所需资料"};
        String filePath = exportAdviceWithResponse(list, sheetName, titleName,
                fileName, columnNumber, columnWidth,
                columnName, list);

        return filePath;
    }

    private static String exportAdviceWithResponse(List<AdviceModel> list, String sheetName, String titleName,
                                             String fileName, int columnNumber, int[] columnWidth,
                                             String[] columnName, List<AdviceModel> dataList) {
        if (columnNumber == columnWidth.length && columnWidth.length == columnName.length) {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            // sheet.setDefaultColumnWidth(15); //统一设置列宽
            for (int i = 0; i < columnNumber; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j) {
                        sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
                    }
                }
            }
            // 创建第0行 也就是标题
            HSSFRow row1 = sheet.createRow(0);
            row1.setHeightInPoints(50);// 设备标题的高度
            // 第三步创建标题的单元格样式style2以及字体样式headerFont1
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
            style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFFont headerFont1 = wb.createFont(); // 创建字体样式
            headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont1.setFontName("黑体"); // 设置字体类型
            headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
            style2.setFont(headerFont1); // 为标题样式设置字体样式
            HSSFCell cell1 = row1.createCell(0);// 创建标题第一列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
                    columnNumber - 1)); // 合并列标题
            cell1.setCellValue(titleName); // 设置值标题
            cell1.setCellStyle(style2); // 设置标题样式
            // 创建第1行 也就是表头
            HSSFRow row = sheet.createRow(1);
            row.setHeightInPoints(37);// 设置表头高度
            // 第四步，创建表头单元格样式 以及表头的字体样式
            HSSFCellStyle style = wb.createCellStyle();
            style.setWrapText(true);// 设置自动换行
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
            style.setBottomBorderColor(HSSFColor.BLACK.index);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont.setFontName("黑体"); // 设置字体类型
            headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
            style.setFont(headerFont); // 为标题样式设置字体样式
            // 第四.一步，创建表头的列
            for (int i = 0; i < columnNumber; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(columnName[i]);
                cell.setCellStyle(style);
            }
            // 第五步，创建单元格，并设置值
            for (int i = 0; i < dataList.size(); i++) {
                row = sheet.createRow(i + 2);
                AdviceModel adviceModel = dataList.get(i);
                // 为数据内容设置特点新单元格样式1 自动换行 上下居中
                HSSFCellStyle cellStyle = setCellStyle(wb);
                HSSFCell datacell = null;
                //  1. 公司名称2.用户姓名3.用户地址4.用户手机号5.用户qq号6.用户邮箱7.报名展会8.用户来源src
                // 9.用户uid10.创建时间
                createCell(0, adviceModel.getClientName(), cellStyle, row);
                createCell(1, adviceModel.getMobileNo(), cellStyle, row);
                createCell(2, adviceModel.getCompany(), cellStyle, row);
                createCell(3, adviceModel.getMaterial(),cellStyle, row);
            }
            //自定义文件存储路径
            String localPath = "C:\\Users\\xufan\\Documents"; //本地
            String prodPath = Constants.EXCEL_BASE_PATH; //服务器
            //发送响应流方法
            String excelName = "/咨询表-" + UUID.randomUUID().toString() + ".xls";
            String creteFile = localPath + excelName;//本地
            String responseFileUrl = Constants.EXCEL_SERVER_ADMIN + excelName;//服务器
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(creteFile));
                wb.write(fos);
//                fos.close();
            } catch (FileNotFoundException e) {
                throw new BizException("导出文件错误");
            } catch (IOException e) {
                throw new BizException("导出文件错误");
            } finally {
                try {
                    if(fos != null){
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return responseFileUrl;

        }
        return null;
    }

}
