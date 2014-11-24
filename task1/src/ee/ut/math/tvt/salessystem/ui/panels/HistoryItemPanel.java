package ee.ut.math.tvt.salessystem.ui.panels;

import ee.ut.math.tvt.salessystem.domain.data.SaleItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
/**
 * Add item pane modal UI.
 */

public class HistoryItemPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(StockTableModel.class);
	

    // Warehouse model
    private SalesSystemModel model;
	
    public HistoryItemPanel(SalesSystemModel model, SaleItem item) {
    	super("History item");
        this.model = model;
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setSize(500,500);
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        model.getHistoryItemTableModel().addItems(item.getItems());
        add(drawDialogPane());
    }
    
 // purchase dialog
    private JComponent drawDialogPane() {

        // Create the panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Items"));

        JTable table = new JTable(model.getHistoryItemTableModel());
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, getBacketScrollPaneConstraints());
        
        return panel;
    }

    private GridBagConstraints getBacketScrollPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.CENTER;
        gc.gridheight = GridBagConstraints.REMAINDER;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.BOTH;

        return gc;
    }
}
