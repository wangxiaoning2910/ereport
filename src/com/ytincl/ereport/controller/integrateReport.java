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
		
		logger.debug("��������===="+tableName);
		String tn = null;
		if(tableName.equals("���ո�ҵ��ͳ���±�--������")){
			tn = "pbsmr_inst";
		}else if(tableName.equals("���ո�ҵ��ͳ���±�--��ҵ��")){
			tn = "pbsmr_busi";
		}else if(tableName.equals("���ո�ҵ��ͳ���±�--��ί�е�λ")){
			tn = "pbsmr_entrustunit";
		}else{
			tn = "null";
		}
		logger.debug("��������===="+tn);
		return irservice.getTableField(tn);
	}
}