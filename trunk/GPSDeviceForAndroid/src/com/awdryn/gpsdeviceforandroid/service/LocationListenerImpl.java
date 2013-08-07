package com.awdryn.gpsdeviceforandroid.service;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class LocationListenerImpl implements LocationListener{
	
	private double latPoint = 0d;
	private double lngPoint = 0d;
	private float accuracy = 0f;
	private float speed = 0f;
	private double altitude = 0d;
	private long gpsTime = 0l;
	private boolean updatedPositions;
	
	@Override
	public void onLocationChanged(Location location) {

		latPoint = location.getLatitude();
		lngPoint = location.getLongitude();
		accuracy = location.getAccuracy();
		speed = (location.getSpeed() == 0)? 0f: location.getSpeed() * 3.6f;
		altitude = location.getAltitude();
		gpsTime = location.getTime();

		updatedPositions = true;

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		/*switch (status) {
		case LocationProvider.OUT_OF_SERVICE:
			gpsConnected = false;
			clearGPSValues();
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			gpsConnected = false;
			clearGPSValues();
			break;
		case LocationProvider.AVAILABLE:
			gpsConnected = true;
			break;
		default:
		}*/
	}
	
	public void clearGPSValues() {
		latPoint = 0d;
		lngPoint = 0d;
		accuracy = 0f;
		speed = 0f;
		altitude = 0d;
		gpsTime = 0l;
	}

	public double getLatPoint() {
		return latPoint;
	}

	public void setLatPoint(double latPoint) {
		this.latPoint = latPoint;
	}

	public double getLngPoint() {
		return lngPoint;
	}

	public void setLngPoint(double lngPoint) {
		this.lngPoint = lngPoint;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public long getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(long gpsTime) {
		this.gpsTime = gpsTime;
	}

	public boolean isUpdatedPositions() {
		return updatedPositions;
	}

	public void setUpdatedPositions(boolean updatedPositions) {
		this.updatedPositions = updatedPositions;
	}
	

}
