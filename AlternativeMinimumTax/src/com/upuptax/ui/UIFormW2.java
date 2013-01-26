package com.upuptax.ui;
import java.util.HashMap;
import java.util.Map;

import com.upuptax.form.Form;
import com.upuptax.form.Form1040;
import com.upuptax.form.FormLineDetail;
import com.upuptax.form.FormW2;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.text.TextBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


public class UIFormW2 extends Application {
	public static void main(String[] args){
		launch(args);
	}


	public void start(Stage stage) throws Exception {
		stage.setTitle("W-2 Form");
		Group root = new Group();
        Scene scene = new Scene(root, 500, 250, Color.WHITE);
		SplitPane splitPane = new SplitPane();
		splitPane.prefWidthProperty().bind(scene.widthProperty());
		splitPane.prefHeightProperty().bind(scene.heightProperty());
		
        ObservableList<Form> fillingforms = FXCollections.observableArrayList();
        final ListView<Form> fillingformsView = new ListView<Form>(fillingforms);
        
        ObservableMap<String,Double> form = FXCollections.observableHashMap();
        
        TableColumn<FormLineDetail,String> linenumber = new TableColumn<FormLineDetail,String>("Line Number");
        TableColumn<FormLineDetail,String> lineinput = new TableColumn<FormLineDetail,String>("Value");
        TableView<FormLineDetail> formInputView = new TableView<FormLineDetail>();
        final ObservableList<FormLineDetail> forminputs=FXCollections.observableArrayList();
        formInputView.setItems(forminputs);
        formInputView.getColumns().addAll(linenumber,lineinput);
        formInputView.setEditable(true);
        linenumber.setEditable(false);
        linenumber.setCellValueFactory(new PropertyValueFactory<FormLineDetail,String>("lineNumber"));
        lineinput.setEditable(true);
        lineinput.setCellFactory(cellFactory);
        lineinput.setCellValueFactory(new PropertyValueFactory<FormLineDetail,String>("value"));
        lineinput.setOnEditCommit(new EventHandler<CellEditEvent<FormLineDetail, String>>() {
        	            @Override
        	            public void handle(CellEditEvent<FormLineDetail, String> t) {
        	                ((FormLineDetail) t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(t.getNewValue());
        	            }
        	        });
        
        fillingforms.add(new FormW2());
        fillingforms.add(new Form1040());
        
        fillingformsView.setPrefWidth(150);
        fillingformsView.setPrefHeight(150);
        
        // display first and last name with tooltip using alias
        fillingformsView.setCellFactory(new Callback<ListView<Form>, ListCell<Form>>() {

            public ListCell<Form> call(ListView<Form> param) {
                final Label leadLbl = new Label();
                final Tooltip tooltip = new Tooltip();
                    final ListCell<Form> cell = new ListCell<Form>() {
                        @Override 
                        public void updateItem(Form item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    leadLbl.setText(item.getName());
                                    setText(item.getName());
                                    tooltip.setText(item.getDescription());
                                    setTooltip(tooltip);
                                }
                        }
                    }; // ListCell
                    return cell;
            
            }
        }); // setCellFactory

        
        
		VBox leftArea = new VBox(10);
		Label leftLabel = new Label("Filling Forms");
		leftArea.getChildren().add(leftLabel);
		leftArea.getChildren().add(fillingformsView);
		
		fillingformsView.getSelectionModel().selectedItemProperty().addListener(
			new ChangeListener<Form>() {
			@Override
			public void changed(ObservableValue<? extends Form> observable,Form arg1, Form arg2){
				if (observable != null && observable.getValue() != null) {
			}
	                    forminputs.clear();
	                    Map<String,Double> inputs = observable.getValue().getForm();
	                    
	                    forminputs.addAll(transform(inputs));
	                }

			
				
			}
        );


		
		leftArea.setAlignment(Pos.TOP_CENTER);

		// Upper and lower split pane
		VBox rightArea = new VBox();
//		rightArea.getChildren().add(e)
		
		rightArea.setAlignment(Pos.CENTER);
		rightArea.getChildren().add(formInputView);

		// add left area
		splitPane.getItems().add(leftArea);
		splitPane.getItems().add(rightArea);


		// evenly position divider
		ObservableList<SplitPane.Divider> dividers = splitPane.getDividers();
		for (int i = 0; i < dividers.size(); i++) {
		    dividers.get(i).setPosition((i + 1.0) / 3);
		}

		HBox hbox = new HBox();
		hbox.getChildren().add(splitPane);
		root.getChildren().add(hbox);
		stage.setScene(scene);
		stage.show();
		
	}
    Callback<TableColumn<FormLineDetail,String>, TableCell<FormLineDetail,String>>    cellFactory = new Callback<TableColumn<FormLineDetail,String>, TableCell<FormLineDetail,String>>() 
    		{

				@Override
				public TableCell<FormLineDetail, String> call(
						TableColumn<FormLineDetail, String> arg0) {
					
					return new EditingCell();
				} 
    };
    
    public ObservableList<FormLineDetail> transform(Map<String,Double> map){
    	ObservableList<FormLineDetail> inputs = FXCollections.observableArrayList();
    	if(map!=null)
    	for (String key:map.keySet()){
    		FormLineDetail detail = new FormLineDetail();
    		detail.setLineNumber(key);
    		detail.setValue(String.valueOf(map.get(key)));
    		inputs.add(detail);
    	}
    	return inputs;
    }



}
