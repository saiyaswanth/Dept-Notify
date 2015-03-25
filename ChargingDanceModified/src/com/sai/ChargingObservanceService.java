package com.sai;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.BatteryManager;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;


public class ChargingObservanceService extends Service
{
	
	int flag =0;
	
	private String number;
	private String msg;
	private int level;

	Thread t;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() 
	{
		
			super.onCreate();
			
	}
	
	@Override
	public void onStart(Intent intent, int startId) 
	{
		try{
	 t = new Thread(new Runnable() {
			
			@Override
			public void run() 
			{
				Log.d("charg","49..observance");

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
						Log.d("charg","64..observance");
						
						arg0.unregisterReceiver(this);
						
						int currentlevel = arg1.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
						int scale = arg1.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
					
						if(currentlevel >= 0 && scale >=0 )
						{
							level = (currentlevel*100)/scale;
							
							if(level== 100)
							{
	Toast.makeText(ChargingObservanceService.this, "Battery got Fully Charged", Toast.LENGTH_SHORT).show();
	Log.d("charg","78..observance");
				
				
				CharginDancedbhelper db = new CharginDancedbhelper(ChargingObservanceService.this);
				Cursor cursor = db.fetch();
				cursor.moveToLast();
				
					if(cursor.getString(0).equals("true"))
					{
						number = cursor.getString(1).toString();
						msg = cursor.getString(2).toString();

						if(flag == 0)
						{
							Log.d("charg","92..observance");

						//	SmsManager sms = SmsManager.getDefault();
						//	sms.sendTextMessage(number, null,msg,null, null);
							
							SmsManager sms = SmsManager.getDefault();
							sms.sendTextMessage(number, null, msg, null, null);
							
							flag = 1;
							db.close();
						}	
							
						
					}
							}
							else
							{
								flag =0;
							}
						}
					}
				
				}, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
				}
			}
		});
		}
		catch(Exception e)
		{
			Log.e("charg","e point lo gabbu ayindhi");
			Log.e("charg", e.toString());
		}
		t.start();
		
	}
	
	@Override
	public void onDestroy() 
	{
		super.onDestroy();
		t.stop();
	}

}
