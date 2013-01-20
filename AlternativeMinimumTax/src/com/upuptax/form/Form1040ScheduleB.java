package com.upuptax.form;

import java.util.HashMap;
import java.util.Map;

import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;
import com.upuptax.utils.NumberUtil;

public class Form1040ScheduleB implements Form{
	private Map<String,Double> interests;
	private Map<String,Double> ordinaryDividends;
	private Map<String,Double> qualifiedDividends;
	private Map<String,Double> scheduleB=new HashMap<String,Double>();
	private FillingFormsAndSchedules fillingForms;
	
	public void init(){
		double line2=NumberUtil.add(interests);
		scheduleB.put("2", line2);
		scheduleB.put("4", NumberUtil.substractWithPositiveReturn(scheduleB.get("2"), scheduleB.get("3")));
		double line6=NumberUtil.add(ordinaryDividends);
		scheduleB.put("6", line6);
		
		double qualifiedDivendend = NumberUtil.add(qualifiedDividends);
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
	

}
