package tn.esprit.b2.esprit1718b2erp.services;



import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Events;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Quotation;
import tn.esprit.b2.esprit1718b2erp.utilities.IGenericDAO;

@Local
public interface QuotationServiceLocal extends IGenericDAO<Quotation> {
	
	List<Quotation> findQuotationByClient(Contact contact);
	List<Quotation> findBillByClient(Contact contact);
	Double findTotalByClient(Contact contact);
	List<Quotation> sortByTaxes(Float taxes);
	List<Quotation> sortByClient(String client);
	List<Quotation> sortBySettlment(String settlment);
	List<Quotation> findSales();
	List<Quotation> findQuotations();
	Product findProductByQuotation(Quotation quotation);

}
