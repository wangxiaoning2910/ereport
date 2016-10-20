package com.ytincl.ereport.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ytincl.ereport.model.FilesModel;
import com.ytincl.ereport.model.TemplateRes;
import com.ytincl.ereport.pojo.Template;
import com.ytincl.ereport.pojo.TemplateDetail;
import com.ytincl.ereport.pojo.TemplateDetailKey;
import com.ytincl.ereport.service.TemplateService;
import com.ytincl.ereport.util.FileUtil;
import com.ytincl.ereport.util.templateUtil.ExcelReader;
import com.ytincl.ereport.util.templateUtil.ReportTools2;


@Controller
public class TemplateController {
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(upLoadFile.class);
	@Autowired
	private TemplateService templateService;
	/**
	 * �ϴ���һ������ģ�� ������ģ��
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/view/ModelUpload.do")
	@ResponseBody
	public BaseModel ModelUpload(@RequestParam("templateFile") CommonsMultipartFile file,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String filePath = request.getSession().getServletContext().getRealPath("/uploadModelFiles/");
		logger.debug("filePath================="+filePath);
		String fileName;
		fileName= this.saveFile(file, filePath);
		String t = request.getParameter("t");//t=1 ��һ����     t=2 ��ģ��
		String temp_name = request.getParameter("temp_name");
		String temp_id = request.getParameter("temp_id");
		//--------------------------     �洢ģ�������Ϣ------------------------------------//
		Template template = new Template();
		template.setCatalog("/uploadModelFiles");
		template.setFile_name(fileName);
		template.setTemp_id(temp_id);
		template.setTemp_name(temp_name);//ģ������˵��
		template.setUrl(filePath);
		template.setVersion("V1");//ֱ�Ӵ��v1�汾
		if("1".equals(t)){
			String temp_type = request.getParameter("tempType");
			template.setTemp_type(temp_type);
		}else if("2".equals(t)){
			template.setTemp_type("2");
			String upTemp_id = request.getParameter("upTemp_id");
			template.setUpTemp_id(upTemp_id);
		}
		
		Date date = new Date();
		template.setCreate_time(date);
		templateService.insertTemplate(template);
		//---------------------------��ģ����������ģ����ϸ��------------------------------//
		String temp_start = request.getParameter("temp_start");//���յ�����
		String temp_rows = request.getParameter("temp_rows");//����
		String[] se = temp_start.split("-"); //�ָ����յ����꣬�õ����������
		ReportTools2 reportTools2 = new ReportTools2();
		ExcelReader excelReader = new ExcelReader();		
		String[] fprop = reportTools2.getPos_ColRow(se[0], se[1]);	//���������������
		//�õ���������
		String  sheet1_kh = filePath+"/"+fileName;
		List<String[]> list1=excelReader.getALLExcelListByFileAndLetters(sheet1_kh,fprop, 0);
		//ֻ�õ���һ�У��б���
		String []firstArray =excelReader.getListArrayOnlyFirstRow(list1);
		TemplateDetail templateDetail = new TemplateDetail();
		templateDetail.setTemp_id(temp_id);
		templateDetail.setFormula("");//����
		templateDetail.setIs_extend("0");//�Ƿ���չ��
		templateDetail.setType("0");//��������
		templateDetail.setVersion("V1");
		
		for(int i=0;i<firstArray.length;i++){		
			logger.debug("firstArray["+i+"]="+firstArray[i]+",");		
			templateDetail.setLoc_name(firstArray[i]);
			templateDetail.setLoc_num( Integer.toString(i+1));
			templateDetail.setLocation(fprop[i]);
			templateService.insertTemplateDetail(templateDetail);
		}
		return new BaseModel("000000");
	}
	
	/**
	 * ��ѯģ�� ȫ��/����/Դ
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/view/queryTemplate.do")
	@ResponseBody
	public TemplateRes queryTemplate(HttpServletRequest request, HttpServletResponse response){
		String t = request.getParameter("t");
		ArrayList<Template> list = new ArrayList<Template>();
		if("0".equals(t)){//��ѯȫ��ģ��
			list = templateService.queryTemplate();
		}else if("1".equals(t)){//ֻ��ѯ����ģ��
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
	 * ��ѯģ����ϸ
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
	 * ��ѯģ���µ�Դ�ļ�
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
		System.out.println("��Ŀ¼�¶��������"+tempList.length);
		for (int i = 0; i < tempList.length; i++) {
		   if (tempList[i].isFile()) {
		    System.out.println("��     ����"+tempList[i]);
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
	/**
	 * ɾ��ģ�������Դ�ļ�
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
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
	 * �ϴ�Դ�ļ���ģ��Ŀ¼��
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
	 * ��ѯԴģ����  =====��ѯģ����ϸ
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/view/queryTemplateCRow.do")
	@ResponseBody
	public TemplateRes queryTemplateCRow(HttpServletRequest request, HttpServletResponse response){
		String version = request.getParameter("version");
		String Temp_idC = request.getParameter("Temp_idC");
		TemplateDetailKey key = new TemplateDetailKey();
		key.setTemp_id(Temp_idC);
		key.setVersion(version);
		ArrayList<TemplateDetail> list = new ArrayList<TemplateDetail>();
		list = templateService.queryTemplateDetail(key);
		TemplateRes tr = new TemplateRes();
		tr.setList1(list);
		return tr;
	}
	public String saveFile( CommonsMultipartFile file,String filePath) throws IOException{
		String fileName = file.getOriginalFilename();
		logger.debug("fileName====="+fileName);
		String fileType = fileName.split("[.]")[1];
		logger.debug("fileType====="+fileType);
		//Ϊ�˱����ļ����ظ������ļ���ǰ��UUID
		String uuid = UUID.randomUUID().toString().replace("-","");
		String uuidFileName = uuid + fileName;
		//File f = new File(filePath+"/"+uuid+"."+fileType);
		//���ļ����浽������
//		FileUtil.upFile(file.getInputStream(), uuidFileName, filePath);
		//���ԣ��Ȳ���uuid
		FileUtil.upFile(file.getInputStream(), fileName, filePath);
		return fileName;
	}
}
