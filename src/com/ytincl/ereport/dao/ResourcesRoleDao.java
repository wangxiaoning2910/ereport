package com.ytincl.ereport.dao;

import com.ytincl.ereport.pojo.ResourcesRole;

public interface ResourcesRoleDao {
    int deleteByPrimaryKey(ResourcesRole key);

    int insert(ResourcesRole record);

    int insertSelective(ResourcesRole record);
}