package com.ytincl.ereport.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ytincl.ereport.model.BaseModel;

@Controller
public class uploadCustomTemplate {
	private static Logger logger = LoggerFactory.getLogger(uploadCustomTemplate.class);

	
	@RequestMapping(value="/view/uploadCT.do")
	@ResponseBody
	public BaseModel uploadCT(
			@RequestParam("ctfile") CommonsMultipartFile file,
			HttpServletRequest req,
			HttpServletResponse response) throws IOException, ParseException{
		logger.debug("ctfilename===="+file.getName());
		InputStream is = file.getInputStream();
		
		return null;
		
	}
}
