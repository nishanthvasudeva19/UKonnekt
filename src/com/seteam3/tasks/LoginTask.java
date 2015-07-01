package com.seteam3.tasks;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.util.JsonReader;

import com.seteam3.parameters.LoginParameters;
import com.seteam3.tasks.LoginResult;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LoginTask extends AsyncTask <Void, Void, LoginResult > {
	
	ProgressDialog progressDialog;
	LoginParameters parameters;
	//URL of the Database Stored on the Server
	String url = "http://omega.uta.edu/~tmm4546/Ukonnekt.php?method=signInUser&signinusercallback=";
	
	public LoginTask(ProgressDialog progressDialog, LoginParameters loginParameters) {
		
		this.progressDialog = progressDialog;
		this.parameters = loginParameters;
	}

	//onPreExecute() function is used when we want to perform any functionality before the activity is being loaded
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		//Before the Activity is loaded, we need to show the Progress Dailog or Progress Bar
		progressDialog.show();
	}

	//doInBackground(), commands within this function are also performed before the Activity starts
	@Override
	protected LoginResult doInBackground(Void... params) {
		
		//HttpClient Used required to Execute Http Requests
		HttpClient client = new DefaultHttpClient();
		//HttpResponse used to collect the response received due to the HeepRequest
		HttpResponse httpResponse;
        int responseCode;
        String message;
        LoginResult response = null;
        
        //Always keep the Functions related to HTTP within the TRY and CATCH method
        //Probability of generating Exception is too high
        try
        {
        	//HttpPost used to request that the origin server accept the entity enclosed in the HtpRequest
        	HttpPost request = new HttpPost(url);
        	
        	ArrayList<NameValuePair> postParameters;
            
            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("username", parameters.getName()));
            postParameters.add(new BasicNameValuePair("password", parameters.getPassword()));
            //Provides the Entity to the HttpRequest
            //Here request provides the Username and the Password to the HttpRequest to be sent to the Server for Verification
            request.setEntity(new UrlEncodedFormEntity(postParameters));
            
            //HttpResponse stores the data received when HttpClient executes the Request provided by HttpRequest
            httpResponse = client.execute(request);
            //Obtains the status line of this response, getStatusLine() returns the status line, or null if not yet set
            responseCode = httpResponse.getStatusLine().getStatusCode();
            //
            message = httpResponse.getStatusLine().getReasonPhrase();
            HttpEntity entity = httpResponse.getEntity();
 
            if (entity != null) {
 
            	String jsonContent = EntityUtils.toString(entity);
                // Create a Reader from String and Store the Data Fetched from the HttpResponse in it
                Reader stringReader = new StringReader(jsonContent);
                //response in a Object of the LoginResult class
                //Reads the content and stores it in the LoginResult class
                response = readJsonStream(stringReader);
            }
 
        } 
        catch (ClientProtocolException e)  
        {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        } catch (IOException e) 
        {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
        catch(Exception e)
        {
        	client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
        
		return response;
	}

	//All Functions used to fetch the data from the HttpResponse
	public LoginResult readJsonStream(Reader stringReader) throws IOException
	{
		LoginResult returnValue= null;
		
		//To store data related to Json
	     JsonReader reader = new JsonReader(stringReader);
	     reader.setLenient(true);
	     
	     try 
	     {
	    	 returnValue = readMessagesArray(reader);
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
	     finally 
	     {
	       reader.close();
	     }
	     
	     return returnValue;
	}

	   public LoginResult readMessagesArray(JsonReader reader) throws IOException 
	   {
		   LoginResult result = null;

		   reader.beginArray();
		   while (reader.hasNext()) 
		   {
			   result = readMessage(reader);
		   }
		   reader.endArray();
		   
		   return result;
	   }

	   public LoginResult readMessage(JsonReader reader) throws IOException 
	   {
		   int errorCode = -1;
		   int  UniversityID = 0 ;
		     String FirstName = null;
		     String LastName = null;
		     
		     reader.beginObject();
		     while (reader.hasNext()) 
		     {
		       String name = reader.nextName();
		       
		       if (name.equals("Error Code"))
		       {
		    	   errorCode = reader.nextInt();
		       }
		       else if (name.equals("University_ID"))
		       {
		    	   UniversityID = reader.nextInt();
		       } 
		       else if (name.equals("First_name")) 
		       {
		    	   FirstName = reader.nextString();
		       }		  
		       else if (name.equals("Last_name"))
		       {
		    	   LastName = reader.nextString();
		       }
		       else
		       {
		         reader.skipValue();
		       }
		     }
		     reader.endObject();
		     
		     return new LoginResult(errorCode, UniversityID, FirstName, LastName);		     
		}

	@Override
	protected void onPostExecute(LoginResult result) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		super.onPostExecute(result);
	}
}