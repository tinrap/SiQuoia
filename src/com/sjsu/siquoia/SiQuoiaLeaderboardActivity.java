package com.sjsu.siquoia;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.sjsu.siquoia.adapters.SiQuoiaLeaderboardAdapter;
import com.sjsu.siquoia.model.Question;
import com.sjsu.siquoia.model.SiQuoiaJSONParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

/**
 * 
 * @author Parnit Sainion
 * @since 8 December 2013
 * Description: This activity displays the top 20 correctly answered questions.
 */
public class SiQuoiaLeaderboardActivity extends Activity {

	private ProgressDialog progressBar;
	private ArrayList<Question> questions;
	private SiQuoiaLeaderboardAdapter leaderboardAdapter;
	private ListView leaderboardList;
	private final String LEADERBOARD_URL = "http://ec2-54-201-65-140.us-west-2.compute.amazonaws.com/getLeaderboard.php";
	
		
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.leaderboard_list);
	        
	        //initialize variables
	        questions = new ArrayList<Question>(); 
	        
	        //get leader-board from database
	        new SiQuoiaGetLeaderboardTask().execute();	       
		}
		
		/**
		 * @return JSON contain leader board for questions
		 */
	   public String getLeaderboard()
	    {
	    	//variables declared
	    	String message ="";
	    	HttpClient httpclient = new DefaultHttpClient();
	    	HttpPost httppost = new HttpPost(LEADERBOARD_URL);
	    	
	    	try {		    		
				//set up response handler and execute post
				ResponseHandler<String> handler = new BasicResponseHandler();
				message = httpclient.execute(httppost,handler);
				
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
	    	return message;
	    }
		
		   /**
		    * 
		    * @author Parnit Sainion
		    * @since 28 November 2013
		    * Description: This background task gets the top 20 questions from database
		    */
		class SiQuoiaGetLeaderboardTask extends AsyncTask<String, String, String>
	    {
	    	@Override
			protected void onPreExecute() {
				//create the progress dialog and display it
	    		progressBar = new ProgressDialog(SiQuoiaLeaderboardActivity.this);
				progressBar.setIndeterminate(true);
				progressBar.setCancelable(false);
				progressBar.setMessage("Getting Leaderboard.");
				progressBar.show();			
			}
	    	
	    	@Override
			protected String doInBackground(String... input) {
				return getLeaderboard();
			}
			
			protected void onPostExecute(String result) {
					
					//parse leader board JSON
					questions = SiQuoiaJSONParser.parseLeaderboard(result);
					
					//sets my listAdapter to the list
			        leaderboardAdapter = new SiQuoiaLeaderboardAdapter(SiQuoiaLeaderboardActivity.this,R.layout.leaderboard_list,questions);
			        leaderboardList = (ListView) findViewById(R.id.leaderboardList);
			        leaderboardList.setAdapter(leaderboardAdapter); 
					
					//close progress dialog
					progressBar.dismiss();
			}    	
	    }
}
