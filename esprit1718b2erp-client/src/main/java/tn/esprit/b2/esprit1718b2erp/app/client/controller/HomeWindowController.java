package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.io.IOException;
import java.net.URL;

import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.Node;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class HomeWindowController implements Initializable {

	@FXML
    private ScrollPane scrollPane;
	
	@FXML
	private TitledPane crmPane;

	@FXML
	private TitledPane productPane;

	@FXML
	private TitledPane financialPane;

	@FXML
	private TitledPane projectPane;

	@FXML
	private TitledPane salePane;

	@FXML
	private TitledPane manufacturingPane;
	@FXML
	private AnchorPane globalAnchor;
	

	 
	    @FXML
	    void projectManagement(ActionEvent event) {
	        Node node;
			try {
				node = (Node) FXMLLoader.load(getClass().getResource("../gui/Project.fxml"));
				globalAnchor.getChildren().setAll(node);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    @FXML
	    void taskManagement(ActionEvent event) {
	        Node node;
			try {
				node = (Node) FXMLLoader.load(getClass().getResource("../gui/Task.fxml"));
				globalAnchor.getChildren().setAll(node);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    @FXML
	    void incidentManagement(ActionEvent event) {
	        Node node;
			try {
				node = (Node) FXMLLoader.load(getClass().getResource("../gui/Incident.fxml"));
				globalAnchor.getChildren().setAll(node);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
    @FXML
    void ContactMangement(ActionEvent event) {
    	Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/ContactManagement.FXML"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    @FXML
    void Calendar(ActionEvent event) {
    	Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/Calendar.FXML"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    @FXML
    void AppointmentManagement(ActionEvent event) {
    	Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/AppointmentsManagement.fxml"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void ClaimsManagement(ActionEvent event) {
    	Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/ClaimsManagement.fxml"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@FXML
	void rawMaterial(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/RawMaterialManagement.fxml"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void products(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/ProductManagement.fxml"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void manufacturing(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/ProductionManagement.fxml"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void inventory(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/InventoryManagement.fxml"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void salesManagement(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/AddSale.fxml"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void eventsManagement(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/AddEvent.fxml"));
			scrollPane.setContent(node);
			globalAnchor.getChildren().setAll(scrollPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void manifacturingPlanning(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/ProductionPlanning.fxml"));
			scrollPane.setContent(node);
			globalAnchor.getChildren().setAll(scrollPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
    void manufacturingStatistique(ActionEvent event) {
		Node node;
		try {
			node = (Node) FXMLLoader.load(getClass().getResource("../gui/ManufacturingStatistique.fxml"));
			globalAnchor.getChildren().setAll(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO

		 /*crmPane.setStyle("-fx-background-color: #000000;-fx-effect: dropshadow(#00FF00);-fx-animated: true;-fx-text-fill: #FFFFFF;");
		 salePane.setStyle("-fx-effect: dropshadow(three-pass-box, #9F9F9F, 15,0, 0, 0);-fx-animated: true;-fx-text-fill: #505050;");
		 projectPane.setStyle("-fx-effect: dropshadow(three-pass-box, #9F9F9F, 15,0, 0, 0);-fx-animated: true;-fx-text-fill: #505050;");
		 productPane.setStyle("-fx-effect: dropshadow(three-pass-box, #9F9F9F, 15,0, 0, 0);-fx-animated: true;-fx-text-fill: #505050;");
		 manufacturingPane.setStyle("-fx-effect: dropshadow(three-pass-box, #9F9F9F, 15,0, 0, 0);-fx-animated: true;-fx-text-fill: #505050;");
		 financialPane.setStyle("-fx-effect: dropshadow(three-pass-box, #9F9F9F, 15,0, 0, 0);-fx-animated: true;-fx-text-fill: #505050;");*/
	}

}
