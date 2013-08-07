package com.awdryn.gpsdeviceforandroid.service;

import java.util.Date;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.awdryn.gpsdeviceforandroid.PositionMessage;

public class PositionCollectorTask implements Runnable{

	private String deviceId = "AWDRYN_MOBILE_001";
	private String URL = "http://sints.cloudvirgo.com:9080/sintsrestserver/storage/position";

	private PositionCollectorService positionCollectorService;

	private int totalSentPositions = 0;
	private Date lastSendPosition = new Date();

	public PositionCollectorTask(PositionCollectorService positionCollectorService){
		this.positionCollectorService = positionCollectorService;
	}

	@Override
	public void run() {
		try{
			enviarPositionServidor();
		}catch(Exception e){
			Log.d("PositionCollectorTask", " Erro ao chamar enviarPositionServidor.",e);
		}
	}

	private void enviarPositionServidor() throws RestClientException{
		
		Log.d("PositionCollectorTask", " enviarPositionServidor chamdo..");
		
		if(!estaConectado()){
			Log.d("PositionCollectorTask", "Sem conexao... ");
			return;
		}
		
		if (getLocationListenerImpl() == null || getRestTemplate() == null){
			Log.d("PositionCollectorTask", " LocationListenerImpl e RestTemplate estao nulos.");
			return;
		}

		if(getLocationListenerImpl().isUpdatedPositions() && getLocationListenerImpl().getLatPoint() != 0 
				&& getLocationListenerImpl().getLngPoint() != 0){

			PositionMessage message = new PositionMessage();
			message.setDeviceId(this.deviceId);
			message.setLatitude(getLocationListenerImpl().getLatPoint());
			message.setLongitude(getLocationListenerImpl().getLngPoint());
			message.setSpeed(getLocationListenerImpl().getSpeed());
			message.setCreationDate(getLocationListenerImpl().getGpsTime());

			Log.d("PositionCollectorTask", "Tentando enviar uma Position... ");
			
			String response = getRestTemplate().postForObject(URL, message, String.class);

			totalSentPositions++;
			getLocationListenerImpl().setUpdatedPositions(false);
			lastSendPosition = new Date();

			Log.d("PositionCollectorTask", " Position enviada com sucesso... ");
			Log.d("PositionCollectorTask", " Response = "+response);
		}else{
			Log.d("PositionCollectorTask", "Os dados não foram enviados pq o atributo updatedPositions é "+getLocationListenerImpl().isUpdatedPositions());
		}

	}
	
	private boolean estaConectado() {
		ConnectivityManager manager = (ConnectivityManager) positionCollectorService.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		return info.isConnected();
	}

	private RestTemplate getRestTemplate() {
		return positionCollectorService.getRestTemplate();
	}

	private LocationListenerImpl getLocationListenerImpl() {
		return positionCollectorService.getLocationListenerImpl();
	}


}
