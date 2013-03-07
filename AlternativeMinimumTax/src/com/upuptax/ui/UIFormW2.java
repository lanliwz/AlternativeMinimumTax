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
import com.upuptax.io.FileUtil;
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
import javafx.scene.shape.CircleBuilder;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuBarBuilder;
import javafx.scene.control.MenuBuilder;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabBuilder;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPaneBuilder;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableViewBuilder;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleButtonBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ToolBarBuilder;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;


public class UIFormW2 extends Application {
	FillingStatus fillingStatus=FillingStatus.JOIN;
	FillingFormsAndSchedules fillingforms=FillingFormsAndSchedules.newInstance();
	List<Form> ferderaforms = new ArrayList<Form>();
	List<InfoForm> info = new ArrayList<InfoForm>();
	List<Form> wagesAndIncomes = new ArrayList<Form>();
	List<String> filenames;
	String fillingName="wei_tax_test";

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

		
		ferderaforms.clear();
		Form1040 frm1040=new Form1040(fillingStatus);
		Map<String,Double> form1040=new HashMap<String,Double>();

		try {
			frm1040.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

//		Map<String,Double> w2tax1=new HashMap<String,Double>();
//		Map<String,Double> w2tax2=new HashMap<String,Double>();
//
//		
//		FormW2 w2f1=new FormW2();
//		w2f1.setForm(w2tax1);
//		w2f1.setName("f1");
//		try {
//			w2f1.load();
//			w2tax1=w2f1.getForm();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		FormW2 w2f2=new FormW2();
//		w2f2.setForm(w2tax2);
//		w2f2.setName("f2");
//		try {
//			w2f2.load();
//			w2tax2=w2f2.getForm();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		if(wagesAndIncomes!=null)
		for (Form f:wagesAndIncomes){
			if(f.getName().contains(TaxConstant.FORM_W2))
				fillingforms.putForm(f.getName(), f.getForm());
			

		}

		
		
//		fillingforms.putForm(TaxConstant.FORM_W2+"-1", w2tax1);
//		fillingforms.putForm(TaxConstant.FORM_W2+"-2", w2tax2);
		
		
//		Form1099DIV f1099div01=new Form1099DIV();
//		f1099div01.setName("ETrade");
//		try {
//			f1099div01.load();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		f1099div01.init();
//		
//		Form1099DIV f1099div02=new Form1099DIV();
//		f1099div02.setName("ETrade TW");
//		try {
//			f1099div02.load();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		f1099div02.init();
//
//		Form1099INT f1099int01=new Form1099INT();
//		f1099int01.setName("ETrade Wei");
//		try {
//			f1099int01.load();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		f1099int01.init();
//
//		Form1099INT f1099int02=new Form1099INT();
//		f1099int02.setName("Citibank Wei");
//		try {
//			f1099int02.load();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		f1099int02.init();

		//		fillingforms.putForm(f1099div01.getName(),f1099div01);

		
		Form1040ScheduleB scheduleB=new Form1040ScheduleB();
		scheduleB.setFillingForms(fillingforms);
		Map<String,Double> interests = new HashMap<String,Double>();
		Map<String,Double> dividends = new HashMap<String,Double>();
		Map<String,Double> qdividends = new HashMap<String,Double>();
		if(wagesAndIncomes!=null)
		for (Form f:wagesAndIncomes){
			if(f.getName().contains(TaxConstant.FORM_1099_INT))
				interests.put(f.getName(), f.getForm().get("1"));
			if(f.getName().contains(TaxConstant.FORM_1099_DIV))
			{
				dividends.put(f.getName(), f.getForm().get("1a"));
				qdividends.put(f.getName(), f.getForm().get("1b"));	
			}
			

		}
//		interests.put(f1099int01.getName(), f1099int01.getForm().get("1"));
//		interests.put(f1099int02.getName(), f1099int02.getForm().get("1"));
		scheduleB.setInterests(interests);


//		dividends.put(f1099div01.getName(), f1099div01.getForm().get("1a"));
//		dividends.put(f1099div02.getName(), f1099div02.getForm().get("1a"));
//
//		qdividends.put(f1099div01.getName(), f1099div01.getForm().get("1b"));
//		qdividends.put(f1099div02.getName(), f1099div02.getForm().get("1b"));
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
		ferderaforms.add(frm1040);
		ferderaforms.add(form6521);
		ferderaforms.add(scheduleA);
		ferderaforms.add(scheduleD);
		ferderaforms.add(scheduleB);
		ferderaforms.add(scheduleE);
	    ferderaforms.add(cptGain);
//	    formProcess.add(w2f2);
//	    formProcess.add(w2f1);
	    ferderaforms.add(taxreport);
//	    formProcess.add(f1099div01);
//	    formProcess.add(f1099div02);
//	    formProcess.add(f1099int01);
//	    formProcess.add(f1099int02);
		return ferderaforms;
	}

	
	public Form createW2Form(String name) throws IOException {
		Map<String,Double> w2tax1=new HashMap<String,Double>();
		FormW2 w2f1=new FormW2();
		w2f1.setForm(w2tax1);
		w2f1.setName(name);
		w2f1.load();
		w2f1.init();
		return w2f1;
			

	}
	public Form create1099DivForm(String name) throws IOException {
		Form1099DIV f1099div01=new Form1099DIV();
		f1099div01.setName(name);
		
		f1099div01.load();
		
		f1099div01.init();
		
		return f1099div01;
			

	}

	public Form create1099IntForm(String name) throws IOException {
		Form1099INT f1099=new Form1099INT();
		f1099.setName(name);
		
		f1099.load();
		
		f1099.init();
		
		return f1099;
			

	}

	public List<Form> getWagesAndIncomeForms(){
		for(String fname:filenames){
			if(fname.startsWith(TaxConstant.FORM_W2)){
				try {
					
					wagesAndIncomes.add(createW2Form(fname.replace(".properies", "")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(fname.startsWith(TaxConstant.FORM_1099_DIV)){
				try {
//					create1099DivForm(fname.replace(".properies", ""));
					wagesAndIncomes.add(create1099DivForm(fname.replace(".properies", "")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(fname.startsWith(TaxConstant.FORM_1099_INT)){
				try {
					
					wagesAndIncomes.add(create1099IntForm(fname.replace(".properies", "")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
		}

		return wagesAndIncomes;
	}


	public void start(Stage stage) throws Exception {
		filenames=FileUtil.getListOfFiledForms(fillingName);
		stage.setTitle("Federal Tax");
		Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.WHITE);
        

		VBox hbox = new VBox();
		HBox wfbox=new HBox();
		Button stage1 = new Button("Personal information");
		Button stage2 = new Button("Wage and Income");
		Button stage3 = new Button("Credit and Deduction");
		Button stage4 = new Button("Calculate Tax");

		
		stage4.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			           for(Form fm:ferderaforms){
			        	   fm.init();
			           }
			        }
			});
		wfbox.getChildren().add(stage1);
		wfbox.getChildren().add(stage2);
		wfbox.getChildren().add(stage3);
		wfbox.getChildren().add(stage4);
		hbox.getChildren().add(createMenus());
		hbox.getChildren().add(createToolBar());
		hbox.getChildren().add(createTabs(scene));
//		hbox.getChildren().add(wfbox);
//		hbox.getChildren().add(splitPane);
		
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
    
    public MenuBar createMenus() {
        MenuBar menuBar = MenuBarBuilder.create()
          .menus(
            MenuBuilder.create()
              .text("File")
              .items(
                MenuItemBuilder.create()
                  .text("New...")
//                  .graphic((new ImageView(
//                    new Image(getClass().getResourceAsStream("images/paper.png"))))
//                  )
                  .accelerator(KeyCombination.keyCombination("Ctrl+N"))
                  .onAction(new EventHandler<ActionEvent>() {
                      @Override public void handle(ActionEvent e) {
                        System.out.println(e.getEventType() + 
                                          " occurred on MenuItem New");
                      }
                    }
                  )
                  .build(),
                MenuItemBuilder.create()
                  .text("Save")
                  .build()
              ).build(),
              MenuBuilder.create()
              .text("Edit")
              .items(
                MenuItemBuilder.create()
                  .text("Cut")
                  .build(),
                MenuItemBuilder.create()
                  .text("Copy")
                  .build(),
                MenuItemBuilder.create()
                  .text("Paste")
                  .build()
              )
    
             .build()
          )
          .build();
    
       return menuBar;
      }
    public ToolBar createToolBar() {
        final ToggleGroup alignToggleGroup = new ToggleGroup();
        ToolBar toolBar = ToolBarBuilder.create()
          .items(
            ButtonBuilder.create()
              .id("newButton")
//              .graphic(new ImageView(
//                new Image(getClass().getResourceAsStream("images/paper.png")))
//              )
              .tooltip(new Tooltip("New Document... Ctrl+N"))
              .onAction(new EventHandler<ActionEvent>() {
                  @Override public void handle(ActionEvent e) {
                    System.out.println("New toolbar button clicked");
                  }
                })
              .build(),
              ButtonBuilder.create()
              .id("editButton")
              .graphic(
                CircleBuilder.create()
                  .fill(Color.GREEN)
                  .radius(8)
                  .build()
              )
              .build(),
            ButtonBuilder.create()
              .id("deleteButton")
              .graphic(
                CircleBuilder.create()
                  .fill(Color.BLUE)
                  .radius(8)
                  .build()
              )
              .build(),
            new Separator(Orientation.VERTICAL),
            ToggleButtonBuilder.create()
              .id("boldButton")
              .graphic(
                CircleBuilder.create()
                  .fill(Color.MAROON)
                  .radius(8)
                  .build()
              )
              .onAction(new EventHandler<ActionEvent>() {
                  @Override public void handle(ActionEvent e) {
                    ToggleButton tb = ((ToggleButton)e.getTarget());
                    System.out.print(e.getEventType() + " occurred on ToggleButton " 
                                    + tb.getId());
    
                   System.out.print(", and selectedProperty is: ");
                    System.out.println(tb.selectedProperty().getValue());
                  }
                })
              .build(),
            ToggleButtonBuilder.create()
              .id("italicButton")
              .graphic(
                CircleBuilder.create()
                  .fill(Color.YELLOW)
                  .radius(8)
                  .build()
              )
              .onAction(new EventHandler<ActionEvent>() {
                  @Override public void handle(ActionEvent e) {
                    ToggleButton tb = ((ToggleButton)e.getTarget());
                    System.out.print(e.getEventType() + " occurred on ToggleButton " 
                                    + tb.getId());
                    System.out.print(", and selectedProperty is: ");
                    System.out.println(tb.selectedProperty().getValue());
                  }
                })
              .build(),
            new Separator(Orientation.VERTICAL),
            ToggleButtonBuilder.create()
              .id("leftAlignButton")
              .toggleGroup(alignToggleGroup)
              .graphic(
                CircleBuilder.create()
                  .fill(Color.PURPLE)
                  .radius(8)
                  .build()
              )
              .build(),
            ToggleButtonBuilder.create()
              .id("centerAlignButton")
              .toggleGroup(alignToggleGroup)
              .graphic(
                CircleBuilder.create()
                  .fill(Color.ORANGE)
                  .radius(8)
                  .build()
              )
              .build(),
            ToggleButtonBuilder.create()
              .id("rightAlignButton")
              .toggleGroup(alignToggleGroup)
              .graphic(
    
               CircleBuilder.create()
                  .fill(Color.CYAN)
                  .radius(8)
                  .build()
              )
              .build()
          )
          .build();
    
       alignToggleGroup.selectToggle(alignToggleGroup.getToggles().get(0));
        alignToggleGroup.selectedToggleProperty().addListener(new ChangeListener() {
          public void changed(ObservableValue ov, Object oldValue, Object newValue) {
            ToggleButton tb = ((ToggleButton)alignToggleGroup.getSelectedToggle());
            if (tb != null) {
                System.out.println(tb.getId() + " selected");
            }
          }
        });
    
       return toolBar;
      } 
    public TabPane createTabs(Scene scene) {
        final WebView webView;
        TabPane tabPane = TabPaneBuilder.create()
          .tabs(
            TabBuilder.create()
              .text("Personal Information")
              .content(createPersonalInfoNode(scene))
              .closable(false)
              .build(),
            TabBuilder.create()
              .text("Wages and Incomes")
              .content(createWagesAndIncomesNode(scene))
              .closable(false)
              .build(),           
              TabBuilder.create()
              .text("Federal Tax")
              .content(createFederalTaxNode(scene))
              .closable(false)
              .build())

          .build();
    
       return tabPane;
      }   
    public Node createPersonalInfoNode(Scene scene) {
		SplitPane splitPane = new SplitPane();
		
        
        ObservableList<InfoForm> infoforms=FXCollections.observableArrayList();
        final ListView<InfoForm> infoView = new ListView<InfoForm>(infoforms);
        
        
        ObservableMap<String,Double> form = FXCollections.observableHashMap();
        
        TableColumn<FormLineDetail,String> linenumber = new TableColumn<FormLineDetail,String>("Line Number");
        linenumber.setPrefWidth(500);
        
        
        
        
        TableColumn<FormLineDetail,String> lineinput = new TableColumn<FormLineDetail,String>("Value");
        lineinput.setPrefWidth(200);
        
        TableView<FormLineDetail> formInputView = new TableView<FormLineDetail>();
        formInputView.setPrefHeight(scene.getHeight());
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



        
        for(InfoForm f:getListOfInfoForm()){
        	infoforms.add(f);
        }
                
        
        

        infoView.setPrefWidth(150);
        infoView.setPrefHeight(scene.getHeight());
        
        // display first and last name with tooltip using alias
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


        
        return splitPane;
      } 
    


    public Node createWagesAndIncomesNode(Scene scene) {
		SplitPane splitPane = new SplitPane();
		splitPane.prefWidthProperty().bind(scene.widthProperty());
		splitPane.prefHeightProperty().bind(scene.heightProperty());

		
        final ObservableList<Form> fillingforms = FXCollections.observableArrayList();
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



        for (Form f:getWagesAndIncomeForms()){
        fillingforms.add(f);
        }
        
                
        
        fillingformsView.setPrefWidth(150);
        fillingformsView.setPrefHeight(scene.getHeight());
        
        
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

        
		VBox leftArea = new VBox(10);
		Label leftLabel = new Label("Filling Forms");
		leftArea.getChildren().add(leftLabel);
		final TextArea fname = new TextArea("Type your form name here");
		leftArea.getChildren().add(fname);
		Button addW2 = new Button("Add New W-2");
		leftArea.getChildren().add(addW2);
		Button add1099Div = new Button("Add New 1099 DIV");
		leftArea.getChildren().add(add1099Div);
		Button add1099Int = new Button("Add New 1099 INT");
		add1099Int.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent e) {
	        	try {
					Form nform = create1099IntForm(fname.getText());
					fillingforms.add(nform);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        	
	        	   
	           
	        }}
	        );
		leftArea.getChildren().add(add1099Int);
		leftArea.getChildren().add(fillingformsView);


		
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


        
        return splitPane;
      } 
    
    public Node createFederalTaxNode(Scene scene) {
		SplitPane splitPane = new SplitPane();
		splitPane.prefWidthProperty().bind(scene.widthProperty());
		splitPane.prefHeightProperty().bind(scene.heightProperty());

		
        final ObservableList<Form> fillingforms = FXCollections.observableArrayList();
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
        
                
        
        fillingformsView.setPrefWidth(150);
        fillingformsView.setPrefHeight(scene.getHeight());
        
        
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

        
		VBox leftArea = new VBox(10);
		Label leftLabel = new Label("Filling Forms");
		leftArea.getChildren().add(leftLabel);
		Button refresh = new Button("Refresh");
		refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent e) {
	        	fillingforms.clear();
	        	
	        	for(Form fm:getListOfForm()){
	        	   fillingforms.add(fm);
	           }
	        }
	});
		leftArea.getChildren().add(refresh);
		leftArea.getChildren().add(fillingformsView);


		
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


        
        return splitPane;
      } 



}
