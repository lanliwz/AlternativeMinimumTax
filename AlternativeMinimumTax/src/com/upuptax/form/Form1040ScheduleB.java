package com.upuptax.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;
import com.upuptax.utils.TaxNumberUtil;

public class Form1040ScheduleB implements Form{
	private String filedBy="wei_tax_test";
	private Map<String,Double> interests;
	private Map<String,Double> ordinaryDividends;
	private Map<String,Double> qualifiedDividends;
	private Map<String,Double> scheduleB=new HashMap<String,Double>();
	private FillingFormsAndSchedules fillingForms;
	
	public void init(){
		double line2=TaxNumberUtil.add(interests);
		scheduleB.put("2", line2);
		scheduleB.put("4", TaxNumberUtil.substractWithPositiveReturn(scheduleB.get("2"), scheduleB.get("3")));
		double line6=TaxNumberUtil.add(ordinaryDividends);
		scheduleB.put("6", line6);
		
		double qualifiedDivendend = TaxNumberUtil.add(qualifiedDividends);
		scheduleB.put(TaxConstant.QUALIFIED_DIVIDENDS, qualifiedDivendend);
		if (fillingForms==null){
			fillingForms=new FillingFormsAndSchedules();
		}
		fillingForms.putSchedule(TaxConstant.SCHEDULE_B, scheduleB);
		
		
	}
	
	public void setInterests(Map<String, Double> interests) {
		this.interests = interests;
	}
	
	public void setOrdinaryDividends(Map<String, Double> ordinaryDividends) {
		this.ordinaryDividends = ordinaryDividends;
	}
	
	public void setQualifiedDividends(Map<String, Double> qualifiedDividends) {
		this.qualifiedDividends = qualifiedDividends;
	}
	public Map<String, Double> getForm() {
		return scheduleB;
	}
	public void setForm(Map<String, Double> scheduleB) {
		this.scheduleB = scheduleB;
	}

	public void setFillingForms(FillingFormsAndSchedules forms) {
		this.fillingForms=forms;
		
	}

	public FillingFormsAndSchedules getFillingForms() {
		// TODO Auto-generated method stub
		return fillingForms;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return TaxConstant.SCHEDULE_B;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Schedule B";
	}

	
	
	public void save() throws IOException{
		FileUtil.save(getName(), filedBy, scheduleB);
		
	}
	public void load() throws IOException{
		
		scheduleB=FileUtil.load(getName(), filedBy, scheduleB);
		
		
	}
	
	private List<FormLineDetail> lineDetails;	
	@Override
	public List<FormLineDetail> getLineDetails() throws IOException {
		if(lineDetails==null){
		   lineDetails=FileUtil.loadLineDescription(getName(), "");	
		}
		return lineDetails;
	}

	@Override
	public Map<String, String> getInfoForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfoForm(Map<String, String> info) {
		// TODO Auto-generated method stub
		
	}

}
