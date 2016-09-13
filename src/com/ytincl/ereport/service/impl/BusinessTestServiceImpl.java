package com.ytincl.ereport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.BusinessTestDao;
import com.ytincl.ereport.pojo.BusinessTest;
import com.ytincl.ereport.service.BusinessTestService;

@Service("BusinessTestService")
public class BusinessTestServiceImpl implements BusinessTestService {
	@Autowired
	private BusinessTestDao businessTestDao;

	@Override
	public List<BusinessTest> queryBusinessTest() {
		
		return businessTestDao.queryBusinessTest();
	}
	
}
