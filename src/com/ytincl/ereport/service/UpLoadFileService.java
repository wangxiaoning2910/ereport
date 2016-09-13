package com.ytincl.ereport.service;

import java.util.List;
import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.pojo.UpLoadFile;

public interface UpLoadFileService {
	public List<UpLoadFile> getToBeUpLoadedList(String querydate);
	public int inserExcelContent(ResolveExcel re);
	public int updateStatus(UpLoadFile ulf);
}
