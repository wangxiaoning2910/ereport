package com.ytincl.ereport.model;

import java.util.Date;

public class AccountRoleModel {

	 private Integer id;

	    private String accountName;

	    private String password;

	    private String accountType;

	    private String description;//描述

	    private String state;//账号状态  0 表示停用  1表示启用

	    private Date createTime;

	    private Integer deletestatus;//暂时没用
	    
	    private String role;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getAccountName() {
	        return accountName;
	    }

	    public void setAccountName(String accountName) {
	        this.accountName = accountName == null ? null : accountName.trim();
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password == null ? null : password.trim();
	    }

	    public String getAccountType() {
	        return accountType;
	    }

	    public void setAccountType(String accountType) {
	        this.accountType = accountType == null ? null : accountType.trim();
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description == null ? null : description.trim();
	    }

	    public String getState() {
	        return state;
	    }

	    public void setState(String state) {
	        this.state = state == null ? null : state.trim();
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    public Integer getDeletestatus() {
	        return deletestatus;
	    }

	    public void setDeletestatus(Integer deletestatus) {
	        this.deletestatus = deletestatus;
	    }

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
}
