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
    * @param title ��������
    * @param headers ���������������
    * @param rowdatalist ��Ҫ��ʾ�����ݼ���,
    * @param out ������豸�����������󣬿��Խ�EXCEL�ĵ������������ļ�����������
    */
	public void exportExcel(String title,String[] headers,ArrayList<RowData> rowdatalist,OutputStream out){
		// ����һ��������
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // ����һ�����
	    HSSFSheet sheet = workbook.createSheet(title);
	    // ���ñ��Ĭ���п��Ϊ15���ֽ�
	    sheet.setDefaultColumnWidth((short) 15);
	    
	    //������������
	    HSSFRow row = sheet.createRow(0);
	    for (short i = 0; i < headers.length; i++) {
	    	HSSFCell cell = row.createCell(i);
	    	HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	    	cell.setCellValue(text);
	    }
	    //����������
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
    * @param headers ���������������
    * @param rowdatalist ��Ҫ��ʾ�����ݼ���,
    * @param out ������豸�����������󣬿��Խ�EXCEL�ĵ������������ļ�����������
    */
	public void exportExcel(String[] headers,ArrayList<RowData> rowdatalist,OutputStream out){
		// ����һ��������
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // ����һ�����
	    HSSFSheet sheet = workbook.createSheet(CommonConstants.DEFAULT_SHEETNAME);
	    // ���ñ��Ĭ���п��Ϊ15���ֽ�
	    sheet.setDefaultColumnWidth((short) 15);
	    //������������
	    HSSFRow row = sheet.createRow(0);
	    for (short i = 0; i < headers.length; i++) {
	    	HSSFCell cell = row.createCell(i);
	    	HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	    	cell.setCellValue(text);
	    }
	    //����������
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
