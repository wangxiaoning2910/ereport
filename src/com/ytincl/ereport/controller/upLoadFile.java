package com.ytincl.ereport.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.ytincl.ereport.pojo.SavingsDifferenceType;
import com.ytincl.ereport.pojo.SavingsNetAmount;
import com.ytincl.ereport.pojo.SavingsNetAmount2;
import com.ytincl.ereport.pojo.UpLoadFile;
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
		logger.debug("��ѯ������====="+querydate);
		ToBeUploaded tbu = new ToBeUploaded();
		ArrayList<UpLoadFile> list;
		list = (ArrayList<UpLoadFile>) tobeuploaded.getToBeUpLoadedList(querydate);
		tbu.setList(list);
		return tbu;
	}
	
	
	
}
