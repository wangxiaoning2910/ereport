package com.ytincl.ereport.service;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.MonthTable;

public interface MonthTableService {
	public ArrayList<MonthTable> queryMonthTable(String monthTableName);
	
}
