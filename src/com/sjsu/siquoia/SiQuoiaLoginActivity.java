/**
 * 
 */
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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Parnit Sainion
 *
 */
public class SiQuoiaLoginActivity extends Activity 
{
	private Button loginButton;
	private ProgressDialog progressBar;
	private EditText userNameInput, passwordInput;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        
        //dummy variable for now
        boolean logggedIn= false;
        
        if(logggedIn)//logged in
        {
        	Intent intent = new Intent();
        	intent.setClass(SiQuoiaLoginActivity.this, SiQuoiaHomeActivity.class);
        	startActivity(intent);
        	finish();
        }
        else
        {
        	//get
        	loginButton = (Button) findViewById(R.id.loginButton);
        	userNameInput = (EditText) findViewById(R.id.usernameField);
        	passwordInput = (EditText) findViewById(R.id.passwordField);
        	
        	//set up the login button's onClickListener
        	loginButton.setOnClickListener(new OnClickListener(){

        		//attempts to login the user
				@Override
				public void onClick(View arg0) 
				{
					new SiQuoiaLoginTask().execute(userNameInput.getText().toString(),passwordInput.getText().toString());
				}
        		
        	});
        	
        }
    }

    /**
     * This is the background task that will log the user into the application by check user credentials on a database.
     * @author Parnit Sainion
     *
     */
    class SiQuoiaLoginTask extends AsyncTask<String, String, String>
    {
    	@Override
		protected void onPreExecute() {
			//create the login in progress dialog and display it
    		progressBar = new ProgressDialog(SiQuoiaLoginActivity.this);
			progressBar.setIndeterminate(true);
			progressBar.setCancelable(false);
			progressBar.setMessage("Logging In");
			progressBar.show();			
		}
    	
    	@Override
		protected String doInBackground(String... input) {
    		
			return " ";
		}
		
		protected void onPostExecute(String result) {
			
			//close progress dialog
			progressBar.dismiss();
		}    	
    }
    
    /**
     * This is the background task that will create a new user in the database
     * @author Parnit Sainion
     *
     */
    class SiQuoiaCreateUserTask extends AsyncTask<String, String, String>
    {
    	@Override
		protected void onPreExecute() {
			//create the login in progress dialog and display it
    		progressBar = new ProgressDialog(SiQuoiaLoginActivity.this);
			progressBar.setIndeterminate(true);
			progressBar.setCancelable(false);
			progressBar.setMessage("Creating User");
			progressBar.show();			
		}
    	
    	@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		protected void onPostExecute(String result) {
			
			//close progress dialog
			progressBar.dismiss();
			
		}    	
    }
    
    public String login(String sql)
    {
    	String message ="";
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://XXX.XXX.X.X/siquoia/createUser.php");
    	
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
