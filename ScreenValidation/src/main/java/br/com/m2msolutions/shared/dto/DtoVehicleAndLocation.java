package br.com.m2msolutions.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoVehicleAndLocation extends BaseModelData {

	private static final long serialVersionUID = 1935973171397196593L;

	public static final String VEHICLE = "vehicle";
	public static final String ADDRESS = "address";
	public static final String BUSSERVICE_NAME = "busServiceName";
	
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";

	public static final String NEXT_STOP = "nextStop";
	public static final String COMPANY_NAME = "companyName";

	public DtoVehicleAndLocation(String vehicleCode, String address, String busserviceName, String nextStop, String companyName, Double latitude, Double longitude){
		setVehicle(vehicleCode);
		setAddress(address);
		setBusServiceName(busserviceName);
		setNextStop(nextStop);
		setCompanyName(companyName);
		setLatitude(latitude);
		setLongitude(longitude);
	}
	public DtoVehicleAndLocation() {}

	public String getVehicle() {
		return get(VEHICLE);
	}

	public void setVehicle(String vehicle) {
		set(VEHICLE, vehicle);
	}

	public String getAddress() {
		return get(ADDRESS);
	}

	public void setAddress(String address) {
		set(ADDRESS, address);
	}

	public String getBusServiceName() {
		return get(BUSSERVICE_NAME);
	}

	public void setBusServiceName(String busServiceName) {
		set(BUSSERVICE_NAME, busServiceName);
	}

	public Double getLatitude() {
		return get(LATITUDE);
	}

	public void setLatitude(Double latitude) {
		set(LATITUDE, latitude);
	}

	public Double getLongitude() {
		return get(LONGITUDE);
	}

	public void setLongitude(Double longitude) {
		set(LONGITUDE, longitude);
	}

	public String getNextStop() {
		return get(NEXT_STOP);
	}

	public void setNextStop(String nextStop) {
		set(NEXT_STOP, nextStop);
	}

	public String getCompanyName() {
		return get(COMPANY_NAME);
	}

	public void setCompanyName(String companyName) {
		set(COMPANY_NAME, companyName);
	}
}
