package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.InventoryProduct;
import tn.esprit.b2.esprit1718b2erp.entities.InventoryRawMaterial;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.InventoryProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.InventoryRawMaterialService;
import tn.esprit.b2.esprit1718b2erp.services.InventoryRawMaterialServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote;

public class InventoryManagementController implements Initializable {

	private static final String LoadAllProducts = null;
	@FXML
	private TableColumn<?, ?> productT1;

	@FXML
	private JFXRadioButton date_radio1;

	@FXML
	private DatePicker inventoryDate1;

	@FXML
	private TableColumn<?, ?> desc;

	@FXML
	private TableColumn<?, ?> descT;

	@FXML
	private TableColumn<?, ?> employeeT1;

	@FXML
	private TableColumn<?, ?> quantityT2;

	@FXML
	private TableColumn<?, ?> quantityT1;

	@FXML
	private JFXRadioButton date_radio2;

	@FXML
	private JFXComboBox<RawMaterial> rawmaterialComboBox;

	@FXML
	private TableColumn<?, ?> employeeT2;

	@FXML
	private JFXComboBox<Employee> employeeComboBox1;

	@FXML
	private TableView<InventoryProduct> productInventoryTable;

	@FXML
	private JFXTextField priceText;

	@FXML
	private TableColumn<?, ?> priceT1;

	@FXML
	private DatePicker inventoryDate;

	@FXML
	private JFXRadioButton quantity_radio1;

	@FXML
	private JFXRadioButton quantity_radio2;

	@FXML
	private TableColumn<?, ?> rawmaterialT2;

	@FXML
	private JFXComboBox<Product> productComboBox;

	@FXML
	private JFXTextField descText1;
	
	@FXML
	private JFXTextField quantityText1;

	@FXML
	private JFXComboBox<Employee> EmployeeComboBox;

	@FXML
	private JFXRadioButton price_radio1;

	@FXML
	private TableColumn<?, ?> dateT1;

	@FXML
	private JFXTextField quantityText;

	@FXML
	private JFXTextField descText;

	@FXML
	private TableView<InventoryRawMaterial> rawmaterialInventoryTable;

	@FXML
	private TableColumn<?, ?> dateT2;

	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/InventoryProductService!tn.esprit.b2.esprit1718b2erp.services.InventoryProductServiceRemote";
	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/InventoryRawMaterialService!tn.esprit.b2.esprit1718b2erp.services.InventoryRawMaterialServiceRemote";
	private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/RawMaterialService!tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote";
	private String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductService!tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote";
	private String jndi4 = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";
	private String jndi5 = "esprit1718b2erp-ear/esprit1718b2erp-service/AssignementService!tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote";

	@FXML
	void addProductInventory(ActionEvent event) {
		try {
			Context context = new InitialContext();
			InventoryProductServiceRemote inventoryProductServiceRemote = (InventoryProductServiceRemote) context
					.lookup(jndi);
			AssignementServiceRemote assignementServiceRemote = (AssignementServiceRemote) context.lookup(jndi5);
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi3);
			Date date = new Date();
			if ((java.sql.Date.valueOf(inventoryDate.getValue())).compareTo(date) > -1 ) {
				InventoryProduct inventoryProduct = new InventoryProduct(descText.getText(),
						java.sql.Date.valueOf(inventoryDate.getValue()), Integer.parseInt(quantityText.getText()),
						Float.parseFloat(priceText.getText()));
				assignementServiceRemote.assignInventoryToProductAndEmployee(inventoryProduct, productComboBox.getValue(),
						EmployeeComboBox.getValue());
				
				productComboBox.getValue().setQuantity(Integer.parseInt(quantityText.getText()));
				productServiceRemote.update(productComboBox.getValue());
				this.DisplayProductInventory();
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Date invalid !");
				alert.showAndWait();
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void getPrice(ActionEvent event) {
		priceText.setText(productComboBox.getValue().getPrice().toString());
	}

	@FXML
	void addRawMaterailInventory(ActionEvent event) {
		try {
			Context context = new InitialContext();
			InventoryRawMaterialServiceRemote inventoryRawMaterialServiceRemote = (InventoryRawMaterialServiceRemote) context
					.lookup(jndi1);
			AssignementServiceRemote assignementServiceRemote = (AssignementServiceRemote) context.lookup(jndi5);
			RawMaterialServiceRemote materialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi2);
			Date date = new Date();
			if ((java.sql.Date.valueOf(inventoryDate1.getValue())).compareTo(date) > -1) {
				InventoryRawMaterial inventoryRawMaterial = new InventoryRawMaterial(descText1.getText(),
						java.sql.Date.valueOf(inventoryDate1.getValue()), Integer.parseInt(quantityText1.getText()));
				assignementServiceRemote.assingInventoryToRawMaterialAndEmployee(inventoryRawMaterial,
						rawmaterialComboBox.getValue(), employeeComboBox1.getValue());
				rawmaterialComboBox.getValue().setQuantity(Integer.parseInt(quantityText1.getText()));
				materialServiceRemote.update(rawmaterialComboBox.getValue());
				
				this.DisplayRawMaterialInventory();
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Date invalid !");
				alert.showAndWait();
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void sortProductByDate(ActionEvent event) {
		price_radio1.setSelected(false);
		quantity_radio1.setSelected(false);
		this.DisplayProductInventorySortedByDate();
	}

	private void DisplayProductInventorySortedByDate() {
		try {
			Context context = new InitialContext();
			InventoryProductServiceRemote inventoryProductServiceRemote = (InventoryProductServiceRemote) context
					.lookup(jndi);
			ObservableList<InventoryProduct> l = FXCollections
					.observableArrayList(inventoryProductServiceRemote.sortByDate());
			productInventoryTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void sortProductByPrice(ActionEvent event) {
		date_radio1.setSelected(false);
		quantity_radio1.setSelected(false);
		this.DisplayProductInventorySortedByPrice();
	}

	private void DisplayProductInventorySortedByPrice() {
		try {
			Context context = new InitialContext();
			InventoryProductServiceRemote inventoryProductServiceRemote = (InventoryProductServiceRemote) context
					.lookup(jndi);
			ObservableList<InventoryProduct> l = FXCollections
					.observableArrayList(inventoryProductServiceRemote.sortByPrice());
			productInventoryTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void sortProductByQuantity(ActionEvent event) {
		date_radio1.setSelected(false);
		price_radio1.setSelected(false);
		this.DisplayProductInventorySortedByQuantity();
	}

	private void DisplayProductInventorySortedByQuantity() {
		try {
			Context context = new InitialContext();
			InventoryProductServiceRemote inventoryProductServiceRemote = (InventoryProductServiceRemote) context
					.lookup(jndi);
			ObservableList<InventoryProduct> l = FXCollections
					.observableArrayList(inventoryProductServiceRemote.sortByQuantity());
			productInventoryTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void sortMaterialByDate(ActionEvent event) {
		quantity_radio2.setSelected(false);
		this.DisplayRawMaterialInventorySortedByDate();
	}

	private void DisplayRawMaterialInventorySortedByDate() {
		try {
			Context context = new InitialContext();
			InventoryRawMaterialServiceRemote inventoryRawMaterialServiceRemote = (InventoryRawMaterialServiceRemote) context
					.lookup(jndi1);
			ObservableList<InventoryRawMaterial> l = FXCollections
					.observableArrayList(inventoryRawMaterialServiceRemote.sortByDate());
			rawmaterialInventoryTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void sortMaterialByQuantity(ActionEvent event) {
		date_radio2.setSelected(false);
		this.DisplayRawMaterialInventorySortedByQuantity();
	}

	private void DisplayRawMaterialInventorySortedByQuantity() {
		try {
			Context context = new InitialContext();
			InventoryRawMaterialServiceRemote inventoryRawMaterialServiceRemote = (InventoryRawMaterialServiceRemote) context
					.lookup(jndi1);
			ObservableList<InventoryRawMaterial> l = FXCollections
					.observableArrayList(inventoryRawMaterialServiceRemote.sortByQuantity());
			rawmaterialInventoryTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.LoadAllEmployees();
		this.LoadAllProducts();
		this.LoadAllRawMaterials();
		this.DisplayProductInventory();
		this.DisplayRawMaterialInventory();
	}

	private void DisplayRawMaterialInventory() {
		for (int i = 0; i < rawmaterialInventoryTable.getItems().size(); i++) {
			rawmaterialInventoryTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			InventoryRawMaterialServiceRemote inventoryRawMaterialServiceRemote = (InventoryRawMaterialServiceRemote) context
					.lookup(jndi1);
			desc.setCellValueFactory(new PropertyValueFactory<>("description"));
			dateT2.setCellValueFactory(new PropertyValueFactory<>("dateInventory"));
			rawmaterialT2.setCellValueFactory(new PropertyValueFactory<>("material"));
			quantityT2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			employeeT2.setCellValueFactory(new PropertyValueFactory<>("employee"));
			ObservableList<InventoryRawMaterial> l = FXCollections
					.observableArrayList(inventoryRawMaterialServiceRemote.findAll());
			rawmaterialInventoryTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void DisplayProductInventory() {
		for (int i = 0; i < productInventoryTable.getItems().size(); i++) {
			productInventoryTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			InventoryProductServiceRemote inventoryProductServiceRemote = (InventoryProductServiceRemote) context
					.lookup(jndi);
			descT.setCellValueFactory(new PropertyValueFactory<>("description"));
			dateT1.setCellValueFactory(new PropertyValueFactory<>("dateInventory"));
			productT1.setCellValueFactory(new PropertyValueFactory<>("product"));
			quantityT1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			priceT1.setCellValueFactory(new PropertyValueFactory<>("price"));
			employeeT1.setCellValueFactory(new PropertyValueFactory<>("employee"));
			ObservableList<InventoryProduct> l = FXCollections
					.observableArrayList(inventoryProductServiceRemote.findAll());
			productInventoryTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void LoadAllProducts() {
		List<Product> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi3);
			List<Product> products = productServiceRemote.findAll();
			for (Product product : products) {
				descriptions.add(product);
			}
			productComboBox.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void LoadAllRawMaterials() {
		List<RawMaterial> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			RawMaterialServiceRemote rawMaterialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi2);
			List<RawMaterial> materials = rawMaterialServiceRemote.findAll();
			for (RawMaterial material : materials) {
				descriptions.add(material);
			}
			rawmaterialComboBox.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void LoadAllEmployees() {
		List<Employee> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			EmployeeServiceRemote employeeServiceRemote = (EmployeeServiceRemote) context.lookup(jndi4);
			List<Employee> employees = employeeServiceRemote.findAll();
			for (Employee employee : employees) {
				descriptions.add(employee);
			}
			EmployeeComboBox.setItems(FXCollections.observableArrayList(descriptions));
			employeeComboBox1.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

}
