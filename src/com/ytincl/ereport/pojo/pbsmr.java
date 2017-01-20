package com.ytincl.ereport.pojo;
/**
 * 代收付业务统计报表
 * **/
public class pbsmr {
	private String reportName;//报表名称
	private String statisticsmechanism;//统计机构代码/名称
	private String statisticsdate;//统计年月
	private String statisticstype;//统计方式
	private String dotproperties;//网点属性
	private String paytype;//支付方式
	private String containsubordinateinst;//是否包含下级机构
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getStatisticsmechanism() {
		return statisticsmechanism;
	}
	public void setStatisticsmechanism(String statisticsmechanism) {
		this.statisticsmechanism = statisticsmechanism;
	}
	public String getStatisticsdate() {
		return statisticsdate;
	}
	public void setStatisticsdate(String statisticsdate) {
		this.statisticsdate = statisticsdate;
	}
	public String getStatisticstype() {
		return statisticstype;
	}
	public void setStatisticstype(String statisticstype) {
		this.statisticstype = statisticstype;
	}
	public String getDotproperties() {
		return dotproperties;
	}
	public void setDotproperties(String dotproperties) {
		this.dotproperties = dotproperties;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getContainsubordinateinst() {
		return containsubordinateinst;
	}
	public void setContainsubordinateinst(String containsubordinateinst) {
		this.containsubordinateinst = containsubordinateinst;
	}
	
}
