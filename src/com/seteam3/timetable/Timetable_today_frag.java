package com.seteam3.timetable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.seteam3.parameters.TimeTableParameters;
import com.seteam3.parameters.TimeTableResultParameters;
import com.seteam3.tasks.TimeTableTask;
import com.seteam3.ukonnekt.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Timetable_today_frag extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		return inflater.inflate(R.layout.timetable_todayfrag, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = df.format(c.getTime());
		TimeTableParameters timeTableParameters = new TimeTableParameters(
				formattedDate, "today");

		try {
			ProgressDialog progressDialog = new ProgressDialog(getActivity());
			TimeTableTask timeTableTask = new TimeTableTask(progressDialog,
					timeTableParameters);
			timeTableTask.execute();

			ArrayList<TimeTableResultParameters> arrayList = timeTableTask.get();
			String texts[] = new String[arrayList.size()]; 
			int i = 0;
			
			for (TimeTableResultParameters timeTableResultParameters : arrayList) {
				String text = "";
				text = 	text + "Course ID : "	+ timeTableResultParameters.getCourseID() + "\n" ;	
				text = 	text + "Course Topic : "	+ timeTableResultParameters.getCourseTopic() + "\n" ;
				text = 	text + "Start Time : "	+ timeTableResultParameters.getStart_time() + "\n" ;
				text = 	text + "End Time : "	+ timeTableResultParameters.getEnd_time() + "\n\n" ;
				texts[i] = text;
				i++;
			}
			
			ListView listview = (ListView)getView().findViewById(R.id.list_TT);
			
			TimeTableRowAdapter objAdapter = new TimeTableRowAdapter(this.getActivity(), R.layout.timetable_list_item, arrayList);
			listview.setAdapter(objAdapter);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		public class TimeTableRowAdapter extends ArrayAdapter<TimeTableResultParameters> {

			private Activity activityTimeTableList;
			private List<TimeTableResultParameters> TimeTableItems;
			private TimeTableResultParameters TimeTableObjBean;
			private int row;
			
			public TimeTableRowAdapter(Activity context, int resource, List<TimeTableResultParameters> objects) {
				super(context, resource, objects);
				this.activityTimeTableList = context;
				this.row = resource;
				this.TimeTableItems = objects;
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				View cview = convertView;
				viewHolderTimeTableList holder;
				
				if (cview == null) {
					LayoutInflater inflater = (LayoutInflater) activityTimeTableList.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					cview = inflater.inflate(row, null);
					holder = new viewHolderTimeTableList();
					holder.courseName = (TextView) cview.findViewById(R.id.TT_CourseName);
					holder.courseTopic = (TextView) cview.findViewById(R.id.TT_CourseTopic);
					holder.date = (TextView) cview.findViewById(R.id.TT_Date);
					holder.startTime = (TextView) cview.findViewById(R.id.TT_StartTIme);
					holder.endTime = (TextView) cview.findViewById(R.id.TT_EndTime);
					cview.setTag(holder);
				} else {
					holder = (viewHolderTimeTableList) cview.getTag();
				}

				if ((TimeTableItems == null) || ((position + 1) > TimeTableItems.size()))
					return cview;

				
				TimeTableObjBean = TimeTableItems.get(position);			

			    //final variable is assigned only once
				final String courseName = TimeTableObjBean.getCourseID();
				holder.courseName.setText(courseName);

				final String courseTopic = TimeTableObjBean.getCourseTopic();
				holder.courseTopic.setText(courseTopic);
				
				final String date = TimeTableObjBean.getDate();
				holder.date.setText(date);

				final String startTime = TimeTableObjBean.getStart_time();
				holder.startTime.setText(startTime);
				
				final String endTime = TimeTableObjBean.getEnd_time();
				holder.endTime.setText(endTime);
				
				return cview;
			}
		}
		public class viewHolderTimeTableList{
			public TextView courseName, courseTopic, date, startTime, endTime;
		}
}
