package com.ytincl.ereport.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExportExcel<T> {
	private static Logger logger = LoggerFactory.getLogger(ExportExcel.class);
	public void exportExcel(List<int[]> style,Collection<T> dataset, OutputStream out,String[] fixedata,List<int[]> coordinate) {
		
		HSSFWorkbook workbook = creatBook(style);
		HSSFSheet sheet = workbook.getSheet("Sheet1");
		insertfixedata(sheet, coordinate, fixedata);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFRow row ;
		//遍历集合数据，产生数据行
	      Iterator<T> it = dataset.iterator();
	      int index = 0;
	      while (it.hasNext()) {
	         index++;
	         row = sheet.createRow(index);
	         T t = (T) it.next();
	         //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	         Field[] fields = t.getClass().getDeclaredFields();
	         for (short i = 0; i < fields.length; i++) {
	            HSSFCell cell = row.createCell(i);
	            Field field = fields[i];
	            String fieldName = field.getName();
	            String getMethodName = "get"
	                   + fieldName.substring(0, 1).toUpperCase()
	                   + fieldName.substring(1);
	            try {
	            	Class tCls = t.getClass();
	            	Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
	            	Object value = getMethod.invoke(t, new Object[] {});
	                //判断值的类型后进行强制类型转换
	                String textValue = null;
	                if (value instanceof byte[]) {
	                   // 有图片时，设置行高为60px;
	                   row.setHeightInPoints(60);
	                   // 设置图片所在列宽度为80px,注意这里单位的一个换算
	                   sheet.setColumnWidth(i, (short) (35.7 * 80));
	                   // sheet.autoSizeColumn(i);
	                   byte[] bsValue = (byte[]) value;
	                   HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
	                         1023, 255, (short) 6, index, (short) 6, index);
	                   patriarch.createPicture(anchor, workbook.addPicture(
	                         bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
	                } else{
	                   //其它数据类型都当作字符串简单处理
	                   textValue = value.toString();
	                }
	                //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
	                if(textValue!=null){
	                   Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
	                   Matcher matcher = p.matcher(textValue);
	                   if(matcher.matches()){
	                      //是数字当作double处理
	                      cell.setCellValue(Double.parseDouble(textValue));
	                   }else{
	                      HSSFRichTextString richString = new HSSFRichTextString(textValue);
	                      HSSFFont font3 = workbook.createFont();
	                      font3.setColor(HSSFColor.BLUE.index);
	                      richString.applyFont(font3);
	                      cell.setCellValue(richString);
	                   }
	                }
	            } catch (SecurityException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (NoSuchMethodException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IllegalArgumentException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } finally {
	                //清理资源
	            }
	         }
	 
	      }
	      try {
	         workbook.write(out);
	         workbook.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	}
	/**
	 * 设置单元格格式，返回一个工作簿HSSFWorkbook
	 * @param List<int[]> style
	 * @return HSSFWorkbook
	 * 
	 * **/
	private HSSFWorkbook creatBook(List<int[]> style){
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
     	// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		if(style != null){
			for(int i = 0;i<style.size();i++){
				CellRangeAddress cra = new CellRangeAddress(style.get(i)[0], style.get(i)[1], style.get(i)[2], style.get(i)[3]);  
				//在sheet里增加合并单元格  
		        sheet.addMergedRegion(cra); 
			}
		}else{}
		return workbook;
	}
	/**
	 * 向复杂表结构中写入表头
	 * **/
	private void insertfixedata(HSSFSheet sheet,List<int[]> coordinate,String[] strs){
		int x,y;
		HSSFRow row;
		HSSFCell cell;
		for(int i = 0;i<coordinate.size();i++){
			x = coordinate.get(i)[1];
			y = coordinate.get(i)[0];
			logger.debug("x====="+x+",y====="+y);
			row = sheet.createRow((short)y);  
			cell = row.createCell((short)x);
			cell.setCellValue(strs[i]);
		}
	}
}
