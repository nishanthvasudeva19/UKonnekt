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

import com.seteam3.parameters.SyllabusMeterParameters;
import com.seteam3.parameters.SyllabusResultParameters;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.JsonReader;

public class SyllabusMeterTask  extends AsyncTask<Void, Void, ArrayList>{
	
	ProgressDialog progressDialog;
	SyllabusMeterParameters syllabusMeterParameters;
	String url = "http://omega.uta.edu/~tmm4546/Ukonnekt.php?method=getSyllabusMeter&getSyllabusMetercallback=";

	public SyllabusMeterTask(ProgressDialog progressDialog,
			SyllabusMeterParameters syllabusMeterParameters) {

		this.progressDialog = progressDialog;
		this.syllabusMeterParameters = syllabusMeterParameters;
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
            postParameters.add(new BasicNameValuePair("courseID", String.valueOf(syllabusMeterParameters.getCourseID())));
                      
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
		   SyllabusResultParameters syllabusResultParameters;
		   ArrayList messages = new ArrayList<SyllabusResultParameters>();

	     reader.beginArray();
	     while (reader.hasNext()) 
	     {
	    	 syllabusResultParameters = readMessage(reader);
	    	  messages.add(syllabusResultParameters);
	     }
	     reader.endArray();
	     return messages;
	   }
	   
	   public SyllabusResultParameters readMessage(JsonReader reader) throws IOException 
	   {  
		   int totalClasses = 0;
			int completedClasses = 0;
		     reader.beginObject();
		     while (reader.hasNext()) 
		     {
		       String name = reader.nextName();
		       if (name.equals("totalClasses")) 
		       {
		    	   totalClasses = reader.nextInt();
		       } 
		       else if (name.equals("completedClasses")) 
		       {
		    	   completedClasses = reader.nextInt();
		       }		  
		       else
		       {
		         reader.skipValue();
		       }
		     }
		     reader.endObject();
		     
		     return new SyllabusResultParameters(totalClasses, completedClasses);		     
		}
	@Override
	protected void onPostExecute(ArrayList result) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		super.onPostExecute(result);
	}

}
