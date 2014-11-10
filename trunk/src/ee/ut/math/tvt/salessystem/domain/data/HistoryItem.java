package ee.ut.math.tvt.salessystem.domain.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Already bought HistoryItem. stockItem duplicates name and price for
 * preserving history.
 */
public class HistoryItem implements Cloneable, DisplayableItem {

	private String date;
	private String time;
	private double total;
	private List<SoldItem> items;

	public HistoryItem(List<SoldItem> items) {
		this(new SimpleDateFormat("yyyy/MM/dd").format(new Date()),
				new SimpleDateFormat("HH:mm:ss").format(new Date()), items);
	}

	public HistoryItem(String date, String time, List<SoldItem> items) {
		this.date = date;
		this.time = time;
		this.items = items;
		double total = 0;
		for (final SoldItem item : items) {
			total += item.getSum();
		}
		this.total = total;
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

}
