package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoPredefinedMessage extends BaseModelData {

	private static final long serialVersionUID = 1L;
	public static final String TEXT = "text";

	public DtoPredefinedMessage() {
	}

	public DtoPredefinedMessage(String text) {
		setText(text);
	}

	public String getText() {
		return get(TEXT);
	}

	public void setText(String text) {
		set(TEXT, text);
	}
}
