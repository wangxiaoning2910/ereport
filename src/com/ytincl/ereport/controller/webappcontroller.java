package com.ytincl.ereport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.model.Menu;
import com.ytincl.ereport.model.UserInfo;
import com.ytincl.ereport.service.UserInfoService;
import com.ytincl.ereport.util.BaseApplicationException;
import com.ytincl.ereport.util.JSONUtils;

@Controller
public class webappcontroller {
	@Autowired
	private UserInfoService userinfoservice;
	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	@RequestMapping(value="/loginsystem")
	public String login(HttpServletRequest request,HttpServletResponse response) throws BaseApplicationException{
		Map<String,Object> map = new HashMap<String,Object>();
		String username = request.getParameter("inputUsername");
		String password = request.getParameter("inputPassword");
		UserInfo user = null;
		try {
			user = userinfoservice.getUserByName(username);
			if(null == user){
				logger.debug("≤È—Ø≥ˆ¥Ì£¨≤‚ ‘git Œ““≤≤‚ ‘Egit≥ÂÕª");
				map.put("resrultcode", "0002");
			}else{
				String password1 = user.getPassword();
				if(!password.equals(password1)){
					map.put("resrultcode", "0001");
				}else{
					map.put("resrultcode", "0000");
					request.getSession().setAttribute(CommonConstants.SESSION_USER, user);
				}
			}
			JSONUtils.obj2Writer(map, response,CommonConstants.CONTENT_TYPE_JSON_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseApplicationException("00001");
		}
		
		
		return null;
	}
	
	
	@RequestMapping(value="/view/getMenu")
	public String getMenu(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		logger.debug("≤È—Ø≤Àµ•");
		List<Menu> list  = userinfoservice.getMenu();
		map.put("result", list);
		JSONUtils.obj2Writer(map, response,CommonConstants.CONTENT_TYPE_JSON_UTF8);
		return null;
		
	}
}
