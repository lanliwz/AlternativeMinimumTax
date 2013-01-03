package com.upuptax.amt;

public class W2Tax {
	private double wage;
	private double socialSecurityTax;
	private double medicareTax;
	/**
	 * 
	 * @return alternative minimum tax income
	 */
	public double getAMTI(){
		return wage-socialSecurityTax-medicareTax;
	}
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	public double getSocialSecurityTax() {
		return socialSecurityTax;
	}
	public void setSocialSecurityTax(double socialSecurityTax) {
		this.socialSecurityTax = socialSecurityTax;
	}
	public double getMedicareTax() {
		return medicareTax;
	}
	public void setMedicareTax(double medicareTax) {
		this.medicareTax = medicareTax;
	}
	
	
}
