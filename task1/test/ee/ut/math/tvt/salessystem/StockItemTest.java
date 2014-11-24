package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {

	private StockItem item1;
	
	@Before
	public void setUp() {
		item1 = new StockItem(1L,"Lauaviin","Odav ja halb", 4.95, 11);
	}
	
	@Test
	public void testClone() {
		StockItem cloned = (StockItem)item1.clone();
		assertEquals(cloned.getId(), item1.getId());
		assertEquals(cloned.getName(), item1.getName());
		assertEquals(cloned.getPrice(), item1.getPrice(), 0.0001);
		assertEquals(cloned.getDescription(), item1.getDescription());
		assertEquals(cloned.getQuantity(), item1.getQuantity());
	}

	@Test
	public void testGetColumn() {
		assertEquals(item1.getColumn(0), item1.getId());
		assertEquals(item1.getColumn(1), item1.getName());
		assertEquals(item1.getColumn(2), item1.getPrice());
		assertEquals(item1.getColumn(3), item1.getQuantity());
	}
	
}
