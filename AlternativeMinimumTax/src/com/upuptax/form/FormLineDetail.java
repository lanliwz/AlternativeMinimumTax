package com.upuptax.form;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FormLineDetail {
	private Form form;
	private String formName;
	private String lineNumber;
	private String lineDescription;
	private StringProperty value;
	
	
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getLineDescription() {
		return lineDescription;
	}
	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}
	public String getValue() {
		return value.getValue();
	}
	public void setValue(String value) {
		this.value = new SimpleStringProperty(value);
	}
	public StringProperty valueProperty(){
		return this.value;
	}
	

}
