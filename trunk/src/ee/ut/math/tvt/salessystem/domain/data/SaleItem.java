package ee.ut.math.tvt.salessystem.domain.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	public SaleItem() {
		this(new SimpleDateFormat("yyyy/MM/dd").format(new Date()),
				new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}

	public SaleItem(String date, String time) {
		this.date = date;
		this.time = time;
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

	public List<SoldItem> getItems() {
		return items;
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
