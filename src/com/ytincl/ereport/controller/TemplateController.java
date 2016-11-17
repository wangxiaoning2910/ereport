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
import com.ytincl.ereport.util.ExcelControll;
import com.ytincl.ereport.util.ExcelReader;
import com.ytincl.ereport.util.ReportTools2;


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
		TemplateRes tr = new TemplateRes();
		if("0".equals(t)){//��ѯȫ��ģ��
			list = templateService.queryTemplate();
		}else if("1".equals(t)){//ֻ��ѯ����ģ��
			list = templateService.queryTemplateF();
		}else if("3".equals(t)){//��ѯԴģ�弰Դģ�����
			String temp_id = request.getParameter("temp_id");
			String version = request.getParameter("version");
			list = templateService.queryTemplateC(temp_id);
			TemplateDetailKey key = new TemplateDetailKey();
			ArrayList<TemplateDetail> list1 = new ArrayList<TemplateDetail>();
			for(int i=0;i<list.size();i++){//��ѯԴģ����
				key.setTemp_id(list.get(i).getTemp_id());
				key.setVersion(list.get(i).getVersion());
				System.out.println("========="+list.get(i).getTemp_name()+list.get(i).getVersion()+list.get(i).getVersion());
				list1.addAll(templateService.queryTemplateDetail(key));
			}
			tr.setList1(list1);
		}
		
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
	/**��ģ����ϸ����ӹ�ʽ
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/view/addTemplateFormula.do")
	@ResponseBody
	public BaseModel addTemplateFormula(HttpServletRequest request, HttpServletResponse response){
		TemplateDetail templateDetail = new TemplateDetail();
		templateDetail.setTemp_id(request.getParameter("temp_id"));
		templateDetail.setVersion(request.getParameter("version"));
		templateDetail.setLoc_num(request.getParameter("loc_num"));
		templateDetail.setFormula(request.getParameter("formula"));
		templateService.addTemplateFormula(templateDetail);
		return new BaseModel("000000");
	}
	
	/**��һģ���ϴ��ļ����ϲ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/view/exportByTemplate.do")
	@ResponseBody
	public BaseModel exportByTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileNames = request.getParameter("fileNames");
		fileNames = java.net.URLDecoder.decode(fileNames, "UTF-8");
		String id_version = request.getParameter("temp_id");
		String filesCol = request.getParameter("files_start");
		String filesRows = request.getParameter("files_rows");
		String temp_id = id_version.split("\\.")[0];
		String version = id_version.split("\\.")[1];
		String filePath = request.getSession().getServletContext().getRealPath("/"+id_version+"/");
		String sheet1_kh = filePath +"/"+ fileNames;
		System.out.println(sheet1_kh+"=====================");
		ExcelReader excelReader=new ExcelReader();
		ReportTools2 reportTools2 = new ReportTools2();
		//�����ļ����յ��ȡ��
		String[] fprop=reportTools2.getPos_ColRow(filesCol.split("-")[0], filesCol.split("-")[1]);	
		TemplateDetailKey key = new TemplateDetailKey();
		key.setTemp_id(temp_id);
		key.setVersion(version);
		ArrayList<TemplateDetail> list = new ArrayList<TemplateDetail>();
		list = templateService.queryTemplateDetail(key);
		String[] firstArray = new String[list.size()];//��ȡģ���һ�е�ֵ
		String[] formulaArray=new String[list.size()];
		String[] fprop_temp = new String[list.size()];
		for(int i=0;i<list.size();i++){
			firstArray[i] = list.get(i).getLoc_name();
			formulaArray[i] = list.get(i).getFormula();//��ȡ�й�ʽ��������
			fprop_temp[i] = list.get(i).getLoc_num();
		}
		//����EXCEL�ļ�ʱ������ghost_fprop
		String[] ghost_fprop=fprop_temp.clone();
		//�õ���������
		List<String[]> list1=excelReader.getALLExcelListByFileAndLetters(sheet1_kh,fprop, Integer.parseInt(filesRows));
		//�õ�����һ�����������
		List<String[]> listData=excelReader.getListArrayWithoutFirstRow(list1);
		List ghostList=excelReader.copyListStringArray(listData);
		//�����飬�ж�Ӧ��ʽ,����List�ļ��ϣ�������list
		//��һģ�幫ʽ����
		List computeSumList=excelReader.getSingleFormulaComputeList(listData,ghostList,fprop,formulaArray);
		excelReader.showListByString(computeSumList);
		//����ִ�е�һģ��ϲ�����
		List totalList=new ArrayList();
		totalList.add(excelReader.copyListStringArray(computeSumList));
		//���յõ���һģ��Ľ�����ݡ�
        List sumList=reportTools2.getSumColByIndexForSumListTotal(totalList,fprop,formulaArray);
        ExcelControll excelControll=new ExcelControll();
		String date = new SimpleDateFormat("yyyyMMddhh24mmss").format(new Date());
		String targetDirPath="d:\\1\\";
		String targetFileName="ѧ���ɼ�������single_"+date+".xls";		
		excelControll.downloadBatchInst( sheet1_kh, targetDirPath,targetFileName,computeSumList,ghost_fprop,firstArray);
		return new BaseModel("000000");
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
