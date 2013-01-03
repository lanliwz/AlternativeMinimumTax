package com.upuptax.form;

import java.util.HashMap;
import java.util.Map;

import com.upuptax.utils.NumberUtil;

public class Form6521ExceptionWorksheet {
	
	private Map<String,Double> worksheet = new HashMap<String,Double>();
	private Map<String,Double> form6521;
	private Double fillingStatusExemption=74450d;
	
	private Double phaseoutThreshhold=150000d;
	
	private Double phaseoutRate=0.25d;
	
	
	public void init(){
		worksheet.put("1", fillingStatusExemption);
		worksheet.put("2",form6521.get("28"));
		worksheet.put("3",phaseoutThreshhold);
		double line4=NumberUtil.substractWithPositiveReturn(worksheet.get("2"),worksheet.get("3"));
		worksheet.put("4", line4);
		double line5=line4*phaseoutRate;
		worksheet.put("5", line5);
		double line6=NumberUtil.substractWithPositiveReturn(worksheet.get("1"),line5);
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
