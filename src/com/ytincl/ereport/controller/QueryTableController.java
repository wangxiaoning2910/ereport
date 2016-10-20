package com.ytincl.ereport.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ytincl.ereport.model.MonthTableModel;
import com.ytincl.ereport.pojo.MonthTable;
import com.ytincl.ereport.service.MonthTableService;

/**
 * @author yit ≤È—Ø±®±Ì
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
}
