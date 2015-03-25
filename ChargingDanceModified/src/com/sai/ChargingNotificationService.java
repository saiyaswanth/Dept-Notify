package com.sai;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.BatteryManager;
import android.os.IBinder;

public class ChargingNotificationService extends Service
{
	
	
	
	 int var = 0;
	
	private int level;
	
	Thread t;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) 
	{
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		t = new Thread(new Runnable() {
			
			@Override
			public void run() 
			{
				while(true)
				{
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				// TODO Auto-generated method stub
				registerReceiver(new BroadcastReceiver() {
					
				
			

					@Override
					public void onReceive(Context arg0, Intent arg1)
					{
						arg0.unregisterReceiver(this);
						
						int currentlevel = arg1.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
						int scale = arg1.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
					
						if(currentlevel >= 0 && scale >=0 )
						{
							level = (currentlevel*100)/scale;
							
							if(level== 100)
							{
						
			
				

					if(var == 0)
					{
					NotificationManager n = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
					Notification noti = new Notification();
					
					noti.icon = R.drawable.ic_launcher;
					noti.tickerText = "Battery fully Charged";
					noti.when = System.currentTimeMillis();
							
					Intent i = new Intent(ChargingNotificationService.this,ChargingDanceActivity.class);
					PendingIntent p = PendingIntent.getActivity(ChargingNotificationService.this.getApplicationContext(), 0, i, 0);
							
					noti.setLatestEventInfo(ChargingNotificationService.this, "Battert Full", "Battery poyindhi ...inka thiyaka pothe", p);
					n.notify(0 , noti);
						var = 1;
					}
					
			
				
				
							}
							else
							{
								var = 0;
								
							}
						}
					}
				
				}, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
				}
			}
		});
		t.start();
		
	}
	
	@Override
	public void onDestroy() 
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		t.stop();
	}
}

