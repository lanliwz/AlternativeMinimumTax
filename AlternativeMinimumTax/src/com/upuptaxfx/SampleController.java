/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upuptaxfx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 *
 * @author lli
 */
public class SampleController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ListView listView;
    
    private ObservableList<String> listString=FXCollections.observableArrayList();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        listString.add("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listString.add("1");
        listString.add("2");
        
//        listView = new ListView<String>(listString);
        listView.setItems(listString);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				// TODO Auto-generated method stub
				
			}
		});
    }    
}
