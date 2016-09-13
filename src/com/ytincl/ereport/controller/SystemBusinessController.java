package com.ytincl.ereport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ytincl.ereport.model.SystemBusinessReq;
import com.ytincl.ereport.pojo.BusinessTest;
import com.ytincl.ereport.pojo.SystemBusiness;
import com.ytincl.ereport.service.BusinessTestService;
import com.ytincl.ereport.service.SystemBusinessService;

/**
 * @author yit 业务系统设置
 */
@Controller
public class SystemBusinessController {
	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	@Resource
	private SystemBusinessService systemBusinessService;
	@Resource
	private BusinessTestService businessTestService;

	@RequestMapping(value = "/view/systemBuseniss.do")
	public String queryAllSysBusiness(HttpServletRequest request, HttpServletResponse response) {
		List<SystemBusiness> list = systemBusinessService.querySystemBusiness();
		request.setAttribute("list", list);
		
		List<BusinessTest> bt = businessTestService.queryBusinessTest();
		request.setAttribute("list1", bt);
		return "sysBusiness";
	}

	@RequestMapping(value = "/view/systemBusenissAdd.do")
	public String insertSysBusiness(HttpServletRequest request, HttpServletResponse response,
			SystemBusinessReq sysBusinessReq) {
//		sysBusinessReq.setSystemBusinessName(request.getParameter("systemBusinessName"));
//		sysBusinessReq.setUserName(request.getParameter("userName"));
//		sysBusinessReq.setPassword(request.getParameter("password"));
		
		String systemBusinessName= request.getParameter("systemBusinessAdd");
		String userName= request.getParameter("userName");
		String password= request.getParameter("password");
		
		SystemBusiness systembusiness = new SystemBusiness();
		
		//==========新增，
		try {
			//解码
			systemBusinessName = java.net.URLDecoder.decode(systemBusinessName , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		systembusiness.setSystemBusinessName(systemBusinessName);
		systembusiness.setUserName(userName);
		//TODO 增加到几个表
		logger.debug("==========增加业务系统============");
		systemBusinessService.insertSystemBusiness(systembusiness);
		logger.debug("==========增加业务系统完成============");
		return "forward:/view/systemBuseniss.do";
	}
	@RequestMapping(value = "/view/systemBusenissUpdate.do")
	public String updateSystemBusiness(HttpServletRequest request,SystemBusiness systemBusiness){
		String systemBusinessName= request.getParameter("systemBusinessUpdate");
		try {
			//解码
			systemBusinessName = java.net.URLDecoder.decode(systemBusinessName , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		systemBusiness.setSystemBusinessName(systemBusinessName);
		systemBusiness.setUserName(request.getParameter("userName"));
		int s = systemBusinessService.updateSystemBusiness(systemBusiness);
		return "forward:/view/systemBuseniss.do";
	}
	@RequestMapping(value = "/view/systemBusenissDelete.do")
	public String deleteSystemBusiness(String userName){
		
		systemBusinessService.deleteSystemBusiness(userName);
		return "forward:/view/systemBuseniss.do";
	}
}
