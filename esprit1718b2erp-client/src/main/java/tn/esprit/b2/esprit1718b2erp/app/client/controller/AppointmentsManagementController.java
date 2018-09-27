package tn.esprit.b2.esprit1718b2erp.app.client.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.services.AppointmentServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;

public class AppointmentsManagementController implements Initializable {

	@FXML
	private JFXComboBox<String> type_combo;
	@FXML
	private JFXComboBox<Contact> client_combo;
	@FXML
	private DatePicker date_picker;
	@FXML
	private TextArea sujet_area;
	@FXML
	private JFXButton confirm_button;
	@FXML
	private JFXButton cancel_button;
	@FXML
	private TableView<Appointment> appointment_tab;
	@FXML
	private TableColumn<?, ?> client_colone;
	@FXML
	private TableColumn<?, ?> type_colone;
	@FXML
	private TableColumn<?, ?> date_colone;
	@FXML
	private TableColumn<?, ?> topic_colone;
	@FXML
	private JFXTextField search_text;
	@FXML
	private JFXButton search_button;
	@FXML
	private JFXButton delete_button;
	@FXML
	private JFXButton update_button;
	@FXML
	private JFXRadioButton client_radio;
	@FXML
	private JFXRadioButton type_radio;
	@FXML
	private JFXRadioButton topic_radio;

    @FXML
    private JFXButton refrech_button;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
		this.Views();
		this.ChargeClient();
		this.SendSMS();

		
		
		
		

	}

	@FXML
	private void typeSelected(ActionEvent event) {
		topic_radio.setSelected(false);
		client_radio.setSelected(false);
	}

	@FXML
	private void topicSelected(ActionEvent event) {
		client_radio.setSelected(false);
		type_radio.setSelected(false);

	}

	@FXML
	private void clientSelected(ActionEvent event) {
		type_radio.setSelected(false);
		topic_radio.setSelected(false);
	}

	private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/AppointmentService!tn.esprit.b2.esprit1718b2erp.services.AppointmentServiceRemote";
	private String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/ContactService!tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote";

	@FXML
	private void ConfirmAction(ActionEvent event) throws ParseException {

		try {
			Context context = new InitialContext();
			AppointmentServiceRemote appointmentServiceRemote = (AppointmentServiceRemote) context.lookup(jndi2);
			if(verification()==true){
			Date date = java.sql.Date.valueOf(date_picker.getValue());
			Date dates = new Date();
			if (date.compareTo(dates)>=1) {
				
			
			/*String dateF = date.toString();
			Date dates =new SimpleDateFormat("YYYY-MM-dd").parse(dateF);
			System.out.println(dates);
			*/
			Appointment appointment = new Appointment(sujet_area.getText(), type_combo.getValue(), date,
					client_combo.getValue());
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Warning");
	        alert.setContentText("Do you really want to confirm this appoinment");
	        alert.showAndWait();
	        if (alert.getResult().getText().equalsIgnoreCase("OK")) {
			appointmentServiceRemote.save(appointment);
	        }
			}
			else{
				Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Warning");
		        alert.setContentText("this date is unvalid");
		        alert.showAndWait();
				
			}
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Views();

	}

	@FXML
	private void CancelAction(ActionEvent event) {
		

	}

	@FXML
	private void SearchAction(ActionEvent event) {
		/*if (client_radio.isSelected()) {
			try {
				Context context = new InitialContext();
				AppointmentServiceRemote appointmentServiceRemote = (AppointmentServiceRemote) context.lookup(jndi2);
				appointment_tab.setItems((FXCollections
						.observableArrayList(appointmentServiceRemote.getAppointmentByTopic(search_text.getText()))));

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}*/
		try {
			Context context = new InitialContext();
			AppointmentServiceRemote appointmentServiceRemote = (AppointmentServiceRemote) context.lookup(jndi2);
			
			if(client_radio.isSelected()){
				appointment_tab.setItems((FXCollections
						.observableArrayList(appointmentServiceRemote.getAppointmentByName(search_text.getText()))));
			}
			if(type_radio.isSelected()){
				appointment_tab.setItems((FXCollections
						.observableArrayList(appointmentServiceRemote.getAppointmentByType(search_text.getText()))));
			}
			if(topic_radio.isSelected()){
				appointment_tab.setItems((FXCollections
						.observableArrayList(appointmentServiceRemote.getAppointmentByTopic(search_text.getText()))));
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void DeleteAction(ActionEvent event) {
		try {
			Context context = new InitialContext();
			AppointmentServiceRemote appointmentServiceRemote = (AppointmentServiceRemote) context.lookup(jndi2);
			Appointment appointment = appointment_tab.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Warning");
	        alert.setContentText("Do you really want to delete this contact");
	        alert.showAndWait();
	        if (alert.getResult().getText().equalsIgnoreCase("OK")) {
			appointmentServiceRemote.delete(appointment);
			this.Views();}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void updateAction(ActionEvent event) {
		
	}
	
	 @FXML
	    void RefrechAction(ActionEvent event) {
		 this.Views();
		// this.callURL();

	    }

	private void Views() {
		for (int i = 0; i < appointment_tab.getItems().size(); i++) {
			appointment_tab.getItems().clear();
		}
		try {
			Context context = new InitialContext();
			AppointmentServiceRemote appointmentServiceRemote = (AppointmentServiceRemote) context.lookup(jndi2);
			client_colone.setCellValueFactory(new PropertyValueFactory<>("contact"));
			type_colone.setCellValueFactory(new PropertyValueFactory<>("type"));
			date_colone.setCellValueFactory(new PropertyValueFactory<>("appointementDate"));
			topic_colone.setCellValueFactory(new PropertyValueFactory<>("sujet"));
			ObservableList<Appointment> l = FXCollections.observableArrayList(appointmentServiceRemote.findAll());
			appointment_tab.setItems(l);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
    public  void callURL(String a) {
         
        String myURL="https://rest.nexmo.com/sms/json?api_key=fa966f8a&api_secret=02KdmnMmerHnJWEa&to=216"+
                "21492477"+"&from=OrionErp&text=you+have+an+appointment+tomorrow+please+be+in+Time+"+a;
        System.out.println(myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }
        
        
    }

    public void ChargeClient(){
    	List<Contact> contacts = new ArrayList<>();

		try {
			Context context = new InitialContext();
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi1);
			List<Contact> contactsOld = contactServiceRemote.findAll();
			for (Contact contact : contactsOld) {
				contacts.add(contact);
				ObservableList<Contact> items = FXCollections.observableArrayList(contacts);
				client_combo.setItems(items);
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void SendSMS(){
    	
    	try {
			Context context = new InitialContext();
			AppointmentServiceRemote appointmentServiceRemote = (AppointmentServiceRemote) context.lookup(jndi2);
			List<Appointment> lista = appointmentServiceRemote.findAll();
			Date dates = new Date();
			for (Appointment appointment : lista) {
				//if(appointment.getAppointementDate().compareTo(dates))
				Date dates2 = appointment.getAppointementDate();
				long x = ((dates2.getTime()-dates.getTime())/3600000);
					if(x<24 && x>0){
						this.callURL(appointment.getContact().getName());
						//System.out.println("cc");
					}
				//System.out.println(x);
				//System.out.println("cc");
			}
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public boolean verification() {
    if (client_combo.getValue()==null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("select a Contact please ");
        alert.showAndWait();
        client_combo.requestFocus();
        return false;
    }
    
     if ("".equals(sujet_area.getText())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("check the  topic please");
        alert.showAndWait();
        sujet_area.requestFocus();
        return false;
    }
     
     if (date_picker.getValue()==null) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText("select date please  ");
         alert.showAndWait();
         date_picker.requestFocus();
         return false;
     }
     
     if (type_combo.getValue()==null) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText("select a appointment type please ");
         alert.showAndWait();
         type_combo.requestFocus();
         return false;
     }
    
   
    return true;

	}
    
    
}




