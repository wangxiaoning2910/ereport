package com.ytincl.ereport.model;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.Account;
import com.ytincl.ereport.pojo.Resources;
import com.ytincl.ereport.pojo.Role;

public class AccountsModel {
	private ArrayList<Account> AccountList;
	private ArrayList<Role> roleList;
	private ArrayList<Resources> resources;
	private ArrayList<AccountRoleModel> AccountRoleModel;

	public ArrayList<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(ArrayList<Role> roleList) {
		this.roleList = roleList;
	}

	public ArrayList<Account> getAccountList() {
		return AccountList;
	}

	public void setAccountList(ArrayList<Account> accountList) {
		AccountList = accountList;
	}

	public ArrayList<Resources> getResources() {
		return resources;
	}

	public void setResources(ArrayList<Resources> resources) {
		this.resources = resources;
	}

	public ArrayList<AccountRoleModel> getAccountRoleModel() {
		return AccountRoleModel;
	}

	public void setAccountRoleModel(ArrayList<AccountRoleModel> accountRoleModel) {
		AccountRoleModel = accountRoleModel;
	}
}
