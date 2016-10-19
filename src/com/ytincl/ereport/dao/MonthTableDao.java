package com.ytincl.ereport.dao;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.MonthTable;

public interface MonthTableDao {
	public ArrayList<MonthTable> queryMonthTable(String monthTableName);
	
}
