package com.sai;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;


public class ChargingSongService extends Service
{
	private MediaPlayer p;
	Thread t;


int var = 0;

	private int level;

	
	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("charg","20.....songservice");
		p = MediaPlayer.create(this	,R.raw.a);
		p.setLooping(false);
	}

	@Override
	public IBinder onBind(Intent arg0) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) 
	{
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
						
						p.start();
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
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("charg","57.....songservice");
		p.stop(); 
		t.stop();
	}
	
	

}
