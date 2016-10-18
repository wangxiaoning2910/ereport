package com.ytincl.ereport.pojo;

public class TemplateDetail extends TemplateDetailKey {
    private String location;

    private String loc_name;

    private String formula;

    private String type;

    private String is_extend;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name == null ? null : loc_name.trim();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIs_extend() {
        return is_extend;
    }

    public void setIs_extend(String is_extend) {
        this.is_extend = is_extend == null ? null : is_extend.trim();
    }
}