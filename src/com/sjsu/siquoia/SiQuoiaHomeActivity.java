/**
 * 
 */
package com.sjsu.siquoia;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Parnit Sainion
 *
 */
public class SiQuoiaHomeActivity extends Activity {
	
	//Variable Declaration
	Button continueButton, newGameButton, leaderboardButton, submitQuestionButton, quitButton;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        
        //locate buttons from view
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
