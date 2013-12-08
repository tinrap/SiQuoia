package com.sjsu.siquoia;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


/**
 * @author Parnit Sainion
 * @since 8 December 2013
 * Description: This activity displays the quiz results of a completed quiz.
 *
 */
public class QuizResultsActivity extends Activity {

	//declare variables
	private int numberCorrect;
	private TextView numberCorrectText, percentCorrect, totalPoints;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setup view
		setContentView(R.layout.quiz_results);
				
		//get TextViews
		numberCorrectText = (TextView) findViewById(R.id.numberCorrect);
		percentCorrect = (TextView) findViewById(R.id.percentCorrect);
		totalPoints = (TextView) findViewById(R.id.totalPoints);
		
		//get Intent and number correct
		Intent intent = getIntent();
		numberCorrect = intent.getIntExtra(SiQuoiaHomeActivity.CURRENT_SCORE, 0);
		
		//set number correct, percent correct, and point earned text views 
		numberCorrectText.setText(numberCorrect+"/20");
		percentCorrect.setText(((numberCorrect*100)/20) +"%");
		totalPoints.setText(numberCorrect+"");		
	}
}
