package com.upuptax.form;

import java.util.List;
import java.util.Map;

import com.upuptax.amt.AMTConstant;
import com.upuptax.amt.CapitalGainWorksheet;
import com.upuptax.reference.FillingFormsAndSchedules;

public class Form1040 {
	private FillingFormsAndSchedules fillingForms;
	private List<Map<String,Double>> w2Forms;
	private Map<String,Double> form1040;
	private Map<String,Double> scheduleA;
	private Map<String,Double> scheduleB;
	private Map<String,Double> scheduleD;
	//capital gain
	private CapitalGainWorksheet capitalGainWorksheet;
	//alternative minimum tax
	private Form6521 form6521;
	
	public void init(){
		double line7 =0;
		if (w2Forms!=null){
			for (Map<String,Double> w2:w2Forms){
				line7=w2.get("1")+line7;
			}
		}
		form1040.put("7", line7);
		
		if (scheduleB!=null){
			form1040.put("8a", scheduleB.get("4"));
			form1040.put("9b", scheduleB.get(AMTConstant.QUALIFIED_DIVIDENDS));
			form1040.put("9a", scheduleB.get("6"));
		}
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
	public CapitalGainWorksheet getCapitalGainWorksheet() {
		return capitalGainWorksheet;
	}
	public void setCapitalGainWorksheet(CapitalGainWorksheet capitalGainWorksheet) {
		this.capitalGainWorksheet = capitalGainWorksheet;
	}
	public Form6521 getForm6521() {
		return form6521;
	}
	public void setForm6521(Form6521 form6521) {
		this.form6521 = form6521;
	}
	public Map<String, Double> getForm1040() {
		return form1040;
	}
	

}
