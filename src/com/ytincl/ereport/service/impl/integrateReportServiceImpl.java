package com.ytincl.ereport.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ytincl.ereport.dao.integrateReportDao;
import com.ytincl.ereport.service.integrateReportService;

@Service("irservice")
public class integrateReportServiceImpl implements integrateReportService {
	@Autowired
	private integrateReportDao irDao;

	@Override
	public List<String> getTableField(String tablename) {
		// TODO Auto-generated method stub
		return irDao.getTableField(tablename);
	}

	
	
}
