package com.upuptax.reference;

public class TaxRateRule {
	private double taxRate;
	private double taxableIncomeLowEnd;
	private double taxableIncomeHighEnd;
	private String template="%s on taxable income over %s to %s";
	
	public TaxRateRule(double taxRate,double low, double high){
		this.taxRate=taxRate;
		this.taxableIncomeLowEnd=low;
		this.taxableIncomeHighEnd=high;
	}
	
	public String toString(){
		return String.format(template, taxRate,taxableIncomeLowEnd,taxableIncomeHighEnd);
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public double getTaxableIncomeLowEnd() {
		return taxableIncomeLowEnd;
	}
	public void setTaxableIncomeLowEnd(double taxableIncomeLowEnd) {
		this.taxableIncomeLowEnd = taxableIncomeLowEnd;
	}
	public double getTaxableIncomeHighEnd() {
		return taxableIncomeHighEnd;
	}
	public void setTaxableIncomeHighEnd(double taxableIncomeHighEnd) {
		this.taxableIncomeHighEnd = taxableIncomeHighEnd;
	}
	

}
