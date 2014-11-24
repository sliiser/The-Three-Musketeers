package ee.ut.math.tvt.salessystem.ui.panels;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.apache.log4j.Logger;
/**
 * Add item pane modal UI.
 */

public class AddItemPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(StockTableModel.class);
	
	// Text field on the dialogPane
	private JTextField idField;
    private JTextField nameField;
    private JTextField descField;
    private JTextField priceField;
    private JTextField quantityField;

    private JButton addItemButton;

    // Warehouse model
    private SalesSystemModel model;
    
    /**
     * Constructs add item panel.
     * 
     * @param model
     *            composite model of the warehouse and the shopping cart.
     */
	
    public AddItemPanel(SalesSystemModel model) {
    	super("Add Product");
        this.model = model;
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setSize(320,280);
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        add(drawDialogPane(), getDialogPaneConstraints());
        
        setEnabled(false);
    }
    
 // purchase dialog
    private JComponent drawDialogPane() {

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Product Parameters"));
        
        idField = new JTextField();
        nameField = new JTextField();
        descField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        // == Add components to the panel
        
        // - id
        panel.add(new JLabel("Id:"));
        panel.add(idField);
        
        // - name
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        
        // - description
        panel.add(new JLabel("Description:"));
        panel.add(descField);

        // - price
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        
        // - quantity
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        
        // Add focus listeners
        idField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				checkFields();
			}
			public void focusLost(FocusEvent e) {
				checkFields();	
			}
        });
        nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				checkFields();
			}
			public void focusLost(FocusEvent e) {
				checkFields();	
			}
        });
        priceField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				checkFields();
			}
			public void focusLost(FocusEvent e) {
				checkFields();	
			}
        });
        quantityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				checkFields();
			}
			public void focusLost(FocusEvent e) {
				checkFields();	
			}
        });

        // Create and add the button
        addItemButton = new JButton("Add to database");
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemEventHandler();
            }
        });

        panel.add(addItemButton);

        return panel;
    }
    public void checkFields() {
        if (!idField.getText().trim().isEmpty() && 
        		!nameField.getText().trim().isEmpty() && 
        		!priceField.getText().trim().isEmpty() && 
        		!quantityField.getText().trim().isEmpty() &&
        		Integer.parseInt(quantityField.getText()) > 0
        	) {
        	setEnabled(true);
        }
    }
    
    /**
     * Add new item to the cart.
     */
    
	StockItem addMe;
    public void addItemEventHandler() {
    	try {
    		double price = Double.parseDouble(priceField.getText());
    		int quantity = Integer.parseInt(quantityField.getText());
    		long id = Integer.parseInt(idField.getText());
    		addMe = new StockItem(id,nameField.getText(), descField.getText(), price, quantity);
	    }
    	catch (NumberFormatException ex) {
    		log.error(ex.getMessage());
	    }
	    finally {
	        model.getWarehouseTableModel().addItem(addMe);
	    	dispose();
	    } 
    }
    /**
     * Sets whether or not this component is enabled.
     */
    
    @Override
    public void setEnabled(boolean enabled) {
        this.addItemButton.setEnabled(enabled);
    }
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
    /**
     * Reset dialog fields.
     */
    public void reset() {
        quantityField.setText("");
        nameField.setText("");
        priceField.setText("");
    }

}
