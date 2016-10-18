package com.ytincl.ereport.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ytincl.ereport.model.FilesModel;
import com.ytincl.ereport.model.TemplateRes;
import com.ytincl.ereport.pojo.Template;
import com.ytincl.ereport.pojo.TemplateDetail;
import com.ytincl.ereport.pojo.TemplateDetailKey;
import com.ytincl.ereport.service.TemplateService;
import com.ytincl.ereport.util.FileUtil;


@Controller
public class TemplateController {
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(upLoadFile.class);
	@Autowired
	private TemplateService templateService;
	/**
	 * 上传单一及复合模板
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/view/ModelUpload.do")
	@ResponseBody
	public BaseModel ModelUpload(@RequestParam("templateFile") CommonsMultipartFile file,HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String filePath = request.getSession().getServletContext().getRealPath("/uploadModelFiles/");
		logger.debug("filePath================="+filePath);
		String fileName;
		fileName= this.saveFile(file, filePath);
		String temp_name = request.getParameter("temp_name");
		String temp_id = request.getParameter("temp_id");
		String temp_type = request.getParameter("tempType");
		String temp_start = request.getParameter("temp_start");//起终点坐标
		String temp_rows = request.getParameter("temp_rows");//行数
		//存储
		Template template = new Template();
		template.setCatalog("/uploadModelFiles");
		template.setFile_name(fileName);
		template.setTemp_id(temp_id);
		template.setTemp_name(temp_name);//模板名称说明
		template.setUrl(filePath);
		template.setVersion("V1");//直接存的v1版本
		template.setTemp_type(temp_type);
		Date date = new Date();
		template.setCreate_time(date);
		templateService.insertTemplate(template);
		return new BaseModel("000000");
	}
	
	/**
	 * 查询模板 全部 复合 源
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/view/queryTemplate.do")
	@ResponseBody
	public TemplateRes queryTemplate(HttpServletRequest request, HttpServletResponse response){
		String t = request.getParameter("t");
		ArrayList<Template> list = new ArrayList<Template>();
		if("0".equals(t)){//查询全部模板
			list = templateService.queryTemplate();
		}else if("1".equals(t)){//只查询复合模板
			list = templateService.queryTemplateF();
		}else if("3".equals(t)){
			String temp_id = request.getParameter("temp_id");
			list = templateService.queryTemplateC(temp_id);
		}
		TemplateRes tr = new TemplateRes();
		tr.setList(list);
		return tr;
	}
	/**
	 * 查询模板明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/view/queryTemplateDetail.do")
	@ResponseBody
	public TemplateRes queryTemplateDetail(HttpServletRequest request, HttpServletResponse response){
		String temp_id = request.getParameter("temp_id");
		String version = request.getParameter("version");
		TemplateDetailKey key = new TemplateDetailKey();
		key.setTemp_id(temp_id);
		key.setVersion(version);
		ArrayList<TemplateDetail> list = new ArrayList<TemplateDetail>();
		list = templateService.queryTemplateDetail(key);
		TemplateRes tr = new TemplateRes();
		tr.setList1(list);
		return tr;
	}
	/**
	 * 查询模板下的源文件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/view/queryFile.do")
	@ResponseBody
	public TemplateRes queryFile(HttpServletRequest request, HttpServletResponse response){
		String temp_id = request.getParameter("temp_id");
		FilesModel fm = new FilesModel();
		ArrayList list = new ArrayList();
		String filePath = request.getSession().getServletContext().getRealPath("/"+temp_id+"/");
		File file=new File(filePath);
		File[] tempList = file.listFiles();
		System.out.println("该目录下对象个数："+tempList.length);
		for (int i = 0; i < tempList.length; i++) {
		   if (tempList[i].isFile()) {
		    System.out.println("文     件："+tempList[i]);
		    String file_name = tempList[i].getName();
		    fm.setFile_name(file_name);
		    fm.setTemp_id(temp_id);
		    list.add(fm);
		   }
		}
		
		TemplateRes tr = new TemplateRes();
		tr.setList2(list);
		return tr;
	}
	@RequestMapping(value="/view/fileDelete.do")
	@ResponseBody
	public BaseModel fileDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String fileName = request.getParameter("fileName");
		String temp_id = request.getParameter("temp_id");
		String filePath = request.getSession().getServletContext().getRealPath("/"+temp_id+"/");
		File file = new File(filePath+"/"+fileName);
		logger.debug("filePath================="+filePath);
		file.delete();
		return new BaseModel("000000");
	}
	/**
	 * 上传源文件到模板目录下
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/view/uploadFiles.do")
	@ResponseBody
	public BaseModel uploadFiles(@RequestParam("files") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String temp_id = request.getParameter("temp_id");
		String filePath = request.getSession().getServletContext().getRealPath("/"+temp_id+"/");
		logger.debug("filePath================="+filePath);
		this.saveFile(file, filePath);
		return new BaseModel("000000");
	}
	/**
	 * 上传子模板
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/view/ModelUploadC.do")
	@ResponseBody
	public BaseModel ModelUploadC(@RequestParam("templateFileC") CommonsMultipartFile file,HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String filePath = request.getSession().getServletContext().getRealPath("/uploadModelFiles/");
		logger.debug("filePath================="+filePath);
		String fileName;
		fileName= this.saveFile(file, filePath);
		String temp_name = request.getParameter("temp_name");
		String temp_id = request.getParameter("temp_id");
		String temp_type = request.getParameter("tempType");
		String temp_start = request.getParameter("temp_start");
		String upTemp_id = request.getParameter("upTemp_id");
		String temp_rows = request.getParameter("temp_rows");//行数
		//存储
		Template template = new Template();
		template.setCatalog("/uploadModelFiles");
		template.setFile_name(fileName);
		template.setTemp_id(temp_id);
		template.setTemp_name(temp_name);//模板名称说明
		template.setUrl(filePath);
		template.setVersion("V1");//直接存的v1版本
		template.setTemp_type(temp_type);
		template.setTemp_type("2");
		template.setUpTemp_id(upTemp_id);
		Date date = new Date();
		template.setCreate_time(date);
		templateService.insertTemplate(template);
		return new BaseModel("000000");
	}
	public String saveFile( CommonsMultipartFile file,String filePath) throws IOException{
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
		return fileName;
	}
}
