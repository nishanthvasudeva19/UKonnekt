package com.seteam3.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.seteam3.timetable.Timetable_today_frag;
import com.seteam3.timetable.Timetable_upcoming_frag;

public class TimetableAdapter extends FragmentStatePagerAdapter {

	public TimetableAdapter(FragmentManager fm) {

		super(fm);

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:

			return new Timetable_today_frag();

		case 1:

			return new Timetable_upcoming_frag();
		
		}
		return null;

	}

	@Override
	public int getCount() {
		return 2;
	}

}
