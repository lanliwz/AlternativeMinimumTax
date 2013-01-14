package com.upuptax.form;

import java.util.Map;

import com.upuptax.reference.FillingFormsAndSchedules;

public interface Form {
	public void init();
	public Map<String,Double> getForm();
	public void setForm(Map<String,Double> form);
	public void setFillingForms(FillingFormsAndSchedules forms);
	public FillingFormsAndSchedules getFillingForms();

}
