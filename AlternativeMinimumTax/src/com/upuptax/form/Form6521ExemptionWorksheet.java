package com.upuptax.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FillingStatus;
import com.upuptax.utils.TaxNumberUtil;

public class Form6521ExemptionWorksheet {
	
	private FillingStatus fillingStatus;
	private Map<String,Double> worksheet = new HashMap<String,Double>();
	private Map<String,Double> form6521;
	private Double fillingStatusExemption;
	
	private Double phaseoutThreshhold;
	
	private Double phaseoutRate;
	
	public Form6521ExemptionWorksheet(FillingStatus status){
		this.fillingStatus=status;
	}
	
	public void load(){
		try {
			List<String> slines = FileUtil.loadParameters("6251", "");
			for (String ln:slines){
				String[] token = ln.split(";");
				if(token.length==3 && token[1].equals("PHASEOUT_RATE")){
					phaseoutRate=Double.valueOf(token[2]);
				}
				switch(fillingStatus){
				case SINGLE:{
				if(token.length==4 && token[1].equals("PHASEOUT_THRESHOLD") && token[2].equals("SINGLE")){
					phaseoutThreshhold=Double.valueOf(token[3]);
				}
				if(token.length==4 && token[1].equals("MAX") && token[2].equals("SINGLE")){
					fillingStatusExemption=Double.valueOf(token[3]);
				}
				}
				break;
				case JOIN:{
					if(token.length==4 && token[1].equals("PHASEOUT_THRESHOLD") && token[2].equals("JOIN")){
						phaseoutThreshhold=Double.valueOf(token[3]);
					}
					if(token.length==4 && token[1].equals("MAX") && token[2].equals("JOIN")){
						fillingStatusExemption=Double.valueOf(token[3]);
					}
					}
				break;
				case SEPERATE:{
					if(token.length==4 && token[1].equals("PHASEOUT_THRESHOLD") && token[2].equals("SEPERATE")){
						phaseoutThreshhold=Double.valueOf(token[3]);
					}
					if(token.length==4 && token[1].equals("MAX") && token[2].equals("SEPERATE")){
						fillingStatusExemption=Double.valueOf(token[3]);
					}
					}
				break;
				case HEAD:{
					if(token.length==4 && token[1].equals("PHASEOUT_THRESHOLD") && token[2].equals("HEAD")){
						phaseoutThreshhold=Double.valueOf(token[3]);
					}
					if(token.length==4 && token[1].equals("MAX") && token[2].equals("HEAD")){
						fillingStatusExemption=Double.valueOf(token[3]);
					}
					};
				break;
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init(){
		worksheet.put("1", fillingStatusExemption);
		System.out.println("AMT Standard Exemption = "+fillingStatusExemption);
		worksheet.put("2",form6521.get("28"));
		worksheet.put("3",phaseoutThreshhold);
		double line4=TaxNumberUtil.substractWithPositiveReturn(worksheet.get("2"),worksheet.get("3"));
		worksheet.put("4", line4);
		double line5=line4*phaseoutRate;
		worksheet.put("5", line5);
		double line6=TaxNumberUtil.substractWithPositiveReturn(worksheet.get("1"),line5);
		System.out.println("Your AMT Exemption = " + line6);
		worksheet.put("6", line6);
		
	}
	
	public double getExcemtion(){
		return worksheet.get("6");
	}

	public Map<String, Double> getForm6521() {
		return form6521;
	}

	public void setForm6521(Map<String, Double> form6521) {
		this.form6521 = form6521;
	}
	

}
