package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;

/**
 * Stock item table model.
 */
public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StockTableModel.class);

	public HistoryTableModel() {
		super(new String[] {"Date", "Time", "Total"});
	}

	@Override
	protected Object getColumnValue(HistoryItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getDate();
		case 1:
			return item.getTime();
		case 2:
			return item.getTotal();
		}
		throw new IllegalArgumentException("Column index out of range");
	}
	public void addItem(final HistoryItem hItem) {
		rows.add(hItem);
		fireTableDataChanged();
	}
	public HistoryItem getItem(int index) {
		return rows.get(index);
	}

}
