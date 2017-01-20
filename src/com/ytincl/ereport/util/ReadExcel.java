package com.ytincl.ereport.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ytincl.ereport.constant.CommonConstants;



public class ReadExcel {
	private static Logger logger = LoggerFactory.getLogger(ReadExcel.class);
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
                if (hssfRow != null) {
                	//初始化String数组的大小为数据的行数
                	arr = new String[endx-startx+1];
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
     * Read the Excel 2003-2007 适合文件内容比较规则
     * @param path the path of the Excel,
     * @return
     * @throws IOException
     */
    public List<String[]> readXls2norm(String path,Map<String,String> map) throws IOException {
    	//根据路径获取文件
        InputStream is = new FileInputStream(path);
        //HSSFWorkbook读取该文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<String[]> list = new ArrayList<String[]>();
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
        String [] arr = new String[endx-startx+1];
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);//每一个Sheet对象
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row 读取每个Sheet中的每一行
            //rowNum 为开始的行
            
            for (int rowNum = starty; rowNum <= rowNum+1; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);//每一行的对象
                if (hssfRow != null) {
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
    /**
     * Read the Excel 2003-2007 适合文件内容比较规则,在线抓取
     * @param Excel File,
     * @return
     * @throws IOException
     */
    public Map<String,List<String[]>> readXls2norm(File file,Map<String,String> map) throws IOException {
    	//根据路径获取文件
        InputStream is = new FileInputStream(file);
        //HSSFWorkbook读取该文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<String[]> looplist = new ArrayList<String[]>();
        List<String[]> singlelist =  new ArrayList<String[]>();
        List<String[]> singlelist1 =  new ArrayList<String[]>();
        Map<String, List<String[]>> returnmap = new HashMap<>();
        // Read the Sheet 解析该Excel的每个Sheet
        int startx,starty,endx,endy;
        String sigledata = map.get("normal");
        String sigledata_sign = map.get("normal_sign");
        String start = map.get("datastart");
        String end = map.get("dataend");
        GetGrid gg = new GetGrid(start);
        startx = gg.getX();
        starty = gg.getY();
        int x = starty;
        GetGrid gg1 = new GetGrid(end);
        endx = gg1.getX();
        endy = gg1.getY();
        
        //读取循环数据
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);//每一个Sheet对象
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row 读取每个Sheet中的每一行
            //rowNum 为开始的行
            
            for (int rowNum = starty; rowNum <= x; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);//每一行的对象
                if (hssfRow != null) {
                	//xssfRowsize为开始的列
                	String[] strs = new String[endx-startx+1];
                    for(int xssfRowsize = startx,xb = 0;xssfRowsize <= endx;xssfRowsize++,xb++){
                    	strs[xb] = getValue(hssfRow.getCell(xssfRowsize));
                    }
                    looplist.add(strs);
                    x++;
                }
            }
            returnmap.put("loopdata", looplist);
            //读取单个数据
            GetGrid gg2;
            int siglex,siglexy;
            if(null != sigledata){
            	String[] sd = sigledata.split(",");
            	String[] sigled = new String[2];
            	for(int i = 0;i<sd.length;i++){
            		gg2 = new GetGrid(sd[i]);
            		siglex = gg2.getX();
            		siglexy = gg2.getY();
            		HSSFRow hssfRow = hssfSheet.getRow(siglexy);
            		String s = getValue(hssfRow.getCell(siglex));
            		sigled[i] = s;
            	}
            	singlelist.add(sigled);
            	returnmap.put("sl", singlelist);
            }
            
            if(null != sigledata_sign){
            	String[] sds = sigledata_sign.split(",");
            	String[] sigled_sing = new String[sds.length];
            	for(int i = 0;i<sds.length;i++){ 
            		gg2 = new GetGrid(sds[i]);
            		siglex = gg2.getX();
            		siglexy = gg2.getY();
            		HSSFRow hssfRow = hssfSheet.getRow(siglexy);
            		String s = getValue(hssfRow.getCell(siglex));
            		if(s.contains(":")){
            			s = s.split(":")[1];
            		}else{
            			s = s.split("：")[1];
            		}
            		sigled_sing[i] = s;
            	}
            	singlelist1.add(sigled_sing);
            	returnmap.put("sl1", singlelist1);
            }
        }
       
        return returnmap;
    }
    private String getValue(HSSFCell hssfCell) {
    	if(null == hssfCell || hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK){
        	return " ";
        }else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC || 
        		hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA) {
            Object inputValue = hssfCell.getNumericCellValue();// 单元格值
            Long longVal = Math.round(hssfCell.getNumericCellValue());
            Double doubleVal = hssfCell.getNumericCellValue();
            if(Double.parseDouble(longVal + ".0") == doubleVal){   //判断是否含有小数位.0
                inputValue = longVal;
            }else{
                inputValue = doubleVal;
            }
            inputValue = new DecimalFormat("#.###").format(inputValue); 
            return String.valueOf(inputValue);
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING){
        	//字符串类型
        	return String.valueOf(hssfCell.getStringCellValue());
        } else{
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
