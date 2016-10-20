package com.ytincl.ereport.model;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.Template;
import com.ytincl.ereport.pojo.TemplateDetail;

public class TemplateRes {
	 private ArrayList<Template> list;
	 private ArrayList<TemplateDetail> list1;
	 private ArrayList list2;

	public ArrayList<Template> getList() {
		return list;
	}

	public void setList(ArrayList<Template> list) {
		this.list = list;
	}

	public ArrayList<TemplateDetail> getList1() {
		return list1;
	}

	public void setList1(ArrayList<TemplateDetail> list1) {
		this.list1 = list1;
	}

	public ArrayList getList2() {
		return list2;
	}

	public void setList2(ArrayList list2) {
		this.list2 = list2;
	}
}
