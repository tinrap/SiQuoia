package com.sjsu.siquoia;

import com.sjsu.siquoia.model.User;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Parnit Sainion
 * @since 8 December 2013
 * Description: This activity displays the logged in user's information.
 *
 */
public class UserProfileActivity extends Activity {

	//Declare variable
	private TextView email, siquoiaPoints, packetsBought, memorabiliaBought, totalPointsSpent, totalPoints; 
	private final int INITIAL_POINTS = 20;
	private int totalPointsEarned;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile_screen);
	
		//get textviews
		email = (TextView) findViewById(R.id.email);
		siquoiaPoints = (TextView) findViewById(R.id.siquoiaPoints);
		packetsBought = (TextView) findViewById(R.id.packetsBought);
		memorabiliaBought = (TextView) findViewById(R.id.memorabiliaBought);
		totalPointsSpent = (TextView) findViewById(R.id.totalPointsSpent);
		totalPoints = (TextView) findViewById(R.id.totalPoints);
		
		//get intent and user
		Intent intent = getIntent();
		User user = (User) intent.getSerializableExtra(SiQuoiaHomeActivity.USER);
		
		//get User info
		SharedPreferences preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);
		user.setSequoiaBucks(preferences.getInt(SiQuoiaHomeActivity.SIQUOIA_POINTS, 0));
		
		totalPointsEarned = user.getSiquoiaBucks() + user.getTotalPointsSpent() - INITIAL_POINTS;
		
		//set textviews' text
		email.setText(user.getEmail());
		siquoiaPoints.setText(user.getSiquoiaBucks()+"");
		packetsBought.setText(user.getPacketsBougth()+"");
		memorabiliaBought.setText(user.getMemorabiliaBougth()+"");
		totalPointsSpent.setText(user.getTotalPointsSpent()+"");
		totalPoints.setText(totalPointsEarned+"");
	}
}
