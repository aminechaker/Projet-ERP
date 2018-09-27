package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Quotation;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.QuotationServiceLocal;

@ManagedBean
@ViewScoped
public class SalesStatistics {

	private BarChartModel barModel;
	private PieChartModel pieModel1;
	private List<Quotation> quotations;
	private List<Product> products;
	@EJB
	private QuotationServiceLocal quotationServiceLocal;
	@EJB
	private ProductServiceLocal productServiceLocal;

	@PostConstruct
	public void init() {
		quotations = quotationServiceLocal.findAll();
		products = productServiceLocal.findAll();
		List<Quotation> x = new ArrayList<>();
		List<Quotation> y = new ArrayList<>();
		for (Quotation quotation : quotations) {
			if (quotation.getStatus().equalsIgnoreCase("Quotation")) {
				x.add(quotation);
			}
			if (quotation.getStatus().equalsIgnoreCase("Bill")) {
				y.add(quotation);
			}
		}
		
		pieModel1 = new PieChartModel();

		pieModel1.set("Quotations", x.size());
		pieModel1.set("Bills", y.size());

		pieModel1.setTitle("Quotation Statistics");
		pieModel1.setLegendPosition("w");
		ChartSeries bar = new ChartSeries();
		barModel = new BarChartModel();
		for (Product product : products) {
			bar.set(product.getDescription(),product.getNbVente());
		}
		barModel.addSeries(bar);
		barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Products");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Sales");
        yAxis.setMax(50);
        barModel.setAnimate(true);
		 
       
	}

	private void createPieModels() {
		createPieModel1();
	}

	private void createPieModel1() {
		List<Quotation> x = new ArrayList<>();
		List<Quotation> y = new ArrayList<>();
		for (Quotation quotation : quotations) {
			if (quotation.getStatus().equalsIgnoreCase("Quotation")) {
				x.add(quotation);
			}
			if (quotation.getStatus().equalsIgnoreCase("Bill")) {
				y.add(quotation);
			}
		}
		pieModel1 = new PieChartModel();

		pieModel1.set("Quotations", x.size());
		pieModel1.set("Bills", y.size());

		pieModel1.setTitle("Quotation Statistics");
		pieModel1.setLegendPosition("w");
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public List<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
}
