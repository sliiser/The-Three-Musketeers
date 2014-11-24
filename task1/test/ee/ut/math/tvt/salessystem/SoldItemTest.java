package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public class SoldItemTest {

	private StockItem item1;
	
	@Before
	public void setUp() {
		item1 = new StockItem(1L,"Lauaviin","Odav ja halb", 4.5, 11);
	}
	
	@Test
	public void testGetSum() {
		SoldItem solditem = new SoldItem(item1, 2);
		assertEquals(solditem.getSum(), 9, 0.001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		SoldItem solditem = new SoldItem(item1, 0);
		assertEquals(solditem.getSum(), 0, 0.001);
	}
}
