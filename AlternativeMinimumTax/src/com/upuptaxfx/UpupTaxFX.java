/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upuptaxfx;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.upuptax.form.Form;
import com.upuptax.form.FormLineDetail;
import com.upuptax.form.InfoForm;
import com.upuptax.reference.FillingStatus;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lli
 */
public class UpupTaxFX extends Application {
	FillingStatus fillingStatus=FillingStatus.JOIN;
	String fillingName="wei_tax_test";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UpupTaxFXMain.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public ObservableList<FormLineDetail> transform(Form form){
    	Map<String,Double> map=form.getForm();
    	ObservableList<FormLineDetail> inputs = FXCollections.observableArrayList();
    	if(map!=null){
    		List<FormLineDetail> linedetails;
			try {
				linedetails = form.getLineDetails();
		    	for(FormLineDetail dt:linedetails){
		    		FormLineDetail detail=new FormLineDetail();
		    		detail.setForm(form);
		    		detail.setFormName(form.getName());
		    		detail.setLineDescription(dt.getLineDescription());
		    		detail.setLineNumber(dt.getLineNumber());
		    		detail.setValue(map.get(detail.getLineNumber())==null?"":String.valueOf(map.get(detail.getLineNumber())));
		    		inputs.add(detail);
		    	}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		

    	}

    	return inputs;
    }
    public ObservableList<FormLineDetail> transform(InfoForm form){
    	Map<String,String> map=form.getForm();
    	ObservableList<FormLineDetail> inputs = FXCollections.observableArrayList();
    	if(map!=null){
    		List<FormLineDetail> linedetails=form.getLineDetails();
    		
	    	for(FormLineDetail dt:linedetails){
	    		FormLineDetail detail=new FormLineDetail();
	    		detail.setInfoForm(form);
	    		detail.setFormName(form.getName());
	    		detail.setLineDescription(dt.getLineDescription());
	    		detail.setLineNumber(dt.getLineNumber());
//	    		System.out.println(detail.getLineNumber());
	    		detail.setValue(map.get(detail.getLineNumber())==null?"":map.get(detail.getLineNumber()));
//	    		detail.setValue(detail.getLineNumber());
	    		inputs.add(detail);
	    	}
    	}
    	return inputs;
    }

}