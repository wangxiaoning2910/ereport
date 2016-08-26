package com.ytincl.ereport.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9133520001989791062L;
	private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);
	
	
	
	public BusinessException(String rspCode){
		ReturnFormat.retParam(rspCode);
	}
	
	

}
