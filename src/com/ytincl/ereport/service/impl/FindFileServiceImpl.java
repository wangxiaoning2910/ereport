package com.ytincl.ereport.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.FindFileDao;
import com.ytincl.ereport.model.FindFileAsParam;
import com.ytincl.ereport.pojo.FindFile;
import com.ytincl.ereport.service.FindFileService;


@Service("findFileService")
public class FindFileServiceImpl implements FindFileService{
	@Autowired
	private FindFileDao dao;
	
	@Override
	public List[] geFindFilename(FindFile obj) {
		// TODO Auto-generated method stub
		return dao.getFindFileByName(obj);
	}

	@Override
	public List<FindFile> getByDate() {
		// TODO Auto-generated method stub
		return null;
	}

}


