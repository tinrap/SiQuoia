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
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
	private String currentAnswers;
	private SharedPreferences preferences;
	private int numberCorrect;
	private TextView currentScoreTextView;
	

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_list);
		
		currentScoreTextView = (TextView) findViewById(R.id.currentScore);
		
		//get preferences
		preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);
		
		String quizJson = preferences.getString(SiQuoiaHomeActivity.QUIZ, "");
		currentAnswers = preferences.getString(SiQuoiaHomeActivity.ANSWERS, "");
		
		//set number of  correct ANswers
		setNumberCorrect();
		currentScoreTextView.setText("Current Score: "+numberCorrect+"/20");
		currentScoreTextView.refreshDrawableState();
		
		
		
		quiz = SiQuoiaJSONParser.parseQuiz(quizJson,currentAnswers);
		
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
				int nextQuestion = currentAnswers.length();
				
				if(selectedQuestion.getAnswered() == Question.UNANSWERED)
				{					
					if(nextQuestion != position) //check if selected question is the next question
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Please Select Question " + ++nextQuestion, Toast.LENGTH_SHORT);
						toast.show();
					}
					else{
						//checks if the question has not been answered
						Intent intent = new Intent(QuizActivity.this, SiQuoiaQuestionActivity.class);
						intent.putExtra("question", selectedQuestion);
						intent.putExtra("selectedPosition", selectedPosition);
						intent.putExtra(SiQuoiaHomeActivity.CURRENT_SCORE, numberCorrect);
						final int intentMarker = 0;
						startActivityForResult(intent, intentMarker);
					}
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
			currentAnswers = preferences.getString(SiQuoiaHomeActivity.ANSWERS, "");
	     	
	     	if(position != 101)
	     	{
	     		//gets the question that was answered from list of questions
	         	Question question = quiz.get(position);
	         	
	         	//changes the status of question to correct or incorrect
	         	question.setStatus(answer);
	         	
	         	System.out.println(question.getAnswered() == Question.CORRECT);
	         	if(question.getAnswered() == Question.CORRECT)
	         	{
	         		//increment current score and change text
	        		currentScoreTextView.setText("Current Score: "+ ++numberCorrect + "/20");
	        		currentScoreTextView.refreshDrawableState();
	         	}
	         	
	         	//updates GUI to reflect changes
	         	quizAdapter.notifyDataSetChanged();
	     	}
		}		
    }
	
	/**
	 * Sets the number of correct answers based on Users current Answers
	 */
	public void setNumberCorrect()
	{
		numberCorrect = 0;
		int size = currentAnswers.length();
		String correct = Question.CORRECT + "";
		
		for(int count = 0; count < size ; count++ )
		{			
			if(correct.equals(currentAnswers.charAt(count)+""))
			{
				numberCorrect++;
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
