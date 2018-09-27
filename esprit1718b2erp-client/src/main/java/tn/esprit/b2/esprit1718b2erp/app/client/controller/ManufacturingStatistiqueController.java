package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;

public class ManufacturingStatistiqueController implements Initializable {

	@FXML
	private PieChart productionPieChart;
	private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductionService!tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote";
	final Label caption = new Label("");
	
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.statistique();
	}

	public void statistique() {
		try {
			//caption.setTextFill(Color.DARKORANGE);
			caption.setStyle("-fx-font: 24 arial;");
			Context context = new InitialContext();
			productionPieChart.setTitle("Manufacturing Status");
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi);
			List<Production> todo = productionServiceRemote.findProductionByStatus("To_Do");
			List<Production> doing = productionServiceRemote.findProductionByStatus("Doing");
			List<Production> done = productionServiceRemote.findProductionByStatus("Done");
			productionPieChart.setTitle("Manufacturing Status");
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
					new PieChart.Data("To Do", todo.size()), 
					new PieChart.Data("Doing", doing.size()),
					new PieChart.Data("Done", done.size()));
			productionPieChart.setData(pieChartData);
			productionPieChart.setClockwise(true);
			productionPieChart.setLabelLineLength(50);
			productionPieChart.setLabelsVisible(true);
			productionPieChart.setStartAngle(180);
			productionPieChart.setOnMouseClicked(e-> {
				if (productionPieChart.getData().get(0).getName().equalsIgnoreCase("To_Do")) {
					System.out.println(productionPieChart.getData().get(0).getPieValue());
					
				}
			});
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}