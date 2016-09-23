package com.ytincl.ereport.controller;

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
	public MonthTableModel queryMonthTables(HttpServletRequest request, HttpServletResponse response){
		ArrayList<MonthTable> monthTables = monthTableService.queryMonthTable();
//		for(int i=0;i<monthTables.size();i++){
//			System.out.println(monthTables.get(i).getRanking());
//		}
		MonthTableModel mm = new MonthTableModel();
		mm.setList(monthTables);
		return mm;
	}
}
