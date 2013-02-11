package com.upuptax.reference;

public class DeductionRule {
	private int numberOfDependency;
	private double deduction;
	public DeductionRule(String[] token){
		this.numberOfDependency=Integer.valueOf(token[2]);
		this.deduction=Double.valueOf(token[3]);
	}
	public DeductionRule(int numOfDep,double deduction){
		this.numberOfDependency=numOfDep;
		this.deduction=deduction;
	}
	public int getNumberOfDependency() {
		return numberOfDependency;
	}
	public double getDeduction() {
		return deduction;
	}
	

}
