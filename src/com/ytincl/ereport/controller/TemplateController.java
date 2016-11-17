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
	 * 上传单一及复合模板 还有子模板
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
		String t = request.getParameter("t");//t=1 单一复合     t=2 子模板
		String temp_name = request.getParameter("temp_name");
		String temp_id = request.getParameter("temp_id");
		//--------------------------     存储模板相关信息------------------------------------//
		Template template = new Template();
		template.setCatalog("/uploadModelFiles");
		template.setFile_name(fileName);
		template.setTemp_id(temp_id);
		template.setTemp_name(temp_name);//模板名称说明
		template.setUrl(filePath);
		template.setVersion("V1");//直接存的v1版本
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
		//---------------------------将模板列名存入模板明细表------------------------------//
		String temp_start = request.getParameter("temp_start");//起终点坐标
		String temp_rows = request.getParameter("temp_rows");//行数
		String[] se = temp_start.split("-"); //分割起终点坐标，得到两个坐标点
		ReportTools2 reportTools2 = new ReportTools2();
		ExcelReader excelReader = new ExcelReader();		
		String[] fprop = reportTools2.getPos_ColRow(se[0], se[1]);	//所有列坐标的数组
		//得到所有数据
		String  sheet1_kh = filePath+"/"+fileName;
		List<String[]> list1=excelReader.getALLExcelListByFileAndLetters(sheet1_kh,fprop, 0);
		//只得到第一行，列标题
		String []firstArray =excelReader.getListArrayOnlyFirstRow(list1);
		TemplateDetail templateDetail = new TemplateDetail();
		templateDetail.setTemp_id(temp_id);
		templateDetail.setFormula("");//规则
		templateDetail.setIs_extend("0");//是否扩展列
		templateDetail.setType("0");//计算类型
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
	 * 查询模板 全部/复合/源
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
		if("0".equals(t)){//查询全部模板
			list = templateService.queryTemplate();
		}else if("1".equals(t)){//只查询复合模板
			list = templateService.queryTemplateF();
		}else if("3".equals(t)){//查询源模板及源模板的列
			String temp_id = request.getParameter("temp_id");
			String version = request.getParameter("version");
			list = templateService.queryTemplateC(temp_id);
			TemplateDetailKey key = new TemplateDetailKey();
			ArrayList<TemplateDetail> list1 = new ArrayList<TemplateDetail>();
			for(int i=0;i<list.size();i++){//查询源模板列
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
	/**
	 * 删除模板的数据源文件
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
	/**向模板明细中添加公式
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
	
	/**单一模板上传文件并合并输出
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
		//根据文件起终点获取列
		String[] fprop=reportTools2.getPos_ColRow(filesCol.split("-")[0], filesCol.split("-")[1]);	
		TemplateDetailKey key = new TemplateDetailKey();
		key.setTemp_id(temp_id);
		key.setVersion(version);
		ArrayList<TemplateDetail> list = new ArrayList<TemplateDetail>();
		list = templateService.queryTemplateDetail(key);
		String[] firstArray = new String[list.size()];//获取模板第一列的值
		String[] formulaArray=new String[list.size()];
		String[] fprop_temp = new String[list.size()];
		for(int i=0;i<list.size();i++){
			firstArray[i] = list.get(i).getLoc_name();
			formulaArray[i] = list.get(i).getFormula();//获取列公式放入数组
			fprop_temp[i] = list.get(i).getLoc_num();
		}
		//生成EXCEL文件时候适用ghost_fprop
		String[] ghost_fprop=fprop_temp.clone();
		//得到所有数据
		List<String[]> list1=excelReader.getALLExcelListByFileAndLetters(sheet1_kh,fprop, Integer.parseInt(filesRows));
		//得到除第一行外的所有行
		List<String[]> listData=excelReader.getListArrayWithoutFirstRow(list1);
		List ghostList=excelReader.copyListStringArray(listData);
		//列数组，列对应公式,数据List的集合，返回新list
		//单一模板公式处理
		List computeSumList=excelReader.getSingleFormulaComputeList(listData,ghostList,fprop,formulaArray);
		excelReader.showListByString(computeSumList);
		//这里执行单一模板合并操作
		List totalList=new ArrayList();
		totalList.add(excelReader.copyListStringArray(computeSumList));
		//最终得到单一模板的结果数据。
        List sumList=reportTools2.getSumColByIndexForSumListTotal(totalList,fprop,formulaArray);
        ExcelControll excelControll=new ExcelControll();
		String date = new SimpleDateFormat("yyyyMMddhh24mmss").format(new Date());
		String targetDirPath="d:\\1\\";
		String targetFileName="学生成绩工作表single_"+date+".xls";		
		excelControll.downloadBatchInst( sheet1_kh, targetDirPath,targetFileName,computeSumList,ghost_fprop,firstArray);
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
//		FileUtil.upFile(file.getInputStream(), uuidFileName, filePath);
		//测试，先不加uuid
		FileUtil.upFile(file.getInputStream(), fileName, filePath);
		return fileName;
	}
}
