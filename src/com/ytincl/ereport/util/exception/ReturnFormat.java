package com.ytincl.ereport.util.exception;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ytincl.ereport.model.UserInfo;
import com.ytincl.ereport.util.JsonUtil;

public class ReturnFormat {
	private static ServiceResponse sr;
	private static Logger logger = LoggerFactory.getLogger(ReturnFormat.class);
    public static String retParam(String rspCode) {
    	sr = new ServiceResponse();
    	sr.setRspCode(rspCode);
    	sr.setRspMsg(ResponseMessage.getResponseMessage(rspCode));
        return JsonUtil.convertJSON(sr);
    }
    public static String retParam(String rspCode,Object Data) {
    	sr = new ServiceResponse();
    	sr.setRspCode(rspCode);
    	sr.setRspMsg(ResponseMessage.getResponseMessage(rspCode));
    	sr.setBusinessData(Data);
        return JsonUtil.convertJSON(sr);
    }
    public static void main(String[] args){
    	List<UserInfo> a  = new ArrayList<UserInfo>();
    	UserInfo b = new UserInfo();
    	b.setUsername("test");
    	a.add(b);
    	a.add(b);
    	System.out.println(retParam("100000",a));
    }
}
