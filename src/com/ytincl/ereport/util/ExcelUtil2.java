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
		System.out.println("---------------------����excel����ʼ---------------------");

		if(fileName == null || "".equals(fileName)){
			System.out.println("����excel�ļ�������Ϊ��");
			return null;
		}
		if(!fileName.endsWith(".xls")){
			System.out.println("�ļ�������xls��β�������е���excel����");
			return null;
		}
		
		
		
		
		targetFileName=targetPath + File.separator+targetFileName;		
		exprotExcel(dataList, fprop,fpropValue ,fileName, targetFileName);
	
		System.out.println("---------------------����excel�������---------------------");
	
		return targetFileName;
	}
	public static <T> void exprotExcel(List<String[]> dataList,String[]fprop,String[] fpropValue,
			String tempFilePath,String targetFilePath){
		exportExcel("sheet0", null, dataList,fprop, "yyyy-MM-dd", fpropValue, tempFilePath, targetFilePath);
//		exportExcels("����", null, dataset, "yyyy-MM-dd", columns, tempFilePath, targetFilePath);
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
				//�޸�sheetҳ����
				workbook.setSheetName(0, tiltle);
				
			}
			sheet.setDefaultColumnWidth((short)15);	         
	          //����һ����ʽ
	          HSSFCellStyle style = workbook.createCellStyle();	         
	          //������Щ��ʽ
	          style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	          style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	          style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	          style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);	         
	          //����һ������
	          HSSFFont font = workbook.createFont();
	          font.setColor(HSSFColor.VIOLET.index);
	          font.setFontHeightInPoints((short)12);
	          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	         
	          //������Ӧ�õ���ǰ����ʽ
	          style.setFont(font);	         
	          //���ɲ�������һ����ʽ
	          HSSFCellStyle style2 = workbook.createCellStyle();
	          style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	          style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	          style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	          style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	          style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	          //������һ������
	          HSSFFont font2 = workbook.createFont();
	          font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	          style2.setFont(font2);	         
	          //����һ����ͼ�Ķ���������
	          HSSFPatriarch patriarch = sheet.createDrawingPatriarch();	         
	          //����ע�͵Ĵ�С��λ��,����ĵ�
	          HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0,0,0,(short)4,2,(short)6,5));
	          //����ע������
	          comment.setString(new HSSFRichTextString("������POI�����ע�ͣ�"));	          
	          //������������
	          HSSFRow row = sheet.createRow(0);
	          if(headers == null){
	        	  System.out.println("�ޱ�����-->Ĭ�ϱ����ʽ");
	          }else {
	        	  for (short i = 0; i < headers.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell.setCellValue(text); 
				}
	          }
	          //�õ�������кš�
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
					    
					//�������ͼƬ���ݣ�������������ʽ�ж��Ƿ�ȫ�����������
					if(textValue != null){
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if(matcher.matches()){
							//�����ֵ���double����
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
					    
					//�������ͼƬ���ݣ�������������ʽ�ж��Ƿ�ȫ�����������
					if(textValue != null){
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if(matcher.matches()){
							//�����ֵ���double����
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
	          //��excel����д��Ŀ���ļ�����
	          out = new FileOutputStream(new File(targetFilePath));
	          workbook.write(out);
	          System.out.println("���ݳɹ�д��excel��-->excel����д�����");
		} catch (FileNotFoundException e) {
			System.out.println("ָ��·��������:"+e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("��д�ļ�ʧ��:"+e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("excelд��ʧ��:"+e);
			e.printStackTrace();
		} finally{
			try {
				//��Դ����
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
				System.out.println("�ر�IO��ʧ��:"+e2);
			}
			
		}
		
	}
}
