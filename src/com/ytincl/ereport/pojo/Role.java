package com.ytincl.ereport.pojo;

public class Role {
    private int id;

    private int enable;//�Ƿ���ý�ɫ��0����ʾ���á�1����ʾ������

    private String name;

    private String roleKey;//Ψһ,�¾�ʱ,��Ҫ�ж�

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey == null ? null : roleKey.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}