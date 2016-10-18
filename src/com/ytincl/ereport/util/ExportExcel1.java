package com.ytincl.ereport.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.pojo.RowData;

public class ExportExcel1 {
	/**
    * @param title 表格标题名
    * @param headers 表格属性列名数组
    * @param rowdatalist 需要显示的数据集合,
    * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
    */
	public void exportExcel(String title,String[] headers,ArrayList<RowData> rowdatalist,OutputStream out){
		// 声明一个工作薄
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 生成一个表格
	    HSSFSheet sheet = workbook.createSheet(title);
	    // 设置表格默认列宽度为15个字节
	    sheet.setDefaultColumnWidth((short) 15);
	    
	    //产生表格标题行
	    HSSFRow row = sheet.createRow(0);
	    for (short i = 0; i < headers.length; i++) {
	    	HSSFCell cell = row.createCell(i);
	    	HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	    	cell.setCellValue(text);
	    }
	    //产生数据行
	    Iterator<RowData> it = rowdatalist.iterator();
	    int index = 0;
      	while (it.hasNext()) {
      		index++;
      		row = sheet.createRow(index);
      		RowData rowdata = (RowData) it.next();
      		String[] fields = rowdata.getStrs();
      		for (short i = 0; i < fields.length; i++) {
      			HSSFCell cell = row.createCell(i);
	            String field = fields[i];
	            cell.setCellValue(field);
	      	}
      	}
      	try {
      		workbook.write(out);
      	} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
    * @param headers 表格属性列名数组
    * @param rowdatalist 需要显示的数据集合,
    * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
    */
	public void exportExcel(String[] headers,ArrayList<RowData> rowdatalist,OutputStream out){
		// 声明一个工作薄
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 生成一个表格
	    HSSFSheet sheet = workbook.createSheet(CommonConstants.DEFAULT_SHEETNAME);
	    // 设置表格默认列宽度为15个字节
	    sheet.setDefaultColumnWidth((short) 15);
	    //产生表格标题行
	    HSSFRow row = sheet.createRow(0);
	    for (short i = 0; i < headers.length; i++) {
	    	HSSFCell cell = row.createCell(i);
	    	HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	    	cell.setCellValue(text);
	    }
	    //产生数据行
	    Iterator<RowData> it = rowdatalist.iterator();
	    int index = 0;
      	while (it.hasNext()) {
      		index++;
      		row = sheet.createRow(index);
      		RowData rowdata = (RowData) it.next();
      		String[] fields = rowdata.getStrs();
      		for (short i = 0; i < fields.length; i++) {
      			HSSFCell cell = row.createCell(i);
	            String field = fields[i];
	            cell.setCellValue(field);
	      	}
      	}
      	try {
      		workbook.write(out);
      	} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
