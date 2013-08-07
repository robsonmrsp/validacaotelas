package com.awdryn.gpsdeviceforandroid;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.awdryn.gpsdeviceforandroid.service.GPSStorageService;
import com.awdryn.gpsdeviceforandroid.service.GPSStorageService.GPSStorageBinder;
import com.awdryn.gpsdeviceforandroid.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {

	private boolean mBound;

//	private GPSStorageService gpsStoregeService;
	
	private TextView txtServicoIniciado;
	private TextView txtUltimaPosicao;
	private TextView txtVelocidade;
	private TextView txtPosicoes;

	private DecimalFormat decimalFormat = new DecimalFormat("###.##");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm:ss"); 

	/*private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			GPSStorageBinder binder = (GPSStorageBinder) service;
			gpsStoregeService = binder.getService();
			mBound = true;
			
			updateView();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}
	};*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fullscreen);
		
		mBound = false;
		
		txtVelocidade = (TextView)findViewById(R.id.txtVelocidade);
		txtPosicoes = (TextView)findViewById(R.id.txtPosicoes);
		txtServicoIniciado = (TextView)findViewById(R.id.txtServicoIniciado);
		txtUltimaPosicao = (TextView)findViewById(R.id.txtUltimaPosicao);

		findViewById(R.id.btIniciar).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startOrConnectGPSService();
			}
		});

		findViewById(R.id.btFinalizar).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				stopGPSService();
			}
		});
		
		/*findViewById(R.id.btAtualizarDados).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mBound){
					updateView();
				}else{
					startOrConnectGPSService();
				}
			}
		});*/

	}

	private void startOrConnectGPSService(){
		
		Intent intent = new Intent("PositionCollectorService");
		startService(intent);
		
		/*Intent intent = new Intent("GPSStorageService");
		
		if(isGPSStorageServiceRunning()){
			bindService(intent, mConnection,Context.BIND_AUTO_CREATE);
		}else{
			startService(intent);
			bindService(intent, mConnection,Context.BIND_AUTO_CREATE);
		}*/
	}

	private void stopGPSService(){
		
		/*if(isGPSStorageServiceRunning()){
			Intent intent = new Intent("GPSStorageService");
			stopService(intent);
		}*/
		
		if(isGPSStorageServiceRunning()){
			Intent intent = new Intent("PositionCollectorService");
			stopService(intent);
		}
		
	}

//	private void updateView() {
//		txtServicoIniciado.setText("Serviço Iniciado:"+String.valueOf(dateFormat.format(gpsStoregeService.getCreateDate())));
//		txtUltimaPosicao.setText("Última Posição:"+String.valueOf(dateFormat.format(gpsStoregeService.getLastSendPosition())));
//		txtPosicoes.setText("Posições Enviadas:"+String.valueOf(gpsStoregeService.getTotalSentPositions()));
//	}
	
	private boolean isGPSStorageServiceRunning() {
	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (GPSStorageService.class.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		/*if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}*/
		
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
