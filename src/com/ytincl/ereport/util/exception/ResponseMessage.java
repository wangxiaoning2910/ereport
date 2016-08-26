package com.ytincl.ereport.util.exception;
 
import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {
	public final static Map<String, String> map = new HashMap<String, String>();  
	static {  
	    map.put("000001", "系统运行异常");  
	    map.put("000002", "空指针异常");  
	    map.put("000003", "类型转换异常");  
	    map.put("000004", "I/O异常");  
	    map.put("000005", "未知方法异常");  
	    map.put("000006", "数组越界异常");  
	    map.put("000007", "400错误");  
	    map.put("000009", "405错误");  
	    map.put("000010", "406错误");  
	    map.put("000011", "500错误");  
	    map.put("100000", "交易成功"); 
	    map.put("100001", "记录不存在");  
	    map.put("100002", "获取数据失败");  
	    map.put("100003", "登录失败,该用户不存在");  
	    map.put("100004", "登录失败,密码错误");  
	    map.put("000012", "数据库连接失败");  
	} 
	public static String getResponseMessage(String rspCode){
		return map.get(String.valueOf(rspCode));
	}
}


