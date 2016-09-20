package com.ytincl.ereport.model;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.BusinessTest;
import com.ytincl.ereport.pojo.SystemBusiness;

public class SystemBusinessReq {
	private String systemBusinessName;
	private String userName;
	private String password;
	private String institution;
	private ArrayList<SystemBusiness> list;
	private ArrayList<BusinessTest> list1;
	
	public String getSystemBusinessName() {
		return systemBusinessName;
	}
	public void setSystemBusinessName(String systemBusinessName) {
		this.systemBusinessName = systemBusinessName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public ArrayList<SystemBusiness> getList() {
		return list;
	}
	public void setList(ArrayList<SystemBusiness> list) {
		this.list = list;
	}
	public ArrayList<BusinessTest> getList1() {
		return list1;
	}
	public void setList1(ArrayList<BusinessTest> list1) {
		this.list1 = list1;
	}
	
}
