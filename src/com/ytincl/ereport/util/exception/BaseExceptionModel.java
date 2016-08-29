package com.ytincl.ereport.util.exception;

import com.ytincl.ereport.model.BaseModel;

public class BaseExceptionModel extends BaseModel {
	
	public BaseExceptionModel(String rspCode){
		this.setRspCode(rspCode);
	}
    
}
