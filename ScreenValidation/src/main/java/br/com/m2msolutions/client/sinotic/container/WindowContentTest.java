package br.com.m2msolutions.client.sinotic.container;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;

public class WindowContentTest extends LayoutContainer {
	
	private BaseFieldSet fieldGeral;
	private BaseFieldSet baseFieldSet_1;
	private Html htmlGeral;
	private Html htmlInfolinha;
	private BaseFieldSet fieldCameras;
	private Html htmlCameras;

	public WindowContentTest() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new RowLayout(Orientation.VERTICAL));
		add(getFieldGeral(), new RowData(Style.DEFAULT, 145.0, new Margins()));
		add(getBaseFieldSet_1(), new RowData(Style.DEFAULT, 120.0, new Margins()));
		add(getFieldCameras(), new RowData(Style.DEFAULT, 185.0, new Margins()));
	}

	private BaseFieldSet getFieldGeral() {
		if (fieldGeral == null) {
			fieldGeral = new BaseFieldSet();
			fieldGeral.setLegend("Geral");
			fieldGeral.setContent(getHtmlGeral());
		}
		return fieldGeral;
	}

	private BaseFieldSet getBaseFieldSet_1() {
		if (baseFieldSet_1 == null) {
			baseFieldSet_1 = new BaseFieldSet();
			baseFieldSet_1.setLegend("Info linha");
			baseFieldSet_1.setContent(getHtmlInfolinha());
		}
		return baseFieldSet_1;
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

		buffer.append("	<iframe width='560' height='315' src='http://www.youtube.com/embed/25hWn46YTro' frameborder='0' allowfullscreen></iframe>");
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
