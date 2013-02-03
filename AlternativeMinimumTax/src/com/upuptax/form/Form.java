package com.upuptax.form;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;

import com.upuptax.reference.FillingFormsAndSchedules;

public interface Form {
	
	public void init();
	public String getName();
	public String getDescription();
	public Map<String,Double> getForm();
	public void setForm(Map<String,Double> form);
	public void setFillingForms(FillingFormsAndSchedules forms);
	public FillingFormsAndSchedules getFillingForms();
	public void save() throws IOException;
	public void load() throws IOException;
	public List<FormLineDetail> getLineDetails() throws IOException;


}
