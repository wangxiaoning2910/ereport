package com.ytincl.ereport.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.model.BaseModel;
import com.ytincl.ereport.model.ToBeUploaded;
import com.ytincl.ereport.model.pbsmr_busis;
import com.ytincl.ereport.model.pbsmr_entrustunits;
import com.ytincl.ereport.model.pbsmr_insts;
import com.ytincl.ereport.pojo.SavingsDifferenceType;
import com.ytincl.ereport.pojo.SavingsNetAmount;
import com.ytincl.ereport.pojo.SavingsNetAmount2;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_entrustunit;
import com.ytincl.ereport.pojo.pbsmr_inst;
import com.ytincl.ereport.service.UpLoadFileService;
import com.ytincl.ereport.util.FileUtil;
import com.ytincl.ereport.util.FormatConversion;
import com.ytincl.ereport.util.ReadExcel;
import com.ytincl.ereport.util.XMLManager;

@Controller
public class upLoadFile {
	@Autowired
	private UpLoadFileService tobeuploaded;
	private static Logger logger = LoggerFactory.getLogger(upLoadFile.class);
	//�����ϴ��ļ�
	@RequestMapping(value="/view/uploadFile.do")
	@ResponseBody
	public String uploadFile(@RequestParam("pic")CommonsMultipartFile pic,HttpServletRequest req,
			HttpServletResponse response) throws IOException{
		logger.debug("===========================UpLoadFile=================");
		//�����ļ�����ı���·��
		String filePath = req.getSession().getServletContext().getRealPath("/uploadFiles/");
		String fileName = pic.getOriginalFilename();
		//Ϊ�˱����ļ����ظ������ļ���ǰ��UUID
		String uuid = UUID.randomUUID().toString().replace("-","");
		String uuidFileName = uuid + fileName;
	
		//���ļ����浽������
		FileUtil.upFile(pic.getInputStream(), uuidFileName, filePath);
		return uuidFileName;

	}
	
	
	@RequestMapping(value="/view/uploadFile1.do")
	@ResponseBody
	public BaseModel uploadFile1(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest req,
			HttpServletResponse response) throws IOException, ParseException{
		//�����ļ�����ı���·��
		String filePath = req.getSession().getServletContext().getRealPath("/uploadFiles/");
		String fileName = file.getOriginalFilename();
		//Ϊ�˱����ļ����ظ������ļ���ǰ��UUID
		String uuid = UUID.randomUUID().toString().replace("-","");
		String uuidFileName = uuid + fileName;
		//���ļ����浽������
		FileUtil.upFile(file.getInputStream(), uuidFileName, filePath);
		//��ȡ��ȡExcel����
		Map<String,String> map = XMLManager.getRuelRegulation(req, fileName.split("[.]")[0]);
		ReadExcel re = new ReadExcel();
		List<String[]> list = re.readXls(filePath+"\\"+uuidFileName,map);
		String[] strs;
		SavingsDifferenceType sdt = null;
		SavingsNetAmount sna = null;
		SavingsNetAmount2 sna2 = null;
		String aname = map.get("name");
		Date currentDate = new Date(System.currentTimeMillis());
		String currentDatestr = currentDate.toString();
		String date = currentDatestr.split("-")[0]+""+currentDatestr.split("-")[1];
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("����-������ȡ-������=111");
		list1.add("����-������ȡ-����=112");
		list1.add("����-������ȡ-һ��=113");
		list1.add("����-������ȡ-����=114");
		list1.add("����-������ȡ-����=115");
		list1.add("����-������ȡ-����=116");
		list1.add("����-������ȡ-����=117");
		list1.add("����-������ȡ-С��=118");
		
		list1.add("����-������ȡЭ����-������=121");
		list1.add("����-������ȡЭ����-����=122");
		list1.add("����-������ȡЭ����-һ��=123");
		list1.add("����-������ȡЭ����-����=124");
		list1.add("����-������ȡЭ����-����=125");
		list1.add("����-������ȡЭ����-����=126");
		list1.add("����-������ȡЭ����-С��=127");
		
		
		list1.add("����-�����ȡ-һ��=131");
		list1.add("����-�����ȡ-����=132");
		list1.add("����-�����ȡ-����=133");
		list1.add("����-�����ȡ-С��=134");
		
		
		list1.add("����-�汾ȡϢ-һ��=141");
		list1.add("����-�汾ȡϢ-����=142");
		list1.add("����-�汾ȡϢ-����=143");
		list1.add("����-�汾ȡϢ-С��=144");
		
		list1.add("����-������ȡ-һ��=151");
		list1.add("����-������ȡ-����=152");
		list1.add("����-������ȡ-����=153");
		list1.add("����-������ȡ-С��=154");
		
		list1.add("����-�����-��=161");
		list1.add("����-��ֵ����-��=171");
		list1.add("����-���ںϼ�-��=181");
		list1.add("����-��-��=211");
		list1.add("��������-��-��=311");
		list1.add("֪ͨ���-��-��=411");
		list1.add("�ܼ�-��-��=511");
		
		
		if (list != null) {
			for (int i = 0;i<list.size();i++) {
				strs = list.get(i);
				if(null != aname && aname.equals("����ִ������")){
					sdt = new SavingsDifferenceType();
					String str = list1.get(i);
					String[] strs3 = str.split("=");
					String a = strs3[0];
					String b = strs3[1];//����
					String[] strs2 = a.split("-");//ǰ����
					sdt.setDate(date);
					sdt.setSavetype_no(Integer.parseInt(b));
					sdt.setSavetype_name(strs2[0]);
					sdt.setSaveway_no(Integer.parseInt(b));
					sdt.setSaveway_name(strs2[1]);
					sdt.setSavetime_no(Integer.parseInt(b));
					sdt.setSavetime_name(strs2[2]);
					sdt.setBalance_number(FormatConversion.string2Int(strs[0]));
					sdt.setBalance_money(FormatConversion.string2Double(strs[1]));
					sdt.setSave_compare(FormatConversion.string2Float(strs[2]));
					sdt.setOrder_add(FormatConversion.string2Float(strs[3]));
					sdt.setBalance_number1(FormatConversion.string2Int(strs[4]));
					sdt.setBalance_money1(FormatConversion.string2Double(strs[5]));
					sdt.setSave_compare1(FormatConversion.string2Float(strs[6]));
					sdt.setOrder_add1(FormatConversion.string2Float(strs[7]));
					tobeuploaded.insertSavingsDifferenceType(sdt);
					
				}else if(null != aname && aname.equals("�������������__����")){
					sna = new SavingsNetAmount();
					sna.setUnit_no(i);
					sna.setDate(date);
					sna.setUnit_name(strs[0]);
					sna.setTime_money(FormatConversion.string2Double(strs[1]));
					sna.setCurrentbalance_money(FormatConversion.string2Float(strs[2]));
					sna.setTime_money1(FormatConversion.string2Double(strs[3]));
					sna.setTime_money2(FormatConversion.string2Double(strs[4]));
					sna.setTime_money3(FormatConversion.string2Double(strs[5]));
					sna.setTime_money4(FormatConversion.string2Double(strs[6]));
					sna.setTime_money5(FormatConversion.string2Double(strs[7]));
					sna.setTime_money6(FormatConversion.string2Double(strs[8]));
					sna.setTime_money7(FormatConversion.string2Double(strs[9]));
					sna.setTime_money8(FormatConversion.string2Double(strs[10]));
					sna.setTime_money9(FormatConversion.string2Double(strs[11]));
					sna.setTime_money10(FormatConversion.string2Double(strs[12]));
					sna.setTime_money11(FormatConversion.string2Double(strs[13]));
					sna.setTime_money12(FormatConversion.string2Double(strs[14]));
					sna.setCurrentbalance_money1(FormatConversion.string2Float(strs[15]));
					sna.setTime_money13(FormatConversion.string2Double(strs[16]));
					sna.setTime_money14(FormatConversion.string2Double(strs[17]));
					sna.setTime_money15(FormatConversion.string2Double(strs[18]));
					sna.setTime_money16(FormatConversion.string2Double(strs[19]));
					sna.setTime_money17(FormatConversion.string2Double(strs[20]));
					sna.setTime_money18(FormatConversion.string2Double(strs[21]));
					sna.setTime_money19(FormatConversion.string2Double(strs[22]));
					sna.setTime_money20(FormatConversion.string2Double(strs[23]));
					sna.setTime_money21(FormatConversion.string2Double(strs[24]));
					sna.setTime_money22(FormatConversion.string2Double(strs[25]));
					sna.setTime_money23(FormatConversion.string2Double(strs[26]));
					sna.setCurrentbalance_money2(FormatConversion.string2Float(strs[27]));
					sna.setCurrentbalance_money3(FormatConversion.string2Float(strs[28]));
					sna.setRanking_no(strs[29]);
					sna.setCurrentbalance_money4(FormatConversion.string2Float(strs[30]));
					sna.setCurrentbalance_money5(FormatConversion.string2Float(strs[31]));
					sna.setRanking_no1(strs[32]);
					tobeuploaded.insertSavingsNetAmount(sna);
				
				}else if(null != aname && aname.equals("�������������__����")){
					sna2 = new SavingsNetAmount2();
					sna2.setDate(date);
					sna2.setUnit_no(i);
					sna2.setUnit_name(strs[0]);
					sna2.setTime_money(FormatConversion.string2Double(strs[1]));
					sna2.setCurrentbalance_money(FormatConversion.string2Float(strs[2]));
					sna2.setTime_money1(FormatConversion.string2Double(strs[3]));
					sna2.setTime_money2(FormatConversion.string2Double(strs[4]));
					sna2.setTime_money3(FormatConversion.string2Double(strs[5]));
					sna2.setTime_money4(FormatConversion.string2Double(strs[6]));
					sna2.setTime_money5(FormatConversion.string2Double(strs[7]));
					sna2.setTime_money6(FormatConversion.string2Double(strs[8]));
					sna2.setTime_money7(FormatConversion.string2Double(strs[9]));
					sna2.setRanking_no(strs[10]);
					sna2.setTime_money8(FormatConversion.string2Double(strs[11]));
					sna2.setTime_money9(FormatConversion.string2Double(strs[12]));
					sna2.setRanking_no1(strs[13]);
					sna2.setTime_money10(FormatConversion.string2Double(strs[14]));
					sna2.setTime_money11(FormatConversion.string2Double(strs[15]));
					sna2.setCurrentbalance_money1(FormatConversion.string2Float(strs[16]));
					sna2.setTime_money12(FormatConversion.string2Double(strs[17]));
					sna2.setTime_money13(FormatConversion.string2Double(strs[18]));
					sna2.setTime_money14(FormatConversion.string2Double(strs[19]));
					sna2.setTime_money15(FormatConversion.string2Double(strs[20]));
					sna2.setTime_money16(FormatConversion.string2Double(strs[21]));
					sna2.setTime_money17(FormatConversion.string2Double(strs[22]));
					sna2.setTime_money18(FormatConversion.string2Double(strs[23]));
					sna2.setRanking_no2(strs[24]);
					sna2.setTime_money19(FormatConversion.string2Double(strs[25]));
					sna2.setTime_money20(FormatConversion.string2Double(strs[26]));
					sna2.setRanking_no2(strs[27]);
					sna2.setTime_money21(FormatConversion.string2Double(strs[28]));
					//�������ݿ�
					tobeuploaded.insertSavingsNetAmount2(sna2);
				}else if(null != aname && aname.equals("�������������__����")){
					
				}
				
				
				
			}
		}
		//���²���״̬
		String[] sss = fileName.split("\\.");
		String fn = sss[0];
		UpLoadFile ulf = new UpLoadFile();
		if(fn.contains("__")){
			fn = fn.replaceAll("__", "--");
		}
		ulf.setName(fn);
		ulf.setQueryDate(date);
		tobeuploaded.updateStatus(ulf);
		return new BaseModel("000000");

	}
	@RequestMapping(value="/view/getToBeUpLoaded.do")
	@ResponseBody
	public ToBeUploaded getToBeUpLoaded(
			@RequestParam(value="ymounth", required=true) String querydate,
			HttpServletRequest request,
			HttpServletResponse response){
		ToBeUploaded tbu = new ToBeUploaded();
		ArrayList<UpLoadFile> list;
		list = (ArrayList<UpLoadFile>) tobeuploaded.getToBeUpLoadedList(querydate);
		tbu.setList(list);
		return tbu;
	}
	@RequestMapping(value="/view/test.do")
	public void resolveReport(
			@RequestParam("file")CommonsMultipartFile file,
			HttpServletRequest request) throws IOException{
		
		String filePath = request.getSession().getServletContext().getRealPath("/uploadFiles/");
		String fname = file.getOriginalFilename();
		//Ϊ�˱����ļ����ظ������ļ���ǰ��UUID
		String uuid = UUID.randomUUID().toString().replace("-","");
		String uuidFileName = uuid + fname;
		//���ļ����浽������
		FileUtil.upFile(file.getInputStream(), uuidFileName, filePath);
		
		if(fname.contains("������")){
			fname = "���ո�ҵ��ͳ���±�-������";
		}else if(fname.contains("��ҵ��")){
			fname = "���ո�ҵ��ͳ���±�-��ҵ��";
		}else if(fname.contains("��ί�е�λ")){
			fname = "���ո�ҵ��ͳ���±�-��ί�е�λ";
		}
		//��ȡ��ȡExcel����
		Map<String,String> map = XMLManager.getRuelRegulation(request, fname.split("[.]")[0]);
		ReadExcel re = new ReadExcel();
		File f = new File(filePath+"\\"+uuidFileName);
		try {
			if(null != map){
				String aname = map.get("name");
				Map<String,List<String[]>> resultmap = re.readXls2norm(f, map);
				if(aname.contains("��ҵ��")){
					pbsmr_busis pbs = new pbsmr_busis();
					pbsmr_busi pb;
					List<pbsmr_busi> list = new ArrayList<pbsmr_busi>();
					ArrayList<String[]> looplist = (ArrayList<String[]>) resultmap.get("loopdata");
					ArrayList<String[]> singlelist = (ArrayList<String[]>) resultmap.get("sl");
					ArrayList<String[]> singlelist1 = (ArrayList<String[]>) resultmap.get("sl1");
					String[] loopdatas;
					String[] sl = singlelist1.get(0);
					String reportname = singlelist.get(0)[0];//��������
					String codename = singlelist.get(0)[1];//ͳ�ƻ�������/����
					String yearmonth = sl[0];//ͳ������
					String cstype = sl[1];//ͳ�Ʒ�ʽ
					String dottype = sl[2];//��������
					String paytype = sl[3];//֧����ʽ
					String Contains = sl[4];//�Ƿ�����¼�����
					for (int i = 0; i < looplist.size(); i++) {
						//ÿһ��
						loopdatas = looplist.get(i);
						pb = new pbsmr_busi();
						pb.setReportName(reportname);
						pb.setStatisticsmechanism(codename);
						pb.setStatisticsdate(yearmonth);
						pb.setStatisticstype(cstype);
						pb.setDotproperties(dottype);
						pb.setPaytype(paytype);
						pb.setContainsubordinateinst(Contains);
						/**ÿһ��**/
						pb.setBusicode(loopdatas[0]);
						pb.setBusiname(loopdatas[1]);
						pb.setTransamount_month(loopdatas[2]);
						pb.setTransamount_year(loopdatas[3]);
						pb.setTransmoney_month(loopdatas[4]);
						pb.setTransmoney_year(loopdatas[5]);
						pb.setCorrectnum_month(loopdatas[6]);
						pb.setCorrectnum_year(loopdatas[7]);
						pb.setCorrectmoney_month(loopdatas[8]);
						pb.setCorrectmoney_year(loopdatas[9]);
						pb.setCancelamount_month(loopdatas[10]);
						pb.setCancelamount_year(loopdatas[11]);
						pb.setCancelmoney_month(loopdatas[12]);
						pb.setCancelmoney_year(loopdatas[13]);
						pb.setTranstotalnum_month(loopdatas[14]);
						pb.setTranstotalnum_year(loopdatas[15]);
						pb.setTranstotalmoney_month(loopdatas[16]);
						pb.setTranstotalmoney_year(loopdatas[17]);
						list.add(pb);
					}
					pbs.setList(list);
					//�������ݿ�
					pbsmr_busi insertpb;
					for(int i = 0;i<pbs.getList().size();i++){
						insertpb = pbs.getList().get(i);
						int insertresult = tobeuploaded.insertNormData(insertpb);

					}
				}else if(aname.contains("������")){
					pbsmr_inst pi;
					pbsmr_insts pis = new pbsmr_insts();
					List<pbsmr_inst> list = new ArrayList<pbsmr_inst>();
					ArrayList<String[]> looplist = (ArrayList<String[]>) resultmap.get("loopdata");
					ArrayList<String[]> singlelist = (ArrayList<String[]>) resultmap.get("sl");
					ArrayList<String[]> singlelist1 = (ArrayList<String[]>) resultmap.get("sl1");
					String reportname = singlelist.get(0)[0];//��������
					String codename = singlelist.get(0)[1];//ͳ�ƻ�������/����
					
					String[] sl = singlelist1.get(0);
					String[] loopdatas;
					String date = sl[0];//ͳ������
					String type = sl[1];//ͳ�Ʒ�ʽ
					String dot = sl[2];//��������
					String paytype = sl[3];//֧����ʽ
					String baohan = sl[4];//�Ƿ�����¼�����
					String busicode = sl[5];
					for (int i = 0; i < looplist.size(); i++) {
						loopdatas = looplist.get(i);
						pi = new pbsmr_inst();
						pi.setReportName(reportname);
						pi.setStatisticsmechanism(codename);
						pi.setStatisticsdate(date);
						pi.setStatisticstype(type);
						pi.setDotproperties(dot);
						pi.setPaytype(paytype);
						pi.setContainsubordinateinst(baohan);
						pi.setBusicode(busicode);
						
						
						pi.setInstcode(loopdatas[0]);
						pi.setInstname(loopdatas[1]);
						pi.setTransamount_month(loopdatas[2]);
						pi.setTransamount_year(loopdatas[3]);
						pi.setTransmoney_month(loopdatas[4]);
						pi.setTransmoney_year(loopdatas[5]);
						pi.setCorrectnum_month(loopdatas[6]);
						pi.setCorrectnum_year(loopdatas[7]);
						pi.setCorrectmoney_month(loopdatas[8]);
						pi.setCorrectmoney_year(loopdatas[9]);
						pi.setCancelamount_month(loopdatas[10]);
						pi.setCancelamount_year(loopdatas[11]);
						pi.setCancelmoney_month(loopdatas[12]);
						pi.setCancelmoney_year(loopdatas[13]);
						pi.setTranstotalnum_month(loopdatas[14]);
						pi.setTranstotalnum_year(loopdatas[15]);
						pi.setTranstotalmoney_month(loopdatas[16]);
						pi.setTranstotalmoney_year(loopdatas[17]);
						list.add(pi);
					}
					pis.setList(list);
					//�������ݿ�
					pbsmr_inst insertpb;
					for(int i = 0;i<pis.getList().size();i++){
						insertpb = pis.getList().get(i);
						int insertresult = tobeuploaded.insertNormData(insertpb);

					}
					
				}else if(aname.contains("��ί�е�λ")){
					pbsmr_entrustunit pe;
					pbsmr_entrustunits pes = new pbsmr_entrustunits();
					List<pbsmr_entrustunit> list = new ArrayList<pbsmr_entrustunit>();
					ArrayList<String[]> looplist = (ArrayList<String[]>) resultmap.get("loopdata");
					ArrayList<String[]> singlelist = (ArrayList<String[]>) resultmap.get("sl");
					ArrayList<String[]> singlelist1 = (ArrayList<String[]>) resultmap.get("sl1");
					String reportname = singlelist.get(0)[0];//��������
					String codename = singlelist.get(0)[1];//ͳ�ƻ�������/����
					
					String[] sl = singlelist1.get(0);
					String[] loopdatas;
					String date = sl[0];//ͳ������
					String type = sl[1];//ͳ�Ʒ�ʽ
					String dot = sl[2];//��������
					String paytype = sl[3];//֧����ʽ
					String baohan = sl[4];//�Ƿ�����¼�����
					for (int i = 0; i < looplist.size(); i++) {
						loopdatas = looplist.get(i);
						pe = new pbsmr_entrustunit();
						pe.setReportName(reportname);
						pe.setStatisticsmechanism(codename);
						pe.setStatisticsdate(date);
						pe.setStatisticstype(type);
						pe.setDotproperties(dot);
						pe.setPaytype(paytype);
						pe.setContainsubordinateinst(baohan);
						
						
						pe.setMerchid(loopdatas[0]);
						pe.setMerchname(loopdatas[1]);
						pe.setBusiname(loopdatas[2]);
						pe.setTransamount_month(loopdatas[3]);
						pe.setTransamount_year(loopdatas[4]);
						pe.setTransmoney_month(loopdatas[5]);
						pe.setTransmoney_year(loopdatas[6]);
						pe.setCorrectnum_month(loopdatas[7]);
						pe.setCorrectnum_year(loopdatas[8]);
						pe.setCorrectmoney_month(loopdatas[9]);
						pe.setCorrectmoney_year(loopdatas[10]);
						pe.setCancelamount_month(loopdatas[11]);
						pe.setCancelamount_year(loopdatas[12]);
						pe.setCancelmoney_month(loopdatas[13]);
						pe.setCancelmoney_year(loopdatas[14]);
						pe.setTranstotalnum_month(loopdatas[15]);
						pe.setTranstotalnum_year(loopdatas[16]);
						pe.setTranstotalmoney_month(loopdatas[17]);
						pe.setTranstotalmoney_year(loopdatas[18]);
						list.add(pe);
					}
					pes.setList(list);
					//�������ݿ�
					pbsmr_entrustunit insertpe;
					for(int i = 0;i<pes.getList().size();i++){
						insertpe = pes.getList().get(i);
						int insertresult = tobeuploaded.insertNormData(insertpe);
					}
				}
			}else{
				logger.debug("==============map is null==================");
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
