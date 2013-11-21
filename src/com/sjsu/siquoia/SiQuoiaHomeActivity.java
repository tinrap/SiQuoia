/**
 * 
 */
package com.sjsu.siquoia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Parnit Sainion
 * @since 19 November 2013
 * Description: This app is the home landing screen for the app. Users can: continue a previous quiz, start a new quiz,
 * 				check the leader-board, submit a question to put into the same, and quit the app.
 *
 */
public class SiQuoiaHomeActivity extends Activity {
	
	//Variable Declaration
	Button continueButton, newGameButton, leaderboardButton, submitQuestionButton, quitButton;
	
	//preferences
	protected static final String SIQUOIA_PREF = "SiquoiaPref";
	protected static final String LOGGED_IN = "loggedIn";
	protected static final String NEW_USER = "newUser";
	protected static final String EMAIL = "email";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        
        //initialize buttons from view
        continueButton = (Button) findViewById(R.id.continueButton);
        newGameButton = (Button) findViewById(R.id.newGameButton);
        leaderboardButton = (Button) findViewById(R.id.leaderboardButton);
        submitQuestionButton = (Button) findViewById(R.id.submitQuesButton);
        quitButton = (Button) findViewById(R.id.quitButton);
        
        //Set Listener for continue
        continueButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.i("homeScreenButtons", "continue clicked");				
			}        	
        });
        
      //Set Listener for newGameButton
        newGameButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.i("homeScreenButtons", "new game clicked");				
			}        	
        });
        
      //Set Listener for leaderboardButton
        leaderboardButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.i("homeScreenButtons", "leaderboardButton clicked");				
			}        	
        });
        
        //Set Listener for submitQuestionButton
        submitQuestionButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.i("homeScreenButtons", "submitQuestionButton clicked");				
			}        	
        });
        
      //Set Listener for quitButton
        quitButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//closes the application
				finish();				
			}        	
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
		switch(item.getItemId())
		{
			//user wantsto log out
			case R.id.action_logout:
				//update user info
				SharedPreferences preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);
				SharedPreferences.Editor perferenceUpdater = preferences.edit();
				perferenceUpdater.putBoolean(SiQuoiaHomeActivity.LOGGED_IN, false);
				
				//commit preference changes
				perferenceUpdater.commit();
				
				Intent intent = new Intent();
	        	intent.setClass(SiQuoiaHomeActivity.this, SiQuoiaLoginActivity.class);
	        	startActivity(intent);
	        	finish();
				break;
				
			//user is redeeming a code
			case R.id.action_redeem:
				Toast toast = Toast.makeText(getApplicationContext(), "To Be Implemented", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
				toast.show();
				break;
				
			default:
				break;
		}
    	return false;
    	
    }
}
