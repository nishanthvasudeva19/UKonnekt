package com.seteam3.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.seteam3.pages.Course1View;
import com.seteam3.pages.Course2View;
import com.seteam3.pages.Course3View;
import com.seteam3.pages.EventsView;
import com.seteam3.pages.TimetableView;

public class AllPagesAdapter extends FragmentStatePagerAdapter {

	public AllPagesAdapter(FragmentManager fm) {

		super(fm);

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:

			return new TimetableView();

		case 1:

			return new Course1View();
		case 2:

			return new Course3View();

		case 3:

			return new Course2View();

		case 4:

			return new EventsView();

		}
		return null;

	}

	@Override
	public int getCount() {
		return 5;
	}

}
