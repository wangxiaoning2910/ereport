package com.ytincl.ereport.util;

import com.ytincl.ereport.constant.CommonConstants;

public class FormatConversion {
	public static Double string2Double(String str){
		//return Double.parseDouble(str);
		if(str.equals("*")){
			return 0.0;
		}
		if(null == str || CommonConstants.NULL_STRING.equals(str)){
			return  null;
		}
		return Double.valueOf(str.toString());
	}
	public static Float string2Float(String str){
		if(str.equals("*")){
			return (float) 0.0;
		}
		if(null == str || CommonConstants.NULL_STRING.equals(str)){
			return  null;
		}
		return Float.parseFloat(str);
	}
	public static int string2Int(String str){
		if(str.equals("*")){
			return 0;
		}
		if(null == str || CommonConstants.NULL_STRING.equals(str)){
			return (Integer) null;
		}
		return Integer.parseInt(str);
	}
}
