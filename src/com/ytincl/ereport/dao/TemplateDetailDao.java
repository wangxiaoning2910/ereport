package com.ytincl.ereport.dao;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.TemplateDetail;
import com.ytincl.ereport.pojo.TemplateDetailKey;

public interface TemplateDetailDao {
    int deleteByPrimaryKey(TemplateDetailKey key);

    int insert(TemplateDetail record);

    int insertSelective(TemplateDetail record);

    ArrayList<TemplateDetail> queryTemplateDetail(TemplateDetailKey key);

    int updateByPrimaryKeySelective(TemplateDetail record);

    int updateByPrimaryKey(TemplateDetail record);
}