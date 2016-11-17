package com.ytincl.ereport.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtil2 {
	
	public static <T> String exportReport(String fileName,String targetPath,String targetFileName,List<String[]> dataList,String[]fprop,String[] fpropValue){
		System.out.println("---------------------生成excel报表开始---------------------");

		if(fileName == null || "".equals(fileName)){
			System.out.println("生成excel文件名不能为空");
			return null;
		}
		if(!fileName.endsWith(".xls")){
			System.out.println("文件名不是xls结尾，不进行导出excel操作");
			return null;
		}
		
		
		
		
		targetFileName=targetPath + File.separator+targetFileName;		
		exprotExcel(dataList, fprop,fpropValue ,fileName, targetFileName);
	
		System.out.println("---------------------生成excel报表结束---------------------");
	
		return targetFileName;
	}
	public static <T> void exprotExcel(List<String[]> dataList,String[]fprop,String[] fpropValue,
			String tempFilePath,String targetFilePath){
		exportExcel("sheet0", null, dataList,fprop, "yyyy-MM-dd", fpropValue, tempFilePath, targetFilePath);
//		exportExcels("测试", null, dataset, "yyyy-MM-dd", columns, tempFilePath, targetFilePath);
	}
	public static <T> void exportExcel(String tiltle,String[] headers,List<String[]> dataList,String[]fprop,
			String pattern,String[] fpropValue,String tempFilePath,String targetFilePath){	
		
		InputStream is = null;
		HSSFWorkbook workbook = null;
		FileOutputStream out = null;
		try {
			is = new FileInputStream(new File(tempFilePath));
			workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			if(tiltle != null){
				//修改sheet页名称
				workbook.setSheetName(0, tiltle);
				
			}
			sheet.setDefaultColumnWidth((short)15);	         
	          //生成一个样式
	          HSSFCellStyle style = workbook.createCellStyle();	         
	          //设置这些样式
	          style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	          style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	          style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	          style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);	         
	          //生成一个字体
	          HSSFFont font = workbook.createFont();
	          font.setColor(HSSFColor.VIOLET.index);
	          font.setFontHeightInPoints((short)12);
	          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	         
	          //把字体应用到当前的样式
	          style.setFont(font);	         
	          //生成并设置另一种样式
	          HSSFCellStyle style2 = workbook.createCellStyle();
	          style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	          style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	          style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	          style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	          style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	          //生成另一个字体
	          HSSFFont font2 = workbook.createFont();
	          font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	          style2.setFont(font2);	         
	          //声明一个画图的顶级管理器
	          HSSFPatriarch patriarch = sheet.createDrawingPatriarch();	         
	          //定义注释的大小和位置,详见文档
	          HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0,0,0,(short)4,2,(short)6,5));
	          //设置注释内容
	          comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));	          
	          //产生表格标题行
	          HSSFRow row = sheet.createRow(0);
	          if(headers == null){
	        	  System.out.println("无标题行-->默认标题格式");
	          }else {
	        	  for (short i = 0; i < headers.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell.setCellValue(text); 
				}
	          }
	          //得到坐标的行号。
	          System.out.println("fprop[0]="+fprop[0]);
	         int header_index=ReportTools2.getStringToCood_ColRow(fprop[0])[1];
	         // int header_index=5;
	         System.out.println("header_index="+header_index);
	         row=sheet.createRow(header_index);
	         for(int m=0;m<fprop.length;m++){
	        	 
					for(int k=0;k<fprop.length;k++){
					    String textValue=fpropValue[k];					    
						HSSFCell cell = row.createCell(k);
						//cell.setCellStyle(style2);
					    
					//如果不是图片数据，就利用正则表达式判断是否全部由数据组成
					if(textValue != null){
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if(matcher.matches()){
							//是数字当做double处理
							cell.setCellValue(Double.parseDouble(textValue));
						}else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
	        		  
					}
	        	 
	        	 
	        	 
	         }
	         
	         
	          
	          int  index=header_index+1;
	          for(int i=0;i<dataList.size();i++){
	        	  row = sheet.createRow(index);
	        	  index++;
	        	  String []	textArrayValue=(String[])dataList.get(i);
					for(int k=0;k<textArrayValue.length;k++){
					    String textValue=textArrayValue[k];					    
						HSSFCell cell = row.createCell(k);
						//cell.setCellStyle(style2);
					    
					//如果不是图片数据，就利用正则表达式判断是否全部由数据组成
					if(textValue != null){
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if(matcher.matches()){
							//是数字当做double处理
							cell.setCellValue(Double.parseDouble(textValue));
						}else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
	        		  
					}
	        	  
	          }
	          //将excel数据写入目标文件流中
	          out = new FileOutputStream(new File(targetFilePath));
	          workbook.write(out);
	          System.out.println("数据成功写入excel中-->excel数据写入完成");
		} catch (FileNotFoundException e) {
			System.out.println("指定路径不存在:"+e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读写文件失败:"+e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("excel写入失败:"+e);
			e.printStackTrace();
		} finally{
			try {
				//资源清理
				if(is != null){
					is.close();
				}
				if(out != null){
					out.close();
				}
				if(workbook != null){
					workbook.close();
				}
			} catch (Exception e2) {
				System.out.println("关闭IO流失败:"+e2);
			}
			
		}
		
	}
}
