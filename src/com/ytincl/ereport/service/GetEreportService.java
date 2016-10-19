package com.ytincl.ereport.service;

import java.util.Date;
import java.util.List;

import com.ytincl.ereport.model.GetEreportAsParam;
import com.ytincl.ereport.pojo.GetEreport;

public interface GetEreportService {
	
	public List getGetEreportname(String date);

	List<GetEreport> getByDate();

}
