package com.seteam3.Course3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.seteam3.Course1.Course1_announcements.CourseAnnouncementRowAdapter;
import com.seteam3.Course1.Course1_announcements.viewHolderCourseAnnouncement;
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

public class Course3_announcements extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course1_announcement,container,false);
    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = df.format(c.getTime());
		
		String courseID = "CSE5325";
		AnnouncementParameters announcementParameters = new AnnouncementParameters(
				courseID, "Announcement");

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
//				text = 	text + "Due Date : "	+ announcementResultParameters.getDueDate() + "\n" ;
				text = 	text + "Description : "	+ announcementResultParameters.getDescription() + "\n" ;
				texts[i] = text;
				i++;
			}
			ListView listview = (ListView)getView().findViewById(R.id.list_ANN);
			
			CourseAnnouncementRowAdapter objAdapter = new CourseAnnouncementRowAdapter(this.getActivity(), R.layout.announcementslistitem, arrayList);
			listview.setAdapter(objAdapter);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public class CourseAnnouncementRowAdapter extends ArrayAdapter<AnnouncementResultParameters> {

		private Activity activityCourseAnnouncement;
		private List<AnnouncementResultParameters> CourseAnnouncementItems;
		private AnnouncementResultParameters CourseAnnouncementObjBean;
		private int row;
		
		public CourseAnnouncementRowAdapter(Activity context, int resource, List<AnnouncementResultParameters> objects) {
			super(context, resource, objects);
			this.activityCourseAnnouncement = context;
			this.row = resource;
			this.CourseAnnouncementItems = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View cview = convertView;
			viewHolderCourseAnnouncement holder;
			
			if (cview == null) {
				LayoutInflater inflater = (LayoutInflater) activityCourseAnnouncement.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				cview = inflater.inflate(row, null);
				holder = new viewHolderCourseAnnouncement();
				holder.announcementTitle = (TextView) cview.findViewById(R.id.announcementTitle);
				holder.announcementDetails = (TextView) cview.findViewById(R.id.announcementDescription);
				cview.setTag(holder);
			} else {
				holder = (viewHolderCourseAnnouncement) cview.getTag();
			}

			if ((CourseAnnouncementItems == null) || ((position + 1) > CourseAnnouncementItems.size()))
				return cview;

			
			CourseAnnouncementObjBean = CourseAnnouncementItems.get(position);			

		    //final variable is assigned only once
			final String annTitle = CourseAnnouncementObjBean.getTitle();
			holder.announcementTitle.setText(annTitle);

			final String annDescription = CourseAnnouncementObjBean.getDescription();
			holder.announcementDetails.setText(annDescription);
			
			return cview;
		}
	}
	public class viewHolderCourseAnnouncement{
		public TextView announcementTitle, announcementDetails;
	}
	
}

