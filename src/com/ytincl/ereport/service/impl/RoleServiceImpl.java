package com.ytincl.ereport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.RoleDao;
import com.ytincl.ereport.pojo.Role;
import com.ytincl.ereport.service.RoleService;
@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Override
	public ArrayList<Role> queryAllRole() {
		// TODO Auto-generated method stub
		return roleDao.queryAllRole();
	}
	@Override
	public void roleDelete(String name) {
		roleDao.deleteByName(name);
		
	}
	@Override
	public int updateByName(Role role) {
		return roleDao.updateByName(role);
		
	}
	@Override
	public void roleAdd(Role role) {
		roleDao.insert(role);
		
	}
	@Override
	public Role getRoleByRoleKey(String roleKey) {
		// TODO Auto-generated method stub
		return roleDao.getRoleByRoleKey(roleKey);
	}
	@Override
	public Role getRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return roleDao.getRoleByName(roleName);
	}
	@Override
	public ArrayList<Role> findRoleByAccount(int id) {
		// TODO Auto-generated method stub
		return roleDao.findRoleByAccount(id);
	}

}
