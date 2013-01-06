package com.upuptax.form;

import java.util.Map;

import com.upuptax.utils.NumberUtil;

public class Form1040ScheduleD {
	private Map<String,Double> scheduleD;
	
	public void init(){
		double line1h = NumberUtil.substract(scheduleD.get("1e"),scheduleD.get("1f"));
		scheduleD.put("1h", line1h);
		scheduleD.put("1", line1h);
		double line2h = NumberUtil.substract(scheduleD.get("2e"),scheduleD.get("2f"));
		scheduleD.put("2h", line2h);
		scheduleD.put("2", line2h);
		double line3h = NumberUtil.substract(scheduleD.get("3e"),scheduleD.get("3f"));
		scheduleD.put("3h", line3h);
		scheduleD.put("3", line3h);
		
		scheduleD.put("7", NumberUtil.add(1,6, scheduleD));
		double line8h = NumberUtil.substract(scheduleD.get("8e"),scheduleD.get("8f"));
		scheduleD.put("8h", line8h);
		scheduleD.put("8", line8h);
		double line9h = NumberUtil.substract(scheduleD.get("9e"),scheduleD.get("9f"));
		scheduleD.put("9h", line9h);
		scheduleD.put("9", line9h);
		double line10h = NumberUtil.substract(scheduleD.get("10e"),scheduleD.get("10f"));
		scheduleD.put("10h", line10h);
		scheduleD.put("10", line10h);
		scheduleD.put("15", NumberUtil.add(8,14, scheduleD));
		Double[] line16={scheduleD.get("7"),scheduleD.get("15")};
		scheduleD.put("16", NumberUtil.add(line16));
		
		
		if ((scheduleD.get("16")!=null && scheduleD.get("16")>=0)|| scheduleD.get("16")==null){
			scheduleD.put("22", scheduleD.get("16"));
		}else
			scheduleD.put("22", scheduleD.get("21"));

		
		
	}

	public Map<String, Double> getScheduleD() {
		return scheduleD;
	}

	public void setScheduleD(Map<String, Double> scheduleD) {
		this.scheduleD = scheduleD;
	}
	

}
