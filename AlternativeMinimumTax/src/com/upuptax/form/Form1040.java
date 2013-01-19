package com.upuptax.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.upuptax.amt.CapitalGainWorksheet;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxComputationWorksheet;
import com.upuptax.reference.TaxConstant;
import com.upuptax.reference.TaxRateRule;
import com.upuptax.utils.NumberUtil;

public class Form1040  implements Form{
	private double standardDeduction=11600d;
	private double personExemption=3700;
	private FillingFormsAndSchedules fillingForms;
	private List<Map<String,Double>> w2Forms;
	private Map<String,Double> form1040;
	private Map<String,Double> form6521;
	private Map<String,Double> scheduleA;
	private Map<String,Double> scheduleB;
	private Map<String,Double> scheduleD;
	//capital gain
	private  Map<String,Double> capitalGainWorksheet;
	//alternative minimum tax
	
	public static void main(String[] args){
		FillingFormsAndSchedules fillingforms = FillingFormsAndSchedules.newInstance();
		
		Form1040 frm1040=new Form1040();
		Map<String,Double> form1040=new HashMap<String,Double>();
		form1040.put("6d",2d);
		form1040.put("10",716d);
		form1040.put("52",400d);
		frm1040.setForm(form1040);
		
		
//		List<Map<String,Double>> w2forms=new ArrayList<Map<String,Double>>();
		Map<String,Double> w2tax1=new HashMap<String,Double>();
		Map<String,Double> w2tax2=new HashMap<String,Double>();
		w2tax1.put("1",125405.88);
		w2tax1.put("2",21590.65);
		w2tax1.put("3",106800d);
		w2tax1.put("4",4485.6);
		w2tax1.put("5",141905.88);
		w2tax1.put("6",2057.64);
		w2tax2.put("1",100177.95);
		w2tax2.put("2",14768.0);
		w2tax2.put("3",106800d);
		w2tax2.put("4",4485.6);
		w2tax2.put("5",116677.95);
		w2tax2.put("6",1691.83);
//		w2forms.add(w2tax1);
//		w2forms.add(w2tax2);
		
		fillingforms.putForm(TaxConstant.FORM_W2+"-1", w2tax1);
		fillingforms.putForm(TaxConstant.FORM_W2+"-2", w2tax2);
//		frm1040.setFillingForms(fillingforms);
//		frm1040.setW2Forms(w2forms);
//		frm1040.init();
		
		Form1040ScheduleB scheduleB=new Form1040ScheduleB();
		scheduleB.setFillingForms(fillingforms);
		Map<String,Double> interests = new HashMap<String,Double>();
		interests.put("citi1", 76.06);
		interests.put("citi2", 187.3);
		interests.put("citi3", 22.51);
		interests.put("citi4", 22.5);
		interests.put("citi5", 49.8);
		interests.put("citi6", 11.56);
		scheduleB.setInterests(interests);
		Map<String,Double> dividends = new HashMap<String,Double>();
		dividends.put("citi", 734.4);
		Map<String,Double> qdividends = new HashMap<String,Double>();
		qdividends.put("citi", 699.29);
		
		scheduleB.setOrdinaryDividends(dividends);
		scheduleB.setQualifiedDividends(qdividends);
		scheduleB.init();
		fillingforms=scheduleB.getFillingForms();
		
		fillingforms.print();
		
		Form1040ScheduleD scheduleD=new Form1040ScheduleD();
		scheduleD.setFillingForms(fillingforms);
		Map<String,Double> schD= new HashMap<String,Double>();
		schD.put("9e", 3490d);
		schD.put("9f", 3060d);
		scheduleD.setForm(schD);
		
		scheduleD.init();
		fillingforms=scheduleD.getFillingForms();
		fillingforms.print();
		
		Form1040ScheduleA scheduleA=new Form1040ScheduleA();
		scheduleA.setFillingForms(fillingforms);
		Map<String,Double> schA= new HashMap<String,Double>();
		schA.put("5", 14467d);
		schA.put("6", 18111d);
		schA.put("7", 130d);
		schA.put("10", 1087d);
		schA.put("12", 99d);
		schA.put("16", 3000d);
		schA.put("22", 120d);
		scheduleA.setForm(schA);
		scheduleA.init();
		fillingforms=scheduleA.getFillingForms();
		fillingforms.print();
		
		
//		frm1040.setScheduleD(scheduleD.getForm());
//		frm1040.setScheduleB(scheduleB.getForm());
//		frm1040.setScheduleA(scheduleA.getForm());
		frm1040.setFillingForms(fillingforms);
		frm1040.init();
		scheduleA.calculate(form1040);
		fillingforms=scheduleA.getFillingForms();
		fillingforms.print();

		
		CapitalGainWorksheet cptGain=new CapitalGainWorksheet();
		cptGain.setFillingForms(fillingforms);
		
		TaxComputationWorksheet marriedJoin = new TaxComputationWorksheet();
		List<TaxRateRule> rules = new ArrayList<TaxRateRule>();
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
		
//		Map<String,Double> frm = new HashMap<String,Double>();	
		cptGain.setForm1040(frm1040.getForm());
		
//		FillingFormsAndSchedules fillingForms = new FillingFormsAndSchedules();

		
//		Map<String,Map> schedules = new HashMap<String,Map>();
//		
//		
//		schedules.put(AMTConstant.SCHEDULE_A, scheduleA.getForm());
//		schedules.put(AMTConstant.SCHEDULE_D, scheduleD.getForm());
//		fillingforms.setSchedules(schedules);
//		
//		Map<String,Map> forms = new HashMap<String,Map>();
//		forms.put(AMTConstant.FORM_1040, frm1040.getForm());
//		
//		fillingforms.setForms(forms);
		
//		cptGain.setFillingForms(fillingforms);
		
		cptGain.init();
		
		fillingforms=cptGain.getFillingForms();
		frm1040.setFillingForms(fillingforms);
		
		Map<String,Double> cptGainWks =cptGain.getForm();
		
		frm1040.setCapitalGainWorksheet(cptGainWks);
		frm1040.init();
		fillingforms=frm1040.getFillingForms();
		fillingforms.print();
//		forms.put(AMTConstant.FORM_1040, frm1040.getForm());
//		
//		fillingforms.setForms(forms);
		
		Form6521 form6521 = new Form6521();
		
		form6521.setFillingForms(fillingforms);
		form6521.init();
		

		frm1040.setForm6521(form6521.getForm6521());
		frm1040.init();
		
		fillingforms.print();
		
		
		
		
		
		
	}
	
	public Map<String, Double> getForm6521() {
		return form6521;
	}

	public void setForm6521(Map<String, Double> form6521) {
		this.form6521 = form6521;
	}

	public Map<String, Double> getCapitalGainWorksheet() {
		return capitalGainWorksheet;
	}

	public void setCapitalGainWorksheet(Map<String, Double> capitalGainWorksheet) {
		this.capitalGainWorksheet = capitalGainWorksheet;
	}

	public void setForm(Map<String, Double> form1040) {
		this.form1040 = form1040;
	}

	public void init(){
		
		double line7 =0;
		double line62=0;
		if(w2Forms==null)
			w2Forms=fillingForms.getForms(TaxConstant.FORM_W2);
		if (w2Forms!=null){
			for (Map<String,Double> w2:w2Forms){
				line7=w2.get("1")+line7;
			}
			for (Map<String,Double> w2:w2Forms){
				line62=w2.get("2")+line62;
			}

		}
		form1040.put("7", line7);
		System.out.println("Wages = "+line7);
		form1040.put("62", line62);
		System.out.println("Federal Tax Withhold = "+line62);
		scheduleB=fillingForms.getSchedule(TaxConstant.SCHEDULE_B);
		if (scheduleB!=null){
			form1040.put("8a", scheduleB.get("4"));
			System.out.println("Interests = "+form1040.get("8a"));
			form1040.put("8",  scheduleB.get("4"));
			
			form1040.put("9b", scheduleB.get(TaxConstant.QUALIFIED_DIVIDENDS));
			form1040.put("9a", scheduleB.get("6"));
			System.out.println("Dividents = "+form1040.get("9a"));
			form1040.put("9",  scheduleB.get("6"));
			System.out.println("Qualified Dividents = "+form1040.get("9b"));
		}
		scheduleD=fillingForms.getSchedule(TaxConstant.SCHEDULE_D);
		if (scheduleD!=null){
			form1040.put("13", scheduleD.get("22"));
		}
		
		double line22 = NumberUtil.add(7,21,form1040);
		form1040.put("22", line22);
		System.out.println("Total Income = "+line22);
		
		double line36 = NumberUtil.add(23,35,form1040);
		form1040.put("36", line36);
		
		double line37=NumberUtil.substractWithPositiveReturn(form1040.get("22"), form1040.get("36"));
		System.out.println("Adjusted Gross Income = "+line37);
		
		form1040.put("38", line37);
		scheduleA=fillingForms.getSchedule(TaxConstant.SCHEDULE_A);
		if (scheduleA!=null){
			if (scheduleA.get("29")!=null && scheduleA.get("29").doubleValue()>standardDeduction){
				form1040.put("40", scheduleA.get("29"));
				System.out.println("Itermized Deduction = "+form1040.get("40"));
			}
			else{
				form1040.put("40", standardDeduction);
				System.out.println("Standard Deduction = "+standardDeduction);
			}
			
				
			
		}
		
		form1040.put("41", NumberUtil.substractWithPositiveReturn(form1040.get("38"),form1040.get("40") ));
		
		double line42 = NumberUtil.multiply(form1040.get("6d"),personExemption);
		form1040.put("42", line42);
		
		System.out.println("Exemption Amount = "+line42);
		
		double line43= NumberUtil.substractWithPositiveReturn(form1040.get("41"),form1040.get("42"));
		form1040.put("43", line43);
		System.out.println("Taxable Income = "+line43);
		
		if (capitalGainWorksheet!=null){
			form1040.put("44", capitalGainWorksheet.get("19"));
			
		}
		
		if (form6521!=null){
			form1040.put("45", form6521.get("35"));
		}
		double line46 = NumberUtil.add(44, 45, form1040);
		form1040.put("46",line46);
		
		double line54=NumberUtil.add(47, 53, form1040);
		form1040.put("54", line54);
		
		System.out.println("Total Credits = "+line54);
		
		form1040.put("55", NumberUtil.substractWithPositiveReturn(form1040.get("46"),form1040.get("54")));
		double line61= NumberUtil.add(55, 60, form1040);
		form1040.put("61", line61);
		System.out.println("total tax = "+form1040.get("61"));
		
		
		form1040.put("72", NumberUtil.add(62,71,form1040));
		System.out.println("total Payments = "+form1040.get("72"));
		
		double line73 = NumberUtil.substractWithPositiveReturn(form1040.get("72"), form1040.get("61"));
		form1040.put("73", line73);
		System.out.println("Overpaid Tax = "+line73);
		
		double line76 = NumberUtil.substractWithPositiveReturn(form1040.get("61"), form1040.get("72"));
		form1040.put("76", line76);
		System.out.println("Tax you own = "+line76);
		System.out.println(form1040);
		if(fillingForms==null){
			fillingForms = new FillingFormsAndSchedules();
		}
		fillingForms.putForm(TaxConstant.FORM_1040, form1040);

	}
	public FillingFormsAndSchedules getFillingForms() {
		return fillingForms;
	}
	public void setFillingForms(FillingFormsAndSchedules fillingForms) {
		this.fillingForms = fillingForms;
	}

	public Map<String, Double> getForm() {
		return form1040;
	}

	

	
	

}
