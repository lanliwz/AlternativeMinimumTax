package com.upuptax.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.upuptax.amt.AMTConstant;
import com.upuptax.amt.CapitalGainWorksheet;
import com.upuptax.amt.W2Tax;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.utils.NumberUtil;

public class Form1040 {
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
		Form1040 frm1040=new Form1040();
		Map<String,Double> form1040=new HashMap<String,Double>();
		frm1040.setForm1040(form1040);
		List<Map<String,Double>> w2forms=new ArrayList<Map<String,Double>>();
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
		w2forms.add(w2tax1);
		w2forms.add(w2tax2);
		frm1040.setW2Forms(w2forms);
		frm1040.init();
		
	}
	
	public void setForm1040(Map<String, Double> form1040) {
		this.form1040 = form1040;
	}

	public void init(){
		double line7 =0;
		double line62=0;
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
		if (scheduleB!=null){
			form1040.put("8a", scheduleB.get("4"));
			form1040.put("8",  scheduleB.get("4"));
			form1040.put("9b", scheduleB.get(AMTConstant.QUALIFIED_DIVIDENDS));
			form1040.put("9a", scheduleB.get("6"));
			form1040.put("9",  scheduleB.get("6"));
		}
		
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
		
		form1040.put("72", NumberUtil.add(62,71,form1040));
		System.out.println("total Payments"+form1040.get("72"));
		
		double line73 = NumberUtil.substractWithPositiveReturn(form1040.get("61"), form1040.get("72"));
		form1040.put("73", line73);
		System.out.println("Overpaid Tax = "+line73);
		
		double line76 = NumberUtil.substractWithPositiveReturn(form1040.get("72"), form1040.get("61"));
		form1040.put("76", line76);
		System.out.println("Tax you own = "+line73);

	}
	public FillingFormsAndSchedules getFillingForms() {
		return fillingForms;
	}
	public void setFillingForms(FillingFormsAndSchedules fillingForms) {
		this.fillingForms = fillingForms;
	}
	public List<Map<String, Double>> getW2Forms() {
		return w2Forms;
	}
	public void setW2Forms(List<Map<String, Double>> w2Forms) {
		this.w2Forms = w2Forms;
	}
	public Map<String, Double> getScheduleA() {
		return scheduleA;
	}
	public void setScheduleA(Map<String, Double> scheduleA) {
		this.scheduleA = scheduleA;
	}
	public Map<String, Double> getScheduleB() {
		return scheduleB;
	}
	public void setScheduleB(Map<String, Double> scheduleB) {
		this.scheduleB = scheduleB;
	}
	public Map<String, Double> getScheduleD() {
		return scheduleD;
	}
	public void setScheduleD(Map<String, Double> scheduleD) {
		this.scheduleD = scheduleD;
	}


	public Map<String, Double> getForm1040() {
		return form1040;
	}
	

}
