package ee.ut.math.tvt.salessystem.ui.panels;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

/**
 * Purchase pane + shopping cart tabel UI.
 */
public class PurchaseItemPanel extends JPanel {

    private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
    // Text field on the dialogPane
    private JComboBox<ComboItem> barCodeField;
    private JTextField quantityField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField paymentField;
    private JLabel errorField;
    private JLabel returnField;
    private JLabel totalField;
    private JComponent dialogPane;
    private JComponent basketPane;
    private JComponent confirmPane;

    private JButton addItemButton;
    private JButton confirmButton;

    // Warehouse model
    private SalesSystemModel model;
    private PurchaseTab purchaseTab;
    /**
     * Constructs new purchase item panel.
     * 
     * @param model
     *            composite model of the warehouse and the shopping cart.
     */
    public PurchaseItemPanel(SalesSystemModel model, PurchaseTab controller) {
        this.model = model;

        setLayout(new GridBagLayout());
        purchaseTab = controller;
        dialogPane = drawDialogPane();
        add(dialogPane, getDialogPaneConstraints());
        basketPane = drawBasketPane();
        add(basketPane, getBasketPaneConstraints());
        confirmPane = drawConfirmPane();
        add(confirmPane, getConfirmPaneConstraints());
        confirmPane.setVisible(false);
        setEnabled(false);
    }

    // shopping cart pane
    private JComponent drawBasketPane() {

        // Create the basketPane
        JPanel basketPane = new JPanel();
        basketPane.setLayout(new GridBagLayout());
        basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

        // Create the table, put it inside a scollPane,
        // and add the scrollPane to the basketPanel.
        JTable table = new JTable(model.getCurrentPurchaseTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        basketPane.add(scrollPane, getBacketScrollPaneConstraints());

        return basketPane;
    }
    public void showConfirmPane(List<SoldItem> rows) {
        double total = 0;
		for (final SoldItem item : rows) {
			total += item.getSum();
		}
		totalField.setText(Double.toString(total));
    	dialogPane.setVisible(false);
    	basketPane.setVisible(false);
    	confirmPane.setVisible(true);
    	purchaseTab.buttonPane.setVisible(false);
    	
    }
    public void resetPurchase() {
    	totalField.setText("");
    	paymentField.setText("");
    	returnField.setText("");
    	dialogPane.setVisible(true);
    	basketPane.setVisible(true);
    	confirmPane.setVisible(false);
    	purchaseTab.buttonPane.setVisible(true);
    }
    // purchase dialog
    private JComponent drawConfirmPane() {

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Purchase summary"));

        paymentField = new JTextField();
        paymentField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            	double sum = Double.parseDouble(paymentField.getText()) - Double.parseDouble(totalField.getText());
            	if(sum >= 0) {
            		confirmButton.setEnabled(true);
            	} else {
            		confirmButton.setEnabled(false);
            	}
            	returnField.setText(Double.toString(sum));
            }
        });
        returnField = new JLabel();
        totalField = new JLabel();
        // - bar code
        panel.add(new JLabel("Total sum:"));
        panel.add(totalField);

        // - amount
        panel.add(new JLabel("Payment amount:"));
        panel.add(paymentField);

        // - name
        panel.add(new JLabel("Return:"));
        panel.add(returnField);
        

    	// Create and add the button
        confirmButton = new JButton("Confirm purchase");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	resetPurchase();
            	purchaseTab.submitParsedOrder();
            }
        });
        confirmButton.setEnabled(false);
        panel.add(confirmButton);

        // Create and add the button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	resetPurchase();
            }
        });
        panel.add(cancelButton);
        
        return panel;
    }
    // purchase dialog
    private JComponent drawDialogPane() {

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Product"));

        // Initialize the textfields
        barCodeField = new JComboBox<ComboItem>();
        updateDropdown();
        quantityField = new JTextField("1");
        nameField = new JTextField();
        priceField = new JTextField();

        // Fill the dialog fields if the bar code text field loses focus
        barCodeField.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	if(barCodeField.getItemCount() > 0) {
            	fillDialogFields();
            	}
            }
        });

        nameField.setEditable(false);
        priceField.setEditable(false);

        // == Add components to the panel

        // - bar code
        panel.add(new JLabel("Bar code:"));
        panel.add(barCodeField);

        // - amount
        panel.add(new JLabel("Amount:"));
        panel.add(quantityField);

        // - name
        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        // - price
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        
        //
        errorField = new JLabel();
        panel.add(errorField);
    	errorField.setVisible(false);
        
    	// Create and add the button
        addItemButton = new JButton("Add to cart");
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemEventHandler();
            }
        });

        panel.add(addItemButton);

        return panel;
    }

    // Fill dialog with data from the "database".
    public void fillDialogFields() {
        StockItem stockItem = getStockItemByBarcode();

        if (stockItem != null) {
            nameField.setText(stockItem.getName());
            String priceString = String.valueOf(stockItem.getPrice());
            priceField.setText(priceString);
        } else {
            reset();
        }
    }
    public void updateDropdown() {
    	barCodeField.removeAllItems();
		barCodeField.addItem(new ComboItem((long) 0, "Vali toode"));
		for (final StockItem stockItem : model.getWarehouseTableModel().rows) {
			barCodeField.addItem(new ComboItem(stockItem.getId(), stockItem.getName()));
		}
    }

    // Search the warehouse for a StockItem with the bar code entered
    // to the barCode textfield.
    private StockItem getStockItemByBarcode() {
        try {
        	Object item = barCodeField.getSelectedItem();
            return model.getWarehouseTableModel().getItemById(((ComboItem)item).getKey());
        } catch (NumberFormatException ex) {
            return null;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    /**
     * Add new item to the cart.
     */
    public void addItemEventHandler() {
        // add chosen item to the shopping cart.
        StockItem stockItem = getStockItemByBarcode();
        if (stockItem != null) {
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                quantity = 1;
            }
            int stock = stockItem.getQuantity();
            if(stock - quantity < 0) {
            	showError("Laos järel " + stock + " " + (stock == 1 ? "toode" : "toodet"));
            	log.debug(stockItem.getQuantity());
            } else {
            	errorField.setVisible(false);
            	stockItem.setQuantity(stock - quantity);
            	model.getCurrentPurchaseTableModel().addItem(new SoldItem(stockItem, quantity));
            }
        } else {
        	showError("Vali toode");
        }
    }
    
    public void showError(String msg) {
    	errorField.setText("<html><font color='red'>" + msg + "</font></html>");
    	errorField.setVisible(true);
    }

    /**
     * Sets whether or not this component is enabled.
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.addItemButton.setEnabled(enabled);
        this.barCodeField.setEnabled(enabled);
        this.quantityField.setEnabled(enabled);
    }

    /**
     * Reset dialog fields.
     */
    public void reset() {
        barCodeField.setSelectedIndex(0);
        quantityField.setText("1");
        nameField.setText("");
        priceField.setText("");
    }

    /*
     * === Ideally, UI's layout and behavior should be kept as separated as
     * possible. If you work on the behavior of the application, you don't want
     * the layout details to get on your way all the time, and vice versa. This
     * separation leads to cleaner, more readable and better maintainable code.
     * 
     * In a Swing application, the layout is also defined as Java code and this
     * separation is more difficult to make. One thing that can still be done is
     * moving the layout-defining code out into separate methods, leaving the
     * more important methods unburdened of the messy layout code. This is done
     * in the following methods.
     */

    // Formatting constraints for the dialogPane
    private GridBagConstraints getDialogPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 0d;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.NONE;

        return gc;
    }

    // Formatting constraints for the dialogPane
    private GridBagConstraints getConfirmPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 0.2;
        gc.weighty = 0d;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.NONE;

        return gc;
    }

    // Formatting constraints for the basketPane
    private GridBagConstraints getBasketPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 1.0;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.BOTH;

        return gc;
    }

    private GridBagConstraints getBacketScrollPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        return gc;
    }

}
