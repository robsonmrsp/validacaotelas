package com.awdryn.gpsdeviceforandroid.service;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class PositionCollectorService extends Service{

	private long INTERVAL = 20;
	private int DELAY_INITIAL = 30;
	private int REST_TEMPLATE_TIME_OUT = 15000;

	private RestTemplate restTemplate;
	private LocationManager locationManager;
	private LocationListenerImpl locationListenerImpl = new LocationListenerImpl();

	private boolean started;
	private Date createDate = new Date();

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		createDate = new Date();
		started = false;
		
		try{

			configLocationManager();
			configRestTemplate();
			start();

		}catch(Exception e){
			e.printStackTrace();
		}

		Log.d("PositionCollectorService", " Metodo OnCreate chamado.");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		startSchedule();

		Log.d("PositionCollectorService", " Metodo onStartCommand chamado.");				

		return START_STICKY;
	}

	private void start(){
		if(!started){
			Intent intentStorageService = new Intent("PositionCollectorService");
			startService(intentStorageService);
		}
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private void startSchedule() {

		started = true;

		ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);

		TimeUnit unit = TimeUnit.SECONDS;
		pool.scheduleAtFixedRate(new PositionCollectorTask(this), DELAY_INITIAL, INTERVAL, unit);
	}

	private void configLocationManager() throws Exception{
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, locationListenerImpl);
		locationManager.addGpsStatusListener(new GpsStatus.Listener(){
			@Override
			public void onGpsStatusChanged(int status) {
				//				Log.d("GPSStorageService", "Status do GPS: "+status);	
			}
		});
	}

	private void configRestTemplate() throws Exception{

		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		ClientHttpRequestFactory clientHttpRequestFactory = restTemplate.getRequestFactory();

		if (clientHttpRequestFactory instanceof SimpleClientHttpRequestFactory) {
			Log.d("PositionCollectorService", "HttpUrlConnection foi usado...");
			((SimpleClientHttpRequestFactory) clientHttpRequestFactory).setConnectTimeout(REST_TEMPLATE_TIME_OUT);
			((SimpleClientHttpRequestFactory) clientHttpRequestFactory).setReadTimeout(REST_TEMPLATE_TIME_OUT);
		} else if (clientHttpRequestFactory instanceof HttpComponentsClientHttpRequestFactory) {
			Log.d("PositionCollectorService", "HttpClient foi usado...");
			((HttpComponentsClientHttpRequestFactory) clientHttpRequestFactory).setReadTimeout(REST_TEMPLATE_TIME_OUT);
			((HttpComponentsClientHttpRequestFactory) clientHttpRequestFactory).setConnectTimeout(REST_TEMPLATE_TIME_OUT);
		}

	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public LocationListenerImpl getLocationListenerImpl() {
		return locationListenerImpl;
	}

}
