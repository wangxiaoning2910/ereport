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

import com.ytincl.ereport.dao.FindFileDao;

import com.ytincl.ereport.dao.impl.FindFileDaoImpl;

import com.ytincl.ereport.model.FindFileAsParam;
import com.ytincl.ereport.pojo.DepCity;
import com.ytincl.ereport.pojo.DepCounty;
import com.ytincl.ereport.pojo.DepType;
import com.ytincl.ereport.pojo.FindFileSet;
import com.ytincl.ereport.service.FindFileService;


@RestController
@Controller  
public class FindFileController {  
	@Autowired   
	FindFileService service;
	private static Logger logger = LoggerFactory.getLogger(FindFileController.class);
    @RequestMapping(value="/view/getDepType.do")
   // @ResponseBody
    
    public ModelAndView getDepType(HttpServletRequest request,HttpServletResponse response){
    	FindFileAsParam uiap = new FindFileAsParam();
		String date = request.getParameter("date");
        List<DepType> result = service.getDepTypeService(date);
		FindFileSet rset = new FindFileSet();
        rset.setDepTypeResult(result);
        ModelAndView page = new ModelAndView("../view/depType");
        page.addObject("rset", rset);
        page.addObject("date", date);
        

        return page;
    }

    @RequestMapping(value="/view/getDepCity.do")
   // @ResponseBody
    public ModelAndView getDepCity(HttpServletRequest request,HttpServletResponse response){
    	FindFileAsParam uiap = new FindFileAsParam();
		String date = request.getParameter("date");
        List<DepCity> result = service.getDepCityService(date);
		FindFileSet rset = new FindFileSet();
        rset.setDepCityResult(result);
        ModelAndView page = new ModelAndView("../view/depCity");
        page.addObject("rset", rset);
        page.addObject("date", date);
        
        return page;
    }

    @RequestMapping(value="/view/getDepCounty.do")
   // @ResponseBody
    public ModelAndView getDepCounty(HttpServletRequest request,HttpServletResponse response){
    	FindFileAsParam uiap = new FindFileAsParam();
		String date = request.getParameter("date");
        List<DepCounty> result = service.getDepCountyService(date);
		FindFileSet rset = new FindFileSet();
        rset.setDepCountyResult(result);
        ModelAndView page = new ModelAndView("../view/depCounty");
        page.addObject("rset", rset);
        page.addObject("date", date);
        
        return page;
    }

    
}  
