package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.SaleItem;

/**
 * Stock item table model.
 */
public class HistoryTableModel extends SalesSystemTableModel<SaleItem> {
	private static final long serialVersionUID = 1L;

	public HistoryTableModel() {
		super(new String[] {"Date", "Time", "Total"});
	}

	@Override
	protected Object getColumnValue(SaleItem item, int columnIndex) {
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
	public void addItem(final SaleItem hItem) {
		rows.add(hItem);
		fireTableDataChanged();
	}
	public SaleItem getItem(int index) {
		return rows.get(index);
	}

}
