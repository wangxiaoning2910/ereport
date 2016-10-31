package com.ytincl.ereport.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


/**
 * excel操作工具类
 * @author wanghui
 *
 */
public class ExcelUtil{
	/*
	public static Logger log = Logger.getLogger(ExcelUtil.class);
	public static Map<String, String[]> mapColumns = new HashMap<String, String[]>();
	public static Map<String, String> mapTempPath = new HashMap<String, String>();
	public static Map<String, String> mapTarPath = new HashMap<String, String>();
         //初始化模板和字段数组

	static{
		Properties prop = BaseUtil.getResource("config.properties");
		mapColumns.put("1",new String[]{"khmc","khbh","zzjgdmz","ssyjfh","ywjbh","qyxz","qygm","ssjtkhbh","ssjtkhmc","khszj_s","kggx","kgbl","khpj","khpjzt","khedzt","sfydsx_s","edkhfxxe","edzxfxxe","edybfxed","edcked","eddbed","edjjsxed","dkye","khhy","sfslgyshy","sfxxgs_s","sfxczf_s","zfrq","sfgmk_s","sftg_2015_s","wtgbbyy","zcc_2014_je","zcc_2015_je","zcc_2015_bh_s","zcc_2016_bh_s","yysr_2014_je","yysr_2015_je","yysr_2015_bh_s","yysr_2016_bh_s","lrze_2014_je","lrze_2015_je","lrze_2015_bh_s","lrze_2016_bh_s","zcfz_2014_je","zcfz_2015_je","zcfz_2015_bh_s","zcfz_2016_bh_s","jyxjl_2014_je","jyxjl_2015_je","jyxjl_2015_bh_s","jyxjl_2016_bh_s","csxpj_2015_s","csfxxe_2015","cwfxjl","t1_ywzfg_s","t1_ywzfg","t1_zygdg_s","t1_zygdg","t2_scjyz_s","t2_scjyz","t2_cnlyq_s","t2_cnlyq","t2_sfczz_s","t2_sfczz","t2_scczc_s","t2_scczc","t2_sfcym_s","t2_sfcym","t2_ynndq","t2_jqsfc_s","t2_jqsfc","t2_sfczg_s","t2_sfczg","t2_glqys_s","t2_glqys","t2_sfczd_s","t2_sfczd","t2_sfyzj_s","t2_sfyzj","t3_sfbaq_s","t3_sfbaq","t3_sfsqc_s","t3_sfsqc","t4_sfsjz_s","t4_sfsjz","t4_sfsjd_s","t4_sfsjd","t5_dwdbj","t5_sfczh_s","t5_sfczh","t5_sfsym_s","t5_sfsym","t5_sjdbq","t6_sfczt_s","t6_sfczt","t6_qyhqy_s","t6_qyhqy","t6_qtyhs_s","t6_qtyhs","t6_tysxs_s","t6_tysxs","t6_thsxz","t6_jkrtg","t6_sffsw_s","t6_sffsw","t7_qysfs_s","t7_qysfs","t7_qyfzr_s","t7_qyfzr","t7_qysfc_s","t7_qysfc","t7_sfypc_s","t7_sfypc","t7_sfzfy_s","t7_sfzfy","t7_sfyzd_s","t7_sfyzd","t8_sfyqt_s","t8_sfyqt","t9_fxzcjl_s","t9_fxzcjl","fhjcr","fhlxfs"} );
		mapColumns.put("2",new String[]{"khmc","khbh","zzjgdmz","ssyjfh","ywjbh","qygm","ssjtkhbh","khpj","khpjzt","khedzt","sfydsx_s","edkhfxxe","edzxfxxe","edybfxed","edcked","eddbed","edjjsxed","dkye","khhy","sfxczf_s","zfrq","sfgmk_s","ybyss_2013","ybyss_2014","ybyss_2015","zfxjj_2013","zfxjj_2014","zfxjj_2015","sjbzj_2013","sjbzj_2014","sjbzj_2015","ybyzc_2013","ybyzc_2014","ybyzc_2015","zfxzc_2013","zfxzc_2014","zfxzc_2015","zfxzw_2013","zfxzw_2014","zfxzw_2015","fzl_2013","fzl_2014","fzl_2015","zwl_2013","zwl_2014","zwl_2015","chl_2013","chl_2014","chl_2015","sczzg_2013","sczzg_2014","sczzg_2015","rjsczz_2013","rjsczz_2014","rjsczz_2015","sftg_2015_s","wtgbbyy","zcc_2014_je","zcc_2015_je","zcc_2015_bh_s","zcc_2016_bh_s","yysr_2014_je","yysr_2015_je","yysr_2015_bh_s","yysr_2016_bh_s","lrze_2014_je","lrze_2015_je","lrze_2015_bh_s","lrze_2016_bh_s","zcfz_2014_je","zcfz_2015_je","zcfz_2015_bh_s","zcfz_2016_bh_s","jyxjl_2014_je","jyxjl_2015_je","jyxjl_2015_bh_s","jyxjl_2016_bh_s","csxpj_2015_s","csfxxe_2015","cwfxjl_s","cwfxjl","t1_ptcj_s","t1_ptcj","t1_ptlx_s","t1_ptlx","t1_zfzct_s","t1_zfzct","t1_czbth_s","t1_czbth","t2_ynndq","t2_dwdbj","t2_zygdg_s","t2_zygdg","t2_sfczd_s","t2_sfczd","t2_sfyzj_s","t2_sfyzj","t2_sfsjz_s","t2_sfsjz","t2_sfsym_s","t2_sfsym","t3_sfczt_s","t3_sfczt","t3_qtyhs_s","t3_qtyhs","t3_thsxs_s","t3_thsxs","t3_jkrtg","t4_qysfs_s","t4_qysfs","t4_qysfc_s","t4_qysfc","t4_sfypc_s","t4_sfypc","t4_sfzfy_s","t4_sfzfy","t4_sfyzd_s","t4_sfyzd","t5_sfyqt_s","t5_sfyqt","t6_fxzcj_s","t6_fxzcj","fhjcr","fhlxfs"} );
//		mapColumns.put("3",new String[]{"khmc","khbh","ywjbh","ssjt","htbh","sxcp","dktxhy","bz","htje","dkye","htqq","htzq","hkfs","zcfl","zdbfs","t1_dzywl_s","t1_dzywl","t1_dywzs_s","t1_dywzs","t1_dyzyd_s","t1_dyzyd","t1_sfddz_s","t1_sfddz","t1_dqdzy","t1_bzr","t1_bzrpj","t1_bzrsf_s","t1_bzrsf","t1_bzrdb_s","t1_bzrdb","t1_sfjtn_s","t1_sfjtn","t1_sfczh_s","t1_sfczh","t1_dbhsx_s","t1_dbhsx","t2_htqd_s","t2_htqd","t2_zjyt_s","t2_zjyt","t2_scsph_s","t2_scsph","t2_tjls_s","t2_tjls","t2_dhjcb_s","t2_dhjcb","t2_zjhkj_s","t2_zjhkj","t2_xdywz_s","t2_xdywz","t2_whdkc_s","t2_whdkc","t2_qtsms","t2_xdglf_s","t2_xdglf","t3_sfzyh_t","t3_sfzyh","t3_hxqyj_s","t3_hxqyj","t3_jkryh_s","t3_jkryh","t3_whyhx_s","t3_whyhx","t3_dksfb_s","t3_dksfb","t3_hkzfl_s","t3_hkzfl","t3_sfsxd_s","t3_sfsxd","t3_yszkz_s","t3_yszkz","t3_sfzxx_s","t3_sfzxx","t3_fxzcj_s","t3_fxzcj","t4_xmztz","t4_edzxe","t4_zbjdw","t4_zxdj","t4_xsed_s","t4_xsed","t4_jgyq_s","t4_jgyq","t4_wgjd_s","t4_wgjd","t4_cbkz_s","t4_cbkz","t4_ysyq_s","t4_ysyq","t4_lryq_s","t4_lryq","t4_dqsfc_s","t4_dqsfc","t4_sffsk_s","t4_sffsk","t4_sfcxb_s","t4_sfcxb","t4_scxqs_s","t4_scxqs","t4_sfyqt_s","t4_sfyqt","t5_jyflt_s","t5_jyflt","t6_fxzcj_s","t6_fxzcj","fhjcr","fhlxfs"} );
		mapColumns.put("3",new String[]{"khmc","khbh","ywjbh","ssjt","htbh","sxcp","dktxhy","bz","htje","dkye","htqq","htzq","hkfs","zcfl","zdbfs","t1_dzywl_s","t1_dzywl","t1_dywzs_s","t1_dywzs","t1_dyzyd_s","t1_dyzyd","t1_sfddz_s","t1_sfddz","t1_dqdzy","t1_bzr","t1_bzrpj","t1_bzrsf_s","t1_bzrsf","t1_bzrdb_s","t1_bzrdb","t1_sfjtn_s","t1_sfjtn","t1_sfczh_s","t1_sfczh","t1_dbhsx_s","t1_dbhsx","t2_htqd_s","t2_htqd","t2_zjyt_s","t2_zjyt","t2_scsph_s","t2_scsph","t2_tjls_s","t2_tjls","t2_dhjcb_s","t2_dhjcb","t2_zjhkj_s","t2_zjhkj","t2_xdywz_s","t2_xdywz","t2_whdkc_s","t2_whdkc","t2_qtsms","t2_xdglf_s","t2_xdglf","t3_sfzyh_t","t3_sfzyh","t3_hxqyj_s","t3_hxqyj","t3_jkryh_s","t3_jkryh","t3_whyhx_s","t3_whyhx","t3_dksfb_s","t3_dksfb","t3_hkzfl_s","t3_hkzfl","t3_sfsxd_s","t3_sfsxd","t3_yszkz_s","t3_yszkz","t3_sfzxx_s","t3_sfzxx","t3_fxzcj_s","t3_fxzcj","t4_xmztz","t4_edzxe","t4_zbjdw","t4_zxdj","t4_xsed_s","t4_xsed","t4_jgyq_s","t4_jgyq","t4_wgjd_s","t4_wgjd","t4_cbkz_s","t4_cbkz","t4_ysyq_s","t4_ysyq","t4_lryq_s","t4_lryq","t4_dqsfc_s","t4_dqsfc","t4_sffsk_s","t4_sffsk","t4_sfcxb_s","t4_sfcxb","t4_scxqs_s","t4_scxqs","t4_fxzcj_s","t4_fxzcj","t4_sfyqt_s","t4_sfyqt","t5_jyflt_s","t5_jyflt","t6_fxzcj_s","t6_fxzcj","fhjcr","fhlxfs"});
		mapColumns.put("4",new String[]{"khmc","khbh","ywjbh","sxcp","htbh","bz","dktxhy","dkye","htje","htqq","htzq","zcfl","hkfs","zdbfs","t1_sfyzf_s","t1_sfyzf","t1_zfdbw_s","t1_zfdbw","t1_nrzfz_s","t1_nrzfz","t2_xmmc","t2_xmztz","t2_zbjdw","t2_zxdj","t2_xmsx_s","t2_xmsx","t2_xmdkt_s","t2_xmdkt","t2_xmxgs_s","t2_xmxgs","t2_sfbaq_s","t2_sfbaq","t2_xmwgq_s","t2_xmwgq","t2_xmcbk_s","t2_xmcbk","t2_xmzyl_s","t2_xmzyl","t2_xmyqc","t2_czczz","t2_sfyqt_s","t2_sfyqt","t2_xmczf_s","t2_xmczf","t3_dzywl_s","t3_dzywl","t3_dywzs_s","t3_dywzs","t3_dyzyd_s","t3_dyzyd","t3_sfddz_s","t3_sfddz","t3_dqdzy","t3_bzr","t3_bzrpj","t3_bzrdb_s","t3_bzrdb","t3_dbhsx_s","t3_dbhsx","t4_htqd_s","t4_htqd","t4_zjyt_s","t4_zjyt","t4_scsph_s","t4_scsph","t4_tjls_s","t4_tjls","t4_dhjcb_s","t4_dhjcb","t4_zjhkj_s","t4_zjhkj","t4_xdywz_s","t4_xdywz","t4_dkczj_s","t4_dkczj","t4_qtsms","t4_xdglf_s","t4_xdglf","t4_sfyqt_s","t4_sfyqt","t4_jyflt_s","t4_jyflt","t4_fxzcj_s","t4_fxzcj","fhjcr","fhlxfs"} );
		mapColumns.put("5",new String[]{"jt_mc","jtglh","jtglh_s","sfhbj_s","sfqtj_s","zzjgdmz","jtkhp","edkhfxxe","edybfxed","edcked","edyxr","khbh","khmc","jtcjlx","kggx","kgbl","khpj","ybfxed","gsxd","xmdk","gyljr","pjcrxyzhbh","qt","zq","tytz","lc","xj","csskh_s","csskh","sftg_2015_s","wtgbbyy","zcc_2014_je","zcc_2015_je","zcc_2015_bh_s","zcc_2016_bh_s","yysr_2014_je","yysr_2015_je","yysr_2015_bh_s","yysr_2016_bh_s","lrze_2014_je","lrze_2015_je","lrze_2015_bh_s","lrze_2016_bh_s","zcfz_2014_je","zcfz_2015_je","zcfz_2015_bh_s","zcfz_2016_bh_s","jyxjl_2014_je","jyxjl_2015_je","jyxjl_2015_bh_s","jyxjl_2016_bh_s","csxpj_2015_s","csfxxe_2015","cwfxjl","t1_ywzfg_s","t1_ywzfg","t1_zygdg_s","t1_zygdg","t1_sfbaq_s","t1_sfbaq","t1_sfsqc_s","t1_sfsqc","t1_sfsgd_s","t1_sfsgd","t1_sfsjz_s","t1_sfsjz","t1_dwdbj","t1_tysxs_s","t1_tysxs","t1_sfczt_s","t1_sfczt","t1_sffsw_s","t1_sffsw","t1_sfyzd_s","t1_sfyzd","t1_sfyqt_s","t1_sfyqt","t1_jyfxj_s","t1_jyfxj","t2_whjts_t","t2_whjts","t2_sfczy_s","t2_sfczy","t2_sfczd_s","t2_sfczd","t2_sfczg_s","t2_sfczg","t2_cyhbx_s","t2_cyhbx","t2_jtnsj_t","t2_jtnsj","t2_whdks_t","t2_whdks","t2_jtdhx_s","t2_jtdhx","t2_sfczz_s","t2_sfczz","t2_sfczj_s","t2_sfczj","t2_sfyqt_s","t2_sfyqt","t2_jtfxj_s","t2_jtfxj","t2_fxzcy_s","t2_fxzcy","fhjcr","fhlxfs"} );
		mapTempPath.put("1", prop.getProperty("excelStandard1"));
		mapTempPath.put("2", prop.getProperty("excelStandard2"));
		mapTempPath.put("3", prop.getProperty("excelStandard3"));
		mapTempPath.put("4", prop.getProperty("excelStandard4"));
		mapTempPath.put("5", prop.getProperty("excelStandard5"));
		mapTarPath.put("1", prop.getProperty("excelExpPath"));
		mapTarPath.put("2", prop.getProperty("excelExpPath"));
		mapTarPath.put("3", prop.getProperty("excelExpPath"));
		mapTarPath.put("4", prop.getProperty("excelExpPath"));
		mapTarPath.put("5", prop.getProperty("excelExpPath"));
	}
//通过excel模板，以及结果集，将数据导入顺序到excel报表中

	public static <T> String exportReport(String templeteType,Collection<T> dataset,String fileName){
		log.info("---------------------生成excel报表开始---------------------");
		String[] columns = mapColumns.get(templeteType);
		String tempFilePath = codeTrans(mapTempPath.get(templeteType));
		String targetDirPath = codeTrans(mapTarPath.get(templeteType));
		if(fileName == null || "".equals(fileName)){
			log.info("生成excel文件名不能为空");
			return null;
		}
		if(!fileName.endsWith(".xls")){
			log.info("文件名不是xls结尾，不进行导出excel操作");
			return null;
		}
		String targetFilePath = targetDirPath + File.separator+fileName;
		File file = new File(targetDirPath);
		if(!file.exists()){
			file.mkdirs();
		}
		exprotExcel(dataset, columns, tempFilePath, targetFilePath);
		log.info("数据dataset-->"+dataset.size());
		log.info("---------------------生成excel报表结束---------------------");
		log.info(targetFilePath);
		return targetFilePath;
	}
	
	
	public static <T> void exprotExcel(Collection<T> dataset,String[] columns,
			String tempFilePath,String targetFilePath){
		exportExcel("测试", null, dataset, "yyyy-MM-dd", columns, tempFilePath, targetFilePath);
//		exportExcels("测试", null, dataset, "yyyy-MM-dd", columns, tempFilePath, targetFilePath);
	}
	*/
	/**
	 * 读取模板excel，将数据写入
	 * @param tiltle 标题
	 * @param headers 标题头
	 * @param dataset 数据集合
	 * @param pattern 日期格式
	 * @param columns 模板数据字段（顺序）
	 * @param tempFilePath 模板excel路径
	 * @param targetFilePath 另存模板excel路径
	 */
/*	public static <T> void exportExcel(String tiltle,String[] headers,Collection<T> dataset,
			String pattern,String[] columns,String tempFilePath,String targetFilePath){
		if(tempFilePath == null || "".equals(tempFilePath)){
			log.info("未获取到模板路径："+tempFilePath);
		}
		if(targetFilePath == null || "".equals(targetFilePath)){
			log.info("未获取到输入文件路径："+targetFilePath);
		}
		
		InputStream is = null;
		HSSFWorkbook workbook = null;
		FileOutputStream out = null;
		try {
			is = new FileInputStream(new File(tempFilePath));
			workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			if(tiltle != null){
				//修改sheet页名称
				workbook.setSheetName(0, tiltle);
				log.info("sheet名称：-->"+tiltle);
			}
			sheet.setDefaultColumnWidth((short)15);
	         
	          //生成一个样式
	          HSSFCellStyle style = workbook.createCellStyle();
	         
	          //设置这些样式
	          style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	          style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	          style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	          style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	         
	          //生成一个字体
	          HSSFFont font = workbook.createFont();
	          font.setColor(HSSFColor.VIOLET.index);
	          font.setFontHeightInPoints((short)12);
	          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	         
	          //把字体应用到当前的样式
	          style.setFont(font);
	         
	          //生成并设置另一种样式
	          HSSFCellStyle style2 = workbook.createCellStyle();
	          style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	          style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	          style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	          style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	          style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	          style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	          //生成另一个字体
	          HSSFFont font2 = workbook.createFont();
	          font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	          style2.setFont(font2);
	         
	          //声明一个画图的顶级管理器
	          HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	         
	          //定义注释的大小和位置,详见文档
	          HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0,0,0,(short)4,2,(short)6,5));
	          //设置注释内容
	          comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
	          
	          //产生表格标题行
	          HSSFRow row = sheet.createRow(0);
	          if(headers == null){
	        	  log.info("无标题行-->默认标题格式");
	          }else {
	        	  for (short i = 0; i < headers.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell.setCellValue(text); 
				}
	          }
	          
	          //遍历集合数据，产生数据行******数据导入excel
	          Iterator<T> it = dataset.iterator();
	          int index = sheet.getLastRowNum();//读取excel模板最后行，开始导入数据
	          log.info("模板lastRowNum:"+index);
	          while(it.hasNext()){
	        	  index++;
	        	  row = sheet.createRow(index);
	        	  T t = (T)it.next();
	        	  //利用反射机制，根据属性列表，动态将数据写入excel中
	        	  for (int i = 0; i < columns.length; i++) {
//					log.info("columns"+i+":-->"+columns[i]);
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style2);
					String fieldName = columns[i];
					String getMethodName = "get" 
							+ fieldName.substring(0,1).toUpperCase() 
							+ fieldName.substring(1);
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(t, new Object[]{});
//					log.info(fieldName+":-->"+value);
					if(value ==  null){
						continue;
					}
					//判断值的类型后进行强行类型转换
					String textValue = null;
					if(value instanceof Boolean){
						boolean bValue = (Boolean) value;
						textValue = "男";
						if(!bValue){
							textValue = "女";
						}
					}else if(value instanceof Date){
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					}else if(value instanceof byte[]){
						//有图片时，设置行高为60px
						row.setHeightInPoints(60);
						//设置图片所在列宽80px，注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7*80));
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = 
								new HSSFClientAnchor(0,0,1023,255,(short)6,index,(short)6,index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, 
								workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					}else {
						//其他数据类型都当做字符串简单处理
						textValue = value.toString();
					}
					//如果不是图片数据，就利用正则表达式判断是否全部由数据组成
					if(textValue != null){
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if(matcher.matches()){
							//是数字当做double处理
							cell.setCellValue(Double.parseDouble(textValue));
						}else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				}
	          }
	          //将excel数据写入目标文件流中
	          out = new FileOutputStream(new File(targetFilePath));
	          workbook.write(out);
	          log.info("数据成功写入excel中-->excel数据写入完成");
		} catch (FileNotFoundException e) {
			log.error("指定路径不存在", e);
		} catch (IOException e) {
			log.error("读写文件失败", e);
		} catch (Exception e) {
			log.error("excel写入失败", e);
		} finally{
			try {
				//资源清理
				if(is != null){
					is.close();
				}
				if(out != null){
					out.close();
				}
				if(workbook != null){
					workbook.close();
				}
			} catch (Exception e2) {
				log.error("关闭IO流失败", e2);
			}
			
		}
		
	}
	*/
	
	/**
	 * 限制每个excel的数据量，然后导出为多个excel
	 * @param tiltle
	 * @param headers
	 * @param dataset
	 * @param pattern
	 * @param columns
	 * @param tempFilePath
	 * @param targetFilePath
	 */
	/*
	public static <T> void exportExcels(String tiltle,String[] headers,Collection<T> dataset,
			String pattern,String[] columns,String tempFilePath,String targetFilePath){
		int dataSize = dataset.size();
		int dataNum = 50;
		if(dataSize < dataNum){
			exportExcel(tiltle, headers, dataset, pattern, columns, tempFilePath, targetFilePath);
		}else {
			int n = 0 ; 
			int mod = dataSize % dataNum;
			if(mod == 0){
				n = dataSize / dataNum;
			}else {
				n = dataSize / dataNum + 1;
			}
			log.info("dataset instanceof List:"+(dataset instanceof List));
			List<T> datas = new ArrayList<T>();
			if(dataset instanceof List){
				datas = (List<T>)dataset;
				log.info(datas.size());
				for (int i = 0; i < n; i++) {
					List<T> list = new ArrayList<T>();
					if(mod != 0 && i == n-1){
						list = datas.subList(i*dataNum, i*dataNum+mod);
					}else {
						list = datas.subList(i*dataNum, (i+1)*dataNum);
					}
					String path = targetFilePath.substring(0,targetFilePath.length()-4)+"_"+i+".xls";
					exportExcel(tiltle, headers, list, pattern, columns, tempFilePath, path);
				}
			}else {
				log.info("传入非list结果集，暂不支持该处理方式");
			}
			
		}
	}
	
	*/
	/**
	 * 测试
	 * @param args
	 */
/*	public static void main(String[] args) {
		String tempFilePath = mapTempPath.get("1");
		System.out.println(codeTrans(tempFilePath));
		List<ExcelObjectModel> dataset = new ArrayList<ExcelObjectModel>();
		ExcelObjectModel excelObjectModel = new ExcelObjectModel();
		excelObjectModel.setAb("dfasdf");
		excelObjectModel.setAc("1111");
		excelObjectModel.setAb("dbd");
		dataset.add(excelObjectModel);
		ExcelObjectModel excelObjectModel1 = new ExcelObjectModel();
		excelObjectModel1.setAb("dfasdffdsaf");
		excelObjectModel1.setAc("11fdsfasd11");
		excelObjectModel1.setAb("dbddfadfadsfads");
		dataset.add(excelObjectModel1);
		String[] columns = {"ab","ac","ad"};
		exprotExcel(dataset, columns, 
				"C:\\Users\\Lenovo\\Desktop\\重点公司客户风险排查汇总表2.0-20160824.xls", 
				"C:\\Users\\Lenovo\\Desktop\\abc.xls");
	}
*/	
	/**
	 * 编码转换
	 * @param str
	 * @return
	 */
	/*
	@SuppressWarnings("unused")
	private static String codeTrans(String str){
		try {
			str = new String(str.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("编码转换失败", e);
		}
		return str;
	}
	*/
}
