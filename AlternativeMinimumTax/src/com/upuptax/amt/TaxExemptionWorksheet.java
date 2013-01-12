package com.upuptax.amt;

import java.util.Map;

public class TaxExemptionWorksheet {
	private Map<String,Double> exceptionParameters;
	private double AMTICoefficent=0.25;
	private Map<Integer,Double> worksheet;
	public double getExemptionAmt(){
		double maxException=worksheet.get(1).doubleValue();
		double alternativeMinimumTaxIncome=worksheet.get(2).doubleValue();
		double phaseoutAMTI=worksheet.get(3).doubleValue();
		if(alternativeMinimumTaxIncome<=phaseoutAMTI){
			return maxException;
		}else{
			return maxException- (alternativeMinimumTaxIncome-phaseoutAMTI)*AMTICoefficent;
		}
	}

}
