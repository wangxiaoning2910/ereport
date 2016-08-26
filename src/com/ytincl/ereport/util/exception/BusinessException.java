package com.ytincl.ereport.util.exception;


public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9133520001989791062L;
	
	
	public BusinessException(String rspCode){
		ReturnFormat.retParam(rspCode);
	}
	
	

}
