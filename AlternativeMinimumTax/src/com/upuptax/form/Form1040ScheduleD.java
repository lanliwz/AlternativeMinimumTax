package com.upuptax.form;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.upuptax.io.FileUtil;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxConstant;
import com.upuptax.utils.TaxNumberUtil;

public class Form1040ScheduleD implements Form {
	private String filedBy="wei_tax_test";
	private Map<String,Double> scheduleD;
	private FillingFormsAndSchedules fillingForms;
	
	public void init(){
		double line1h = TaxNumberUtil.substract(scheduleD.get("1d"),scheduleD.get("1e"));
		scheduleD.put("1h", line1h);
		scheduleD.put("1", TaxNumberUtil.add(line1h,scheduleD.get("1g")));
		double line2h = TaxNumberUtil.substract(scheduleD.get("2d"),scheduleD.get("2e"));
		scheduleD.put("2h", line2h);
		scheduleD.put("2", TaxNumberUtil.add(line2h,scheduleD.get("2g")));
		double line3h = TaxNumberUtil.substract(scheduleD.get("3d"),scheduleD.get("3e"));
		scheduleD.put("3h", line3h);
		scheduleD.put("3", TaxNumberUtil.add(line3h,scheduleD.get("3g")));
		
		scheduleD.put("7", TaxNumberUtil.add(1,6, scheduleD));
		double line8h = TaxNumberUtil.substract(scheduleD.get("8d"),scheduleD.get("8e"));
		scheduleD.put("8h", line8h);
		scheduleD.put("8", TaxNumberUtil.add(line8h,scheduleD.get("8g")));
		double line9h = TaxNumberUtil.substract(scheduleD.get("9d"),scheduleD.get("9e"));
		scheduleD.put("9h", line9h);
		scheduleD.put("9", TaxNumberUtil.add(line9h,scheduleD.get("9g")));
		double line10h = TaxNumberUtil.substract(scheduleD.get("10d"),scheduleD.get("10e"));
		scheduleD.put("10h", line10h);
		scheduleD.put("10", TaxNumberUtil.add(line10h,scheduleD.get("10g")));
		scheduleD.put("15", TaxNumberUtil.add(8,14, scheduleD));
		Double[] line16={scheduleD.get("7"),scheduleD.get("15")};
		scheduleD.put("16", TaxNumberUtil.add(line16));
		
		
		if ((scheduleD.get("16")!=null && scheduleD.get("16")>=0)|| scheduleD.get("16")==null){
			scheduleD.put("22", scheduleD.get("16"));
		}else
			scheduleD.put("22", scheduleD.get("21"));

		if (fillingForms==null){
			fillingForms=new FillingFormsAndSchedules();
		}
		fillingForms.putSchedule(TaxConstant.SCHEDULE_D, scheduleD);		
		
	}

	public Map<String, Double> getForm() {
		return scheduleD;
	}

	public void setForm(Map<String, Double> scheduleD) {
		this.scheduleD = scheduleD;
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
		return TaxConstant.SCHEDULE_D;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Schedule D";
	}

	
	public void save() throws IOException{
		FileUtil.save(getName(), filedBy, scheduleD);
		
	}
	public void load() throws IOException{
		
		scheduleD=FileUtil.load(getName(), filedBy, scheduleD);
		
		
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
