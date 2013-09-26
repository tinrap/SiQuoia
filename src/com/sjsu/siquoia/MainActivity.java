package com.sjsu.siquoia;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DatabaseAsyncTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public String createUserAccount(String username, String fname, String lname, String password)
    {
    	
    	String sql =" Insert into users(fname,lname,username,password,curr_answer, curr_quiztopic, startingQuesNum)" +
    			"Values ('" + fname + "'," + 
    					"'" + lname + "'," + 
    	    			"'" + username +"  '," + 
    	    			"'" + password+"'," + 
    	    			"''," + //empty currentAnswer
    	    			"''," + //empty quizTopic
    	    			"0" + //0 for starting numbers
    					")";  
    	
    	return databaseConnect(sql);
     }
    
    public String getQuestions(String topic, int currentNumber)
    {
    	String sql = "select * from " + topic +" Limit "+currentNumber+","+(currentNumber+25); 
    	return databaseConnect(sql);
    }
    
    public String databaseConnect(String sql)
    {
    	String message ="";
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://192.168.1.7/siquoia/create.php");
    	
    	try {

        	List<NameValuePair> sqlCommand = new ArrayList<NameValuePair>(1);    	
        	sqlCommand.add(new BasicNameValuePair("sql",sql));
			httppost.setEntity(new UrlEncodedFormEntity(sqlCommand));
			
			//HttpResponse response = httpclient.execute(httppost);
			
			ResponseHandler<String> handler = new BasicResponseHandler();
			message = httpclient.execute(httppost,handler);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	return message;
    }
    
    
    private class DatabaseAsyncTask extends AsyncTask<String, String, String>
    {

		@Override
		protected String doInBackground(String... params) 
		{
			//Log.i("JSON",getQuestions("questionsanswers",0));
			Log.i("UserI Info",createUserAccount("mark22", "Mark", "Polo", "password1"));
			return null;
		}
    	
    }
    
}
