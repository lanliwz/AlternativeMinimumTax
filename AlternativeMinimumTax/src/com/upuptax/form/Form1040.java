package com.upuptax.form;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FedTaxTable;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.FillingStatus;
import com.upuptax.reference.TaxComputationWorksheet;
import com.upuptax.reference.TaxConstant;
import com.upuptax.reference.TaxRateRule;
import com.upuptax.utils.TaxNumberUtil;


public class Form1040  implements Form{
	private FillingStatus fillingStatus;
	private String filedBy="wei_tax_test";
	private double standardDeduction;
	private double personExemption;
	private FillingFormsAndSchedules fillingForms;
	private List<Map<String,Double>> w2Forms;
	private Map<String,Double> form1040;
	private Map<String,Double> form6521;
	private Map<String,Double> scheduleA;
	private Map<String,Double> scheduleB;
	private Map<String,Double> scheduleD;
	private TaxComputationWorksheet taxcomputation; 
	private  Map<String,Double> capitalGainWorksheet;

	public Form1040(FillingStatus status){
		this.fillingStatus=status;
	}
	
	public void save() throws IOException{
		FileUtil.save(getName(), filedBy, form1040);
		
	}
	public void setStandardDeduction(double standardDeduction){
		this.standardDeduction=standardDeduction;
	}
	public void setPersonExemption(double exemption){
		personExemption=exemption;
	}
	public void load() throws IOException{
		
		form1040=FileUtil.load(getName(), filedBy, form1040);
		
		
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
		taxcomputation = new TaxComputationWorksheet(fillingStatus);
		taxcomputation.init();
		standardDeduction= taxcomputation.getDeduction(form1040.get("6d").intValue());
		personExemption=taxcomputation.getExemption();
		//capital gain
		
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
		if (form1040==null){
			form1040=new HashMap<String,Double>();
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
		
		double line22 = TaxNumberUtil.add(7,21,form1040);
		form1040.put("22", line22);
		System.out.println("Total Income = "+line22);
		
		double line36 = TaxNumberUtil.add(23,35,form1040);
		form1040.put("36", line36);
		
		double line37=TaxNumberUtil.substractWithPositiveReturn(form1040.get("22"), form1040.get("36"));
		System.out.println("Adjusted Gross Income = "+line37);
		
		form1040.put("37", line37);
		
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
		
		form1040.put("41", TaxNumberUtil.substractWithPositiveReturn(form1040.get("38"),form1040.get("40") ));
		
		double line42 = TaxNumberUtil.multiply(form1040.get("6d"),personExemption);
		form1040.put("42", line42);
		
		System.out.println("Exemption Amount = "+line42);
		
		double line43= TaxNumberUtil.substractWithPositiveReturn(form1040.get("41"),form1040.get("42"));
		form1040.put("43", line43);
		System.out.println("Taxable Income = "+line43);
		
		if (capitalGainWorksheet!=null){
			form1040.put("44", capitalGainWorksheet.get("19"));
			
		}
		
		if (form6521!=null){
			form1040.put("45", form6521.get("35"));
		}
		double line46 = TaxNumberUtil.add(44, 45, form1040);
		form1040.put("46",line46);
		
		double line54=TaxNumberUtil.add(47, 53, form1040);
		form1040.put("54", line54);
		
		System.out.println("Total Credits = "+line54);
		
		form1040.put("55", TaxNumberUtil.substractWithPositiveReturn(form1040.get("46"),form1040.get("54")));
		double line61= TaxNumberUtil.add(55, 60, form1040);
		form1040.put("61", line61);
		System.out.println("total tax = "+form1040.get("61"));
		
		
		form1040.put("72", TaxNumberUtil.add(62,71,form1040));
		System.out.println("total Payments = "+form1040.get("72"));
		
		double line73 = TaxNumberUtil.substractWithPositiveReturn(form1040.get("72"), form1040.get("61"));
		form1040.put("73", line73);
		System.out.println("Overpaid Tax = "+line73);
		
		double line76 = TaxNumberUtil.substractWithPositiveReturn(form1040.get("61"), form1040.get("72"));
		//add penalty line77
		line76=TaxNumberUtil.add(line76,form1040.get("77"));
		
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return TaxConstant.FORM_1040;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Form 1040";
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
