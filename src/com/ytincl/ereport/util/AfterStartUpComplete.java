package com.ytincl.ereport.util;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.pojo.originalData;
import com.ytincl.ereport.service.ToBeDownLoadListService;
import com.ytincl.ereport.service.UpLoadFileService;
@Component("BeanDefineConfigue")
public class AfterStartUpComplete implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ToBeDownLoadListService getdownloadlist;
	@Autowired
	private UpLoadFileService tobeuploaded;
	private static Logger logger = LoggerFactory.getLogger(AfterStartUpComplete.class);
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("do something"); 
		//1判断当月是否已经存在数据
		UpLoadFile upl;
		Date currentDate = new Date(System.currentTimeMillis());
		String currentDatestr = currentDate.toString();
		String querydate = currentDatestr.split("-")[0]+""+currentDatestr.split("-")[1];
		List<UpLoadFile> list = tobeuploaded.getToBeUpLoadedList(querydate);
		List<originalData> alist = getdownloadlist.getOriginalData();
		if(list.isEmpty()){
			//准备插入数据
			for(int i = 0;i<alist.size();i++){
				upl = new UpLoadFile();
				upl.setName(alist.get(i).getText());
				upl.setStatus("0");
				upl.setQueryDate(querydate);
				tobeuploaded.insertdata(upl);
			}
			logger.info("任务执行成功");
		}else{
			logger.info("本月无需insert");
		}
	}


}
