package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoVehicleAndLocation extends BaseModelData {

	private static final long serialVersionUID = 1935973171397196593L;

	public static final String VEHICLE = "vehicle";
	public static final String ADDRESS = "address";
	public static final String BUSSERVICE_NAME = "busServiceName";
	public static final String NEXT_STOP = "nextStop";

	// TODO Verificar se é necessário usar como double para poder localozá-lo no
	// mapa
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";

	public static final String COMPANY_NAME = "companyName";

	// String adress;
	// String busServiceName;
	// String latitude;
	// String longitude;
	// String nextStop;
	//
	public DtoVehicleAndLocation() {
		// TODO Auto-generated constructor stub
	}

	public String getVehicle() {
		return get(VEHICLE);
	}

	public void setVehicle(String vehicle) {
		set(VEHICLE, vehicle);
	}

	public String getAddress() {
		return get(ADDRESS);
	}

	public void setAddress(String adress) {
		set(ADDRESS, adress);
	}

	public String getBusServiceName() {
		return get(BUSSERVICE_NAME);
	}

	public void setBusServiceName(String busServiceName) {
		set(BUSSERVICE_NAME, busServiceName);
	}

	public String getLatitude() {
		return get(LATITUDE);
	}

	public void setLatitude(String latitude) {
		set(LATITUDE, latitude);
	}

	public String getLongitude() {
		return get(LONGITUDE);
	}

	public void setLongitude(String longitude) {
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
