package com.ytincl.ereport.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ytincl.ereport.constant.CommonConstants;

public class EntryValidate implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(EntryValidate.class);
	private  String loginpage = "/login.html";
	private  String loginurl = "/loginsystem";
	private  String[] args = {
			".css",
			".otf",
			".js",
			".gif",
			".bmp",
			".jpg",
			".png",
			".eot",
			".svg",
			".woff2",
			".woff",
			".ttf"};
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//控制用户访问权限
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse res = (HttpServletResponse)arg1;
		String contextPath = req.getContextPath();
		String requestPath = null;
		if((contextPath != null) && (contextPath.length() > 0)){
			requestPath = req.getRequestURI().substring(contextPath.length());
		}else{
			requestPath = req.getRequestURI();
		}
		if(!noFilter(requestPath)){
			HttpSession session = req.getSession();
			LOGGER.debug("==========请求被拦截=========");
			if(session != null){
				LOGGER.debug("session 不为空 ");
				Object userObj = session.getAttribute(CommonConstants.SESSION_USER);
				if(userObj == null){
					LOGGER.debug("用户为空 ，跳转到登录页面");
	    			sendRedirect(req, res, this.loginpage);
	    			return;
	    		}
			}else{
				LOGGER.debug("session 为空 ，跳转到登录页面");
	    		sendRedirect(req, res, this.loginpage);
	    		return;
	    	}
		}
		//判断是否登陆状态
	    if(requestPath.equals(this.loginpage) || requestPath.equals("/")){
	    	LOGGER.debug("==========请求不被拦截=========");
	    	HttpSession session = req.getSession(false);
	    	if(session != null){
	    		LOGGER.debug("==========SESSION不为空=========");
	    		Object userObj = session.getAttribute(CommonConstants.SESSION_USER);
	    		if(userObj != null){
	    			LOGGER.debug("==========用户不为空，跳转到登录后的几面=========");
	    			sendRedirect(req, res, this.loginurl);
    				return;
	    		}
	    	}
	    }
	    arg2.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private boolean noFilter(String uri){
		if(this.loginpage.equals(uri))return true;
		if(this.loginurl.equals(uri))return true;
		if("/".equals(uri))return true;
		for(int i = 0;i < args.length;i++){
			if(uri.contains(args[i])){
				return true;
			}
		}
		return false;
	}
	private void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url){
		try{
			if (url.indexOf("://") > 0){
				response.sendRedirect(url);
			} else {
				String contextpath = request.getContextPath();
				if ((contextpath != null) && (contextpath.length() > 0)) {
					LOGGER.debug("Redirect:" + contextpath + url);
					response.sendRedirect(contextpath + url);
				} else {
					response.sendRedirect(url);
					LOGGER.debug("Redirect:" + url);
				}
			}
	   }catch (Exception e) {
	      LOGGER.error("sendRedirect Error:" + e.getMessage(),e);
	   }
	}
}
