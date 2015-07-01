package com.seteam3.ukonnekt;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.seteam3.database.*;

public class Home extends Activity {
	DataSQLHelper dbHelper;
    List<PasswordList> userPassword;
    public	DataAccess passwordData = new DataAccess(this);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		try {
            new Thread(){
	            @Override
	            public void run(){
	                try {
	                    synchronized(this){
	                    	dbHelper = new DataSQLHelper(Home.this);
	                		dbHelper.createDataBase();
	                		dbHelper.openDataBase();
	                		dbHelper.close();
	                    }
	                    sleep(2000);
	                }
	                catch(Exception ex){
	                	return;
	                }
	        		passwordData.open();
	        		userPassword = passwordData.getPasswordList();
	        		String password = userPassword.get(0).getPassword();
      		
	        		if(password.equals("0")){
	        			startActivity(new Intent(Home.this, LogIn.class));
	        			overridePendingTransition(R.anim.fadein, R.anim.slideup);
		                finish();
	        		}
	        		else{
	        			startActivity(new Intent(Home.this, StuMainActivity.class));
	        			overridePendingTransition(R.anim.fadein, R.anim.slideup);
		                finish();
	        		}
	            }
	        }.start();        
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
    protected void onDestroy() {
      super.onDestroy();
    }
}