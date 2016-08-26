package com.ytincl.ereport.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.constant.ErrorConstants;
import com.ytincl.ereport.model.Menu;
import com.ytincl.ereport.model.MenuList;
import com.ytincl.ereport.model.UserInfo;
import com.ytincl.ereport.service.UserInfoService;
import com.ytincl.ereport.util.exception.ReturnFormat;




@RestController
@Controller
public class webappcontroller{
	@Autowired
	private UserInfoService userinfoservice;
	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	@RequestMapping(value="/loginsystem")
	public String login(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		UserInfo queryuser = (UserInfo)session.getAttribute(CommonConstants.SESSION_USER);
		String username = CommonConstants.NULL_STRING;
		String password = CommonConstants.NULL_STRING;
		if(queryuser != null){
			username = (String) session.getAttribute("username");
			password = (String) session.getAttribute("password");
		}else{
			username = request.getParameter("inputUsername");
			password = request.getParameter("inputPassword");
		}
		List<UserInfo> userlist = userinfoservice.getUserByName(username);
		if((!userlist.isEmpty()) && (userlist.size() == 1)){
			UserInfo user = userlist.get(0);
			String password1 = user.getPassword();
			if(password1.equals(password)){
				session.setAttribute(CommonConstants.SESSION_USER, user);
				session.setAttribute("username", username);
				session.setAttribute("password", password1);
				return ReturnFormat.retParam(ErrorConstants.ERR_100000);
			}else{
				return ReturnFormat.retParam(ErrorConstants.ERR_100004);
			}
			
		}else{
			return ReturnFormat.retParam(ErrorConstants.ERR_100003);
		}
			
		
	}
	@RequestMapping(value="/dumptomainpage")
	public ModelAndView dumptomainpage(){
		return new ModelAndView("index");
	}
	@RequestMapping(value="/view/getMenu")
	public String getMenu(HttpServletRequest request,HttpServletResponse response){
		List<Menu> list  = userinfoservice.getMenu();
		MenuList menulist = new MenuList();
		menulist.setMenuList(list);
		return ReturnFormat.retParam(ErrorConstants.ERR_100000,list);
		
	}
}
