package com.ytincl.ereport.dao;

import java.util.List;

import com.ytincl.ereport.pojo.Menu;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.UserInfo;
import com.ytincl.ereport.pojo.testdownloaddata;

public interface ToBeDownLoadListDao {
	public List<ToBeDownLoadFile> getList(String querydate);
	public List<testdownloaddata> getDataList(String querydate);
}
