package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.omg.SendingContext.RunTime;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote;
import javafx.event.ActionEvent;

public class RawMaterialManagementController implements Initializable {
	@FXML
    private JFXComboBox<Contact> providerComboBox;
	@FXML
    private JFXTextField priceText;
	@FXML
	private TableView<RawMaterial> RawMaterialTable;
	@FXML
	private TableColumn<?, ?> idT;
	@FXML
	private TableColumn<?, ?> descT;
	@FXML
	private TableColumn<?, ?> quantityT;
	@FXML
	private JFXTextField quantityText;
	@FXML
	private JFXComboBox<?> sortComboBox;

	@FXML
	private JFXTextField descText;
	ObservableList<Contact> items;
	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/RawMaterialService!tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote";
	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/AssignementService!tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote";
	private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/ContactService!tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote";
	@FXML
	void addRawMaterial(ActionEvent event) throws Exception {
		try {
			Context context = new InitialContext();
			RawMaterialServiceRemote rawMaterialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi);
			AssignementServiceRemote assignementServiceRemote = (AssignementServiceRemote) context.lookup(jndi1);
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi2);
			if (IsNumber(quantityText.getText())) {
				if (IsNumber1(priceText.getText())) {
					RawMaterial rawMaterial = new RawMaterial(descText.getText(), Integer.parseInt(quantityText.getText()),Float.parseFloat(priceText.getText()));
					rawMaterialServiceRemote.update(rawMaterial);
					//Contact contact = contactServiceRemote.getContactByName(providerComboBox.getValue().toString());
					System.out.println(providerComboBox.getValue().getType());
					RawMaterial rawMaterial2 = rawMaterialServiceRemote.findRawMaterialByDescription(descText.getText());
					assignementServiceRemote.assignRawMaterialToContact(rawMaterial2, providerComboBox.getValue());
					
					this.Display();
				}
				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText("Price invalid !");
					alert.showAndWait();
				}
				
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Quantity invalid !");
				alert.showAndWait();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
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
	
	public boolean IsNumber1(String x){
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
	void returnbtn(ActionEvent event) {

	}

	@FXML
	void deleteRawMaterial(ActionEvent event) {
		Context context;
		try {
			context = new InitialContext();
			RawMaterialServiceRemote rawMaterialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi);
			RawMaterial rawMaterial = RawMaterialTable.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Warning");
			alert.setContentText("Do you really want to delete this Raw Material");
			alert.showAndWait();
			if (alert.getResult().getText().equalsIgnoreCase("OK")) {
				rawMaterialServiceRemote.delete(rawMaterial);
				this.Display();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Unable to delete this Raw Material");
			alert.showAndWait();
		}
	}

	@FXML
	void Return(ActionEvent event) {

	}

	@FXML
	void sortBy(ActionEvent event) {
		if (sortComboBox.getValue().toString().equalsIgnoreCase("Quantity")) {
			this.displayByQuantity();
		}
	}

	private void displayByQuantity() {
		for (int i = 0; i < RawMaterialTable.getItems().size(); i++) {
			RawMaterialTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			RawMaterialServiceRemote rawMaterialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi);
			descT.setCellValueFactory(new PropertyValueFactory<>("description"));
			idT.setCellValueFactory(new PropertyValueFactory<>("id"));
			quantityT.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			ObservableList<RawMaterial> l = FXCollections.observableArrayList(rawMaterialServiceRemote.sortByQuantity());
			RawMaterialTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.LoadProviders();
		this.Display();
	}

	private void LoadProviders() {
		List<Contact> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi2);
			List<Contact> contacts = contactServiceRemote.getAllProviders();
			for (Contact contact : contacts) {
				descriptions.add(contact);
			}
			items = FXCollections.observableArrayList(descriptions);
			providerComboBox.setItems(items);
		} catch (NamingException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No existing suppliers\n Please add new Providers first");
			alert.showAndWait();
		}
	}

	public void Display() {
		for (int i = 0; i < RawMaterialTable.getItems().size(); i++) {
			RawMaterialTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			RawMaterialServiceRemote rawMaterialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi);
			descT.setCellValueFactory(new PropertyValueFactory<>("description"));
			idT.setCellValueFactory(new PropertyValueFactory<>("id"));
			quantityT.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			ObservableList<RawMaterial> l = FXCollections.observableArrayList(rawMaterialServiceRemote.findAll());
			RawMaterialTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
