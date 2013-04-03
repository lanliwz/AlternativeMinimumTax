package com.upuptax.io;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.upuptax.model.jaxb.*;

public class JaxbUtil {
	private static JAXBContext jc;
	
	static {
		try {
			jc= JAXBContext.newInstance( "com.upuptax.model.jaxb" );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TaxForm load(String formName,String fillingBy) throws JAXBException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".xml");
		Unmarshaller u = jc.createUnmarshaller();
		
		TaxForm frm=(TaxForm) u.unmarshal(file.toFile());
		
		return frm;

		
	}
	public static void save(String formName,String fillingBy,TaxForm form) throws IOException, JAXBException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".properies");
		Path folder=Paths.get(System.getProperty("user.home"),fillingBy);
		if (!Files.exists(folder)){
			Files.createDirectory(folder);
		}
		if(!Files.exists(file)){
			Files.createFile(file);		
		
			
		}
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(form, new FileOutputStream(file.toFile()));

	}

}
