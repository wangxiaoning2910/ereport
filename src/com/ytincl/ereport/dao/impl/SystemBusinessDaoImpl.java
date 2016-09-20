package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.SystemBusinessDao;
import com.ytincl.ereport.pojo.SystemBusiness;
@Repository("systemBusinessDao")
public class SystemBusinessDaoImpl implements SystemBusinessDao {
	@Resource
	private SqlSession sqlSession;
	@Override
	public ArrayList<SystemBusiness> querySystemBusiness() {
		ArrayList<SystemBusiness> sysBusiness = new ArrayList<>();
		sysBusiness = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.SystemBusinessDao.querySystemBusiness");
		return sysBusiness;
	}
	@Override
	public void insertSystemBusiness(SystemBusiness systemBusiness) {
		sqlSession.insert("com.ytincl.ereport.dao.SystemBusinessDao.insertSystemBusiness",systemBusiness);
		
	}
	@Override
	public int updateSystemBusiness(SystemBusiness systemBusiness) {
		int s = sqlSession.update("com.ytincl.ereport.dao.SystemBusinessDao.updateSystemBusiness", systemBusiness);
		return s;
	}
	@Override
	public void deleteSystemBusiness(String userName) {
		sqlSession.delete("com.ytincl.ereport.dao.SystemBusinessDao.deleteSystemBusiness",userName);
		
	}

}
