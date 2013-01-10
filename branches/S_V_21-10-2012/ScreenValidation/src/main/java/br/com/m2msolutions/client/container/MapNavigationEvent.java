package br.com.m2msolutions.client.container;

import br.com.m2msolutions.shared.dto.DtoVehicleAndLocation;

import com.extjs.gxt.ui.client.event.BaseEvent;

public class MapNavigationEvent extends BaseEvent {

	private DtoVehicleAndLocation vehicleAndLocation;

	public MapNavigationEvent(Object source, DtoVehicleAndLocation vehicleAndLocation) {
		super(source);
		this.setVehicleAndLocation(vehicleAndLocation);
	}

	public DtoVehicleAndLocation getVehicleAndLocation() {
		return vehicleAndLocation;
	}

	public void setVehicleAndLocation(DtoVehicleAndLocation vehicleAndLocation) {
		this.vehicleAndLocation = vehicleAndLocation;
	}

}
