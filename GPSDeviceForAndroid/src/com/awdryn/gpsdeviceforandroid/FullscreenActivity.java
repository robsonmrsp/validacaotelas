package com.awdryn.gpsdeviceforandroid;

import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.awdryn.gpsdeviceforandroid.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity implements LocationListener{

	private LocationManager locationManager;

	private TextView txtLat;
	private TextView txtLon;
	private TextView txtPrecisao;
	private TextView txtVelocidade;
	private TextView txtAltitude;
	private DecimalFormat decimalFormat = new DecimalFormat(".##");
	
	double latPoint = 0d;
	double lngPoint = 0d;
	float accuracy = 0f;
	float speed = 0f;
	double altitude = 0d;
	
	String url = "http://awdryn.dlinkddns.com:8989/storageposition/storage/position";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fullscreen);

		txtLat = (TextView)findViewById(R.id.txtLatitude);
		txtLon = (TextView)findViewById(R.id.txtLongitude);
		txtPrecisao = (TextView)findViewById(R.id.txtPrecisao);
		txtVelocidade = (TextView)findViewById(R.id.txtVelocidade);
		txtAltitude = (TextView)findViewById(R.id.txtAltitude);
		
		findViewById(R.id.btIniciar).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				iniciarObserverGPS();
			}
		});

		findViewById(R.id.btFinalizar).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finalizarObserverGPS();
			}
		});
		
		/*findViewById(R.id.btEnviar).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				enviarDadosServidor();
			}
		});*/
		
		findViewById(R.id.btEnviarDados).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				enviarDadosServidor();
			}
		});

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		
		//delayedHide(100);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		iniciarObserverGPS();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		finalizarObserverGPS();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		finalizarObserverGPS();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		finalizarObserverGPS();
	}


	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 */
	/*View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};*/

	/*Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};*/

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 */
	/*private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}*/

	public void iniciarObserverGPS(){

		try{

			locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: "+e.getCause());
		}

	}

	public void finalizarObserverGPS(){

		try{
			
			locationManager.removeUpdates(this);
			
			txtLat.setText("Latitude:  ");
			txtLon.setText("Longigutude: ");
			txtPrecisao.setText("Precisao: ");
			txtVelocidade.setText("Velocidade: ");
			txtAltitude.setText("Altitude: ");

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: "+e.getCause());
		}

	}
	
	private void enviarDadosServidor(){
		
		// Create and populate a simple object to be used in the request
		PositionMessage message = new PositionMessage();
		message.setDeviceId("AWDRYN_MOBILE_001");
		message.setLatitude(latPoint);
		message.setLongitude(lngPoint);
		message.setSpeed(speed);
		message.setAltitude(altitude);
		message.setAccuracy(accuracy);
		message.setCreationDate(new Date().getTime());
		
		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		// Add the Jackson and String message converters
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
		String response = restTemplate.postForObject(url, message, String.class);
		

		/*// Set the Content-Type header
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("application","json"));
		HttpEntity<PositionMessage> requestEntity = new HttpEntity<PositionMessage>(message, requestHeaders);

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		// Add the Jackson and String message converters
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		String result = responseEntity.getBody();*/
		
	}
	
	public void onLocationChanged(Location location) {

		latPoint = location.getLatitude();
		lngPoint = location.getLongitude();
		accuracy = location.getAccuracy();
		speed = (location.getSpeed() == 0)? 0f: location.getSpeed() * 3.6f;
		altitude = location.getAltitude();

		txtLat.setText("Latitude:  "+latPoint);
		txtLon.setText("Longigutude: "+lngPoint);
		txtPrecisao.setText("Precisao: "+decimalFormat.format(accuracy)+" m");
		txtVelocidade.setText("Velocidade: "+decimalFormat.format(speed)+" km/h");
		txtAltitude.setText("Altitude: "+decimalFormat.format(altitude)+" m");
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {}
	public void onProviderEnabled(String provider) {}
	public void onProviderDisabled(String provider) {}

}
