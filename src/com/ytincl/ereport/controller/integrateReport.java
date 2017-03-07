package com.ytincl.ereport.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ytincl.ereport.service.integrateReportService;

@Controller
public class integrateReport {
	@Autowired
	private integrateReportService irservice;
	private static Logger logger = LoggerFactory.getLogger(integrateReport.class);
	
	
	@RequestMapping(value="/view/getFieldName.do")
	@ResponseBody
	public List<String> getTableField(
			@RequestParam(value="tablename", required=true) String tableName){
		
		logger.debug("报表名称===="+tableName);
		String tn = null;
		if(tableName.equals("代收付业务统计月报--按机构")){
			tn = "pbsmr_inst";
		}else if(tableName.equals("代收付业务统计月报--按业务")){
			tn = "pbsmr_busi";
		}else if(tableName.equals("代收付业务统计月报--按委托单位")){
			tn = "pbsmr_entrustunit";
		}else{
			tn = "null";
		}
		logger.debug("报表名称===="+tn);
		return irservice.getTableField(tn);
	}
}