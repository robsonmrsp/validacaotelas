package br.com.m2msolutions.client.sinotic.container;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOSimpleVehicle extends BaseModelData {
	private static final long serialVersionUID = 1607765721222470299L;
	public static final String ID = "id";
	public static final String CODE = "id";

	public DTOSimpleVehicle() {
	}

	public DTOSimpleVehicle(Long id, String code) {
		this.setId(id);
		this.setCode(code);
	}

	public String getCode() {
		return get(CODE);
	}

	public void setCode(String code) {
		set(CODE, code);
	}

	public Long getId() {
		return get(ID);
	}

	public void setId(Long id) {
		set(ID, id);
	}
}
