package com.seteam3.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.seteam3.Course2.Course2_Assignments_frag;
import com.seteam3.Course2.Course2_Grades_frag;
import com.seteam3.Course2.Course2_announcements;
import com.seteam3.Course2.Course2_schedule_frag;
import com.seteam3.Course2.Course2_slides_frag;

public class Course2Adapter extends FragmentStatePagerAdapter {

	public Course2Adapter(FragmentManager fm) {

		super(fm);

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			
			return new Course2_announcements();
			
		case 1:

			return new Course2_Assignments_frag();
		case 2:

			return new Course2_Grades_frag();

		case 3:

			return new Course2_schedule_frag();
		case 4:
			
			return new Course2_slides_frag();

		}
		return null;

	}

	@Override
	public int getCount() {
		return 5;
	}

}
