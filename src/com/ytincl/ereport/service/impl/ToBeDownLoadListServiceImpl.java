package com.ytincl.ereport.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.ToBeDownLoadListDao;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
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

}
