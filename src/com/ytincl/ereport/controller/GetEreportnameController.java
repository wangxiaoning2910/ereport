package com.ytincl.ereport.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ytincl.ereport.pojo.GetEreport;
import com.ytincl.ereport.pojo.GetEreportSet;
import com.ytincl.ereport.service.GetEreportService;


@RestController
@Controller  
public class GetEreportnameController {  
	@Autowired   
	GetEreportService service;
    @RequestMapping(value="/view/getereport.do")
   // @ResponseBody
    
    public ModelAndView ereportname(HttpServletRequest request,HttpServletResponse response){
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
