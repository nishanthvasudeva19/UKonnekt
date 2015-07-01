package com.seteam3.Course1;

import java.util.ArrayList;

import com.seteam3.SyllabusMeter.SyllabusMeterView;
import com.seteam3.parameters.SyllabusMeterParameters;
import com.seteam3.parameters.SyllabusResultParameters;
import com.seteam3.tasks.SyllabusMeterTask;
import com.seteam3.ukonnekt.R;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Course1_schedule_frag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.syllabus_meter,container,false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
		super.onCreate(savedInstanceState);
		
		final SyllabusMeterView SyllabusMeterProgressBarView = (SyllabusMeterView)getView().findViewById(R.id.progress);
		SyllabusMeterProgressBarView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		SyllabusMeterProgressBarView.setClipping(0);
		
		
		String courseID = "CSE5311";
		SyllabusMeterParameters syllabusMeterParameters = new SyllabusMeterParameters(courseID);
		
		try {
			ProgressDialog progressDialog = new ProgressDialog(getActivity());
			SyllabusMeterTask syllabusMeterTask = new SyllabusMeterTask(progressDialog,
					syllabusMeterParameters);
			syllabusMeterTask.execute();

			ArrayList<SyllabusResultParameters> arrayList = syllabusMeterTask.get();	
			float totalClasses = 0;
			float completedClasses = 0;
			
			for (SyllabusResultParameters syllabusResultParameters : arrayList) {
				 totalClasses = syllabusResultParameters.getTotalClasses();
				 completedClasses = syllabusResultParameters.getCompletedClasses();
			}
			try {
				float progress = (completedClasses/totalClasses)* 100 ;
	            SyllabusMeterProgressBarView.setClipping(progress);
				TextView tv = (TextView) getView().findViewById(R.id.progressSyllabus);
				String mytext=Integer.toString((int) progress);
				tv.setText(mytext);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}

