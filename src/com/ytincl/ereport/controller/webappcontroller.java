package com.ytincl.ereport.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.constant.ErrorConstants;
import com.ytincl.ereport.model.BaseModel;
import com.ytincl.ereport.model.UserInfoAsParam;
import com.ytincl.ereport.pojo.Account;
import com.ytincl.ereport.pojo.Menu;
import com.ytincl.ereport.pojo.Resources;
import com.ytincl.ereport.pojo.UserInfo;
import com.ytincl.ereport.pojo.waterMarkFileName;
import com.ytincl.ereport.service.AccountService;
import com.ytincl.ereport.service.ResourcesService;
import com.ytincl.ereport.service.UserInfoService;
import com.ytincl.ereport.util.waterMark;



@RestController
@Controller
public class webappcontroller {
	@Autowired
	private UserInfoService userinfoservice;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ResourcesService resourcesService;
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	
	
	@RequestMapping(value="/verifyUser.do")
	@ResponseBody
	public UserInfoAsParam verifyUser(HttpServletRequest request,HttpServletResponse response){
		UserInfoAsParam uiap = new UserInfoAsParam();
		String username = request.getParameter("inputUsername");
		String password = request.getParameter("inputPassword");
//		List<UserInfo> userList = null;
//		UserInfo user = null;
//		userList = userinfoservice.getUserByName(username);
		//TODO 改成从account表中查找已注册的账号
		Account account = accountService.getAccountByName(username);
		if(account!=null){
			String pass = account.getPassword();
			if(password.equals(pass)){
				//密码正确
				Authentication authentication = myAuthenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(username,account.getPassword()));
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);
				System.out.println("======================="+authentication.getPrincipal().toString());
				uiap.setRspCode(ErrorConstants.BUS_100000);
				HttpSession session = request.getSession();
				session.setAttribute(CommonConstants.SESSION_USER, account);
			}else{
				//密码不正确
				uiap.setRspCode(ErrorConstants.BUS_100004);
			}
		}else{
			//没有查询到结果，用户不存在
			uiap.setRspCode(ErrorConstants.BUS_100003);
		}
		
		return uiap;
	}
	
	@RequestMapping(value="/ereport.do")
	public ModelAndView signin(HttpServletRequest request,HttpServletResponse response){
		ModelAndView ereportPage = new ModelAndView("index");
		HttpSession session = request.getSession(false);
		if(session != null){
			return ereportPage;
		}
		return new ModelAndView("redirect:/login.jsp");
	}
	
	
	
	@RequestMapping(value="/getMenu.do")
	@ResponseBody
	public Object getMenu(HttpServletRequest request,HttpServletResponse response){
//		List<Menu> list = userinfoservice.getMenu();
//		List<Resources> list = resourcesService.getAllResources();
		Account account= (Account) request.getSession().getAttribute(CommonConstants.SESSION_USER);
		List<Resources> menus =null;
		if("root".equals(account.getAccountName())){
			menus = resourcesService.getAllResources();
		}else{
			menus = resourcesService.findAccountResourcess(String.valueOf(account.getId()));
		}
		return menus;
	}
	
	@RequestMapping(value="/view/queryWaterMarkFileName.do")
	@ResponseBody
	public waterMarkFileName getFilename(
			@RequestParam(value="institution", required=true) String institution,
			@RequestParam(value="operator", required=true) String operator,
			HttpServletRequest request,
			HttpServletResponse response){
		String fn = waterMark.getWaterMark(institution,operator);
		waterMarkFileName wmfn = new waterMarkFileName();
		wmfn.setFilename(fn);
		return wmfn;
	}
}
