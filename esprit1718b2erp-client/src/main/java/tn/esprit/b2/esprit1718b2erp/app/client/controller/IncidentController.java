package tn.esprit.b2.esprit1718b2erp.app.client.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2erp.entities.Incident;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.services.IncidentServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProjectServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


public class IncidentController implements Initializable {

    @FXML
    private ComboBox<Project> relatedProjectCB;
    @FXML
    private JFXTextField incidentDescription;
    @FXML
    private JFXDatePicker incidentDate;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXTextField incidentTitle;
    @FXML
    private ComboBox<Task> relatedTaskCB;
    @FXML
    private Button searchBtn;
    @FXML
    private JFXTextField searchTitle;
    @FXML
    private TableView<Incident> incidentTable;
    @FXML
    private TableColumn<?, ?> titleT;
    @FXML
    private TableColumn<?, ?> descriptionT;
    @FXML
    private TableColumn<?, ?> projectT;
    @FXML
    private TableColumn<?, ?> taskT;
    @FXML
    private TableColumn<?, ?> dateT;
    @FXML
    private TableColumn<?, ?> statusT;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXComboBox<?> sortCB;
    
    private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProjectService!tn.esprit.b2.esprit1718b2erp.services.ProjectServiceRemote";
    private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/TaskService!tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote";
    private String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/IncidentService!tn.esprit.b2.esprit1718b2erp.services.IncidentServiceRemote";
    
    public static Incident incident;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	saveBtn.setCursor(Cursor.HAND);
    	cancelBtn.setCursor(Cursor.HAND);
    	updateBtn.setCursor(Cursor.HAND);
    	deleteBtn.setCursor(Cursor.HAND);
    	
        this.loadAllProjects();        
        this.display();
        
        relatedProjectCB.setOnAction(e -> {
        	this.loadRelatedTasks();
        });
        
        searchTitle.setOnKeyTyped(x -> {
        	try {
    			Context context = new InitialContext();
    			//ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi2);
    			//TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
    			IncidentServiceRemote incidentServiceRemote =  (IncidentServiceRemote) context.lookup(jndi3);
    			titleT.setCellValueFactory(new PropertyValueFactory<>("name"));
    			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
    			//projectT.setCellValueFactory(new PropertyValueFactory<>("?"));
    			taskT.setCellValueFactory(new PropertyValueFactory<>("task"));
    			dateT.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
    			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
    			ObservableList<Incident> l = FXCollections.observableArrayList(incidentServiceRemote.getIncidentsByTitle(searchTitle.getText()));
    			incidentTable.setItems(l);
    		} catch (NamingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        });
    }    

    private void display() {
    	for (int i=0;i<incidentTable.getItems().size();i++){
			incidentTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			IncidentServiceRemote incidentServiceRemote =  (IncidentServiceRemote) context.lookup(jndi3);
			titleT.setCellValueFactory(new PropertyValueFactory<>("name"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			//projectT.setCellValueFactory(new PropertyValueFactory<>("?"));
			taskT.setCellValueFactory(new PropertyValueFactory<>("task"));
			dateT.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Incident> l = FXCollections.observableArrayList(incidentServiceRemote.findAll());
			incidentTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void loadRelatedTasks() {
    	List<Task> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
			List<Task> tasks = taskServiceRemote.loadRelatedTasks(relatedProjectCB.getValue());
			for (Task task : tasks) {
				descriptions.add(task);
			}
			relatedTaskCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
		
	}

	private void loadAllProjects() {
    	List<Project> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			List<Project> projects = projectServiceRemote.findAll();
			for (Project project : projects) {
				descriptions.add(project);
			}
			relatedProjectCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
		
	}

	@FXML
    private void addIncident(ActionEvent event) throws NamingException {
		if ((relatedProjectCB.getValue()==null)||(relatedTaskCB.getValue()==null)||(incidentTitle.getText().isEmpty())||(incidentDate.getValue()==null)||(incidentDescription.getText().isEmpty())) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setHeaderText("Empty fields");
    		alert.setContentText("Please complete the empty fields");

    		alert.showAndWait();
		}
		else {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
	    	IncidentServiceRemote incidentServiceRemote = (IncidentServiceRemote) context.lookup(jndi3);
	    	Incident incident = new Incident(incidentTitle.getText(), incidentDescription.getText(), java.sql.Date.valueOf(incidentDate.getValue()), "To_Do");
	    	incidentServiceRemote.save(incident);
	    	taskServiceRemote.assignIncidentToTask(incident, relatedTaskCB.getValue());
	    	this.display();
	    	
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText(null);
        	alert.setContentText("New incident added successfully");
        	alert.showAndWait();
        	
        	relatedProjectCB.setValue(null);
        	relatedTaskCB.setValue(null);
        	incidentTitle.setText(null);
        	incidentDate.setValue(null);
        	incidentDescription.setText(null);
		}
		
		
    }
	
	@FXML
    private void cancelIncident(ActionEvent event) throws NamingException {
		
	}

    @FXML
    private void findIncidentByTitle(ActionEvent event) {
    	for (int i=0;i<incidentTable.getItems().size();i++){
			incidentTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			//ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi2);
			//TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
			IncidentServiceRemote incidentServiceRemote =  (IncidentServiceRemote) context.lookup(jndi3);
			titleT.setCellValueFactory(new PropertyValueFactory<>("name"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			//projectT.setCellValueFactory(new PropertyValueFactory<>("?"));
			taskT.setCellValueFactory(new PropertyValueFactory<>("task"));
			dateT.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Incident> l = FXCollections.observableArrayList(incidentServiceRemote.getIncidentsByTitle(searchTitle.getText()));
			incidentTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void updateIncident(ActionEvent event) throws IOException {
    	if (incidentTable.getSelectionModel().getSelectedIndex()<0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Task not selected");
        	alert.setContentText("Please select an incident");
        	alert.showAndWait();
    	}
    	else {
    		this.incident = incidentTable.getSelectionModel().getSelectedItem();
        	Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../gui/IncidentUpdate.fxml")));
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.show();
    	}	
    }

    @FXML
    private void deleteIncident(ActionEvent event) throws NamingException {
    	if (incidentTable.getSelectionModel().getSelectedIndex()<0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Task not selected");
        	alert.setContentText("Please select an incident");
        	alert.showAndWait();
    	}
    	else {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("Confirmation Dialog");
    		alert.setHeaderText("Your are going to delete the selected incident");
    		alert.setContentText("Are you sure?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			Context context = new InitialContext();
    			IncidentServiceRemote incidentServiceRemote =  (IncidentServiceRemote) context.lookup(jndi3);
    	    	Incident incident = incidentTable.getSelectionModel().getSelectedItem();
    	    	incidentServiceRemote.delete(incident);
    	    	this.display();
    	    	
    	    	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            	alert.setTitle("Information Dialog");
            	alert.setHeaderText(null);
            	alert.setContentText("Incident deleted");
            	alert.showAndWait();
    		} else {
    		    this.display();
    		}
    	}
    }

    @FXML
    private void sortBy(ActionEvent event) {
    	if (sortCB.getValue().toString().equalsIgnoreCase("Project")){
    		this.displayByProject();
    	}
    	if (sortCB.getValue().toString().equalsIgnoreCase("Task")){
    		this.displayByTask();
    	}
    	if (sortCB.getValue().toString().equalsIgnoreCase("Status")){
    		this.displayByStatus();
    	}
    }

	private void displayByStatus() {
		for (int i=0;i<incidentTable.getItems().size();i++){
			incidentTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			IncidentServiceRemote incidentServiceRemote =  (IncidentServiceRemote) context.lookup(jndi3);
			titleT.setCellValueFactory(new PropertyValueFactory<>("name"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			//projectT.setCellValueFactory(new PropertyValueFactory<>("?"));
			taskT.setCellValueFactory(new PropertyValueFactory<>("task"));
			dateT.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Incident> l = FXCollections.observableArrayList(incidentServiceRemote.sortTaskByStatus());
			incidentTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void displayByTask() {
		for (int i=0;i<incidentTable.getItems().size();i++){
			incidentTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			IncidentServiceRemote incidentServiceRemote = (IncidentServiceRemote) context.lookup(jndi3);
			titleT.setCellValueFactory(new PropertyValueFactory<>("name"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			//projectT.setCellValueFactory(new PropertyValueFactory<>("?"));
			taskT.setCellValueFactory(new PropertyValueFactory<>("task"));
			dateT.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Incident> l = FXCollections.observableArrayList(incidentServiceRemote.sortTaskByTask());
			incidentTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void displayByProject() {
		// TODO Auto-generated method stub
		
	}
    
}
