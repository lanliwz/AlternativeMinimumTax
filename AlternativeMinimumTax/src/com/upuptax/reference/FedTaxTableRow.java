package com.upuptax.reference;

public class FedTaxTableRow {
	
	private double low;
	private double high;
	private double single;
	private double marriedJoin;
	private double marriedSeperate;
	private double headOfHousehold;
	public FedTaxTableRow(String srow){
		String[] tokens = srow.split(";");		
		low = Double.valueOf(tokens[0]);
		high = Double.valueOf(tokens[1]);
		single = Double.valueOf(tokens[2]);
		marriedJoin = Double.valueOf(tokens[3]);
		marriedSeperate = Double.valueOf(tokens[4]);
		headOfHousehold = Double.valueOf(tokens[5]);
		
	}
	public double getLow() {
		return low;
	}
	public double getHigh() {
		return high;
	}
	public double getSingle() {
		return single;
	}
	public double getMarriedJoin() {
		return marriedJoin;
	}
	public double getMarriedSeperate() {
		return marriedSeperate;
	}
	public double getHeadOfHousehold() {
		return headOfHousehold;
	}
	
	
	

}
