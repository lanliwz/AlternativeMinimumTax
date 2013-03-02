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
	private Map<String,Double> data;
	private Map<String,String> info;
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
		FileUtil.save(getName(), filedBy, data);
//		FileUtil.saveInfo(getName(), filedBy, info);
		
	}
	public void setStandardDeduction(double standardDeduction){
		this.standardDeduction=standardDeduction;
	}
	public void setPersonExemption(double exemption){
		personExemption=exemption;
	}
	public void load() throws IOException{
		
		data=FileUtil.load(getName(), filedBy, data);
//		info=FileUtil.loadInfo(getName(), filedBy, info);
		
		
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
		this.data = form1040;
	}

	public void init(){
		taxcomputation = new TaxComputationWorksheet(fillingStatus);
		taxcomputation.init();
		standardDeduction= taxcomputation.getDeduction(data.get("6d").intValue());
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
		if (data==null){
			data=new HashMap<String,Double>();
		}
		data.put("7", line7);
		System.out.println("Wages = "+line7);
		data.put("62", line62);
		System.out.println("Federal Tax Withhold = "+line62);
		scheduleB=fillingForms.getSchedule(TaxConstant.SCHEDULE_B);
		if (scheduleB!=null){
			data.put("8a", scheduleB.get("4"));
			System.out.println("Interests = "+data.get("8a"));
			data.put("8",  scheduleB.get("4"));
			
			data.put("9b", scheduleB.get(TaxConstant.QUALIFIED_DIVIDENDS));
			data.put("9a", scheduleB.get("6"));
			System.out.println("Dividents = "+data.get("9a"));
			data.put("9",  scheduleB.get("6"));
			System.out.println("Qualified Dividents = "+data.get("9b"));
		}
		scheduleD=fillingForms.getSchedule(TaxConstant.SCHEDULE_D);
		if (scheduleD!=null){
			data.put("13", scheduleD.get("22"));
		}
		
		double line22 = TaxNumberUtil.add(7,21,data);
		data.put("22", line22);
		System.out.println("Total Income = "+line22);
		
		double line36 = TaxNumberUtil.add(23,35,data);
		data.put("36", line36);
		
		double line37=TaxNumberUtil.substractWithPositiveReturn(data.get("22"), data.get("36"));
		System.out.println("Adjusted Gross Income = "+line37);
		
		data.put("37", line37);
		
		data.put("38", line37);
		scheduleA=fillingForms.getSchedule(TaxConstant.SCHEDULE_A);
		if (scheduleA!=null){
			if (scheduleA.get("29")!=null && scheduleA.get("29").doubleValue()>standardDeduction){
				data.put("40", scheduleA.get("29"));
				System.out.println("Itermized Deduction = "+data.get("40"));
			}
			else{
				data.put("40", standardDeduction);
				System.out.println("Standard Deduction = "+standardDeduction);
			}
			
				
			
		}
		
		data.put("41", TaxNumberUtil.substractWithPositiveReturn(data.get("38"),data.get("40") ));
		
		double line42 = TaxNumberUtil.multiply(data.get("6d"),personExemption);
		data.put("42", line42);
		
		System.out.println("Exemption Amount = "+line42);
		
		double line43= TaxNumberUtil.substractWithPositiveReturn(data.get("41"),data.get("42"));
		data.put("43", line43);
		System.out.println("Taxable Income = "+line43);
		
		if (capitalGainWorksheet!=null){
			data.put("44", capitalGainWorksheet.get("19"));
			
		}
		
		if (form6521!=null){
			data.put("45", form6521.get("35"));
		}
		double line46 = TaxNumberUtil.add(44, 45, data);
		data.put("46",line46);
		
		double line54=TaxNumberUtil.add(47, 53, data);
		data.put("54", line54);
		
		System.out.println("Total Credits = "+line54);
		
		data.put("55", TaxNumberUtil.substractWithPositiveReturn(data.get("46"),data.get("54")));
		double line61= TaxNumberUtil.add(55, 60, data);
		data.put("61", line61);
		System.out.println("total tax = "+data.get("61"));
		
		
		data.put("72", TaxNumberUtil.add(62,71,data));
		System.out.println("total Payments = "+data.get("72"));
		
		double line73 = TaxNumberUtil.substractWithPositiveReturn(data.get("72"), data.get("61"));
		data.put("73", line73);
		System.out.println("Overpaid Tax = "+line73);
		
		double line76 = TaxNumberUtil.substractWithPositiveReturn(data.get("61"), data.get("72"));
		//add penalty line77
		line76=TaxNumberUtil.add(line76,data.get("77"));
		
		data.put("76", line76);
		System.out.println("Tax you own = "+line76);
		System.out.println(data);
		if(fillingForms==null){
			fillingForms = new FillingFormsAndSchedules();
		}
		fillingForms.putForm(TaxConstant.FORM_1040, data);

	}
	public FillingFormsAndSchedules getFillingForms() {
		return fillingForms;
	}
	public void setFillingForms(FillingFormsAndSchedules fillingForms) {
		this.fillingForms = fillingForms;
	}

	public Map<String, Double> getForm() {
		return data;
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

	@Override
	public Map<String, String> getInfoForm() {
		return info;
	}

	@Override
	public void setInfoForm(Map<String, String> info) {
		this.info=info;
		
	}

	
	

}
