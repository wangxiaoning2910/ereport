package com.ytincl.ereport.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.MonthTableDao;
import com.ytincl.ereport.pojo.MonthTable;
import com.ytincl.ereport.service.MonthTableService;

@Service("MonthTableService")
public class MonthTableServiceImpl implements MonthTableService {
	@Autowired
	private MonthTableDao monthTableDao;

	@Override
	public ArrayList<MonthTable> queryMonthTable(String monthTableName) {
		// TODO Auto-generated method stub
		return monthTableDao.queryMonthTable(monthTableName);
	}

	
}
