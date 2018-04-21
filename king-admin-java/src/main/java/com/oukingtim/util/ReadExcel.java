package com.oukingtim.util;

import com.oukingtim.domain.FlowSrcModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>创建日期：2018/4/1
 *
 * @author JackieChan</b>
 * @version 1.****</b>
 */
@Service
public class ReadExcel {

    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;

    //构造方法
    public ReadExcel() {
    }

    //获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    //获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    //获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    private boolean validateExcel(String filePath) {
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    /**
     * 读EXCEL文件，获取客户信息集合
     *
     * @return
     */
    public List<FlowSrcModel> gotExcelInfo(String fileName, MultipartFile multipartFile) {
        List<FlowSrcModel> customerList = new ArrayList<FlowSrcModel>();
        //初始化输入流
        InputStream is = null;
        try {
            is = multipartFile.getInputStream();
            //验证文件名是否合格
            if (!validateExcel(fileName)) {
                return null;
            }
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if (WDWUtil.isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            customerList = getExcelInfo(is, isExcel2003);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("导入excel数据发生错误，请重新导入");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return customerList;
    }

    /**
     * 根据excel里面的内容读取客户信息
     *
     * @param is          输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    private List<FlowSrcModel> getExcelInfo(InputStream is, boolean isExcel2003) {
        List<FlowSrcModel> customerList = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            //当excel是2003时
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面客户的信息
            customerList = readExcelValue(wb);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException("导入excel错误，请重新导入");
        }
        return customerList;
    }

    /**
     * 读取Excel里面客户的信息
     *
     * @param wb
     * @return
     */
    private List<FlowSrcModel> readExcelValue(Workbook wb) {
        //得到第一个shell
        Sheet sheet = wb.getSheetAt(0);

        //得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();

        //得到Excel的列数(前提是有行数)
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }

        List<FlowSrcModel> flowSrcModels = new ArrayList<FlowSrcModel>();
        FlowSrcModel flowSrcModel;
        //循环Excel行数,从第二行开始。标题不入库
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            flowSrcModel = new FlowSrcModel();

            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {//第一列不读
                        flowSrcModel.setCompany(cell.getStringCellValue());
                    } else if (c == 1) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setClientName(cell.getStringCellValue());
                    } else if (c == 2) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setAddress(cell.getStringCellValue());
                    } else if (c == 3) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setMobileNo(cell.getStringCellValue());
                    } else if (c == 4) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setQqNo(cell.getStringCellValue());
                    } else if (c == 5) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setEmail(cell.getStringCellValue());
                    } else if (c == 6) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setExhibition(cell.getStringCellValue());
                    }
                    else if (c == 7) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setSrc(cell.getStringCellValue());
                    } else if (c == 8) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setUid(cell.getStringCellValue());
                    } else if (c == 9) {
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        flowSrcModel.setCreateTime(DateUtil.stringToDate(cell.getStringCellValue()));
                    }
                }
            }
            flowSrcModels.add(flowSrcModel);
        }
        return flowSrcModels;
    }
}
