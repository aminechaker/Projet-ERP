package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.b2.esprit1718b2erp.entities.Claims;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.services.ClaimsServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;

public class ClaimsManagementController implements Initializable {

	@FXML
	private JFXComboBox<Contact> client_combo;
	@FXML
	private TextArea description_area;
	/*@FXML
	private DatePicker date_picker;*/
	@FXML
	private JFXButton approuve_button;
	@FXML
	private JFXButton abord_button;
	@FXML
	private TableView<Claims> claims_tab;
	@FXML
	private TableColumn<?, ?> id_colone;
	@FXML
	private TableColumn<?, ?> contact_colone;
	@FXML
	private TableColumn<?, ?> description_colone;
	@FXML
	private TableColumn<?, ?> date_colone;
	@FXML
	private TableColumn<?, ?> status_colone;
	@FXML
	private JFXTextField claims_search;
	@FXML
	private JFXButton search_button;
	@FXML
	private JFXButton refrach_button;
	@FXML
	private JFXButton drop_button;
	@FXML
	private JFXButton traite_button;
	@FXML
	private JFXRadioButton contact_radio;
	@FXML
	private JFXRadioButton status_radio;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.Views();

		List<Contact> contacts = new ArrayList<>();

		try {
			Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
			List<Contact> contactsOld = contactServiceRemote.findAll();
			for (Contact contact : contactsOld) {
				contacts.add(contact);
				ObservableList<Contact> items = FXCollections.observableArrayList(contacts);
				client_combo.setItems(items);
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/ClaimsService!tn.esprit.b2.esprit1718b2erp.services.ClaimsServiceRemote";
	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/ContactService!tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote";

	@FXML
	private void approuveAction(ActionEvent event) {
		try {
			Context context = new InitialContext();
			ClaimsServiceRemote claimsServiceRemote = (ClaimsServiceRemote) context.lookup(jndi);
			if(verification()==true){
			Date dates =new Date();
			//Date date = java.sql.Date.valueOf(date_picker.getValue());
			Claims claims = new Claims(description_area.getText(), dates, "untreated", client_combo.getValue());
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Warning");
	        alert.setContentText("Do you want to approuve this Claim");
	        alert.showAndWait();
	        if (alert.getResult().getText().equalsIgnoreCase("OK")) {
			claimsServiceRemote.save(claims);
			 this.Views();}
	        
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void abrodAction(ActionEvent event) {
	}

	@FXML
	private void searchAction(ActionEvent event) {

		try {
			Context context = new InitialContext();
			ClaimsServiceRemote claimsServiceRemote = (ClaimsServiceRemote) context.lookup(jndi);
			if (contact_radio.isSelected()) {
				claims_tab.setItems(FXCollections
						.observableArrayList(claimsServiceRemote.getClaimstByClientName(claims_search.getText())));
			}
			if (status_radio.isSelected()) {
				claims_tab.setItems(FXCollections
						.observableArrayList(claimsServiceRemote.getClaimsByStatus(claims_search.getText())));
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void refrechAction(ActionEvent event) {
		this.Views();
	}

	@FXML
	private void DropClaimAction(ActionEvent event) {
		try {
			Context context = new InitialContext();
			ClaimsServiceRemote claimsServiceRemote = (ClaimsServiceRemote) context.lookup(jndi);
			Claims claims = claims_tab.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Warning");
	        alert.setContentText("Do you really want to delete this claim");
	        alert.showAndWait();
	        if (alert.getResult().getText().equalsIgnoreCase("OK")) {
			
			claimsServiceRemote.delete(claims);
	        }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Views();
	}

	@FXML
	private void traiteClaimAction(ActionEvent event) {
		try {
			Context context = new InitialContext();
			ClaimsServiceRemote claimsServiceRemote = (ClaimsServiceRemote) context.lookup(jndi);

			Claims claims = claimsServiceRemote.find(claims_tab.getSelectionModel().getSelectedItem().getId());
			claims.setStatus("treated");
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Warning");
	        alert.setContentText("Do you really want to treat this claim");
	        alert.showAndWait();
	        if (alert.getResult().getText().equalsIgnoreCase("OK")) {
	        	
			claimsServiceRemote.update(claims);
	        }	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Views();

	}

	@FXML
	private void contactSelected(ActionEvent event) {
		status_radio.setSelected(false);
	}

	@FXML
	private void StatusSelected(ActionEvent event) {
		contact_radio.setSelected(false);

	}

	private void Views() {
		for (int i = 0; i < claims_tab.getItems().size(); i++) {
			claims_tab.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ClaimsServiceRemote claimsServiceRemote = (ClaimsServiceRemote) context.lookup(jndi);
			id_colone.setCellValueFactory(new PropertyValueFactory<>("id"));
			contact_colone.setCellValueFactory(new PropertyValueFactory<>("contact"));
			description_colone.setCellValueFactory(new PropertyValueFactory<>("description"));
			date_colone.setCellValueFactory(new PropertyValueFactory<>("claimDate"));
			status_colone.setCellValueFactory(new PropertyValueFactory<>("status"));

			ObservableList<Claims> l = FXCollections.observableArrayList(claimsServiceRemote.findAll());
			claims_tab.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

    private boolean verification() {
    if (client_combo.getValue()==null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("select a Contact please ");
        alert.showAndWait();
        client_combo.requestFocus();
        return false;
    }
    
     if ("".equals(description_area.getText())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("check the description please");
        alert.showAndWait();
        description_area.requestFocus();
        return false;
    }
     return true;
    }

}
