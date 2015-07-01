package com.seteam3.ukonnekt;

import java.util.concurrent.ExecutionException;

import com.seteam3.parameters.LoginParameters;
import com.seteam3.tasks.LoginResult;
import com.seteam3.tasks.LoginTask;
import com.seteam3.database.DataAccess;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class LogIn extends Activity {

	RadioGroup radioSelect;
	EditText netId;
	EditText passText;
	Button login;
	TextView incorrectLogIn;
	String userDetail;
    public	DataAccess passwordData = new DataAccess(this);
	
	//comment
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		
		radioSelect = (RadioGroup) findViewById(R.id.radioSelect);
		netId = (EditText) findViewById(R.id.editTextNetId);
		passText = (EditText) findViewById(R.id.editTextPassword);
		login = (Button) findViewById(R.id.login);
		incorrectLogIn = (TextView) findViewById(R.id.textViewIncorrectPassword);
		int selectedId = radioSelect.getCheckedRadioButtonId();
		incorrectLogIn.setVisibility(View.INVISIBLE);
		userDetail = Integer.toString(selectedId);
		
	}

	//On Click Function
	//Called when the SignIn Button is Clicked
	public void stuProfLogin(View v)
	{
		
		try 
		{
			String name = ((EditText)findViewById(R.id.editTextNetId)).getText().toString();
			String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();
			String studentOrProfessor=userDetail;
			
			//LoginParameters in Parameters Package
			//Name, Password and User detail sent to this function using its object
			//Data Stored in the Function and is accessed to compare it with the data in the Database
			LoginParameters parameters = new LoginParameters(name, password, studentOrProfessor);
			
			//Progress Dailog Set
			//Will plan replacing it with a progress bar
			ProgressDialog progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("Loading UKonnekt.....");
	        
			
			LoginTask loginTask = new LoginTask(progressDialog, parameters);
	        loginTask.execute();       
       
			LoginResult result = loginTask.get();
			if(null!= result)
			{
				//This LoginResult is a Function Defined Down in the Code
				//Used to show the LogIn results
				LoginResult(result);
			}
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}
	
	private void LoginResult(LoginResult result)
	{
		if(result.GetErrorId() == 0)
		{
			//Intent defined so as to start a new Activity and take the User to his Home Screen
			incorrectLogIn.setVisibility(View.INVISIBLE);
			Intent intent = new Intent(LogIn.this, StuMainActivity.class);
			intent.putExtra("content", result.getUniversityID());
    		passwordData.changePassword("1");
			startActivity(intent);
			overridePendingTransition(R.anim.fadein, R.anim.slideup);
			finish();
		}
		else if(result.GetErrorId() == 1)
		{
			incorrectLogIn.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}

}
