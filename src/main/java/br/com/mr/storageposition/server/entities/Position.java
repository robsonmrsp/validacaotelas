package br.com.mr.storageposition.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "POSITION")
@SequenceGenerator(name = "POSITION_SEQUENCY", sequenceName = "POSITION_SEQUENCY")
public class Position implements Serializable {

	private static final long serialVersionUID = 2929623862319517953L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSITION_SEQUENCY")
	private Long id;

	@Column
	private String deviceId;

	@Column
	private Long creationDate;
	
	@Column
	private Double latitude;

	@Column
	private Double longitude;
	
	@Column
	private Float accuracy;
	
	@Column
	private Float speed;
	
	@Column
	private Double altitude;
	
	
	public Position() {}

	public Position(String deviceId, Long creationDate, Double latitude, Double longitude, Float accuracy, Float speed, Double altitude) {
		super();
		this.deviceId = deviceId;
		this.setCreationDate(creationDate);
		this.latitude = latitude;
		this.longitude = longitude;
		this.speed = speed;
		this.altitude = altitude;
		this.accuracy = accuracy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Float accuracy) {
		this.accuracy = accuracy;
	}

	public Float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

}
