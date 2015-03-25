package com.example.deptallstage;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity
{
	EditText rollno,password;
	static String r,p;
	Button submit,register;
	static String urlString1;
	static String urlString2;
	static int indi = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_lgin);
		Log.d("loginpage","26");
		rollno = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		submit = (Button)findViewById(R.id.button1);
		register = (Button)findViewById(R.id.button2);
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				Log.d("loginpage","37");
				
				// TODO Auto-generated method stub
				r = rollno.getText().toString();
				p = password.getText().toString();
				urlString1 = "http://10.0.2.2:5000/Server/Login?RollNo="+r+"&Pswrd="+p+"";
				//urlString1 = "http://14.139.82.130:5000/Server/Login?RollNo="+r+"&Pswrd="+p+"";
				MyTask m = new MyTask();
				m.execute(urlString1);
			}
		});
		
		
		Toast.makeText(getApplicationContext(), "create/login", Toast.LENGTH_LONG).show();
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				Log.d("loginpage","54");
				
				// TODO Auto-generated method stub
				startActivity(new Intent(Login.this,registration.class));
			/*	r = rollno.getText().toString();
				p = password.getText().toString();
				urlString = "http://10.0.2.2:5000/Server/Login?RollNo="+r+"&Pswrd="+p+"";
				MyTask m = new MyTask();
				m.execute(urlString);
			*/
			}
		});
		
	}
	private class MyTask extends AsyncTask<String, Void, String>
	{
		String response;
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.d("loginpage","121");
			
			response=ConnectionClass.send(urlString1);
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "gt response as" + result, Toast.LENGTH_LONG).show();
			if(result.equals("1"))
			{
				Log.d("loginpage","132");
				
				
				Intent i = new Intent(Login.this,MainActivity.class);
				i.putExtra("un", r);
				i.putExtra("pw", p);
				startActivity(i);
		
			}
			else
			{
				Log.d("loginpage","143");
				
				Toast.makeText(getApplicationContext(), "login details are incorrrect..try again", Toast.LENGTH_LONG).show();
			}
		}
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(getApplicationContext(), "start/login-------outside", Toast.LENGTH_LONG).show();
		
		Toast.makeText(getApplicationContext(), "start/login", Toast.LENGTH_LONG).show();
		urlString2 = "http://10.0.2.2:5000/Server/LogoutReturn?RollNo="+r+"&Pswrd="+p+"";
		MyStartTask m = new MyStartTask();
		m.execute(urlString2);
		
	}

	
	private class MyStartTask extends AsyncTask<String, Void, String>
	{
		String response;
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.d("loginpage","90");
			
				 response=ConnectionClass.send(urlString2);
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "gt response as" + result, Toast.LENGTH_LONG).show();
			if(result.equals("1"))
			{
				Log.d("loginpage","101");
				
				Toast.makeText(getApplicationContext(), "ifloop/mystartTask", Toast.LENGTH_LONG).show();
				

				Intent i = new Intent(Login.this,MainActivity.class);
				i.putExtra("un", r);
				i.putExtra("pw", p);
				startActivity(i);
		
		
			
			}
			else
			{
				Toast.makeText(getApplicationContext(), "elseloop/mystartTask", Toast.LENGTH_LONG).show();
			}
		}
		
	}

	
	

}
