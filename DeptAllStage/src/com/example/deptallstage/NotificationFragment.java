package com.example.deptallstage;
import java.util.*;

import org.json.JSONArray;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NotificationFragment extends ListFragment implements OnItemClickListener
{
	String urlString;
	//ListView lv;
	public String[] pokemon;
	FragmentManager manager;
	public String tabname = "";
	
	void TnameMethod(String tname)
	{
		tabname = new String(tname);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Toast.makeText(getActivity(), "onListitemclick toast", Toast.LENGTH_LONG).show();
		
	
	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v =  inflater.inflate(R.layout.notifrag, container, false);
		Log.d("not","oncreate");
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.pokemon, android.R.layout.simple_list_item_1);
	
		Log.d("not","onactivity");
		
	//	MyAdapter myadapter = new MyAdapter(getActivity());
	//	setListAdapter(myadapter);
	//	getListView().setOnItemClickListener(this);
	//	setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, new ArrayList<String>()));
		
		
		//urlString="http://14.139.82.130:5000/Server/FragmentListInfo?tname="+tabname+"";
		urlString="http://10.0.2.2:5000/Server/FragmentListInfo?tname="+tabname+"";
		MyTask m = new MyTask();
		m.execute(urlString);
		
		
		
	}
	
	public class MyTask extends AsyncTask<String, String, String>
	{
		String response;
		
		public ArrayList<String> info;
		ArrayAdapter<String> adapter;
		/*	@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
//			adapter = (ArrayAdapter<String>)getListAdapter();
		}
		*/
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
				 response=ConnectionClass.send(urlString);
					Log.d("not","doInBackground");
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
	//		Toast.makeText(getActivity(), "gt response as" + result, Toast.LENGTH_LONG).show();
		
			try{
				 JSONArray ary=new JSONArray(result);
					info = new ArrayList<String>();
					Log.d("not","onpostexecute");
					for(int i = 0; i < ary.length(); i++) 
					{
						if(ary.getJSONObject(i).has("Name"))
						{
							String infos=ary.getJSONObject(i).getString("Name");
							info.add(infos);
						}
					
					}		 
					Log.d("not","onpostExectute");
					pokemon = info.toArray(new String[info.size()]);
//					NotificationFragment n = new NotificationFragment();
					NotificationFragment.MyTask m = new NotificationFragment.MyTask();
					
					for(int i=0;i<pokemon.length;i++)
					{
						Log.d("pokedata",pokemon[i]);
					
						m.putPoke(pokemon[i]);
					}
					SimpleAdapter s = new SimpleAdapter(getActivity(),pokemon,tabname);
					setListAdapter(s);
					
			}catch(Exception e)
			{
				System.out.print(e);
			}
		
		}
		int f=0;
		private void putPoke(String string) {
			// TODO Auto-generated method stub
			
			Log.d("data recieved is ",string);
			
			pokemon[f++]=string;
			
			Log.d("not","onputpoke");
			
		}
		
		
		
		
		
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "onitemclick toast", Toast.LENGTH_LONG).show();
	}
	
	
	 
/*
	public class MyAdapter extends BaseAdapter
	{	
		
		Context context;
		public String[] pokemon;
		public ArrayList<String> info;
		String urlString;
		int f=0;
		int[] imags = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
		
		public MyAdapter(int i) {
			// TODO Auto-generated constructor stub
			System.out.print(i);
		}
		MyAdapter(Context context)
		{
			this.context = context;
			//pokemon = getActivity().getResources().getStringArray(R.array.pokemon);
			
			/*urlString="http://10.0.2.2:9999/Server/FragmentListInfo";
			MyTask m = new MyTask();
			m.execute(urlString);
		*/
			/*try
			{
			this.context = context;
			Log.d("error-main fragment", "69");
			String url="http://10.0.2.2:8989/FragmentServerInfo/FragmentListInfo";
			URL u= new URL(url);
			HttpURLConnection conn = null;
			try{
			
				conn =(HttpURLConnection) u.openConnection();
			//conn.setRequestMethod("GET");
            //conn.connect();
			
			}catch(Exception e)
			{
				System.out.print(e);
			}
			if(conn!=null)
			{
				Toast.makeText(getActivity(), "server connection established", Toast.LENGTH_LONG).show();
			}
			else
			{

				Toast.makeText(getActivity(), "server connection not established", Toast.LENGTH_LONG).show();
			
			}
			InputStream is =conn.getInputStream();
			System.out.print("asdf");
			int i=0;
			StringBuffer sb=new StringBuffer("");
			Log.d("error-main fragment", "77");
			
			while((i=is.read())!=-1)
			{
				sb.append((char)i);
				
			}
			
			String response = sb.toString();
			JSONArray ary=new JSONArray(response);
			info = new ArrayList<String>();
			for(i = 0; i < ary.length(); i++) 
			{
				if(ary.getJSONObject(i).has("info"))
				{
					String infos=ary.getJSONObject(i).getString("info");
					info.add(infos);
				}
			
			}
			//String[] arr = list.toArray(new String[list.size()]);
			pokemon = info.toArray(new String[info.size()]);
			}
			catch(Exception e)
			{
				System.out.print(e);
			}
			*/
			
/*		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
		//	Toast.makeText(getActivity(), "in getCount() ", Toast.LENGTH_LONG).show();
			Log.d("notifragment","getcount");
			urlString="http://10.0.2.2:9999/Server/FragmentListInfo";
			MyTask m = new MyTask();
			m.execute(urlString);
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				}
			});
			Log.d("pokemon array lenght",pokemon.length+"");
			return pokemon.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			//Toast.makeText(getActivity(), "in getitem() ", Toast.LENGTH_LONG).show();
			Log.d("notifragment","getitem");
			
			return pokemon[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			//Toast.makeText(getActivity(), "in getitemid() ", Toast.LENGTH_LONG).show();
			Log.d("notifragment","getitemid");
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) 
		{
			// TODO Auto-generated method stub
			//Toast.makeText(getActivity(), "in getitemid() ", Toast.LENGTH_LONG).show();
			Log.d("notifragment","getview");
			View row = null;
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			row=inflater.inflate(R.layout.noti,arg2,false);
		/*	FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			FragmentA A = new FragmentA();
			FragmentB B = new FragmentB();
			
			
			transaction.add(R.id.frameLayout1,A,"a");
			transaction.add(R.id.frameLayout2,B,"b");
		
		//B.ChangeImg(arg0);
			transaction.commit();
		/*Log.d("n","154");
			FragmentA a2 = (FragmentA)manager.findFragmentByTag("a");
			a2.ChangeText(arg0);
			Log.d("n","157");
			FragmentB b2 =(FragmentB)manager.findFragmentByTag("b");
			b2.ChangeImg(arg0);
			Log.d("n","159");
		*/	
			
/*			TextView tv = (TextView) row.findViewById(R.id.textView1);
			tv.setText(pokemon[arg0]);
		//	ImageButton ib = (ImageButton) row.findViewById(R.id.imageButton1);
		//  ib.setImageResource(imags[arg0]);
			/*ib.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "you pressed some button", Toast.LENGTH_LONG).show();
				}
			});
			*/
/*			return row;
		}
		
		void putPoke(String name)
		{
			pokemon[f++]=name;
			Log.d("putpoke",name);
		}
		
		public class MyTask extends AsyncTask<String, Void, String>
		{
			String response;
			MyAdapter m = new MyAdapter(1);
			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				
				Log.d("notifragment","in my task...do in background");
					 response=ConnectionClass.send(urlString);
				
				return response;
			}
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub

				Log.d("notifragment","onPostExecute");
				//			Toast.makeText(getActivity(), "on post execute"+ result, Toast.LENGTH_LONG).show();
			
				try{
					 JSONArray ary=new JSONArray(result);
						info = new ArrayList<String>();
						for(int i = 0; i < ary.length(); i++) 
						{
							if(ary.getJSONObject(i).has("Name"))
							{
								String infos=ary.getJSONObject(i).getString("Name");
								info.add(infos);
							}
						
						}		 
						pokemon = info.toArray(new String[info.size()]);
						for(int i=0;i<pokemon.length;i++)
						{	
							m.putPoke(pokemon[i]);
						}
				}catch(Exception e)
				{
					System.out.print(e);
				}
			
			}
			
		}
		
		
		
		
		
	}*/

	/*@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "edo okati vathaav", Toast.LENGTH_LONG).show();
		Log.d("not","onitemclick");
	}*/
	
}


class SimpleAdapter extends ArrayAdapter<String>
{
	Context context;
	static String[] titles;
	TextView tv;
	String tabname = "";
	ImageView iv;
	int f=0;
	int[] images4 = {R.drawable.quest1,R.drawable.quest2,R.drawable.quest1,R.drawable.quest2,R.drawable.quest1,R.drawable.quest2,R.drawable.quest1,R.drawable.quest2,R.drawable.quest1,R.drawable.quest2,R.drawable.quest1,R.drawable.quest2,R.drawable.quest1,R.drawable.quest2,R.drawable.quest1,R.drawable.quest2,R.drawable.quest1,R.drawable.quest2};
	int[] images3 = {R.drawable.nnsc,R.drawable.ieee,R.drawable.nnsc,R.drawable.icon,R.drawable.nnsc,R.drawable.icon,R.drawable.nnsc,R.drawable.icon,R.drawable.nnsc,R.drawable.icon,R.drawable.nnsc,R.drawable.icon,R.drawable.nnsc,R.drawable.icon,R.drawable.nnsc,R.drawable.icon,R.drawable.nnsc,R.drawable.icon,R.drawable.nnsc,R.drawable.icon};
	int[] images2 = {R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb,R.drawable.sectionb};

	int[] images = {R.drawable.kamakshiprasad,R.drawable.cse2,R.drawable.cse3,R.drawable.cse4,R.drawable.cse5,R.drawable.cse6,R.drawable.cse7,R.drawable.cse8,R.drawable.ic,R.drawable.ic,R.drawable.ic,R.drawable.ic,R.drawable.ic,R.drawable.ic,R.drawable.ic,R.drawable.ic,R.drawable.ic,R.drawable.ic,R.drawable.ic};
	public SimpleAdapter(Context context,String[] titles,String tabname)
	{
		super(context,R.layout.noti,R.id.textView1,titles);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.titles = titles;
		this.tabname = tabname;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = null;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		row=inflater.inflate(R.layout.noti,parent,false);
		tv = (TextView)row.findViewById(R.id.textView1);
		tv.setText(titles[position]);
		iv = (ImageView)row.findViewById(R.id.imageView1);
	
		if(tabname.equalsIgnoreCase("data"))
		{
			iv.setImageResource(images[position]);
		}
		if(tabname.equalsIgnoreCase("data2"))
		{
			iv.setImageResource(images2[position]);
		}
		if(tabname.equalsIgnoreCase("data3"))
		{
			iv.setImageResource(images3[position]);
		}
		if(tabname.equalsIgnoreCase("data4"))
		{
			iv.setImageResource(images4[position]);
		}
		return row;
	}

}