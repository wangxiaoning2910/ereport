package com.ytincl.ereport.service;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.Role;

public interface RoleService {

	public ArrayList<Role> queryAllRole();

	void roleDelete(String name);

	public int updateByName(Role role);

	public void roleAdd(Role role);

	public Role getRoleByRoleKey(String string);

	public Role getRoleByName(String roleResName);
	
	ArrayList<Role> findRoleByAccount(int id);
}
