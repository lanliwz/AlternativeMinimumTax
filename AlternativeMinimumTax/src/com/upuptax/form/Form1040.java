package com.upuptax.form;

import java.util.List;
import java.util.Map;

import com.upuptax.amt.CapitalGainWorksheet;
import com.upuptax.reference.FillingFormsAndSchedules;

public class Form1040 {
	private FillingFormsAndSchedules fillingForms;
	private List<Map<String,Double>> w2Forms;
	private Map<String,Double> form1040;
	private Map<String,Double> scheduleA;
	private Map<String,Double> scheduleB;
	private Map<String,Double> scheduleD;
	//capital gain
	private CapitalGainWorksheet capitalGainWorksheet;
	//alternative minimum tax
	private Form6521 form6521;

}
