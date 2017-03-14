package com.ytincl.ereport.dao;

import com.ytincl.ereport.pojo.AccountRoles;

public interface AccountRolesDao {
    int deleteByPrimaryKey(AccountRoles key);

    int insert(AccountRoles record);

    int insertSelective(AccountRoles record);
}