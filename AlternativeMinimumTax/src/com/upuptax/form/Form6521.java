package com.upuptax.form;

import java.util.HashMap;
import java.util.Map;

import com.upuptax.amt.AMTConstant;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.utils.NumberUtil;

public class Form6521 {
	private FillingFormsAndSchedules fillingForms;
	private Form6521ExceptionWorksheet exemption=new Form6521ExceptionWorksheet();
	private Map<String,Double> form1040;
	private Map<String,Double> form6521=new HashMap<String,Double>();
	private double medicalDentalRate=0.025;
	private double line31Threshold=175000d;
	private double line31RateBellow=0.26d;
	private double line31RateAbove=0.28d;
	private double line31AboveAbstraction=3500d;
	public void init(){
		double line1=0;
		if (fillingForms.getSchedules()!=null && fillingForms.getSchedules().get(AMTConstant.SCHEDULE_A)!=null){
			Map<String,Double> scheduleA=fillingForms.getSchedules().get(AMTConstant.SCHEDULE_A);
			line1=form1040.get("41");
			double line2 = NumberUtil.getSmaller(scheduleA.get("4"),medicalDentalRate*form1040.get("38"));
			form6521.put("2", line2);
			form6521.put("3", form1040.get("9"));
			form6521.put("5", form1040.get("27"));
			form6521.put("7",-form1040.get("10"));
			
		} else {
			line1=form1040.get("38");
		}
		form6521.put("1", line1);
		double line28=NumberUtil.add(1, 27, form6521);
		form6521.put("28", line28);
		
		exemption.setForm6521(form6521);
		exemption.init();
		form6521.put("29", exemption.getExcemtion());
		double line30 = NumberUtil.substractWithPositiveReturn(line28, form6521.get("29"));
		form6521.put("30", line30);
		
		double line31=0;
		if (line30<=line31Threshold){
			line31=line30*line31RateBellow;
		}else{
			line31=line30*line31RateAbove-line31AboveAbstraction;
		}
		form6521.put("31", line31);
		double line33=NumberUtil.substractWithPositiveReturn(line31, form6521.get("32"));
		form6521.put("33",line33);
		
		//Tax Computation using Capital Gain Rate
		form6521.put("36", form6521.get("30"));
		if (fillingForms.getWorksheets()!=null && fillingForms.getWorksheets().get(AMTConstant.WKS_CAPITAL_GAIN)!=null){
			Map<String,Double> cg=fillingForms.getWorksheets().get(AMTConstant.WKS_CAPITAL_GAIN);
			form6521.put("37", cg.get("6"));
		}
		
		
		
		
		
			
			
	}


}
