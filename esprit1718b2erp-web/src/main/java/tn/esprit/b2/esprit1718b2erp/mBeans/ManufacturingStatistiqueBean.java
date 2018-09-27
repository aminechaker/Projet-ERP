package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

import org.primefaces.model.chart.PieChartModel;

import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceLocal;

@ManagedBean
@ViewScoped
public class ManufacturingStatistiqueBean {

	private BubbleChartModel bubbleModel1;
	private PieChartModel pieModel1;
	private List<Product> products;
	private List<Production> todo;
	private List<Production> doing;
	private List<Production> done;

	@EJB
	private ProductionServiceLocal productionServiceLocal;
	@EJB
	private ProductServiceLocal productServiceLocal;

	@PostConstruct
	public void init() {
		todo = new ArrayList<>();
		doing = new ArrayList<>();
		done = new ArrayList<>();
		products = new ArrayList<>();
		this.pieChart();
		createBubbleModels();
	}

	private void pieChart() {
		todo = productionServiceLocal.findProductionByStatus("To_Do");
		doing = productionServiceLocal.findProductionByStatus("Doing");
		done = productionServiceLocal.findProductionByStatus("Done");
		pieModel1 = new PieChartModel();
		pieModel1.set("To Do", todo.size());
		pieModel1.set("Doing", doing.size());
		pieModel1.set("Done", done.size());
		pieModel1.setTitle("Manufacturing Status");
		pieModel1.setShowDataLabels(true);
		pieModel1.setDiameter(150);
		pieModel1.setLegendPosition("w");
	}

	private void createBubbleModels() {
		bubbleModel1 = initBubbleModel();
		bubbleModel1.setTitle("Bubble Chart");
		bubbleModel1.getAxis(AxisType.X).setLabel("Quantity");
		Axis yAxis = bubbleModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(100);
		yAxis.setLabel("Price");
		bubbleModel1.setAnimate(true);
	}

	private BubbleChartModel initBubbleModel() {
		products = productServiceLocal.findAll();
		BubbleChartModel model = new BubbleChartModel();
		for (Product product : products) {
			int x = (int) (Math.round(product.getPrice()));
			model.add(new BubbleChartSeries(product.getDescription(),product.getQuantity(),x,product.getQuantity()));
		}
		return model;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public List<Production> getTodo() {
		return todo;
	}

	public void setTodo(List<Production> todo) {
		this.todo = todo;
	}

	public List<Production> getDoing() {
		return doing;
	}

	public void setDoing(List<Production> doing) {
		this.doing = doing;
	}

	public List<Production> getDone() {
		return done;
	}

	public void setDone(List<Production> done) {
		this.done = done;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public BubbleChartModel getBubbleModel1() {
		return bubbleModel1;
	}

	public void setBubbleModel1(BubbleChartModel bubbleModel1) {
		this.bubbleModel1 = bubbleModel1;
	}

}
