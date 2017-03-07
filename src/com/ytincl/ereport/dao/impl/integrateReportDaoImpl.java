package com.ytincl.ereport.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.integrateReportDao;
@Repository("irDao")
public class integrateReportDaoImpl implements integrateReportDao {
	@Resource
	private SqlSession sqlSession;

	@Override
	public List<String> getTableField(String tablename) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ytincl.ereport.dao.irDao.queryTableField",tablename);
	}
	
}
