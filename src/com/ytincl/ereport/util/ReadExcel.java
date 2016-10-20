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
    	//根据路径获取文件
        InputStream is = new FileInputStream(path);
        //HSSFWorkbook读取该文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<String[]> list = new ArrayList<String[]>();
        String [] arr = null;
        // Read the Sheet 解析该Excel的每个Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);//每一个Sheet对象
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row 读取每个Sheet中的每一行
            //rowNum 为开始的行
            for (int rowNum = starty; rowNum <= endy; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);//每一行的对象
                if (hssfRow != null) {
                	//初始化String数组的大小为数据的行数
                	arr = new String[hssfRow.getLastCellNum()];
                	//xssfRowsize为开始的列
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
    	//根据路径获取文件
        InputStream is = new FileInputStream(path);
        //HSSFWorkbook读取该文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<String[]> list = new ArrayList<String[]>();
        String [] arr = null;
        // Read the Sheet 解析该Excel的每个Sheet
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
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);//每一个Sheet对象
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row 读取每个Sheet中的每一行
            //rowNum 为开始的行
            for (int rowNum = starty; rowNum <= endy; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);//每一行的对象
                System.out.println("行数=="+rowNum);
                if (hssfRow != null) {
                	//初始化String数组的大小为数据的行数
                	arr = new String[endx-startx+1];
                	//xssfRowsize为开始的列
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
            Object inputValue = hssfCell.getNumericCellValue();// 单元格值
            Long longVal = Math.round(hssfCell.getNumericCellValue());
            Double doubleVal = hssfCell.getNumericCellValue();
            String stringVal = String.valueOf(hssfCell.getNumericCellValue())+"";
            if(Double.parseDouble(longVal + ".0") == doubleVal){   //判断是否含有小数位.0
                inputValue = longVal;
            }else{
                inputValue = doubleVal;
            }
            return String.valueOf(inputValue);
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA){
        	//数值类型
        	Object inputValue = hssfCell.getNumericCellValue();// 单元格值
            Long longVal = Math.round(hssfCell.getNumericCellValue());
        	Double doubleVal = hssfCell.getNumericCellValue();
        	if(Double.parseDouble(longVal + ".0") == doubleVal){   //判断是否含有小数位.0
                inputValue = longVal;
            }else{
                inputValue = doubleVal;
            }
        	return String.valueOf(inputValue);
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING){
        	//字符串类型
        	return String.valueOf(hssfCell.getStringCellValue());
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK){
        	System.out.println("控制转换为“”");
        	return " ";
        }else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    /*
     * 获取excel后缀
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
