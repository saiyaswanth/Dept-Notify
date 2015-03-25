package com.sai;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChargingDanceActivity extends Activity 
{
	String MessageInterest = "false";
	String songInterest = "false";
	private long res;
	CheckBox interestedrnot;
	CheckBox two;
	CheckBox song;
	EditText et1;
	private String phonenumber = null;
	private String message = null;
	private EditText et2;
	TextView tv3;
	TextView tv4;
	Button b ,b2;
	SQLiteDatabase sqlitedb;
	private SharedPreferences sp;

	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
       
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        interestedrnot = (CheckBox)findViewById(R.id.checkBox1);
        two = (CheckBox)findViewById(R.id.checkBox2);
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        song = (CheckBox)findViewById(R.id.checkBox3);
        b = (Button)findViewById(R.id.button1);
      
     

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
      
        interestedrnot.setChecked(sp.getBoolean("interestedrnotsp", false));
        two.setChecked(sp.getBoolean("twosp", false));
        song.setChecked(sp.getBoolean("songsp", false));
       
        	interestedrnot.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) 
				{
					if(interestedrnot.isChecked())
					{
		startService(new Intent(ChargingDanceActivity.this,ChargingNotificationService.class));
					}
					else
					{
		stopService(new Intent(ChargingDanceActivity.this,ChargingNotificationService.class));
					}
				}
			});
       	
       
        
        
       
       
        two.setOnCheckedChangeListener(new OnCheckedChangeListener() 
        {
								
		        					
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) 
		{
		if(et1.getVisibility()==View.GONE)
		{
		
		        							MessageInterest = "true";
		        							tv3.setVisibility(View.VISIBLE);
		        							tv4.setVisibility(View.VISIBLE);
		        							et1.setVisibility(View.VISIBLE);
		        							et2.setVisibility(View.VISIBLE);
		        							b.setVisibility(View.VISIBLE);
		        		
		   
		    
		 b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
			    CharginDancedbhelper db = new CharginDancedbhelper(ChargingDanceActivity.this);
				if(et1.getText().toString()==""||et2.getText().toString()=="")
				{
					Toast.makeText(ChargingDanceActivity.this, "Details Can't be Empty",Toast.LENGTH_SHORT).show();
				}
				else
				{
					phonenumber = et1.getText().toString();
					message = et2.getText().toString();
			    res = db.insertintodb(MessageInterest,phonenumber,message);				
			    if(res>0)
			    {
			    	Toast.makeText(ChargingDanceActivity.this, "We Will Msg for Sure :P",Toast.LENGTH_LONG).show();
			    	startService(new Intent(ChargingDanceActivity.this,ChargingObservanceService.class));
			    	et1.setText("");
			    	et2.setText("");
			    }
			}

			}
		});
		        							
		}
		else
		{
		        							MessageInterest = "false";
		        							tv3.setVisibility(View.GONE);
		        							tv4.setVisibility(View.GONE);
		        							et1.setVisibility(View.GONE);
		        							et2.setVisibility(View.GONE);
		        							b.setVisibility(View.GONE);
		    
		stopService(new Intent(ChargingDanceActivity.this,ChargingObservanceService.class));
		        							
		}
		        					}
       				});
        			
       
        	

      song.setOnCheckedChangeListener(new OnCheckedChangeListener() 
      {
				
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) 
				{
					if(song.isChecked())
					{
						startService(new Intent(ChargingDanceActivity.this, ChargingSongService.class)); 
					}
					else
					{
						stopService(new Intent(ChargingDanceActivity.this, ChargingSongService.class)); 
					}
					
				}
		});
      
        	
        
      
     
		
    }
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 two = (CheckBox)findViewById(R.id.checkBox2);
	        et1 = (EditText)findViewById(R.id.editText1);
	        et2 = (EditText)findViewById(R.id.editText2);
	        tv3 = (TextView)findViewById(R.id.textView3);
	        tv4 = (TextView)findViewById(R.id.textView4);
		if(two.isChecked())
		{

			MessageInterest = "true";
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			et1.setVisibility(View.VISIBLE);
			et2.setVisibility(View.VISIBLE);
			b.setVisibility(View.VISIBLE);



b.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View arg0) 
{
CharginDancedbhelper db = new CharginDancedbhelper(ChargingDanceActivity.this);
if(et1.getText().toString()==""||et2.getText().toString()=="")
{
Toast.makeText(ChargingDanceActivity.this, "Details Can't be Empty",Toast.LENGTH_SHORT).show();
}
else
{
phonenumber = et1.getText().toString();
message = et2.getText().toString();
res = db.insertintodb(MessageInterest,phonenumber,message);				
if(res>0)
{
Toast.makeText(ChargingDanceActivity.this, "We Will Msg for Sure :P",Toast.LENGTH_LONG).show();
startService(new Intent(ChargingDanceActivity.this,ChargingObservanceService.class));
et1.setText("");
et2.setText("");
}
}

}
});
			
		}
		
		 

	}
	
	@Override
	protected void onStop() 
	{
	// TODO Auto-generated method stub
		
        
        
		super.onStop();
	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preferencedit = sp.edit();
        
        preferencedit.putBoolean("interestedrnotsp", interestedrnot.isChecked());
        preferencedit.putBoolean("twosp", two.isChecked());
        preferencedit.putBoolean("songsp", song.isChecked());
        
        preferencedit.commit();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preferencedit = sp.edit();
        
        preferencedit.putBoolean("interestedrnotsp", interestedrnot.isChecked());
        preferencedit.putBoolean("twosp", two.isChecked());
        preferencedit.putBoolean("songsp", song.isChecked());
        
        preferencedit.commit();
	}
       




















}