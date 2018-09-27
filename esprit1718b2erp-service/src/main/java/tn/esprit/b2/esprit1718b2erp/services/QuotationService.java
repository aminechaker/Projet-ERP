package tn.esprit.b2.esprit1718b2erp.services;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2erp.entities.Contact;
import tn.esprit.b2.esprit1718b2erp.entities.Events;
import tn.esprit.b2.esprit1718b2erp.entities.Product;
import tn.esprit.b2.esprit1718b2erp.entities.Quotation;
import tn.esprit.b2.esprit1718b2erp.utilities.GenericDAO;

/**
 * Session Bean implementation class QuotationService
 */
@Stateless
@LocalBean
public class QuotationService extends GenericDAO<Quotation> implements QuotationServiceRemote, QuotationServiceLocal {

	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public QuotationService() {
        super(Quotation.class);
    }

	@Override
	public List<Quotation> findQuotationByClient(Contact contact) {
		String req = "SELECT q FROM Quotation q JOIN q.contact c where c.id = :id";
		return entityManager.createQuery(req).setParameter("id", contact.getId()).getResultList();
	}
	//add
	@Override
	public List<Quotation> findSales() {
		String jpql = "SELECT Q FROM Quotation Q WHERE Q.Status = Bill";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Quotation> findBillByClient(Contact contact) {
		String req = "SELECT q FROM Quotation q JOIN q.contact c where c.id = :id AND q.status like :param";
		return entityManager.createQuery(req).setParameter("id", contact.getId()).setParameter("param", "Bill").getResultList();
	}

	@Override
	public Double findTotalByClient(Contact contact) {
		String req = "SELECT SUM(q.finalPrice) FROM Quotation q JOIN q.contact c where c.id = :id AND q.status like :param";
		return (Double) entityManager.createQuery(req).setParameter("id", contact.getId()).setParameter("param", "Bill").getSingleResult();
	}

	@Override
	public List<Quotation> sortByTaxes(Float taxes) {
		String req = "SELECT q FROM Quotation q WHERE format(q.taxes,2) = format(:taxes,2)";
		return entityManager.createQuery(req).setParameter("taxes", taxes).getResultList();
	}
	

	@Override
	public List<Quotation> sortByClient(String client) {
		String req = "SELECT q FROM Quotation q WHERE q.contact like :client";
		return entityManager.createQuery(req).setParameter("client", client).getResultList();
	}

	@Override
	public List<Quotation> sortBySettlment(String settlment) {
		String req = "SELECT q FROM Quotation q WHERE q.settelment like :settlment";
		return entityManager.createQuery(req).setParameter("settlment", settlment).getResultList();
	}

	@Override
	public Product findProductByQuotation(Quotation quotation) {
		String req = "SELECT p FROM Product p join p.quotations q WHERE q.id = :id";
		return (Product) entityManager.createQuery(req).setParameter("id", quotation.getId()).getSingleResult();
	}

	@Override
	public List<Quotation> findQuotations() {
		String jpql = "SELECT Q FROM Quotation Q WHERE Q.Status = :param";
		return entityManager.createQuery(jpql).setParameter("param", "Quotation").getResultList();
	}

}
