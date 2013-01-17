package com.awdryn.gpsdeviceforandroid.service;

import java.util.Date;
import java.util.Timer;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import com.awdryn.gpsdeviceforandroid.PositionMessage;



public class GPSStorageService extends IntentService implements LocationListener{

	public GPSStorageService() {
		super("GPSStorageService");
	}

	private String URL = "http://awdryn.dlinkddns.com:8989/storageposition/storage/position";
	private long INTERVAL = 5000;
	private boolean updatedPositions;
	private boolean ativa;
	private Timer timer = new Timer();
	private LocationManager locationManager;
	private int totalSentPositions = 0;

	private String deviceId = "AWDRYN_MOBILE_001";
	double latPoint = 0d;
	double lngPoint = 0d;
	float accuracy = 0f;
	float speed = 0f;
	double altitude = 0d;
	long gpsTime = 0l;
	
	private final IBinder mBinder = new GPSStorageBinder();
	
	private RestTemplate restTemplate;
	
	private Date createDate = new Date();
	private Date lastSendPosition = new Date();
	

	@Override
	public void onCreate() {
		super.onCreate();

		ativa = true;
		createDate = new Date();
		
		try{

			locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 5, this);
			
			restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		
		while(ativa){
			try {
				enviarDadosServidor();
				wait(INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	

	@Override
	public void onDestroy() {
		super.onDestroy();

		stopService();
	}

	public void stopService() {
		
		try{

			ativa = false;

			clearGPSValues();

			locationManager.removeUpdates(this);
			
			stopSelf();

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void enviarDadosServidor() throws Exception{

		if(updatedPositions && latPoint != 0 && lngPoint != 0){

			PositionMessage message = new PositionMessage();
			message.setDeviceId(this.deviceId);
			message.setLatitude(latPoint);
			message.setLongitude(lngPoint);
			message.setSpeed(speed);
			message.setAltitude(altitude);
			message.setAccuracy(accuracy);
			message.setCreationDate(gpsTime);

			// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
			String response = restTemplate.postForObject(URL, message, String.class);

			totalSentPositions++;
			updatedPositions = false;
			lastSendPosition = new Date();

		}

	}	

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
	public void onProviderDisabled(String arg0) {}

	@Override
	public void onProviderEnabled(String arg0) {}

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
	
	
	private void clearGPSValues() {
		latPoint = 0d;
		lngPoint = 0d;
		accuracy = 0f;
		speed = 0f;
		altitude = 0d;
		gpsTime = 0l;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	public class GPSStorageBinder extends Binder {
		public GPSStorageService getService() {
            return GPSStorageService.this;
        }
    }
	
	public boolean isUpdatedPositions() {
		return updatedPositions;
	}

	public void setUpdatedPositions(boolean gpsConnected) {
		this.updatedPositions = gpsConnected;
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

	public int getTotalSentPositions() {
		return totalSentPositions;
	}

	public void setTotalSentPositions(int totalSentPositions) {
		this.totalSentPositions = totalSentPositions;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastSendPosition() {
		return lastSendPosition;
	}

	public void setLastSendPosition(Date lastSendPosition) {
		this.lastSendPosition = lastSendPosition;
	}

}
