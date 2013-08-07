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
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.awdryn.gpsdeviceforandroid.PositionMessage;



public class GPSStorageService extends IntentService{
	
	private String deviceId = "AWDRYN_MOBILE_001";
	private String URL = "http://sints.cloudvirgo.com:9080/sintsrestserver/storage/position";
	private long INTERVAL = 20000;
	private long INTERVAL_FOR_REST_ERROR = 90000;
	private int REST_TEMPLATE_TIME_OUT = 10000;
	
	private boolean ativa;
	private int totalSentPositions = 0;
	private RestTemplate restTemplate;
	private LocationManager locationManager;
	private Date createDate = new Date();
	private Date lastSendPosition = new Date();
	private IBinder mBinder = new GPSStorageBinder();
	
	private LocationListenerImpl locationListenerImpl = new LocationListenerImpl();
	
	public GPSStorageService() {
		super("GPSStorageService");
	}

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
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, locationListenerImpl);
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

			locationListenerImpl.clearGPSValues();

			locationManager.removeUpdates(locationListenerImpl);

			stopSelf();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private void enviarPositionServidor() throws RestClientException{

		if(locationListenerImpl.isUpdatedPositions() && locationListenerImpl.getLatPoint() != 0 && locationListenerImpl.getLngPoint() != 0){

			PositionMessage message = new PositionMessage();
			message.setDeviceId(this.deviceId);
			message.setLatitude(locationListenerImpl.getLatPoint());
			message.setLongitude(locationListenerImpl.getLngPoint());
			message.setSpeed(locationListenerImpl.getSpeed());
			message.setCreationDate(locationListenerImpl.getGpsTime());

			Log.d("GPSStorageService", "Tentando enviar uma Position... ");

			// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
			String response = restTemplate.postForObject(URL, message, String.class);

			totalSentPositions++;
			locationListenerImpl.setUpdatedPositions(false);
			lastSendPosition = new Date();

			Log.d("GPSStorageService", " Position enviada com sucesso... ");
			Log.d("GPSStorageService", " Response = "+response);
		}else{
			Log.d("GPSStorageService", "Os dados não foram enviados pq o atributo updatedPositions é "+locationListenerImpl.isUpdatedPositions());
		}

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
