package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.input.KeyEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.ProductionStatus;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;

public class ProductionManagementController implements Initializable {

	@FXML
	private JFXComboBox<Employee> employeeComboBox;
	@FXML
	private TableColumn<?, ?> employeeT;
	@FXML
	private DatePicker date;

	@FXML
	private TableColumn<?, ?> descriptionT;

	@FXML
	private Label sortLabel;

	@FXML
	private JFXComboBox<Product> produit;

	@FXML
	private JFXButton searchBtn;

	@FXML
	private JFXTextField description;

	@FXML
	private TableColumn<?, ?> dateT;

	@FXML
	private JFXComboBox<Production> productionComboBox;

	@FXML
	private TableColumn<?, ?> quantityT;

	@FXML
	private TableColumn<?, ?> productT;

	@FXML
	private TableColumn<?, ?> statusT;

	@FXML
	private JFXButton returnBtn;

	@FXML
	private JFXButton refreshBtn;

	@FXML
	private JFXTextField searchText;

	@FXML
	private JFXComboBox<?> sortCombobox;

	@FXML
	private DatePicker newDate;

	@FXML
	private JFXTextField newQuantity;

	@FXML
	private TableView<Production> ProductionTable;

	@FXML
	private JFXTextField quantite;

	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductService!tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote";
	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductionService!tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote";
	private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";

	@FXML
	void AddProduction(ActionEvent event) throws Exception {
		Context context = new InitialContext();
		ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi1);
		ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi);
		ProductionStatus productionStatus;
		if (IsNumber(quantite.getText())) {
			Date sysdate = new Date();
			Date dates = java.sql.Date.valueOf(date.getValue());
			String oldstring = date.getValue().toString();
			System.out.println(oldstring);
			Date dateProduction = new SimpleDateFormat("yyyy-MM-dd").parse(oldstring);
			if (dates.compareTo(sysdate) == 1) {
				productionStatus = ProductionStatus.valueOf("To_Do");
				Production production = new Production(description.getText(), dateProduction,
						Integer.parseInt(quantite.getText()), productionStatus.toString());
				Product product = produit.getValue();
				productionServiceRemote.assignProductionToProductAndEmployee(product, production,
						employeeComboBox.getValue());
				this.sendGet(employeeComboBox.getValue().getEmail(),employeeComboBox.getValue().getName(),production.getDescription(),produit.getValue().getDescription(),production.getQuantite(),production.getDateProduction());
				System.out.println("yessss");
				this.display();
				this.LoadProductions();
			} else if (dates.compareTo(sysdate) == 0) {
				productionStatus = ProductionStatus.valueOf("Doing");
				Production production = new Production(description.getText(), dateProduction,
						Integer.parseInt(quantite.getText()), productionStatus.toString());
				Product product = produit.getValue();
				productionServiceRemote.assignProductionToProductAndEmployee(product, production,
						employeeComboBox.getValue());
				this.sendGet(employeeComboBox.getValue().getEmail(),employeeComboBox.getValue().getName(),production.getDescription(),produit.getValue().getDescription(),production.getQuantite(),production.getDateProduction());
				this.display();
				this.LoadProductions();
				System.out.println("yessss");
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Invalid Date!");
				alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Invalid Quantity!");
			alert.showAndWait();
		}
		

		this.display();
		this.LoadProductions();
		
	}
	public boolean IsNumber(String x){
        boolean verif=true;
        try{
            Integer.parseInt(x);
        }
        catch(NumberFormatException e){
            verif=false;
        }
        return verif;
    }

	public void sendGet(String email, String name, String desc,String prod,int quantity,Date date) throws Exception {

		String url = "http://localhost/testmobile/mail.php?email="+email+"&name="+name+"&desc="+desc+"&prod="+prod+"&quantity="+quantity+"&date="+date;

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

	@FXML
	void Return(ActionEvent event) {

	}

	@FXML
	void setProductionAttributes(ActionEvent event) {
		newQuantity.setText(productionComboBox.getValue().getQuantite() + "");
	}

	@FXML
	void UpdateProduction(ActionEvent event) {
		try {
			Context context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi1);
			Production production = productionComboBox.getValue();
			production.setQuantite(Integer.parseInt(newQuantity.getText()));
			Date sysdate = new Date();
			Date dates = java.sql.Date.valueOf(newDate.getValue());
			if (dates.compareTo(sysdate) == 1) {
				production.setProductionStatus((ProductionStatus.valueOf("To_Do").toString()));
				productionServiceRemote.update(production);
			} else {
				production.setProductionStatus((ProductionStatus.valueOf("Doing").toString()));
				productionServiceRemote.update(production);
			}
			quantite.setText("");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.displays();
	}

	@FXML
	void SortBy(ActionEvent event) {
		if (sortCombobox.getValue().toString().equalsIgnoreCase("Date")) {
			this.displayByDate();
		}
		if (sortCombobox.getValue().toString().equalsIgnoreCase("Quantity")) {
			this.displayByQuantity();
		}
		if (sortCombobox.getValue().toString().equalsIgnoreCase("Status")) {
			this.displayByStatus();
		}
	}

	@FXML
	void refresh(ActionEvent event) {
		this.display();
		searchText.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			this.displays();
			this.verifPoductQuantity();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void verifPoductQuantity() throws NamingException {
		Context context = new InitialContext();
		ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi);
		List<Product> products = productServiceRemote.findProductLowQuantity();
		String x = "";
		for (Product product : products) {
			x += product.getDescription() + "\n";
		}
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setContentText("There are the Lowest Product Quantity"+"\n"+x);
		alert.showAndWait();
	}

	public void displays() {
		this.display();
		this.LoadProducts();
		this.LoadProductions();
		this.LoadAllEmployees();
	}

	private void LoadProductions() {
		List<Production> list = new ArrayList<>();
		Context context;
		try {
			context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi1);
			List<Production> productions = productionServiceRemote.findProductionByStatus("To_Do");
			for (Production production : productions) {
				list.add(production);
			}
			ObservableList<Production> items = FXCollections.observableArrayList(list);
			productionComboBox.setItems(items);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void LoadAllEmployees() {
		List<Employee> employees1 = new ArrayList<>();
		try {
			Context context = new InitialContext();
			EmployeeServiceRemote employeeServiceRemote = (EmployeeServiceRemote) context.lookup(jndi2);
			List<Employee> employees = employeeServiceRemote.findAll();
			for (Employee employee : employees) {
				employees1.add(employee);
			}

			employeeComboBox.setItems(FXCollections.observableArrayList(employees1));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void LoadProducts() {
		List<Product> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi);
			List<Product> products = productServiceRemote.findAll();
			for (Product product : products) {
				descriptions.add(product);
			}
			ObservableList<Product> items = FXCollections.observableArrayList(descriptions);
			produit.setItems(items);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void display() {
		for (int i = 0; i < ProductionTable.getItems().size(); i++) {
			ProductionTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi1);
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("Description"));
			dateT.setCellValueFactory(new PropertyValueFactory<>("dateProduction"));
			productT.setCellValueFactory(new PropertyValueFactory<>("products"));
			quantityT.setCellValueFactory(new PropertyValueFactory<>("quantite"));
			employeeT.setCellValueFactory(new PropertyValueFactory<>("employee"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("productionStatus"));
			ObservableList<Production> l = FXCollections.observableArrayList(productionServiceRemote.findAll());
			ProductionTable.setItems(l);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void displayByDate() {
		try {
			Context context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi1);
			ObservableList<Production> l = FXCollections
					.observableArrayList(productionServiceRemote.sortProductionByDate());
			ProductionTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void displayByStatus() {
		try {
			Context context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi1);
			ObservableList<Production> l = FXCollections
					.observableArrayList(productionServiceRemote.sortProductionByStatus());
			ProductionTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void displayByQuantity() {
		try {
			Context context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi1);
			ObservableList<Production> l = FXCollections
					.observableArrayList(productionServiceRemote.sortProductionByQuantity());
			ProductionTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void search(KeyEvent event) {
		try {
			Context context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi1);
			ObservableList<Production> l = FXCollections
					.observableArrayList(productionServiceRemote.findProductionsByDescription(searchText.getText()));
			ProductionTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		// System.out.println(searchText.getText());
	}

}
