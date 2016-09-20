package com.ytincl.ereport.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	SystemBusiness systembusiness = new SystemBusiness();
	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	@Autowired
	private SystemBusinessService systemBusinessService;
	@Autowired
	private BusinessTestService businessTestService;

	@RequestMapping(value = "/view/systemBuseniss.do")
	@ResponseBody
	public SystemBusinessReq queryAllSysBusiness(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("==========查询业务系统============");
		ArrayList<SystemBusiness> list = systemBusinessService.querySystemBusiness();
		logger.debug("==========查询业务系统完成============");
		SystemBusinessReq sbr = new SystemBusinessReq();
		sbr.setList(list);
		logger.debug("==========查询业务系统名称============");
		ArrayList<BusinessTest> list1 = businessTestService.queryBusinessTest();
		logger.debug("==========查询业务系统名称完成============");
		sbr.setList1(list1);
		return sbr;
	}

	@RequestMapping(value = "/view/systemBusenissAdd.do")
	public SystemBusiness insertSysBusiness(HttpServletRequest request, HttpServletResponse response) {
		String systemBusinessName = request.getParameter("systemBusinessAdd");
		String userName = request.getParameter("userNameAdd");
		String password = request.getParameter("password");

		// ==========新增，
		try {
			// 解码
			systemBusinessName = java.net.URLDecoder.decode(systemBusinessName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		systembusiness.setSystemBusinessName(systemBusinessName);
		systembusiness.setUserName(userName);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String openDate = sdf.format(date);
		systembusiness.setOpenDate(openDate);
		systembusiness.setModifyDate(openDate);
		// TODO 增加到几个表
		logger.debug("==========增加业务系统============");
		systemBusinessService.insertSystemBusiness(systembusiness);
		logger.debug("==========增加业务系统完成============");
		return systembusiness;
	}

	@RequestMapping(value = "/view/systemBusenissUpdate.do")
	public int updateSystemBusiness(HttpServletRequest request) {
		String systemBusinessName = request.getParameter("systemBusinessUpdate");
		try {
			// 解码
			systemBusinessName = java.net.URLDecoder.decode(systemBusinessName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		systembusiness.setSystemBusinessName(systemBusinessName);
		systembusiness.setUserName(request.getParameter("userName"));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifyDate = sdf.format(date);
		systembusiness.setModifyDate(modifyDate);
		logger.debug("==========更新业务系统============");
		int s = systemBusinessService.updateSystemBusiness(systembusiness);
		logger.debug("==========更新业务系统完成============");
		// return "forward:/view/systemBuseniss.do";
		return s;
	}

	@RequestMapping(value = "/view/systemBusenissDelete.do")
	public int deleteSystemBusiness(HttpServletRequest request) {
		String userNames = request.getParameter("userNames");
		System.out.println(userNames);
		if (userNames.indexOf(",") != -1) {
			String[] userNameArr = userNames.split(",");
			logger.debug("==========删除业务系统============");
			for (int i = 0; i < userNameArr.length; i++) {
				systemBusinessService.deleteSystemBusiness(userNameArr[i]);
			}
			logger.debug("==========删除业务系统完成============");
			return userNameArr.length;
		} else {
			logger.debug("==========删除业务系统============");
			systemBusinessService.deleteSystemBusiness(userNames);
			logger.debug("==========删除业务系统完成============");
			return 1;
		}
		
	}
}
