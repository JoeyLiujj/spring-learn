package com.sinosoft;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxudong on 2018/3/11.
 */
public class ExcleUtils {
    /**
     * excel导出到输出流
     * 谁调用谁负责关闭输出流
     * @param os 输出流
     * @param excelExtName excel文件的扩展名，支持xls和xlsx，不带点号
     * @param data
     * @throws IOException
     */
    public static void writeExcel(OutputStream os, String excelExtName, Map<String, List<List<String>>> data) throws IOException {
        Workbook wb = null;
        try {
            if ("xls".equals(excelExtName)) {
                wb = new HSSFWorkbook();
            } else if ("xlsx".equals(excelExtName)) {
                wb = new XSSFWorkbook();
            } else {
                throw new Exception("当前文件不是excel文件");
            }
            for (String sheetName : data.keySet()) {
                Sheet sheet = wb.createSheet(sheetName);
                List<List<String>> rowList = data.get(sheetName);
                for (int i = 0; i < rowList.size(); i++) {
                    List<String> cellList = rowList.get(i);
                    Row row = sheet.createRow(i);
                    for (int j = 0; j < cellList.size(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(cellList.get(j));
                    }
                }
            }
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
    }
    /**
     * excel导出到输出流
     * 谁调用谁负责关闭输出流
     * @param os 输出流
     * @param excelExtName excel文件的扩展名，支持xls和xlsx，不带点号
     * @param data excel数据，map中的key是标签页的名称，value对应的list是标签页中的数据。list中的子list是标签页中的一行，子list中的对象是一个单元格的数据，包括是否居中、跨几行几列以及存的值是多少
     * @throws IOException
     */
    public static void testWrite(OutputStream os, String excelExtName, Map<String, List<List<ExcelData>>> data) throws IOException{
        Workbook wb = null;
        CellStyle cellStyle = null;
        boolean isXls;
        try {
            if ("xls".equals(excelExtName)) {
                wb = new HSSFWorkbook();
                isXls = true;
            } else if ("xlsx".equals(excelExtName)) {
                wb = new XSSFWorkbook();
                isXls = false;
            } else {
                throw new Exception("当前文件不是excel文件");
            }
            cellStyle = wb.createCellStyle();
            if (isXls) {
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            } else {
                cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            }
            for (String sheetName : data.keySet()) {
                Sheet sheet = wb.createSheet(sheetName);
                List<List<ExcelData>> rowList = data.get(sheetName);
                //i 代表第几行 从0开始
                for (int i = 0; i < rowList.size(); i++) {
                    List<ExcelData> cellList = rowList.get(i);
                    Row row = sheet.createRow(i);
                    int j = 0;//j 代表第几列 从0开始
                    for (ExcelData excelData : cellList) {
                        if (excelData != null) {
                            if (excelData.getColSpan() > 1 || excelData.getRowSpan() > 1) {
                                CellRangeAddress cra = new CellRangeAddress(i, i + excelData.getRowSpan() - 1, j, j + excelData.getColSpan() - 1);
                                sheet.addMergedRegion(cra);
                            }
                            Cell cell = row.createCell(j);
                            cell.setCellValue(excelData.getValue());
                            if (excelData.isAlignCenter()) {
                                cell.setCellStyle(cellStyle);
                            }
                            j = j + excelData.getColSpan();
                        } else {
                            j++;
                        }
                    }
                }
            }
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
    }
}
