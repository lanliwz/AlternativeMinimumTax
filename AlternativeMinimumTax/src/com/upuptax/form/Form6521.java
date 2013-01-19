package com.upuptax.form;

import java.util.HashMap;
import java.util.Map;

import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;
import com.upuptax.utils.NumberUtil;

public class Form6521 implements Form{
	private FillingFormsAndSchedules fillingForms;
	private Form6521ExemptionWorksheet exemption=new Form6521ExemptionWorksheet();
	private Map<String,Double> form1040;
	private Map<String,Double> form6521=new HashMap<String,Double>();
	private double medicalDentalRate=0.025;
	private double line31Threshold=175000d;
	private double line31RateBellow=0.26d;
	private double line31RateAbove=0.28d;
	private double line31AboveAbstraction=3500d;
	private double taxRate4capitalGain=0.15;
	
	public FillingFormsAndSchedules getFillingForms() {
		return fillingForms;
	}
	public Map<String,Double> getForm6521(){
		return form6521;
	}

	public void setFillingForms(FillingFormsAndSchedules fillingForms) {
		this.fillingForms = fillingForms;
	}

	public void init(){
		this.form1040=fillingForms.getForms().get(TaxConstant.FORM_1040);
		Double line1=0d;
		if (fillingForms.getSchedules()!=null && fillingForms.getSchedules().get(TaxConstant.SCHEDULE_A)!=null){
			Map<String,Double> scheduleA=fillingForms.getSchedules().get(TaxConstant.SCHEDULE_A);
			line1=form1040.get("41");
			double line2 = NumberUtil.getSmaller(scheduleA.get("4"),medicalDentalRate*form1040.get("38"));
			form6521.put("2", line2);
			form6521.put("3", scheduleA.get("9"));
			form6521.put("5", form1040.get("27"));
			form6521.put("7",-(form1040.get("10")==null?0d:form1040.get("10")));
			
		} else {
			line1=form1040.get("38");
		}
		form6521.put("1", line1);
		double line28=NumberUtil.add(1, 27, form6521);
		form6521.put("28", line28);
		System.out.println("Alternative Minimum Taxable Income = "+line28);
		
		exemption.setForm6521(form6521);
		exemption.init();
		
		form6521.put("29", exemption.getExcemtion());
		double line30 = NumberUtil.substractWithPositiveReturn(line28, form6521.get("29"));
		form6521.put("30", line30);
		
		double line31=calculateAMT(line30);
		
		form6521.put("31", line31);
		double line33=NumberUtil.substractWithPositiveReturn(line31, form6521.get("32"));
		form6521.put("33",line33);
		
		//Tax Computation using Capital Gain Rate
		form6521.put("36", form6521.get("30"));
		if (fillingForms.getWorksheets()!=null && fillingForms.getWorksheets().get(TaxConstant.WKS_CAPITAL_GAIN)!=null){
			Map<String,Double> cg=fillingForms.getWorksheets().get(TaxConstant.WKS_CAPITAL_GAIN);
			form6521.put("37", cg.get("6"));
		}
		
		double line39 = NumberUtil.add(37, 38, form6521);
		form6521.put("39", line39);
		
		double line40 = NumberUtil.getSmaller(form6521.get("36"),line39);
		form6521.put("40", line40);
		System.out.println("Capital Gain Income = "+line40);
		
		double line41=NumberUtil.substractWithPositiveReturn(form6521.get("36"),form6521.get("39"));
		form6521.put("41", line41);
		System.out.println("Non Capital Gain Income = "+line41);
		
		double line42=calculateAMT(line41);
		form6521.put("42", line42);
		System.out.println("AMT Based On Non Capital Gain Income = "+line42);
		
		if (fillingForms.getWorksheets()!=null && fillingForms.getWorksheets().get(TaxConstant.WKS_CAPITAL_GAIN)!=null){
			Map<String,Double> cg=fillingForms.getWorksheets().get(TaxConstant.WKS_CAPITAL_GAIN);
			form6521.put("43", cg.get("8"));
			form6521.put("44", cg.get("7"));
			double line45 = NumberUtil.substractWithPositiveReturn(form6521.get("43"), form6521.get("44"));
			form6521.put("45", line45);
	
		}
		
		form6521.put("46", NumberUtil.getSmaller(form6521.get("36"), form6521.get("37")));
		
		form6521.put("47", NumberUtil.getSmaller(form6521.get("45"), form6521.get("46")));
		
		form6521.put("48", NumberUtil.substractWithPositiveReturn(form6521.get("46"), form6521.get("47")));
		
		double line49 = taxRate4capitalGain*form6521.get("48");
		form6521.put("49", line49);
		System.out.println("Capital Gain Tax = "+line49);
		
		form6521.put("50", NumberUtil.substractWithPositiveReturn(form6521.get("40"), form6521.get("46")));
		form6521.put("51",0.25*form6521.get("50"));
		Double[] params = {form6521.get("42"),form6521.get("49"),form6521.get("51")};
		
		form6521.put("52", NumberUtil.add(params));
		
		form6521.put("53", calculateAMT(form6521.get("36")));
		System.out.println("AMT Tax Based on All Taxable Income = "+form6521.get("53"));
		
		form6521.put("54", NumberUtil.getSmaller(form6521.get("52"), form6521.get("53")));
		
		System.out.println("Final AMT Based Tax = "+form6521.get("54"));
		
		form6521.put("31", form6521.get("54"));
		
		form6521.put("33", NumberUtil.substractWithPositiveReturn(form6521.get("31"),form6521.get("32") ));
		
		form6521.put("34", form1040.get("44"));
		
		form6521.put("35", NumberUtil.substractWithPositiveReturn(form6521.get("33"),form6521.get("34") ));
		
		System.out.println(form6521);	
		
		fillingForms.putForm(TaxConstant.FORM_6251, form6521);
			
	}
	
	private double calculateAMT(double amount ){
		double line31=0;
		if (amount<=line31Threshold){
			line31=amount*line31RateBellow;
		}else{
			line31=amount*line31RateAbove-line31AboveAbstraction;
		}
		return line31;

	}
	@Override
	public Map<String, Double> getForm() {
		// TODO Auto-generated method stub
		return form6521;
	}
	@Override
	public void setForm(Map<String, Double> form) {
		this.form6521=form;
		
	}


}
