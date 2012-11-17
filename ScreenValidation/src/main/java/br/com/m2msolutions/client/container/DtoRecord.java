package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoRecord extends BaseModelData {
	
	private static final long serialVersionUID = -7604561910947438145L;
	
	public static final String MESSAGE_DATE = "messageDate";
	public static final String MESSAGE = "message";
	public static final String TYPE = "type";
	public static final String STATUS = "status";
	public static final String IMAGE_SRC = "imageSrc";

	//
	// String messageDate;
	// String message;
	// String type;
	// String status;
	// String imageSrc;

	public String getMessageDate() {
		return get(MESSAGE_DATE);
	}

	public void setMessageDate(String messageDate) {
		set(MESSAGE_DATE, messageDate);
	}

	public String getMessage() {
		return get(MESSAGE);
	}

	public void setMessage(String message) {
		set(MESSAGE, message);
	}

	public String getType() {
		return get(TYPE);
	}

	public void setType(String type) {
		set(TYPE, type);
	}

	public String getStatus() {
		return get(STATUS);
	}

	public void setStatus(String status) {
		set(STATUS, status);
	}

	public String getImageSrc() {
		return get(IMAGE_SRC);
	}

	public void setImageSrc(String imageSrc) {
		set(IMAGE_SRC, imageSrc);
	}
}
