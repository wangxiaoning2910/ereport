package com.ytincl.ereport.dao;

import java.util.ArrayList;
import java.util.List;

import com.ytincl.ereport.pojo.Resources;

public interface ResourcesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);
    
    public List<Resources> queryAll(Resources r);

	List<Resources> findAccountResourcess(String accountId);
	
	public List<Resources> getAllResources();

	Resources getResourcesByMid(String mid);

}