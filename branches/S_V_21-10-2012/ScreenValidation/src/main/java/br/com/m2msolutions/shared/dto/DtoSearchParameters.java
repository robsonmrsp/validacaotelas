package br.com.m2msolutions.shared.dto;

import java.io.Serializable;

public class DtoSearchParameters implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dateFrom;
	private String dateTo;
	private String operator;
	private String protocol;
	private String vehicleCode;

	public DtoSearchParameters(String dateFrom, String dateTo, String operator, String protocol, String vehicleCode) {
		this();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.operator = operator;
		this.protocol = protocol;
		this.vehicleCode = vehicleCode;
	}

	public DtoSearchParameters() {
		// TODO Auto-generated constructor stub
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public String getOperator() {
		return operator;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

}
