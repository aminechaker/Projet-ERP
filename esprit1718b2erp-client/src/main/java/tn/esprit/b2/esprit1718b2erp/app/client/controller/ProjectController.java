package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout.Alignment;

import org.hibernate.internal.util.xml.FilteringXMLEventReader;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Supervisor;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProjectService;
import tn.esprit.b2.esprit1718b2erp.services.ProjectServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.SupervisorServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote;



public class ProjectController implements Initializable {

	@FXML
    private JFXTextField projectTitle;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXComboBox<Supervisor> supervisorCB;
    @FXML
    private JFXComboBox<Contact> clientCB;
    @FXML
    private JFXButton CancelBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXComboBox<?> sectorCB;
    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<?, ?> titleT;
    @FXML
    private TableColumn<?, ?> clientT;
    @FXML
    private TableColumn<?, ?> supervisorT;
    @FXML
    private TableColumn<?, ?> sectorT;
    @FXML
    private TableColumn<?, ?> startDateT;
    @FXML
    private TableColumn<?, ?> endDateT;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton timesheetBtn;
    @FXML
    private JFXTextField searchTitle;
    /*@FXML
    private Button searchBtn;*/
    @FXML
    private JFXButton diagramBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXComboBox<?> sortCB;
    
    public static Project project;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	LocalDate today= LocalDate.now();
		startDate.setValue(today);
		endDate.setValue(today.plusDays(1));
    	
        //this.loadAllEmployees();
    	this.loadAllSupervisors();
        this.loadAllClients();
        this.display();
        saveBtn.setCursor(Cursor.HAND);
        CancelBtn.setCursor(Cursor.HAND);
        updateBtn.setCursor(Cursor.HAND);
        deleteBtn.setCursor(Cursor.HAND);
        timesheetBtn.setCursor(Cursor.HAND);
        searchTitle.setOnKeyTyped(e -> {
        	//System.out.println(searchTitle.getText());
        	try {
    			Context context = new InitialContext();
    			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
    			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
    			clientT.setCellValueFactory(new PropertyValueFactory<>("contacts"));
    			supervisorT.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
    			sectorT.setCellValueFactory(new PropertyValueFactory<>("sector"));
    			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    			ObservableList<Project> l = FXCollections.observableArrayList(projectServiceRemote.getProjectsByTitle(searchTitle.getText()));
    			projectTable.setItems(l);
    		} catch (NamingException a) {
    			// TODO Auto-generated catch block
    			a.printStackTrace();
    		}
        });
    }    

    private void loadAllSupervisors() {
    	List<Supervisor> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			SupervisorServiceRemote superisorServiceRemote =  (SupervisorServiceRemote) context.lookup(jndi7);
			List<Supervisor> supervisors = superisorServiceRemote.findAll();
			for (Supervisor supervisor : supervisors) {
				descriptions.add(supervisor);
			}
			supervisorCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
		
	}

	private void display() {
    	for (int i = 0; i < projectTable.getItems().size(); i++) {
			projectTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			clientT.setCellValueFactory(new PropertyValueFactory<>("contacts"));
			supervisorT.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
			sectorT.setCellValueFactory(new PropertyValueFactory<>("sector"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			ObservableList<Project> l = FXCollections.observableArrayList(projectServiceRemote.findAll());
			projectTable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

	private void loadAllClients() {
    	List<Contact> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi4);
			List<Contact> contacts = contactServiceRemote.getAllClients();
			for (Contact contact : contacts) {
				descriptions.add(contact);
			}
			clientCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
		
	}

	/*private void loadAllEmployees() {
		List<Employee> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			EmployeeServiceRemote employeeServiceRemote = (EmployeeServiceRemote) context.lookup(jndi5);
			List<Employee> employees = employeeServiceRemote.findAll();
			for (Employee employee : employees) {
				descriptions.add(employee);
			}
			supervisorCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
		
	}*/

	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProjectService!tn.esprit.b2.esprit1718b2erp.services.ProjectServiceRemote";
    private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/TaskService!tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote";
    private String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/IncidentService!tn.esprit.b2.esprit1718b2erp.services.IncidentServiceRemote";
    private String jndi4 = "esprit1718b2erp-ear/esprit1718b2erp-service/ContactService!tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote";
    private String jndi5 = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";
    private String jndi6 = "esprit1718b2erp-ear/esprit1718b2erp-service/AssignementService!tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote";
    private String jndi7 = "esprit1718b2erp-ear/esprit1718b2erp-service/SupervisorService!tn.esprit.b2.esprit1718b2erp.services.SupervisorServiceRemote";
	
	@FXML
    private void CancelProject(ActionEvent event) {
		projectTitle.setText("");
		startDate.setValue(null);
		endDate.setValue(null);
		supervisorCB.setValue(null);
		clientCB.setValue(null);
		sectorCB.setValue(null);
    }
    
    @FXML
    private void AddProject(ActionEvent event) throws NamingException {
    	if ((projectTitle.getText().isEmpty())||(startDate.getValue()==null)||(endDate.getValue()==null)||(supervisorCB.getValue()==null)||(clientCB.getValue()==null)||(sectorCB.getValue()==null)){
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setHeaderText("Empty fields");
    		alert.setContentText("Please complete the empty fields");

    		alert.showAndWait();
    	}
    	else {
    		Context context = new InitialContext();
        	ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
        	Project project = new Project(sectorCB.getValue().toString(), projectTitle.getText(), java.sql.Date.valueOf(startDate.getValue()), java.sql.Date.valueOf(endDate.getValue()));
        	//projectServiceRemote.update(project);
        	AssignementServiceRemote assignementServiceRemote = (AssignementServiceRemote) context.lookup(jndi6);
        	assignementServiceRemote.assignProjectToContactAndSupervisor(project, clientCB.getValue(), supervisorCB.getValue());
        	this.display();
        	
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText(null);
        	alert.setContentText("New project added successfully");
        	alert.showAndWait();
        	
        	projectTitle.setText(null);
    		startDate.setValue(null);
    		endDate.setValue(null);
    		supervisorCB.setValue(null);
    		clientCB.setValue(null);
    		sectorCB.setValue(null);
    	}
    }

    @FXML
    private void deleteProject(ActionEvent event) throws NamingException {
    	if (projectTable.getSelectionModel().getSelectedIndex()<0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Error Dialog");
    		alert.setHeaderText("Project not selected");
    		alert.setContentText("Please choose a a project to delete");
    		alert.showAndWait();
    	}
    	else {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("Confirmation Dialog");
    		alert.setHeaderText("Your are going to delete the selected project");
    		alert.setContentText("Are you sure?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			Context context = new InitialContext();
    	    	ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
    	    	Project project = projectTable.getSelectionModel().getSelectedItem();
    	    	projectServiceRemote.delete(project);
    	    	this.display();
    	    	
    	    	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            	alert.setTitle("Information Dialog");
            	alert.setHeaderText(null);
            	alert.setContentText("Project deleted");
            	alert.showAndWait();
    		} else {
    		    this.display();
    		}
    	} 	
    }

    @FXML
    private void timesheetProject(ActionEvent event) throws IOException {
    	if (projectTable.getSelectionModel().getSelectedIndex()<0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Project not selected");
        	alert.setContentText("Please select a project");
        	alert.showAndWait();
    	}
    	else {
    		this.project = projectTable.getSelectionModel().getSelectedItem();
        	Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../gui/Timesheet.fxml")));
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.show();
    	}
    }

    @FXML
    private void findProjectByTitle(ActionEvent event) {
    	for (int i=0;i<projectTable.getItems().size();i++){
			projectTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			clientT.setCellValueFactory(new PropertyValueFactory<>("contacts"));
			supervisorT.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
			sectorT.setCellValueFactory(new PropertyValueFactory<>("sector"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			ObservableList<Project> l = FXCollections.observableArrayList(projectServiceRemote.getProjectsByTitle(searchTitle.getText()));
			projectTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void diagramProject(ActionEvent event) {
    }

    @FXML
    private void updateProject(ActionEvent event) throws IOException {
    	if (projectTable.getSelectionModel().getSelectedIndex()<0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Project not selected");
        	alert.setContentText("Please select a project");
        	alert.showAndWait();
    	}
    	else {
    		this.project = projectTable.getSelectionModel().getSelectedItem();
        	Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../gui/ProjectUpdate.fxml")));
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.show();
    	}    	
    }
    
    @FXML
	void SortBy(ActionEvent event) {
    	if (sortCB.getValue().toString().equalsIgnoreCase("Sector")){
    		this.displayBySector();
    	}
    	if (sortCB.getValue().toString().equalsIgnoreCase("Client")){
    		this.displayByClient();
    	}
    	if (sortCB.getValue().toString().equalsIgnoreCase("Supervisor")){
    		this.displayBySupervisor();
    	}
    }

	private void displayBySupervisor() {
		for (int i=0;i<projectTable.getItems().size();i++){
			projectTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			clientT.setCellValueFactory(new PropertyValueFactory<>("contacts"));
			supervisorT.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
			sectorT.setCellValueFactory(new PropertyValueFactory<>("sector"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			ObservableList<Project> l = FXCollections.observableArrayList(projectServiceRemote.sortProjectBySupervisor());
			projectTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayByClient() {
		for (int i=0;i<projectTable.getItems().size();i++){
			projectTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			clientT.setCellValueFactory(new PropertyValueFactory<>("contacts"));
			supervisorT.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
			sectorT.setCellValueFactory(new PropertyValueFactory<>("sector"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			ObservableList<Project> l = FXCollections.observableArrayList(projectServiceRemote.sortProjectByClient());
			projectTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void displayBySector() {
		for (int i=0;i<projectTable.getItems().size();i++){
			projectTable.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			clientT.setCellValueFactory(new PropertyValueFactory<>("contacts"));
			supervisorT.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
			sectorT.setCellValueFactory(new PropertyValueFactory<>("sector"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			ObservableList<Project> l = FXCollections.observableArrayList(projectServiceRemote.sortProjectBySector());
			projectTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
