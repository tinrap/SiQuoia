/**
 * 
 */
package com.sjsu.siquoia;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * @author Parnit Sainion
 * @since 25 October 2013
 * Description: This activity displays a list of questions the use can select to answer
 */
public class QuizActivity extends Activity {

	//variable
	ArrayList<Question> quiz;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
	}
	
	public void parseQuiz(String json)
	{
		int size;
		Question question;
		JSONObject questionJson;
		try {
			JSONObject jsonObj = new JSONObject(json);
			JSONArray jsonArray = jsonObj.getJSONArray("quiz");
			
			size = jsonArray.length();
			
			for( int count = 0; count < size; count++)
			{
				//initialize variables for a new question
				question = new Question();
				questionJson = jsonArray.getJSONObject(count);
				
				//get question fields
				question.setQuestion(questionJson.getString("text"));
				
				//add question to quiz
				quiz.add(question);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
