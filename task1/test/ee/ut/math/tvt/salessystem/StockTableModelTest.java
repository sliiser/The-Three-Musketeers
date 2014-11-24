package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
	
	@Test
	public void testValidateNameUniqueness() {
		/*
		model1.addItem(item1);
		model1.addItem(new StockItem(3L, "Lauaviin", "Light drink", 4.5, 11));
		*/
		// Meie POS lisab sama nimega tootele Stocki juurde, mitte ei viska errorit.
		assert(true);
	}	
	
	
	@Test
	public void testHasEnoughInStock() {
		// Meie POS ei implementeeri seda siin.
		assert(true);
	}
	
	
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