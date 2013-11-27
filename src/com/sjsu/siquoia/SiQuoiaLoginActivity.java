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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Parnit Sainion
 * @since 26 November 2013
 *	Description: This activity is used for a user to login or create a new account.
 */
public class SiQuoiaLoginActivity extends Activity 
{
	//variables declared
	private Button loginButton;
	private ProgressDialog progressBar;
	private EditText userNameInput, passwordInput;
	private TextView createUserText;
	private SharedPreferences preferences;
	private String loginUrl = "http://ec2-54-201-65-140.us-west-2.compute.amazonaws.com/login.php";
	private String email;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        //get users info from app
        preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);
       
    	//Variable Initialization
    	loginButton = (Button) findViewById(R.id.loginButton);
    	userNameInput = (EditText) findViewById(R.id.usernameField);
    	passwordInput = (EditText) findViewById(R.id.passwordField);
    	createUserText = (TextView) findViewById(R.id.createUserText);
    	
    	//set up the login button's onClickListener
    	loginButton.setOnClickListener(new OnClickListener(){
    		//attempts to login the user
			@Override
			public void onClick(View arg0) 
			{
				//get inputer username and password
				email = userNameInput.getText().toString().trim();
				String password = passwordInput.getText().toString().trim();
				
				//hash the password
				password= (password+password).hashCode()+"";
				
				//confirms user has entered both fields
				if(email.equals("")||password.equals(""))
				{
					//display message to tell user to fill both fields
					Toast toast = Toast.makeText(getApplicationContext(), "Please enter both fields", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
					toast.show();
				}
				else
					new SiQuoiaLoginTask().execute(email,password);
			}        		
    	});
    	
    	//creates a listener to allow users click on "create account"
    	createUserText.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//go to Create user activity
				Intent intent = new Intent();
				intent.setClass(SiQuoiaLoginActivity.this, CreateUserAccountActivity.class);
				startActivity(intent);
				finish();
			}        		
    	});        	
    	
    	//hide keyboard on start up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    
    /**
     * checks user credentials to database
     * @param email user's email
     * @param password user's password
     * @return user information or nothing
     */
    public String login(String email, String password)
    {
    	//variables declared
    	String message ="";
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost(loginUrl);
    	
    	try {
    		//add user information to post
        	List<NameValuePair> data = new ArrayList<NameValuePair>(1);    	
        	data.add(new BasicNameValuePair("email",email));
        	data.add(new BasicNameValuePair("password",password));
			httppost.setEntity(new UrlEncodedFormEntity(data));
			
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
    		//input[0] = username
    		//input[1] = password
			return login(input[0], input[1]);
		}
		
		protected void onPostExecute(String result) {
			
			if (result.equalsIgnoreCase("true")) //correct Credentials
			{
				//update user info
				SharedPreferences.Editor perferenceUpdater = preferences.edit();
				perferenceUpdater.putBoolean(SiQuoiaHomeActivity.LOGGED_IN, true);
				perferenceUpdater.putString(SiQuoiaHomeActivity.EMAIL, email);
				
				//commit preference changes
				perferenceUpdater.commit();
				
				//close progress dialog
				progressBar.dismiss();
				
				//go to home screen activity
				Intent intent = new Intent();
				intent.setClass(SiQuoiaLoginActivity.this, SiQuoiaHomeActivity.class);
				intent.putExtra(SiQuoiaHomeActivity.NEW_USER, false);
				startActivity(intent);
				finish();			
			}
			else //incorrect credentials
			{
				Toast toast = Toast.makeText(getApplicationContext(), "Incorrect Credentials" , Toast.LENGTH_SHORT);
				toast.show();
				
				//clear password field
				passwordInput.setText("");
				
				//close progress dialog
				progressBar.dismiss();
			}			
		}    	
    }
}
