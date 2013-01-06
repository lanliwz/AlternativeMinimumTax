package com.upuptax.reference;

import java.util.List;

public class TaxComputationWorksheet {

	private List<TaxRateRule> taxRateRules;

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

}
