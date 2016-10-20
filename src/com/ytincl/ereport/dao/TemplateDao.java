package com.ytincl.ereport.dao;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.Template;

public interface TemplateDao {
    int deleteByPrimaryKey(String temp_id);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(String temp_id);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);
    
    ArrayList<Template> queryTemplate();
    
    ArrayList<Template> queryTemplateF();
    
    ArrayList<Template> queryTemplateC(String temp_id);
}