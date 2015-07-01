package com.seteam3.pages;


import com.seteam3.ukonnekt.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventsView extends Fragment {
	
	public EventsView(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View eventView = inflater.inflate(R.layout.eventlayout, container,
				false);

		return eventView;

	}

}