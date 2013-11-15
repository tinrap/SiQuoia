/**
 * 
 */
package com.sjsu.siquoia;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Parnit Sainion
 * @since 25 October 2013
 *	Description: This activity is used for a user to login or create a new account.
 */
public class SiQuoiaLoginActivity extends Activity 
{
	private Button loginButton;
	private ProgressDialog progressBar;
	private EditText userNameInput, passwordInput;
	private TextView userCreationField;
	private SharedPreferences preferences;
	
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
    	userCreationField = (TextView) findViewById(R.id.userCreationField);
    	
    	//set up the login button's onClickListener
    	loginButton.setOnClickListener(new OnClickListener(){
    		//attempts to login the user
			@Override
			public void onClick(View arg0) 
			{
				String username = userNameInput.getText().toString().trim();
				String password = passwordInput.getText().toString().trim();
				
				//confirms user has entered both fields
				if(username.equals("")||password.equals(""))
				{
					Toast toast = Toast.makeText(getApplicationContext(), "Please enter both fields", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
					toast.show();
				}
				else
					new SiQuoiaLoginTask().execute(username,password);
			}        		
    	});
    	
    	userCreationField.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//go to home screen activity
				Intent intent = new Intent();
				intent.setClass(SiQuoiaLoginActivity.this, CreateUserAccountActivity.class);
				startActivity(intent);
				finish();
			}        		
    	});        	
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
			return " ";
		}
		
		protected void onPostExecute(String result) {
			
			//update user info
			SharedPreferences.Editor perferenceUpdater = preferences.edit();
			perferenceUpdater.putBoolean(SiQuoiaHomeActivity.LOGGED_IN, true);
			
			//commit preference changes
			perferenceUpdater.commit();
			
			//close progress dialog
			progressBar.dismiss();
			
			//go to home screen activity
			Intent intent = new Intent();
			intent.setClass(SiQuoiaLoginActivity.this, SiQuoiaHomeActivity.class);
			startActivity(intent);
			finish();
		}    	
    }
}
