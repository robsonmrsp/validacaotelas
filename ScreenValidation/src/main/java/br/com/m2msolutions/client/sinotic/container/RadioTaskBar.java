package br.com.m2msolutions.client.sinotic.container;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;

public class RadioTaskBar extends LayoutContainer {
	private CheckBoxGroup group;
	private CheckBox checkPoint;
	private CheckBox checkControlPoint;
	private CheckBox chequePointName;
	private CheckBox checkPlain;
	private CheckBox checkMap;
	public RadioTaskBar() {
		initComponents();
	}
	private void initComponents() {
		setLayout(new RowLayout(Orientation.HORIZONTAL));
		add(getGroup(), new RowData(655.0, 15.0, new Margins()));
	}
	private CheckBoxGroup getGroup() {
		if (group == null) {
			group = new CheckBoxGroup();
			group.setFieldLabel("New CheckBoxGroup");
			group.add(getCheckPoint());
			group.add(getCheckControlPoint());
			group.add(getChequePointName());
			group.add(getCheckPlain());
			group.add(getCheckMap());
		}
		return group;
	}
	private CheckBox getCheckPoint() {
		if (checkPoint == null) {
			checkPoint = new CheckBox();
			checkPoint.setBoxLabel("Ponto");
			checkPoint.setHideLabel(true);
		}
		return checkPoint;
	}
	private CheckBox getCheckControlPoint() {
		if (checkControlPoint == null) {
			checkControlPoint = new CheckBox();
			checkControlPoint.setBoxLabel("Ponto de Controle");
			checkControlPoint.setHideLabel(true);
		}
		return checkControlPoint;
	}
	private CheckBox getChequePointName() {
		if (chequePointName == null) {
			chequePointName = new CheckBox();
			chequePointName.setBoxLabel("Nome do Ponto");
			chequePointName.setHideLabel(true);
		}
		return chequePointName;
	}
	private CheckBox getCheckPlain() {
		if (checkPlain == null) {
			checkPlain = new CheckBox();
			checkPlain.setBoxLabel("Planejamento");
			checkPlain.setHideLabel(true);
		}
		return checkPlain;
	}
	private CheckBox getCheckMap() {
		if (checkMap == null) {
			checkMap = new CheckBox();
			checkMap.setBoxLabel("Mapa");
			checkMap.setHideLabel(true);
		}
		return checkMap;
	}
}
