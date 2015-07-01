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

import com.seteam3.parameters.TimeTableParameters;
import com.seteam3.parameters.TimeTableResultParameters;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.JsonReader;

public class TimeTableTask  extends AsyncTask<Void, Void, ArrayList>{
	
	ProgressDialog progressDialog;
	TimeTableParameters timeTableParameters;
	String url = "http://omega.uta.edu/~tmm4546/Ukonnekt.php?method=getTimetable&getTimetablecallback=";

	public TimeTableTask(ProgressDialog progressDialog,
			TimeTableParameters timeTableParameters) {

		this.progressDialog = progressDialog;
		this.timeTableParameters = timeTableParameters;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progressDialog.show();
	}

	@Override
	protected ArrayList doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse;
        int responseCode;
        String message;
        ArrayList response = null;
        
        try 
        {
        	HttpPost request = new HttpPost(url);
        	
        	ArrayList<NameValuePair> postParameters;
            
            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("today_date", String.valueOf(timeTableParameters.gettodayDate())));
            postParameters.add(new BasicNameValuePair("results_For", String.valueOf(timeTableParameters.getResultsFor())));
            
            request.setEntity(new UrlEncodedFormEntity(postParameters));
        	
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();
 
            HttpEntity entity = httpResponse.getEntity();
 
            if (entity != null) {
 
            	String jsonContent = EntityUtils.toString(entity);

                // Create a Reader from String
                Reader stringReader = new StringReader(jsonContent);

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
	
	public ArrayList readJsonStream(Reader stringReader) throws IOException 
	{
		ArrayList returnArrayList = null;
		
	     JsonReader reader = new JsonReader(stringReader);
	     reader.setLenient(true);
	     
	     try 
	     {
	    	 returnArrayList = readMessagesArray(reader);
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
	     finally 
	     {
	       reader.close();
	     }
	     
	     return returnArrayList;
	}

	   public ArrayList readMessagesArray(JsonReader reader) throws IOException 
	   {
		   TimeTableResultParameters scheduleList;
		   ArrayList messages = new ArrayList<TimeTableResultParameters>();

	     reader.beginArray();
	     while (reader.hasNext()) 
	     {
	    	 scheduleList = readMessage(reader);
	    	  messages.add(scheduleList);
	     }
	     reader.endArray();
	     return messages;
	   }
	   
	   public TimeTableResultParameters readMessage(JsonReader reader) throws IOException 
	   {  
		   String courseID = "";
			String courseTopic = "";
			int status = 0;
			String date = "";
			String start_time = "";
			String end_time = "";
		     
		     reader.beginObject();
		     while (reader.hasNext()) 
		     {
		       String name = reader.nextName();
		       if (name.equals("courseID")) 
		       {
		    	   courseID = reader.nextString();
		       } 
		       else if (name.equals("courseTopic")) 
		       {
		    	   courseTopic = reader.nextString();
		       }		  
		       else if (name.equals("status"))
		       {
		    	   status = reader.nextInt();
		       }
		       else if (name.equals("date"))
		       {
		    	   date = reader.nextString();
		       }
		       else if (name.equals("start_time"))
		       {
		    	   start_time = reader.nextString();
		       }
		       else if (name.equals("end_time"))
		       {
		    	   end_time = reader.nextString();
		       }
		       else
		       {
		         reader.skipValue();
		       }
		     }
		     reader.endObject();
		     
		     return new TimeTableResultParameters(courseID, courseTopic, status, date , start_time, end_time);		     
		}
	@Override
	protected void onPostExecute(ArrayList result) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		super.onPostExecute(result);
	}

}
