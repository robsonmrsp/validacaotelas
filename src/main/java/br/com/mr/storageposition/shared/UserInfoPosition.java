package br.com.mr.storageposition.shared;

import java.io.Serializable;

public class UserInfoPosition implements Serializable {

	private static final long serialVersionUID = -6995455412302836637L;
	private String imageSrc;
	private String userName;
	private Double latitude;
	private Double longitude;

	public UserInfoPosition() {
		
	}

//	mudar para devide id ao invez de nome do usuário
	public UserInfoPosition(String userName, Double latitude, Double longitude, String imageSrc) {
		super();
		this.userName = userName;
		this.imageSrc = imageSrc;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
