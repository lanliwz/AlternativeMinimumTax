package com.upuptax.reference;

import java.io.IOException;
import java.util.List;
import com.upuptax.io.*;

public class FedTaxTable {

	private FillingStatus fillingStatus;
	private List<FedTaxTableRow> taxtable;
	
	
	public FedTaxTable(FillingStatus fillingStatus){
		this.fillingStatus=fillingStatus;
	}
	public void init(){
		try {
			taxtable =FileUtil.loadTaxTable("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public FedTaxTableRow getTax(double income){
//		for (FedTaxTableRow row:taxtable){
//			if (row.getLow()<=income && row.getHigh()>income){
//				return row;
//			}
//		}
//		return null;
//	}
	public double getTax(double income){
		for (FedTaxTableRow row:taxtable){
			if (row.getLow()<=income && row.getHigh()>income){
				switch (fillingStatus){
				case SINGLE: return row.getSingle();

				case JOIN: return row.getMarriedJoin();
	
				case SEPERATE: return row.getMarriedSeperate();
	
				case HEAD: return row.getHeadOfHousehold();

				}

			}
		}
		return 0;
	}
	
	
	

}
