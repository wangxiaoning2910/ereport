package com.ytincl.ereport.util.exception;

public class ServiceResponse {
	//��Ӧ��
    private String rspCode;
    //��Ӧ��Ϣ
    private String rspMsg;
    //ҵ������
    private Object businessData;
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	public String getRspMsg() {
		return rspMsg;
	}
	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}
	public Object getBusinessData() {
		return businessData;
	}
	public void setBusinessData(Object businessData) {
		this.businessData = businessData;
	}
    
    
    
}
