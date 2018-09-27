package tn.esprit.b2.esprit1718b2erp.app.client.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/HomeWindow.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		//stage.setFullScreen(true);
		//stage.setMaximized(true);
		stage.setTitle("Orion ERP");
		String path = "file:///C:/Users/Khalil/git/esprit1718b2erp1/esprit1718b2erp/esprit1718b2erp-client/src/main/java/tn/esprit/b2/esprit1718b2erp/app/client/ressources/logoerp.PNG";
		Image image = new Image(path);
		stage.getIcons().add(image);
		stage.show();
	}
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
