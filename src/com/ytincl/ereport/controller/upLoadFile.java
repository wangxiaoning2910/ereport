package com.ytincl.ereport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.ytincl.ereport.model.BaseModel;
import com.ytincl.ereport.model.ToBeUploaded;
import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.service.UpLoadFileService;
import com.ytincl.ereport.util.FileUtil;
import com.ytincl.ereport.util.ReadExcel;

@Controller
public class upLoadFile {
	@Autowired
	private UpLoadFileService tobeuploaded;
	private static Logger logger = LoggerFactory.getLogger(upLoadFile.class);
	//接收上传文件
	@RequestMapping(value="/view/uploadFile.do")
	@ResponseBody
	public String uploadFile(@RequestParam("pic")CommonsMultipartFile pic,HttpServletRequest req,
			HttpServletResponse response) throws IOException{
		logger.debug("===========================UpLoadFile=================");
		//设置文件保存的本地路径
		String filePath = req.getSession().getServletContext().getRealPath("/uploadFiles/");
		logger.debug("filePath================="+filePath);
		String fileName = pic.getOriginalFilename();
		logger.debug("fileName====="+fileName);
		String fileType = fileName.split("[.]")[1];
		logger.debug("fileType====="+fileType);
		//为了避免文件名重复，在文件名前加UUID
		String uuid = UUID.randomUUID().toString().replace("-","");
		String uuidFileName = uuid + fileName;
		//File f = new File(filePath+"/"+uuid+"."+fileType);
	
		//将文件保存到服务器
		FileUtil.upFile(pic.getInputStream(), uuidFileName, filePath);
		return uuidFileName;

	}
	
	
	@RequestMapping(value="/view/uploadFile1.do")
	@ResponseBody
	public BaseModel uploadFile1(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest req,
			HttpServletResponse response) throws IOException{
		logger.debug("===========================UpLoadFile=================");
		//设置文件保存的本地路径
		String filePath = req.getSession().getServletContext().getRealPath("/uploadFiles/");
		logger.debug("filePath================="+filePath);
		String fileName = file.getOriginalFilename();
		logger.debug("fileName====="+fileName);
		String fileType = fileName.split("[.]")[1];
		logger.debug("fileType====="+fileType);
		//为了避免文件名重复，在文件名前加UUID
		String uuid = UUID.randomUUID().toString().replace("-","");
		String uuidFileName = uuid + fileName;
		//File f = new File(filePath+"/"+uuid+"."+fileType);
		//将文件保存到服务器
		FileUtil.upFile(file.getInputStream(), uuidFileName, filePath);
		ReadExcel re = new ReadExcel();
		UpLoadFile ulf = new UpLoadFile();
		ResolveExcel resolvexcel = null;
		//execel内容//
		List<String[]> list = re.readExcel(filePath+"\\"+uuidFileName);
		if (list != null) {
			for (int i = 0;i<list.size();i++) {
				resolvexcel = new ResolveExcel();
				String str[] = list.get(i);
				resolvexcel.setNo(str[0]);
				resolvexcel.setName(str[1]);
				resolvexcel.setAge(str[2]);
				resolvexcel.setScore(str[3]);
				//存入数据库
				tobeuploaded.inserExcelContent(resolvexcel);
			}
		}
		//更新操作状态
		tobeuploaded.updateStatus(ulf);
		return new BaseModel("000000");

	}
	@RequestMapping(value="/view/getToBeUpLoaded.do")
	@ResponseBody
	public ToBeUploaded getToBeUpLoaded(
			@RequestParam(value="ymounth", required=true) String querydate,
			HttpServletRequest request,
			HttpServletResponse response){
		logger.debug("查询日期是====="+querydate);
		ToBeUploaded tbu = new ToBeUploaded();
		ArrayList<UpLoadFile> list;
		list = (ArrayList<UpLoadFile>) tobeuploaded.getToBeUpLoadedList(querydate);
		tbu.setList(list);
		return tbu;
	}
	public int updatestatus(){
		
		return 0;
		
	}
}
