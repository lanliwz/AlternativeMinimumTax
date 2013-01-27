package com.upuptax.ui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.upuptax.form.CapitalGainWorksheet;
import com.upuptax.form.Form;
import com.upuptax.form.Form1040;
import com.upuptax.form.Form1040ScheduleA;
import com.upuptax.form.Form1040ScheduleB;
import com.upuptax.form.Form1040ScheduleD;
import com.upuptax.form.Form6521;
import com.upuptax.form.FormLineDetail;
import com.upuptax.form.FormW2;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.TaxComputationWorksheet;
import com.upuptax.reference.TaxConstant;
import com.upuptax.reference.TaxRateRule;

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
	
	public List<Form> getListOfForm(){
		FillingFormsAndSchedules fillingforms=FillingFormsAndSchedules.newInstance();
		List<Form> formProcess = new ArrayList<Form>();
		Form1040 frm1040=new Form1040();
		Map<String,Double> form1040=new HashMap<String,Double>();
		form1040.put("6d",2d);
		form1040.put("10",716d);
		form1040.put("52",400d);
		frm1040.setForm(form1040);
		
		
//		List<Map<String,Double>> w2forms=new ArrayList<Map<String,Double>>();
		Map<String,Double> w2tax1=new HashMap<String,Double>();
		Map<String,Double> w2tax2=new HashMap<String,Double>();
		w2tax1.put("1",125405.88);
		w2tax1.put("2",21590.65);
		w2tax1.put("3",106800d);
		w2tax1.put("4",4485.6);
		w2tax1.put("5",141905.88);
		w2tax1.put("6",2057.64);
		w2tax2.put("1",100177.95);
		w2tax2.put("2",14768.0);
		w2tax2.put("3",106800d);
		w2tax2.put("4",4485.6);
		w2tax2.put("5",116677.95);
		w2tax2.put("6",1691.83);
//		w2forms.add(w2tax1);
//		w2forms.add(w2tax2);
		
		fillingforms.putForm(TaxConstant.FORM_W2+"-1", w2tax1);
		fillingforms.putForm(TaxConstant.FORM_W2+"-2", w2tax2);
//		frm1040.setFillingForms(fillingforms);
//		frm1040.setW2Forms(w2forms);
//		frm1040.init();
		
		Form1040ScheduleB scheduleB=new Form1040ScheduleB();
		scheduleB.setFillingForms(fillingforms);
		Map<String,Double> interests = new HashMap<String,Double>();
		interests.put("citi1", 76.06);
		interests.put("citi2", 187.3);
		interests.put("citi3", 22.51);
		interests.put("citi4", 22.5);
		interests.put("citi5", 49.8);
		interests.put("citi6", 11.56);
		scheduleB.setInterests(interests);
		Map<String,Double> dividends = new HashMap<String,Double>();
		dividends.put("citi", 734.4);
		Map<String,Double> qdividends = new HashMap<String,Double>();
		qdividends.put("citi", 699.29);
		
		scheduleB.setOrdinaryDividends(dividends);
		scheduleB.setQualifiedDividends(qdividends);
		scheduleB.init();
		fillingforms=scheduleB.getFillingForms();
		
		fillingforms.print();
		
		Form1040ScheduleD scheduleD=new Form1040ScheduleD();
		scheduleD.setFillingForms(fillingforms);
		Map<String,Double> schD= new HashMap<String,Double>();
		schD.put("9e", 3490d);
		schD.put("9f", 3060d);
		scheduleD.setForm(schD);
		
		scheduleD.init();
		fillingforms=scheduleD.getFillingForms();
		fillingforms.print();
		
		Form1040ScheduleA scheduleA=new Form1040ScheduleA();
		scheduleA.setFillingForms(fillingforms);
		Map<String,Double> schA= new HashMap<String,Double>();
		schA.put("5", 14467d);
		schA.put("6", 18111d);
		schA.put("7", 130d);
		schA.put("10", 1087d);
		schA.put("12", 99d);
		schA.put("16", 3000d);
		schA.put("22", 120d);
		scheduleA.setForm(schA);
		scheduleA.init();
		fillingforms=scheduleA.getFillingForms();
		fillingforms.print();
		
		
//		frm1040.setScheduleD(scheduleD.getForm());
//		frm1040.setScheduleB(scheduleB.getForm());
//		frm1040.setScheduleA(scheduleA.getForm());
		frm1040.setFillingForms(fillingforms);
		frm1040.init();
		scheduleA.calculate(form1040);
		fillingforms=scheduleA.getFillingForms();
		fillingforms.print();

		
		CapitalGainWorksheet cptGain=new CapitalGainWorksheet();
		cptGain.setFillingForms(fillingforms);
		
		TaxComputationWorksheet marriedJoin = new TaxComputationWorksheet();
		List<TaxRateRule> rules = new ArrayList<TaxRateRule>();
		TaxRateRule r3=new TaxRateRule(0.25,70700,142700,7750);
		rules.add(r3);
		TaxRateRule r4=new TaxRateRule(0.28,142700,217450,11930.5);
		rules.add(r4);
		TaxRateRule r5=new TaxRateRule(0.33,217450,388350,22545.5);
		rules.add(r5);
		TaxRateRule r6=new TaxRateRule(0.35,388350,Double.POSITIVE_INFINITY,30128.5);
		rules.add(r6);
		
		marriedJoin.setTaxRateRules(rules);
		cptGain.setTaxRate4income(marriedJoin);
		
	
		cptGain.setForm1040(frm1040.getForm());
		
		
		cptGain.init();
		
		fillingforms=cptGain.getFillingForms();
		frm1040.setFillingForms(fillingforms);
		
		Map<String,Double> cptGainWks =cptGain.getForm();
		
		frm1040.setCapitalGainWorksheet(cptGainWks);
		frm1040.init();
		fillingforms=frm1040.getFillingForms();
		fillingforms.print();
//		forms.put(AMTConstant.FORM_1040, frm1040.getForm());
//		
//		fillingforms.setForms(forms);
		
		Form6521 form6521 = new Form6521();
		
		form6521.setFillingForms(fillingforms);
		form6521.init();
		

		frm1040.setForm6521(form6521.getForm6521());
		frm1040.init();
		
		fillingforms.print();
		formProcess.add(frm1040);
		formProcess.add(form6521);
		formProcess.add(scheduleA);
		formProcess.add(scheduleD);
		formProcess.add(scheduleB);
	    formProcess.add(cptGain);
		
		return formProcess;
	}


	public void start(Stage stage) throws Exception {
		stage.setTitle("W-2 Form");
		Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.WHITE);
		SplitPane splitPane = new SplitPane();
		splitPane.prefWidthProperty().bind(scene.widthProperty());
		splitPane.prefHeightProperty().bind(scene.heightProperty());
		
        ObservableList<Form> fillingforms = FXCollections.observableArrayList();
        final ListView<Form> fillingformsView = new ListView<Form>(fillingforms);
        
        ObservableMap<String,Double> form = FXCollections.observableHashMap();
        
        TableColumn<FormLineDetail,String> linenumber = new TableColumn<FormLineDetail,String>("Line Number");
        linenumber.setPrefWidth(500);
        
        
        TableColumn<FormLineDetail,String> lineinput = new TableColumn<FormLineDetail,String>("Value");
        lineinput.setPrefWidth(200);
        
        TableView<FormLineDetail> formInputView = new TableView<FormLineDetail>();
        formInputView.prefHeightProperty().bind(scene.heightProperty());
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
        
        for (Form f:getListOfForm()){
        fillingforms.add(f);
        }
                
        
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
		
		rightArea.setAlignment(Pos.TOP_CENTER);
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