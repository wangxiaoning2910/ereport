package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.MonthTableDao;
import com.ytincl.ereport.pojo.MonthTable;
@Repository("MonthTableDao")
public class MonthTableImpl implements MonthTableDao {

	@Resource
	private SqlSession sqlSession;
	@Override
	public ArrayList<MonthTable> queryMonthTable() {
		ArrayList<MonthTable> monthTables= new ArrayList<>();
		monthTables = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.MonthTableDao.queryMonthTable");
		return monthTables;
	}

}
