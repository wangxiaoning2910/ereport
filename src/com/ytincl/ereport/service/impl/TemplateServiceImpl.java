package com.ytincl.ereport.service.impl;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.SystemBusinessDao;
import com.ytincl.ereport.dao.TemplateDao;
import com.ytincl.ereport.dao.TemplateDetailDao;
import com.ytincl.ereport.pojo.SystemBusiness;
import com.ytincl.ereport.pojo.Template;
import com.ytincl.ereport.pojo.TemplateDetail;
import com.ytincl.ereport.pojo.TemplateDetailKey;
import com.ytincl.ereport.service.TemplateService;

@Service("TemplateService")
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateDao templateDao;
	@Autowired
	private TemplateDetailDao templateDetailDao;

	@Override
	public int insertTemplate(Template template) {
		int n = templateDao.insert(template);
		return n;
	}

	@Override
	public ArrayList<Template> queryTemplate() {
		ArrayList<Template> list = new ArrayList<Template>();
		list = templateDao.queryTemplate();
		return list;
	}

	@Override
	public ArrayList<TemplateDetail> queryTemplateDetail(TemplateDetailKey key) {
		
		return templateDetailDao.queryTemplateDetail(key);
	}

	@Override
	public ArrayList<Template> queryTemplateF() {
		ArrayList<Template> list = new ArrayList<Template>();
		list = templateDao.queryTemplateF();
		return list;
	}

	@Override
	public ArrayList<Template> queryTemplateC(String temp_id) {
		ArrayList<Template> list = new ArrayList<Template>();
		list = templateDao.queryTemplateC(temp_id);
		return list;
	}
	

	
	
}
