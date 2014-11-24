package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryItemTableModel;

public class HistoryItemTableModelTest {
	 private SoldItem item1;
	 private SoldItem item2;
	 private SoldItem item3;
	 private HistoryItemTableModel model1;
	 
	 @Before
	 public void startUp() {
		  model1 = new HistoryItemTableModel();
		  item1 = new SoldItem(new StockItem(1L,"Lauaviin","Odav ja halb", 4.5),1);
		  item2 = new SoldItem(new StockItem(2L,"Russkiy razmer", "Ehtne ja hea", 10.40), 2);
		  item3 = new SoldItem(new StockItem(3L, "Y2K", "Vene kvaliteet", 9.50), 3);
	 }
 
	 @Test
	 public void testAddMultipleItems() {
		  List<SoldItem> soldItemsList = new ArrayList<SoldItem>();
		  soldItemsList.add(item1);
		  soldItemsList.add(item2);
		  soldItemsList.add(item3);
		  model1.addItems(soldItemsList);
		  assertEquals(model1.getRowCount(), soldItemsList.size());
	 } 
 
}