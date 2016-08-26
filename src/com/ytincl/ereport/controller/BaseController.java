package com.ytincl.ereport.controller;

import javax.servlet.http.HttpServletResponse;

import com.ytincl.ereport.util.exception.ReturnFormat;

public abstract class BaseController {
	protected String retContent(String status) {
        return ReturnFormat.retParam(status);
    }
	protected String retContent(String status,Object data) {
        return ReturnFormat.retParam(status, data);
    }
}
