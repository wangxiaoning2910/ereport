package com.ytincl.ereport.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.FindFileDao;
import com.ytincl.ereport.model.FindFileAsParam;
import com.ytincl.ereport.pojo.DepType;
import com.ytincl.ereport.pojo.DepCity;
import com.ytincl.ereport.pojo.DepCounty;
import com.ytincl.ereport.service.FindFileService;


@Service("findFileService")
public class FindFileServiceImpl implements FindFileService{
	@Autowired
	private FindFileDao dao;
	

	
	@Override
	public List<DepType> getByDate() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List getDepTypeService(String date) {
		// TODO Auto-generated method stub
		return dao.getDepTypeData(date);
	}

	@Override
	public List getDepCityService(String date) {
		// TODO Auto-generated method stub
		return dao.getDepCityData(date);
	}



	@Override
	public List getDepCountyService(String date) {
		// TODO Auto-generated method stub
		return dao.getDepCountyData(date);
	}

}


