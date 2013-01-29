package com.upuptax.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

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
				prop.put(key, String.valueOf(form.get(key)));
			}
		}
		prop.store(writer, null);
		
		
	}

	public static Map<String,Double> load(String formName,String fillingBy,Map<String,Double> form) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".properies");
		if(Files.exists(file)){
			Reader reader= Files.newBufferedReader(file, Charset.defaultCharset());		
			Properties prop = new Properties();
			prop.load(reader);
			if (prop.size()>0 && form!=null){
				for (Object key:prop.keySet()){
					form.clear();
					form.put((String) key, Double.valueOf(prop.getProperty((String)key)));
				}
			}
		
			
		}
	
		return form;
		
	}
	public static Map<String,String> loadInfo(String formName,String fillingBy,Map<String,String> form) throws IOException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".properies");
		if(Files.exists(file)){
			Reader reader= Files.newBufferedReader(file, Charset.defaultCharset());		
			Properties prop = new Properties();
			prop.load(reader);
			if (prop.size()>0 && form!=null){
				for (Object key:prop.keySet()){
					form.clear();
					form.put((String) key, prop.getProperty((String)key));
				}
			}
		
			
		}
	
		return form;
		
	}

}
