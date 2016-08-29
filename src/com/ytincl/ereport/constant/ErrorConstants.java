package com.ytincl.ereport.constant;


public class ErrorConstants{

	/**
	 * 系统范围异常(开发人员 ERR_000001~ERR_099999)
	 */
	public static final String ERR_000001 = "000001";//运行时异常
	public static final String ERR_000002 = "000002";//空指针异常
	public static final String ERR_000003 = "000003";//类型转换异常
	public static final String ERR_000004 = "000004";//I/O异常
	public static final String ERR_000005 = "000005";//未知方法异常
	public static final String ERR_000006 = "000006";//数组越界异常
	public static final String ERR_000007 = "000007";//400错误
	public static final String ERR_000009 = "000009";//405错误
	public static final String ERR_000010 = "000010";//406错误
	public static final String ERR_000011 = "000011";//500错误
	public static final String ERR_000012 = "000012";//数据库连接失败
	
	/**
	 * 缓存异常定义
	 */
	
	public static final String ERR_800005 = "800005";//缓存读取失败
	/**
	 * 业务范围异常(业务人员 ERR_100001~ERR_199999)
	 */
	public static final String BUS_100000 = "100000";//交易成功
	public static final String BUS_100001 = "100001";//记录不存在
	public static final String BUS_100002 = "100002";//获取数据失败
	public static final String BUS_100003 = "100003";//登录失败，用户不存在
	public static final String BUS = "100004";//登录失败，密码不正确
	
	

	/** 私有构造 **/
	private ErrorConstants() {
	}
}
