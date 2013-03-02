package com.upuptax.form;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;
import com.upuptax.utils.TaxNumberUtil;

public class Form1040ScheduleA implements Form{
	private String filedBy="wei_tax_test";
	private double jobExpenseRate=0.02;
	private Map<String,Double> scheduleA;
	private FillingFormsAndSchedules fillingForms;
	private List<Map<String,Double>> w2Forms;

	public void init(){
		if(w2Forms==null)
			w2Forms=fillingForms.getForms(TaxConstant.FORM_W2);
		double line5=0;
		if (w2Forms!=null){
			for (Map<String,Double> w2:w2Forms){
				line5=TaxNumberUtil.add(w2.get("17"),line5);
			}


		}
		scheduleA.put("5", line5);
		scheduleA.put("9", TaxNumberUtil.add(1,8,scheduleA));
		scheduleA.put("15", TaxNumberUtil.add(10,14,scheduleA));
		scheduleA.put("19", TaxNumberUtil.add(16,18,scheduleA));
		scheduleA.put("24", TaxNumberUtil.add(21,23,scheduleA));
		Double[] l29 = {scheduleA.get("4"),scheduleA.get("9"),scheduleA.get("15"),scheduleA.get("19"),scheduleA.get("20"),scheduleA.get("27"),scheduleA.get("28")};
		scheduleA.put("29", TaxNumberUtil.add(l29));
		if (fillingForms==null){
			fillingForms=new FillingFormsAndSchedules();
		}
		fillingForms.putSchedule(TaxConstant.SCHEDULE_A, scheduleA);

	}
	public void calculate(Map<String,Double> form1040){
		scheduleA.put("25", form1040.get("38"));
		scheduleA.put("26", jobExpenseRate*(form1040.get("38")==null?0d:form1040.get("38")));
		double line27 = TaxNumberUtil.substractWithPositiveReturn(scheduleA.get("24"), scheduleA.get("26"));
		scheduleA.put("27", line27);
		Double[] l29 = {scheduleA.get("4"),scheduleA.get("9"),scheduleA.get("15"),scheduleA.get("19"),scheduleA.get("20"),scheduleA.get("27"),scheduleA.get("28")};
		scheduleA.put("29", TaxNumberUtil.add(l29));
		fillingForms.putSchedule(TaxConstant.SCHEDULE_A, scheduleA);
	}
	public Map<String, Double> getForm() {
		return scheduleA;
	}

	public void setForm(Map<String, Double> scheduleA) {
		this.scheduleA = scheduleA;
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
		return TaxConstant.SCHEDULE_A;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Schedule A";
	}
	
	public void save() throws IOException{
		FileUtil.save(getName(), filedBy, scheduleA);
		
	}
	public void load() throws IOException{
		
		scheduleA=FileUtil.load(getName(), filedBy, scheduleA);
		
		
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
