package com.ytincl.ereport.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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

import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.model.MonthTableModel;
import com.ytincl.ereport.pojo.MonthTable;
import com.ytincl.ereport.pojo.testdownloaddata;
import com.ytincl.ereport.service.MonthTableService;
import com.ytincl.ereport.util.ExportExcel;

/**
 * @author yit 查询报表
 *
 */
@Controller
public class QueryTableController { 
	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	@Autowired
	private MonthTableService monthTableService;
	
	@RequestMapping(value = "/view/queryMonthTables.do")
	@ResponseBody
	public MonthTableModel queryMonthTables(HttpServletRequest request, HttpServletResponse response) {
		
//		for(int i=0;i<monthTables.size();i++){
//			System.out.println(monthTables.get(i).getRanking());
//		}
		String monthTableName1 = request.getParameter("monthTableName1");
		String monthTableName2 = request.getParameter("monthTableName2");
		String monthTableName3 = request.getParameter("monthTableName3");
		String monthTableName4 = request.getParameter("monthTableName4");
		ArrayList<MonthTable> monthTables1 = monthTableService.queryMonthTable(monthTableName1);
		ArrayList<MonthTable> monthTables2 = monthTableService.queryMonthTable(monthTableName2);
		ArrayList<MonthTable> monthTables3 = monthTableService.queryMonthTable(monthTableName3);
		ArrayList<MonthTable> monthTables4 = monthTableService.queryMonthTable(monthTableName4);
		MonthTableModel mm = new MonthTableModel();
		mm.setList1(monthTables1);
		mm.setList2(monthTables2);
		mm.setList3(monthTables3);
		mm.setList4(monthTables4);
		return mm;
	}
	@RequestMapping(value = "/view/exportFileMonth.do")
	public ResponseEntity<byte[]> querydatalist(
//			@RequestParam(value="ymounth", required=true) String querydate,
			@RequestParam(value="MonthFilename", required=true) String fn,
    		HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1，查询数据库中的数据，
		//2，创建Excel文件，将数据写入
		//3，将文件送到前台下载
		
		//文件夹路径
		String filePath = request.getSession().getServletContext().getRealPath(CommonConstants.UPLOADFILEPATH);
		//反斜杠
		String backslant = CommonConstants.BACKSLANT;
		//文件名称+后缀
		String filename = fn+CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
		//路径+文件名+后缀
		String fullPath = filePath + backslant +filename;
		//表头
		String[] headers = {"ceshi1","ceshi2","ceshi3","ceshi4","ceshi5","ceshi6","ceshi7","ceshi8"};
		//查询表头
		
		//查询数据
		ArrayList<MonthTable> monthTables1 = monthTableService.queryMonthTable(fn);
		
		ExportExcel<MonthTable> ex = new ExportExcel<MonthTable>();
		try {
			//定义Outputstream
			OutputStream out = new FileOutputStream(fullPath);
			//生成Excel文件
			ex.exportExcel(headers, monthTables1, out);
			out.close();
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
		return null;
	}
}
