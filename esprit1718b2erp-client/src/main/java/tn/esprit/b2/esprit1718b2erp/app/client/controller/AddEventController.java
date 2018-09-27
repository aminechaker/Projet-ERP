
package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.awt.Event;
import java.awt.Panel;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.internal.util.xml.FilteringXMLEventReader;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Events;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.Quotation;
import tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EventServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.QuotationServiceRemote;

public class AddEventController implements Initializable {

	@FXML
	private Pane EventPane;
	@FXML
	private JFXButton updateNE;
	@FXML
	private JFXButton deleteNE;

	@FXML
	private JFXButton deletePE;
	@FXML
	private JFXRadioButton localisationNS;

	@FXML
	private JFXRadioButton subjectNS;
	@FXML
	private JFXRadioButton localisationS;
	@FXML
	private JFXRadioButton subjectS;
	@FXML
	private JFXTextField maxText;
	@FXML
	private JFXTextField subjectText;
	@FXML
	private JFXTextField minText;
	@FXML
	private TableColumn<?, ?> nameTE;

	@FXML
	private TableView<Events> NEtable;

	@FXML
	private TableColumn<?, ?> subjectNE;

	@FXML
	private Label minP;

	@FXML
	private JFXButton saveEvent;

	@FXML
	private TableColumn<?, ?> dateNE;
	@FXML
	private JFXTextField searchNE;
	@FXML
	private JFXTextField searchPE;

	@FXML
	private JFXTextField eventName;

	@FXML
	private TableColumn<?, ?> managerTE;

	@FXML
	private Label maxP;

	@FXML
	private JFXButton cancelEvent;

	@FXML
	private TableColumn<?, ?> managerNE;

	@FXML
	private JFXButton validateEvent;

	@FXML
	private JFXButton searchNEbtn;

	@FXML
	private JFXButton searchPEbtn;

	@FXML
	private TableColumn<?, ?> localisationTE;

	@FXML
	private TableColumn<?, ?> localisationNE;

	@FXML
	private JFXComboBox<Employee> managerCombobox;

	@FXML
	private TableColumn<?, ?> dateTE;

	@FXML
	private TableColumn<?, ?> minTE;

	@FXML
	private TableColumn<?, ?> subjectTE;

	@FXML
	private TableColumn<?, ?> maxNE;

	@FXML
	private JFXComboBox<?> localisationCombobox;

	@FXML
	private TableView<Events> PEtable;

	@FXML
	private TableColumn<?, ?> maxTE;

	@FXML
	private TableColumn<?, ?> minNE;

	@FXML
	private TableColumn<?, ?> nameNE;

	@FXML
	private DatePicker eventDate;
	@FXML
	private JFXButton refreshPE;
	@FXML
	private JFXButton refreshNE;

	public static URL url1;
	public static ResourceBundle rb1;
	public static Events e;
	public static long x;

	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";
	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/EventService!tn.esprit.b2.esprit1718b2erp.services.EventServiceRemote";
	private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/AssignementService!tn.esprit.b2.esprit1718b2erp.services.AssignementServiceRemote";

	@FXML
	void saveEvent(ActionEvent event) throws ParseException {
		int x = 0;
		try {
			Context context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			AssignementServiceRemote assignementServiceRemote = (AssignementServiceRemote) context.lookup(jndi2);
			Employee employee = managerCombobox.getValue();
			String dates = java.sql.Date.valueOf(eventDate.getValue()).toString();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dates);
			List<Events> evenements = eventServiceRemote.findEventByDate(date);
			System.out.println(evenements.size());
			for (Events events : evenements) {
				if (events.getEventDate().compareTo(date) == 0
						&& (events.getEmployee().getCode() == employee.getCode())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Warning");
					alert.setContentText("This employee is already engaged");
					alert.showAndWait();
					break;
				}
				Events events1 = new Events(eventName.getText(), subjectText.getText(),
						java.sql.Date.valueOf(eventDate.getValue()), Integer.parseInt(maxText.getText()),
						Integer.parseInt(minText.getText()), localisationCombobox.getValue().toString());
				// eventServiceRemote.save(events1);
				assignementServiceRemote.assignEventToEmployee(events1, employee);
				// System.out.print(employee.getName());
				x = 1;

			}
			if ((evenements.size()) == 0 && (x == 0)) {
				Events events1 = new Events(eventName.getText(), subjectText.getText(),
						java.sql.Date.valueOf(eventDate.getValue()), Integer.parseInt(maxText.getText()),
						Integer.parseInt(minText.getText()), localisationCombobox.getValue().toString());
				// eventServiceRemote.save(events1);
				assignementServiceRemote.assignEventToEmployee(events1, employee);
				// System.out.print(employee.getName());
			}
			this.DisplayPreviousEvents();
			this.DisplayNextEvents();
			this.DisplayEvents();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean IsNumber(String x){
        boolean verif=true;
        try{
            Float.parseFloat(x);
        }
        catch(NumberFormatException e){
            verif=false;
        }
        return verif;
    }

	public void DisplayEvents() {
		try {
			Float a = 1F;
			Context context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			List<Events> events = eventServiceRemote.sortEventByDate();
			//EventPane.setStyle("-fx-background-color: #616161;");
			Pane pane1 = new Pane();
			Pane pane2 = new Pane();
			pane1.setPrefHeight(3000);
			pane1.setPrefWidth(500);
			pane2.setPrefHeight(3000);
			pane2.setPrefWidth(500);
			pane1.setLayoutX(20);
			pane1.setLayoutY(59);
			pane2.setLayoutX(pane1.getLayoutX() + 500);
			pane2.setLayoutY(59);
			Double x = 25D;
			Double y = 65D;
			Double x1 = pane1.getLayoutX() + 500 + 5 ;
			Double y1 = 65D;		
			for (Events event : events) {
				if ((a%2) != 0) {
					Pane pane = new Pane();
					pane.setPrefHeight(90);
					pane.setPrefWidth(300);
					Date date = event.getEventDate();
					LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int day = localDate.getDayOfMonth();
					int month = localDate.getMonthValue();
					int year = localDate.getYear();
					Button button = new Button();
					Button button1 = new Button();
					button.setText("   " + day + "\n" + " " + this.getMonth(month) + "\n" + year + "\n");
					button.setPrefHeight(90);
					button.setPrefWidth(90);
					button.setStyle("-fx-background-color: #E7D11C;-fx-text-fill: white;-fx-font-family: " + "Arial"
							+ ";-fx-font-size: 14px;-fx-padding: 10 20 10 20;");
					button1.setCursor(Cursor.HAND);
					button1.setText(event.getName() + "\n" + event.getSubject() + "\n" + event.getLieu());
					button1.setPrefHeight(90);
					button1.setPrefWidth(210);
					button1.setLayoutX(button.getLayoutX() + 90);
					button1.setStyle("-fx-background-color: #ffffff;-fx-text-fill: black;-fx-font-family: " + "Arial"
							+ "-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
					pane.setLayoutX(x);
					pane.setLayoutY(y);
					pane1.getChildren().add(pane);
					y+= 100D;
					a++;
					button1.setOnMouseClicked(e -> {
						if (e.getButton() == MouseButton.SECONDARY) {
							final ContextMenu contextMenu = new ContextMenu();
							MenuItem details = new MenuItem("Details");
							details.setOnAction(r -> {
								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setTitle("Information");
								alert.setContentText("Name: " + event.getName() + "\nSubject: " + event.getSubject()
										+ "\nLocalisation" + event.getLieu() + "\nManager: "
										+ event.getEmployee().getName() + "\nMax Participants:" + event.getMax()
										+ "\nMin Participants: " + event.getMin());
								alert.showAndWait();
							});
							MenuItem update = new MenuItem("Remaining time");
							update.setOnAction(t -> {
								Date d1 = event.getEventDate();
								Date d2 = new Date();
								long dureest = (d1.getTime() - d2.getTime()) / 3600000;
								System.out.println(dureest);
								this.x = dureest;
								if (d1.compareTo(d2) == 1) {
									Timer timer = new Timer();
									timer.init();
									timer.initialize(url1, rb1);
									/*Alert alert = new Alert(Alert.AlertType.INFORMATION);
									alert.setTitle("Remaining time");
									alert.setContentText("Il vous reste " + dureest + " Heures");
									alert.showAndWait();*/
								} else {
									Alert alert = new Alert(Alert.AlertType.INFORMATION);
									alert.setTitle("Remaining time");
									alert.setContentText("The event has already passed");
									alert.showAndWait();
								}
							});
							contextMenu.getItems().addAll(details, update);
							button1.setContextMenu(contextMenu);
						}
					});

					/*************************************/
					pane.getChildren().add(button);
					pane.getChildren().add(button1);
					EventPane.getChildren().add(pane);
				} else {
					Pane pane = new Pane();
					pane.setPrefHeight(90);
					pane.setPrefWidth(300);
					Date date = event.getEventDate();
					LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int day = localDate.getDayOfMonth();
					int month = localDate.getMonthValue();
					int year = localDate.getYear();
					Button button = new Button();
					Button button1 = new Button();
					button.setText("   " + day + "\n" + " " + this.getMonth(month) + "\n" + year + "\n");
					button.setPrefHeight(90);
					button.setPrefWidth(90);
					button.setStyle("-fx-background-color: #E7D11C;-fx-text-fill: white;-fx-font-family: " + "Arial"
							+ ";-fx-font-size: 14px;-fx-padding: 10 20 10 20;");
					button1.setCursor(Cursor.HAND);
					button1.setText(event.getName() + "\n" + event.getSubject() + "\n" + event.getLieu());
					button1.setPrefHeight(90);
					button1.setPrefWidth(210);
					button1.setLayoutX(button.getLayoutX() + 90);
					button1.setStyle("-fx-background-color: #ffffff;-fx-text-fill: black;-fx-font-family: " + "Arial"
							+ "-fx-font-size: 12px;-fx-padding: 10 20 10 20;");

					pane.setLayoutX(x1);
					pane.setLayoutY(y1);
					pane2.getChildren().add(pane);
					y1+= 100D;
					a++;
					button1.setOnMouseClicked(e -> {
						if (e.getButton() == MouseButton.SECONDARY) {
							final ContextMenu contextMenu = new ContextMenu();
							MenuItem details = new MenuItem("Details");
							details.setOnAction(r -> {
								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setTitle("Information");
								alert.setContentText("Name: " + event.getName() + "\nSubject: " + event.getSubject()
										+ "\nLocalisation" + event.getLieu() + "\nManager: "
										+ event.getEmployee().getName() + "\nMax Participants:" + event.getMax()
										+ "\nMin Participants: " + event.getMin());
								alert.showAndWait();
							});
							MenuItem update = new MenuItem("Remaining time");
							update.setOnAction(t -> {
								Date d1 = event.getEventDate();
								Date d2 = new Date();
								long dureest = (d1.getTime() - d2.getTime()) / 3600000;
								this.x = dureest;
								System.out.println(dureest);
								if (d1.compareTo(d2) == 1) {
									Timer timer = new Timer();
									timer.init();
									timer.initialize(url1, rb1);
									/*Alert alert = new Alert(Alert.AlertType.INFORMATION);
									alert.setTitle("Remaining time");
									alert.setContentText("Il vous reste " + dureest + " Heures");
									alert.showAndWait();*/
								} else {
									Alert alert = new Alert(Alert.AlertType.INFORMATION);
									alert.setTitle("Remaining time");
									alert.setContentText("The event has already passed");
									alert.showAndWait();
								}
							});
							contextMenu.getItems().addAll(details, update);
							button1.setContextMenu(contextMenu);
						}
					});
					pane.getChildren().add(button);
					pane.getChildren().add(button1);
					EventPane.getChildren().add(pane);
				}

			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void deleteNE(ActionEvent event) {
		try {
			Context context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			Events events = NEtable.getSelectionModel().getSelectedItem();
			eventServiceRemote.delete(events);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.DisplayNextEvents();
	}

	@FXML
	void refreshNE(ActionEvent event) {
		INIT();

	}

	@FXML
	void refreshPE(ActionEvent event) {
		INIT();
	}

	@FXML
	void updateNE(ActionEvent event) throws IOException {
		Events e = NEtable.getSelectionModel().getSelectedItem();
		this.e = e;
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../gui/updateEvent.fxml")));
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	void search(KeyEvent event) {
		try {
			Context context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			ObservableList<Events> l = FXCollections
					.observableArrayList(eventServiceRemote.findEventByName(searchNE.getText()));
			NEtable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		// System.out.println(searchText.getText());
	}

	@FXML
	void sortNByLocalisation(ActionEvent event) {
		subjectNS.setSelected(false);
	}

	@FXML
	void sortNBySubject(ActionEvent event) {
		localisationNS.setSelected(false);
	}

	@FXML
	void deletePE(ActionEvent event) {
		try {
			Context context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			Events events = PEtable.getSelectionModel().getSelectedItem();
			eventServiceRemote.delete(events);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.DisplayPreviousEvents();
	}

	@FXML
	void validateEvent(ActionEvent event) {

	}

	@FXML
	void cancelEvent(ActionEvent event) {
		INIT();
	}

	@FXML
	void searchPE(ActionEvent event) {
		try {
			Context context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			if (localisationS.isSelected()) {
				PEtable.setItems(
						FXCollections.observableArrayList(eventServiceRemote.sortByLocalisation(searchPE.getText())));
			} else if (subjectS.isSelected()) {
				PEtable.setItems(
						FXCollections.observableArrayList(eventServiceRemote.sortBySubject(searchPE.getText())));
			}
		} catch (NamingException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void searchNE(ActionEvent event) {
		Context context;
		try {
			context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			if (localisationNS.isSelected()) {

				NEtable.setItems(
						FXCollections.observableArrayList(eventServiceRemote.sortByLocalisation(searchNE.getText())));
			} else if (subjectNS.isSelected()) {
				NEtable.setItems(
						FXCollections.observableArrayList(eventServiceRemote.sortBySubject(searchNE.getText())));
			}

		} catch (NamingException e) {

			e.printStackTrace();
		}

	}

	@FXML
	void sortByLocalisation(ActionEvent event) {
		subjectS.setSelected(false);
	}

	@FXML
	void sortBySubject(ActionEvent event) {
		localisationS.setSelected(false);
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

			managerCombobox.setItems(FXCollections.observableArrayList(employees1));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void DisplayPreviousEvents() {
		for (int i = 0; i < PEtable.getItems().size(); i++) {
			PEtable.getItems().clear();
		}
		try {
			Date date = new Date();
			Context context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			nameTE.setCellValueFactory(new PropertyValueFactory<>("name"));
			dateTE.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
			managerTE.setCellValueFactory(new PropertyValueFactory<>("employee"));
			maxTE.setCellValueFactory(new PropertyValueFactory<>("max"));
			minTE.setCellValueFactory(new PropertyValueFactory<>("min"));
			subjectTE.setCellValueFactory(new PropertyValueFactory<>("subject"));
			localisationTE.setCellValueFactory(new PropertyValueFactory<>("lieu"));
			List<Events> events = eventServiceRemote.findAll();
			List<Events> list = new ArrayList<>();
			for (Events events2 : events) {
				if (events2.getEventDate().compareTo(date) == -1) {
					list.add(events2);
				}
			}
			ObservableList<Events> l = FXCollections.observableArrayList(list);
			PEtable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void DisplayNextEvents() {
		for (int i = 0; i < NEtable.getItems().size(); i++) {
			NEtable.getItems().clear();
		}
		try {
			Date date = new Date();
			Context context = new InitialContext();
			EventServiceRemote eventServiceRemote = (EventServiceRemote) context.lookup(jndi1);
			nameNE.setCellValueFactory(new PropertyValueFactory<>("name"));
			dateNE.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
			managerNE.setCellValueFactory(new PropertyValueFactory<>("employee"));
			maxNE.setCellValueFactory(new PropertyValueFactory<>("max"));
			minNE.setCellValueFactory(new PropertyValueFactory<>("min"));
			subjectNE.setCellValueFactory(new PropertyValueFactory<>("subject"));
			localisationNE.setCellValueFactory(new PropertyValueFactory<>("lieu"));
			List<Events> events = eventServiceRemote.findAll();
			List<Events> list = new ArrayList<>();
			for (Events events2 : events) {
				if (events2.getEventDate().compareTo(date) == 1) {
					list.add(events2);
				}
			}
			ObservableList<Events> l = FXCollections.observableArrayList(list);
			NEtable.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rb1 = resources;
		url1 = location;
		localisationCombobox.setValue(null);
		managerCombobox.setValue(null);
		eventName.setText(null);
		eventDate.setValue(null);
		maxText.setText(null);
		minText.setText(null);
		subjectText.setText(null);
		searchNE.setText(null);
		searchPE.setText(null);
		this.DisplayPreviousEvents();
		this.DisplayNextEvents();
		this.LoadAllEmployees();
		this.DisplayEvents();
	}

	public void INIT() {

		this.initialize(url1, rb1);
	}

	public String getMonth(int x) {
		return new DateFormatSymbols().getMonths()[x - 1];
	}

}