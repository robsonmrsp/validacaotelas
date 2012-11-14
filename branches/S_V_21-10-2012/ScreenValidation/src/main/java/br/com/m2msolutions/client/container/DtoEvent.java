package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoEvent extends BaseModelData {
	private static final long serialVersionUID = 6308342747740478807L;

	public static final String START_TIME = "startTime";
	public static final String VEHICLE_CODE = "vehicleCode";
	public static final String OPERATOR = "operator";
	public static final String PROTOCOL = "protocol";
	public static final String IMAGE_SRC = "imageSrc";
	public static final String DESCRIPTION = "description";

	// public String startTime;
	// public String vehicleCode;
	// public String operator;
	// public String protocol;
	// public String imageSrc;
	// public String description;
	public DtoEvent(String src, String desc) {
		setImageSrc(src);
		setDescription(desc);
	}

	public String getStartTime() {
		return get(START_TIME);
	}

	public void setStartTime(String startTime) {
		set(START_TIME, startTime);
	}

	public String getVehicleCode() {
		return get(VEHICLE_CODE);
	}

	public void setVehicleCode(String vehicleCode) {
		set(VEHICLE_CODE, vehicleCode);
	}

	public String getOperator() {
		return get(OPERATOR);
	}

	public void setOperator(String operator) {
		get(OPERATOR, operator);
	}

	public String getProtocol() {
		return get(PROTOCOL);
	}

	public void setProtocol(String protocol) {
		set(PROTOCOL, protocol);
	}

	public String getImageSrc() {
		return get(IMAGE_SRC);
	}

	public void setImageSrc(String imageSrc) {
		set(IMAGE_SRC, imageSrc);
	}

	public String getDescription() {
		return get(DESCRIPTION);
	}

	public void setDescription(String description) {
		set(DESCRIPTION, description);
	}
}
