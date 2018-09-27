package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javafx.scene.control.Alert;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.ProductionStatus;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceLocal;

@ManagedBean
@ViewScoped
public class ManufacturingBean {

	private Production production;
	private List<Production> productions;
	private String idProduct;
	private List<Product> products;
	private String idEmployee;
	private List<Employee> employees;
	private boolean showForm;

	@EJB
	private ProductionServiceLocal productionServiceLocal;
	@EJB
	private ProductServiceLocal productServiceLocal;
	@EJB
	private EmployeeServiceLocal employeeServiceLocal;

	@PostConstruct
	private void init() {
		production = new Production();
		products = productServiceLocal.findAll();
		employees = employeeServiceLocal.findAll();
		productions = productionServiceLocal.findAll();
		showForm = false;
	}

	public void doSelect() {
		showForm = true;
	}

	public void doCancel() {
		showForm = false;
	}

	public void doAddManufacturing() throws Exception {
		ProductionStatus productionStatus;
		Date sysdate = new Date();
		productionStatus = ProductionStatus.valueOf("To_Do");
		production.setProductionStatus(productionStatus.toString());
		Product product = productServiceLocal.find(Integer.parseInt(idProduct));
		Employee employee = employeeServiceLocal.find(Integer.parseInt(idEmployee));
		productionServiceLocal.assignProductionToProductAndEmployee(product, production, employee);
		this.sendGet(employee.getEmail(),employee.getName(),production.getDescription(),product.getDescription(),production.getQuantite(),production.getDateProduction());
	//	String oldstring = production.getDateProduction().toString();
		//System.out.println(oldstring);
		//Date dateProduction = new SimpleDateFormat( "EEE MMM dd HH:mm:ss z yyyy" ).parse(oldstring);
		/*if (production.getDateProduction().compareTo(sysdate) == 1) {
			productionStatus = ProductionStatus.valueOf("To_Do");
			Product product = productServiceLocal.find(Integer.parseInt(idProduct));
			Employee employee = employeeServiceLocal.find(Integer.parseInt(idEmployee));
			productionServiceLocal.assignProductionToProductAndEmployee(product, production, employee);
			// this.sendGet(employeeComboBox.getValue().getEmail(),employeeComboBox.getValue().getName(),production.getDescription(),produit.getValue().getDescription(),production.getQuantite(),production.getDateProduction());
			System.out.println("yessss");
		} else if (production.getDateProduction().compareTo(sysdate) == 0) {
			productionStatus = ProductionStatus.valueOf("Doing");
			Product product = productServiceLocal.find(Integer.parseInt(idProduct));
			Employee employee = employeeServiceLocal.find(Integer.parseInt(idEmployee));
			productionServiceLocal.assignProductionToProductAndEmployee(product, production, employee);
			/*
			 * this.sendGet(employeeComboBox.getValue().getEmail(),
			 * employeeComboBox.getValue().getName(),
			 * production.getDescription(), produit.getValue().getDescription(),
			 * production.getQuantite(), production.getDateProduction());
			 */
			//System.out.println("yessss");
		//}
		this.init();
	}
	public void sendGet(String email, String name, String desc,String prod,int quantity,Date date) throws Exception {

		String url = "http://localhost/mail.php?email="+email+"&name="+name+"&desc="+desc+"&prod="+prod+"&quantity="+quantity+"&date="+date;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	public void doDeleteManufacturing() {
		productionServiceLocal.delete(production);
		this.init();
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public List<Production> getProductions() {
		return productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

}
