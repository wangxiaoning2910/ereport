package com.ytincl.ereport.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.GetEreportDao;
import com.ytincl.ereport.model.GetEreportAsParam;
import com.ytincl.ereport.pojo.GetEreport;
import com.ytincl.ereport.service.GetEreportService;


@Service("getEreportService")
public class GetEreportServiceImpl implements GetEreportService{
	@Autowired
	private GetEreportDao dao;
	
	@Override
	public List getGetEreportname(String date) {
		// TODO Auto-generated method stub
		return dao.getGetEreportByName(date);
	}

	@Override
	public List<GetEreport> getByDate() {
		// TODO Auto-generated method stub
		return null;
	}
	

}


