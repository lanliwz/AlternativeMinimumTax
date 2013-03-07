package com.upuptax.form;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;

public class Form1099DIV implements Form {
	private String filedBy="wei_tax_test";
	private String name;
	private Map<String,Double> data;
	private Map<String,String> info;
	
	public void setName(String myname){
		this.name=myname;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		
//		return TaxConstant.FORM_1099_DIV+name;
		return name;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "1099 DIV";
	}

	@Override
	public Map<String, Double> getForm() {
		
		return data;
	}

	@Override
	public Map<String, String> getInfoForm() {
		
		return info;
	}

	@Override
	public void setForm(Map<String, Double> form) {
		this.data=form;

	}

	@Override
	public void setInfoForm(Map<String, String> info) {
		this.info=info;

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

	@Override
	public void save() throws IOException {
		FileUtil.save(getName(), filedBy, data);

	}

	@Override
	public void load() throws IOException {
		data=FileUtil.load(getName(), filedBy, data);

	}


	private List<FormLineDetail> lineDetails;	
	@Override
	public List<FormLineDetail> getLineDetails() throws IOException {
		if(lineDetails==null){
		   lineDetails=FileUtil.loadLineDescription(TaxConstant.FORM_1099_DIV, "");	
		}
		return lineDetails;
	}

}
