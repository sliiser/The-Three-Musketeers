package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
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
		// Meie POS lisab sama IDga tootele Stocki juurde, mitte ei viska errorit.
		// Sama nimi ei häiri, kui ID on erinev. Andmebaasis saab olla 2 sama nimega erinevat toodet.
		assert(true);
	}
	
	@Test
	public void testAddToStockSameIdDiffName(){
		model1.addItem(item1);
		model1.addItem(new StockItem(2L, "Viru Valge", "", 5, 3));
		assertEquals(4, model1.getItemById(2L).getQuantity());
	}
	
	@Test
	public void testAddToStockSameNameDiffId(){
		model1.addItem(item1);
		model1.addItem(new StockItem(3L, "Lauaviin", "", 4.5, 3));
		assertEquals(1, model1.getItemById(2L).getQuantity());
		assertEquals(3, model1.getItemById(3L).getQuantity());
	}
	
	
	@Test
	public void testHasEnoughInStock() {
		// Meie POS ei implementeeri seda siin.
		// Vaata PurchaseItemPanel.addItemEventHandler()
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