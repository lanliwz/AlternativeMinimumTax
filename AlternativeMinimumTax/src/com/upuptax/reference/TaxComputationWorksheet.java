package com.upuptax.reference;

import java.io.IOException;
import java.util.List;

import com.upuptax.io.FileUtil;

public class TaxComputationWorksheet {
	private FillingStatus fillingStatus;
	

	private List<TaxRateRule> taxRateRules;

	public TaxComputationWorksheet(FillingStatus status){
		this.fillingStatus=status;
	}
	public void init(){
		try {
			taxRateRules=FileUtil.loadTaParameters("", fillingStatus);
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

}
