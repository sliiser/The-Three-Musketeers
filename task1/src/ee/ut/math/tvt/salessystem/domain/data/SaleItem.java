package ee.ut.math.tvt.salessystem.domain.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Already bought HistoryItem. stockItem duplicates name and price for
 * preserving history.
 */
@Entity
@Table(name = "SALEITEM")
public class SaleItem implements Cloneable, DisplayableItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DATE")
	private String date;

	@Column(name = "TIME")
	private String time;

	@Column(name = "TOTAL")
	private double total;

	@Transient
	private List<SoldItem> items;

	@Transient
	public static final Logger log = Logger.getLogger(SaleItem.class);

	public SaleItem() {
		log.debug(this.time);
	}

	public void setCurrentDate() {
		this.date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		this.time = new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public double getTotal() {
		return total;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public Long getID() {
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<SoldItem> getItems() {
		if (items == null) {
			return HibernateUtil.currentSession()
					.createQuery("from SoldItem where SALE_ID = " + id).list();
		} else {
			return items;
		}
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addRows(List<SoldItem> items) {
		double total = 0;
		this.items = items;
		for (final SoldItem item : items) {
			total += item.getSum();
		}
		this.total = total;
	}

}
