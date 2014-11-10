package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SaleItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {

	private static final Logger log = Logger.getLogger(SalesDomainControllerImpl.class);
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx  = session.beginTransaction();
			
			//Insert all items to SOLDITEM
			for (SoldItem item : goods){
				session.save(item);
			}
			//Create and insert a HistoryItem into SALES
			SaleItem si = new SaleItem();
			si.addRows(goods);
			session.save(si);
			
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			log.error(e);
		}
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	@SuppressWarnings("unchecked")
	public List<StockItem> loadWarehouseState() {
		return HibernateUtil.currentSession().createQuery("from StockItem").list();
	}
	@SuppressWarnings("unchecked")
	public List<SaleItem> loadHistoryState() {
		return HibernateUtil.currentSession().createQuery("from SaleItem").list();
	}
	public void endSession() {
	    HibernateUtil.closeSession();
	}
}
