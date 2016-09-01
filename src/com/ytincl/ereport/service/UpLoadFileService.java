package com.ytincl.ereport.service;

import java.util.List;

import com.ytincl.ereport.pojo.Menu;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.pojo.UserInfo;

public interface UpLoadFileService {
	
	public List<UpLoadFile> getToBeUpLoadedList();

}
