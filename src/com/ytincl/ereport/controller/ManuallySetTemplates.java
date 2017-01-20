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
import com.ytincl.ereport.model.ManuallySetTempLateList;
import com.ytincl.ereport.pojo.ManuallyTemplate;
import com.ytincl.ereport.service.ToBeDownLoadListService;

@Controller
public class ManuallySetTemplates {
	@Autowired
	private ToBeDownLoadListService getdownloadlist;
	private static Logger logger = LoggerFactory.getLogger(ManuallySetTemplates.class);
	
	
	
	@RequestMapping(value="/view/queryTemplates.do")
	@ResponseBody
    public ManuallySetTempLateList queryList(HttpServletRequest request,HttpServletResponse response){  
		ArrayList<ManuallyTemplate> list = getdownloadlist.getTempList();
		ManuallySetTempLateList mstll = new ManuallySetTempLateList();
		mstll.setList(list);
		return mstll;
    } 
	
}