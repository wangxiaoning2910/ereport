package com.ytincl.ereport.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class Common {
	//后台访问
	public static final String BACKGROUND_PATH = "WEB-INF/jsp/background";
	//前台访问
	public static final String WEB_PATH = "/WEB-INF/jsp/web";
	//默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    
	/**
	 * String转换double
	 * @param string
	 * @return double
	 */
	public static double convertSourData(String dataStr) throws Exception{
		if(dataStr!=null&&!"".equals(dataStr)){
			return Double.valueOf(dataStr);
		}
		throw new NumberFormatException("convert error!");
	}
	
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 返回当前时间�?格式：yyyy-MM-dd hh:mm:ss
	 * @return String
	 */
	public static String fromDateH(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format1.format(new Date());
	}
	/**
	 * 返回当前时间�?格式：yyyy-MM-dd
	 * @return String
	 */
	public static String fromDateY(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}
	/**
	 * 用来去掉List中空值和相同项的�?
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}
	
	/**
	 * 获取登录账号的ID
	 * @author lanyuan
	 * Email：mmm333zzz520@163.com
	 * date�?2014-2-27
	 * @param request
	 * @return
	 */
	public static String findUserSessionId(HttpServletRequest request) { 
		return request.getSession().getAttribute("userSessionId").toString();
	 }
	
	/**
	 * 获取登录账号的的对象
	 * @author lanyuan
	 * Email：mmm333zzz520@163.com
	 * date�?2014-2-27
	 * @param request
	 * @return Object 返回是Object..�?要转型为(Account)Object
	 */
	public static Object findUserSession(HttpServletRequest request) { 
	    return (Object)request.getSession().getAttribute("userSession");
	 }
	
  
}
