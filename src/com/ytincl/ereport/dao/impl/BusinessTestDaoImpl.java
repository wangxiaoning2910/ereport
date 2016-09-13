package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.BusinessTestDao;
import com.ytincl.ereport.pojo.BusinessTest;
@Repository("BusinessTestDao")
public class BusinessTestDaoImpl implements BusinessTestDao {
	@Resource
	private SqlSession sqlSession;
	@Override
	public List<BusinessTest> queryBusinessTest() {
		List<BusinessTest> sysBusiness = new ArrayList<>();
		sysBusiness = (List)sqlSession.selectList("com.ytincl.ereport.dao.BusinessTestDao.queryBusinessTest");
		return sysBusiness;
	}
	

}
