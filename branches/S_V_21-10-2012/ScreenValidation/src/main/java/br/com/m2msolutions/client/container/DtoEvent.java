package br.com.m2msolutions.client.container;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoEvent extends BaseModelData {

	private static final long serialVersionUID = 6308342747740478807L;

	public static final String START_DATE_TIME_AS_STRING = "startTimeAsString";
	public static final String START_DATE_TIME = "startTime";
	public static final String VEHICLE_CODE = "vehicleCode";
	public static final String OPERATOR = "operator";
	public static final String PROTOCOL = "protocol";
	public static final String IMAGE_SRC = "imageSrc";
	public static final String DESCRIPTION = "description";
	public static final String CATEGORY = "category";
	public static final String STATUS = "status";
	public static final String TIME_IN_EVENT = "timeInEvent";
	public static final String TIME_ATTENDANCE = "timeAttendance";


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getImageSrc() == null) ? 0 : getImageSrc().hashCode());
		result = prime * result + ((getStartDateTime() == null) ? 0 : getStartDateTime().hashCode());
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
		if (getImageSrc() == null) {
			if (other.getImageSrc() != null)
				return false;
		} else if (!getImageSrc().equals(other.getImageSrc()))
			return false;
		if (getStartDateTime() == null) {
			if (other.getStartDateTime() != null)
				return false;
		} else if (!getStartDateTime().equals(other.getStartDateTime()))
			return false;
		if (getVehicleCode() == null) {
			if (other.getVehicleCode() != null)
				return false;
		} else if (!getVehicleCode().equals(other.getVehicleCode()))
			return false;
		return true;
	}

	public DtoEvent(String src, String vehicle, Date startTime, String category, String operator, String protocol, String status, String timeInEvent, String timeAttendance) {
		this(src, vehicle, startTime, category);
		setOperator(operator);
		setProtocol(protocol);
		setStatus(status);
		setTimeInEvent(timeInEvent);
		setTimeAttendance(timeAttendance);
	}

	public DtoEvent(String src, String vehicle, Date startTime, String category) {
		this(src, vehicle, startTime);
		setCategory(category);
	}

	public DtoEvent(String src, String vehicle, Date startTime) {
		this(src, vehicle);
		setStartDateTime(startTime);
	}

	public DtoEvent(String src, String vehicle) {
		setImageSrc(src);
		setVehicleCode(vehicle);
	}

	public String getStartDateTimeAsText() {
		return get(START_DATE_TIME_AS_STRING);
	}

	public void setStartDateTimeAsText(String startTime) {
		set(START_DATE_TIME_AS_STRING, startTime);

	}

	public Date getStartDateTime() {
		return get(START_DATE_TIME);
	}

	public void setStartDateTime(Date startTime) {
		setStartDateTimeAsText(UtilData.format(startTime));
		System.out.println("DtoEvent.setStartDateTime()" + getStartDateTimeAsText());
		set(START_DATE_TIME, startTime);
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
		set(OPERATOR, operator);
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

	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((getStartDateTime() == null) ? 0 :
	// getStartDateTime().hashCode());
	// result = prime * result + ((getVehicleCode() == null) ? 0 :
	// getVehicleCode().hashCode());
	// return result;
	// }

	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// DtoEvent other = (DtoEvent) obj;
	// if (getStartDateTime() == null) {
	// if (other.getStartDateTime() != null)
	// return false;
	// } else if (!getStartDateTime().equals(other.getStartDateTime()))
	// return false;
	// if (getVehicleCode() == null) {
	// if (other.getVehicleCode() != null)
	// return false;
	// } else if (!getVehicleCode().equals(other.getVehicleCode()))
	// return false;
	// return true;
	// }

	public String getCategory() {
		return get(CATEGORY);
	}

	public void setCategory(String category) {
		set(CATEGORY, category);
	}

	public String getStatus() {
		return get(STATUS);
	}

	public void setStatus(String status) {
		set(STATUS, status);
	}

	public String getTimeAttendance() {
		return get(TIME_ATTENDANCE);
	}

	public String getTimeInEvent() {

		return get(TIME_IN_EVENT);
	}

	public void setTimeInEvent(String timeInEvent) {
		set(TIME_IN_EVENT, timeInEvent);
	}

	public void setTimeAttendance(String timeAttendance) {
		set(TIME_ATTENDANCE, timeAttendance);
	}

	public Boolean matchCi(String... regex) {

		String allFiels = getAllFiels().toLowerCase();
		boolean ret = true;

		for (int i = 0; i < regex.length; i++) {
			ret = ret && allFiels.contains(regex[i].toLowerCase());
		}

		return ret;
	}

	public Boolean match(String... regex) {
		String allFiels = getAllFiels();
		System.out.println("DtoEvent.match()" + allFiels);
		boolean ret = true;

		for (int i = 0; i < regex.length; i++) {
			ret = ret && allFiels.contains(regex[i]);
		}

		return ret;
	}

	private String getAllFiels() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getVehicleCode());
		buffer.append(" ");
		buffer.append(getDescription());
		buffer.append(" ");
		buffer.append(getCategory());
		buffer.append(" ");
		buffer.append(getOperator());
		buffer.append(" ");
		buffer.append(getProtocol());
		buffer.append(" ");
		buffer.append(getStartDateTime());
		buffer.append(" ");
		buffer.append(getStartDateTimeAsText());
		buffer.append(" ");
		return buffer.toString();
	}
}
