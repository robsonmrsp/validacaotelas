package br.com.mr.storageposition;

import java.io.Serializable;
import java.sql.Timestamp;

public class JsonPosition implements Serializable {

	private String devideId;
	private Timestamp creationDate;
	private Double latitude;
	private Double longitude;

	public String getDevideId() {
		return devideId;
	}

	public void setDevideId(String devideId) {
		this.devideId = devideId;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "JsonPosition [devideId=" + devideId + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
