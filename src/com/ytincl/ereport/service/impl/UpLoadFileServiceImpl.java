package com.ytincl.ereport.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ytincl.ereport.dao.UpLoadFileDao;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.service.UpLoadFileService;


@Service("tobeuploaded")
public class UpLoadFileServiceImpl implements UpLoadFileService{
	@Autowired
	private UpLoadFileDao uploadfiledao;

	@Override
	public List<UpLoadFile> getToBeUpLoadedList() {
		// TODO Auto-generated method stub
		return uploadfiledao.getToBeUpLoaded();
	}
	
	
}


