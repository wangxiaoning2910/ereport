package com.ytincl.ereport.dao;

import java.util.List;

import com.ytincl.ereport.pojo.DepType;


public interface FindFileDao {

	public List getDepTypeData(String date);

	public List getDepCityData(String date);

	public List getDepCountyData(String date);

	//List<FindFileAsParam> getByName();
	
}
