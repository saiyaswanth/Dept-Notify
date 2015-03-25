
package com.example.deptallstage;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

@SuppressLint("CommitTransaction")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity implements OnItemClickListener
{

	//static int startindicator=0;
	DrawerLayout drawerlayout;
	ListView listview;
	String[] planets;
	ActionBarDrawerToggle drawerlistener;
	MyAdapter myadapter;
	TextView tv;
	static String urlString;
	public int fc = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		Log.d("mistaken place", "41");
		drawerlayout = (DrawerLayout)findViewById(R.id.drawerLayout);
		planets = getResources().getStringArray(R.array.planets);
		listview = (ListView)findViewById(R.id.drawerlist);
	//	listview.setAdapter(new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1,planets));
		myadapter = new MyAdapter(this);
		listview.setAdapter(myadapter);
		listview.setOnItemClickListener(this);
		
		
		
		
		Toast.makeText(getApplicationContext(), "oncreate mei hu", Toast.LENGTH_LONG).show();
		
		
		drawerlistener = new ActionBarDrawerToggle
				(this, drawerlayout, R.drawable.karateedit,
						R.string.openDrawerContentDescRes,
						R.string.closeDrawerContentDescRes){
			@Override
			public void onDrawerOpened(View drawerView) 
			{
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				Toast.makeText(MainActivity.this, "opened", Toast.LENGTH_LONG).show();
			}
			@Override
							
			public void onDrawerClosed(View drawerView) 
			{
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
				Toast.makeText(MainActivity.this, "closed", Toast.LENGTH_LONG).show();
			}
			
		};
		drawerlayout.setDrawerListener(drawerlistener);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		/*
	}
	else
	{
		startActivity(new Intent(MainActivity.this,Login.class));
	}
	
		*/
		
		
		String r = getIntent().getExtras().getString("un");
		Log.d("username/mainactivity",r);
		String p = getIntent().getExtras().getString("pw");
		Log.d("password/mainactivity",p);
		int l=1;
	urlString = "http://10.0.2.2:5000/Server/Logoutstatus?RollNo="+r+"&Pswrd="+p+"&logot="+l+"";
//  urlString = "http://14.139.82.130:5000/Server/Logoutstatus?RollNo="+r+"&Pswrd="+p+"&logot="+l+"";
		MyTask m = new MyTask();
		m.execute(urlString);
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	
		Toast.makeText(getApplicationContext(), "now ur in mainactivity/start", Toast.LENGTH_LONG).show();
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
				//startindicator = 1;
				Toast.makeText(getApplicationContext(),"if loop is getting executed/MainActivity", Toast.LENGTH_LONG).show();
				
			}
			else 
			{
				//startindicator = 0;
				Toast.makeText(getApplicationContext(),"else loop is getting executed/MainActivity", Toast.LENGTH_LONG).show();
				
			}
		}
		
	}

	public class MyAdapter extends BaseAdapter
	{
		
		Context context;
		String[] planets;
		MyAdapter(Context context)
		{
			Log.d("mistaken place", "41");
			this.context = context;
			planets = context.getResources().getStringArray(R.array.planets);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return planets.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return planets[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) 
		{
			// TODO Auto-generated method stub
			View row = null;
			if(arg1 == null)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
				row=inflater.inflate(R.layout.test,arg2,false);
			}
			else
			{
				row = arg1;
			}
			TextView tv = (TextView) row.findViewById(R.id.textView1);
			tv.setText(planets[arg0]);
			return row;
		}
		
	}
	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerlistener.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		drawerlistener.onConfigurationChanged(newConfig);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		
		if(item.getItemId() == R.id.logout)
		{
			Toast.makeText(getApplicationContext(), "u pressed logout dude", Toast.LENGTH_LONG).show();
		
			String r = getIntent().getExtras().getString("un");
			Log.d("username/mainactivity",r);
			String p = getIntent().getExtras().getString("pw");
			Log.d("password/mainactivity",p);
			int l=0;
			urlString = "http://10.0.2.2:5000/Server/Logoutstatus?RollNo="+r+"&Pswrd="+p+"&logot="+l+"";
		//	urlString = "http://10.0.2.2:5000/Server/Registration?Name="+n+"&RollNo="+r+"&PhNum="+ph+"&Pswrd="+pa+"";
			MyLogoutTask m = new MyLogoutTask();
			m.execute(urlString);
		}
		if(item.getItemId() == R.id.pdetails)
		{
		
			UserDetails ud = new UserDetails();
			String r = getIntent().getExtras().getString("un");
			String p = getIntent().getExtras().getString("pw");
			Log.d("username in mainactivity",r);
			Log.d("password in mainactivity",p);
			ud.Getunpw(r, p);
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.mainContent,ud,"asdffdsa");
			
			transaction.commit();
			 
		}
		
		if(drawerlistener.onOptionsItemSelected(item))
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class MyLogoutTask extends AsyncTask<String, Void, String>
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
				startActivity(new Intent(MainActivity.this,Login.class));
			}
			else 
			{
				Toast.makeText(getApplicationContext(),"else loop is getting executed/MyLogoutTask/MainActivity", Toast.LENGTH_LONG).show();
			}
		}
		
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}*/
	

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		int sass = 0;
		String valuetab = "";
		tv = (TextView)findViewById(R.id.textView1);
		tv.setText(planets[arg2]+"is getting displayed");
		Toast.makeText(this, planets[arg2]+"is getting displayed", Toast.LENGTH_LONG).show();
		
		NotificationFragment frag = new NotificationFragment();
		int sasd = 0;
		
		switch (arg2) {
		case 0:
			frag.TnameMethod("data");
			valuetab = "data";
			break;
		case 1:
			frag.TnameMethod("data2");
			valuetab = "data2";
			break;
		case 2:
			frag.TnameMethod("data3");
			valuetab = "data3";
			break;
		case 3:
			
			frag.TnameMethod("data4");
			valuetab = "data4";
			break;
		case 4:
			PunishmentDetails ud = new PunishmentDetails();
			String r = getIntent().getExtras().getString("un");
			String p = getIntent().getExtras().getString("pw");
			ud.Getunpw(r, p);
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.mainContent,ud,"asdffdsa");
			transaction.commit();
			 sass = 1;
		default:
			Toast.makeText(getApplicationContext(), "u pressed some thing like " + arg2, Toast.LENGTH_LONG).show();
			break;
		}
			;
			
			if(sass==0)
			{
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			if(fc==0)
			{
				fc = 1;
			transaction.add(R.id.mainContent,frag,"asdf");
			}
			else
			{
				
				transaction.replace(R.id.mainContent, frag, "asdf");
			}
			
			transaction.commit();
			}
			
			    
		
		
		//selectitem(arg2);
	}
	
	public void selectitem(int arg2) 
	{
		// TODO Auto-generated method stub
		listview.setItemChecked(arg2,true);
		settitle(planets[arg2]);
	}
	public void settitle(String string) 
	{
		// TODO Auto-generated method stub
		getSupportActionBar().setTitle(string);
		Intent intent = new Intent(MainActivity.this,test.class);
		startActivity(intent);
	}


}
