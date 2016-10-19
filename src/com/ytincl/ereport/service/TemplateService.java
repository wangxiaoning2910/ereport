package com.ytincl.ereport.service;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.Template;
import com.ytincl.ereport.pojo.TemplateDetail;
import com.ytincl.ereport.pojo.TemplateDetailKey;

public interface TemplateService {
	public int insertTemplate(Template template);
	public ArrayList<Template> queryTemplate();
	ArrayList<TemplateDetail> queryTemplateDetail(TemplateDetailKey key);
	public ArrayList<Template> queryTemplateF();
	public ArrayList<Template> queryTemplateC(String temp_id);
	
	public int insertTemplateDetail(TemplateDetail templateDetail);
}
