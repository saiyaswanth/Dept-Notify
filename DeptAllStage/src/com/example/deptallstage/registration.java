package com.example.deptallstage;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;

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

public class registration extends Activity
{
	EditText name,rollno,phnum,password;
	String n,r,ph,pa;
	Button submit;
	TextView tv;
	static String urlString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_main);
		name =(EditText)findViewById(R.id.editText1);
		rollno =(EditText)findViewById(R.id.editText2);
		phnum =(EditText)findViewById(R.id.editText3);
		password =(EditText)findViewById(R.id.editText4);
		submit = (Button)findViewById(R.id.button1);
		tv = (TextView)findViewById(R.id.textView3);
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				n = name.getText().toString();
				r = rollno.getText().toString();
				ph = phnum.getText().toString();
				pa = password.getText().toString();
				
				 //urlString = "http://14.139.82.130 :5000/Server/Registration?Name="+n+"&RollNo="+r+"&PhNum="+ph+"&Pswrd="+pa+"";
				urlString = "http://10.0.2.2:5000/Server/Registration?Name="+n+"&RollNo="+r+"&PhNum="+ph+"&Pswrd="+pa+"";
				
				MyTask m = new MyTask();
				m.execute(urlString);
			}
		});
		tv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(registration.this,Login.class));
			}
		});
		
	}
	
	private class MyTask extends AsyncTask<String, Void, String>
	{
		String response;
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
				 response=ConnectionClass.send(urlString);
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "gt response as" + result, Toast.LENGTH_LONG).show();
			if(result.equals("1"))
			{
				startActivity(new Intent(registration.this,Login.class));
			}
			else 
			{
				Toast.makeText(getApplicationContext(),"else loop is getting executed", Toast.LENGTH_LONG).show();
				
			}
		
		}
		
	}

}
