package com.ytincl.ereport.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ytincl.ereport.constant.CommonConstants;



public class ReadExcel {
    
    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<String[]> readXls(String path,int startx,int starty,int endx,int endy) throws IOException {
    	//����·����ȡ�ļ�
        InputStream is = new FileInputStream(path);
        //HSSFWorkbook��ȡ���ļ�
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<String[]> list = new ArrayList<String[]>();
        String [] arr = null;
        // Read the Sheet ������Excel��ÿ��Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);//ÿһ��Sheet����
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row ��ȡÿ��Sheet�е�ÿһ��
            //rowNum Ϊ��ʼ����
            for (int rowNum = starty; rowNum <= endy; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);//ÿһ�еĶ���
                if (hssfRow != null) {
                	//��ʼ��String����Ĵ�СΪ���ݵ�����
                	arr = new String[hssfRow.getLastCellNum()];
                	//xssfRowsizeΪ��ʼ����
                    for(int xssfRowsize = startx,xb = 0;xssfRowsize <= endx;xssfRowsize++,xb++){
                    	arr[xb] = getValue(hssfRow.getCell(xssfRowsize));
                    }
                    list.add(arr);
                }
            }
        }
        return list;
    }
    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<String[]> readXls(String path,Map<String,String> map) throws IOException {
    	//����·����ȡ�ļ�
        InputStream is = new FileInputStream(path);
        //HSSFWorkbook��ȡ���ļ�
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<String[]> list = new ArrayList<String[]>();
        String [] arr = null;
        // Read the Sheet ������Excel��ÿ��Sheet
        int startx,starty,endx,endy;
        String start = map.get("datastart");
        String end = map.get("dataend");
        GetGrid gg = new GetGrid(start);
        startx = gg.getX();
        starty = gg.getY();
        GetGrid gg1 = new GetGrid(end);
        endx = gg1.getX();
        endy = gg1.getY();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);//ÿһ��Sheet����
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row ��ȡÿ��Sheet�е�ÿһ��
            //rowNum Ϊ��ʼ����
            for (int rowNum = starty; rowNum <= endy; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);//ÿһ�еĶ���
                System.out.println("����=="+rowNum);
                if (hssfRow != null) {
                	//��ʼ��String����Ĵ�СΪ���ݵ�����
                	arr = new String[endx-startx+1];
                	//xssfRowsizeΪ��ʼ����
                    for(int xssfRowsize = startx,xb = 0;xssfRowsize <= endx;xssfRowsize++,xb++){
                    	arr[xb] = getValue(hssfRow.getCell(xssfRowsize));
                    	System.out.println("===arr[xb]=="+arr[xb]);
                    }
                    list.add(arr);
                }
            }
        }
        return list;
    }
   
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            Object inputValue = hssfCell.getNumericCellValue();// ��Ԫ��ֵ
            Long longVal = Math.round(hssfCell.getNumericCellValue());
            Double doubleVal = hssfCell.getNumericCellValue();
            String stringVal = String.valueOf(hssfCell.getNumericCellValue())+"";
            if(Double.parseDouble(longVal + ".0") == doubleVal){   //�ж��Ƿ���С��λ.0
                inputValue = longVal;
            }else{
                inputValue = doubleVal;
            }
            return String.valueOf(inputValue);
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA){
        	//��ֵ����
        	Object inputValue = hssfCell.getNumericCellValue();// ��Ԫ��ֵ
            Long longVal = Math.round(hssfCell.getNumericCellValue());
        	Double doubleVal = hssfCell.getNumericCellValue();
        	if(Double.parseDouble(longVal + ".0") == doubleVal){   //�ж��Ƿ���С��λ.0
                inputValue = longVal;
            }else{
                inputValue = doubleVal;
            }
        	return String.valueOf(inputValue);
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING){
        	//�ַ�������
        	return String.valueOf(hssfCell.getStringCellValue());
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK){
        	System.out.println("����ת��Ϊ����");
        	return " ";
        }else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    /*
     * ��ȡexcel��׺
     * */
	public static String getPostfix(String path) {
	     if (path == null || CommonConstants.NULL_STRING.equals(path.trim())) {
	         return CommonConstants.NULL_STRING;
	     }
	     if (path.contains(CommonConstants.POINT)) {
	         return path.substring(path.lastIndexOf(CommonConstants.POINT) + 1, path.length());
	     }
	     return CommonConstants.NULL_STRING;
	 }

}
