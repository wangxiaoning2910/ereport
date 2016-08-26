package com.ytincl.ereport.service;

import java.util.List;
import com.ytincl.ereport.model.Menu;
import com.ytincl.ereport.model.UserInfo;

public interface UserInfoService {
	
	public List<UserInfo> getUserByName(String username);
	public List<Menu> getMenu();

}
