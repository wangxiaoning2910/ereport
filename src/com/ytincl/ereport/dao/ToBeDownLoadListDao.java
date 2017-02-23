package com.ytincl.ereport.dao;

import java.util.ArrayList;
import java.util.List;

import com.ytincl.ereport.pojo.ManuallyTemplate;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.originalData;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_entrustunit;
import com.ytincl.ereport.pojo.pbsmr_inst;
import com.ytincl.ereport.pojo.testdownloaddata;

public interface ToBeDownLoadListDao {
	public List<ToBeDownLoadFile> getList(String querydate);
	public List<testdownloaddata> getDataList(String querydate);
	public List<ManuallyTemplate> getTempList();
	public List<originalData> getOriginalData();
	public ArrayList<pbsmr_busi> getpbsmr_busiList(pbsmr_busi pb);
	public ArrayList<pbsmr_inst> getpbsmr_busiList(pbsmr_inst pi);
	public ArrayList<pbsmr_entrustunit> getpbsmr_enList(pbsmr_entrustunit pe);
}
