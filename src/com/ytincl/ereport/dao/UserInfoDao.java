package com.ytincl.ereport.dao;

import java.util.List;
import com.ytincl.ereport.model.Menu;
import com.ytincl.ereport.model.UserInfo;

public interface UserInfoDao {
	public List<UserInfo> getUserByName(String username);
	public List<Menu> getMenu();

}
