package com.upuptax.reference;

import java.io.IOException;
import java.util.List;

import com.upuptax.io.FileUtil;

public class TaxComputationWorksheet {
	private FillingStatus fillingStatus;
	

	private List<TaxRateRule> taxRateRules;
	
	private List<DeductionRule> deductionRules;
	
	private double exemption;

	public TaxComputationWorksheet(FillingStatus status){
		this.fillingStatus=status;
	}
	public double getExemption(){
		return exemption;
	}
	public void init(){
		try {
			taxRateRules=FileUtil.loadTaxParameters("", fillingStatus);
			deductionRules=FileUtil.loadTaxDeductions("", fillingStatus);
			exemption=FileUtil.loadExemption("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<TaxRateRule> getTaxRateRules() {
		return taxRateRules;
	}

	public void setTaxRateRules(List<TaxRateRule> taxRateRules) {
		this.taxRateRules = taxRateRules;
	}
	
	public double getTaxRate(double taxableIncome){
		for (TaxRateRule rule:taxRateRules){
			if (rule.getTaxableIncomeLowEnd()<taxableIncome && rule.getTaxableIncomeHighEnd()>=taxableIncome)
				{
				System.out.println(rule.toString());
				return rule.getTaxRate();
				
				}
		}
		return -1;
		
	}

	public double getTax(double taxableIncome){
		for (TaxRateRule rule:taxRateRules){
			if (rule.getTaxableIncomeLowEnd()<taxableIncome && rule.getTaxableIncomeHighEnd()>=taxableIncome)
				{
				System.out.println(rule.toString());
				return rule.getTaxRate()*taxableIncome-rule.getSubstraction();
				
				}
		}
		return -1;
		
	}
	public double getDeduction(int numberOfDependency){
		
		double maxdeduction=0;
		for (DeductionRule rule:deductionRules){
			if (rule.getNumberOfDependency() ==numberOfDependency )
				{
				System.out.println(rule.toString());
				return rule.getDeduction();
				
				}
			if (rule.getDeduction()>maxdeduction)
				maxdeduction=rule.getDeduction();
		}
		if (numberOfDependency>deductionRules.size())
			return maxdeduction;
		else
			return 0;
		
	}

}
