package com.ytincl.ereport.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
	public List<String[]> readExcel(String path) throws IOException {
        if (path == null || CommonConstants.NULL_STRING.equals(path)) {
            return null;
        } else {
            String postfix = getPostfix(path);
            //�ж�excel��׺
            if (!CommonConstants.NULL_STRING.equals(postfix)) {
                if (CommonConstants.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                	//�����Excel2003
                    //return readXls(path);
                } else if (CommonConstants.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                	//�����Excel2010
                    return readXlsx(path);
                }
            } else {
            }
        }
        return null;
    }

    /**
     * ��ȡ  Excel2010
     * @param path the path of the excel file
     * @return 
     * @throws IOException
     */
    public List<String[]> readXlsx(String path) throws IOException {
    	//����·���õ��ļ�
        InputStream is = new FileInputStream(path);
        //XSSFWorkbook ��ȡ������
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<String[]> list = new ArrayList<String[]>();
        String [] arr = null;
        // Read the Sheet
        //�������
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
        	//���γ�ʼ��sheet
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row ��ȡ��Sheet�е�ÿһ��
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            	//��ȡ��ÿһ�У�XSSFRow
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);//��
                if (xssfRow != null) {
                	//ÿ�е�ÿһ��
                	arr = new String[xssfRow.getLastCellNum()];
                    for(int xssfRowsize = 0;xssfRowsize < xssfRow.getLastCellNum();xssfRowsize++){
                    	arr[xssfRowsize] = getValue(xssfRow.getCell(xssfRowsize));
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
    public List<Object> readXls(String path) throws IOException {
    	//����·����ȡ�ļ�
        InputStream is = new FileInputStream(path);
        //HSSFWorkbook��ȡ���ļ�
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Object> list = new ArrayList<Object>();
        Object [] arr = null;
        // Read the Sheet ������Excel��ÿ��Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);//ÿһ��Sheet����
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row ��ȡÿ��Sheet�е�ÿһ��
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);//ÿһ�еĶ���
                if (hssfRow != null) {
                	//ÿ�е�ÿһ��
                	arr = new Object[hssfRow.getLastCellNum()];
                    for(int xssfRowsize = 0;xssfRowsize < hssfRow.getLastCellNum();xssfRowsize++){
                    	arr[xssfRowsize] = getValue(hssfRow.getCell(xssfRowsize));
                    }
                    list.add(arr);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            // ������ֵ���͵�ֵ
            Object inputValue = null;// ��Ԫ��ֵ
            Long longVal = Math.round(xssfRow.getNumericCellValue());
            Double doubleVal = xssfRow.getNumericCellValue();
            if(Double.parseDouble(longVal + ".0") == doubleVal){   //�ж��Ƿ���С��λ.0
                inputValue = longVal;
            }
            else{
                inputValue = doubleVal;
            }
            DecimalFormat df = new DecimalFormat("#.####");    //��ʽ��Ϊ��λС�������Լ�����ѡ��
            return String.valueOf(df.format(inputValue));      //����String����
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // ������ֵ���͵�ֵ
            Object inputValue = null;// ��Ԫ��ֵ
            Long longVal = Math.round(hssfCell.getNumericCellValue());
            Double doubleVal = hssfCell.getNumericCellValue();
            if(Double.parseDouble(longVal + ".0") == doubleVal){   //�ж��Ƿ���С��λ.0
                inputValue = longVal;
            }
            else{
                inputValue = doubleVal;
            }
            DecimalFormat df = new DecimalFormat("#.####");    //��ʽ��Ϊ��λС�������Լ�����ѡ��
            return String.valueOf(df.format(inputValue));      //����String����
        } else {
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
