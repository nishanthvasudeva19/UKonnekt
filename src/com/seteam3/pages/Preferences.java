package com.seteam3.pages;

import android.app.Activity;
import android.graphics.AvoidXfermode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

import com.seteam3.ukonnekt.R;

public class Preferences extends Fragment {
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View eventView = inflater.inflate(R.layout.fragment_preferences, container,
				false);
		Button setTheme;
		setTheme = (Button)eventView.findViewById(R.id.SetTheme);
		setTheme.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		return eventView;

	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	}

}