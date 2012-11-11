package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class ContentPanelImp extends ContentPanel {
	public ContentPanelImp() {
		setHideCollapseTool(true);
		setCollapsible(false);
		setBodyStyle("backgroundColor: #d6e2f6;");
	}

	public void addToolButton(ToolButton toolButton) {
		getHeader().addTool(toolButton);
		
	}
}
