package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.ResourcesDao;
import com.ytincl.ereport.pojo.Resources;
@Repository("ResourcesDao")
public class ResourcesDaoImpl implements ResourcesDao {

	@Resource
	private SqlSession sqlSession;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Resources record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Resources record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Resources selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Resources record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Resources record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Resources> queryAll(Resources r) {
		List<Resources> list = new ArrayList<Resources>();
		list = (List)sqlSession.selectList("com.ytincl.ereport.dao.ResourcesDao.queryAll",r);
		return list;
	}

	@Override
	public List<Resources> findAccountResourcess(String accountId) {
		List<Resources> list = new ArrayList<Resources>();
		list = (List)sqlSession.selectList("com.ytincl.ereport.dao.ResourcesDao.findAccountResourcess",accountId);
		return list;
	}

	@Override
	public List<Resources> getAllResources() {
		List<Resources> list = new ArrayList<Resources>();
		list = (List)sqlSession.selectList("com.ytincl.ereport.dao.ResourcesDao.getAllResourcess");
		return list;
	}

	@Override
	public Resources getResourcesByMid(String mid) {
		Resources resouces = (Resources)sqlSession.selectOne("com.ytincl.ereport.dao.ResourcesDao.getResourcesByMid", mid);
		return resouces;
	}


}
