package com.upuptax.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.upuptax.reference.DeductionRule;
import com.upuptax.reference.FedTaxTable;
import com.upuptax.reference.FedTaxTableRow;
import com.upuptax.reference.FillingStatus;
import com.upuptax.reference.TaxRateRule;
import com.upuptax.utils.TaxNumberUtil;

import com.upuptax.form.FormLineDetail;

public class FileUtil {
	public static void save(String formName,String fillingBy,Map<String,Double> form) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".properies");
		Path folder=Paths.get(System.getProperty("user.home"),fillingBy);
		if (!Files.exists(folder)){
			Files.createDirectory(folder);
		}
		if(!Files.exists(file)){
			Files.createFile(file);		
		
			
		}
		Writer writer = Files.newBufferedWriter(file,Charset.defaultCharset());
		Properties prop = new Properties();
		if (form!=null){
			for (String key:form.keySet()){
				prop.put(key, String.valueOf(form.get(key)));
			}
		}
		prop.store(writer, null);
		
		
	}
	public static void saveInfo(String formName,String fillingBy,Map<String,String> form) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".properies");
		Path folder=Paths.get(System.getProperty("user.home"),fillingBy);
		if (!Files.exists(folder)){
			Files.createDirectory(folder);
		}
		if(!Files.exists(file)){
			Files.createFile(file);		
		
			
		}
		Writer writer = Files.newBufferedWriter(file,Charset.defaultCharset());
		Properties prop = new Properties();
		if (form!=null){
			for (String key:form.keySet()){
				prop.put(key, String.valueOf(form.get(key)==null?"":form.get(key)));
			}
		}
		prop.store(writer, null);
		
		
	}

	public static Map<String,Double> load(String formName,String fillingBy,Map<String,Double> form) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".properies");
		if (form==null){
			form=new HashMap<String,Double>();
		}
		if(Files.exists(file)){
			Reader reader= Files.newBufferedReader(file, Charset.defaultCharset());		
			Properties prop = new Properties();
			prop.load(reader);
			if (prop.size()>0 && form!=null){
				for (Object key:prop.keySet()){

					form.put((String) key, TaxNumberUtil.valueOf(prop.getProperty((String)key)));
				}
			}
		
			
		}
	
		return form;
		
	}
	
	public static List<String> getListOfFiledForms(String fillingBy){
		List<String> filenames=new ArrayList<String>();
		File[] files=null;
		Path folder = Paths.get(System.getProperty("user.home"),fillingBy);
		if (Files.isDirectory(folder)){
			files = folder.toFile().listFiles();
			
		}
		if(files!=null && files.length>0){
			for(File f:files){
				if(f.isFile())
					filenames.add(f.getName());
			}
		}
		
		return filenames;
	}
	public static Map<String,String> loadInfo(String formName,String fillingBy,Map<String,String> form) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".properies");
		if (form==null){
			form=new HashMap<String,String>();
		}
		if(Files.exists(file)){
			Reader reader= Files.newBufferedReader(file, Charset.defaultCharset());		
			Properties prop = new Properties();
			prop.load(reader);
			if (prop.size()>0 && form!=null){
				for (Object key:prop.keySet()){
					
					form.put((String) key, prop.getProperty((String)key));
				}
			}
		
			
		}
	
		return form;
		
	}
//	public static Properties loadLineDescription(String formName,String year) throws IOException{
//		Path file = Paths.get(System.getProperty("user.home"),"upuptax"+year,formName+".properies");
//		Properties prop = new Properties();
//		if(Files.exists(file)){
//			Reader reader= Files.newBufferedReader(file, Charset.defaultCharset());		
//			prop.load(reader);		
//			
//		}
//	
//		return prop;
//		
//	}
	public static List<FormLineDetail> loadLineDescription(String formName,String year) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),"upuptax"+year,formName+".properies");
		List<FormLineDetail> lines = new ArrayList<FormLineDetail>(); 
		
		if(Files.exists(file)){
			List<String> slines = Files.readAllLines(file, Charset.defaultCharset());
			for (String ln:slines){
				String[] token =ln.split("=");
				if (token!=null && token.length==2){
					FormLineDetail detail=new FormLineDetail();
					detail.setFormName(formName);
					detail.setLineNumber(token[0]);
					detail.setLineDescription(token[1]);
					lines.add(detail);
				}
					
			}
			
			
			
		}
	
		return lines;
		
	}
	public static List<FedTaxTableRow> loadTaxTable(String year) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),"upuptax"+year,"FED TAX TABLES.properies");
		List<FedTaxTableRow> lines = new ArrayList<FedTaxTableRow>(); 
		
		if(Files.exists(file)){
			List<String> slines = Files.readAllLines(file, Charset.defaultCharset());
			for (String ln:slines){
				FedTaxTableRow row = new FedTaxTableRow(ln);

				lines.add(row);

					
			}
			
			
			
		}
	
		return lines;
		
	}
	public static List<DeductionRule> loadTaxDeductions(String year,FillingStatus status) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),"upuptax"+year,"TAX-PARAMETERS.properies");
		List<DeductionRule> lines = new ArrayList<DeductionRule>(); 
		
		if(Files.exists(file)){
			List<String> slines = Files.readAllLines(file, Charset.defaultCharset());
			for (String ln:slines){
				String[] token = ln.split(";");
				if (token.length==4 && token[0].equals("DEDUCTION")){
					DeductionRule row=null;
					switch(status){
					case SINGLE: {
						 if (token[1].equals("SINGLE"))
							 row= new DeductionRule(token);
						 break;

					}
					case JOIN: {
						 if (token[1].equals("JOIN"))
							 row= new DeductionRule(token);
						 break;
					}
					case SEPERATE: {
						 if (token[1].equals("SEPERATE"))
							 row= new DeductionRule(token);
						 break;
					}
					case HEAD: {
						 if (token[1].equals("HEAD"))
							 row= new DeductionRule(token);
						 break;
					}
					
					}
					if (row!=null)
						lines.add(row);					
					
				}

					
			}
			
			
			
		}
	
		return lines;
		
	}
	public static List<String> loadParameters(String formName,String year) throws IOException {
		Path file = Paths.get(System.getProperty("user.home"),"upuptax"+year,formName+"-PARAMETERS.properies");
		
		if(Files.exists(file)){
			List<String> slines = Files.readAllLines(file, Charset.defaultCharset());
			return slines;
					
			}			
	
		return null;
	}
	public static List<TaxRateRule> loadTaxParameters(String year,FillingStatus status) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),"upuptax"+year,"TAX-PARAMETERS.properies");
		List<TaxRateRule> lines = new ArrayList<TaxRateRule>(); 
		
		if(Files.exists(file)){
			List<String> slines = Files.readAllLines(file, Charset.defaultCharset());
			for (String ln:slines){
				String[] token = ln.split(";");
				if (token.length==6 && token[0].equals("RATE")){
					TaxRateRule row=null;
					switch(status){
					case SINGLE: {
						 if (token[1].equals("SINGLE"))
							 row= new TaxRateRule(ln);
						 break;

					}
					case JOIN: {
						 if (token[1].equals("JOIN"))
							 row= new TaxRateRule(ln);
						 break;
					}
					case SEPERATE: {
						 if (token[1].equals("SEPERATE"))
							 row= new TaxRateRule(ln);
						 break;
					}
					case HEAD: {
						 if (token[1].equals("HEAD"))
							 row= new TaxRateRule(ln);
						 break;
					}
					
					}
					if (row!=null)
						lines.add(row);					
					
				}

					
			}
			
			
			
		}
	
		return lines;
		
	}
	
	public static double loadExemption(String year) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),"upuptax"+year,"TAX-PARAMETERS.properies");

		
		if(Files.exists(file)){
			List<String> slines = Files.readAllLines(file, Charset.defaultCharset());
			for (String ln:slines){
				String[] token = ln.split(";");
				if (token.length==2 && token[0].equals("EXEMPTION")){
					return Double.valueOf(token[1]);
					
					
			}
			
			
			
			}
	
		
		
	}
		return 0;
		}

}
