package com.upuptax.reference;

public class TaxRateRule {
	private double taxRate;
	private double taxableIncomeLowEnd;
	private double taxableIncomeHighEnd;
	private double substraction;
	private String template="%s on taxable income over %s to %s";
	
	public TaxRateRule(double taxRate,double low, double high,double substraction){
		this.taxRate=taxRate;
		this.taxableIncomeLowEnd=low;
		this.taxableIncomeHighEnd=high;
		this.substraction=substraction;
	}
	
	public TaxRateRule(String row){
		String[] srow = row.split(";");
		this.taxRate=Double.valueOf(srow[4])/100;
		this.taxableIncomeLowEnd=Double.valueOf(srow[2]);
		if (srow[3]==null ||srow[3].equals(""))
			this.taxableIncomeHighEnd=Double.MAX_VALUE;
		else
			this.taxableIncomeHighEnd=Double.valueOf(srow[3]);
		this.substraction=Double.valueOf(srow[5]);;
	}

	public double getSubstraction() {
		return substraction;
	}

	public void setSubstraction(double substraction) {
		this.substraction = substraction;
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
