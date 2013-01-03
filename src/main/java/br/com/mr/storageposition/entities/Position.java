package br.com.mr.storageposition.entities;

import java.io.Serializable;
import java.sql.Timestamp;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 2929623862319517953L;

	public Position() {
	}

	public Position(String deviceId, Timestamp creationDate, Double latitude, Double longitude) {
		super();
		this.deviceId = deviceId;
		this.setCreationDate(creationDate);
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSITION_SEQUENCY")
	private Long id;

	@Column
	private String deviceId;

	@Column
	private Timestamp creationDate;
	@Column
	private Double latitude;

	@Column
	private Double longitude;

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

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}
