package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2erp.entities.User;
import tn.esprit.b2.esprit1718b2erp.services.UserServiceRemote;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class CreateUserController implements Initializable {

    @FXML
    private JFXTextField loginText;

    @FXML
    private JFXTextField adresseText;

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private JFXTextField mobileText;

    @FXML
    private JFXTextField emailText;

    @FXML
    private JFXTextField nameText;
    private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/UserService!tn.esprit.b2.esprit1718b2erp.services.UserServiceRemote";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void createUser(ActionEvent event) throws IOException {
    	Context context;
		try {
			context = new InitialContext();
			UserServiceRemote userServiceRemote = (UserServiceRemote) context.lookup(jndi);
	    	User user = new User(nameText.getText(), loginText.getText(), passwordText.getText(), mobileText.getText(), adresseText.getText(), emailText.getText());
	    	userServiceRemote.save(user);
	    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Sign up Confirmation");
	        alert.setContentText("User added with success");
	        alert.showAndWait();
	        if (alert.getResult().getText().equalsIgnoreCase("OK")) {
	        	Scene x = new Scene(FXMLLoader.load(getClass().getResource("../gui/Login.fxml")));
	            Stage stage = new Stage();
	            stage.setScene(x);
	            stage.show();
	            ((Node)(event.getSource())).getScene().getWindow().hide();
			}
		} catch (NamingException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Sign up Error");
	        alert.setContentText("Please check your data");
	        alert.showAndWait();
		}
    	
    }
    
}
