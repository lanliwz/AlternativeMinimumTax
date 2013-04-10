package com.upuptax.io;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
	
	public static void main(String[] args){
		try {
			TaxForm form = JaxbUtil.load("SCHEDULE E", "wei zhang");
			System.out.println("Form Name:"+form.getName());
			List<Object> elements = form.getPartAndHeader();
			for(Object obj:elements){
				if(obj instanceof Header){
					List<NameValue> headerItems = ((Header) obj).getItem();
					for(NameValue nv:headerItems){
						System.out.println(nv.getName()+":"+nv.getValue());
					}
				}
				if(obj instanceof Part){
					Part part=(Part) obj;
						System.out.println("Part "+part.getNumber()+"--"+part.getTitle()+part.getDescription());
					
				}
			}
			
//			TaxForm root=new TaxForm();
//			root.setName("SCHEDULE E");
//			JaxbUtil.save("SCHEDULE E", "wei zhang", root);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}	
	public static TaxForm load(String formName,String fillingBy) throws JAXBException{

		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".xml");
		Unmarshaller u = jc.createUnmarshaller();
		
		Object frm= u.unmarshal(file.toFile());
		
		return (TaxForm)frm;

		
	}
	public static void save(String formName,String fillingBy,TaxForm form) throws IOException, JAXBException{
		Path file = Paths.get(System.getProperty("user.home"),fillingBy,formName+".xml");
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
