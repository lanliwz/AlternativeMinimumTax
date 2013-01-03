package com.upuptax.form;

import java.util.HashMap;
import java.util.Map;

import com.upuptax.amt.AMTConstant;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.utils.NumberUtil;

public class Form6521 {
	private FillingFormsAndSchedules fillingForms;
	private Map<String,Double> form1040;
	private Map<String,Double> form6521=new HashMap<String,Double>();
	private double medicalDentalRate=0.025;
	public void init(){
		double line1=0;
		if (fillingForms.getSchedules()!=null && fillingForms.getSchedules().get(AMTConstant.SCHEDULE_A)!=null){
			Map<String,Double> scheduleA=fillingForms.getSchedules().get(AMTConstant.SCHEDULE_A);
			line1=form1040.get("41");
			double line2 = NumberUtil.getSmaller(scheduleA.get("4"),medicalDentalRate*form1040.get("38"));
			form6521.put("2", line2);
		} else {
			line1=form1040.get("38");
		}
		form6521.put("1", line1);
			
			
	}


}
