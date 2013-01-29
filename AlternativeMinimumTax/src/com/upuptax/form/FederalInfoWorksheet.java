package com.upuptax.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.TaxConstant;

public class FederalInfoWorksheet implements InfoForm {
	private Map<String,String> form=new HashMap<String,String>();
	private String filedBy="wei_tax_test";

	@Override
	public Map<String, String> getForm() {
		// TODO Auto-generated method stub
		return this.form;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return TaxConstant.WKS_FED_INFO;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Federal Information Worksheet";
	}

	@Override
	public void save() throws IOException {
		FileUtil.saveInfo(getName(), filedBy, form);
		
	}

	@Override
	public void load() throws IOException {
		this.form=FileUtil.loadInfo(getName(), filedBy, form);
		
	}

	@Override
	public void setForm(Map<String, String> form) {
		this.form=form;
		
	}
}
