package com.ytincl.ereport.pojo;

public class TemplateDetailKey {
    private String temp_id;

    private String version;

    private String loc_num;

    public String getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(String temp_id) {
        this.temp_id = temp_id == null ? null : temp_id.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getLoc_num() {
        return loc_num;
    }

    public void setLoc_num(String loc_num) {
        this.loc_num = loc_num == null ? null : loc_num.trim();
    }
}