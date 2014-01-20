package com.upuptaxfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.upuptax.form.FederalInfoWorksheet;
import com.upuptax.form.FormLineDetail;
import com.upuptax.form.InfoForm;
import com.upuptax.io.FileUtil;
import com.upuptax.ui.EditingCell;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
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
import javafx.scene.control.MenuItem;
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
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.util.Callback;

public class UpupTaxFXMainController implements Initializable {
	private UpupTaxFX app;
	
	@FXML
	private WebView webViewInfo;
	@FXML
	private VBox vboxPopup;
	
	@FXML
	private TextField stateTaxAmount;
	@FXML
	private TextField federalTaxAmount;
	@FXML
	private TextField fileName;
	private StringProperty strFileName = new SimpleStringProperty();
	
	@FXML
	private TextField saveAsfileName;
	private StringProperty strSaveAsFileName = new SimpleStringProperty();
	
	
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
		fileName.textProperty().bindBidirectional(strFileName);
		saveAsfileName.textProperty().bindBidirectional(strSaveAsFileName);
		webViewInfo.getEngine().load("http://www.google.com");
//		popupWin = new Popup();


//		vboxNewFile.styleProperty().set("dialog");
		
	
		vboxPopup.visibleProperty().set(false);
//		popupWin.getContent().addAll(vboxPopup);
//		popupWin.autoHideProperty().set(true);
//		popupWin.hide();
		
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
	private void createNewTaxFile(ActionEvent event){
		
		MenuItem mitem =(MenuItem)event.getSource();
		if (mitem.getText().equalsIgnoreCase("save as")){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Save As - " +strFileName.getValue() );
			if(strFileName.getValue()!=null){
				saveAsfileName.setText(strFileName.getValue());
				vboxPopup.visibleProperty().set(true);
				vboxPopup.toFront();
			}
			
		} else{
			vboxPopup.visibleProperty().set(true);
			vboxPopup.toFront();
		}
//		popupWin.show(app.getStage());
//		popupWin.show(app.getStage(),app.getStage().getX(),app.getStage().getY());//,.scaleXProperty().getValue(),mitem.getGraphic().scaleYProperty().getValue());
	}
	@FXML
	private void cancelCreateNewTaxFile(ActionEvent event){
		

		
		vboxPopup.visibleProperty().set(false);

	}
	@FXML
	private void commitCreateNewTaxFile(ActionEvent event){
		

		app.getAvailableFiles().add("FILENAME;"+strSaveAsFileName.getValue()+";");
		try {
			FileUtil.saveParameters("FILENAME", "",app.getAvailableFiles() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vboxPopup.visibleProperty().set(false);

	}
	
	
	@FXML
	private void fillListView4TaxFile(){
			
			app.gotoFilePicker();
			
		
	}
	
	
    
	private void createPersonalInfoNode() {
	    if(app==null || app.getFileName()==null){
	    	return;
	    }
        
	    
       
        
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
