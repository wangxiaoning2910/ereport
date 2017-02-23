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
		// ����һ����ͼ�Ķ���������
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFRow row ;
		//�����������ݣ�����������
	      Iterator<T> it = dataset.iterator();
	      int index = 0;
	      while (it.hasNext()) {
	         index++;
	         row = sheet.createRow(index);
	         T t = (T) it.next();
	         //���÷��䣬����javabean���Ե��Ⱥ�˳�򣬶�̬����getXxx()�����õ�����ֵ
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
	                //�ж�ֵ�����ͺ����ǿ������ת��
	                String textValue = null;
	                if (value instanceof byte[]) {
	                   // ��ͼƬʱ�������и�Ϊ60px;
	                   row.setHeightInPoints(60);
	                   // ����ͼƬ�����п��Ϊ80px,ע�����ﵥλ��һ������
	                   sheet.setColumnWidth(i, (short) (35.7 * 80));
	                   // sheet.autoSizeColumn(i);
	                   byte[] bsValue = (byte[]) value;
	                   HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
	                         1023, 255, (short) 6, index, (short) 6, index);
	                   patriarch.createPicture(anchor, workbook.addPicture(
	                         bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
	                } else{
	                   //�����������Ͷ������ַ����򵥴���
	                   textValue = value.toString();
	                }
	                //�������ͼƬ���ݣ�������������ʽ�ж�textValue�Ƿ�ȫ�����������
	                if(textValue!=null){
	                   Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
	                   Matcher matcher = p.matcher(textValue);
	                   if(matcher.matches()){
	                      //�����ֵ���double����
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
	                //������Դ
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
	 * ���õ�Ԫ���ʽ������һ��������HSSFWorkbook
	 * @param List<int[]> style
	 * @return HSSFWorkbook
	 * 
	 * **/
	private HSSFWorkbook creatBook(List<int[]> style){
		// ����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();
     	// ����һ�����
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		if(style != null){
			for(int i = 0;i<style.size();i++){
				CellRangeAddress cra = new CellRangeAddress(style.get(i)[0], style.get(i)[1], style.get(i)[2], style.get(i)[3]);  
				//��sheet�����Ӻϲ���Ԫ��  
		        sheet.addMergedRegion(cra); 
			}
		}else{}
		return workbook;
	}
	/**
	 * ���ӱ�ṹ��д���ͷ
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
