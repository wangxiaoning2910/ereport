package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ytincl.ereport.dao.ToBeDownLoadListDao;
import com.ytincl.ereport.pojo.ManuallyTemplate;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.newMerch;
import com.ytincl.ereport.pojo.originalData;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_entrustunit;
import com.ytincl.ereport.pojo.pbsmr_inst;
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
	public ArrayList<testdownloaddata> getDataList(String querydate) {
		ArrayList<testdownloaddata> list;
		list = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.download.selectdownloadata",querydate);
		return list;
	}
	@Override
	public List<ManuallyTemplate> getTempList() {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.UpLoadFileDao.queryTemplates");
	}
	@Override
	public List<originalData> getOriginalData() {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.UpLoadFileDao.queryOriginalData");
	}
	@Override
	public ArrayList<pbsmr_busi> getpbsmr_busiList(pbsmr_busi pb) {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.UpLoadFileDao.querypbs",pb);
	}
	@Override
	public ArrayList<pbsmr_inst> getpbsmr_busiList(pbsmr_inst pi) {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.UpLoadFileDao.querypis",pi);
	}
	@Override
	public ArrayList<pbsmr_entrustunit> getpbsmr_enList(pbsmr_entrustunit pe) {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.UpLoadFileDao.querypes",pe);
	}
	@Override
	public int insertNewMerch(newMerch nm) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.UpLoadFileDao.insertNewMerch",nm);
	}
	@Override
	public ArrayList<newMerch> getnewMerch(newMerch nm) {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.UpLoadFileDao.getNewMerch",nm);
	}

}
