package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import tn.esprit.b2.esprit1718b2erp.entities.User;
import tn.esprit.b2.esprit1718b2erp.services.UserServiceRemote;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public class LoginController implements Initializable {

    @FXML
   private JFXTextField loginText;

   @FXML
   private JFXPasswordField passwordText;
   private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/UserService!tn.esprit.b2.esprit1718b2erp.services.UserServiceRemote";
   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
       // TODO
   }
   @FXML
   void loginAction(ActionEvent event) throws NamingException, IOException {
	   Context context = new InitialContext();
	   UserServiceRemote userServiceRemote = (UserServiceRemote) context.lookup(jndi);
	   User user = null;
	   user = userServiceRemote.login(loginText.getText(), passwordText.getText());
	   if (user != null) {
		   Scene x = new Scene(FXMLLoader.load(getClass().getResource("../gui/HomeWindow.fxml")));
           Stage stage = new Stage();
           stage.setScene(x);
           stage.setMaximized(true);
           stage.show();
           ((Node)(event.getSource())).getScene().getWindow().hide();
	}
	   else {
		   Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Authentification error");
           alert.setContentText("Please check your data");
           alert.showAndWait();
	}
   }
   @FXML
   void createAccountAction(ActionEvent event) throws IOException {
	   Scene x = new Scene(FXMLLoader.load(getClass().getResource("../gui/CreateUser.fxml")));
       Stage stage = new Stage();
       stage.setScene(x);
       stage.show();
       ((Node)(event.getSource())).getScene().getWindow().hide();
   }

}
