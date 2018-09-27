package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DragDropEvent;

import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Production;
import tn.esprit.b2.esprit1718b2erp.entities.ProductionStatus;
import tn.esprit.b2.esprit1718b2erp.services.ProductServiceLocal;
import tn.esprit.b2.esprit1718b2erp.services.ProductionServiceLocal;

@ManagedBean
@ViewScoped
public class ManufacturingPlanning {

	private Production production;
	private List<Production> droppedPT;
	private List<Production> droppedPD;
	private List<Production> todo;
	private List<Production> doing;
	private List<Production> done;
	@EJB
	private ProductionServiceLocal productionServiceLocal;
	@EJB
	private ProductServiceLocal productServiceLocal;
	
	@PostConstruct
	public void init(){
		droppedPT = new ArrayList<>();
		droppedPD = new ArrayList<>();
		todo = new ArrayList<>();
		doing = new ArrayList<>();
		done = new ArrayList<>();
		todo = productionServiceLocal.findProductionByStatus("To_Do");
		doing = productionServiceLocal.findProductionByStatus("Doing");
		done = productionServiceLocal.findProductionByStatus("Done");
	}
	public void doUpdateDoing(){
		ProductionStatus productionStatus;
		productionStatus = ProductionStatus.valueOf("Doing");
		production.setProductionStatus(productionStatus.toString());
		productionServiceLocal.update(production);
		this.init();
	}
	public void doUpdateDone(){
		ProductionStatus productionStatus;
		productionStatus = ProductionStatus.valueOf("Done");
		production.setProductionStatus(productionStatus.toString());
		Product product = production.getProducts();
		product.setQuantity(product.getQuantity() + production.getQuantite());
		productServiceLocal.update(product);
		productionServiceLocal.update(production);
		this.init();
	}
	public void onTodoDrop(DragDropEvent ddEvent) {
        Production p = ((Production) ddEvent.getData());
        droppedPT.add(p);
        todo.remove(p);
    }
	public void onDoingDrop(DragDropEvent ddEvent) {
        Production p = ((Production) ddEvent.getData());
        droppedPD.add(p);
        doing.remove(p);
    }

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
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
	public List<Production> getDroppedPT() {
		return droppedPT;
	}
	public void setDroppedPT(List<Production> droppedPT) {
		this.droppedPT = droppedPT;
	}
	public List<Production> getDroppedPD() {
		return droppedPD;
	}
	public void setDroppedPD(List<Production> droppedPD) {
		this.droppedPD = droppedPD;
	}
	
	
}
