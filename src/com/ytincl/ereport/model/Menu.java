package com.ytincl.ereport.model;

public class Menu {
	private String mid;
	private String parentmid;
	private String text;
	private String href;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getParentmid() {
		return parentmid;
	}
	public void setParentmid(String parentmid) {
		this.parentmid = parentmid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
}
