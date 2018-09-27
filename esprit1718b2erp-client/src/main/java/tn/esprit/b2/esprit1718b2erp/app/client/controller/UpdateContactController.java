package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateContactController implements Initializable {

    @FXML
    private JFXTextField name_text;
    @FXML
    private JFXTextField address_text;
    @FXML
    private JFXTextField tel_text;
    @FXML
    private JFXTextField email_text;
    @FXML
    private JFXComboBox<String> region_Combo;
    @FXML
    private JFXComboBox<String> sector_Combo;
    @FXML
    private JFXComboBox<String> type_Combo;
    @FXML
    private JFXButton update_button;
    @FXML
    private JFXButton cancel_button;

    /**
     * Initializes the controller class.
     */
    
    private String jndi1="esprit1718b2erp-ear/esprit1718b2erp-service/ContactService!tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	ContactManagementController contactManagementController = new ContactManagementController();
    	Contact contact = contactManagementController.c;
    	name_text.setText(contact.getName());
    	address_text.setText(contact.getAdresse());
    	tel_text.setText(contact.getMobile());
    	email_text.setText(contact.getEmail());
    	region_Combo.setValue(contact.getRegion());
    	sector_Combo.setValue(contact.getActivitySector());
    	type_Combo.setValue(contact.getType());
    	
    }    

    @FXML
    private void UpdateAction(ActionEvent event) {
    		
    	ContactManagementController contactManagementController = new ContactManagementController();
    	Contact contact = contactManagementController.c;
    	try {
			Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
			Contact contact2= contactServiceRemote.find(contactManagementController.c.getId());
			contact2.setActivitySector(sector_Combo.getValue());
			contact2.setAdresse(address_text.getText());
			contact2.setName(name_text.getText());
			contact2.setAdresse(address_text.getText());
			contact2.setMobile(tel_text.getText());
			contact2.setEmail(email_text.getText());
			contact2.setRegion(region_Combo.getValue());
			contact2.setType(type_Combo.getValue());
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Warning");
	        alert.setContentText("Do you really want to add this contact");
	        alert.showAndWait();
	        if(alert.getResult().getText().equalsIgnoreCase("OK")){
			contactServiceRemote.update(contact2);
			
			
			
			}
	        
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
       
    	
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }
    
}
