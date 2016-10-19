package com.ytincl.ereport.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Driver;
import com.ytincl.ereport.dao.GetEreportDao;
import com.ytincl.ereport.pojo.GetEreport;

@Repository("getEreportDao")
public class GetEreportDaoImpl  implements  GetEreportDao{
	@Resource
	private SqlSession sqlSession;
	@Override
	public List getGetEreportByName(String date) {
		// TODO Auto-generated method stub
		List<GetEreport> data = sqlSession.selectList("com.ytincl.ereport.dao.GetEreportDao.selectGetEreportByName", date); //报表个数
		return data;
	}

}
