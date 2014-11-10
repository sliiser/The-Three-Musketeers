package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {
    
    // Warehouse model
    private StockTableModel warehouseTableModel;
    private HistoryTableModel historyTableModel;
    private HistoryItemTableModel historyItemTableModel;
    
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;

    private final SalesDomainController domainController;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;
        
        warehouseTableModel = new StockTableModel();
        historyTableModel = new HistoryTableModel();
        historyItemTableModel = new HistoryItemTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel();

        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());
        // populate history model with data from the warehouse
        historyTableModel.populateWithData(domainController.loadHistoryState());

    }

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }
    
    public HistoryTableModel getHistoryTableModel() {
        return historyTableModel;
    }
    
    public HistoryItemTableModel getHistoryItemTableModel() {
        return historyItemTableModel;
    }
    
}
