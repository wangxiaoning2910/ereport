package com.ytincl.ereport.model;

public class FindFileAsParam extends BaseModel {

    private String date;
	private String status;
    private String name;
    
    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
        return " FindFileAsParam [date=" + date + ", status=" + status
                + ", name=" + name + "]";
    }

	public static long getSerialversionuid() {
		return getSerialversionuid();
	}

	
}
