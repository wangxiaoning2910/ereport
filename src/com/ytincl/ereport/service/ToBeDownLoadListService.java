package com.ytincl.ereport.service;

import java.util.ArrayList;
import java.util.List;

import com.ytincl.ereport.pojo.ManuallyTemplate;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.originalData;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_inst;
import com.ytincl.ereport.pojo.testdownloaddata;

public interface ToBeDownLoadListService {
	public ArrayList<ToBeDownLoadFile> getList(String querydate);
	public ArrayList<testdownloaddata> getDataList(String querydate);
	public ArrayList<ManuallyTemplate> getTempList();
	public ArrayList<originalData> getOriginalData();
	public ArrayList<pbsmr_busi> getpbsmr_busiList(pbsmr_busi pb);
	public ArrayList<pbsmr_inst> getpbsmr_instList(pbsmr_inst pi);
}
