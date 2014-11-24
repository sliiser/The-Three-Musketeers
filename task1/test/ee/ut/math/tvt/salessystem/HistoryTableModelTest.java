package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SaleItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;

public class HistoryTableModelTest {
	
	private SoldItem soldItem1;
	private SoldItem soldItem2;
	private SoldItem soldItem3;

	private HistoryTableModel history1;
	
	@Before
	public void setUp() {
		history1 = new HistoryTableModel();
		soldItem1 = new SoldItem(new StockItem(1L,"Lauaviin","Odav ja halb", 4.5),1);
		soldItem2 = new SoldItem(new StockItem(2L,"Russkiy razmer", "Ehtne ja hea", 10.40), 2);
		soldItem3 = new SoldItem(new StockItem(3L, "Y2K", "Vene kvaliteet", 9.50), 3);
	}

	@Test
	public void testHistoryAddItem() {
		
		List<SoldItem> soldItemsList = new ArrayList<SoldItem>();
		soldItemsList.add(soldItem1);
		soldItemsList.add(soldItem2);
		soldItemsList.add(soldItem3);
		
		SaleItem saleItem = new SaleItem();
		saleItem.addRows(soldItemsList);
		saleItem.setCurrentDate();
		
		history1.addItem(saleItem);
		
		assertSame(saleItem, history1.getItem(0));

	}
}
