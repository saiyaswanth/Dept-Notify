package com.example.deptallstage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FragmentA extends Fragment
{
	int i;
	TextView tv;
	String[] pokemon;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.notfrag, container, false);
		tv = (TextView)v.findViewById(R.id.textView1);
		Log.d("n-frag a","30");
		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d("n-frag a","36");
		pokemon = getActivity().getResources().getStringArray(R.array.pokemon);
			
	}
	public void ChangeText(int i) {
		// TODO Auto-generated method stub
		Log.d("n-frag a","41");
		tv = (TextView) getActivity().findViewById(R.id.textView1);
	tv.setText(pokemon[i]);
	
	}
	
	
}
