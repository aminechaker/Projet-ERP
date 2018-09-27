package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote;
import javafx.event.ActionEvent;

public class AddRawMaterialController {
	@FXML
    private JFXTextField quantityText;

    @FXML
    private JFXTextField descText;

    private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/RawMaterialService!tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote";
    @FXML
    void addRawMaterial(ActionEvent event) {
    	try {
			Context context = new InitialContext();
			RawMaterialServiceRemote rawMaterialServiceRemote = (RawMaterialServiceRemote) context.lookup(jndi);
			RawMaterial rawMaterial = new RawMaterial(descText.getText(), Integer.parseInt(quantityText.getText()));
			rawMaterialServiceRemote.save(rawMaterial);
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void returnbtn(ActionEvent event) {

    }

}
