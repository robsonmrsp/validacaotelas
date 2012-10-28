package br.com.mr.dock.client;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

public abstract class DockWindow extends LayoutContainer{
	public abstract Component getHeader();
}
