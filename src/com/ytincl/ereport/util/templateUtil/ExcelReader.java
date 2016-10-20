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
 * ����Excel���Ĺ�����
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
	 * ��ȡExcel����ͷ������
	 * 
	 * @param InputStream
	 * @return String ��ͷ���ݵ�����
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
		// ����������
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
	 * ��ȡExcel����ͷ������
	 * 
	 * @param InputStream
	 * @param sheetNum
	 *            means ��ȡȫ����sheetҳ
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
		// ������
		int irow = sheet.getPhysicalNumberOfRows();
		// ��������
		int i = 0;
		int colNum = 0;

		if (sheet != null) {
			HSSFRow row0 = sheet.getRow(0);
			if (row0 != null)
				colNum = row0.getPhysicalNumberOfCells();
		}

		// �ӵڶ��п�ʼ��ȡ���ݣ�ÿһ��һ����Ϣ
		for (i = 1; i <= irow; i++) {
			// ��ȡ������
			HSSFRow row = sheet.getRow(i);
			// �в���Ϊ��
			if (null != row) {
				String[] strs = new String[colNum];
				for (int j = 0; j < colNum; j++) {
					HSSFCell cell = row.getCell(j); // �õ��� i ��
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
	 * ��ȡ��Ԫ����������Ϊ�ַ������͵�����
	 * 
	 * @param cell
	 *            Excel��Ԫ��
	 * @return String ��Ԫ����������
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
	 * ��ȡ��Ԫ����������Ϊ�������͵�����
	 * 
	 * @param cell
	 *            Excel��Ԫ��
	 * @return String ��Ԫ����������
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
				result = date.replaceAll("[����]", "-").replace("��", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("���ڸ�ʽ����ȷ!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ����HSSFCell������������
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// �жϵ�ǰCell��Type
			switch (cell.getCellType()) {
			// �����ǰCell��TypeΪNUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// �жϵ�ǰ��cell�Ƿ�ΪDate
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// �����Date������ת��ΪData��ʽ

					// ����1�������ӵ�data��ʽ�Ǵ�ʱ����ģ�2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// ����2�������ӵ�data��ʽ�ǲ�����ʱ����ģ�2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// ����Ǵ�����
				else {
					// ȡ�õ�ǰCell����ֵ
					Double d = cell.getNumericCellValue();
					NumberFormat nf = NumberFormat.getInstance();
					nf.setGroupingUsed(false);
					cellvalue = nf.format(d);
				}
				break;
			}
			// �����ǰCell��TypeΪSTRIN
			case HSSFCell.CELL_TYPE_STRING:
				// ȡ�õ�ǰ��Cell�ַ���
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// Ĭ�ϵ�Cellֵ
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
			
			//��һ�ϲ� A001����
			
			System.out.println("---------------------------------");
			ExcelReader  excelReader= new ExcelReader();
			String sheet1_kh = "d:\\ѧ���ɼ�������.xls";
			String sheet2_kh = "d:\\ѧ���ɼ�������1.xls";
			//String sheet1_kh = "d:\\����_��ƽ̨�ͻ������Ų�������ܱ�_20160927_11005293888888.xls";			
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
			//i������//j����
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
		
		//A001:������������õ��������⡱��List������������ļ�ȫ·�����������飨a1��a1,b1,c1),�к�
		//A001:ȥ���б����List(�����б���������У�
		//A001:ֻ��ѯ�б���List		
		String sheet1_kh = "d:\\ѧ���ɼ�������.xls";
		String sheet2_kh = "d:\\ѧ���ɼ�������1.xls";
		ReportTools2 reportTools2 = new ReportTools2();
		ExcelReader excelReader=new ExcelReader();		
		String[] fprop=reportTools2.getPos_ColRow("a6", "c6");	
		//�õ���������
		List<String[]> list1=excelReader.getALLExcelListByFileAndLetters(sheet1_kh,fprop, 0);
		excelReader.showListByString(list1);	
		//ֻ�õ���һ�У��б���
		String []firstArray =excelReader.getListArrayOnlyFirstRow(list1);
		excelReader.showOneArray(firstArray);		
		//�õ�����һ�����������
		List<String[]> list2=excelReader.getListArrayWithoutFirstRow(list1);
		excelReader.showListByString(list2);
		//�õ��б��������Ӧ��ֵ  a6:��ţ�b6:���֣�C6���ɼ�
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