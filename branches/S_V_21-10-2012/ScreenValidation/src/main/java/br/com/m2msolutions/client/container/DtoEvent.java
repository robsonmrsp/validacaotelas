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

//	 public String vehicleCode;
//	 public String startTime;
	// public String operator;
	// public String protocol;
	// public String imageSrc;
	// public String description;
	public DtoEvent(String src, String vehicle, String startTime) {
		setVehicleCode(vehicle);
		setImageSrc(src);
		setStartTime(startTime);
	}

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
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
		result = prime * result + ((getVehicleCode() == null) ? 0 : getVehicleCode().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtoEvent other = (DtoEvent) obj;
		if (getStartTime() == null) {
			if (other.getStartTime() != null)
				return false;
		} else if (!getStartTime().equals(other.getStartTime()))
			return false;
		if (getVehicleCode() == null) {
			if (other.getVehicleCode() != null)
				return false;
		} else if (!getVehicleCode().equals(other.getVehicleCode()))
			return false;
		return true;
	}

}
