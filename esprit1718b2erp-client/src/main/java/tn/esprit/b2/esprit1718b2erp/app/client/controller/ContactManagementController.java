package tn.esprit.b2.esprit1718b2erp.app.client.controller;
import com.jfoenix.controls.IFXTextInputControl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.plaf.synth.SynthOptionPaneUI;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Claims;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.services.AppointmentServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ClaimsServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;


public class ContactManagementController implements Initializable {
	private static URL url1;
	private static ResourceBundle rb1 ;
	
    @FXML
    private Tab add_contact_bt;
    @FXML
    private JFXTextField name_text;
    @FXML
    private JFXTextField address_text;
   /* @FXML
    private JFXTextField region_text;*/
    @FXML
    private JFXTextField tel_text;
    @FXML
    private JFXTextField email_text;
   /* @FXML
    private JFXTextField sector_text;*/
    @FXML
    private JFXComboBox<?> type_combo;
    @FXML
    private JFXButton save_button;
    @FXML
    private JFXButton cancel_button;
    @FXML
    private TableView<Contact> contact_tab;
    @FXML
    private TableColumn<?, ?> name_col;
    @FXML
    private TableColumn<?, ?> address_col;
    @FXML
    private TableColumn<?, ?> region_col;
    @FXML
    private TableColumn<?, ?> tel_col;
    @FXML
    private TableColumn<?, ?> email_col;
    @FXML
    private TableColumn<?, ?> sector_col;
    @FXML
    private TableColumn<?, ?> type_col;
    @FXML
    private JFXRadioButton radio_name;
    @FXML
    private JFXRadioButton radio_region;
    @FXML
    private JFXRadioButton ratio_activity;
    @FXML
    private JFXRadioButton type_radio;
    @FXML
    private JFXTextField search_text;
    @FXML
    private JFXButton search_button;
    @FXML
    private JFXButton refrech_button;
    @FXML
    private JFXButton delete_button;
    @FXML
    private JFXButton update_button;
    @FXML
    private JFXButton link_button;
    
    @FXML
    private JFXComboBox<?> sector_combo;
    @FXML
    private JFXComboBox<?> region_combo;
  /*  @FXML
    private ImageView imgView;*/
    @FXML
    private JFXButton reset_button;

    /**
     * Initializes the controller class.
     */
    
    private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/ClaimsService!tn.esprit.b2.esprit1718b2erp.services.ClaimsServiceRemote";
	private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/AppointmentService!tn.esprit.b2.esprit1718b2erp.services.AppointmentServiceRemote";
	private String jndi1="esprit1718b2erp-ear/esprit1718b2erp-service/ContactService!tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote";
    
    public static Contact c;
	public static int i ;
    
    public static int getI() {
		return i;
	}

	public static void setI(int i) {
		ContactManagementController.i = i;
	}

	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	this.Views();
    	/*String path =("file/// D:/javaEETools/Contact.png");
    	 Image img = new Image(path);
         imgView.setImage(img);
    	*/
    	
        
    }   
    	
    @FXML
    private void SaveAction(ActionEvent event) {
    	try{
    	Context context =new InitialContext();
    	
		ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
		if(verification()==true){
		//	if(checkAdress(email_text.getText())==true){
		Contact contact = new Contact(name_text.getText(),address_text.getText(),tel_text.getText(),email_text.getText(),sector_combo.getValue().toString(),region_combo.getValue().toString(),type_combo.getValue().toString());
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setContentText("Do you really want to add this contact");
        alert.showAndWait();
        if (alert.getResult().getText().equalsIgnoreCase("OK")) {
		contactServiceRemote.save(contact);
		this.Views();
        }
		//	}
				}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    }
    
    @FXML
    private void ResetAction(ActionEvent event){
    	
    	//this.initialize(url1, rb1);
    	this.name_text.setText(null);
    	this.address_text.setText(null);
    	this.tel_text.setText(null);
    	this.email_text.setText(null);
    	this.sector_combo.setValue(null);
    	this.region_combo.setValue(null);
    	this.type_combo.setValue(null);
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    	
    }
    @FXML
    private void nameSelected(ActionEvent event){
    	radio_region.setSelected(false);
    	ratio_activity.setSelected(false);
    	type_radio.setSelected(false);
    }
    @FXML
    private void regionSelected(ActionEvent event){
    	radio_name.setSelected(false);
    	ratio_activity.setSelected(false);
    	type_radio.setSelected(false);
    }
    @FXML
    private void typeSelected(ActionEvent event){
    	radio_region.setSelected(false);
    	ratio_activity.setSelected(false);
    	radio_name.setSelected(false);
    }
    
    @FXML
    private void sectorSelected(ActionEvent event){
    	radio_region.setSelected(false);
    	radio_name.setSelected(false);
    	type_radio.setSelected(false);
    }
    @FXML
    private void SearchAction(ActionEvent event) {
    	
    	if (radio_name.isSelected()){
    			
    		try {
    			Context context = new InitialContext();
				ContactServiceRemote contactServiceRemote=(ContactServiceRemote) context.lookup(jndi1);
				contact_tab.setItems((FXCollections.observableArrayList(contactServiceRemote.searchContactByName(search_text.getText()))) );
					//contact_tab.setItems((FXCollections.observableArrayList(contactServiceRemote.FindName(search_text.getText()))));
    			

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    		
    	if (radio_region.isSelected()){
    		 	
    		try {
    			Context context = new InitialContext();
				ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
				contact_tab.setItems(FXCollections.observableArrayList(contactServiceRemote.getContactByRegion(search_text.getText())));
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			}
    		
    	if (type_radio.isSelected()){
    		
    		try {
    			Context context = new InitialContext();
				ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
				contact_tab.setItems(FXCollections.observableArrayList(contactServiceRemote.getContactByType(search_text.getText())));
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	if(ratio_activity.isSelected())
    	{
    		try {
    			Context context = new InitialContext();
				ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
				contact_tab.setItems(FXCollections.observableArrayList(contactServiceRemote.getContactByActivitySector(search_text.getText())));
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    		
    		
    	}
    
    @FXML
    private void search(KeyEvent event) throws NamingException{
    	
    	
    }
    
    @FXML
    private void linkAction(ActionEvent event) {
    	
    	Scene x;
		try {
			
			x = new Scene(FXMLLoader.load(getClass().getResource("../gui/terminal.fxml")));
			 Stage stage = new Stage();
		        stage.setScene(x);
		        stage.show();
		
		} catch (IOException e) {
				
			
			e.printStackTrace();
		}
		this.Views();
    	
    	
    }
    

    @FXML
    private void RefrechAction(ActionEvent event) {
    	this.Views();
    	this.search_text.setText(null);
    	this.radio_name.setSelected(false);
    	this.radio_region.setSelected(false);
    	this.ratio_activity.setSelected(false);
    	this.type_radio.setSelected(false);
    	
    }

    @FXML
    private void DeleteAction(ActionEvent event) {
    	/*try {
    	
    			Contact contact ;
			 Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
			contact= contact_tab.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Warning");
	        alert.setContentText("Do you really want to delete this Contact");
	        alert.showAndWait();
	        if (alert.getResult().getText().equalsIgnoreCase("OK")) {  
			
			contactServiceRemote.delete(contact);
	            this.Views();
	        }
	            
	            
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	try {
    		
			Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
			AppointmentServiceRemote appointmentServiceRemote= (AppointmentServiceRemote) context.lookup(jndi2);
			ClaimsServiceRemote claimsServiceRemote = (ClaimsServiceRemote) context.lookup(jndi);
		Contact	contact= contact_tab.getSelectionModel().getSelectedItem();
		
				
			List<Appointment> appointmentClient= appointmentServiceRemote.getAppointmentByClient(contact);
			List <Claims> claimsClient = claimsServiceRemote.getClaimstByClient(contact);
					if(appointmentClient.isEmpty() && claimsClient.isEmpty())
					{
					
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				        alert.setTitle("Warning");
				        alert.setContentText("Do you really want to delete this Contact");
				        alert.showAndWait();
				        if (alert.getResult().getText().equalsIgnoreCase("OK")) {  
						
						contactServiceRemote.delete(contact);
				            this.Views();
				        }
					}
					else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
				        alert.setTitle("Warning");
				        alert.setContentText("this contact could not be removed ");
				        alert.showAndWait();
				        this.Views();
					}
					

    	
    	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }

    @FXML
    private void UpdateAction(ActionEvent event) {
    
    	
    	
    	Scene y;
		try {
			Contact	contact= contact_tab.getSelectionModel().getSelectedItem();
			this.c = contact;
			if(contact == null){
				Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Warning");
		        alert.setContentText("you must select a Contact  ");
		        alert.showAndWait();
			}
			else{
			y = new Scene(FXMLLoader.load(getClass().getResource("../gui/UpdateContact.fxml")));
			 Stage stage = new Stage();
		        stage.setScene(y);
		        stage.show();}
		
		} catch (IOException e) {
				
			
			e.printStackTrace();
		}
		this.Views();
    	
    }
    
	private void Views(){
		for (int i = 0; i < contact_tab.getItems().size(); i++) {
			contact_tab.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
			name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
			address_col.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			region_col.setCellValueFactory(new PropertyValueFactory<>("region"));
			tel_col.setCellValueFactory(new PropertyValueFactory<>("mobile"));
			email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
			sector_col.setCellValueFactory(new PropertyValueFactory<>("activitySector"));
			type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
			ObservableList<Contact> l = FXCollections.observableArrayList(contactServiceRemote.findAll());
			contact_tab.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	///////////contrl saisie
	
	public boolean verification() {

        if ("".equals(name_text.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("check the name please");
            alert.showAndWait();
            name_text.requestFocus();
            return false;
        }
        
        if (sector_combo.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("check the activity sector please please");
            alert.showAndWait();
            sector_combo.requestFocus();
            return false;
        }
        
        if (region_combo.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("check the region  please please");
            alert.showAndWait();
            region_combo.requestFocus();
            return false;
        }
        if (type_combo.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("check the Contact Type please please");
            alert.showAndWait();
            type_combo.requestFocus();
            return false;
        }
        
         if ("".equals(address_text.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("check the address please");
            alert.showAndWait();
            address_text.requestFocus();
            return false;
        }
        if ((tel_text.getText().length() != 8)||(IsNumber(tel_text.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("check the phone number please");
            alert.showAndWait();
            tel_text.requestFocus();
            return false;
        }
       
        
        if ("".equals(email_text.getText()) || (checkAdress(email_text.getText())==false) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("check the E-mail please");
            alert.showAndWait();
            email_text.requestFocus();
            return false;
        }
  
        
        return true;

		}
		
		
	
	
	public static boolean checkAdress(String b) {
		String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(b);
		if (controler.matches()) {
			return true;

		}
		return false;
	}
	
	public boolean IsNumber(String x){
        boolean verif=false;
        try{
            Integer.parseInt(x);
        }
        catch(NumberFormatException e){
            verif=true;
        }
        return verif;
    }
	

}
