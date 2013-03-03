package com.upuptax.ui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.upuptax.form.CapitalGainWorksheet;
import com.upuptax.form.FederalInfoWorksheet;
import com.upuptax.form.Form;
import com.upuptax.form.Form1040;
import com.upuptax.form.Form1040ScheduleA;
import com.upuptax.form.Form1040ScheduleB;
import com.upuptax.form.Form1040ScheduleD;
import com.upuptax.form.Form1040ScheduleE;
import com.upuptax.form.Form1099DIV;
import com.upuptax.form.Form1099INT;
import com.upuptax.form.Form6521;
import com.upuptax.form.FormLineDetail;
import com.upuptax.form.FormW2;
import com.upuptax.form.InfoForm;
import com.upuptax.form.TaxReport;
import com.upuptax.reference.FillingFormsAndSchedules;
import com.upuptax.reference.FillingStatus;
import com.upuptax.reference.TaxComputationWorksheet;
import com.upuptax.reference.TaxConstant;
import com.upuptax.reference.TaxRateRule;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.text.TextBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


public class UIFormW2 extends Application {
	FillingStatus fillingStatus=FillingStatus.JOIN;
	FillingFormsAndSchedules fillingforms=FillingFormsAndSchedules.newInstance();
	List<Form> formProcess = new ArrayList<Form>();
	List<InfoForm> info = new ArrayList<InfoForm>();
	

	public static void main(String[] args){


		launch(args);
	}
	
	public List<InfoForm> getListOfInfoForm(){
		
		FederalInfoWorksheet infowks = new FederalInfoWorksheet();
		info.add(infowks);
		try {
			infowks.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
		
	}
	
	public List<Form> getListOfForm(){

		
		
		Form1040 frm1040=new Form1040(fillingStatus);
		Map<String,Double> form1040=new HashMap<String,Double>();

		try {
			frm1040.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String,Double> w2tax1=new HashMap<String,Double>();
		Map<String,Double> w2tax2=new HashMap<String,Double>();

		
		FormW2 w2f1=new FormW2();
		w2f1.setForm(w2tax1);
		w2f1.setName("f1");
		try {
			w2f1.load();
			w2tax1=w2f1.getForm();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FormW2 w2f2=new FormW2();
		w2f2.setForm(w2tax2);
		w2f2.setName("f2");
		try {
			w2f2.load();
			w2tax2=w2f2.getForm();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		fillingforms.putForm(TaxConstant.FORM_W2+"-1", w2tax1);
		fillingforms.putForm(TaxConstant.FORM_W2+"-2", w2tax2);
		
		
		Form1099DIV f1099div01=new Form1099DIV();
		f1099div01.setName("ETrade");
		try {
			f1099div01.load();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f1099div01.init();
		
		Form1099DIV f1099div02=new Form1099DIV();
		f1099div02.setName("ETrade TW");
		try {
			f1099div02.load();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f1099div02.init();

		Form1099INT f1099int01=new Form1099INT();
		f1099int01.setName("ETrade Wei");
		try {
			f1099int01.load();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f1099int01.init();

		Form1099INT f1099int02=new Form1099INT();
		f1099int02.setName("Citibank Wei");
		try {
			f1099int02.load();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f1099int02.init();

		//		fillingforms.putForm(f1099div01.getName(),f1099div01);

		
		Form1040ScheduleB scheduleB=new Form1040ScheduleB();
		scheduleB.setFillingForms(fillingforms);
		Map<String,Double> interests = new HashMap<String,Double>();
		scheduleB.setInterests(interests);
		Map<String,Double> dividends = new HashMap<String,Double>();
		Map<String,Double> qdividends = new HashMap<String,Double>();
		
		scheduleB.setOrdinaryDividends(dividends);
		scheduleB.setQualifiedDividends(qdividends);
		try {
			scheduleB.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scheduleB.init();
		fillingforms=scheduleB.getFillingForms();
		
		fillingforms.print();
		
		Form1040ScheduleD scheduleD=new Form1040ScheduleD();
		scheduleD.setFillingForms(fillingforms);
		Map<String,Double> schD= new HashMap<String,Double>();
//		schD.put("9e", 3490d);
//		schD.put("9f", 3060d);
		scheduleD.setForm(schD);
		try {
			scheduleD.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		scheduleD.init();
		fillingforms=scheduleD.getFillingForms();
		fillingforms.print();
		
		Form1040ScheduleA scheduleA=new Form1040ScheduleA();
		scheduleA.setFillingForms(fillingforms);
		Map<String,Double> schA= new HashMap<String,Double>();
		scheduleA.setForm(schA);
		try {
			scheduleA.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduleA.init();
		fillingforms=scheduleA.getFillingForms();
		fillingforms.print();
		
		

		frm1040.setFillingForms(fillingforms);
		frm1040.init();
		scheduleA.calculate(form1040);
		fillingforms=scheduleA.getFillingForms();
		fillingforms.print();

		Form1040ScheduleE scheduleE = new Form1040ScheduleE();
		scheduleE.setFillingForms(fillingforms);
		try {
			scheduleE.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scheduleE.init();
		
		CapitalGainWorksheet cptGain=new CapitalGainWorksheet();
		cptGain.setFillingForms(fillingforms);
		
		TaxComputationWorksheet marriedJoin = new TaxComputationWorksheet(FillingStatus.JOIN);

		marriedJoin.init();
		cptGain.setTaxRate4income(marriedJoin);
		
		frm1040.init();
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
		
		Form6521 form6521 = new Form6521(fillingStatus);
		
		form6521.setFillingForms(fillingforms);
		try {
			form6521.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		form6521.init();
		

		frm1040.setForm6521(form6521.getForm6521());
		frm1040.init();
		
		TaxReport taxreport = new TaxReport();
		taxreport.setFillingForms(fillingforms);
		taxreport.init();
		
		
		fillingforms.print();
		formProcess.add(frm1040);
		formProcess.add(form6521);
		formProcess.add(scheduleA);
		formProcess.add(scheduleD);
		formProcess.add(scheduleB);
		formProcess.add(scheduleE);
	    formProcess.add(cptGain);
	    formProcess.add(w2f2);
	    formProcess.add(w2f1);
	    formProcess.add(taxreport);
	    formProcess.add(f1099div01);
	    formProcess.add(f1099div02);
	    formProcess.add(f1099int01);
	    formProcess.add(f1099int02);
		return formProcess;
	}


	public void start(Stage stage) throws Exception {
		stage.setTitle("Federal Tax");
		Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.WHITE);
		SplitPane splitPane = new SplitPane();
		splitPane.prefWidthProperty().bind(scene.widthProperty());
		splitPane.prefHeightProperty().bind(scene.heightProperty());
		
        ObservableList<Form> fillingforms = FXCollections.observableArrayList();
        final ListView<Form> fillingformsView = new ListView<Form>(fillingforms);
        
        ObservableList<InfoForm> infoforms=FXCollections.observableArrayList();
        final ListView<InfoForm> infoView = new ListView<InfoForm>(infoforms);
        
        
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
        linenumber.setCellValueFactory(new PropertyValueFactory<FormLineDetail,String>("lineDescription"));
        lineinput.setEditable(true);
        lineinput.setCellFactory(cellFactory);
//        lineinput.setCellFactory(TextFieldTableCell.forTableColumn());
        lineinput.setCellValueFactory(new PropertyValueFactory<FormLineDetail,String>("value"));
        lineinput.setOnEditCommit(new EventHandler<CellEditEvent<FormLineDetail, String>>() {
        	            @Override
        	            public void handle(CellEditEvent<FormLineDetail, String> t) {
        	            	FormLineDetail linedetail=(FormLineDetail) t.getTableView().getItems().get(t.getTablePosition().getRow());
        	                System.out.println("old val:"+linedetail.getValue());
        	                
        	            	linedetail.setValue(t.getNewValue());
        	            	System.out.println("new val:"+linedetail.getValue());
        	            	if (linedetail.getForm()!=null){
        	            		linedetail.getForm().getForm().put(linedetail.getLineNumber(), Double.valueOf(linedetail.getValue()));
        	            		try {
									linedetail.getForm().save();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
        	            		return;
        	            	}
        	            		
        	            	if (linedetail.getInfoForm()!=null){
        	            		linedetail.getInfoForm().getForm().put(linedetail.getLineNumber(),linedetail.getValue());
        	            		try {
									linedetail.getInfoForm().save();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
        	            	}

        	            }
        	        });



        for (Form f:getListOfForm()){
        fillingforms.add(f);
        }
        
        for(InfoForm f:getListOfInfoForm()){
        	infoforms.add(f);
        }
                
        
        fillingformsView.setPrefWidth(150);
        fillingformsView.setPrefHeight(scene.getHeight());
        
        infoView.setPrefHeight(scene.getHeight());
        infoView.setPrefWidth(150);
        
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
                    cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

						@Override
						public void handle(MouseEvent event) {
							if(fillingformsView.getSelectionModel().getSelectedItem()!=null){
								forminputs.clear();
			                    
			                    forminputs.addAll(transform(fillingformsView.getSelectionModel().getSelectedItem()));
			
							}
							
						}
                    	
                    });

                    return cell;
            
            }
        }); // setCellFactory

        infoView.setCellFactory(new Callback<ListView<InfoForm>, ListCell<InfoForm>>() {

            public ListCell<InfoForm> call(ListView<InfoForm> param) {
                final Label leadLbl = new Label();
                final Tooltip tooltip = new Tooltip();
                    final ListCell<InfoForm> cell = new ListCell<InfoForm>() {
                        @Override 
                        public void updateItem(InfoForm item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    leadLbl.setText(item.getName());
                                    setText(item.getName());
                                    tooltip.setText(item.getDescription());
                                    setTooltip(tooltip);
                                }
                        }
                    }; // ListCell
                    cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

						@Override
						public void handle(MouseEvent event) {
							if(infoView.getSelectionModel().getSelectedItem()!=null){
								forminputs.clear();
			                    
			                    forminputs.addAll(transform(infoView.getSelectionModel().getSelectedItem()));
			
							}
							
						}
                    	
                    });
                    return cell;
            
            }
        }); // setCellFactory
       
        
		VBox leftArea = new VBox(10);
		Label leftLabel = new Label("Filling Forms");
		leftArea.getChildren().add(leftLabel);
		leftArea.getChildren().add(fillingformsView);
		leftArea.getChildren().add(infoView);

		
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

		VBox hbox = new VBox();
		HBox wfbox=new HBox();
		Button stage1 = new Button("Personal information");
		Button stage2 = new Button("Wage and Income");
		Button stage3 = new Button("Credit and Deduction");
		Button stage4 = new Button("Calculate Tax");

		
		stage4.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			           for(Form fm:formProcess){
			        	   fm.init();
			           }
			        }
			});
		wfbox.getChildren().add(stage1);
		wfbox.getChildren().add(stage2);
		wfbox.getChildren().add(stage3);
		wfbox.getChildren().add(stage4);
		hbox.getChildren().add(wfbox);
		hbox.getChildren().add(splitPane);
		root.getChildren().add(hbox);
		stage.setScene(scene);
		stage.show();
		
	}
    Callback<TableColumn<FormLineDetail,String>, TableCell<FormLineDetail,String>>    cellFactory
    = new Callback<TableColumn<FormLineDetail,String>, TableCell<FormLineDetail,String>>() 
    		{

				@Override
				public TableCell<FormLineDetail, String> call(
						TableColumn<FormLineDetail, String> arg0) {
					
					return new EditingCell();
				} 
    };
    

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
