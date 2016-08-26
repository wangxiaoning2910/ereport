package com.ytincl.ereport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ytincl.ereport.dao.UserInfoDao;
import com.ytincl.ereport.model.Menu;
import com.ytincl.ereport.model.UserInfo;
import com.ytincl.ereport.service.UserInfoService;


@Service("userInfoService")
public class UserInfoServiceImp implements UserInfoService{
	@Autowired
	private UserInfoDao userinfodao;
	@Override
	public List<UserInfo> getUserByName(String username)  {
		// TODO Auto-generated method stub
		return userinfodao.getUserByName(username);
	}
	@Override
	public List<Menu> getMenu() {
		// TODO Auto-generated method stub
		return userinfodao.getMenu();
	}
}


