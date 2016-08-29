package com.ytincl.ereport.service;

import java.util.List;

import com.ytincl.ereport.pojo.Menu;
import com.ytincl.ereport.pojo.UserInfo;

public interface UserInfoService {
	
	public List<UserInfo> getUserByName(String username);
	public List<Menu> getMenu();

}
