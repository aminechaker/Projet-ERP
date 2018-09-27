package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFileChooser;

import org.hamcrest.Description;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote;
import javafx.fxml.Initializable;



public class TaskUpdateController implements Initializable {

    @FXML
    private Label taskTitle;

    @FXML
    private JFXComboBox<String> priorityCB;

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXDatePicker endDate;

    @FXML
    private JFXTextArea desciption;

    @FXML
    private TableView<?> fileTable;

    @FXML
    private TableColumn<?, ?> fileT;

    @FXML
    private Button addFileBtn;

    @FXML
    private Button removeFileBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton cancelBtn;
    
    @FXML
    private Label fileDirectory;
    
    public static Task task;
    
    String path = null;

    @FXML
    void CancelUpdate(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
    }

    @FXML
    void addFile(ActionEvent event) {
    	final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        File selectedFile = fc.getSelectedFile();
        String path = selectedFile.getAbsolutePath();
        this.path = path;

    }

    @FXML
    void removeFile(ActionEvent event) {

    }

    @FXML
    void updateTask(ActionEvent event) throws NamingException {
    	TaskController tc = new TaskController();
		Task task = tc.task;
    	Context context = new InitialContext();
		TaskServiceRemote taskServiceRemote =  (TaskServiceRemote) context.lookup(jndi2);
		task.setPriority(priorityCB.getValue());
		
		
		//task.setStartDate(java.sql.Date.valueOf(startDate.getValue()));
		//task.setEndDate(java.sql.Date.valueOf(endDate.getValue()));		
		
		task.setDescription(desciption.getText());
		task.setDocument(path);
		fileDirectory.setText(path);
		taskServiceRemote.update(task);
		
		Stage stage = (Stage) updateBtn.getScene().getWindow();
		stage.close();
    }
    
    private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/TaskService!tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateBtn.setCursor(Cursor.HAND);
		cancelBtn.setCursor(Cursor.HAND);
		
		TaskController tc = new TaskController();
		Task task = tc.task;
		taskTitle.setText(task.getTitle());
		priorityCB.setValue(task.getPriority());
		desciption.setText(task.getDescription());
		path = task.getDocument();
		fileDirectory.setText(task.getDocument());
		
		/*Date start = task.getStartDate();
    	LocalDate date1 = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	startDate.setValue(date1);
    	
    	Date end = task.getEndDate();
    	LocalDate date2 = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	startDate.setValue(date2);*/
    	
    	
		//startDate.setValue(task.getStartDate());
		
		/*try {
			Context context = new InitialContext();
			TaskServiceRemote taskServiceRemote =  (TaskServiceRemote) context.lookup(jndi2);
			fileT.setCellValueFactory(new PropertyValueFactory<>("document"));
			ObservableList<Task> l = FXCollections.observableArrayList(task.getDocument());
			fileTable.setItems(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
