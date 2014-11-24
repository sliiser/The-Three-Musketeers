package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public abstract class AbstractTab {

	protected SalesSystemModel model;
    protected final SalesDomainController controller;

	AbstractTab(SalesSystemModel model, SalesDomainController controller) {
		this.model = model;
		this.controller = controller;

	}

	public abstract void refresh();

	public abstract Component draw();

}
