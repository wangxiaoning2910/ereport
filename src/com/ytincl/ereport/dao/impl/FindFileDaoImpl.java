package com.ytincl.ereport.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Driver;
import com.ytincl.ereport.dao.FindFileDao;
import com.ytincl.ereport.pojo.DepType;

@Repository("findFileDao")
public class FindFileDaoImpl  implements  FindFileDao{
	@Resource
	private SqlSession sqlSession;
	@Override
	public List getDepTypeData(String date) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ytincl.ereport.dao.FindFileDao.selectDepTypeByDate", date); //全口径数据
	}

	public List getDepCityData(String date) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ytincl.ereport.dao.FindFileDao.selectDepCityByDate", date); //全口径数据
	}

	@Override
	public List getDepCountyData(String date) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ytincl.ereport.dao.FindFileDao.selectDepCountyByDate", date); //全口径数据
	}

}
