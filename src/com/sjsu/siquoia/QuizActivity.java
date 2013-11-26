/**
 * 
 */
package com.sjsu.siquoia;

import java.util.ArrayList;

import com.sjsu.siquoia.adapters.SiQuoiaQuizAdapter;
import com.sjsu.siquoia.model.Question;
import com.sjsu.siquoia.model.SiQuoiaJSONParser;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * @author Parnit Sainion
 * @since 25 October 2013
 * Description: This activity displays a list of questions the use can select to answer
 */
public class QuizActivity extends Activity {

	//variable
	private ArrayList<Question> quiz;
	private SiQuoiaQuizAdapter quizAdapter;
	private ListView quizList;
	private Question selectedQuestion;
	private int selectedPosition;
	

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_list);
		
		//get preferences
		SharedPreferences preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);
		
		String quizJson = preferences.getString(SiQuoiaHomeActivity.QUIZ, "");
		String currentAnswers = preferences.getString(SiQuoiaHomeActivity.ANSWERS, "");
		quiz = SiQuoiaJSONParser.parseQuiz(quizJson,currentAnswers);
		Log.i("quiz", currentAnswers);
		//sets my listAdapter to the list
		quizAdapter = new SiQuoiaQuizAdapter(this,R.layout.quiz_row,quiz);
		quizList = (ListView) findViewById(R.id.quizList);
		quizList.setAdapter(quizAdapter);
		  
        //Creates the click listener
		quizList.setOnItemClickListener(new AdapterView.OnItemClickListener() {           
		

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) 
			{

				//gets the question at the clicked position
				selectedQuestion = (Question) quizAdapter.getItem(position);
				selectedPosition = position;
				
				//checks if the question has not been answered
				if(selectedQuestion.getAnswered() == Question.UNANSWERED)
				{
					Intent intent = new Intent(QuizActivity.this, SiQuoiaQuestionActivity.class);
					intent.putExtra("question", selectedQuestion);
					intent.putExtra("selectedPosition", selectedPosition);
					final int intentMarker = 0;
					startActivityForResult(intent, intentMarker);
					Log.i("selectedQuestion", selectedQuestion.getQuestion());
				}
				
			}
        });
	}
	
	@Override
    public void onActivityResult(int requestCode,int resultCode, Intent data)
    {
		super.onActivityResult(requestCode, resultCode, data);

		//checks if data is not null (in case user pressed back)
		if(data != null)
		{
			int answer = data.getIntExtra("chosenAnswer", 3);   
	     	int position =data.getIntExtra("position", 101);
	     	
	     	if(position != 101)
	     	{
	     		//gets the question that was answered from list of questions
	         	Question question = quiz.get(position);
	         	
	         	if(question.getStatus() == Question.CORRECT)
	         	{
	         		// update number correct
	         	}
	         	
	         	//changes the status of question to correct or incorrect
	         	question.setStatus(answer);
	         	
	         	//updates GUI to reflect changes
	         	quizAdapter.notifyDataSetChanged();
	     	}
		}		
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
