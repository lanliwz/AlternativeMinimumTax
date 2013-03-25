package com.upuptaxfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.upuptax.form.FederalInfoWorksheet;
import com.upuptax.form.FormLineDetail;
import com.upuptax.form.InfoForm;
import com.upuptax.io.FileUtil;
import com.upuptax.ui.EditingCell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.util.Callback;

public class UpupTaxFXMainController implements Initializable {
	private UpupTaxFX app;
	
	@FXML
	private TextField stateTaxAmount;
	@FXML
	private TextField federalTaxAmount;
	@FXML
	private TextField fileName;
	
    @FXML
    private ListView<InfoForm> infoView;
    
    
    @FXML
    private TableColumn<FormLineDetail,String> linenumber;
    @FXML
    private TableColumn<FormLineDetail,String> lineinput;
    @FXML
    private TableView<FormLineDetail> formInputView;
    
    private List<InfoForm> info = new ArrayList<InfoForm>();
    private ObservableList<InfoForm>    infoforms=FXCollections.observableArrayList();
    private ObservableList<FormLineDetail> forminputs=FXCollections.observableArrayList();
    private ObservableMap<String,Double> form = FXCollections.observableHashMap();
    
    private Popup popupWin;

    public void setApp(UpupTaxFX app){
    	this.app=app;
    	
    }
    public void init(){
    	fileName.setText(app.getFileName());
    	createPersonalInfoNode();
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		popupWin = new Popup();
		popupWin.setWidth(200);
		popupWin.setHeight(300);
		AnchorPane apNewFile= new AnchorPane();

		VBox vboxNewFile=VBoxBuilder.create().styleClass("dialog").build();
		apNewFile.getChildren().add(vboxNewFile);
//		vboxNewFile.styleProperty().set("dialog");
		
		vboxNewFile.getChildren().add(new TextField("Enter new file name"));
		vboxNewFile.getChildren().add(new Button("Save"));
		vboxNewFile.getChildren().add(new Button("Cancel"));
		popupWin.getContent().addAll(apNewFile);
		popupWin.autoHideProperty().set(true);
		popupWin.hide();
		
		if(app!=null)
			fileName.setText(app.getFileName());
		federalTaxAmount.setText("$0");
		stateTaxAmount.setText("$0");
//		createPersonalInfoNode();
//		fillListView4TaxFile();
	
	}
	@FXML
	private void exit(){
		System.exit(0);
	}
	@FXML
	private void createNewTaxFile(){
		
		popupWin.show(app.getStage());
	}
	
	@FXML
	private void fillListView4TaxFile(){
			
			app.gotoFilePicker();
			
		
	}
	
	
    
	private void createPersonalInfoNode() {
	    if(app==null || app.getFileName()==null){
	    	return;
	    }
        
        formInputView.setItems(forminputs);
        linenumber.setCellValueFactory(new PropertyValueFactory<FormLineDetail,String>("lineDescription"));
        lineinput.setCellFactory(cellFactory);
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
                
        
        infoView.setItems(infoforms);
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
	public List<InfoForm> getListOfInfoForm(){
		
		FederalInfoWorksheet infowks = new FederalInfoWorksheet(app.getFileName());
		info.add(infowks);
		try {
			infowks.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;		
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
