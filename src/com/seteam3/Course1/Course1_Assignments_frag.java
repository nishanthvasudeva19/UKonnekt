package com.seteam3.Course1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.seteam3.Course1.Course1_announcements.CourseAnnouncementRowAdapter;
import com.seteam3.parameters.AnnouncementParameters;
import com.seteam3.parameters.AnnouncementResultParameters;
import com.seteam3.tasks.AnnouncementsTask;
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

public class Course1_Assignments_frag  extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.course1_assignment,container,false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = df.format(c.getTime());

		String courseID = "CSE5324";
		AnnouncementParameters announcementParameters = new AnnouncementParameters(
				courseID, "Assignment");

		try {
			ProgressDialog progressDialog = new ProgressDialog(getActivity());
			AnnouncementsTask announcementsTask = new AnnouncementsTask(progressDialog,
					announcementParameters);
			announcementsTask.execute();

			ArrayList<AnnouncementResultParameters> arrayList = announcementsTask.get();
			String texts[] = new String[arrayList.size()]; 
			int i = 0;

			for (AnnouncementResultParameters announcementResultParameters : arrayList) {
				String text = "";
				text = 	text + "Title : "	+ announcementResultParameters.getTitle() + "\n" ;	
				text = 	text + "Due Date : "	+ announcementResultParameters.getDueDate() + "\n" ;
				text = 	text + "Description : "	+ announcementResultParameters.getDescription() + "\n" ;
				texts[i] = text;
				i++;
			}
			ListView listview = (ListView)getView().findViewById(R.id.list_ASI);
			CourseAssignmentRowAdapter objAdapter = new CourseAssignmentRowAdapter(this.getActivity(), R.layout.assignmentslistitem, arrayList);
			listview.setAdapter(objAdapter);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public class CourseAssignmentRowAdapter extends ArrayAdapter<AnnouncementResultParameters> {

		private Activity activityCourseAssignment;
		private List<AnnouncementResultParameters> CourseAssignmentItems;
		private AnnouncementResultParameters CourseAssignmentObjBean;
		private int row;
		
		public CourseAssignmentRowAdapter(Activity context, int resource, List<AnnouncementResultParameters> objects) {
			super(context, resource, objects);
			this.activityCourseAssignment = context;
			this.row = resource;
			this.CourseAssignmentItems = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View cview = convertView;
			viewHolderCourseAssignmentList holder;
			
			if (cview == null) {
				LayoutInflater inflater = (LayoutInflater) activityCourseAssignment.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				cview = inflater.inflate(row, null);
				holder = new viewHolderCourseAssignmentList();
				holder.assignmentTitle = (TextView) cview.findViewById(R.id.assignmentTitle);
				holder.assignmentDescription = (TextView) cview.findViewById(R.id.assignmentDescription);
				holder.assignmentDueDate = (TextView) cview.findViewById(R.id.assignmentDueDateTextView);
				cview.setTag(holder);
			} else {
				holder = (viewHolderCourseAssignmentList) cview.getTag();
			}

			if ((CourseAssignmentItems == null) || ((position + 1) > CourseAssignmentItems.size()))
				return cview;

			
			CourseAssignmentObjBean = CourseAssignmentItems.get(position);			

		    //final variable is assigned only once
			final String assTitle = CourseAssignmentObjBean.getTitle();
			holder.assignmentTitle.setText(assTitle);

			final String assDescription = CourseAssignmentObjBean.getDescription();
			holder.assignmentDescription.setText(assDescription);
			
			final String assDueDate = CourseAssignmentObjBean.getDueDate();
			holder.assignmentDueDate.setText(assDueDate);
			
			return cview;
		}
	}
	public class viewHolderCourseAssignmentList{
		public TextView assignmentTitle, assignmentDescription, assignmentDueDate;
	}
	
}


