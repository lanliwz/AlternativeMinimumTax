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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class UpupTaxFXMainController implements Initializable {
	@FXML
	private Label stateTaxAmount;
	@FXML
	private Label federalTaxAmount;
    @FXML
    private ListView<InfoForm> infoView;
    
    @FXML
    private ListView<String> textListView;
    
    private ObservableList<String> textList;
    @FXML
    private TableColumn<FormLineDetail,String> linenumber;
    @FXML
    private TableColumn<FormLineDetail,String> lineinput;
    @FXML
    private TableView<FormLineDetail> formInputView;
    
    private List<InfoForm> info = new ArrayList<InfoForm>();
    private ObservableList<InfoForm> infoforms;
    private ObservableList<FormLineDetail> forminputs;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		federalTaxAmount.setText("$0");
		stateTaxAmount.setText("$0");
		createPersonalInfoNode();
//		textList=FXCollections.observableArrayList();
//		textList.add("Hello");
//		textList.add("You");
//		textListView = new ListView<String>(textList);
//		textListView.setItems(textList);
		
	}
    
	private void createPersonalInfoNode() {
	
        
        infoforms=FXCollections.observableArrayList();
        
        
        
        
        ObservableMap<String,Double> form = FXCollections.observableHashMap();
        
        linenumber = new TableColumn<FormLineDetail,String>("Line Number");
        
        
        
        
        
        lineinput = new TableColumn<FormLineDetail,String>("Value");
       
        
        formInputView = new TableView<FormLineDetail>();
        
        forminputs=FXCollections.observableArrayList();
        formInputView.setItems(forminputs);
        formInputView.getColumns().addAll(linenumber,lineinput);
        formInputView.setEditable(true);
        linenumber.setEditable(false);
        linenumber.setCellValueFactory(new PropertyValueFactory<FormLineDetail,String>("lineDescription"));
        lineinput.setEditable(true);
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
