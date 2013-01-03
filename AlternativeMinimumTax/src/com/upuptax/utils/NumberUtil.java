package com.upuptax.utils;

public class NumberUtil {
	public static double getSmaller(Double[] values){
		Double min=Double.POSITIVE_INFINITY;
		for (Double val:values){
			if (val!=null && val.doubleValue()<min.doubleValue())
				min=val;
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
}
