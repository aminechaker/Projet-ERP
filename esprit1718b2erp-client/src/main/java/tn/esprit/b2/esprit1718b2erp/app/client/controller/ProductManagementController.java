package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.scene.control.Tab;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ReportingServiceRemote;

public class ProductManagementController implements Initializable {

	@FXML
	private Tab productlistTab;
	    
	@FXML
    private JFXComboBox<RawMaterial> components;

	@FXML
	private TableColumn<?, ?> priceTable;

	@FXML
	private TableColumn<?, ?> descTable;

	@FXML
	private Label descLabel;

	@FXML
	private JFXTextField description;

	@FXML
	private TextArea componentList;

	@FXML
	private TableColumn<?, ?> descTC;

	@FXML
	private JFXComboBox<Product> productComboBox;

	@FXML
	private JFXTextField newPrice;

	@FXML
	private JFXTextField marque;

	@FXML
	private JFXTextField price;

	@FXML
	private TableColumn<?, ?> quantityTable;

	@FXML
	private Label brandLabel;

	@FXML
	private TableColumn<?, ?> idTC;

	@FXML
	private Label priceLabel;

	@FXML
	private Label compLabel;

	@FXML
	private TableView<Product> ProductTable;

	@FXML
	private TableView<RawMaterial> CompononentTable;

	@FXML
	private JFXComboBox<Product> ProductCombobox;

	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/RawMaterialService!tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote";
	private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductService!tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote";
	private String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/ReportingService!tn.esprit.b2.esprit1718b2erp.services.ReportingServiceRemote";
	private String jndi4 = "esprit1718b2erp-ear/esprit1718b2erp-service/AssignementService!tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote";
	private String jndi5 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductionService!tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote";
	List<String> desc = new ArrayList<String>();
	List<RawMaterial> list = new ArrayList<>();
	ObservableList<String> items;
	int x = 0;

	@FXML
	void addComponents(ActionEvent event) {
		for (String string : desc) {
			if (string.equalsIgnoreCase(components.getValue().toString())) {
				x++;
			}
		}
		if (x == 0) {
			desc.add(components.getValue().toString());
			componentList.appendText(components.getValue() + "\n");
		}
		x = 0;
		// items.remove(components.getValue().indexOf(components.getValue()));
	}

	@FXML
	void selectProduct(ActionEvent event) {
		this.DisplayRawMaterialByProduct();
	}

	@FXML
	void AddProduct(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi2);
		RawMaterialServiceRemote rawMaterialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi1);
		AssignementServiceRemote assignementServiceRemote = (AssignementServiceRemote) context.lookup(jndi4);
		if (IsNumber(price.getText())) {
			Product product = new Product(description.getText(), Float.parseFloat(price.getText()));
			productServiceRemote.update(product);
			for (String string : desc) {
				list.add(rawMaterialServiceRemote.findRawMaterialByDescription(string));
			}
			Product product2 = productServiceRemote.findProductByDescription(description.getText());
			for (RawMaterial material : list) {
				assignementServiceRemote.assignRawMaterialToProduct(material, product2);
				material.setQuantity(material.getQuantity() - 1);
				rawMaterialServiceRemote.update(material);
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Eroor");
			alert.setContentText("Please verify the price");
			alert.showAndWait();
		}
		
		this.DisplayProducts();
	}
	public boolean IsNumber(String x){
        boolean verif=true;
        try{
            Float.parseFloat(x);
        }
        catch(NumberFormatException e){
            verif=false;
        }
        return verif;
    }


	@FXML
	void Return(ActionEvent event) {

	}

	@FXML
	void setPrice(ActionEvent event) {
		Context context;
		try {
			context = new InitialContext();
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi2);
			Product product = productServiceRemote.findProductByDescription(productComboBox.getValue().toString());
			newPrice.setText(product.getPrice().toString());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void UpdateProduct(ActionEvent event) {
		Context context;
		try {
			context = new InitialContext();
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi2);
			Product product = productServiceRemote.findProductByDescription(productComboBox.getValue().toString());
			product.setPrice(Float.parseFloat(newPrice.getText()));
			productServiceRemote.update(product);
			this.DisplayProducts();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void DeleteProduct(ActionEvent event) {
		Context context;
		try {
			context = new InitialContext();
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi2);
			Product product = ProductTable.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Warning");
			alert.setContentText("Do you really want to delete this Product");
			alert.showAndWait();
			if (alert.getResult().getText().equalsIgnoreCase("OK")) {
				productServiceRemote.delete(product);
				this.DisplayProducts();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Unable to delete this Product");
			alert.showAndWait();
		}
	}
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.DisplayProducts();
		this.LoadAllProducts();
		this.LoadAllRawMaterials();
		try {
			Context context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi5);
			List<Product> products = this.findProductsByLowQuantity();
			for (Product product : products) {
				Production production = productionServiceRemote.findProductionByProduct(product);
				this.sendGet(production.getEmployee().getEmail(),production.getEmployee().getName(),product.getDescription(),product.getQuantity());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private List<Product> findProductsByLowQuantity() throws NamingException {
			Context context = new InitialContext();
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi2);
			List<Product> list = productServiceRemote.findProductLowQuantity();
			return list;
	}

	public void sendGet(String email, String name, String desc,int quantity ) throws Exception {

		String url = "http://localhost/testmobile/mail1.php?email="+email+"&name="+name+"&desc="+desc+"&quantity="+quantity;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}
	
	private void DisplayRawMaterialByProduct() {
		for (int i = 0; i < CompononentTable.getItems().size(); i++) {
			CompononentTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ReportingServiceRemote reportingServiceRemote = (ReportingServiceRemote) context.lookup(jndi3);
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi2);
			Product product = productServiceRemote.findProductByDescription(ProductCombobox.getValue().toString());
			descTC.setCellValueFactory(new PropertyValueFactory<>("description"));
			idTC.setCellValueFactory(new PropertyValueFactory<>("id"));
			ObservableList<RawMaterial> l = FXCollections
					.observableArrayList(reportingServiceRemote.findRawMaterialsByProduct(product));
			CompononentTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void LoadAllRawMaterials() {
		List<RawMaterial> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			RawMaterialServiceRemote rawMaterialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi1);
			List<RawMaterial> materials = rawMaterialServiceRemote.findAll();
			for (RawMaterial material : materials) {
				descriptions.add(material);
			}
			components.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void LoadAllProducts() {
		List<Product> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi2);
			List<Product> products = productServiceRemote.findAll();
			for (Product product : products) {
				descriptions.add(product);
			}
			productComboBox.setItems(FXCollections.observableArrayList(descriptions));
			ProductCombobox.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void DisplayProducts() {
		for (int i = 0; i < ProductTable.getItems().size(); i++) {
			ProductTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi2);
			descTable.setCellValueFactory(new PropertyValueFactory<>("description"));
			priceTable.setCellValueFactory(new PropertyValueFactory<>("price"));
			quantityTable.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			ObservableList<Product> l = FXCollections.observableArrayList(productServiceRemote.findAll());
			ProductTable.setItems(l);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void updateDescription(ActionEvent event) {
		System.out.println("update");
	}

	@FXML
	void updateQuantity(ActionEvent event) {

	}

}
