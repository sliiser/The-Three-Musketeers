package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	private StockItem item1;
	private StockTableModel model1;
	
	@Before
	public void startUp() {
		item1 = new StockItem(2L, "Lauaviin", "", 4.5, 1);
		model1 = new StockTableModel();
	}
	
	@Test(expected = VerificationFailedException.class)
	public void testValidateNameUniqueness() throws VerificationFailedException {
		model1.addItem(item1);
		model1.addItem(new StockItem(3L, "Lauaviin", "Light drink", 4.5, 11));
	}	
	
	/* Panime selle SaleItemi alla
	@Test
	public void testHasEnoughInStock() {
	}
	*/
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		model1.addItem(item1);
		assertEquals(model1.getItemById(2L), item1);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		assertEquals(model1.getItemById(2L), item1);
	}	
	
}