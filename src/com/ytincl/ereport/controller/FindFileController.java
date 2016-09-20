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
import com.ytincl.ereport.pojo.FindFile;
import com.ytincl.ereport.pojo.FindFileSet;
import com.ytincl.ereport.service.FindFileService;


@RestController
@Controller  
public class FindFileController {  
	@Autowired   
	FindFileService service;
	private static Logger logger = LoggerFactory.getLogger(FindFileController.class);
    @RequestMapping(value="/view/ereportname.do")
   // @ResponseBody
    
    public ModelAndView ereportname(HttpServletRequest request,HttpServletResponse response){
    	FindFileAsParam uiap = new FindFileAsParam();
		String date = request.getParameter("d11");
		String name = request.getParameter("d22");
		FindFile obj  = new FindFile();
		obj.setDate(date);
		obj.setName(name);
        List<FindFile> result[] = service.geFindFilename(obj);
        FindFileSet rset[] = new FindFileSet[2];
        ModelAndView page = new ModelAndView("../view/ereportname");
        rset[0] = new FindFileSet();
        rset[0].setResult(result[0]);           //全口径
        page.addObject("objs_qkj", rset[0]);

        rset[1] = new FindFileSet();
        rset[1].setResult(result[1]);           //银行自营
        page.addObject("objs_zy", rset[1]);
        return page;
    }
	
}  
