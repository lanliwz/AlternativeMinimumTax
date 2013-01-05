package com.upuptax.form;

import java.util.HashMap;
import java.util.Map;

import com.upuptax.amt.AMTConstant;
import com.upuptax.utils.NumberUtil;

public class Form1040ScheduleB {
	private Map<String,Double> interests;
	private Map<String,Double> ordinaryDividends;
	private Map<String,Double> qualifiedDividends;
	private Map<String,Double> scheduleB=new HashMap<String,Double>();
	
	public void init(){
		double line2=NumberUtil.add(interests);
		scheduleB.put("2", line2);
		scheduleB.put("4", NumberUtil.substractWithPositiveReturn(scheduleB.get("2"), scheduleB.get("3")));
		double line6=NumberUtil.add(ordinaryDividends);
		scheduleB.put("6", line6);
		
		double qualifiedDivendend = NumberUtil.add(qualifiedDividends);
		scheduleB.put(AMTConstant.QUALIFIED_DIVIDENDS, qualifiedDivendend);
		
	}
	public Map<String, Double> getInterests() {
		return interests;
	}
	public void setInterests(Map<String, Double> interests) {
		this.interests = interests;
	}
	public Map<String, Double> getOrdinaryDividends() {
		return ordinaryDividends;
	}
	public void setOrdinaryDividends(Map<String, Double> ordinaryDividends) {
		this.ordinaryDividends = ordinaryDividends;
	}
	public Map<String, Double> getQualifiedDividends() {
		return qualifiedDividends;
	}
	public void setQualifiedDividends(Map<String, Double> qualifiedDividends) {
		this.qualifiedDividends = qualifiedDividends;
	}
	public Map<String, Double> getScheduleB() {
		return scheduleB;
	}
	public void setScheduleB(Map<String, Double> scheduleB) {
		this.scheduleB = scheduleB;
	}
	
	

}
