package com.ytincl.ereport.dao;

import java.util.List;

import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.pojo.UpLoadFile;

public interface UpLoadFileDao {
	public List<UpLoadFile> getToBeUpLoaded(String querydate);
	public int insertExcelContent(ResolveExcel re); 
	public int updateStatus(UpLoadFile ulf);
}
