package com.upuptax.form;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;
import com.upuptax.utils.NumberUtil;

public class Form1040ScheduleE implements Form {
	private String filedBy="wei_tax_test";
	private Map<String,Double> data;
	private Map<String,Double> info;
	private FillingFormsAndSchedules fillingForms;
	private List<FormLineDetail> lineDetails;		


	@Override
	public void init() {
		double line20=NumberUtil.add(5, 19, data);
		data.put("20", line20);
		double line21=NumberUtil.substract(NumberUtil.add(data.get("3"), data.get("4")), line20);
		data.put("21", line21);
	}

	@Override
	public String getName(){
		return TaxConstant.SCHEDULE_E;
	}

	@Override
	public String getDescription() {
		
		return "Schedule E";
	}

	@Override
	public Map<String, Double> getForm() {
		
		return data;
	}

	@Override
	public void setForm(Map<String, Double> form) {
		data=form;

	}

	@Override
	public void setFillingForms(FillingFormsAndSchedules forms) {
		this.fillingForms=forms;

	}

	@Override
	public FillingFormsAndSchedules getFillingForms() {
		// TODO Auto-generated method stub
		return fillingForms;
	}

	public void save() throws IOException{
		FileUtil.save(getName(), filedBy, data);
		
	}
	public void load() throws IOException{
		
		data=FileUtil.load(getName(), filedBy, data);
		
		
	}

	@Override
	public List<FormLineDetail> getLineDetails() throws IOException {
		if(lineDetails==null){
		   lineDetails=FileUtil.loadLineDescription(getName(), "");	
		}
		return lineDetails;
	}


}
