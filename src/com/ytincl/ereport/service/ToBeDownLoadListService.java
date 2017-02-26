package com.ytincl.ereport.service;

import java.util.ArrayList;
import com.ytincl.ereport.pojo.ManuallyTemplate;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.newMerch;
import com.ytincl.ereport.pojo.originalData;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_entrustunit;
import com.ytincl.ereport.pojo.pbsmr_inst;
import com.ytincl.ereport.pojo.testdownloaddata;

public interface ToBeDownLoadListService {
	public ArrayList<ToBeDownLoadFile> getList(String querydate);
	public ArrayList<testdownloaddata> getDataList(String querydate);
	public ArrayList<ManuallyTemplate> getTempList();
	public ArrayList<originalData> getOriginalData();
	public ArrayList<pbsmr_busi> getpbsmr_busiList(pbsmr_busi pb);
	public ArrayList<pbsmr_inst> getpbsmr_instList(pbsmr_inst pi);
	public ArrayList<pbsmr_entrustunit> getpbsmr_enList(pbsmr_entrustunit pe);
	public ArrayList<newMerch> getnewMerch(newMerch nm);
	public int insertNewMerch(newMerch nm);
}
