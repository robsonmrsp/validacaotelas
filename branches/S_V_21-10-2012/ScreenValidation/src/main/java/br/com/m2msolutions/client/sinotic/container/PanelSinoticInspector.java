package br.com.m2msolutions.client.sinotic.container;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;

public class PanelSinoticInspector extends LayoutContainer {
	private BaseFieldSet fieldGeral;
	private BaseFieldSet fieldInfoLinha;
	private Html htmlGeral;
	private Html htmlInfolinha;
	private BaseFieldSet fieldCameras;
	private Html htmlCameras;

	public PanelSinoticInspector() {
		initComponents();
	}

	private void initComponents() {
		setId("panelSinoticInspector");
		setLayout(new RowLayout(Orientation.VERTICAL));
		add(getFieldGeral(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins()));
		add(getFieldInfoLinha(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins()));
		add(getFieldCameras(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins()));
	}

	private BaseFieldSet getFieldGeral() {
		if (fieldGeral == null) {
			fieldGeral = new BaseFieldSet();
			fieldGeral.setSize("-1", "164");
			fieldGeral.setLegend("Geral");
			fieldGeral.setContent(getHtmlGeral());
		}
		return fieldGeral;
	}

	private BaseFieldSet getFieldInfoLinha() {
		if (fieldInfoLinha == null) {
			fieldInfoLinha = new BaseFieldSet();
			fieldInfoLinha.setSize("-1", "114");
			fieldInfoLinha.setLegend("Info linha");
			fieldInfoLinha.setContent(getHtmlInfolinha());
		}
		return fieldInfoLinha;
	}

	private Html getHtmlGeral() {
		if (htmlGeral == null) {
			htmlGeral = new Html(createHtmlGeral());
		}
		return htmlGeral;
	}

	private Html getHtmlInfolinha() {
		if (htmlInfolinha == null) {
			htmlInfolinha = new Html(createHtmlInfoLinha());
		}
		return htmlInfolinha;
	}

	private String createHtmlInfoLinha() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("	<div id='info-linha' class='text-info'>");
		buffer.append("	Extensão:<br>");
		buffer.append("	N. de Paradas:<br>");
		buffer.append("	Início:<br>");
		buffer.append("	fim:<br>");
		buffer.append("</div>");

		return buffer.toString();

	}

	private String createHtmlCamera() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("	<iframe width='275' height='216' src='http://www.youtube.com/embed/25hWn46YTro' frameborder='0' allowfullscreen></iframe>");
		return buffer.toString();
	}

	private String createHtmlGeral() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("	<div id='general' class='text-info'>");
		buffer.append("	Cod.:<br>");
		buffer.append("	Veloc.:<br>");
		buffer.append("	QT. Passag.:<br>");
		buffer.append("	Última métrica:<br>");
		buffer.append("	Prox. Ponto:<br>");
		buffer.append("	%Percorrida:<br>");
		buffer.append("	Onde:<br>");
		buffer.append("</div>");
		return buffer.toString();
	}

	private BaseFieldSet getFieldCameras() {
		if (fieldCameras == null) {
			fieldCameras = new BaseFieldSet();
			fieldCameras.setLegend("Cameras");
			fieldCameras.setContent(getHtmlCameras());
		}
		return fieldCameras;
	}

	private Html getHtmlCameras() {
		if (htmlCameras == null) {
			htmlCameras = new Html(createHtmlCamera());
		}
		return htmlCameras;
	}

}
