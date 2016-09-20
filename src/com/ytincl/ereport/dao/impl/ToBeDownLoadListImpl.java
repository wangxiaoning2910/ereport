package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ytincl.ereport.dao.ToBeDownLoadListDao;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.testdownloaddata;


@Repository("toBeDownLoadListDao")
public class ToBeDownLoadListImpl implements ToBeDownLoadListDao{
	@Resource
	private SqlSession sqlSession;
	@Override
	public List<ToBeDownLoadFile> getList(String querydate) {
		ArrayList<ToBeDownLoadFile> list;
		list = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.download.selectToBeDownLoadFile",querydate);
		return list; 
	}
	@Override
	public List<testdownloaddata> getDataList(String querydate) {
		ArrayList<testdownloaddata> list;
		list = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.download.selectdownloadata",querydate);
		return list;
	}

}
