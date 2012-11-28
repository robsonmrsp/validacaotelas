package br.com.m2msolutions.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class DtoExtraInfoEvent implements Serializable {

	private static final long serialVersionUID = -2836175666536543180L;

	private DtoAboutEvent about;
	private DtoVehicleAndLocation vehicleLocation;
	private DtoContact contact;
	private DtoRecord dtoRecord;
	private ArrayList<DtoRecord> records;

	public DtoExtraInfoEvent() {

	}

	public DtoAboutEvent getAbout() {
		return about;
	}

	public void setAbout(DtoAboutEvent about) {
		this.about = about;
	}

	public DtoVehicleAndLocation getVehicleLocation() {
		return vehicleLocation;
	}

	public void setVehicleLocation(DtoVehicleAndLocation vehicleLocation) {
		this.vehicleLocation = vehicleLocation;
	}

	public DtoContact getContact() {
		return contact;
	}

	public void setContact(DtoContact contact) {
		this.contact = contact;
	}

	public ArrayList<DtoRecord> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<DtoRecord> records) {
		this.records = records;
	}
}
