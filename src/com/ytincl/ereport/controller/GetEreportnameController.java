package com.ytincl.ereport.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ytincl.ereport.dao.GetEreportDao;

import com.ytincl.ereport.dao.impl.GetEreportDaoImpl;

import com.ytincl.ereport.model.GetEreportAsParam;
import com.ytincl.ereport.pojo.GetEreport;
import com.ytincl.ereport.pojo.GetEreportSet;
import com.ytincl.ereport.service.GetEreportService;


@RestController
@Controller  
public class GetEreportnameController {  
	@Autowired   
	GetEreportService service;
	private static Logger logger = LoggerFactory.getLogger(GetEreportnameController.class);
    @RequestMapping(value="/view/getereport.do")
   // @ResponseBody
    
    public ModelAndView ereportname(HttpServletRequest request,HttpServletResponse response){
    	GetEreportAsParam uiap = new GetEreportAsParam();
		String date = request.getParameter("date");

        List<GetEreport> result = service.getGetEreportname(date);
        GetEreportSet rset = new GetEreportSet();
        rset.setResult(result);           //报表名称个数
        ModelAndView page = new ModelAndView("getereport");
        page.addObject("rset", rset);
        page.addObject("date", date);
        return page;
    }
	
}  
