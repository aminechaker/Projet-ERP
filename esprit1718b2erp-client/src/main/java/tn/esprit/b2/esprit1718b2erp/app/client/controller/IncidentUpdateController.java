package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2erp.entities.Incident;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.services.IncidentServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote;


public class IncidentUpdateController implements Initializable{

    @FXML
    private Label incidentTitle;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXDatePicker incidentDate;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton cancelBtn;
    
    private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/IncidentService!tn.esprit.b2.esprit1718b2erp.services.IncidentServiceRemote";

    @FXML
    void cancelUpdate(ActionEvent event) {

    }

    @FXML
    void updateIncident(ActionEvent event) throws NamingException {
    	IncidentController ic = new IncidentController();
    	Incident incident = ic.incident;
    	Context context = new InitialContext();
    	IncidentServiceRemote incidentServiceRemote = (IncidentServiceRemote) context.lookup(jndi1);
    	
    	//Date date = Date.from((incidentDate.getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant());
    	//incident.setIncidentDate(date);
    	
    	incident.setDescription(description.getText());
    	incidentServiceRemote.update(incident);
		
		Stage stage = (Stage) updateBtn.getScene().getWindow();
		stage.close();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		IncidentController ic = new IncidentController();
    	Incident incident = ic.incident;
    	incidentTitle.setText(incident.getName());
		
	}

}
