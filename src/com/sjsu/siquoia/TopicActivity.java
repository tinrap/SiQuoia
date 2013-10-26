/**
 * 
 */
package com.sjsu.siquoia;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * @author Parnit Sainion
 * @since 25 October 2013
 * Description: This activity displays a list of topics that the user can select for the quiz.
 */
public class TopicActivity extends Activity {
	
	//Declare variables
	ArrayList<String>topics = new ArrayList<String>();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
