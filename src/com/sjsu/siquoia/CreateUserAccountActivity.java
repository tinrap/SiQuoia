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
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Parnit Sainion
 * @since 26 October 2013
 * Description: Users can create a user account to play the game.
 */
public class CreateUserAccountActivity extends Activity{
	//declare variables
	private ProgressDialog progressBar;
	private EditText userName, passOne, passTwo;
	private TextView passOneString, passTwoString;
	private boolean passwordMatch;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_user_screen);
		
		//Initialize Variables
		passOne = (EditText) findViewById(R.id.passwordOne);
		passTwo = (EditText) findViewById(R.id.passwordTwo);
		passOneString = (TextView) findViewById(R.id.enterPass1);
		passTwoString = (TextView) findViewById(R.id.enterPass2);
		
		passTwo.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				if(passOne.getText().toString().equals(passTwo.getText().toString()))
				{
					passOneString.setTextColor(Color.GREEN);
					passTwoString.setTextColor(Color.GREEN);
					passwordMatch = true;
				}
				else
				{
					passOneString.setTextColor(Color.RED);
					passTwoString.setTextColor(Color.RED);
					passwordMatch = false;
					Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
					toast.show();
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
		
		
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
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
     *
     */
    class SiQuoiaCreateUserTask extends AsyncTask<String, String, String>
    {
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
			return null;
		}
		
		protected void onPostExecute(String result) {
			
			//closes progress dialog
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
}
