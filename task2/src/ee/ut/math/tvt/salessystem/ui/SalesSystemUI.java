package ee.ut.math.tvt.salessystem.ui;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.AbstractTab;
import ee.ut.math.tvt.salessystem.ui.tabs.ClientTab;
import ee.ut.math.tvt.salessystem.ui.tabs.HistoryTab;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;
import ee.ut.math.tvt.salessystem.ui.tabs.StockTab;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

/**
 * Graphical user interface of the sales system.
 */
public class SalesSystemUI extends JFrame implements ChangeListener {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SalesSystemUI.class);

	// Warehouse model
	private SalesSystemModel model;

	// Instances of tab classes
	private AbstractTab[] tabs;
	private static final String[] TITLES = new String[] { "Point-of-sale",
			"Warehouse", "History", "Clients" };
	private SalesDomainController domainController;

	/**
	 * Constructs sales system GUI.
	 * 
	 * @param domainController
	 *            Sales domain controller.
	 */
	public SalesSystemUI(SalesDomainController domainController) {
		this.domainController = domainController;
		this.model = new SalesSystemModel(domainController);
		domainController.setModel(model);

		// Create singleton instances of the tab classes
		tabs = new AbstractTab[4];
		tabs[0] = new PurchaseTab(domainController, model, this);
		tabs[1] = new StockTab(model, domainController);
		tabs[2] = new HistoryTab(model, domainController);
		tabs[3] = new ClientTab(model, domainController);

		setTitle("Sales system");

		// set L&F to the nice Windows style
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			log.warn(e1.getMessage());
		}

		drawWidgets();

		// size & location
		int width = 600;
		int height = 400;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SalesSystemUI.this.domainController.endSession();
				log.info("SalesSystem closed");
				System.exit(0);
			}
		});
	}

	private void drawWidgets() {
		JTabbedPane tabbedPane = new JTabbedPane();

		for (int i = 0; i < tabs.length; i++) {
			tabbedPane.add(TITLES[i], tabs[i].draw());
		}

		tabbedPane.addChangeListener(this);

		getContentPane().add(tabbedPane);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane pane = ((JTabbedPane) e.getSource());
		tabs[pane.getSelectedIndex()].refresh();
	}

}
