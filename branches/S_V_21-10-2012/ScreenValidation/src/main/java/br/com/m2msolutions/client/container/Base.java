package br.com.m2msolutions.client.container;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

public class Base extends Window{
	private VerticalPanel verticalPanel;
	private VerticalPanel verticalPanel_1;
	private VerticalPanel verticalPanel_2;
	private HeadContainer headContainer;
	private LayoutContainer layoutContainer;
	private TextField txtfldNewTextfield;
	private NumberField nmbrfldNewNumberfield;
	public Base() {
		initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout());
		add(getVerticalPanel(), new BorderLayoutData(LayoutRegion.WEST, 265.0f));
		add(getVerticalPanel_1(), new BorderLayoutData(LayoutRegion.CENTER));
		add(getVerticalPanel_2(), new BorderLayoutData(LayoutRegion.EAST));
	}

	private VerticalPanel getVerticalPanel() {
		if (verticalPanel == null) {
			verticalPanel = new VerticalPanel();
			verticalPanel.add(getHeadContainer());
		}
		return verticalPanel;
	}
	private VerticalPanel getVerticalPanel_1() {
		if (verticalPanel_1 == null) {
			verticalPanel_1 = new VerticalPanel();
		}
		return verticalPanel_1;
	}
	private VerticalPanel getVerticalPanel_2() {
		if (verticalPanel_2 == null) {
			verticalPanel_2 = new VerticalPanel();
		}
		return verticalPanel_2;
	}
	private HeadContainer getHeadContainer() {
		if (headContainer == null) {
			headContainer = new HeadContainer();
			headContainer.addToolBox(Images.INSTANCE.printer16(), null);
			
			headContainer.setSize("264px", "131px");
			headContainer.add(getLayoutContainer());
		}
		return headContainer;
	}
	private LayoutContainer getLayoutContainer() {
		if (layoutContainer == null) {
			layoutContainer = new LayoutContainer();
			layoutContainer.setBorders(true);
			layoutContainer.setLayout(new FormLayout());
			layoutContainer.add(getTxtfldNewTextfield(), new FormData("100%"));
			layoutContainer.add(getNmbrfldNewNumberfield(), new FormData("100%"));
		}
		return layoutContainer;
	}
	private TextField getTxtfldNewTextfield() {
		if (txtfldNewTextfield == null) {
			txtfldNewTextfield = new TextField();
			txtfldNewTextfield.setFieldLabel("New TextField");
		}
		return txtfldNewTextfield;
	}
	private NumberField getNmbrfldNewNumberfield() {
		if (nmbrfldNewNumberfield == null) {
			nmbrfldNewNumberfield = new NumberField();
			nmbrfldNewNumberfield.setFieldLabel("New NumberField");
		}
		return nmbrfldNewNumberfield;
	}
}
