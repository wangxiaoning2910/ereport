package com.ytincl.ereport.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ytincl.ereport.dao.ToBeDownLoadListDao;
import com.ytincl.ereport.pojo.ManuallyTemplate;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.newMerch;
import com.ytincl.ereport.pojo.originalData;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_entrustunit;
import com.ytincl.ereport.pojo.pbsmr_inst;
import com.ytincl.ereport.pojo.testdownloaddata;
import com.ytincl.ereport.service.ToBeDownLoadListService;
@Service("getdownloadlist")
public class ToBeDownLoadListServiceImpl implements ToBeDownLoadListService{
	@Autowired
	private ToBeDownLoadListDao toBeDownLoadListDao;
	@Override
	public ArrayList<ToBeDownLoadFile> getList(String querydate) {
		// TODO Auto-generated method stub
		return (ArrayList<ToBeDownLoadFile>) toBeDownLoadListDao.getList(querydate);
	}
	@Override
	public ArrayList<testdownloaddata> getDataList(String querydate) {
		ArrayList<testdownloaddata> list;
		list = (ArrayList<testdownloaddata>) toBeDownLoadListDao.getDataList(querydate);
		return list;
	}
	@Override
	public ArrayList<ManuallyTemplate> getTempList() {
		// TODO Auto-generated method stub
		return (ArrayList<ManuallyTemplate>)toBeDownLoadListDao.getTempList();
	}
	@Override
	public ArrayList<originalData> getOriginalData() {
		// TODO Auto-generated method stub
		return (ArrayList<originalData>)toBeDownLoadListDao.getOriginalData();
	}
	@Override
	public ArrayList<pbsmr_busi> getpbsmr_busiList(pbsmr_busi pb) {
		// TODO Auto-generated method stub
		return (ArrayList<pbsmr_busi>)toBeDownLoadListDao.getpbsmr_busiList(pb);
	}
	@Override
	public ArrayList<pbsmr_inst> getpbsmr_instList(pbsmr_inst pi) {
		// TODO Auto-generated method stub
		return (ArrayList<pbsmr_inst>)toBeDownLoadListDao.getpbsmr_busiList(pi);
	}
	@Override
	public ArrayList<pbsmr_entrustunit> getpbsmr_enList(pbsmr_entrustunit pe) {
		// TODO Auto-generated method stub
		return (ArrayList<pbsmr_entrustunit>)toBeDownLoadListDao.getpbsmr_enList(pe);
	}
	@Override
	public int insertNewMerch(newMerch nm) {
		// TODO Auto-generated method stub
		return toBeDownLoadListDao.insertNewMerch(nm);
	}
	@Override
	public ArrayList<newMerch> getnewMerch(newMerch nm) {
		// TODO Auto-generated method stub
		return (ArrayList<newMerch>)toBeDownLoadListDao.getnewMerch(nm);
	}
}
