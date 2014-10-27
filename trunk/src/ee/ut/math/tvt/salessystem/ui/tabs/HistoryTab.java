package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.AddItemPanel;
import ee.ut.math.tvt.salessystem.ui.panels.HistoryItemPanel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
	private static final Logger log = Logger.getLogger(HistoryTab.class);
    
    private Component basketPane;

    private SalesSystemModel model;
    
    public HistoryTab(SalesSystemModel model) {
    	this.model = model;
    } 

    public Component draw() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        
        basketPane = drawBasketPane();
        panel.add(basketPane, getBasketPaneConstraints());
        
        return panel;
    }

    // shopping cart pane
    	private Component drawBasketPane() {
    	    JPanel panel = new JPanel();

    	    final JTable table = new JTable(model.getHistoryTableModel());

    	    JTableHeader header = table.getTableHeader();
    	    header.setReorderingAllowed(false);
    	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
    	        public void valueChanged(ListSelectionEvent event) {
    	        	if(table.getSelectedRow() >= 0 ) {
    	        		HistoryItemPanel hPanel = new HistoryItemPanel(model, model.getHistoryTableModel().getItem(table.getSelectedRow()));
    	        		hPanel.setVisible(true);
    	        		table.clearSelection();
    	        	}
    	        }
    	    });
    	    JScrollPane scrollPane = new JScrollPane(table);
    	    
    	    GridBagConstraints gc = new GridBagConstraints();
    	    GridBagLayout gb = new GridBagLayout();
    	    gc.fill = GridBagConstraints.BOTH;
    	    gc.weightx = 1.0;
    	    gc.weighty = 1.0;

    	    panel.setLayout(gb);
    	    panel.add(scrollPane, gc);

    	    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    	    return panel;
    	  }

    // Formatting constraints for the basketPane
    private GridBagConstraints getBasketPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.CENTER;
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