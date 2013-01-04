package com.upuptax.amt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.upuptax.form.Form6521;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxRate;
import com.upuptax.reference.TaxRateRule;
import com.upuptax.utils.NumberUtil;

public class CapitalGainWorksheet {
	private FillingFormsAndSchedules fillingForms;
	private Map<String,Double> form1040;
	private Map<String,Double> worksheet=new HashMap<String,Double>();
	
	private double taxableIncome;
	private double qualifiedDividend;
	private double longTermCapitalGain;
	private double taxRate4capitalGain=0.15;
	private double deductAmt=69000;
	private TaxRate taxRate4income;
	
	
	
	public Map<String, Double> getWorksheet() {
		return worksheet;
	}

	public TaxRate getTaxRate4income() {
		return taxRate4income;
	}

	public void setTaxRate4income(TaxRate taxRate4income) {
		this.taxRate4income = taxRate4income;
	}

	public static void main(String[] args){
		CapitalGainWorksheet cptGain=new CapitalGainWorksheet();
		
		TaxRate marriedJoin = new TaxRate();
		List<TaxRateRule> rules = new ArrayList<TaxRateRule>();
//		TaxRateRule r1=new TaxRateRule(0.1,0,17400);
//		rules.add(r1);
//		TaxRateRule r2=new TaxRateRule(0.15,17400,70700);
//		rules.add(r2);
		TaxRateRule r3=new TaxRateRule(0.25,70700,142700,7750);
		rules.add(r3);
		TaxRateRule r4=new TaxRateRule(0.28,142700,217450,11930.5);
		rules.add(r4);
		TaxRateRule r5=new TaxRateRule(0.33,217450,388350,22545.5);
		rules.add(r5);
		TaxRateRule r6=new TaxRateRule(0.35,388350,Double.POSITIVE_INFINITY,30128.5);
		rules.add(r6);
		
		marriedJoin.setTaxRateRules(rules);
		
		cptGain.setTaxRate4income(marriedJoin);
		
		Map<String,Double> frm = new HashMap<String,Double>();
		frm.put("43", 183540d);
		frm.put("9b", 699d);
		frm.put("10", 716d);
		frm.put("38", 227834d);
		frm.put("41", 190940d);
		frm.put("44", 39314d);
		
		cptGain.setForm1040(frm);
		
		FillingFormsAndSchedules fillingForms = new FillingFormsAndSchedules();
		
		Map<String,Map> schedules = new HashMap<String,Map>();
		
		Map<String,Double> scheduleA = new HashMap<String,Double>();
		
		scheduleA.put("9", 32708d);
		
		schedules.put(AMTConstant.SCHEDULE_A, scheduleA);
		Map<String,Double> scheduleD = new HashMap<String,Double>();
		
		scheduleD.put("15", 430d);
		scheduleD.put("16", 430d);
		
		schedules.put(AMTConstant.SCHEDULE_D, scheduleD);
		fillingForms.setSchedules(schedules);
		
		Map<String,Map> forms = new HashMap<String,Map>();
		forms.put(AMTConstant.FORM_1040, frm);
		
		fillingForms.setForms(forms);
		cptGain.setFillingForms(fillingForms);
		cptGain.init();
		
		Map<String,Double> cptGainWks =cptGain.getWorksheet();
		
		Form6521 form6521 = new Form6521();
		
		form6521.setFillingForms(fillingForms);
		form6521.init();
		
		
		
	}
	
	public void init(){
		System.out.println("Capital Gain Tax Rate="+taxRate4capitalGain);
		//taxable income
		double line1=form1040.get("43").doubleValue();
		worksheet.put("1",line1 );
		//qualifying dividend
		double line2=form1040.get("9b").doubleValue();
		worksheet.put("2",line2 );
		
		//capital gain
		if (fillingForms.getSchedules().get(AMTConstant.SCHEDULE_D) != null){
			Map<String,Double> scheduleD=fillingForms.getSchedules().get(AMTConstant.SCHEDULE_D);
			double line3 = NumberUtil.getSmaller(scheduleD.get("15"), scheduleD.get("16"));
			worksheet.put("3",line3);
		}else{
			double line3=form1040.get("13").doubleValue();
			worksheet.put("3",line3 );
		}
			
			worksheet.put("4",worksheet.get("2").doubleValue()+worksheet.get("3").doubleValue() );
			System.out.println("Capital Gain = "+worksheet.get("4"));
			//form 4959 line 4g
			worksheet.put("5", 0d);
			
			double line6=NumberUtil.substractWithPositiveReturn(worksheet.get("4"), worksheet.get("5"));
			
			worksheet.put("6", line6);
			
			double line7=NumberUtil.substractWithPositiveReturn(worksheet.get("1"), worksheet.get("6"));
			worksheet.put("7", line7);
			
			worksheet.put("8", deductAmt);
			
			double line9 = NumberUtil.getSmaller(worksheet.get("1"), worksheet.get("8"));
			worksheet.put("9", line9);
			
			double line10 = NumberUtil.getSmaller(worksheet.get("7"), worksheet.get("9"));
			worksheet.put("10", line10);
			
			double line11=NumberUtil.substractWithPositiveReturn(worksheet.get("9"), worksheet.get("10"));
			worksheet.put("11", line11);
			
			double line12 = NumberUtil.getSmaller(worksheet.get("1"), worksheet.get("6"));
			worksheet.put("12", line12);
			
			worksheet.put("13", line11);
			
			double line14=NumberUtil.substractWithPositiveReturn(worksheet.get("12"), worksheet.get("13"));
			worksheet.put("14", line14);
			
			double line15=getCapitalGainTax(line14);
			worksheet.put("15", line15);
			//
			System.out.println("Taxable Income Not Including Capital Gain="+worksheet.get("7"));
			double line16 = taxRate4income.getTax(worksheet.get("7"));
			worksheet.put("16", line16);
			System.out.println("Tax on Taxable Income Not Including Capital Gain="+worksheet.get("16"));
			
			
			
			double line17 =worksheet.get("15").doubleValue()+worksheet.get("16").doubleValue();
			worksheet.put("17", line17);
			
			System.out.println("Total Tax based on Capital Gain = "+worksheet.get("17"));
			
			System.out.println("Taxable Income="+worksheet.get("1"));
			double line18 = taxRate4income.getTax(worksheet.get("1"));
			worksheet.put("18", line18);
			System.out.println("Tax On Taxable Income="+worksheet.get("18"));
			System.out.println("Total Tax based on Taxable Income = "+worksheet.get("18"));
			
			
			double line19 = NumberUtil.getSmaller(worksheet.get("17"), worksheet.get("18"));
			worksheet.put("19", line19);
			
			System.out.println("Final Tax = "+worksheet.get("19"));
			
			fillingForms.putWorksheet(AMTConstant.WKS_CAPITAL_GAIN,worksheet);
		
			
		
	}
	

	public double getCapitalGainTax(Double cp){
		    double cpt;
		    if (cp!=null)
		    	cpt=cp.doubleValue();
		    else
		    	cpt=0d;
		    cpt=cpt*taxRate4capitalGain;
		System.out.println("Taxable Capital Gain = "+cp);
		System.out.println("Pure Capital Gain Tax = "+cpt);    
		return cpt;

	}
	
	public double getTax(){
		return worksheet.get("19");
				
	}

	public FillingFormsAndSchedules getFillingForms() {
		return fillingForms;
	}

	public void setFillingForms(FillingFormsAndSchedules fillingForms) {
		this.fillingForms = fillingForms;
	}

	public Map<String, Double> getForm1040() {
		return form1040;
	}

	public void setForm1040(Map<String, Double> form1040) {
		this.form1040 = form1040;
	}
	
}
