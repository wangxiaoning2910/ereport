package com.ytincl.ereport.service;

import java.util.Date;
import java.util.List;

import com.ytincl.ereport.model.FindFileAsParam;
import com.ytincl.ereport.pojo.DepCity;
import com.ytincl.ereport.pojo.DepType;
import com.ytincl.ereport.pojo.DepCounty;

public interface FindFileService {
	
	public List getDepTypeService(String obj);

	List<DepType> getByDate();

	public List getDepCityService(String obj);
	public List getDepCountyService(String obj);
	

}
