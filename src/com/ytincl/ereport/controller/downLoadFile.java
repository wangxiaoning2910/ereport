package com.ytincl.ereport.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;  
import org.springframework.http.MediaType;  
import org.springframework.http.ResponseEntity;  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.fabric.xmlrpc.base.Array;
import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.model.ToBeDownLoadList;
import com.ytincl.ereport.model.pbsmr_busis;
import com.ytincl.ereport.model.pbsmr_entrustunits;
import com.ytincl.ereport.model.pbsmr_insts;
import com.ytincl.ereport.model.testdownloaddatalist;
import com.ytincl.ereport.pojo.RowData;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_entrustunit;
import com.ytincl.ereport.pojo.pbsmr_inst;
import com.ytincl.ereport.pojo.testdownloaddata;
import com.ytincl.ereport.service.ToBeDownLoadListService;
import com.ytincl.ereport.service.UpLoadFileService;
import com.ytincl.ereport.util.ExportExcel;
import com.ytincl.ereport.util.ExportExcel1;
import com.ytincl.ereport.util.FileUtil;
import com.ytincl.ereport.util.GetGrid;
import com.ytincl.ereport.util.ReadExcel; 

@Controller
public class downLoadFile {
	@Autowired
	private ToBeDownLoadListService getdownloadlist;
	private static Logger logger = LoggerFactory.getLogger(downLoadFile.class);
	
	@RequestMapping(value="/view/getToBeUpDownLoad.do")
	@ResponseBody
    public ToBeDownLoadList queryList(
    		@RequestParam(value="ymounth", required=true) String querydate,
    		HttpServletRequest request,HttpServletResponse response){  
		ArrayList<ToBeDownLoadFile> list;
		ToBeDownLoadList tbdll = new ToBeDownLoadList();
		list = getdownloadlist.getList(querydate);
		tbdll.setFilelist(list);
		return tbdll;
    } 

	/** 
	 * @Description 下载多个Excel文件 
	 * @author wxn 
	 * @date 2016年09月20日 
	 * @param ymounth - 日期  ; filename - 文件名
	 * @return ResponseEntity<byte[]> 
	 * @throws IOException */
	
	@RequestMapping(value="/view/exportfiles.do")
	public ResponseEntity<byte[]> exportfiles(
			@RequestParam(value="ymounth", required=true) String querydate,
			@RequestParam(value="filename", required=true) String fn,
    		HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1，查询数据库中的数据，
		//2，创建Excel文件，将数据写入
		//3，将文件送到前台下载
		String[] filenames = fn.split(",");
		int filenamenum = filenames.length;
		String[] downloadfiles = new String[filenamenum];
		//文件夹路径
		String filePath = request.getSession().getServletContext().getRealPath(CommonConstants.UPLOADFILEPATH);
		String oldfilePath = request.getSession().getServletContext().getRealPath("/resources");
		//反斜杠
		String backslant = CommonConstants.BACKSLANT;
		String filename = "";
		String oldfilename = "";
		String fullPath = "";
		String oldfullPath = "";
		ReadExcel re = new ReadExcel();
		
		for(int i = 0; i < filenamenum; i++){
			if(filenames[i].equals("代收付按业务(全口径)")){
				oldfilename = "代收付按业务(全口径)"+CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
				//文件名+后缀
				filename = filenames[i]+querydate + CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
				//路径+文件名+后缀
				fullPath = filePath + backslant +filename;
				oldfullPath = oldfilePath+backslant+oldfilename;
				pbsmr_busis pbs;
				pbsmr_busis pbs2015;
				pbsmr_busis pbs2016;
				pbsmr_busis pbs20151;
				pbsmr_busi pb;
				pbsmr_busi pb2015;
				pbsmr_busi pb2016;
				pbsmr_busi pb20151;
				String[] data_busicode1;
				String[] data_busicode2;
				List<pbsmr_busi> list1 = new ArrayList<pbsmr_busi>();
				List<pbsmr_busi> list2 = new ArrayList<pbsmr_busi>();
				List<pbsmr_busi> list3 = new ArrayList<pbsmr_busi>();
				List<pbsmr_busi> list4 = new ArrayList<pbsmr_busi>();
				List<pbsmr_busi> list5 = new ArrayList<pbsmr_busi>();
				List<pbsmr_busi> list6 = new ArrayList<pbsmr_busi>();
				List<pbsmr_busi> list7 = new ArrayList<pbsmr_busi>();
				List<pbsmr_busi> list8 = new ArrayList<pbsmr_busi>();
				pbs = new pbsmr_busis();//2016代收付业务统计月报-按业务(全口径按交易机构）
				pb = new pbsmr_busi();
				pb.setReportName("代收付业务统计月报--按业务");
				pb.setStatisticsdate(querydate);
				pb.setStatisticstype("0-交易机构");
				pb.setDotproperties("0-全部");
				pbs.setList(getdownloadlist.getpbsmr_busiList(pb));
				pbs2015 = new pbsmr_busis();//2015代收付业务统计月报-按业务(全口径按交易机构）
				pb2015 = new pbsmr_busi();
				pb2015.setReportName("代收付业务统计月报--按业务");
				pb2015.setStatisticsdate(String.valueOf(Integer.parseInt(querydate)-100));
				pb2015.setStatisticstype("0-交易机构");
				pb2015.setDotproperties("0-全部");
				pbs2015.setList(getdownloadlist.getpbsmr_busiList(pb2015));
				pbs2016 = new pbsmr_busis();//2016代收付业务统计月报-按业务(全口径按开户机构）
				pb2016 = new pbsmr_busi();
				pb2016.setReportName("代收付业务统计月报--按业务");
				pb2016.setStatisticsdate(querydate);
				pb2016.setStatisticstype("1-开户机构");
				pb2016.setDotproperties("0-全部");
				pbs2016.setList(getdownloadlist.getpbsmr_busiList(pb2016));
				pbs20151 = new pbsmr_busis();//2015代收付业务统计月报-按业务(全口径按开户机构）
				pb20151 = new pbsmr_busi();
				pb20151.setReportName("代收付业务统计月报--按业务");
				pb20151.setStatisticsdate(String.valueOf(Integer.parseInt(querydate)-100));
				pb20151.setStatisticstype("1-开户机构");
				pb20151.setDotproperties("0-全部");
				pbs20151.setList(getdownloadlist.getpbsmr_busiList(pb20151));
				data_busicode1 = new String[pbs.getList().size()];
				data_busicode2 = new String[pbs.getList().size()];
				File of = new File(oldfullPath);
				data_busicode1 = re.getfixedata(of, 2, 6, 2, 53);
				data_busicode2 = re.getfixedata(of, 2, 54, 2, 81);
				
				for(int x = 0;x<data_busicode1.length;x++){
					//logger.debug("用来比较的值==="+data_busicode1[x]);
					for(int j = 0;j<pbs.getList().size();j++){
						//logger.debug("pojo中的值===="+pbs.getList().get(j).getBusicode());
						if(pbs.getList().get(j).getBusicode().equals(data_busicode1[x])){
							//logger.debug("找到相同项，业务代码为==="+pbs.getList().get(j).getBusicode());
							list1.add(pbs.getList().get(j));
						}
					}
					for(int k = 0;k<pbs2015.getList().size();k++){
						if(pbs2015.getList().get(k).getBusicode().equals(data_busicode1[x])){
							list2.add(pbs2015.getList().get(k));
						}
					}
					for(int l = 0;l<pbs2016.getList().size();l++){
						if(pbs2016.getList().get(l).getBusicode().equals(data_busicode1[x])){
							list3.add(pbs2016.getList().get(l));
						}
					}
					for(int m = 0;m<pbs20151.getList().size();m++){
						if(pbs20151.getList().get(m).getBusicode().equals(data_busicode1[x])){
							list4.add(pbs20151.getList().get(m));
						}
					}
				}
				for(int x = 0;x<data_busicode2.length;x++){
					//logger.debug("用来比较的值==="+data_busicode1[x]);
					for(int j = 0;j<pbs.getList().size();j++){
						//logger.debug("pojo中的值===="+pbs.getList().get(j).getBusicode());
						if(pbs.getList().get(j).getBusicode().equals(data_busicode2[x])){
							//logger.debug("找到相同项，业务代码为==="+pbs.getList().get(j).getBusicode());
							list5.add(pbs.getList().get(j));
						}
					}
					for(int k = 0;k<pbs2015.getList().size();k++){
						if(pbs2015.getList().get(k).getBusicode().equals(data_busicode2[x])){
							list6.add(pbs2015.getList().get(k));
						}
					}
					for(int l = 0;l<pbs2016.getList().size();l++){
						if(pbs2016.getList().get(l).getBusicode().equals(data_busicode2[x])){
							list7.add(pbs2016.getList().get(l));
						}
					}
					for(int m = 0;m<pbs20151.getList().size();m++){
						if(pbs20151.getList().get(m).getBusicode().equals(data_busicode2[x])){
							list8.add(pbs20151.getList().get(m));
						}
					}
				}
				//
		        InputStream is = new FileInputStream(of);
		        //HSSFWorkbook读取该文件
		        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
				HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
				if(sheet == null){
					logger.debug("sheet 是 null");
				}else{
					int param = 0;
					int dsxj_bqfs = 0;
					int dsxj_bnlj = 0;
					float jyje_bqfs = 0;
					float jyje_bnlj = 0;
					float jybsbfb = 0;
					float khjgbqje = 0;
					float khjgljje = 0;
					float jebfb = 0;
					for (int rowNum = 6; rowNum < 52; rowNum++) {
			            HSSFRow hssfRow = sheet.getRow(rowNum);//每一行的对象
			            HSSFCell cell = hssfRow.getCell(4);
			            HSSFCell cell1 = hssfRow.getCell(5);
			            HSSFCell cell3 = hssfRow.getCell(7);
			            HSSFCell cell4 = hssfRow.getCell(8);
			            if(list1.isEmpty()){
			            	cell.setCellValue("-");
				            cell1.setCellValue("-");
				            cell3.setCellValue("-");
				            cell4.setCellValue("-");
			            }else{
			            	cell.setCellValue(list1.get(param).getTransamount_month());
				            cell1.setCellValue(list1.get(param).getTransamount_year());
				            cell3.setCellValue(list1.get(param).getTransmoney_month());
				            cell4.setCellValue(list1.get(param).getTransmoney_year());
				            dsxj_bqfs = dsxj_bqfs + Integer.parseInt(list1.get(param).getTransamount_month());
				            dsxj_bnlj = dsxj_bnlj + Integer.parseInt(list1.get(param).getTransamount_year());
				            jyje_bqfs = jyje_bqfs + Float.parseFloat(list1.get(param).getTransmoney_month());
				            jyje_bnlj = jyje_bnlj + Float.parseFloat(list1.get(param).getTransmoney_year());
			            }
			            
			            
			            HSSFCell cell2 = hssfRow.getCell(6);
			            if(list1.isEmpty() || list2.isEmpty()){
			            	cell2.setCellValue("-");
			            }else{
			            	cell2.setCellValue(Float.parseFloat(list1.get(param).getTransamount_year())/Float.parseFloat(list2.get(param).getTransamount_year()));
			            	jybsbfb = jybsbfb + Float.parseFloat(list1.get(param).getTransamount_year())/Float.parseFloat(list2.get(param).getTransamount_year());
			            }
			            
			            HSSFCell cell5 = hssfRow.getCell(9);
			            HSSFCell cell6 = hssfRow.getCell(10);
			            if(list3.isEmpty()){
			            	cell5.setCellValue("-");
			            	cell6.setCellValue("-");
			            }else{
			            	cell5.setCellValue(list3.get(param).getTransmoney_month());
			            	khjgbqje = khjgbqje + Float.parseFloat(list3.get(param).getTransmoney_month());
			            	cell6.setCellValue(list3.get(param).getTransmoney_year());
			            	khjgljje = khjgljje + Float.parseFloat(list3.get(param).getTransmoney_year());
			            }
			            
			            HSSFCell cell7 = hssfRow.getCell(11);
			            if(list4.isEmpty() || list3.isEmpty()){
			            	cell7.setCellValue("-");
			            }else{
			            	if(list4.size() == list3.size()){
			            		cell7.setCellValue(Float.parseFloat(list3.get(param).getTransmoney_year()) / Float.parseFloat(list4.get(param).getTransmoney_year()));
			            		jebfb = jebfb + Float.parseFloat(list3.get(param).getTransmoney_year()) / Float.parseFloat(list4.get(param).getTransmoney_year());
			            	}else if(list4.size() > list3.size()){
		            			String cp = list4.get(param).getBusicode();
		            			float ft = 0;
			            		for(int xh = 0;xh<list3.size();xh++){
			            			if(list3.get(xh).getBusicode().equals(cp)){
			            				ft = Float.parseFloat(list3.get(xh).getTransmoney_year()) / Float.parseFloat(list4.get(param).getTransmoney_year());
			            			}else{
			            			}
			            		}
			            		if(ft == 0){
		            				cell7.setCellValue("-");
		            			}else{
		            				cell7.setCellValue(ft);
		            				jebfb = jebfb + ft;
		            			}
			            	}else{
		            			String cp = list3.get(param).getBusicode();
		            			float ft = 0;
		            			for(int xh1 = 0;xh1<list4.size();xh1++){
			            			if(list4.get(xh1).getBusicode().equals(cp)){
			            				ft = Float.parseFloat(list3.get(param).getTransmoney_year()) / Float.parseFloat(list4.get(xh1).getTransmoney_year());
			            			}else{
			            			}
			            		}
		            			if(ft ==0){
		            				cell7.setCellValue("-");
		            			}else{
		            				cell7.setCellValue(ft);
		            				jebfb = jebfb + ft;
		            			}
			            	}
			            	
			            }
			            param++;
			        }
					int param1 = 0;
					int dsxj_bqfs1 = 0;
					int dsxj_bnlj1 = 0;
					float jyje_bqfs1 = 0;
					float jyje_bnlj1 = 0;
					float jybsbfb1 = 0;
					float khjgbqje1 = 0;
					float khjgljje1 = 0;
					float jebfb1 = 0;
					for(int rowNum1 = 54;rowNum1 < 80;rowNum1++){
						HSSFRow hssfRow = sheet.getRow(rowNum1);//每一行的对象
						HSSFCell cell = hssfRow.getCell(4);
				        HSSFCell cell1 = hssfRow.getCell(5);
				        HSSFCell cell2 = hssfRow.getCell(6);
				        HSSFCell cell3 = hssfRow.getCell(7);
				        HSSFCell cell4 = hssfRow.getCell(8);
				        HSSFCell cell5 = hssfRow.getCell(9);
				        HSSFCell cell6 = hssfRow.getCell(10);
				        HSSFCell cell7 = hssfRow.getCell(11);
				        if(list5.isEmpty()){
			            	cell.setCellValue("-");
				            cell1.setCellValue("-");
				            cell3.setCellValue("-");
				            cell4.setCellValue("-");
			            }else{
			            	cell.setCellValue(list5.get(param1).getTransamount_month());
				            cell1.setCellValue(list5.get(param1).getTransamount_year());
				            cell3.setCellValue(list5.get(param1).getTransmoney_month());
				            cell4.setCellValue(list5.get(param1).getTransmoney_year());
				            dsxj_bqfs1 = dsxj_bqfs1 + Integer.parseInt(list5.get(param1).getTransamount_month());
				            dsxj_bnlj1 = dsxj_bnlj1 + Integer.parseInt(list5.get(param1).getTransamount_year());
				            jyje_bqfs1 = jyje_bqfs1 + Float.parseFloat(list5.get(param1).getTransmoney_month());
				            jyje_bnlj1 = jyje_bnlj1 + Float.parseFloat(list5.get(param1).getTransmoney_year());
			            }
				        if(list5.isEmpty() || list6.isEmpty()){
			            	cell2.setCellValue("-");
			            }else{
			            	cell2.setCellValue(Float.parseFloat(list5.get(param1).getTransamount_year())/Float.parseFloat(list6.get(param1).getTransamount_year()));
			            	jybsbfb1 = jybsbfb1 + Float.parseFloat(list5.get(param1).getTransamount_year())/Float.parseFloat(list6.get(param1).getTransamount_year());
			            }
				        if(list7.isEmpty()){
			            	cell5.setCellValue("-");
			            	cell6.setCellValue("-");
			            }else{
			            	cell5.setCellValue(list7.get(param1).getTransmoney_month());
			            	cell6.setCellValue(list7.get(param1).getTransmoney_year());
			            	khjgbqje1 = khjgbqje1 + Float.parseFloat(list7.get(param1).getTransmoney_month());
			            	khjgljje1 = khjgljje1 + Float.parseFloat(list7.get(param1).getTransmoney_year());
			            }
				        if(list8.isEmpty() || list7.isEmpty()){
			            	cell7.setCellValue("-");
			            }else{
			            	if(list8.size() == list7.size()){
			            		cell7.setCellValue(Float.parseFloat(list7.get(param1).getTransmoney_year()) / Float.parseFloat(list7.get(param1).getTransmoney_year()));
			            		jebfb1 = jebfb1 + Float.parseFloat(list7.get(param1).getTransmoney_year()) / Float.parseFloat(list7.get(param1).getTransmoney_year());
			            	}else if(list8.size() > list7.size()){
		            			String cp = list8.get(param1).getBusicode();
		            			float ft = 0;
			            		for(int xh = 0;xh<list7.size();xh++){
			            			if(list7.get(xh).getBusicode().equals(cp)){
			            				ft = Float.parseFloat(list7.get(xh).getTransmoney_year()) / Float.parseFloat(list8.get(param1).getTransmoney_year());
			            			}else{
			            			}
			            		}
			            		if(ft ==0){
		            				cell7.setCellValue("-");
		            			}else{
		            				cell7.setCellValue(ft);
		            				jebfb1 = jebfb1 + ft;
		            			}
			            	}else{
		            			String cp = list7.get(param1).getBusicode();
		            			float ft = 0;
		            			for(int xh1 = 0;xh1<list8.size();xh1++){
			            			if(list8.get(xh1).getBusicode().equals(cp)){
			            				ft = Float.parseFloat(list7.get(param1).getTransmoney_year()) / Float.parseFloat(list8.get(xh1).getTransmoney_year());
			            			}else{
			            			}
			            		}
		            			if(ft ==0){
		            				cell7.setCellValue("-");
		            			}else{
		            				cell7.setCellValue(ft);
		            				jebfb1 = jebfb1 + ft;
		            			}
			            	}
			            	
			            }
				        param1++;
					}
					//算小计
					HSSFRow hssfRow = sheet.getRow(53);//每一行的对象
					HSSFCell cell = hssfRow.getCell(4);
					HSSFCell cell2 = hssfRow.getCell(5);
					HSSFCell cell3 = hssfRow.getCell(6);
					HSSFCell cell4 = hssfRow.getCell(7);
					HSSFCell cell5 = hssfRow.getCell(8);
					HSSFCell cell6 = hssfRow.getCell(9);
					HSSFCell cell7 = hssfRow.getCell(10);
					HSSFCell cell8 = hssfRow.getCell(11);
					
					cell.setCellValue(dsxj_bqfs);
					cell2.setCellValue(dsxj_bnlj);
					cell3.setCellValue(jybsbfb);
					cell4.setCellValue(jyje_bqfs);
					cell5.setCellValue(jyje_bnlj);
					cell6.setCellValue(khjgbqje);
					cell7.setCellValue(khjgljje);
					cell8.setCellValue(jebfb);
					HSSFRow hssfRow1 = sheet.getRow(81);//每一行的对象
					HSSFCell c = hssfRow1.getCell(4);
					HSSFCell c2 = hssfRow1.getCell(5);
					HSSFCell c3 = hssfRow1.getCell(6);
					HSSFCell c4 = hssfRow1.getCell(7);
					HSSFCell c5 = hssfRow1.getCell(8);
					HSSFCell c6 = hssfRow1.getCell(9);
					HSSFCell c7 = hssfRow1.getCell(10);
					HSSFCell c8 = hssfRow1.getCell(11);
					
					c.setCellValue(dsxj_bqfs1);
					c2.setCellValue(dsxj_bnlj1);
					c3.setCellValue(jybsbfb1);
					c4.setCellValue(jyje_bqfs1);
					c5.setCellValue(jyje_bnlj1);
					c6.setCellValue(khjgbqje1);
					c7.setCellValue(khjgljje1);
					c8.setCellValue(jebfb1);
					
					HSSFRow hssfRow2 = sheet.getRow(6);//总计
					HSSFCell z = hssfRow2.getCell(4);
					HSSFCell z2 = hssfRow2.getCell(5);
					HSSFCell z3 = hssfRow2.getCell(6);
					HSSFCell z4 = hssfRow2.getCell(7);
					HSSFCell z5 = hssfRow2.getCell(8);
					HSSFCell z6 = hssfRow2.getCell(9);
					HSSFCell z7 = hssfRow2.getCell(10);
					HSSFCell z8 = hssfRow2.getCell(11);
					z.setCellValue(dsxj_bqfs1+dsxj_bqfs);
					z2.setCellValue(dsxj_bnlj1+dsxj_bnlj);
					z3.setCellValue(jybsbfb1+jybsbfb);
					z4.setCellValue(jyje_bqfs1+jyje_bqfs);
					z5.setCellValue(jyje_bnlj1+jyje_bnlj);
					z6.setCellValue(khjgbqje1+khjgbqje);
					z7.setCellValue(khjgljje1+khjgljje);
					z8.setCellValue(jebfb1+jebfb);
					
					
					FileOutputStream out = new FileOutputStream(fullPath);
					hssfWorkbook.write(out);
					hssfWorkbook.close();
					
					downloadfiles[i] = fullPath;
					out.close();
				}
			}else if(filenames[i].equals("代收付按业务(自营)")){
				oldfilename = "代收付按业务(自营)"+CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
				//文件名+后缀
				filename = filenames[i]+querydate + CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
				//路径+文件名+后缀
				fullPath = filePath + backslant +filename;
				oldfullPath = oldfilePath+backslant+oldfilename;
				
				//查询数据 共三个数据来源
				pbsmr_busis pbs1 = new pbsmr_busis();//代收付业务月报-按业务(自营按开户机构)2016年12月
				pbsmr_busis pbs2 = new pbsmr_busis();//代收付业务月报-按业务(自营按开户机构)2015年12月
				pbsmr_busi pb1 = new pbsmr_busi();
				pbsmr_busi pb2 = new pbsmr_busi();
				pb1.setReportName("代收付业务统计月报--按业务");
				pb1.setStatisticsdate(querydate);
				pb1.setStatisticstype("1-开户机构");
				pb1.setDotproperties("1-自营");
				pb2.setReportName("代收付业务统计月报--按业务");
				pb2.setStatisticsdate(String.valueOf(Integer.parseInt(querydate)-100));
				pb2.setStatisticstype("1-开户机构");
				pb2.setDotproperties("1-自营");
				pbs1.setList(getdownloadlist.getpbsmr_busiList(pb1));
				pbs2.setList(getdownloadlist.getpbsmr_busiList(pb2));
				
				//获取模板文件
				File file = new File(oldfullPath);
				
				//获取模板文件的固定列
				String[] test_data_businame = {"201010","201014","202000","202010","202011",
						"202012","202017","202020","202021","202022","202030","202040","202050","203000",
						"202019","203010","203011","203012","203014","203015","203016","203017",
						"203019","203020","203021","203022","203023","203024","202070","203025",
						"203026","203042","204010","208010","208050","206040","206051","201041",
						"208030","207020","208095","206030","202060","205020","101020","101030",
						"101040","101050","102010","102020","102030","103011","103014","103000",
						"103010","105020","105100","105110","105220","106010","105000","108000",
						"112010","111100","101070","103060","104000","104021","111210"};
				List<Integer> sp = new ArrayList<Integer>();
				sp.add(6);
				sp.add(18);
				sp.add(38);
				sp.add(51);
				sp.add(77);
				
				//将数据更具读取到模板中的固定列得顺序放置到List当中
				List<pbsmr_busi> test1 = new ArrayList<pbsmr_busi>();
				List<pbsmr_busi> test2 = new ArrayList<pbsmr_busi>();
				
				for(int j = 0;j<test_data_businame.length;j++){
					for(int k = 0;k<pbs1.getList().size();k++){
						if(pbs1.getList().get(k).getBusicode().equals(test_data_businame[j])){
							test1.add(pbs1.getList().get(k));
						}
					}
					for(int l = 0;l<pbs2.getList().size();l++){
						if(pbs2.getList().get(l).getBusicode().equals(test_data_businame[j])){
							test2.add(pbs2.getList().get(l));
						}
					}
				}
				//开始向模板文件中写入数据
				InputStream is = new FileInputStream(file);
		        //HSSFWorkbook读取该文件
		        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
				HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
				if(sheet == null){
					logger.debug("sheet 是 null");
				}else{
					int param = 0;
					int test1size = test1.size();
					int test2size = test2.size();
		            for(int t = 4;t<=77;t++){
		            	HSSFRow row = sheet.getRow(t);
	            		HSSFCell cell1 = row.getCell(3);
	            		HSSFCell cell2 = row.getCell(4);
	            		HSSFCell cell3 = row.getCell(5);
	            		HSSFCell cell4 = row.getCell(6);
	            		HSSFCell cell5 = row.getCell(7);
	            		HSSFCell cell6 = row.getCell(8);
		            	if(!ishave(t, sp)){
		            		cell1.setCellValue(test1.get(param).getTransamount_month());
		            		cell2.setCellValue(test1.get(param).getTransamount_year());
		            		cell4.setCellValue(test1.get(param).getTransmoney_month());
		            		cell5.setCellValue(test1.get(param).getTransmoney_year());
		            		float bfb = 0;
		            		float bfb1 = 0;
		            		if(test1size > test2size){
		            			int transnum2016 = Integer.parseInt(test1.get(param).getTransamount_year());
		            			float tranmoney2016 = Float.parseFloat(test1.get(param).getTransmoney_year());
		            			for(int pp = 0;pp<test2size;pp++){
		            				if(test1.get(param).getBusicode().equals(test2.get(pp).getBusicode())){
		            					int transnum2015 = Integer.parseInt(test2.get(pp).getTransamount_year());
		            					float tranmonye2015 = Float.parseFloat(test2.get(pp).getTransmoney_year());
		            					bfb = (float)(transnum2016 - transnum2015) / transnum2015;
		            					bfb1 = (float)(tranmoney2016 - tranmonye2015) / tranmonye2015;
		            					pp = test2size;
		            				}
		            			}
		            			if(bfb == 0){
		            				cell3.setCellValue("-");
		            				cell6.setCellValue("-");
		            			}else{
		            				cell3.setCellValue(bfb);
		            				cell6.setCellValue(bfb1);
		            			}
		            			
		            				
		            		}else{
		            			
		            		}
		            		
		            		param++;
		            	}else{
		            		HSSFRow xjrow = sheet.getRow(t);
							HSSFCell xjcell = xjrow.getCell(3);
							HSSFCell xjcell1 = xjrow.getCell(4);
							HSSFCell xjcell2 = xjrow.getCell(5);
							HSSFCell xjcell3 = xjrow.getCell(6);
							HSSFCell xjcell4 = xjrow.getCell(7);
							HSSFCell xjcell5 = xjrow.getCell(8);
		            		switch (t) {
							case 6:
								int zs = Integer.parseInt(test1.get(0).getTransamount_month())+Integer.parseInt(test1.get(1).getTransamount_month());
								int zs1 = Integer.parseInt(test1.get(0).getTransamount_year())+Integer.parseInt(test1.get(1).getTransamount_year());
								double a = (double)(Integer.parseInt(test1.get(0).getTransamount_year()) - Integer.parseInt(test2.get(0).getTransamount_year()))
										/Integer.parseInt(test2.get(0).getTransamount_year());
								double b = (double)(Integer.parseInt(test1.get(1).getTransamount_year()) - Integer.parseInt(test2.get(1).getTransamount_year()))
										/Integer.parseInt(test2.get(1).getTransamount_year());
								double bfbxj = a+b;
								double je = Double.parseDouble(test1.get(0).getTransmoney_month())+Double.parseDouble(test1.get(1).getTransmoney_month());
								double je1 = Double.parseDouble(test1.get(0).getTransmoney_year())+Double.parseDouble(test1.get(1).getTransmoney_year());
								double c = (double)(Float.parseFloat(test1.get(0).getTransmoney_year()) - Float.parseFloat(test2.get(0).getTransmoney_year()))/
										Float.parseFloat(test2.get(0).getTransmoney_year());
								double d = (double)(Float.parseFloat(test1.get(1).getTransmoney_year()) - Float.parseFloat(test2.get(1).getTransmoney_year()))/
										Float.parseFloat(test2.get(1).getTransmoney_year());
								double jebfb = c+d;
								xjcell.setCellValue(zs);
								xjcell1.setCellValue(zs1);
								xjcell2.setCellValue(bfbxj);
								xjcell3.setCellValue(je);
								xjcell4.setCellValue(je1);
								xjcell5.setCellValue(jebfb);
								break;
							case 18:
								getxj(test1,test2,2,13,xjcell,xjcell1,xjcell2,xjcell3,xjcell4,xjcell5);
								break;
							case 38:
								getxj(test1,test2,13,32,xjcell,xjcell1,xjcell2,xjcell3,xjcell4,xjcell5);
								break;
							case 51:
								getxj(test1,test2,0,44,xjcell,xjcell1,xjcell2,xjcell3,xjcell4,xjcell5);
								break;
							case 77:
								getxj(test1,test2,44,69,xjcell,xjcell1,xjcell2,xjcell3,xjcell4,xjcell5);
								break;
							default:
								break;
							}
		            		
		            	}
		            	
		            }
		            
				}
				FileOutputStream out = new FileOutputStream(fullPath);
				hssfWorkbook.write(out);
				hssfWorkbook.close();
				downloadfiles[i] = fullPath;
				out.close();
				
			}else if(filenames[i].equals("代收付按机构")){
				oldfilename = filenames[i]+CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
				//文件名+后缀
				filename = filenames[i]+querydate + CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
				//路径+文件名+后缀
				fullPath = filePath + backslant +filename;
				oldfullPath = oldfilePath+backslant+oldfilename;
				
				//查询数据
				pbsmr_insts pis1 = new pbsmr_insts();//全口径业务代码1
				pbsmr_insts pis2 = new pbsmr_insts();//全口径业务代码2
				pbsmr_insts pis3 = new pbsmr_insts();//自营业务代码1——2016
				pbsmr_insts pis4 = new pbsmr_insts();//自营业务代码1——2015
				pbsmr_insts pis5 = new pbsmr_insts();//自营业务代码2——2016
				pbsmr_insts pis6 = new pbsmr_insts();//自营业务代码2——2015
				pbsmr_inst pi = new pbsmr_inst();
				pi.setReportName("代收付业务统计月报--按机构");
				pi.setStatisticsdate(querydate);
				pi.setStatisticstype("1-开户机构");
				pi.setDotproperties("0-全部");
				pi.setBusicode("1");
				pis1.setList(getdownloadlist.getpbsmr_instList(pi));
				pi.setReportName("代收付业务统计月报--按机构");
				pi.setStatisticsdate(querydate);
				pi.setStatisticstype("1-开户机构");
				pi.setDotproperties("0-全部");
				pi.setBusicode("2");
				pis2.setList(getdownloadlist.getpbsmr_instList(pi));
				pi.setReportName("代收付业务统计月报--按机构");
				pi.setStatisticsdate(querydate);
				pi.setStatisticstype("1-开户机构");
				pi.setDotproperties("1-自营");
				pi.setBusicode("1");
				pis3.setList(getdownloadlist.getpbsmr_instList(pi));
				pi.setReportName("代收付业务统计月报--按机构");
				pi.setStatisticsdate(String.valueOf(Integer.parseInt(querydate)-100));
				pi.setStatisticstype("1-开户机构");
				pi.setDotproperties("1-自营");
				pi.setBusicode("1");
				pis4.setList(getdownloadlist.getpbsmr_instList(pi));
				pi.setReportName("代收付业务统计月报--按机构");
				pi.setStatisticsdate(querydate);
				pi.setStatisticstype("1-开户机构");
				pi.setDotproperties("1-自营");
				pi.setBusicode("2");
				pis5.setList(getdownloadlist.getpbsmr_instList(pi));
				pi.setReportName("代收付业务统计月报--按机构");
				pi.setStatisticsdate(String.valueOf(Integer.parseInt(querydate)-100));
				pi.setStatisticstype("1-开户机构");
				pi.setDotproperties("1-自营");
				pi.setBusicode("2");
				pis6.setList(getdownloadlist.getpbsmr_instList(pi));
				
				//获取模板文件
				File file = new File(oldfullPath);
				
				//获取模板文件的固定列
				String[] Permutation = {"哈尔滨","佳木斯","绥化","牡丹江","齐齐哈尔","大庆","鸡西","双鸭山","黑河","鹤岗","伊春",
						"七台河","大兴安岭","直属支行"};
				
				//开始向模板文件中写入数据
				InputStream is = new FileInputStream(file);
		        //HSSFWorkbook读取该文件
		        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
				HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
				if(sheet == null){
					logger.debug("sheet 是 null");
				}else{
					int pt = 0;//获取每行的名称
					pbsmr_inst temp1;
					pbsmr_inst temp2;
					pbsmr_inst temp3;
					pbsmr_inst temp4;
					pbsmr_inst temp5;
					pbsmr_inst temp6;
					
					int xj1 = 0;
					int xj2 = 0;
					int xj3 = 0;
					int xj4 = 0;
					double xj5 = 0;
					double xj6 = 0;
					double xj7 = 0;
					double xj8 = 0;
					double xj9 = 0;
					double xj10 = 0;
					double xj11 = 0;
					double xj12 = 0;
					
					for(int row = 4;row < 18;row++){
						HSSFRow xjrow = sheet.getRow(row);
						HSSFCell setcell1 = xjrow.getCell(2);
						HSSFCell setcell2 = xjrow.getCell(3);
						HSSFCell setcell3 = xjrow.getCell(4);
						HSSFCell setcell4 = xjrow.getCell(5);
						HSSFCell setcell5 = xjrow.getCell(6);
						HSSFCell setcell6 = xjrow.getCell(7);
						HSSFCell setcell7 = xjrow.getCell(8);
						HSSFCell setcell8 = xjrow.getCell(9);
						HSSFCell setcell9 = xjrow.getCell(10);
						HSSFCell setcell10 = xjrow.getCell(11);
						HSSFCell setcell11 = xjrow.getCell(12);
						HSSFCell setcell12 = xjrow.getCell(13);
						temp2 = getpi(pis2,Permutation[pt]);//全口径业务代码2
						temp5 = getpi(pis5,Permutation[pt]);//自营业务代码2——2016
						temp6 = getpi(pis6,Permutation[pt]);//自营业务代码2——2015
						setcell1.setCellValue(temp2.getTransamount_month());
						xj1 = xj1 + Integer.parseInt(temp2.getTransamount_month());
						setcell2.setCellValue(temp2.getTransamount_year());
						xj2 = xj2 + Integer.parseInt(temp2.getTransamount_year());
						setcell3.setCellValue(temp5.getTransamount_month());
						xj3 = xj3 + Integer.parseInt(temp5.getTransamount_month());
						setcell4.setCellValue(temp5.getTransamount_year());
						xj4 = xj4 + Integer.parseInt(temp5.getTransamount_year());
						setcell5.setCellValue(
								(double)(Integer.parseInt(temp5.getTransamount_year())-Integer.parseInt(temp6.getTransamount_year()))/
								Integer.parseInt(temp6.getTransamount_year()));
						xj5 = xj5 + (double)(Integer.parseInt(temp5.getTransamount_year())-Integer.parseInt(temp6.getTransamount_year()))/
								Integer.parseInt(temp6.getTransamount_year());
						setcell6.setCellValue(temp2.getTransmoney_month());
						xj6 = xj6 + Double.parseDouble(temp2.getTransmoney_month());
						setcell7.setCellValue(temp2.getTransmoney_year());
						xj7 = xj7 + Double.parseDouble(temp2.getTransmoney_year());
						setcell8.setCellValue(temp5.getTransmoney_month());
						xj8 = xj8 + Double.parseDouble(temp5.getTransmoney_month());
						setcell9.setCellValue(temp5.getTransmoney_year());
						xj9 = xj9 + Double.parseDouble(temp5.getTransmoney_year());
						setcell10.setCellValue(((Double.parseDouble(temp5.getTransmoney_year())-Double.parseDouble(temp6.getTransmoney_year()))/
								Double.parseDouble(temp6.getTransmoney_year())));
						xj10 = xj10 + ((Double.parseDouble(temp5.getTransmoney_year())-Double.parseDouble(temp6.getTransmoney_year()))/
								Double.parseDouble(temp6.getTransmoney_year()));
						setcell11.setCellValue(Double.parseDouble(temp5.getTransmoney_year())/Double.parseDouble(temp2.getTransmoney_year()));
						xj11 = xj11 + Double.parseDouble(temp5.getTransmoney_year())/Double.parseDouble(temp2.getTransmoney_year());
						setcell12.setCellValue(
								(double)Double.parseDouble(temp5.getTransmoney_year())
								/Integer.parseInt(temp5.getTransamount_year()));
						xj12 = xj12 + (double)Double.parseDouble(temp5.getTransmoney_year())
						/Integer.parseInt(temp5.getTransamount_year());
						pt++;
					}
					HSSFRow xjrow = sheet.getRow(18);
					HSSFCell setcell1 = xjrow.getCell(2);
					HSSFCell setcell2 = xjrow.getCell(3);
					HSSFCell setcell3 = xjrow.getCell(4);
					HSSFCell setcell4 = xjrow.getCell(5);
					HSSFCell setcell5 = xjrow.getCell(6);
					HSSFCell setcell6 = xjrow.getCell(7);
					HSSFCell setcell7 = xjrow.getCell(8);
					HSSFCell setcell8 = xjrow.getCell(9);
					HSSFCell setcell9 = xjrow.getCell(10);
					HSSFCell setcell10 = xjrow.getCell(11);
					HSSFCell setcell11 = xjrow.getCell(12);
					HSSFCell setcell12 = xjrow.getCell(13);
					setcell1.setCellValue(xj1);
					setcell2.setCellValue(xj2);
					setcell3.setCellValue(xj3);
					setcell4.setCellValue(xj4);
					setcell5.setCellValue(xj5);
					setcell6.setCellValue(xj6);
					setcell7.setCellValue(xj7);
					setcell8.setCellValue(xj8);
					setcell9.setCellValue(xj9);
					setcell10.setCellValue(xj10);
					setcell11.setCellValue(xj11);
					setcell12.setCellValue(xj12);
					int pt1 = 0;
					int dfxj1 = 0;
					int dfxj2 = 0;
					int dfxj3 = 0;
					int dfxj4 = 0;
					double dfxj5 = 0;
					double dfxj6 = 0;
					double dfxj7 = 0;
					double dfxj8 = 0;
					double dfxj9 = 0;
					double dfxj10 = 0;
					double dfxj11 = 0;
					double dfxj12 = 0;
					for(int row = 22;row < 36;row++){
						HSSFRow xjrow1 = sheet.getRow(row);
						HSSFCell scell1 = xjrow1.getCell(2);
						HSSFCell scell2 = xjrow1.getCell(3);
						HSSFCell scell3 = xjrow1.getCell(4);
						HSSFCell scell4 = xjrow1.getCell(5);
						HSSFCell scell5 = xjrow1.getCell(6);
						HSSFCell scell6 = xjrow1.getCell(7);
						HSSFCell scell7 = xjrow1.getCell(8);
						HSSFCell scell8 = xjrow1.getCell(9);
						HSSFCell scell9 = xjrow1.getCell(10);
						HSSFCell scell10 = xjrow1.getCell(11);
						HSSFCell scell11 = xjrow1.getCell(12);
						HSSFCell scell12 = xjrow1.getCell(13);
						temp1 = getpi(pis1,Permutation[pt1]);//全口径业务代码2
						temp3 = getpi(pis3,Permutation[pt1]);//自营业务代码2——2016
						temp4 = getpi(pis4,Permutation[pt1]);//自营业务代码2——2015
						scell1.setCellValue(temp1.getTransamount_month());
						dfxj1 = dfxj1 + Integer.parseInt(temp1.getTransamount_month());
						scell2.setCellValue(temp1.getTransamount_year());
						dfxj2 = dfxj2 + Integer.parseInt(temp1.getTransamount_year());
						scell3.setCellValue(temp3.getTransamount_month());
						dfxj3 = dfxj3 + Integer.parseInt(temp3.getTransamount_month());
						scell4.setCellValue(temp3.getTransamount_year());
						dfxj4 = dfxj4 + Integer.parseInt(temp3.getTransamount_year());
						scell5.setCellValue(
								(double)(Integer.parseInt(temp3.getTransamount_year())-Integer.parseInt(temp4.getTransamount_year()))/
								Integer.parseInt(temp4.getTransamount_year()));
						dfxj5 = dfxj5 + (double)(Integer.parseInt(temp3.getTransamount_year())-Integer.parseInt(temp4.getTransamount_year()))/
								Integer.parseInt(temp4.getTransamount_year());
						scell6.setCellValue(temp1.getTransmoney_month());
						dfxj6 = dfxj6 + Double.parseDouble(temp1.getTransmoney_month());
						scell7.setCellValue(temp1.getTransmoney_year());
						dfxj7 = dfxj7 + Double.parseDouble(temp1.getTransmoney_year());
						scell8.setCellValue(temp3.getTransmoney_month());
						dfxj8 = dfxj8 + Double.parseDouble(temp3.getTransmoney_month());
						scell9.setCellValue(temp3.getTransmoney_year());
						dfxj9 = dfxj9 + Double.parseDouble(temp3.getTransmoney_year());
						scell10.setCellValue(((Double.parseDouble(temp3.getTransmoney_year())-Double.parseDouble(temp4.getTransmoney_year()))/
								Double.parseDouble(temp4.getTransmoney_year())));
						dfxj10 = dfxj10 + ((Double.parseDouble(temp3.getTransmoney_year())-Double.parseDouble(temp4.getTransmoney_year()))/
								Double.parseDouble(temp4.getTransmoney_year()));
						scell11.setCellValue(Double.parseDouble(temp3.getTransmoney_year())/Double.parseDouble(temp1.getTransmoney_year()));
						dfxj11 = dfxj11 + Double.parseDouble(temp3.getTransmoney_year())/Double.parseDouble(temp1.getTransmoney_year());
						scell12.setCellValue(
								(double)Double.parseDouble(temp3.getTransmoney_year())
								/Integer.parseInt(temp3.getTransamount_year()));
						dfxj12 = dfxj12 + (double)Double.parseDouble(temp3.getTransmoney_year())
						/Integer.parseInt(temp3.getTransamount_year());
						pt1++;
					}
					HSSFRow dfxjrow = sheet.getRow(36);
					HSSFCell dfcell1 = dfxjrow.getCell(2);
					HSSFCell dfcell2 = dfxjrow.getCell(3);
					HSSFCell dfcell3 = dfxjrow.getCell(4);
					HSSFCell dfcell4 = dfxjrow.getCell(5);
					HSSFCell dfcell5 = dfxjrow.getCell(6);
					HSSFCell dfcell6 = dfxjrow.getCell(7);
					HSSFCell dfcell7 = dfxjrow.getCell(8);
					HSSFCell dfcell8 = dfxjrow.getCell(9);
					HSSFCell dfcell9 = dfxjrow.getCell(10);
					HSSFCell dfcell10 = dfxjrow.getCell(11);
					HSSFCell dfcell11 = dfxjrow.getCell(12);
					HSSFCell dfcell12 = dfxjrow.getCell(13);
					dfcell1.setCellValue(dfxj1);
					dfcell2.setCellValue(dfxj2);
					dfcell3.setCellValue(dfxj3);
					dfcell4.setCellValue(dfxj4);
					dfcell5.setCellValue(dfxj5);
					dfcell6.setCellValue(dfxj6);
					dfcell7.setCellValue(dfxj7);
					dfcell8.setCellValue(dfxj8);
					dfcell9.setCellValue(dfxj9);
					dfcell10.setCellValue(dfxj10);
					dfcell11.setCellValue(dfxj11);
					dfcell12.setCellValue(dfxj12);
				}
				FileOutputStream out = new FileOutputStream(fullPath);
				hssfWorkbook.write(out);
				hssfWorkbook.close();
				downloadfiles[i] = fullPath;
				out.close();
			}else if(filenames[i].equals("代收付新增委托单位")){
				oldfilename = filenames[i]+CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
				//文件名+后缀
				filename = filenames[i]+querydate + CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
				//路径+文件名+后缀
				fullPath = filePath + backslant +filename;
				oldfullPath = oldfilePath+backslant+oldfilename;
				
				//查询数据
				String tomonth = querydate;
				String beforemonth = String.valueOf(Integer.parseInt(querydate)-1);
				String beforeyear = String.valueOf(Integer.parseInt(querydate)-100);
				
				pbsmr_entrustunit pe = new pbsmr_entrustunit();
				pbsmr_entrustunits pes = new pbsmr_entrustunits();
				pbsmr_entrustunits pes_beforemonth = new pbsmr_entrustunits();
				pbsmr_entrustunits pes_beforeyear = new pbsmr_entrustunits();
				pe.setReportName("代收付业务统计月报--按委托单位");
				pe.setStatisticsdate(tomonth);
				pe.setStatisticstype("0-交易机构");
				pe.setDotproperties("0-全部");
				pes.setList(getdownloadlist.getpbsmr_enList(pe));
				pe.setStatisticsdate(beforemonth);
				pes_beforemonth.setList(getdownloadlist.getpbsmr_enList(pe));
				pe.setStatisticsdate(beforeyear);
				pes_beforeyear.setList(getdownloadlist.getpbsmr_enList(pe));
				
				
				//获取模板文件
				File file = new File(oldfullPath);
				
				//模板文件的固定列
				String[] Permutation = {"哈尔滨","佳木斯","绥化","牡丹江","齐齐哈尔","大庆","鸡西","双鸭山","黑河","鹤岗","伊春",
						"七台河","大兴安岭","直属支行"};
				String[] Permutatio_code = {"2301","2308","2312","2310","2302","2306","2303",
						"2305","2311","2304","2307","2309","2327","2399"};
				
				//整理数据 ，按委托单位前四位进行区分、排序
				String[] cell1_data = new String[Permutation.length];//第一列数据
				if(pes_beforeyear.getList().size() == 0){
					for(int cdi = 0;cdi<cell1_data.length;cdi++){
						cell1_data[cdi] = "-";
					}
				}else{
					for(int cdi1 = 0;cdi1<Permutatio_code.length;cdi1++){
						String merchcode = Permutatio_code[cdi1];
						int counter  = 0;
						for(int cdi = 0;cdi < pes_beforeyear.getList().size();cdi++){
							pbsmr_entrustunit temp = pes_beforeyear.getList().get(cdi);
							if(temp.getMerchid().contains(merchcode)){
								counter++;
							}
						}
						cell1_data[cdi1] = String.valueOf(counter);
					}
				}
				
				String[] cell2_data = new String[Permutation.length];//第2列数据：本月新增的全口径的委托单位，先区分，在相减
				String[] beforemonth_data = new String[Permutation.length];//上月各市委托单位数量
				String[] thismonth_data = new String[Permutation.length];//本月各市委托单位数量
				if(pes_beforemonth.getList().size() == 0 || pes.getList().size() ==0){
					for(int cd2 = 0;cd2 < cell2_data.length;cd2++){
						cell2_data[cd2] = "-";
					}
				}else{
					if(pes_beforemonth.getList().size() == 0){
					}else{
						for(int bdi1 = 0;bdi1<Permutatio_code.length;bdi1++){
							String merchcode = Permutatio_code[bdi1];
							int counter  = 0;
							for(int cdi = 0;cdi < pes_beforemonth.getList().size();cdi++){
								pbsmr_entrustunit temp = pes_beforemonth.getList().get(cdi);
								if(temp.getMerchid().length()>4 && temp.getMerchid().substring(0, 4).equals(merchcode)){
									counter++;
								}
							}
							beforemonth_data[bdi1] = String.valueOf(counter);
						}
					}
					if(pes.getList().size() == 0){
					}else{
						for(int bdi1 = 0;bdi1<Permutatio_code.length;bdi1++){
							String merchcode = Permutatio_code[bdi1];
							int counter  = 0;
							for(int cdi = 0;cdi < pes.getList().size();cdi++){
								pbsmr_entrustunit temp = pes.getList().get(cdi);
								if(temp.getMerchid().length()>4 && temp.getMerchid().substring(0, 4).equals(merchcode)){
									counter++;
								}
							}
							thismonth_data[bdi1] = String.valueOf(counter);
						}
					}
					for(int cd2 = 0;cd2<Permutation.length;cd2++){
						cell2_data[cd2] = String.valueOf(Integer.parseInt(thismonth_data[cd2]) - Integer.parseInt(beforemonth_data[cd2]));
					}
				}
				/*
				 * 第三列数据，本年比上一年增加的委托单位，用本年12月减去上一年12月的数据
				 * */
				String[] cell3_data = new String[Permutatio_code.length];
				String[] beforeyear_data = new String[Permutation.length];//上月各市委托单位数量
				if(pes.getList().size() ==0|| pes_beforeyear.getList().size() == 0){
					for(int cd3 = 0;cd3<cell3_data.length;cd3++){
						cell3_data[cd3] = "-";
					}
				}else{
					for(int bdi1 = 0;bdi1<Permutatio_code.length;bdi1++){
						String merchcode = Permutatio_code[bdi1];
						int counter  = 0;
						for(int cdi = 0;cdi < pes_beforeyear.getList().size();cdi++){
							pbsmr_entrustunit temp = pes_beforeyear.getList().get(cdi);
							if(temp.getMerchid().length()>4 && temp.getMerchid().substring(0, 4).equals(merchcode)){
								counter++;
							}
						}
						beforeyear_data[bdi1] = String.valueOf(counter);
					}
					for(int cd3 = 0;cd3<cell3_data.length;cd3++){
						cell3_data[cd3] = String.valueOf(Integer.parseInt(thismonth_data[cd3])-Integer.parseInt(beforeyear_data[cd3]));
					}
				}
				//第4、5列数据，本年新增的委托单位本年入账笔数和本年入账金额
				//首先得到今年新增了哪些委托单位，之后区分这些委托单位，在区分的时候获取其数据
				
				String[] cell4_data =  new String[Permutatio_code.length];
				String[] cell5_data =  new String[Permutatio_code.length];
				if(pes.getList().size() == 0 || pes_beforeyear.getList().size()==0){
					for(int sz = 0;sz<Permutatio_code.length;sz++){
						cell4_data[sz] = "-";
						cell5_data[sz] = "-";
					}
				}else{
					//获取全部的新增
					List<pbsmr_entrustunit>  diff = compare(pes, pes_beforeyear);
					//区分不同的地区
					for(int sz = 0;sz<Permutatio_code.length;sz++){
						int totalrz = 0;
						double totalmoney = 0;
						String tempcode = Permutatio_code[sz];
						for(int sz1 = 0;sz1<diff.size();sz1++){
							if(diff.get(sz1).getMerchid().substring(0, 4).equals(tempcode)){
								totalrz = totalrz + Integer.parseInt(diff.get(sz1).getTransamount_year());
								totalmoney = totalmoney + Double.parseDouble(diff.get(sz1).getTransmoney_year());
							}
						}
						cell4_data[sz] = String.valueOf(totalrz);
						cell5_data[sz] = String.valueOf(totalmoney);
					}	
				}
				
				
				
				
				
				//开始向模板文件中写入数据
				InputStream is = new FileInputStream(file);
		        //HSSFWorkbook读取该文件
		        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
				HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
				if(sheet == null){
					logger.debug("sheet 是 null");
				}else{
					int index = 0;
					for(int row = 3;row < 17;row++){
						HSSFRow everyrow = sheet.getRow(row);
						HSSFCell cell1 = everyrow.getCell(1);
						HSSFCell cell2 = everyrow.getCell(2);
						HSSFCell cell3 = everyrow.getCell(3);
						HSSFCell cell4 = everyrow.getCell(4);
						HSSFCell cell5 = everyrow.getCell(5);
						HSSFCell cell6 = everyrow.getCell(6);
						HSSFCell cell7 = everyrow.getCell(7);
						HSSFCell cell8 = everyrow.getCell(8);
						HSSFCell cell9 = everyrow.getCell(9);
						HSSFCell cell10 = everyrow.getCell(10);
						HSSFCell cell11 = everyrow.getCell(11);
						HSSFCell cell12 = everyrow.getCell(12);
						cell1.setCellValue(cell1_data[index]);
						cell2.setCellValue(cell2_data[index]);
						cell3.setCellValue(cell3_data[index]);
						cell4.setCellValue(cell4_data[index]);
						cell5.setCellValue(cell5_data[index]);
						index++;
					}
				}
				FileOutputStream out = new FileOutputStream(fullPath);
				hssfWorkbook.write(out);
				hssfWorkbook.close();
				downloadfiles[i] = fullPath;
				out.close();
			}else{
				
			}
		}
		if(filenamenum > 1){
			String zipName = querydate + CommonConstants.ZIP;
			String zipNamePath = filePath + backslant + zipName;
			OutputStream os = new BufferedOutputStream(new FileOutputStream(zipNamePath));
			ZipOutputStream zos = new ZipOutputStream( os );
			byte[] buf = new byte[8192];
	        int len;
	        for (int i = 0; i < filenamenum; i++) {  
	            File file = new File(downloadfiles[i]);
	            if ( !file.isFile() ) continue;
	            ZipEntry ze = new ZipEntry( file.getName() );
	            zos.putNextEntry( ze );
	            BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
	            while ((len = bis.read(buf)) > 0){
	                zos.write( buf, 0, len );
	            }
	            zos.closeEntry();
	        }
	        zos.closeEntry();
	        zos.close();
	        try {
				//获取生成的文件
				File file = new File(zipNamePath);
				HttpHeaders httpheaders = new HttpHeaders();
				String zipNamech=new String(zipName.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
				httpheaders.setContentDispositionFormData("attachment", zipNamech);
				httpheaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//返回到前台
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpheaders,HttpStatus.CREATED);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				//获取生成的文件
				File file = new File(fullPath);
				HttpHeaders httpheaders = new HttpHeaders();
				String filenamech=new String(filename.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
				httpheaders.setContentDispositionFormData("attachment", filenamech);
				httpheaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//返回到前台
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpheaders,HttpStatus.CREATED);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        
		
		return null;
	}
	private boolean ishave(int a,List list){
		boolean bool = false;
		if(list.contains(a)){
			bool = true;
		}else{}
		return bool;
	}
	private double getBfb1(List<pbsmr_busi> list1,List<pbsmr_busi> list2,int xj){
		int transnum2016 = Integer.parseInt(list1.get(xj).getTransamount_year());
		double bfb = 0;
		if(list1.size() > list2.size()){
			for(int pp = 0;pp<list2.size();pp++){
				if(list1.get(xj).getBusicode().equals(list2.get(pp).getBusicode())){
					int transnum2015 = Integer.parseInt(list2.get(pp).getTransamount_year());
					bfb = (transnum2016 - transnum2015) / transnum2015;
					pp = list2.size();
				}
			}
		}
		return bfb;
	}
	private double getBfb2(List<pbsmr_busi> list1,List<pbsmr_busi> list2,int xj){
		double transnum2016 = Double.parseDouble(list1.get(xj).getTransmoney_year());
		double bfb = 0;
		if(list1.size() > list2.size()){
			for(int pp = 0;pp<list2.size();pp++){
				if(list1.get(xj).getBusicode().equals(list2.get(pp).getBusicode())){
					double transnum2015 = Double.parseDouble(list2.get(pp).getTransmoney_year());
					bfb = (transnum2016 - transnum2015) / transnum2015;
					pp = list2.size();
				}
			}
		}
		return bfb;
	}
	private void getxj(List<pbsmr_busi> test1,List<pbsmr_busi> test2,int start,int end,HSSFCell xjcell,HSSFCell xjcell1
			,HSSFCell xjcell2,HSSFCell xjcell3
			,HSSFCell xjcell4,HSSFCell xjcell5){
		int xj1 = 0;
		int xj2 = 0;
		double xj3 = 0;
		double xj4 = 0;
		double xj5 = 0;
		double xj6 = 0;
		for(int xji = start;xji < end;xji++){
			
			xj1 = xj1 + Integer.parseInt(test1.get(xji).getTransamount_month());
			xj2 = xj2 + Integer.parseInt(test1.get(xji).getTransamount_year());
			if(getBfb1(test1,test2,xji) == 0){
			}else{
				xj3 = xj3 + getBfb1(test1,test2,xji);
			}
			xj4 = xj4 + Double.parseDouble(test1.get(xji).getTransmoney_month());
			xj5 = xj5 + Double.parseDouble(test1.get(xji).getTransmoney_year());
			if(getBfb2(test1,test2,xji) == 0){
			}else{
				xj6 = xj6 + getBfb2(test1,test2,xji);
			}
		}
		xjcell.setCellValue(xj1);
		xjcell1.setCellValue(xj2);
		xjcell2.setCellValue(xj3);
		xjcell3.setCellValue(xj4);
		xjcell4.setCellValue(xj5);
		xjcell5.setCellValue(xj6);
	}
	private pbsmr_inst getpi(pbsmr_insts pis,String str){
		pbsmr_inst pi = null;
		for(int i = 0;i<pis.getList().size();i++){
			if(pis.getList().get(i).getInstname().contains(str)){
				pi = pis.getList().get(i);
			}else{
			}
		}
		return pi;
	}
	private static List<pbsmr_entrustunit> compare(pbsmr_entrustunits t,pbsmr_entrustunits b){
		List<pbsmr_entrustunit> temp_obj = new ArrayList<pbsmr_entrustunit>();
		String[] n = new String[]{};
		String[] o = new String[]{};
		for(int a = 0;a<t.getList().size();a++){
			n[a] = t.getList().get(a).getMerchid();
		}
		for(int i = 0;i<b.getList().size();i++){
			o[i] = b.getList().get(i).getMerchid();
		}
		List<String> list1 = Arrays.asList(o);
		List<String> list2 = new ArrayList<String>();
		for(String a:n){
			if(!list1.contains(a)){
				list2.add(a);
			}
		}
		for(int dxh = 0;dxh<list2.size();dxh++){
			String tempstr = list2.get(dxh);
			for(int x = 0;x<t.getList().size();x++){
				if(tempstr.equals(t.getList().get(x).getMerchid())){
					temp_obj.add(t.getList().get(x));
				}
				
			}
		}
		return temp_obj;
	}
}