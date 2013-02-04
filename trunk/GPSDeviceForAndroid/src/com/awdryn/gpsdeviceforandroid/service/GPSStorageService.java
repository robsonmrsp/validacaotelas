package com.awdryn.gpsdeviceforandroid.service;

import java.util.Date;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.awdryn.gpsdeviceforandroid.PositionMessage;



public class GPSStorageService extends IntentService implements LocationListener{

	public GPSStorageService() {
		super("GPSStorageService");
	}
	
	private String deviceId = "AWDRYN_MOBILE_001";
	private String URL = "http://awdryn.dlinkddns.com:8989/storageposition/ws/storage/position";
	private long INTERVAL = 20000;
	private long INTERVAL_FOR_REST_ERROR = 90000;
	private int REST_TEMPLATE_TIME_OUT = 10000;
	
	private boolean updatedPositions;
	private boolean ativa;
	private int totalSentPositions = 0;
	double latPoint = 0d;
	double lngPoint = 0d;
	float accuracy = 0f;
	float speed = 0f;
	double altitude = 0d;
	long gpsTime = 0l;

	private IBinder mBinder = new GPSStorageBinder();
	private RestTemplate restTemplate;
	private LocationManager locationManager;
	private Date createDate = new Date();
	private Date lastSendPosition = new Date();

	@Override
	public void onCreate() {
		super.onCreate();

		ativa = true;
		createDate = new Date();

		try{

			configLocationManager();

			configRestTemplate();

		}catch(Exception e){
			e.printStackTrace();
		}

		Log.d("GPSStorageService", " Metodo OnCreate chamado.");
	}

	private void configLocationManager() throws Exception{
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
		locationManager.addGpsStatusListener(new GpsStatus.Listener(){
			@Override
			public void onGpsStatusChanged(int status) {
				Log.d("GPSStorageService", "Status do GPS: "+status);	
			}
		});
	}

	private void configRestTemplate() throws Exception{

		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		ClientHttpRequestFactory clientHttpRequestFactory = restTemplate.getRequestFactory();

		if (clientHttpRequestFactory instanceof SimpleClientHttpRequestFactory) {
			Log.d("GPSStorageService", "HttpUrlConnection foi usado...");
			((SimpleClientHttpRequestFactory) clientHttpRequestFactory).setConnectTimeout(REST_TEMPLATE_TIME_OUT);
			((SimpleClientHttpRequestFactory) clientHttpRequestFactory).setReadTimeout(REST_TEMPLATE_TIME_OUT);
		} else if (clientHttpRequestFactory instanceof HttpComponentsClientHttpRequestFactory) {
			Log.d("GPSStorageService", "HttpClient foi usado...");
			((HttpComponentsClientHttpRequestFactory) clientHttpRequestFactory).setReadTimeout(REST_TEMPLATE_TIME_OUT);
			((HttpComponentsClientHttpRequestFactory) clientHttpRequestFactory).setConnectTimeout(REST_TEMPLATE_TIME_OUT);
		}

	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Log.d("GPSStorageService", " Metodo onHandleIntent chamado.");

		if (ativa) {
			while (ativa) {
				try {
					
					enviarPositionServidor();
					Log.d("GPSStorageService", " Envio de Position executado.");
					Thread.sleep (INTERVAL);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
					stopService();
				} catch (RestClientException e) {
					sleepThreadForRestError();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		Log.d("GPSStorageService", " Metodo onHandleIntent finalizado.");

	}

	private void sleepThreadForRestError(){
		
		try{
			Log.d("GPSStorageService", " Conexao com servidor REST indisponivel. Aguardando "+INTERVAL_FOR_REST_ERROR+" segundos para tentar novamente.");
			Thread.sleep (INTERVAL_FOR_REST_ERROR);
		}catch (InterruptedException e) {
			e.printStackTrace();
			stopService();
		}
		
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		
		stopService();
		
		Log.d("GPSStorageService", " Metodo onDestroy chamado.");
	}

	public void stopService() {

		try{

			Log.d("GPSStorageService", " Metodo stopService chamado.");

			ativa = false;

			clearGPSValues();

			locationManager.removeUpdates(this);

			stopSelf();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private void enviarPositionServidor() throws RestClientException{

		if(updatedPositions && latPoint != 0 && lngPoint != 0){

			PositionMessage message = new PositionMessage();
			message.setDeviceId(this.deviceId);
			message.setLatitude(latPoint);
			message.setLongitude(lngPoint);
			message.setSpeed(speed);
			message.setAltitude(altitude);
			message.setAccuracy(accuracy);
			message.setCreationDate(gpsTime);

			Log.d("GPSStorageService", "Tentando enviar uma Position... ");

			// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
			String response = restTemplate.postForObject(URL, message, String.class);

			totalSentPositions++;
			updatedPositions = false;
			lastSendPosition = new Date();

			Log.d("GPSStorageService", " Position enviada com sucesso... ");
			Log.d("GPSStorageService", " Response = "+response);
		}else{
			Log.d("GPSStorageService", "Os dados não foram enviados pq o atributo updatedPositions é "+updatedPositions);
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
