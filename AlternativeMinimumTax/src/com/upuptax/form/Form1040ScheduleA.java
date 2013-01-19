package com.upuptax.form;

import java.util.Map;

import com.upuptax.amt.AMTConstant;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.utils.NumberUtil;

public class Form1040ScheduleA implements Form{
	private double jobExpenseRate=0.02;
	private Map<String,Double> scheduleA;
	private FillingFormsAndSchedules fillingForms;

	public void init(){
		scheduleA.put("9", NumberUtil.add(1,8,scheduleA));
		scheduleA.put("15", NumberUtil.add(10,14,scheduleA));
		scheduleA.put("19", NumberUtil.add(16,18,scheduleA));
		scheduleA.put("24", NumberUtil.add(21,23,scheduleA));
		Double[] l29 = {scheduleA.get("4"),scheduleA.get("9"),scheduleA.get("15"),scheduleA.get("19"),scheduleA.get("20"),scheduleA.get("27"),scheduleA.get("28")};
		scheduleA.put("29", NumberUtil.add(l29));
		if (fillingForms==null){
			fillingForms=new FillingFormsAndSchedules();
		}
		fillingForms.putSchedule(AMTConstant.SCHEDULE_A, scheduleA);

	}
	public void calculate(Map<String,Double> form1040){
		scheduleA.put("25", form1040.get("38"));
		scheduleA.put("26", jobExpenseRate*(form1040.get("38")==null?0d:form1040.get("38")));
		double line27 = NumberUtil.substractWithPositiveReturn(scheduleA.get("24"), scheduleA.get("26"));
		scheduleA.put("27", line27);
		Double[] l29 = {scheduleA.get("4"),scheduleA.get("9"),scheduleA.get("15"),scheduleA.get("19"),scheduleA.get("20"),scheduleA.get("27"),scheduleA.get("28")};
		scheduleA.put("29", NumberUtil.add(l29));
		fillingForms.putSchedule(AMTConstant.SCHEDULE_A, scheduleA);
	}
	public Map<String, Double> getForm() {
		return scheduleA;
	}

	public void setForm(Map<String, Double> scheduleA) {
		this.scheduleA = scheduleA;
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
	
	

}
