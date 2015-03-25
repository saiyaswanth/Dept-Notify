package com.example.deptallstage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class ConnectionClass 
{
	static String url = "http://10.0.2.2:5000/Server/";
	static String send(String url) 
	{
		Log.d("send method ","reached");

		String responce = null;
		try {
			Log.d("ConnectionClass","reached");

			//urlString is converted into URL object .
			URL urlString = new URL(url);
			Log.e("connection class", "18");
			//HttpUrlConnection is used for opening the connection
			HttpURLConnection connection = (HttpURLConnection) urlString
			.openConnection();
			Log.e("connection class", "22");
			Log.d("ConnectionUtilityclass","21");
			InputStream stream = connection.getInputStream();
			Log.e("connection class", "25");
			Log.d("ConnectionUtilityclass","23");
			int i = 0;
			StringBuffer buffer = new StringBuffer("");
			while ((i = stream.read()) != -1) 
			{
				Log.e("connection class", "31");
				Log.d("ConnectionUtilityclass","23");
				buffer.append((char) i);
			}
			
			responce = buffer.toString();
		}
		catch(Exception e)
		{
			Log.e("connection class", "40");
			System.out.println(e);
		}
		return responce;
	}
}
