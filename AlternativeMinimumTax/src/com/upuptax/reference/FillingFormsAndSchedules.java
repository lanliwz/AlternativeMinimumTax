package com.upuptax.reference;

import java.util.HashMap;
import java.util.Map;

public class FillingFormsAndSchedules {
	private Map<String,Map> forms=new HashMap<String,Map>();
	private Map<String,Map> schedules=new HashMap<String,Map>();
	private Map<String,Map> worksheets=new HashMap<String,Map>();
	
	public Map<String, Map> getWorksheets() {
		return worksheets;
	}
	public void setWorksheets(Map<String, Map> worksheets) {
		this.worksheets = worksheets;
	}
	public Map<String, Map> getForms() {
		return forms;
	}
	public void setForms(Map<String, Map> forms) {
		this.forms = forms;
	}
	public Map<String, Map> getSchedules() {
		return schedules;
	}
	public void setSchedules(Map<String, Map> schedules) {
		this.schedules = schedules;
	}
	public void putWorksheet(String name,Map<String,Double> worksheet){
		worksheets.put(name, worksheet);
	}
	
	public void putForm(String name,Map<String,Double> form){
		forms.put(name, form);
	}
	public void putSchedule(String name,Map<String,Double> schedule){
		schedules.put(name, schedule);
	}
	
	
	

}
