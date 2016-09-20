package com.ytincl.ereport.service;

import java.util.Date;
import java.util.List;

import com.ytincl.ereport.model.FindFileAsParam;
import com.ytincl.ereport.pojo.FindFile;

public interface FindFileService {
	
	public List[] geFindFilename(FindFile obj);

	List<FindFile> getByDate();

	

}
