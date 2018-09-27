package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFileChooser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProjectServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.UserServiceRemote;

public class TaskController implements Initializable {

	@FXML
    private ComboBox<?> priorityCB;

    @FXML
    private ComboBox<Employee> employeeCB;

    @FXML
    private ComboBox<Project> projectCB;
    @FXML
    private JFXTextField taskTitle;
    @FXML
    private JFXTextArea taskDescription;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private Button addFileBtn;
    /*@FXML
    private Button RemoveFileBtn;*/
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXButton saveBtn;
    /*@FXML
    private TableView<?> documentsTable;
    @FXML
    private TableColumn<?, ?> fileT;*/
    @FXML
    private Button SearchBtn;
    @FXML
    private JFXTextField searchTitle;
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<?, ?> titleT;
    @FXML
    private TableColumn<?, ?> projectT;
    @FXML
    private TableColumn<?, ?> employeeT;
    @FXML
    private TableColumn<?, ?> descriptionT;
    @FXML
    private TableColumn<?, ?> priorityT;
    @FXML
    private TableColumn<?, ?> startDateT;
    @FXML
    private TableColumn<Date, Date> endDateT;
    @FXML
    private TableColumn<?, ?> statusT;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXComboBox<?> sortCB;
    
    @FXML
    private JFXButton mailBtn;
    
    @FXML
    private Label fileDirectory;

    private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProjectService!tn.esprit.b2.esprit1718b2erp.services.ProjectServiceRemote";
    private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/TaskService!tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote";
    private String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";
    private String jndi4 = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";
    
    public static Task task;
    String path = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	addFileBtn.setCursor(Cursor.HAND);
    	//RemoveFileBtn.setCursor(Cursor.HAND);
    	saveBtn.setCursor(Cursor.HAND);
    	cancelBtn.setCursor(Cursor.HAND);
    	updateBtn.setCursor(Cursor.HAND);
    	deleteBtn.setCursor(Cursor.HAND);
    	mailBtn.setCursor(Cursor.HAND);
    	
    	this.loadAllEmployees();
        this.LoadAllProjects();
        this.display();
        
        searchTitle.setOnKeyTyped(e -> {
        	try {
    			Context context = new InitialContext();
    			//ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi2);
    			TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
    			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
    			projectT.setCellValueFactory(new PropertyValueFactory<>("project"));
    			employeeT.setCellValueFactory(new PropertyValueFactory<>("employee"));
    			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
    			priorityT.setCellValueFactory(new PropertyValueFactory<>("priority"));
    			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
    			ObservableList<Task> l = FXCollections.observableArrayList(taskServiceRemote.getTasksByTitle(searchTitle.getText()));
    			taskTable.setItems(l);
    		} catch (NamingException x) {
    			// TODO Auto-generated catch block
    			x.printStackTrace();
    		}
        });
    }    

    private void loadAllEmployees() {
    	List<Employee> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			EmployeeServiceRemote employeeServiceRemote = (EmployeeServiceRemote) context.lookup(jndi3);
			List<Employee> employees = employeeServiceRemote.findAll();
			for (Employee employee : employees) {
				descriptions.add(employee);
			}
			employeeCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
		
	}

	private void display() {
    	for (int i = 0; i < taskTable.getItems().size(); i++) {
			taskTable.getItems().clear();
		}
		try {
			//Date date = new Date();
			Context context = new InitialContext();
			TaskServiceRemote taskServiceRemote =  (TaskServiceRemote) context.lookup(jndi2);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			projectT.setCellValueFactory(new PropertyValueFactory<>("project"));
			employeeT.setCellValueFactory(new PropertyValueFactory<>("employee"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			priorityT.setCellValueFactory(new PropertyValueFactory<>("priority"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Task> l = FXCollections.observableArrayList(taskServiceRemote.findAll());
			taskTable.setItems(l);
		
		} catch (NamingException e) {
			e.printStackTrace();
		}

		
	}

	private void LoadAllProjects() {
    	List<Project> descriptions = new ArrayList<>();
		try {
			Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
			List<Project> projects = projectServiceRemote.findAll();
			for (Project project : projects) {
				descriptions.add(project);
			}
			projectCB.setItems(FXCollections.observableArrayList(descriptions));
		} catch (NamingException e) {
			
		}
	}

	@FXML
    private void addFileToTask(ActionEvent event) throws NamingException {
		//void BrowseImage(ActionEvent event) {
	        final JFileChooser fc = new JFileChooser();
	        fc.showOpenDialog(fc);
	        //FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(.jpg)", ".JPG");
	        //FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(.png)", ".PNG");
	        File selectedFile = fc.getSelectedFile();
	        String path = selectedFile.getAbsolutePath();
	        this.path = path;
	        fileDirectory.setText(path);
	        
	        Context context = new InitialContext();
			ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
	        //fileT.setCellValueFactory(new PropertyValueFactory<>("path"));
	        //Context context = new InitialContext();
	        //TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
	        //fileT.setCellValueFactory(new PropertyValueFactory<>(path));
	        //File path2 = selectedFile.getAbsoluteFile();
	        //taskServiceRemote.
	        /*String filename = "file:///" + path;
	        Image img = new Image(filename);
	        image.setImage(img);
	        this.imgpath=filename;*/
	    //}
    }

    /*@FXML
    private void removeFieOfTask(ActionEvent event) {
    }*/

    @FXML
    private void cancelTask(ActionEvent event) {
    	projectCB.setValue(null);
    	employeeCB.setValue(null);
    	taskTitle.setText(null);
    	priorityCB.setValue(null);
    	taskDescription.setText(null);
    	startDate.setValue(null);
    	endDate.setValue(null);
    }

    @FXML
    private void addTask(ActionEvent event) throws NamingException {
    	if ((taskTitle.getText().isEmpty())||(projectCB.getValue()==null)||(employeeCB.getValue()==null)||(priorityCB.getValue()==null)||(taskDescription.getText().isEmpty())||(startDate.getValue()==null)||(endDate.getValue()==null)) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Warning Dialog");
    		alert.setHeaderText("Empty fields");
    		alert.setContentText("Please complete the empty fields");

    		alert.showAndWait();
    	}
    	else {
    		Context context = new InitialContext();
        	TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
        	ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi1);
        	EmployeeServiceRemote employeeServiceRemote = (EmployeeServiceRemote) context.lookup(jndi4);
        	Task task = new Task(taskTitle.getText(), java.sql.Date.valueOf(startDate.getValue()), java.sql.Date.valueOf(endDate.getValue()), taskDescription.getText(), "To_Do", priorityCB.getValue().toString());
        	task.setDocument(path);
        	task.setEmployee(employeeCB.getValue());
        	projectServiceRemote.assignTaskToProject(task, projectCB.getValue());
        	//employeeServiceRemote.assignTaskToEmployee(task, employeeCB.getValue());
        	
        	//Task task = new Task(taskTitle.getText(), java.sql.Date.valueOf(startDate.getValue()), java.sql.Date.valueOf(endDate.getValue()), taskDescription.getText(), "To_Do", Integer.parseInt(priorityCB.getValue().toString()));
        	//taskServiceRemote.update(task);
        	
        	//taskServiceRemote.assignTaskToEmployee(task, employeeCB.getValue());
        	//Task task2 = taskServiceRemote.findTaskByTitleAndDescription(taskTitle.getText(),taskDescription.getText());
        	this.display();
        	
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText(null);
        	alert.setContentText("New task added successfully");
        	alert.showAndWait();
        	
        	taskTitle.setText(null);
        	projectCB.setValue(null);
        	employeeCB.setValue(null);
        	priorityCB.setValue(null);
        	taskDescription.setText(null);
        	startDate.setValue(null);
        	endDate.setValue(null);
    	}
    }
   
    @FXML
    private void findTaskByTitle(ActionEvent event) {
    	for (int i=0;i<taskTable.getItems().size();i++){
			taskTable.getItems().clear();
		}
		Context context;
		try {
			context = new InitialContext();
			//ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi2);
			TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			projectT.setCellValueFactory(new PropertyValueFactory<>("project"));
			employeeT.setCellValueFactory(new PropertyValueFactory<>("employee"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			priorityT.setCellValueFactory(new PropertyValueFactory<>("priority"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Task> l = FXCollections.observableArrayList(taskServiceRemote.getTasksByTitle(searchTitle.getText()));
			taskTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void updateTask(ActionEvent event) throws IOException {
    	if (taskTable.getSelectionModel().getSelectedIndex()<0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Task not selected");
        	alert.setContentText("Please select a task");
        	alert.showAndWait();
    	}
    	else {
    		this.task = taskTable.getSelectionModel().getSelectedItem();
        	Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../gui/TaskUpdate.fxml")));
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.show();
    	}
    }

    @FXML
    private void deleteTask(ActionEvent event) throws NamingException {
    	if (taskTable.getSelectionModel().getSelectedIndex()<0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Task not selected");
        	alert.setContentText("Please select a task");
        	alert.showAndWait();
    	}
    	else {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("Confirmation Dialog");
    		alert.setHeaderText("Your are going to delete the selected task");
    		alert.setContentText("Are you sure?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			Context context = new InitialContext();
    	    	TaskServiceRemote taskServiceRemote =  (TaskServiceRemote) context.lookup(jndi2);
    	    	Task task = taskTable.getSelectionModel().getSelectedItem();
    	    	taskServiceRemote.delete(task);
    	    	this.display();
    	    	
    	    	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            	alert.setTitle("Information Dialog");
            	alert.setHeaderText(null);
            	alert.setContentText("Task deleted");
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
    	if (sortCB.getValue().toString().equalsIgnoreCase("End date")){
    		this.displayByEndDate();
    	}
    	if (sortCB.getValue().toString().equalsIgnoreCase("Status")){
    		this.displayByStatus();
    	}
    }

	private void displayByStatus() {
		for (int i=0;i<taskTable.getItems().size();i++){
			taskTable.getItems().clear();
		}
		Context context;
		try {
			context = new InitialContext();
			//ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi2);
			TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			projectT.setCellValueFactory(new PropertyValueFactory<>("project"));
			employeeT.setCellValueFactory(new PropertyValueFactory<>("employee"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			priorityT.setCellValueFactory(new PropertyValueFactory<>("priority"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Task> l = FXCollections.observableArrayList(taskServiceRemote.sortTaskByStatus());
			taskTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayByEndDate() {
		for (int i=0;i<taskTable.getItems().size();i++){
			taskTable.getItems().clear();
		}
		Context context;
		try {
			context = new InitialContext();
			//ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi2);
			TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			projectT.setCellValueFactory(new PropertyValueFactory<>("project"));
			employeeT.setCellValueFactory(new PropertyValueFactory<>("employee"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			priorityT.setCellValueFactory(new PropertyValueFactory<>("priority"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Task> l = FXCollections.observableArrayList(taskServiceRemote.sortTaskByEndDate());
			taskTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void displayByProject() {
		for (int i=0;i<taskTable.getItems().size();i++){
			taskTable.getItems().clear();
		}
		Context context;
		try {
			context = new InitialContext();
			//ProjectServiceRemote projectServiceRemote = (ProjectServiceRemote) context.lookup(jndi2);
			TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
			titleT.setCellValueFactory(new PropertyValueFactory<>("title"));
			projectT.setCellValueFactory(new PropertyValueFactory<>("project"));
			employeeT.setCellValueFactory(new PropertyValueFactory<>("employee"));
			descriptionT.setCellValueFactory(new PropertyValueFactory<>("description"));
			priorityT.setCellValueFactory(new PropertyValueFactory<>("priority"));
			startDateT.setCellValueFactory(new PropertyValueFactory<>("startDate"));
			endDateT.setCellValueFactory(new PropertyValueFactory<>("endDate"));
			statusT.setCellValueFactory(new PropertyValueFactory<>("status"));
			ObservableList<Task> l = FXCollections.observableArrayList(taskServiceRemote.sortTaskByProject());
			taskTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
    void sendMail(ActionEvent event) throws NamingException {
		if (taskTable.getSelectionModel().getSelectedIndex()<0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Task not selected");
        	alert.setContentText("Please select a task");
        	alert.showAndWait();
    	}
    	else {
    		Context context = new InitialContext();
    		TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi2);
    		EmployeeServiceRemote employeeServiceRemote= (EmployeeServiceRemote) context.lookup(jndi3);
    		Task task = taskTable.getSelectionModel().getSelectedItem();
    		
    		
    		
    		Employee employee = task.getEmployee();
    		//Employee employee=taskServiceRemote.findEmployeeByTask(task);
    		System.out.println(employee);
    		String mail = employee.getEmail();
    		System.out.println(mail);
    		TaskMail taskMail = new TaskMail();
    		taskMail.envoyer(mail);
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText(null);
        	alert.setContentText("Mail sent successfully");
        	alert.showAndWait();
    	}	
	}
}

