package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Supervisor;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProjectServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.SupervisorServiceRemote;

public class ProjectUpdateController implements Initializable {

    @FXML
    private Label projectTitle;

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXDatePicker endDate;

    @FXML
    private JFXComboBox<String> sectorCB;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXComboBox<Contact> clientCB;

    @FXML
    private JFXComboBox<Supervisor> supervisorCB;
    
    private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProjectService!tn.esprit.b2.esprit1718b2erp.services.ProjectServiceRemote";
    private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/SupervisorService!tn.esprit.b2.esprit1718b2erp.services.SupervisorServiceRemote";
    private String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/ContactService!tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote";
    
    private void loadAllClients() {
    	List<Contact> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi3);
			List<Contact> contacts = contactServiceRemote.getAllClients();
			for (Contact contact : contacts) {
				descriptions.add(contact);
			}
			clientCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
		
	}

	private void loadAllSupervisors() {
		List<Supervisor> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			SupervisorServiceRemote superisorServiceRemote =  (SupervisorServiceRemote) context.lookup(jndi2);
			List<Supervisor> supervisors = superisorServiceRemote.findAll();
			for (Supervisor supervisor : supervisors) {
				descriptions.add(supervisor);
			}
			supervisorCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
		
	}

	@FXML
    void cancelUpdate(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();

    }

    @FXML
    void updateProject(ActionEvent event) throws NamingException {
    	ProjectController pc = new ProjectController();
		Project project = pc.project;
		
		Context context = new InitialContext();
    	ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
    	
    	project.setContacts(clientCB.getValue());
    	//project.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
    	
    	project.setSector(sectorCB.getValue());
    	//project.setStartDate(java.sql.Date.valueOf(startDate.getValue()));
    	project.setSupervisor(supervisorCB.getValue());
    	projectServiceRemote.update(project);
    	
    	Stage stage = (Stage) updateBtn.getScene().getWindow();
		stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateBtn.setCursor(Cursor.HAND);
		cancelBtn.setCursor(Cursor.HAND);
		
    	ProjectController pc = new ProjectController();
		Project project = pc.project;
		projectTitle.setText(project.getTitle());
    	//supervisorCB.setValue(project.getSupervisor());
		//clientCB.setValue(project.getContacts());
		//sectorCB.setValue(project.getSector());
		this.loadAllSupervisors();
    	this.loadAllClients();
    	
    	/*LocalDate today= project.getStartDate();
		startDate.setValue(today);
		endDate.setValue(today.plusDays(1));*/
    	
    	/*Date start = project.getStartDate();
    	LocalDate date1 = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	startDate.setValue(date1);
    	
    	Date end = project.getEndDate();
    	LocalDate date2 = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	startDate.setValue(date2);*/
		}

}
