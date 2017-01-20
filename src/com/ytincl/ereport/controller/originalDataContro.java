package com.ytincl.ereport.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ytincl.ereport.model.originalDataList;
import com.ytincl.ereport.model.pbsmr_busis;
import com.ytincl.ereport.model.pbsmr_insts;
import com.ytincl.ereport.pojo.originalData;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_inst;
import com.ytincl.ereport.service.ToBeDownLoadListService;

@Controller
public class originalDataContro {
	@Autowired
	private ToBeDownLoadListService getdownloadlist;
	private static Logger logger = LoggerFactory.getLogger(originalData.class);
	
	/**
	 * 获取报表名称
	 * @author wangxiaoning
	 * @return originalDataList
	 * **/
	@RequestMapping(value="/view/getoriginalDataList.do")
	@ResponseBody
	public originalDataList getoriginalDataList(HttpServletRequest request,HttpServletResponse response){
		List<originalData> list = getdownloadlist.getOriginalData();
		originalDataList odl = new originalDataList();
		odl.setList(list);
		return odl;
	}
	
	
	
	@RequestMapping(value="/view/getPubsmrBusis.do")
	@ResponseBody
	public pbsmr_busis getPubsmrBusis(
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="reportname", required=true)String reportn,
			@RequestParam(value="date", required=true)String querydate,
			@RequestParam(value="used_busi", required=true)String used_busi,
			@RequestParam(value="institutionadd_busi", required=true)String institutionAdd_busi){
		
		pbsmr_busis pbs = new pbsmr_busis();
		pbsmr_busi pb = new pbsmr_busi();
		logger.debug("reportname=="+reportn+",Statisticsdate==="+querydate+",Statisticstype====="+used_busi+",Dotproperties===="+institutionAdd_busi);
		pb.setReportName(reportn);
		pb.setStatisticsdate(querydate);
		pb.setStatisticstype(institutionAdd_busi);
		pb.setDotproperties(used_busi);
		pbs.setList(getdownloadlist.getpbsmr_busiList(pb));
		logger.debug("查询结果==============="+pbs.getList().size());
		return pbs;
		
	}
	
	
	@RequestMapping(value="/view/getPubsmrInsts.do")
	@ResponseBody
	public pbsmr_insts getPubsmrInsts(
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="reportname", required=true)String reportn,
			@RequestParam(value="date", required=true)String querydate,
			@RequestParam(value="used_inst", required=true)String used_busi,
			@RequestParam(value="institutionAdd_inst", required=true)String institutionAdd_busi,
			@RequestParam(value="busicode_inst", required=true)String busicode){
		
		pbsmr_insts pis = new pbsmr_insts();
		pbsmr_inst pi = new pbsmr_inst();
		logger.debug("reportname=="+reportn+",Statisticsdate==="+querydate+",Statisticstype====="+used_busi+",Dotproperties===="+institutionAdd_busi+",交易吗"+busicode);
		pi.setReportName(reportn);
		pi.setStatisticsdate(querydate);
		pi.setStatisticstype(institutionAdd_busi);
		pi.setDotproperties(used_busi);
		pi.setBusicode(busicode);
		pis.setList(getdownloadlist.getpbsmr_instList(pi));
		logger.debug("查询结果==============="+pis.getList().size());
		return pis;
		
	}
}
