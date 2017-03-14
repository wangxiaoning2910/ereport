package com.ytincl.ereport.dao;

import java.util.ArrayList;
import java.util.List;

import com.ytincl.ereport.pojo.Role;

public interface RoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Role role);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    public ArrayList<Role> queryAllRole();

	void deleteByName(String name);

	Role getRoleByRoleKey(String roleKey);

	Role getRoleByName(String roleName);

	ArrayList<Role> findRoleByAccount(int id);

	int updateByName(Role role); 
}