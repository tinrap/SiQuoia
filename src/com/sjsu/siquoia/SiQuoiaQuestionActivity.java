package com.sjsu.siquoia;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
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
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * 
 * @author Parnit Sainion
 * @since 8 December 2013
 *  Description: This activity displays the question for the user to answer.
 */
public class SiQuoiaQuestionActivity extends Activity implements MediaController.MediaPlayerControl
{
	private ListView myListView;
    private static ArrayAdapter<String> myAdapter;
    private Question selectedQuestion;
    private int correctAnswer, currentScore;
    private TextView questionText, currentScoreTextView;
	private SharedPreferences preferences;
	private ProgressDialog progressDialog;
	private final String UPDATE_ANSWERS_URL ="http://ec2-54-201-65-140.us-west-2.compute.amazonaws.com/updateCurrentAns.php";
	private final String UPDATE_RANK_URL ="http://ec2-54-201-65-140.us-west-2.compute.amazonaws.com/updateRank.php";
        
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//retrieves the questions
		Intent intent = getIntent();
		selectedQuestion = (Question) intent.getSerializableExtra("question");
		currentScore =  intent.getIntExtra(SiQuoiaHomeActivity.CURRENT_SCORE,0);
		final int selectedPosition = intent.getIntExtra( "selectedPosition",101);
		correctAnswer = selectedQuestion.getCorrectChoice();
		
		//get question text
		String question = selectedQuestion.getQuestion();
		
		//set layout based on type of question
		if(question.contains(".png")) //for picture based question
		{
			//set layout to image layout
			setContentView(R.layout.picture_question_layout);	
			
			new SiQuoiaDownloadImageTask().execute(question);				
		}
		else if(question.contains(".mp4")||question.contains(".mp3")) //for video and audio question
		{
			//set layout to video layout
			setContentView(R.layout.video_question_layout);
			
			if(question.contains(".mp3"))
			{
				 questionText = (TextView) findViewById(R.id.questionText);
				 questionText.setText("Answer Based On Audio.");	
			}
			
			//get videoview
			VideoView videoView = (VideoView) findViewById(R.id.videoView);

			//set media controls
			MediaController mediaController = new MediaController(this);
			mediaController.setAnchorView(videoView);
			videoView.setMediaController(mediaController);
			
			//set link to video	
			Uri videoLink = Uri.parse(question);
			videoView.setVideoURI(videoLink);
			
			videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
				@Override
				public void onPrepared(MediaPlayer mp) {
					ProgressBar loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
					loadingBar.setVisibility(View.INVISIBLE);					
				}				
			});			

			//start video
			videoView.start();
		}	
		else //for regular text based questions
		{
			//set layout to question text
			setContentView(R.layout.question_layout);

		    //Sets the question textview
		    questionText = (TextView) findViewById(R.id.questionText);
		    questionText.setText(selectedQuestion.getQuestion());			    
		}
		
		//set current Score
		currentScoreTextView = (TextView) findViewById(R.id.currentScore);
		currentScoreTextView.setText("Current Score: " + currentScore + "/20");		
		
		//set current question number
		TextView questionNumber = (TextView) findViewById(R.id.questionNum);
		questionNumber.setText("Question "+ (selectedPosition + 1) +":");
				
		//get the listview
		myListView = (ListView) findViewById(R.id.answerList);
	    myAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, android.R.id.text1);
        
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
				
				if(position==correctAnswer) //if user chose correct answer
				{
					selectedQuestion.setStatus(Question.CORRECT);
					currentAnswers =currentAnswers.concat(""+Question.CORRECT);
					perferenceUpdater.putString(SiQuoiaHomeActivity.ANSWERS, currentAnswers);
					intent.putExtra("chosenAnswer", 1);
					
					//if the packet type is normal, update question rank
					//if(preferences.getString(SiQuoiaHomeActivity.PACKET_TYPE, SiQuoiaHomeActivity.NORMAL).equals(SiQuoiaHomeActivity.NORMAL))
					//	new SiQuoiaUpdateTask().execute(SiQuoiaHomeActivity.QUESTION_TEXT, selectedQuestion.getQuestion());
					
				}
				else //incorrect answer
				{
					selectedQuestion.setStatus(Question.INCORRECT);
					currentAnswers =currentAnswers.concat(""+Question.INCORRECT);
					perferenceUpdater.putString(SiQuoiaHomeActivity.ANSWERS, currentAnswers);
					intent.putExtra("chosenAnswer", 2);
				}
				
				//commit preference changes
				perferenceUpdater.commit();
				
				//update users answer in db
				//new SiQuoiaUpdateTask().execute(SiQuoiaHomeActivity.ANSWERS, preferences.getString(SiQuoiaHomeActivity.EMAIL,""),currentAnswers);
				
				//prepare intent to send back to home activity
			    intent.putExtra("position", selectedPosition);
			    setResult(RESULT_OK, intent);
			    finish();				
			}
        });
	}
	
	/**
	 * update users answers on database
	 * @param email email of user
	 * @param answers new answers of user
	 */
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
	 * updates rank of question on db
	 * @param questionText question whose rank needs to be updated
	 */
	public void updateQuestionRank(String questionText)
    {
    	//variables declared
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost(UPDATE_RANK_URL);
    	
    	try {
    		//add user information to post
        	List<NameValuePair> data = new ArrayList<NameValuePair>(1); 
           	
        	data.add(new BasicNameValuePair(SiQuoiaHomeActivity.QUESTION_TEXT,questionText));
			httppost.setEntity(new UrlEncodedFormEntity(data));
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
    class SiQuoiaUpdateTask extends AsyncTask<String, String, String>
    {
    	
    	@Override
		protected String doInBackground(String... input) {
    		if(input[0].equals(SiQuoiaHomeActivity.ANSWERS))
    			updateAnswers(input[1], input[2]);
    		else if(input[0].equals(SiQuoiaHomeActivity.QUESTION_TEXT))
    			updateQuestionRank(input[1]);
			return "";
		} 	
    }
    
    /**
     * This is the background task that will get the user's current information from the database.
     * @author Parnit Sainion
     *
     */
    class SiQuoiaDownloadImageTask extends AsyncTask<String, String, String>
    {
    	Bitmap bitmap = null;
    	
    	@Override
		protected void onPreExecute() {
			//creates a progress dialog and displays it
    		progressDialog = new ProgressDialog(SiQuoiaQuestionActivity.this);
    		progressDialog.setIndeterminate(true);
    		progressDialog.setCancelable(false);
    		progressDialog.setMessage("Getting Image");
    		progressDialog.show();			
		}
    	
    	@Override
		protected String doInBackground(String... input) {    		
			try {
				URL url = new URL(input[0]);
				bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			return "";
		}
    	
    	protected void onPostExecute(String result) {
    		ImageView picture = (ImageView) findViewById(R.id.questionImage);
			if(bitmap != null)
				picture.setImageBitmap(bitmap);
			
			//dismiss progress dialog
			progressDialog.dismiss();
    	}
    }

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seekTo(int pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
}
