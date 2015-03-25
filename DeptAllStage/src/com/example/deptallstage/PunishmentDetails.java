package com.example.deptallstage;

import java.util.ArrayList;

import org.json.JSONArray;




import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PunishmentDetails extends Fragment
{

	public String urlString = "";
	public String uname ,pword;
	
	public View v;
	
	
	void Getunpw(String un,String pw)
	{
		
		uname = new String(un);
		pword = new String(pw);
	
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.punishmentinfo, container,false);
		Log.d("oncreateview/punishmentdetails","reached");
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d("onActivityCreated/punishmentdetials","reached");
		urlString = "http://10.0.2.2:5000/Server/PunishmentReturn?RollNo="+uname+"&Pswrd="+pword+"";
	//	urlString = "http://14.139.82.130:5000/Server/PunishmentReturn?RollNo="+uname+"&Pswrd="+pword+"";
		MyTask m = new MyTask();
		m.execute(urlString);
		Log.d("after m.execute()/punishmentdetails","reached");
	}
	
	public class MyTask extends AsyncTask<String, String, String>
	{
		String response;
		String infos,infosm;
		
		@Override
		protected void onPreExecute() 
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			Log.d("onPreExecute/punishmentdetails","reached");
		}
		@Override
		protected String doInBackground(String... arg0) {
			
			Log.d("doinbackground before connection class statement/punishmentdetails","reached");
				 response=ConnectionClass.send(urlString);
				 Log.d("doinbackground/punishmentdetails","reached");
				 return response;
		}
		@Override
		protected void onPostExecute(String result) {
		
			try{
				Log.d("onPostExecute/punishmentdetails","reached");
				 JSONArray ary=new JSONArray(result);
					for(int i = 0; i < ary.length(); i++) 
					{
						Log.d("onPostExecute...insideforloop/punishmentdetails","reached");
						if(ary.getJSONObject(i).has("punishment"))
						{
							Log.d("onPostExecute....ifloop/punishmentdetails","reached");
							infos=ary.getJSONObject(i).getString("punishment");
							Log.d("infos.....onPostExecute....ifloop/punishmentdetails",infos);
						}
					}		 
		
					TextView tv1 = (TextView)getView().findViewById(R.id.textView1);
					tv1.setText(infos);
					
			}catch(Exception e)
			{
				System.out.print(e);
			}
		
		}
		
		
	}
	
	
	
}
