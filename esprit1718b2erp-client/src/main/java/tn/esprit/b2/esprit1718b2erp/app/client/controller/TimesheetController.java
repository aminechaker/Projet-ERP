package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.scene.control.Alert;
import java.util.Date;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.input.MouseButton;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;

import tn.esprit.b2.esprit1718b2erp.entities.Project;
import tn.esprit.b2.esprit1718b2erp.entities.Task;
import tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote;

public class TimesheetController implements Initializable {

	@FXML
	private Label ProjectTitle;

	@FXML
	private Pane TaskPane;

	@FXML
	private Separator sep1;

	@FXML
	private Separator sep3;

	@FXML
	private Separator sep2;

	@FXML
	private Separator sep4;

	@FXML
	private Separator sep5;

	@FXML
	private Separator sep6;

	@FXML
	private Separator sep7;

	@FXML
	private Separator sep8;

	@FXML
	private Separator sep9;

	@FXML
	private Separator sep10;

	@FXML
	private Separator sep11;

	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/TaskService!tn.esprit.b2.esprit1718b2erp.services.TaskServiceRemote";

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			this.TasksPlanning();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		/*
		 * ProjectController pc = new ProjectController(); Project project =
		 * pc.project; System.out.println(project.getStartDate().getMonth());
		 */
	}

	/*
	 * @FXML private void returnToProjects(ActionEvent event) { }
	 */

	public void TasksPlanning() throws NamingException {
		ProjectController pc = new ProjectController();
		Project project = pc.project;
		ProjectTitle.setText(project.getTitle());
		;
		Context context = new InitialContext();
		TaskServiceRemote taskServiceRemote = (TaskServiceRemote) context.lookup(jndi);
		List<Task> list = taskServiceRemote.findAllTaskByProject(project);
		Double x1 = 16D;
		Double y1 = 4D;
		Double x2 = 124D;
		Double y2 = 4D;
		Double x3 = 232D;
		Double y3 = 4D;
		Double x4 = 340D;
		Double y4 = 4D;
		Double x5 = 448D;
		Double y5 = 4D;
		Double x6 = 556D;
		Double y6 = 4D;
		Double x7 = 664D;
		Double y7 = 4D;
		Double x8 = 772D;
		Double y8 = 4D;
		Double x9 = 880D;
		Double y9 = 4D;
		Double x10 = 988D;
		Double y10 = 4D;
		Double x11 = 1096D;
		Double y11 = 4D;
		Double x12 = 1184D;
		Double y12 = 4D;
		for (Task task : list) {
			int month = task.getStartDate().getMonth() + 1;
			Button button = new Button();
			if (task.getStatus().equalsIgnoreCase("To_Do")) {
				button.setStyle(
						"-fx-background-color: #ff0000;");
				button.setOnMouseClicked(e ->{
					if (e.getButton() == MouseButton.SECONDARY) {
						final ContextMenu contextMenu = new ContextMenu();
						MenuItem details = new MenuItem("Details");
						details.setOnAction(r ->{
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle(task.getTitle());
							alert.setHeaderText("Priority : "+task.getPriority());
							alert.setContentText("Start date : "+task.getStartDate()+"\n"+"End date : "+task.getEndDate()+"\n"+"Description : "+task.getDescription()+"\n"/*+"Employee in charge : "+task.getEmployees()*/);
							alert.showAndWait();
						});
						MenuItem update = new MenuItem("Change status to Doing");
						update.setOnAction(r ->{
							task.setStatus("Doing");
							taskServiceRemote.update(task);
							button.setStyle(
									"-fx-background-color: #FFFF00;");
						});
						contextMenu.getItems().addAll(details, update);
						button.setContextMenu(contextMenu);
					}
				});
			}
			if (task.getStatus().equalsIgnoreCase("Doing")) {
				button.setStyle(
						"-fx-background-color: #FFFF00;");
				button.setOnMouseClicked(e ->{
					if (e.getButton() == MouseButton.SECONDARY) {
						final ContextMenu contextMenu = new ContextMenu();
						MenuItem details = new MenuItem("Details");
						details.setOnAction(r ->{
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle(task.getTitle());
							alert.setHeaderText("Priority : "+task.getPriority());
							alert.setContentText("Start date : "+task.getStartDate()+"\n"+"End date : "+task.getEndDate()+"\n"+"Description : "+task.getDescription()+"\n"/*+"Employee in charge : "+task.getEmployees()*/);
							alert.showAndWait();
						});
						MenuItem update = new MenuItem("Change status to Done");
						update.setOnAction(r ->{
							task.setStatus("Done");
							taskServiceRemote.update(task);
							button.setStyle(
									"-fx-background-color: #32cd32;");
						});
						contextMenu.getItems().addAll(details, update);
						button.setContextMenu(contextMenu);
					}
				});
			}
			if (task.getStatus().equalsIgnoreCase("Done")) {
				button.setStyle(
						"-fx-background-color: #32cd32;");
				button.setOnMouseClicked(e ->{
					if (e.getButton() == MouseButton.SECONDARY) {
						button.setOnMouseClicked(x ->{
							if (e.getButton() == MouseButton.SECONDARY) {
								final ContextMenu contextMenu = new ContextMenu();
								MenuItem details = new MenuItem("Details");
								details.setOnAction(r ->{
									Alert alert = new Alert(Alert.AlertType.INFORMATION);
									alert.setTitle(task.getTitle());
									alert.setHeaderText("Priority : "+task.getPriority());
									alert.setContentText("Start date : "+task.getStartDate()+"\n"+"End date : "+task.getEndDate()+"\n"+"Description : "+task.getDescription()+"\n"/*+"Employee in charge : "+task.getEmployees()*/);
									alert.showAndWait();
								});
								contextMenu.getItems().addAll(details);
								button.setContextMenu(contextMenu);
							}
						});
					}
				});
			}
			if (month == 1) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x1);
				button.setLayoutY(y1);
				y1 += 65D;

			}
			if (month == 2) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x2);
				button.setLayoutY(y2);
				y2 += 65D;
			}
			if (month == 3) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x3);
				button.setLayoutY(y3);
				y3 += 65D;
			}
			if (month == 4) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x4);
				button.setLayoutY(y4);
				y4 += 65D;
			}
			if (month == 5) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x5);
				button.setLayoutY(y5);
				y5 += 65D;
			}
			if (month == 6) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x6);
				button.setLayoutY(y6);
				y6 += 65D;
			}
			if (month == 7) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x7);
				button.setLayoutY(y7);
				TaskPane.getChildren().add(button);
				y7 += 65D;
			}
			if (month == 8) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x8);
				button.setLayoutY(y8);
				y8 += 65D;
			}
			if (month == 9) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x9);
				button.setLayoutY(y9);
				y9 += 65D;
			}
			if (month == 10) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x10);
				button.setLayoutY(y10);
				y10 += 65D;
			}
			if (month == 11) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x11);
				button.setLayoutY(y11);
				y11 += 65D;
			}
			if (month == 12) {
				button.setText(task.getTitle() + "\n" + task.getStartDate() + "\n" + task.getEndDate());
				button.setPrefHeight(60);
				button.prefWidth(100);
				button.setLayoutX(x12);
				button.setLayoutY(y12);
				y12 += 65D;
			}
			TaskPane.getChildren().add(button);
		}
	}

	public String getMonth(int x) {
		return new DateFormatSymbols().getMonths()[x - 1];
	}

}
