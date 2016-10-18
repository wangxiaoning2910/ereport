package com.ytincl.ereport.service;

import java.util.ArrayList;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.testdownloaddata;

public interface ToBeDownLoadListService {
	public ArrayList<ToBeDownLoadFile> getList(String querydate);
	public ArrayList<testdownloaddata> getDataList(String querydate);

}
