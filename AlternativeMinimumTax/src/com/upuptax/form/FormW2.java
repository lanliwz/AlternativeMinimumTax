package com.upuptax.form;

import java.io.IOException;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;

public class FormW2 implements Form {
	private String filedBy="wei_tax_test";
	private String name;
	private String socialSecurityNumber;
	private boolean spouceW2=false;
	private boolean donotTransferToNextYear=false;
	private Map<String,String> employmentWks;
	private Map<String,Double> incomeWks;
	public String getName() {
		return TaxConstant.FORM_W2;
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


	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "W-2 Form";
	}


	@Override
	public Map<String, Double> getForm() {
		// TODO Auto-generated method stub
		return incomeWks;
	}


	@Override
	public void setForm(Map<String, Double> form) {
		this.incomeWks=form;
		
	}


	@Override
	public void setFillingForms(FillingFormsAndSchedules forms) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public FillingFormsAndSchedules getFillingForms() {
		// TODO Auto-generated method stub
		return null;
	}


	
	public void save() throws IOException{
		FileUtil.save(getName(), filedBy, incomeWks);
		
	}
	public void load() throws IOException{
		
		incomeWks=FileUtil.load(getName(), filedBy, incomeWks);
		
		
	}
	
	

}
