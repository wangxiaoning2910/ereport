package com.ytincl.ereport.pojo;

import java.util.Date;

public class Template {
    private String temp_id;

    private String temp_name;

    private String url;

    private String catalog;

    private String file_name;

    private Date create_time;
    
    private String version;
    
    private String temp_type;
    
    private String upTemp_id;

    public String getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(String temp_id) {
        this.temp_id = temp_id == null ? null : temp_id.trim();
    }

    public String getTemp_name() {
        return temp_name;
    }

    public void setTemp_name(String temp_name) {
        this.temp_name = temp_name == null ? null : temp_name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog == null ? null : catalog.trim();
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name == null ? null : file_name.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

	public String getTemp_type() {
		return temp_type;
	}

	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type == null ? null : temp_type.trim();
	}

	public String getUpTemp_id() {
		return upTemp_id;
	}

	public void setUpTemp_id(String upTemp_id) {
		this.upTemp_id = upTemp_id == null ? null : upTemp_id.trim();
	}
}