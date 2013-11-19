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
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Parnit Sainion
 * @since 19 November 2013
 * Description: Users can create a user account to play the game.
 */
public class CreateUserAccountActivity extends Activity{
	//declare variables
	private ProgressDialog progressBar;
	private EditText passOne, passTwo, emailField;
	private TextView passOneString, passTwoString;
	private Button createButton;
	private boolean passwordMatch;	

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_user_screen);
		
		//Initialize Variables
		emailField = (EditText) findViewById(R.id.emailField);
		passOne = (EditText) findViewById(R.id.passwordOne);
		passTwo = (EditText) findViewById(R.id.passwordTwo);
		passOneString = (TextView) findViewById(R.id.enterPass1);
		passTwoString = (TextView) findViewById(R.id.enterPass2);
		createButton = (Button) findViewById(R.id.createButton);
		
		/*
		 * Add a listener to the second password field. If passwords do not match change color to red, else green.
		 */
		passTwo.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				if(passOne.getText().toString().equals(passTwo.getText().toString()))
				{
					//passwords match
					passOneString.setTextColor(Color.GREEN);
					passTwoString.setTextColor(Color.GREEN);
					passwordMatch = true;
				}
				else
				{
					//password do not match
					passOneString.setTextColor(Color.RED);
					passTwoString.setTextColor(Color.RED);
					passwordMatch = false;
				}			
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub				
			}			
		});
				
		//Add a listener to the createButton
		createButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//In case passwords not not match display a message
				
				String email = emailField.getText().toString();
				String password = passOne.getText().toString();
				password = (password+password).hashCode()+"";
				
				if(vaildEmail(email) && validPassword())
				{
					new SiQuoiaCreateUserTask().execute(email, password);				
				}
			}			
		});	
		
		//hide keyboard on start up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
	
	/**
	 * @param email user's email
	 * @return whether email is valid or not
	 */
	public boolean vaildEmail(String email)
	{
		if(email.trim().equals("") || !(email.contains(".")&& email.contains("@")))
		{
			Toast toast = Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			return false;
		}		
		return true;
	}
	
	/**
	 * @return Whether email is valid and two emails match each other
	 */
	public boolean validPassword()
	{
		if(!passwordMatch)
		{
			Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			return false;
		}		
		return true;
	}

    /**
     * Override back button to go to Login Activity
     */
    @Override
    public void onBackPressed()
    {
    	Intent intent = new Intent();
		intent.setClass(CreateUserAccountActivity.this, SiQuoiaLoginActivity.class);
		startActivity(intent);
		finish();
    }
    
    /**
     * This is the background task that will create a new user in the database
     * @author Parnit Sainion
     * @since 27 October 2013
     */
    class SiQuoiaCreateUserTask extends AsyncTask<String, String, String>
    {
    	private String email;
    	@Override
		protected void onPreExecute() {
			//creates a progress dialog and displays it
    		progressBar = new ProgressDialog(CreateUserAccountActivity.this);
			progressBar.setIndeterminate(true);
			progressBar.setCancelable(false);
			progressBar.setMessage("Creating User");
			progressBar.show();			
		}
    	
    	@Override
		protected String doInBackground(String... params) {	 
    		String response = createUser(params[0], params[1]);
    		email = params[0];
    		Log.i("Response",response);
    		
			return response.trim();
		}
		
		protected void onPostExecute(String result) {			
			
			if(result.equalsIgnoreCase("true"))
			{
				//update user info to logged in
				SharedPreferences preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);			
				SharedPreferences.Editor perferenceUpdater = preferences.edit();
				perferenceUpdater.putBoolean(SiQuoiaHomeActivity.LOGGED_IN, true);
				
				//commit preference changes
				perferenceUpdater.commit();	
				
				//closes progress dialog
				progressBar.dismiss();		
				
				//go to home screen activity
				Intent intent = new Intent();
				intent.setClass(CreateUserAccountActivity.this, SiQuoiaHomeActivity.class);
				
				//store status as new user and pass user email
				intent.putExtra(SiQuoiaHomeActivity.NEW_USER, true);
				intent.putExtra(SiQuoiaHomeActivity.EMAIL, email);
				startActivity(intent);
				finish();
			}			
			else
			{
				//closes progress dialog
				progressBar.dismiss();	
				
				Toast toast= Toast.makeText(getApplicationContext(), "Email is already used.", Toast.LENGTH_SHORT);
				toast.show();
			}
		}    	
    }
    
    public String createUser(String email, String pass)
    {
    	String message ="";
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://ec2-54-201-65-140.us-west-2.compute.amazonaws.com/createUser.php");
    	
    	try {

        	List<NameValuePair> data = new ArrayList<NameValuePair>(1);    	
        	data.add(new BasicNameValuePair("email",email));
        	data.add(new BasicNameValuePair("password",pass));
        	
        	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data);
        	httppost.setEntity(entity);
			
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
}
