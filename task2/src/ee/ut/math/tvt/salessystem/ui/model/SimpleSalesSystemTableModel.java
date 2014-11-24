package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;

public abstract class SimpleSalesSystemTableModel<T extends DisplayableItem>
		extends SalesSystemTableModel<T> {
	private static final long serialVersionUID = 1L;

	protected List<T> rows;

	public SimpleSalesSystemTableModel(String[] headers) {
		super(headers);
		rows = new ArrayList<T>();
	}

	@Override
	public List<T> getTableRows() {
		return rows;
	}

	@Override
	public void clear() {
		rows = new ArrayList<T>();
		fireTableDataChanged();

	}

	@Override
	public void populateWithData(List<T> data) {
		rows.clear();
		rows.addAll(data);

	}

	@Override
	public void addRow(T row) {
		rows.add(row);
		fireTableDataChanged();
	}
}
