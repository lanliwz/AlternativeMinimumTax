package com.upuptax.form;

import java.io.IOException;
import java.util.Map;


public interface InfoForm {
	public Map<String,String> getForm();
	public void setForm(Map<String,String> form);
	public String getName();
	public String getDescription();
	public void save() throws IOException;
	public void load() throws IOException;

}
