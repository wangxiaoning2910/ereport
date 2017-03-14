package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.RoleDao;
import com.ytincl.ereport.pojo.Resources;
import com.ytincl.ereport.pojo.Role;
@Repository("RoleDao")
public class RoleDaoImpl implements RoleDao {

	@Resource
	private SqlSession sqlSession;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Role role) {
		
		return sqlSession.insert("com.ytincl.ereport.dao.RoleDao.insert", role);
	}

	@Override
	public int insertSelective(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Role> queryAllRole() {
		ArrayList<Role> roles = new ArrayList<Role>();
		roles = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.RoleDao.queryAllRole");
		return roles;
	}

	@Override
	public void deleteByName(String name) {
		sqlSession.delete("com.ytincl.ereport.dao.RoleDao.deleteByName", name);
		
	}

	@Override
	public int updateByName(Role role) {
		return sqlSession.update("com.ytincl.ereport.dao.RoleDao.updateByName", role);
		
	}

	@Override
	public Role getRoleByRoleKey(String roleKey) {
		Role role = sqlSession.selectOne("com.ytincl.ereport.dao.RoleDao.getRoleByRoleKey",roleKey);
		return role;
	}

	@Override
	public Role getRoleByName(String roleName) {
		Role role = sqlSession.selectOne("com.ytincl.ereport.dao.RoleDao.getRoleByName",roleName);
		return role;
	}

	@Override
	public ArrayList<Role> findRoleByAccount(int id) {
		ArrayList<Role> roles = new ArrayList<Role>();
		roles = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.RoleDao.findRoleByAccount",id);
		return roles;
	}

}
