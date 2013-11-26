package com.sjsu.siquoia;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.sjsu.siquoia.model.Question;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SiQuoiaQuestionActivity extends Activity 
{
	private ListView myListView;
    private static ArrayAdapter<String> myAdapter;
    private Question selectedQuestion;
    private int correctAnswer;
    private TextView questionText;
	private SharedPreferences preferences;
	private final String UPDATE_ANSWERS_URL ="http://ec2-54-201-65-140.us-west-2.compute.amazonaws.com/updateCurrentAns.php";
        
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_layout);
		
		//retrieves the questions
		Intent intent = getIntent();
		selectedQuestion = (Question) intent.getSerializableExtra("question");
		final int selectedPosition = intent.getIntExtra( "selectedPosition",101);
		correctAnswer = selectedQuestion.getCorrectChoice();
		
		//gets the listview
		myListView = (ListView) findViewById(R.id.answerList);
	    myAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, android.R.id.text1);
        
	    //Sets the question textview
	    questionText = (TextView) findViewById(R.id.questionText);
	    questionText.setText(selectedQuestion.getQuestion());
	    
	    //sets the answers choices list and adds them to the adapter
	    List<String> choices= selectedQuestion.getChoices()	;    
	    int size =choices.size();
	    for(int i=0;i<size;i++)
	    {
	    	myAdapter.add(choices.get(i).toString());
	    }
        myListView.setAdapter(myAdapter);;
        
        //OnClickListener is set for the list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {           
			

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// intent returns the chosen answer to the callign activity
				Intent intent=new Intent();	
				
				//get current answers
		        preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);
		        String currentAnswers = preferences.getString(SiQuoiaHomeActivity.ANSWERS, "");

		        //update current answers
				SharedPreferences.Editor perferenceUpdater = preferences.edit();
				
				if(position==correctAnswer)
				{
					selectedQuestion.setStatus(Question.CORRECT);
					currentAnswers =currentAnswers.concat(""+Question.CORRECT);
					perferenceUpdater.putString(SiQuoiaHomeActivity.ANSWERS, currentAnswers);
				}
				else
				{
					selectedQuestion.setStatus(Question.INCORRECT);
					currentAnswers =currentAnswers.concat(""+Question.INCORRECT);
					perferenceUpdater.putString(SiQuoiaHomeActivity.ANSWERS, currentAnswers);
				}
				
				//commit preference changes
				perferenceUpdater.commit();
				
				new SiQuoiaUpdateAnswerTask().execute(preferences.getString(SiQuoiaHomeActivity.EMAIL,""),currentAnswers);
				
				if(position==correctAnswer)
					intent.putExtra("chosenAnswer", 1);
				else
					intent.putExtra("chosenAnswer", 2);
			    intent.putExtra("position", selectedPosition);
			    setResult(RESULT_OK, intent);
			    finish();
				
			}
        });
	}
	
	public void updateAnswers(String email, String answers)
    {
    	//variables declared
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost(UPDATE_ANSWERS_URL);
    	
    	try {
    		//add user information to post
        	List<NameValuePair> data = new ArrayList<NameValuePair>(1); 
           	
        	data.add(new BasicNameValuePair(SiQuoiaHomeActivity.EMAIL,email));
        	data.add(new BasicNameValuePair(SiQuoiaHomeActivity.ANSWERS,answers));
			httppost.setEntity(new UrlEncodedFormEntity(data));
			
			//HttpResponse response = httpclient.execute(httppost);
			
			httpclient.execute(httppost);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
	
	 /**
     * This is the background task that will get the user's current information from the database.
     * @author Parnit Sainion
     *
     */
    class SiQuoiaUpdateAnswerTask extends AsyncTask<String, String, String>
    {
    	
    	@Override
		protected String doInBackground(String... input) {
    		//input[0] = username
			updateAnswers(input[0], input[1]);
			Log.i("user", input[0]);
			Log.i("ans", input[1]);
			return "";
		}
		
		protected void onPostExecute(String result) {
		
				//get preferences
				//SharedPreferences preferences = getSharedPreferences(SiQuoiaHomeActivity.SIQUOIA_PREF, 0);
				
				//update user info
				//SharedPreferences.Editor perferenceUpdater = preferences.edit();
				
				//commit preference changes
				//perferenceUpdater.commit();				
		}    	
    }
}
