package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.SaleItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurhaseInfoTableModelTest {
	
	PurchaseInfoTableModel model1;
	SoldItem item1, item2, item3, item4;
	double sumOfItems;
	
	@Before
	public void setUp() throws Exception {
		model1 = new PurchaseInfoTableModel();
		item1 = new SoldItem(new StockItem(1L,"Lauaviin","Odav ja halb", 4.5),1);
		item2 = new SoldItem(new StockItem(2L,"Russkiy razmer", "Ehtne ja hea", 10.40), 2);
		item3 = new SoldItem(new StockItem(3L, "Y2K", "Vene kvaliteet", 9.50), 3);
		sumOfItems = 53.8;
	}

	@Test
	public void testAddSoldItem() {
		model1.addItem(item1);
		SoldItem sold1 = model1.getTableRows().get(0);
		assertEquals(item1.getStockItem().getId(), sold1.getStockItem().getId());
		assertEquals(item1.getStockItem().getName(), sold1.getStockItem().getName());
		assertEquals(item1.getStockItem().getDescription(), sold1.getStockItem().getDescription());
		assertEquals(item1.getStockItem().getPrice(), sold1.getStockItem().getPrice(), 0.0001);
		assertEquals(item1.getStockItem().getQuantity(), sold1.getStockItem().getQuantity());
		assertEquals(item1.getQuantity(), sold1.getQuantity());
	}
	
	@Test
	public void testGetSumWithMutlipleItems(){		
		List<SoldItem> soldItemsList = new ArrayList<SoldItem>();
		soldItemsList.add(item1);
		soldItemsList.add(item2);
		soldItemsList.add(item3);
		SaleItem saleItem = new SaleItem();
		saleItem.addRows(soldItemsList);
		saleItem.setCurrentDate();

		assertEquals(saleItem.getTotal(),sumOfItems,0.0001);
	}
	
	@Test
	public void testGetSumWithOneItem(){
		model1.addItem(item1);
		List<SoldItem> soldItemsList = new ArrayList<SoldItem>();
		soldItemsList.add(item1);
		SaleItem saleItem = new SaleItem();
		saleItem.addRows(soldItemsList);
		saleItem.setCurrentDate();
		
		assertEquals(saleItem.getTotal(),4.5,0.0001);
	}
	
	@Test
	public void testGetSumWithNoItems(){
		SaleItem saleItem = new SaleItem();
		saleItem.setCurrentDate();
		assertEquals(saleItem.getTotal(),0.0,0.0001);
	}
}
