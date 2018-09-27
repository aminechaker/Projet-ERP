package tn.esprit.b2.esprit1718b2erp.app.client.controller;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.scene.control.TableView;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.b2.esprit1718b2erp.entities.Assets;
import tn.esprit.b2.esprit1718b2erp.entities.Benefits;
import tn.esprit.b2.esprit1718b2erp.entities.Employee;
import tn.esprit.b2.esprit1718b2erp.entities.Expenses;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.Quotation;
import tn.esprit.b2.esprit1718b2erp.entities.RawMaterial;
import tn.esprit.b2.esprit1718b2erp.services.AssetsServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.BenefitsServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ExpensesServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.QuotationServiceRemote;
import tn.esprit.b2.esprit1718b2erp.services.RawMaterialService;

public class BalanceController implements Initializable {



  

    @FXML
    private PieChart pie;

    
    private String jndi = "esprit1718b2erp-ear/esprit1718b2erp-service/BenefitstService!tn.esprit.b2.esprit1718b2erp.services.BenefitsServiceRemote";
    private String jndi2 = "esprit1718b2erp-ear/esprit1718b2erp-service/ExpensestService!tn.esprit.b2.esprit1718b2erp.services.ExpensesServiceRemote";
    private String jndi3 = "esprit1718b2erp-ear/esprit1718b2erp-service/RawMaterialService!tn.esprit.b2.esprit1718b2erp.services.RawMaterialServiceRemote";
    private String jndi4 = "esprit1718b2erp-ear/esprit1718b2erp-service/EmployeeService!tn.esprit.b2.esprit1718b2erp.services.EmployeeServiceRemote";
    private String jndi5 = "esprit1718b2erp-ear/esprit1718b2erp-service/QuotationService!tn.esprit.b2.esprit1718b2erp.services.QuotationServiceRemote";
   
    public List<Assets> assetDecreaseList = new ArrayList<>();
    public List<Assets> assetIncreaseList = new ArrayList<>();
    public List<RawMaterial> rawMaterialList = new ArrayList<>();
    public List<Employee> salariesList = new ArrayList<>();
    public List<Quotation> salesList = new ArrayList<>();
    public List<Benefits> benefitsList = new ArrayList<>();
    public List<Expenses> expensesList = new ArrayList<>();
    	
    @FXML
    public void generateBalance() throws NamingException {
    	Context context = new InitialContext();
    	BenefitsServiceRemote benefitsServiceRemote = (BenefitsServiceRemote) context.lookup(jndi);
    	ExpensesServiceRemote expensesServiceRemote = (ExpensesServiceRemote) context.lookup(jndi2);
    	float a = assetIncreaseList.get(0).getActual_value()-assetIncreaseList.get(0).getValue();
    	float b = assetDecreaseList.get(0).getValue()-assetIncreaseList.get(0).getActual_value();
    	float c = rawMaterialList.get(0).getPrice();
    	float d = salariesList.get(0).getSalary();
    	float e = salesList.get(0).getFinalPrice();
    	
    	for (int i = 1;i< assetIncreaseList.size();i++){
    	float j = assetIncreaseList.get(i).getActual_value()-assetIncreaseList.get(i).getValue();
        a = a + j ;
    	}
    	for (int i = 1;i< assetDecreaseList.size();i++){
        	float j= assetDecreaseList.get(i).getValue()-assetIncreaseList.get(i).getActual_value();	
        	b = b + j ;
        	}
    	for (int i = 1;i< assetDecreaseList.size();i++){
        	float j= rawMaterialList.get(i).getPrice();	
        	float w= rawMaterialList.get(i).getQuantity();
        	c = c + j*w;
        	}
    	for (int i = 1;i< salariesList.size();i++){
        	float j= salariesList.get(i).getSalary();	
        	d = d + j;
        	}
    	for (int i = 1;i< salesList.size();i++){
        	float j= salesList.get(i).getFinalPrice();	
        	e = e + j;
        	}
    	
    	Benefits benefit= new Benefits( "asset_benefits",a);
    	benefitsServiceRemote.update(benefit);
    	Expenses expense= new Expenses("assets_losses",b);
    	expensesServiceRemote.update(expense);
    	Expenses expense2= new Expenses("material_cost",c);
    	expensesServiceRemote.update(expense2);
    	Expenses salaries= new Expenses("salaries",d);
    	expensesServiceRemote.update(salaries);
    	Benefits sales_benefits= new Benefits("sales",e);
    	benefitsServiceRemote.update(sales_benefits);
    	
    }
    
    public void fillList()throws NamingException{
    	Context context = new InitialContext();
    	AssetsServiceRemote assetsServiceRemote = (AssetsServiceRemote) context.lookup(jndi2);
    	RawMaterialService rawmaterialServiceRemote= (RawMaterialService) context.lookup(jndi3);
    	EmployeeServiceRemote employeeServiceRemote = (EmployeeServiceRemote) context.lookup(jndi4);
    	QuotationServiceRemote quotationServiceRemote = (QuotationServiceRemote) context.lookup(jndi5);
    	BenefitsServiceRemote benefitsServiceRemote = (BenefitsServiceRemote) context.lookup(jndi);
    	ExpensesServiceRemote expensesServiceRemote = (ExpensesServiceRemote) context.lookup(jndi2);
    	
    	assetDecreaseList = assetsServiceRemote.getAssetsExp();
        assetIncreaseList = assetsServiceRemote.getAssetsBen();
        rawMaterialList = rawmaterialServiceRemote.sortByQuantity();
        salariesList = employeeServiceRemote.getEmployees();
        salesList = quotationServiceRemote.findSales();
        benefitsList= benefitsServiceRemote.getBenefits();
        expensesList= expensesServiceRemote.getExpenses();
    }
 
   
    public void showResult() throws NamingException{
    	Context context = new InitialContext();
    	float b = 0;
    	float e = 0;
    	
    	for (int i=0;i< benefitsList.size();i++){
    	 float j = benefitsList.get(i).getValue();
    	 b = b + j;
    	}
    	for (int i=0;i< expensesList.size();i++){
       	 float j = expensesList.get(i).getValue();
       	 e = e + j;
       	}
    	if (b >= e){
    		
    	}
    	else {
    		
    	}
	}
    
    public void  createPieChart() {
	
		try {
			Context context = new InitialContext();
			pie.setTitle("Balance chart");
			BenefitsServiceRemote benefitsServiceRemote = (BenefitsServiceRemote) context.lookup(jndi);
			ExpensesServiceRemote expensesServiceRemote = (ExpensesServiceRemote) context.lookup(jndi2);
			
			List<Benefits> sales_benefits = benefitsServiceRemote.getBenefitsByType("sales_benefits");
			List<Benefits> assets_benefits = benefitsServiceRemote.getBenefitsByType("asset_benefits");
			List<Expenses> salaries_expenses = expensesServiceRemote.getExpensesByType("salaries");
			List<Expenses> assets_expenses= expensesServiceRemote.getExpensesByType("assets_losses");
			List<Expenses> material_cost= expensesServiceRemote.getExpensesByType("material_cost");
			
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
		    new PieChart.Data("sales", sales_benefits.size()), new PieChart.Data("asset_benefits", assets_benefits.size()),
		    new PieChart.Data("salaries", salaries_expenses.size()),new PieChart.Data("assets_losses", assets_expenses.size()),new PieChart.Data("material_cost", material_cost.size()));
			pie.setData(pieChartData);
			pie.setClockwise(true);
			pie.setLabelLineLength(50);
			pie.setLabelsVisible(true);
			pie.setStartAngle(0);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			this.fillList();
			this.createPieChart();
			this.showResult();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
    
    
 
   

}