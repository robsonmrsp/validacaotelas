package br.com.m2msolutions.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoRecord extends BaseModelData {

	private static final long serialVersionUID = -7604561910947438145L;

	public static final String ID = "id";
	public static final String MESSAGE_DATE = "messageDate";
	public static final String MESSAGE = "message";
	public static final String TYPE = "type";
	public static final String STATUS = "status";
	public static final String IMAGE_SRC = "imageSrc";
	public static final String DESCRIPTION = "description";

	public static final String CLASS_CSS = "userMsg";

	public DtoRecord() {
		// TODO Auto-generated constructor stub
	}

	public DtoRecord(Long id, String imageSrc, String type, String messageDate, String message) {
		this(imageSrc, type, messageDate, message);
		setId(id);
	}

	public DtoRecord(String imageSrc, String type, String messageDate, String message) {
		super();
		setImageSrc(imageSrc);
		setType(type);
		setMessageDate(messageDate);
		setMessage(message);
	}

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

	public String getDescription() {
		return get(DESCRIPTION);
	}

	public void setDescription(String description) {
		set(DESCRIPTION, description);
	}

	public Long getId() {
		return get(ID);
	}

	public void setId(Long id) {
		set(ID, id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		DtoRecord other = (DtoRecord) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
