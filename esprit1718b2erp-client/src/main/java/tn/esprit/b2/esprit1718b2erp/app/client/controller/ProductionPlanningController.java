package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollBar;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;

public class ProductionPlanningController implements Initializable {
	@PersistenceContext
	EntityManager entityManager;

	@FXML
	private Separator sep2;

	@FXML
	private Pane pane1;

	@FXML
	private Pane pane3;

	@FXML
	private Separator sep1;
	@FXML
	private ScrollBar scroll;

	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductionService!tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote";
	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductService!tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote";
	private ManufacturingStatistiqueController manufacturingStatistiqueController;
	private double x1,y1,x2,y2 = 60,x3,y3 = 60;
	/**
	 * Initializes the controller class.
	 * 
	 * @throws NamingException
	 */

	public void verifToDo() {
		Context context;
		try {
			context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi);
			List<Production> productions = productionServiceRemote.findProductionByStatus("To_Do");
			double a = 60;
			for (Production production : productions) {
				Button button = new Button();
				button.setDisable(false);
				button.setStyle(
						"-fx-background-color: #4169e1;-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: white;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: "
								+ "Arial"
								+ ";-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 11px;-fx-padding: 10 20 10 20;");
				button.setCursor(Cursor.HAND);
				button.setText(production.getDescription() + "\n" + "Quantity : " + production.getQuantite() + "\n"
						+ "Started : " + production.getDateProduction());
				button.setLayoutX(20);
				button.setLayoutY(a);
				button.setPrefWidth(210);
				button.setPrefHeight(70);
				pane1.getChildren().add(button);
				double x = button.getLayoutX();
				double y = button.getLayoutX();
				button.setOnMouseDragged(e -> {
					button.setLayoutX(e.getSceneX() - 300);
					button.setLayoutY(e.getSceneY() - 150);
				});
				button.setOnMouseReleased(e -> {
					try {
						Context context2 = new InitialContext();
						ProductionServiceRemote productionServiceRemote1 = (ProductionServiceRemote) context
								.lookup(jndi);
						ProductServiceRemote productServiceRemote = (ProductServiceRemote) context2.lookup(jndi1);
						if (button.getLayoutX() > sep1.getLayoutX() && button.getLayoutX() < sep2.getLayoutX()) {
							button.setLayoutX(x2);
							button.setLayoutY(y2 + 75);
							x2 = button.getLayoutX();
							y2 = button.getLayoutY();
							production.setProductionStatus("Doing");
							productionServiceRemote1.update(production);
							button.setStyle(
									"-fx-background-color: #daa520;-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: white;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: "
											+ "Arial"
											+ ";-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 11px;-fx-padding: 10 20 10 20;");
							this.refreshPlanning();
							manufacturingStatistiqueController.statistique();
						}
						if ((button.getLayoutX() > sep2.getLayoutX())) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Warning");
							alert.setContentText("Wrong update for this ProductionXX");
							alert.showAndWait();
							button.setLayoutX(x);
							button.setLayoutY(y);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				});
				button.setOnMouseClicked(e -> {
			            if((e.getClickCount()) == 2 && (e.getButton() == MouseButton.PRIMARY)){
			            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Information");
							alert.setContentText("Description : "+production.getDescription()+"\n"+"Starting Date : "+production.getDateProduction()+"\n"+"Product : "+production.getProducts().getDescription()+"\n"+"Quantity : "+production.getQuantite()+"\n"+"Status : "+production.getProductionStatus()+"\n");
							alert.showAndWait();
			            }
				});
				button.setOnMouseClicked(e ->{
					if (e.getButton() == MouseButton.SECONDARY) {
						final ContextMenu contextMenu = new ContextMenu();
						MenuItem details = new MenuItem("Details");
						details.setOnAction(r ->{
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Information");
							alert.setContentText("Description : "+production.getDescription()+"\n"+"Starting Date : "+production.getDateProduction()+"\n"+"Product : "+production.getProducts().getDescription()+"\n"+"Quantity : "+production.getQuantite()+"\n"+"Status : "+production.getProductionStatus()+"\n");
							alert.showAndWait();
						});
						MenuItem delete = new MenuItem("Delete");
						delete.setOnAction(t ->{
							Alert alert = new Alert(Alert.AlertType.WARNING);
							alert.setTitle("Warning");
							alert.setContentText("Are you sure for Deleting this Manufacturing ?");
							alert.showAndWait();
							if (alert.getResult().getText().equalsIgnoreCase("OK")) {
								productionServiceRemote.delete(production);
								this.refreshPlanning();
							}
						});
						contextMenu.getItems().addAll(details, delete);
						button.setContextMenu(contextMenu);
					}
				});
				a = button.getLayoutY() + button.getPrefHeight() + 5;
			}
		} catch (NamingException e2) {
			e2.printStackTrace();
		}
	}

	public void verifDoing() {
		Context context;
		try {
			context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi);
			List<Production> productions1 = productionServiceRemote.findProductionByStatus("Doing");
			double b = 60;
			for (Production production : productions1) {
				Button button = new Button();
				button.setId("button");
				button.setDisable(false);
				button.setCursor(Cursor.HAND);
				button.setText(production.getDescription() + "\n" + "Quantity : " + production.getQuantite() + "\n"
						+ "Started : " + production.getDateProduction());
				button.setLayoutX(sep1.getLayoutX() + 20 );
				button.setLayoutY(b);
				button.setPrefWidth(210);
				button.setPrefHeight(70);
				double x = button.getLayoutX();
				double y = button.getLayoutY();
				button.setStyle(
						"-fx-background-color: #daa520;-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: white;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: "
								+ "Arial"
								+ ";-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 11px;-fx-padding: 10 20 10 20;");
				pane1.getChildren().add(button);
				x2 = button.getLayoutX();
				y2 = button.getLayoutY();
				button.setOnMouseDragged(e -> {
					button.setLayoutX(e.getSceneX() - 300);
					button.setLayoutY(e.getSceneY() - 150);
				});
				button.setOnMouseReleased(e -> {
					try {
						Context context2 = new InitialContext();
						ProductionServiceRemote productionServiceRemote1 = (ProductionServiceRemote) context
								.lookup(jndi);
						ProductServiceRemote productServiceRemote = (ProductServiceRemote) context2.lookup(jndi1);
						if (button.getLayoutX() < sep1.getLayoutX()) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Warning");
							alert.setContentText("Wrong update for this Production");
							alert.showAndWait();
							button.setLayoutX(x);
							button.setLayoutY(y);
						}
						if (button.getLayoutX() > sep1.getLayoutX() && button.getLayoutX() < sep2.getLayoutX()) {
							button.setLayoutX(x);
							button.setLayoutY(y);
						}
						if ((button.getLayoutX() > sep2.getLayoutX())) {
							button.setLayoutX(x3);
							button.setLayoutY(y3 + 75);
							x3 = button.getLayoutX();
							y3 = button.getLayoutY();
							production.setProductionStatus("Done");
							productionServiceRemote.update(production);
							Product product = production.getProducts();
							product.setQuantity(product.getQuantity() + production.getQuantite());
							productServiceRemote.update(product);
							button.setStyle(
									"-fx-background-color: #32cd32;-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: white;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: "
											+ "Arial"
											+ ";-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 11px;-fx-padding: 10 20 10 20;");
							this.refreshPlanning();
							//manufacturingStatistiqueController.statistique();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				});
				button.setOnMouseClicked(e -> {
		            if((e.getClickCount()) == 2 && (e.getButton() == MouseButton.PRIMARY)){
		            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Information");
						alert.setContentText("Description : "+production.getDescription()+"\n"+"Starting Date : "+production.getDateProduction()+"\n"+"Product : "+production.getProducts().getDescription()+"\n"+"Quantity : "+production.getQuantite()+"\n"+"Status : "+production.getProductionStatus()+"\n");
						alert.showAndWait();
		            }
			});
				button.setOnMouseClicked(e ->{
					if (e.getButton() == MouseButton.SECONDARY) {
						final ContextMenu contextMenu = new ContextMenu();
						MenuItem details = new MenuItem("Details");
						details.setOnAction(r ->{
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Information");
							alert.setContentText("Description : "+production.getDescription()+"\n"+"Starting Date : "+production.getDateProduction()+"\n"+"Product : "+production.getProducts().getDescription()+"\n"+"Quantity : "+production.getQuantite()+"\n"+"Status : "+production.getProductionStatus()+"\n");
							alert.showAndWait();
						});
						MenuItem update = new MenuItem("Send to Done Manufacturings");
						update.setOnAction(t ->{
							Alert alert = new Alert(Alert.AlertType.WARNING);
							alert.setTitle("Warning");
							alert.setContentText("Are you sure that this Manufacturing is DONE ?");
							alert.showAndWait();
							if (alert.getResult().getText().equalsIgnoreCase("OK")) {
								production.setProductionStatus("Done");
								productionServiceRemote.update(production);
								this.refreshPlanning();
							}
						});
						contextMenu.getItems().addAll(details, update);
						button.setContextMenu(contextMenu);
					}
				});
				b = button.getLayoutY() + button.getPrefHeight() + 5;
			}
		} catch (NamingException e2) {
			e2.printStackTrace();
		}

	}

	public void verifDone() {
		Context context;
		try {
			context = new InitialContext();
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi);
			List<Production> productions2 = productionServiceRemote.findProductionByStatus("Done");
			double c = 60;
			for (Production production : productions2) {
				Button button = new Button();
				button.setStyle(
						"-fx-background-color: #32cd32;-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: white;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: "
								+ "Arial"
								+ ";-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 11px;-fx-padding: 10 20 10 20;");
				button.setDisable(false);
				button.setCursor(Cursor.HAND);
				button.setText(production.getDescription() + "\n" + "Quantity : " + production.getQuantite() + "\n"
						+ "Started : " + production.getDateProduction());
				button.setLayoutX(sep2.getLayoutX() + 20);
				button.setLayoutY(c);
				button.setPrefWidth(210);
				button.setPrefHeight(70);
				pane1.getChildren().add(button);
				double x = button.getLayoutX();
				double y = button.getLayoutY();
				x3 = button.getLayoutX();
				y3 = button.getLayoutY();
				button.setOnMouseDragged(e -> {
					button.setLayoutX(e.getSceneX() - 300);
					button.setLayoutY(e.getSceneY() - 150);
				});
				button.setOnMouseReleased(e -> {
					try {
						Context context2 = new InitialContext();
						ProductionServiceRemote productionServiceRemote1 = (ProductionServiceRemote) context
								.lookup(jndi);
						if (button.getLayoutX() > sep1.getLayoutX() && button.getLayoutX() < sep2.getLayoutX()) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Warning");
							alert.setContentText("Wrong update for this Production");
							alert.showAndWait();
							button.setLayoutX(x);
							button.setLayoutY(y);
						}
						if (button.getLayoutX() < sep1.getLayoutX()) {
							/*
							 * production.setProductionStatus("Done");
							 * productionServiceRemote.update(production);
							 */
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Warning");
							alert.setContentText("Wrong update for this Production");
							alert.showAndWait();
							button.setLayoutX(x);
							button.setLayoutY(y);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				});
				button.setOnMousePressed(e -> {
		            if(e.getClickCount() == 2){
		            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Information");
						alert.setContentText("Description : "+production.getDescription()+"\n"+"Starting Date : "+production.getDateProduction()+"\n"+"Product : "+production.getProducts().getDescription()+"\n"+"Quantity : "+production.getQuantite()+"\n"+"Status : "+production.getProductionStatus()+"\n");
						alert.showAndWait();
		            }
			});
				button.setOnMouseClicked(e ->{
					if (e.getButton() == MouseButton.SECONDARY) {
						final ContextMenu contextMenu = new ContextMenu();
						MenuItem details = new MenuItem("Details");
						details.setOnAction(r ->{
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Information");
							alert.setContentText("Description : "+production.getDescription()+"\n"+"Starting Date : "+production.getDateProduction()+"\n"+"Product : "+production.getProducts().getDescription()+"\n"+"Quantity : "+production.getQuantite()+"\n"+"Status : "+production.getProductionStatus()+"\n");
							alert.showAndWait();
						});
						contextMenu.getItems().addAll(details);
						button.setContextMenu(contextMenu);
					}
				});
				c = button.getLayoutY() + button.getPrefHeight() + 5;
			}
		} catch (NamingException e2) {
			e2.printStackTrace();
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		x2 = sep1.getLayoutX() + 20;
		x3 = sep2.getLayoutX() + 20;
		this.verifToDo();
		this.verifDoing();
		this.verifDone();
	}
	public void refreshPlanning(){
		pane1.getChildren().clear();
		this.verifToDo();
		this.verifDoing();
		this.verifDone();
	}

}
