package com.ytincl.ereport.pojo;

import java.util.List;

public class FindFileSet{
	private List<DepType> depTypeResult;
	private List<DepCity> depCityResult;
	private List<DepCounty> depCountyResult;
	
	
	public List<DepType> getDepTypeResult() {
		return depTypeResult;
	}
	public void setDepTypeResult(List<DepType> depTypeResult) {
		this.depTypeResult = depTypeResult;
	}
	public List<DepCity> getDepCityResult() {
		return depCityResult;
	}
	public void setDepCityResult(List<DepCity> depCityResult) {
		this.depCityResult = depCityResult;
	}
	public List<DepCounty> getDepCountyResult() {
		return depCountyResult;
	}
	public void setDepCountyResult(List<DepCounty> depCountyResult) {
		this.depCountyResult = depCountyResult;
	}
	
	
}
