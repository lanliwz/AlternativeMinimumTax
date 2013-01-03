package com.upuptax.reference;

public enum FillingStatus {
	SINGLE(1,"Single"),
	JOIN(2,"Married filling jointly"),
	SEPERATE(3,"Married filling seperately"),
	HEAD(4,"Head of household"),
	WIDOW(5,"Qualifying Widow/Widower with dependent child");
	private int code;
	private String description;
	private FillingStatus(int code,String description){
		this.code=code;
		this.description=description;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
