package com.seteam3.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.seteam3.Course3.Course3_Assignments_frag;
import com.seteam3.Course3.Course3_Grades_frag;
import com.seteam3.Course3.Course3_announcements;
import com.seteam3.Course3.Course3_schedule_frag;
import com.seteam3.Course3.Course3_slides_frag;

public class Course3Adapter extends FragmentStatePagerAdapter {

	public Course3Adapter(FragmentManager fm) {

		super(fm);

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			
			return new Course3_announcements();
			
		case 1:

			return new Course3_Assignments_frag();
		case 2:

			return new Course3_Grades_frag();

		case 3:

			return new Course3_schedule_frag();
		case 4:
			
			return new Course3_slides_frag();

		}
		return null;

	}

	@Override
	public int getCount() {
		return 5;
	}

}

