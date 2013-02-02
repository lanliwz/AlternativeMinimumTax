package com.upuptax.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.TaxConstant;

public class FederalInfoWorksheet implements InfoForm {
	private Map<String,String> form;
	private List<FormLineDetail> lineDetails;
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
	public void init() throws IOException{
		if (form==null){
			lineDetails=FileUtil.loadLineDescription(getName(), "");
			form=new HashMap<String,String>();
			for (FormLineDetail detail:lineDetails){
				form.put(detail.getLineNumber(), null);
			}
		}
	}

	@Override
	public List<FormLineDetail> getLineDetails() {
//		// TODO Auto-generated method stub
//		if (this.form!=null){
//			for(FormLineDetail detail:lineDetails){
//				detail.setValue(form.get(detail.getLineNumber()));
//			}
//		}
		return lineDetails;
	}

}
