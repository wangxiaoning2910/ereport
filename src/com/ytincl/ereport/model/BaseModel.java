package com.ytincl.ereport.model;

import com.ytincl.ereport.util.exception.ResponseMessage;

public class BaseModel {
	private String rspCode;
	private String rspMsg;
	
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
		this.setRspMsg(ResponseMessage.getResponseMessage(rspCode));
	}
	public String getRspMsg() {
		return rspMsg;
	}
	private void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}
	
}
