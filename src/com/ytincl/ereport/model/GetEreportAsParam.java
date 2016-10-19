package com.ytincl.ereport.model;

public class GetEreportAsParam extends BaseModel {

    private String date;
    private String name;
    
    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


   
    //Setters and Getters

    @Override
    public String toString() {
        return " GetEreportAsParam [date=" + date + ", name=" + name + "]";
    }

	public static long getSerialversionuid() {
		return getSerialversionuid();
	}

	
}
