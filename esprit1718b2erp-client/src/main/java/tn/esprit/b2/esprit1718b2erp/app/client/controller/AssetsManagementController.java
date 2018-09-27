package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Observable;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;	
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.AssetsServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.QuotationServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialService;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote;
public class AssetsManagementController implements Initializable {
	 @FXML
	    private BarChart<?, ?> barchart;

	    @FXML
	    private TableView<Assets> assetstable;

	    @FXML
	    private TableColumn<?, ?> typeT;

	    @FXML
	    private JFXTextField newvalue;

	    @FXML
	    private TableColumn<?, ?> nameT;

	    @FXML
	    private JFXButton submitasset;

	    @FXML
	    private TableColumn<?, ?> valueT;

	    @FXML
	    private JFXButton submitnvalue;

	    @FXML
	    private JFXComboBox<Assets> assettypecombobox;

	    @FXML
	    private JFXTextField assetname;

	    @FXML
	    private JFXTextField assetname2;

	    @FXML
	    private JFXTextField assetavalue;
	    
	    private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/AssetstService!tn.esprit.b2.esprit1718b2erp.services.AssetsServiceRemote";
	    private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/BenefitstService!tn.esprit.b2.esprit1718b2erp.services.BenefitsServiceRemote";
	    private String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/ExpensestService!tn.esprit.b2.esprit1718b2erp.services.ExpensesServiceRemote";
	    
	    public List<Assets> assetDecreaseList = new ArrayList<>();
	    public List<Assets> assetIncreaseList = new ArrayList<>();
	    public List<Assets> assetList = new ArrayList<>();
	    
	    @FXML
	    void SaveAsset(ActionEvent event) throws NamingException {
	    	Context context = new InitialContext();
	    	AssetsServiceRemote assetsServiceRemote = (AssetsServiceRemote) context.lookup(jndi);
			Assets asset = new Assets(assettypecombobox.getValue().toString(),assetname.getText(), Float.parseFloat(assetavalue.getText()));
			assetsServiceRemote.update(asset);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("New Asset");
	        alert.setContentText("Asset added");
	        alert.showAndWait();
	        if (alert.getResult().getText().equalsIgnoreCase("OK")) {
	            
	        	this.DisplayAssets();
	        }
	    
	    	this.DisplayAssets();

	    }

	    @FXML
	    void modifyValue(ActionEvent event) {
	    	Context context;
			try {
				context = new InitialContext();
				AssetsServiceRemote assetsServiceRemote = (AssetsServiceRemote) context.lookup(jndi);
		    	Assets asset = assetsServiceRemote.findAssetByName(assetname.getText().toString());
		    	asset.setValue(asset.getActual_value());
		    	asset.setActual_value(Float.parseFloat(newvalue.getText()));
		    	assetsServiceRemote.update(asset);
		        this.DisplayAssets();
			} catch (NamingException e) {
				e.printStackTrace();
			}

	    }
	    public void DisplayAssets(){
	    	for (int i = 0; i < assetstable.getItems().size(); i++) {
	    		assetstable.getItems().clear();
			}
			try {
				Context context = new InitialContext();
				AssetsServiceRemote assetsServiceRemote = (AssetsServiceRemote) context.lookup(jndi);
				typeT.setCellValueFactory(new PropertyValueFactory<>("type"));
				nameT.setCellValueFactory(new PropertyValueFactory<>("name"));
				valueT.setCellValueFactory(new PropertyValueFactory<>("actual_value"));
				ObservableList<Assets> l = FXCollections.observableArrayList(assetsServiceRemote.findAll());
				assetstable.setItems(l);

			} catch (NamingException e) {
				e.printStackTrace();
			}
	    }
	    
	    public void fillList()throws NamingException{
	    	Context context = new InitialContext();
	    	AssetsServiceRemote assetsServiceRemote = (AssetsServiceRemote) context.lookup(jndi2);
	    	
	    	
	    	assetDecreaseList = assetsServiceRemote.getAssetsExp();
	        assetIncreaseList = assetsServiceRemote.getAssetsBen();
	       
	    }
	    
	    public void createBarchart() {
			try{
				Context context = new InitialContext();
				barchart.setTitle("Assets value evoluion");
				AssetsServiceRemote assetsServiceRemote = (AssetsServiceRemote) context.lookup(jndi);
				final CategoryAxis xAxis = new CategoryAxis();
				final NumberAxis yAxis = new NumberAxis();
				final BarChart<String,Number> barchart = new BarChart<String,Number>(xAxis,yAxis);
		        barchart.setTitle("Value evolution");
		        xAxis.setLabel("Asset");       
		        yAxis.setLabel("Value");
		        
		        XYChart.Series series1 = new XYChart.Series();
		        series1.setName("old value");
		        for (int i=0;i<assetList.size();i++){
		        series1.getData().add(new XYChart.Data(assetList.get(i).getType(),assetList.get(i).getValue()));	
		        }
		        XYChart.Series series2 = new XYChart.Series();
		        series1.setName("new value");
                for (int i=0;i<assetList.size();i++){
                series2.getData().add(new XYChart.Data(assetList.get(i).getType(),assetList.get(i).getActual_value()));		
		        }

			
			}
			 catch (NamingException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try {
				this.fillList();
				this.DisplayAssets();
				this.createBarchart();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}