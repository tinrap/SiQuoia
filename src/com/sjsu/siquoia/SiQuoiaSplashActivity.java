/**
 * 
 */
package com.sjsu.siquoia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * @author Parnit Sainion
 * @since 20 November 2013
 * Description: This activity displays a splash image before proceeding to login or home screen.
 */
public class SiQuoiaSplashActivity extends Activity {
	
	private final int SPLASH_TIME = 2000;
	private SharedPreferences preferences;
	  boolean loggedIn;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//set view
		setContentView(R.layout.splash_screen);
		
		//Get Image view
		ImageView splashImageView = (ImageView) findViewById(R.id.splashImageView);
		
		//get splash image and set it to image view
		Bitmap splashImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		splashImageView.setImageBitmap(splashImage);
		
		//get users info from app
        preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);
        
        //get login status 
        loggedIn= preferences.getBoolean(SiQuoiaHomeActivity.LOGGED_IN, false);
        
		
		//create a thread to run the thread
		Thread splashScreenThread = new Thread () {
			
			@Override
			public void run(){
				//synchronize the two threads
				synchronized(this)
				{
					try {
						wait(SPLASH_TIME);
					} catch (InterruptedException e) {
					    e.printStackTrace();
					}
					 
			        if(loggedIn)//logged in
			        {
			        	Intent intent = new Intent();
			        	intent.setClass(getApplicationContext(), SiQuoiaHomeActivity.class);
			        	intent.putExtra(SiQuoiaHomeActivity.NEW_USER, false);
			        	startActivity(intent);
			        	finish();
			        }
			        else
			        { //not logged in
						Intent intent = new Intent(getApplicationContext(), SiQuoiaLoginActivity.class);
						startActivity(intent);
						finish();
			        }
				}				
			}
		};
		
		//start the thread
		splashScreenThread.start();
	}

}
