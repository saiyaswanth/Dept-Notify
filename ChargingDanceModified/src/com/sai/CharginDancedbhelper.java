package com.sai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class CharginDancedbhelper extends SQLiteOpenHelper
{
	private SQLiteDatabase sqlitedb;

	public CharginDancedbhelper(Context context)
	{
		
		//the ChargingDance is the Database name
		super(context,"Charge", null, 1);
		Log.d("charg", "17.....helper");
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) 
	{
		arg0.execSQL("CREATE TABLE Charge( notifiedinterest TEXT,Phonenumber TEXT,Message TEXT);");
		Log.d("charg", "26.....helper");
			
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) 
	{
		//lite..
		onCreate(arg0);
	}
	
	public long insertintodb(String notiinterest,String phnum,String message)
	{
		Log.d("charg", "37.....helper");
		
		sqlitedb = this.getReadableDatabase();
	
		ContentValues c = new ContentValues();
		c.put("notifiedinterest", notiinterest);
		c.put("Phonenumber", phnum);
		c.put("Message", message);
		long a = sqlitedb.insert("Charge", null, c);
		return a;
	}
	public Cursor fetch()
	{
		Log.d("charg", "50.....helper");
		
		sqlitedb = this.getReadableDatabase();
		Cursor cursor = sqlitedb.rawQuery("select * from Charge", null);
		if(cursor != null)
		{
			return cursor;
		}
		else
		{
			return null;
		}
		
	}
	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		sqlitedb.close();
		super.close();
	}
	
	
	
	
}
