package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoOperator extends BaseModelData {
	private static final long serialVersionUID = 2153603773644004525L;

	public static final String ID = "id";
	public static final String NAME = "name";

	public Long getId() {
		return get(ID);
	}

	public void setId(long id) {
		set(ID, id);
	}

	public String getName() {
		return get(NAME);
	}

	public void setName(String name) {
		set(NAME, name);
	}
}
