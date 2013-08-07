package com.awdryn.gpsdeviceforandroid.receiver;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.awdryn.gpsdeviceforandroid.service.GPSStorageService;

public class GPSServiceStartBoot extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		/*if(!isGPSStorageServiceRunning(context)){
			Intent intentStorageService = new Intent("GPSStorageService");
			context.startService(intentStorageService);
		}*/
		
		if(!isGPSStorageServiceRunning(context)){ 
			Intent intentStorageService = new Intent("PositionCollectorService");
			context.startService(intentStorageService);
		}
		
	}
	
	private boolean isGPSStorageServiceRunning(Context context) {
		
	    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (GPSStorageService.class.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}

}
