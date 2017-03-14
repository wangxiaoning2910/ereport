package com.ytincl.ereport.service;

import java.util.ArrayList;
import java.util.List;

import com.ytincl.ereport.pojo.Resources;
import com.ytincl.ereport.pojo.Role;

public interface ResourcesService {

	public List<Resources> getAllResources();
	
	List<Resources> findAccountResourcess(String accountId);

	public ArrayList<Resources> queryResourcesList();

	public Resources getResourcesByMid(String string);
}
