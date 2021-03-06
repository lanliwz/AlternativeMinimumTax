package com.upuptax.utils;

import java.util.Map;

public class TaxNumberUtil {
	public static double getSmaller(Double[] values){
		Double min=Double.POSITIVE_INFINITY;
		for (Double val:values){
			if (val!=null && val.doubleValue()<min.doubleValue())
				min=val;
			else if (val==null && min>0)
				min=0d;
		}
		if(min.isInfinite())
			return 0;
		else
			return min.doubleValue();
	}
	public static double getSmaller(Double value1,Double value2){
		Double[] values ={value1,value2};
		return getSmaller(values);

	}
	public static double substractWithPositiveReturn(Double value1,Double value2){
		double val=0d;
		if (value1!=null && value2!=null && (value1.doubleValue()-value2.doubleValue())>0)
			val=value1.doubleValue()-value2.doubleValue();
		else if (value1!=null && value2==null)
			val=value1.doubleValue();
		
		return val;
	}
	public static double substract(Double value1,Double value2){
		double val=0d;
		if (value1!=null && value2!=null )
			val=value1.doubleValue()-value2.doubleValue();
		else if (value1==null || value2==null)
			val=0d;
		
		return val;
	}
	public static double add(int startLineNum,int endLineNum,Map<String,Double> values){
		double total=0;
		for (int i=startLineNum;i<=endLineNum;i++){
			Double value=values.get(String.valueOf(i));
			if (value!=null)
				total=total+value.doubleValue();
			
		}
		return total;
	}
	public static double add(Map<String,Double> values){
		double total=0;
		if (values!=null)
		for (String key:values.keySet()){
			
			if (values.get(key)!=null)
				total=total+values.get(key);
			
		}
		return total;
	}
	public static double add(Double[] values){
		double total=0;
		for (Double value:values){

			if (value!=null)
				total=total+value.doubleValue();
			
		}
		return total;
	}
	public static double add(Double val1,Double val2){
		double total=0;

		if(val1!=null)		
		  total=total+val1.doubleValue();
		if(val2!=null)		
			  total=total+val2.doubleValue();
			

		return total;
	}
	public static double multiply(Double val1,Double val2){
		if(val1==null || val2==null)
			return 0d;
		else
			return val1*val2;
	}
	public static double valueOf(String svalue){
		double value=0d;
		if (svalue==null)
			return value;
		else if (svalue.trim().equalsIgnoreCase("null") || svalue.trim().equals("")){
			return value;
		} else {
			value=Double.valueOf(svalue);
		}
		
		return value;
	}

}
