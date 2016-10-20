package com.ytincl.ereport.util.templateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;



/**
 * 操作Excel表格的功能类
 */
public class ExcelReader {
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;
	public Logger log = Logger.getLogger(this.getClass());

	public ExcelReader(){
		
	}

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitle(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell(i));
		}
		return title;
	}

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @param sheetNum
	 *            means 读取全部的sheet页
	 * @throws Exception 
	 */
	public List<String[]> readExcelList(InputStream is,
			int sheetNum,String []fprop) throws Exception {
		return readExcelList(is,sheetNum,fprop,0);
	}
	
	public List<String[]> readExcelList(InputStream is,
			int sheetNum,String []fprop,int maxRow) throws Exception {
		List retList=new ArrayList();
		
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(sheetNum);
		int irow = sheet.getPhysicalNumberOfRows();
		System.out.println("111111111111------irow="+irow);
		//a6,b6,c6
		int cur_row=0;
		for(int i=0;i<fprop.length;i++){
		  int [] cur_cood=ReportTools2.getStringToCood_ColRow(fprop[i]);
		  cur_row=cur_cood[1];
		  break;
		 // String []cur_rowstr=new String[fprop.length];		  
		}
		System.out.println("95=="+cur_row);
		int cnt=0;
		while(true){
			 row = sheet.getRow(cur_row);
			 if(row==null){
				 break;
			 }
			 if(cnt>=maxRow&&maxRow!=0){
				 break;
			 }
			 String []cur_rowstr=new String[fprop.length];	
			 //System.out.println("1");			 
				for(int i=0;i<fprop.length;i++){
					  int [] tmp_cur_cood=ReportTools2.getStringToCood_ColRow(fprop[i]);
					  int tmp_cur_col=tmp_cur_cood[0];
					  cur_rowstr[i]=getCellString(row, tmp_cur_col);
					  //System.out.println("107::="+cur_rowstr[i]);
					 // String []cur_rowstr=new String[fprop.length];		  
				}
				retList.add(cur_rowstr);
			 
			 cnt++;
			 cur_row++;
			 if(cnt>=irow){
				 break;
			 }
	    }
		
		if(wb!=null){
			wb.close();
		}
        
		return retList;
	}

	public static List<String[]> getSheetColum(HSSFSheet sheet) {
		List<String[]> list = new ArrayList<String[]>();
		// 行总数
		int irow = sheet.getPhysicalNumberOfRows();
		// 声明变量
		int i = 0;
		int colNum = 0;

		if (sheet != null) {
			HSSFRow row0 = sheet.getRow(0);
			if (row0 != null)
				colNum = row0.getPhysicalNumberOfCells();
		}

		// 从第二行开始读取数据，每一行一条信息
		for (i = 1; i <= irow; i++) {
			// 获取行数据
			HSSFRow row = sheet.getRow(i);
			// 行不能为空
			if (null != row) {
				String[] strs = new String[colNum];
				for (int j = 0; j < colNum; j++) {
					HSSFCell cell = row.getCell(j); // 得到第 i 列
					if (cell != null) {
						strs[j] = cell.getStringCellValue();
					}
				}
				list.add(strs);
			}
		}
		return list;
	}




	public String getCellString(HSSFRow row2, int j) {
		if(row2==null){
			System.out.println("null");
		}
		if(row2.getCell(j)==null){
			return "";
		}		
		return getCellFormatValue(row2.getCell(j))==null?"":getCellFormatValue(row2.getCell(j)).trim();
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					Double d = cell.getNumericCellValue();
					NumberFormat nf = NumberFormat.getInstance();
					nf.setGroupingUsed(false);
					cellvalue = nf.format(d);
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

	public static void main3(String[] args) {
		try {
			
			//单一合并 A001例子
			
			System.out.println("---------------------------------");
			ExcelReader  excelReader= new ExcelReader();
			String sheet1_kh = "d:\\学生成绩工作表.xls";
			String sheet2_kh = "d:\\学生成绩工作表1.xls";
			//String sheet1_kh = "d:\\江苏_非平台客户风险排查情况汇总表_20160927_11005293888888.xls";			
			FileInputStream inputStream = new FileInputStream(sheet1_kh);	
			FileInputStream inputStream2 = new FileInputStream(sheet2_kh);	
			//a6,c6
			ReportTools2 tables = new ReportTools2();
			//tables.getStringToCood_ColRow("AA8");
			String[] fprop=tables.getPos_ColRow("a6", "c6");
			//System.out.println(tables.getStringToCoodNextNRowCoodString("c8", 10));			
			List<String[]> list1=excelReader.readExcelList(inputStream,0,fprop);	
			List<String[]> list2=excelReader.readExcelList(inputStream2,0,fprop);
			if(list1!=null){
				for(int i=0;i<list1.size();i++){
					String[]tmpstr=(String[])list1.get(i);
					for(int k=0;k<tmpstr.length;k++){
						//System.out.print(i+",tmpstr"+k+"="+tmpstr[k]);
						System.out.print(tmpstr[k]+",");
					}
					System.out.println("");
				}				
			}
			if(list2!=null){
				for(int i=0;i<list2.size();i++){
					String[]tmpstr=(String[])list2.get(i);
					for(int k=0;k<tmpstr.length;k++){
						//System.out.print(i+",tmpstr"+k+"="+tmpstr[k]);
						System.out.print(tmpstr[k]+",");
					}
					System.out.println("");
				}				
			}		
			
			String word="c6";
			int index=ReportTools2.getFpropsIndexByWord(fprop,word);			
			System.out.println("aaaa===="+index);
			
			
			
			List list3=ReportTools2.getSumColByIndexForSumList(list1,list2,index);
			if(list3!=null){
				for(int i=0;i<list3.size();i++){
					String[]tmpstr=(String[])list3.get(i);
					for(int k=0;k<tmpstr.length;k++){
						//System.out.print(i+",tmpstr"+k+"="+tmpstr[k]);
						System.out.print(tmpstr[k]+",");
					}
					System.out.println("");
				}				
			}	
			
			//excelReader.readExcelContentForKH(inputStream, 0);
			//Set propertySet = map.entrySet();
			//for (Object o : propertySet) {
				///Map.Entry entry = (Map.Entry) o;
				// System.out.printf("%s=%s%n",entry.getKey(),entry.getValue());

				//System.out.println(entry.getKey() + "," + entry.getValue());
				// log.debug(entry.getKey()+","+entry.getValue());
			//}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) throws Exception {	
		
		test(args);
		
		/*
		System.out.println("---------------------------");
		
		
		ExcelReader  excelReader= new ExcelReader();
		String a1="a1";
		String a2="a2";
		String a3="a3";
		String a4="a4";
		List list=new ArrayList();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		list.add(a4);
		
		 a1="b1";
		 a2="b2";
		 a3="b3";
		 a4="b4";
		
		
		List list2=new ArrayList();
		list2.add(a1);
		list2.add(a2);
		list2.add(a3);
		list2.add(a4);
		
		 a1="c1";
		 a2="c2";
		 a3="c3";
		 a4="c4";
		
		List list3=new ArrayList();
		list3.add(a1);
		list3.add(a2);
		list3.add(a3);
		list3.add(a4);
		
		
		
		List totalList=new ArrayList();
		totalList.add(list);
		totalList.add(list2);
		totalList.add(list3);
		
		String[]props =new String[]{"a1","b1","c1"};
		
		List show=excelReader.getMergeCol(props,totalList);
		//excelReader.showListByString(show);
		
	   */
	
	}
	
	
	public List<String[]> getMergeCol(String[]props,List<List<String>> totalList){
		List retList=new ArrayList();
		int row_num=((List)(totalList.get(0))).size();
		String [][]retTwoArray=new String[row_num][props.length];	
	  
		for(int i=0;i<row_num;i++){			
			for(int j=0;j<props.length;j++){
				retTwoArray[i][j]="";
			}			
	
		}
		System.out.println("426,row_num="+row_num);

		
		int string_len=props.length;
		
		for(int i=0;i<totalList.size();i++){			
			//i是索引//j是行
			List tmpList=(List)totalList.get(i);
			for (int j=0;j<tmpList.size();j++){				
				String tmpstr=(String)tmpList.get(j);	
				//System.out.println("i="+i+",j="+j+",retString["+i+"]="+retString[i]+",422="+tmpstr);
				//System.out.println("444="+tmpstr);				
				retTwoArray[j][i]=tmpstr;					
			}	
			//retString_new[i]=tmpstr;	
			//retList.set(i, retString_new);
			System.out.println("---------");
			
		}	
		
		showTwoArray(retTwoArray);
		retList=showTwoArrayToList(retList,retTwoArray);
		showListByString(retList);
		return retList;
	}
	
	
	public void showListByString(List list){
		System.out.println("list.size=()="+list.size());
		if(list!=null){
			for(int i=0;i<list.size();i++){
				String[]tmpstr=(String[])list.get(i);
				for(int k=0;k<tmpstr.length;k++){
					//System.out.print(i+",tmpstr"+k+"="+tmpstr[k]);
					System.out.print(i+",463="+tmpstr[k]+",");
				}
				System.out.println("");
			}				
		}	
		
	}
	
	public void showTwoArray(String[][] twoArray){
		System.out.println("showTwoArray,begin");
		for(int i=0;i<twoArray.length;i++){
			for(int j=0;j<twoArray[i].length;j++){
				System.out.print("twoArray["+i+"]["+j+"]="+twoArray[i][j]+",");
			}
			System.out.println("");
		}
		System.out.println("showTwoArray,end");
	}
	
	public void showOneArray(String[] oneArray){
		System.out.println("showOneArray,begin");
		for(int i=0;i<oneArray.length;i++){		
				System.out.print("oneArray["+i+"]="+oneArray[i]+",");			
		}
		System.out.println("");
		System.out.println("showOneArray,end");
	}	
	public List showTwoArrayToList(List retList,String[][] twoArray){
	
		for(int i=0;i<twoArray.length;i++){
			String[]retArray=new String[twoArray[i].length];
			for(int j=0;j<twoArray[i].length;j++){
				//System.out.print("showTwoArray,twoArray["+i+"]["+j+"]="+twoArray[i][j]+",");
				retArray[j]=twoArray[i][j];				
			}
			retList.add(retArray);
			System.out.println("");
		}
		return retList;
	}
	
	public static void test(String[] args) throws Exception{
		
		//A001:根据坐标数组得到“带标题”的List，输入参数：文件全路径，坐标数组（a1或a1,b1,c1),行号
		//A001:去掉列标题的List(不含列标题的所有行）
		//A001:只查询列标题List		
		String sheet1_kh = "d:\\学生成绩工作表.xls";
		String sheet2_kh = "d:\\学生成绩工作表1.xls";
		ReportTools2 reportTools2 = new ReportTools2();
		ExcelReader excelReader=new ExcelReader();		
		String[] fprop=reportTools2.getPos_ColRow("a6", "c6");	
		//得到所有数据
		List<String[]> list1=excelReader.getALLExcelListByFileAndLetters(sheet1_kh,fprop, 0);
		excelReader.showListByString(list1);	
		//只得到第一行，列标题
		String []firstArray =excelReader.getListArrayOnlyFirstRow(list1);
		excelReader.showOneArray(firstArray);		
		//得到除第一行外的所有行
		List<String[]> list2=excelReader.getListArrayWithoutFirstRow(list1);
		excelReader.showListByString(list2);
		//得到列标题坐标对应的值  a6:编号，b6:名字，C6：成绩
		Map colMap=excelReader.getColNameByLetter(fprop,firstArray);
		excelReader.showMapString(colMap);
		
	}
	
	
	public Map  getColNameByLetter(String[]fprop,String[]colValue){
		Map<String,String> map=new HashMap<String,String>();
		for(int i=0;i<fprop.length;i++){
			map.put(fprop[i], colValue[i]);
		}		
		return map;
		
	}
	public void showMapString(Map map){

	  Set<Map.Entry<String, String>> set=map.entrySet();
	  for(Iterator<Map.Entry<String,String>> it=set.iterator();it.hasNext();){
		  Map.Entry<String, String> entry=(Map.Entry<String, String>)it.next();
		  System.out.println(entry.getKey()+"--->"+entry.getValue());
	  }
		
	}
	
	
	
	
	
	
	public List<String[]> getALLExcelListByFileAndLetters(String filename,String[] fprop,int colAndDataRow) throws Exception{
		FileInputStream inputStream = new FileInputStream(filename);	
		List<String[]> list=readExcelList(inputStream,0,fprop,colAndDataRow);			
		return list;
	}
	public String[] getListArrayOnlyFirstRow(List<String[]> list) throws Exception{
	    String[]ret=null;
		if(list!=null&&list.size()!=0){
			for(int i=0;i<list.size();i++){
				if(i==0){
					ret=(String[])list.get(i);
					break;
				}
			}
		}				
		return ret;
	}	
	public List<String[]> getListArrayWithoutFirstRow(List<String[]> list) throws Exception{
		List<String[]> retList=new ArrayList<String[]>();
		if(list!=null&&list.size()!=0){
			for(int i=0;i<list.size();i++){
				if(i!=0){
					String[]ret=(String[])list.get(i);
					retList.add(ret);
				}
			}
		}				
		return retList;
	}		
}