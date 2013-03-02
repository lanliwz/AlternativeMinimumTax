package com.upuptax.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;

public class TaxReport implements Form {
	private Map<String,Double> data;
	private Map<String,String> info;
	private FillingFormsAndSchedules fillingforms;

	@Override
	public void init() {
		if(data==null)
			data= new HashMap<String,Double>();
		List<Map<String,Double>> w2Forms=fillingforms.getForms(TaxConstant.FORM_W2);
		double totalWages=0;
		double totalTaxableWages=0;
		double sstax=0;
		double medtax=0;
		if (w2Forms!=null){
			for (Map<String,Double> w2:w2Forms){
				totalWages=w2.get("5")+totalWages;
				totalTaxableWages=w2.get("1")+totalTaxableWages;
				sstax=w2.get("4")+sstax;
				medtax=w2.get("6")+medtax;
			}
			

		}
		double total401k=totalWages-totalTaxableWages;
		
		data.put("1", totalWages);
		data.put("4", total401k);
		double pct401k=total401k/totalWages*100;
		data.put("5", pct401k);
		double pctMedTax=medtax/totalWages*100;
		double pctSsTax=sstax/totalWages*100;
		data.put("6", sstax);
		data.put("8", medtax);
		data.put("7", pctSsTax);
		data.put("9", pctMedTax);
		
		Map<String,Double> frm1040=fillingforms.getForm(TaxConstant.FORM_1040);
		double federalTax=frm1040.get("61");
		double pctFedTax=federalTax/totalWages*100;
		data.put("10", federalTax);
		data.put("11", pctFedTax);
		
		double totaltax=federalTax+medtax+sstax;
		double pcttotaltax=totaltax/totalWages*100;
		data.put("2",totaltax);
		data.put("3", pcttotaltax);
		

	}

	@Override
	public String getName() {
		return TaxConstant.TAX_REPORT;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Tax Report";
	}

	@Override
	public Map<String, Double> getForm() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public Map<String, String> getInfoForm() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setForm(Map<String, Double> form) {
		this.data=form;

	}

	@Override
	public void setInfoForm(Map<String, String> info) {
		this.info=info;

	}

	@Override
	public void setFillingForms(FillingFormsAndSchedules forms) {
		this.fillingforms=forms;

	}

	@Override
	public FillingFormsAndSchedules getFillingForms() {
		
		return fillingforms;
	}

	@Override
	public void save() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() throws IOException {
		// TODO Auto-generated method stub

	}

	private List<FormLineDetail> lineDetails;	
	@Override
	public List<FormLineDetail> getLineDetails() throws IOException {
		if(lineDetails==null){
		   lineDetails=FileUtil.loadLineDescription(getName(), "");	
		}
		return lineDetails;
	}

}
