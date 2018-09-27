package tn.esprit.b2.esprit1718b2erp.app.client.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;

public class TimesheetController implements Initializable {

	@FXML
    private Pane pane;
    @FXML
    private Separator separatorL;
    @FXML
    private Separator separatorR;
    @FXML
    private Label projectTitle;
    @FXML
    private JFXButton returnBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void returnToProjects(ActionEvent event) {
    }
}
