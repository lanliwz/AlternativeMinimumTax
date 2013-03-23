package com.upuptaxfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.upuptax.io.FileUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class TaxFilePicker implements Initializable {
	private UpupTaxFX app;
	@FXML
	private ListView<String> listView4taxFile;
	
	ObservableList<String> olist4taxFile=FXCollections.observableArrayList();
	
	List<String> list4taxFile=new ArrayList<String>();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillListView4TaxFile();

	}
	public void setApp(UpupTaxFX app){
		this.app=app;
	}
	
	@FXML
	private void openTaxFile(){
		String taxfile = listView4taxFile.getSelectionModel().getSelectedItem();
		app.setFileName(taxfile);
		app.gotoFXMain();
	}
	@FXML
	private void cancel(){
		app.gotoFXMain();
	}
	private void fillListView4TaxFile(){
		olist4taxFile.clear();
		try {
			list4taxFile=FileUtil.loadParameters("FILENAME", "");
			for(String raw:list4taxFile){
				String[] token=raw.split(";");
				olist4taxFile.add(token[1]);
			}
	
			if (listView4taxFile!=null)
				listView4taxFile.setItems(olist4taxFile);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
