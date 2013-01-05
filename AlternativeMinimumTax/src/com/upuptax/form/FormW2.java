package com.upuptax.form;

import java.util.Map;

public class FormW2 {
	private String name;
	private String socialSecurityNumber;
	private boolean spouceW2=false;
	private boolean donotTransferToNextYear=false;
	private Map<String,String> employmentWks;
	private Map<String,Double> incomeWks;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public boolean isSpouceW2() {
		return spouceW2;
	}
	public void setSpouceW2(boolean spouceW2) {
		this.spouceW2 = spouceW2;
	}
	public boolean isDonotTransferToNextYear() {
		return donotTransferToNextYear;
	}
	public void setDonotTransferToNextYear(boolean donotTransferToNextYear) {
		this.donotTransferToNextYear = donotTransferToNextYear;
	}
	public Map<String, String> getEmploymentWks() {
		return employmentWks;
	}
	public void setEmploymentWks(Map<String, String> employmentWks) {
		this.employmentWks = employmentWks;
	}
	public Map<String, Double> getIncomeWks() {
		return incomeWks;
	}
	public void setIncomeWks(Map<String, Double> incomeWks) {
		this.incomeWks = incomeWks;
	}
	
	

}
