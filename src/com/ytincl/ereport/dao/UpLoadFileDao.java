package com.ytincl.ereport.dao;

import java.util.List;

import com.ytincl.ereport.pojo.UpLoadFile;

public interface UpLoadFileDao {
	public List<UpLoadFile> getToBeUpLoaded();
}
