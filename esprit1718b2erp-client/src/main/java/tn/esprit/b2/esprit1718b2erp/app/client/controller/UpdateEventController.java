package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Events;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EventServiceRemote;

public class UpdateEventController implements Initializable {
	
	@FXML
	private DatePicker date;

	@FXML
	private JFXComboBox<Employee> employeeComboBox;

	@FXML
	private JFXTextField min;

	@FXML
	private JFXTextField subjet;

	@FXML
	private JFXTextField max;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXComboBox<?> locationComboBox;

	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";
	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/EventService!tn.esprit.b2.esprit1718b2erp.services.EventServiceRemote";
	
	@FXML
	void update(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
		AddEventController addEventController = new AddEventController();
		Events events = addEventController.e;
		events.setEmployee(employeeComboBox.getValue());
		events.setEventDate(java.sql.Date.valueOf(date.getValue()));
		events.setName(name.getText());
		events.setLieu(locationComboBox.getValue().toString());
		events.setSubject(subjet.getText());
		events.setMin(Integer.parseInt(min.getText()));
		events.setMax(Integer.parseInt(max.getText()));
		eventServiceRemote.update(events);
		
		((Node)(event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AddEventController aec = new AddEventController();
		Events event = aec.e;
		name.setText(event.getName());
		subjet.setText(event.getSubject());
		max.setText(event.getMax()+"");
		min.setText(event.getMin()+"");
		this.LoadAllEmployees();
		
	}
	public void LoadAllEmployees() {
		List<Employee> employees1 = new ArrayList<>();
		try {
			Context context = new InitialContext();
			EmployeeServiceRemote employeeServiceRemote = (EmployeeServiceRemote) context.lookup(jndi);
			List<Employee> employees = employeeServiceRemote.findAll();
			for (Employee employee : employees) {
				employees1.add(employee);
			}

			employeeComboBox.setItems(FXCollections.observableArrayList(employees1));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
