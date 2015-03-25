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
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class UserDetails extends Fragment
{

	String urlString = "";
	public String uname ,pword;
	
	public View v;
	
	
	void Getunpw(String un,String pw)
	{
		Log.d("uname",un);
		Log.d("password",pw);
		
		uname = new String(un);
		pword = new String(pw);
	
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.userinfo, container,false);		
		Log.d("oncreateview of userdetails","checking which is executing first");
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Toast.makeText(getActivity(), "now in onactivity of userdetails", Toast.LENGTH_LONG).show();
		Log.d("uname in userdetails/onactivitycreated",uname);
		Log.d("password in userdetails/onactivitycreated",pword);
		//urlString = "http://14.139.82.130:5000/Server/PNReturn?RollNo="+uname+"&Pswrd="+pword+"";
		
		
		urlString = "http://10.0.2.2:5000/Server/PNReturn?RollNo="+uname+"&Pswrd="+pword+"";
		MyTask m = new MyTask();
		m.execute(urlString);
		Log.d("after m.execute in userdetails","reached");
	}
	
	public class MyTask extends AsyncTask<String, String, String>
	{
		String response;
		String infos,infosm;
		
		@Override
		protected String doInBackground(String... arg0) {
				 response=ConnectionClass.send(urlString);
					Log.d("number in doInBackground/userdetails","checking of reachment");
					
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
		
			try{
				 JSONArray ary=new JSONArray(result);
					for(int i = 0; i < ary.length(); i++) 
					{
						if(ary.getJSONObject(i).has("number"))
						{
							infos=ary.getJSONObject(i).getString("number");
							Log.d("number in onpostexecute/userdetails",infos);
						}
						if(ary.getJSONObject(i).has("myname"))
						{
							infosm=ary.getJSONObject(i).getString("myname");
							Log.d("myname in onpostexecute/userdetails",infosm);

						}
					}		 
		
					TextView tv1 = (TextView)getView().findViewById(R.id.textView1);
					tv1.setText(infosm);
					
					TextView tv = (TextView)getView().findViewById(R.id.textView3);
					tv.setText(infos);
	
					TextView tv3 = (TextView)getView().findViewById(R.id.textView2);
					tv3.setText(uname);
				
					
			}catch(Exception e)
			{
				System.out.print(e);
			}
		
		}
		
		
	}
	
	
	
}
