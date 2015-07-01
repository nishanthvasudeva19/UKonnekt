package com.seteam3.ukonnekt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectLogInAs extends Activity {

	
	Button selectStudent;
	Button selectProfessor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectloginas);
		
		selectStudent = (Button) findViewById(R.id.buttonStudent);
		selectProfessor = (Button) findViewById(R.id.buttonProfessor);
		
		selectStudent.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				//Intent use to take the User to a new activity, and pass the selected Button value, as Student or Professor
				Intent intent = new Intent(SelectLogInAs.this, LogIn.class);
				intent.putExtra("item", "Student");
				startActivity(intent);
				// Animations Created
				// Activity Switched Using Slide Up Animation
				overridePendingTransition(R.anim.fadein, R.anim.slideup);
	         }
		});
		
		selectProfessor.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				Intent intent = new Intent(SelectLogInAs.this, LogIn.class);
				intent.putExtra("item", "Professor");
				startActivity(intent);
				// Animations Created
				// Activity Switched Using Slide Up Animation
				overridePendingTransition(R.anim.fadein, R.anim.slideup);
	         }
		});
		
	}		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}

}
