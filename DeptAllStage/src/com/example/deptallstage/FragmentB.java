package com.example.deptallstage;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FragmentB extends Fragment
{
	ImageButton img;
//	int[] imags = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.notfrag2, container, false);
	img = (ImageButton)v.findViewById(R.id.imgbutton);
	Log.d("n-frag b","24");
	return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d("n-frag b","30");
	}
	public void ChangeImg(int i) {
		// TODO Auto-generated method stub
		Log.d("n-frag b","34");
//		img.setImageResource(imags[i]);
	}
}
