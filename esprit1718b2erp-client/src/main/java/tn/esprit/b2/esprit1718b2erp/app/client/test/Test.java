package tn.esprit.b2.esprit1718b2erp.app.client.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b2.esprit1718b2erp.entities.Appointment;
import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.ProductionStatus;
import tn.esprit.b2.esprit1718b2erp.services.AppointmentServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.AssignAppointmentRemote;
import tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.UserServiceRemote;

public class Test {

	public static void main(String[] args) throws ParseException {
		try {
			Context context = new InitialContext();
			String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductService!tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote";
			ProductServiceRemote productServiceRemote = (ProductServiceRemote) context.lookup(jndi);
			String jndi1 = "esprit1718b2erp-ear/esprit1718b2erp-service/UserService!tn.esprit.b2.esprit1718b2erp.services.UserServiceRemote";
			UserServiceRemote userServiceRemote = (UserServiceRemote) context.lookup(jndi1);
			String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/ComponentService!tn.esprit.b2.esprit1718b2erp.services.ComponentServiceRemote";
			Product product = productServiceRemote.find(1);
			String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductionService!tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote";
			String jndi4=  "esprit1718b2erp-ear/esprit1718b2erp-service/AppointmentService!tn.esprit.b2.esprit1718b2erp.services.AppointmentServiceRemote";
			AppointmentServiceRemote appointmentServiceRemote= (AppointmentServiceRemote) context.lookup(jndi4);
			
			String jndi5 ="esprit1718b2erp-ear/esprit1718b2erp-service/ContactService!tn.esprit.b2.esprit1718b2erp.services.ContactServiceRemote";
			ContactServiceRemote contactServiceRemote = (ContactServiceRemote) context.lookup(jndi5);
			String jndi6 = "esprit1718b2erp-ear/esprit1718b2erp-service/AssignAppointment!tn.esprit.b2.esprit1718b2erp.services.AssignAppointmentRemote";
			AssignAppointmentRemote assignAppointmentRemote = (AssignAppointmentRemote) context.lookup(jndi6);
			String jndi7 = "esprit1718b2erp-ear/esprit1718b2erp-service/ProductionService!tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote";
			ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi7);
			String jndi8 = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";
			EmployeeServiceRemote employeeServiceRemote = (EmployeeServiceRemote) context.lookup(jndi8);
			/*
			 * productServiceRemote.update(product); Employee employee = new
			 Employee(); userServiceRemote.update(employee);
			 */
			// RawMaterialServiceRemote materialServiceRemote =
			// (RawMaterialServiceRemote) context.lookup(jndi3);
			// RawMaterial rawMaterial = materialServiceRemote.find(1);
			//ProductionServiceRemote productionServiceRemote = (ProductionServiceRemote) context.lookup(jndi3);
			
			
			// Components components = new Components(0, product, rawMaterial);
			// Date date = new Date();
			// Production production = new Production(0, date, 20);
			// productionServiceRemote.save(production);
			//Production production2 = productionServiceRemote.find(1);
			
	//		productionServiceRemote.assignProductionToProduct(product, production2);

			/*
			String oldstring = "2011/01/18";
			Date date = new SimpleDateFormat("yyyy/MM/dd").parse(oldstring);
			ProductionStatus productionStatus = ProductionStatus.valueOf("Done");
			Production production = new Production(3, date, 20, productionStatus.toString());
			productionServiceRemote.update(production);
			//System.out.println(productionStatus.toString());
			*/
			Date date = new Date();
			//Appointment appointment = new Appointment("sujet","redez-vous",date);
		//	String oldstring = "2011/01/18";
		//Date date = new SimpleDateFormat("yyyy/MM/dd").parse(oldstring);
			
			//appointmentServiceRemote.update(appointment);
			
			Contact contact = new Contact("bohmid","aaa","123","jgjg","indus","tunis","Provider");
			contactServiceRemote.save(contact);
			Employee employee = new Employee("amine", "tydgfc", "hgv", "gvhj", "hvjb", "hjk", 20F, "yghjk");
			employeeServiceRemote.save(employee);
			//Contact contact = contactServiceRemote.find(0);
			//Appointment appointment = appointmentServiceRemote.find(0);
			//assignAppointmentRemote.assignContactToAppointment(contact, appointment);
			//System.out.println(productionServiceRemote.findTotalToDo());
	} catch (NamingException e) {
			e.printStackTrace();
		}

	}

}
