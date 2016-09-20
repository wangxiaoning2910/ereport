package com.ytincl.ereport.dao;

import java.util.List;

import com.ytincl.ereport.pojo.FindFile;


public interface FindFileDao {

	public List[] getFindFileByName(FindFile obj);


	//List<FindFileAsParam> getByName();
	
}
