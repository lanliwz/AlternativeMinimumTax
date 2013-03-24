/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upuptaxfx;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.upuptax.form.Form;
import com.upuptax.form.FormLineDetail;
import com.upuptax.form.InfoForm;
import com.upuptax.reference.FillingStatus;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author lli
 */
public class UpupTaxFX extends Application {
	private Stage stage;
	private static String VERSION="1.0.0.001";
	private static String TAXYEAR="2012";
	private String installedFolder="upuptax";
	
	FillingStatus fillingStatus=FillingStatus.JOIN;
	private String fileName;//="wei_tax_test";
	
	public void setFileName(String filename){
		this.fileName=filename;
	}
	public String getFileName(){
		return this.fileName;
	}
    
    @Override
    public void start(Stage stage) throws Exception {
    	this.stage=stage;
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UpupTaxFXMain.fxml"));
//    	UpupTaxFXMainController maincontroller=loader.getController();
//        Parent root = (Parent) loader.load();
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//    	gotoFilePicker();
    	gotoFXMain();
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
    public void gotoFXMain() {
        try {
            UpupTaxFXMainController ctl = (UpupTaxFXMainController) replaceSceneContent("UpupTaxFXMain.fxml");
            ctl.setApp(this);
            ctl.init();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoFilePicker() {
        try {
        	TaxFilePicker ctl = (TaxFilePicker) replaceSceneContent("TaxFileChooser.fxml");
            ctl.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = this.getClass().getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(this.getClass().getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }


}