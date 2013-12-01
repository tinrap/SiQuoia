package com.sjsu.siquoia;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class QuizResultsActivity extends Activity {

	private int numberCorrect;
	TextView numberCorrectText, percentCorrect, totalPoints;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_results);
				
		//get TextViews
		numberCorrectText = (TextView) findViewById(R.id.numberCorrect);
		percentCorrect = (TextView) findViewById(R.id.percentCorrect);
		totalPoints = (TextView) findViewById(R.id.totalPoints);
		
		//get Intent and number correct
		Intent intent = getIntent();
		numberCorrect = intent.getIntExtra(SiQuoiaHomeActivity.CURRENT_SCORE, 0);
		
		//set Text
		numberCorrectText.setText(numberCorrect+"/20");
		percentCorrect.setText(((numberCorrect*100)/20) +"%");
		totalPoints.setText(numberCorrect+"");
		
		
				
	}

}
