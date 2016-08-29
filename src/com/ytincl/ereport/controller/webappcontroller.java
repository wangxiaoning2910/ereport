package com.ytincl.ereport.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.constant.ErrorConstants;
import com.ytincl.ereport.model.UserInfoAsParam;
import com.ytincl.ereport.model.Menu;
import com.ytincl.ereport.model.UserInfo;
import com.ytincl.ereport.service.UserInfoService;



@RestController
@Controller
public class webappcontroller {
	@Autowired
	private UserInfoService userinfoservice;
	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	
	
	@RequestMapping(value="/verifyUser.do")
	@ResponseBody
	public UserInfoAsParam verifyUser(HttpServletRequest request,HttpServletResponse response){
		UserInfoAsParam uiap = new UserInfoAsParam();
		String username = request.getParameter("inputUsername");
		String password = request.getParameter("inputPassword");
		List<UserInfo> userList = null;
		UserInfo user = null;
		userList = userinfoservice.getUserByName(username);
		if(!userList.isEmpty()){
			user = userList.get(0);
			String pass = user.getPassword();
			if(password.equals(pass)){
				//密码正确
				uiap.setRspCode(ErrorConstants.BUS_100000);
				HttpSession session = request.getSession();
				session.setAttribute(CommonConstants.SESSION_USER, user);
			}else{
				//密码不正确
				uiap.setRspCode(ErrorConstants.BUS_100000);
			}
		}else{
			//没有查询到结果，用户不存在
			uiap.setRspCode(ErrorConstants.BUS_100003);
		}
		
		return uiap;
	}
	
	@RequestMapping(value="/signin.do")
	public ModelAndView signin(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = new UserInfo();
		map.put("test", user);
		return new ModelAndView("index",map);
	}
	
	
	
	@RequestMapping(value="/view/getMenu.do")
	@ResponseBody
	public Object getMenu(HttpServletRequest request,HttpServletResponse response){
		List<Menu> list = userinfoservice.getMenu();
		return list;
	}
}
