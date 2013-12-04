package com.sjsu.siquoia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author Parnit Sainion
 * @since 3 December 2013
 * Description: This class allows user to input information for a new question and send it out to the admin team
 * 				to authenticate and add to the question bank.
 */
public class SubmitQuestionActivity extends Activity {

	//declare variables
	private EditText question, answerOne, answerTwo, answerThree, answerFour, correctAnswer, subject,topic, subtopic;	
	private Button submitButton;
	private final String[] EMAIL = {"siquoiaquiz@gmail.com"};
	private final String SUBJECT = "SiQuoia User Submitted Question";
	
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.submit_question);
			
			//get edittexts and button
			question = (EditText) findViewById(R.id.questionText);
			answerOne = (EditText) findViewById(R.id.answerOne);
			answerTwo = (EditText) findViewById(R.id.answerTwo);
			answerThree = (EditText) findViewById(R.id.answerThree);
			answerFour = (EditText) findViewById(R.id.answerFour);
			correctAnswer = (EditText) findViewById(R.id.correctAnswer);
			subject = (EditText) findViewById(R.id.subject);
			topic = (EditText) findViewById(R.id.topic);
			subtopic = (EditText) findViewById(R.id.subtopic);
			submitButton = (Button) findViewById(R.id.submitButton);
			
			//set on click listener
			submitButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					
					//generate the user message
					String message = generateMessage();
					
					if(!message.equals(""))
					{ //if message is not null,use user's default email client to send email
						Intent intent = new Intent(Intent.ACTION_SEND);
						intent.setType("message/rfc822");
						intent.putExtra(Intent.EXTRA_EMAIL, EMAIL);
						intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
						intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(message)).toString();
						startActivity(intent);
												
						//finish this activity
						finish();
					}					
					else{
						Toast toast = Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT);
						toast.show();
					}					
				}				
			});
	}
	
	
	/**
	 * @return the message creating the user's input
	 */
	public String generateMessage(){
		StringBuffer message = new StringBuffer();
		String questionString, a1, a2, a3, a4, correctA, subjectText, topicText,subtopicText;
		
		//get user input
		questionString =question.getText().toString().trim();
		a1 = answerOne.getText().toString().trim();
		a2 = answerTwo.getText().toString().trim();
		a3 = answerThree.getText().toString().trim();
		a4 = answerFour.getText().toString().trim();
		correctA =correctAnswer.getText().toString().trim();
		subjectText =subject.getText().toString().trim();
		topicText = topic.getText().toString().trim();
		subtopicText = subtopic.getText().toString().trim();
		
		//if all messages are  filled
		if(!questionString.equals("") &&  !a1.equals("") &&  !a2.equals("") &&  !a3.equals("") &&  !a4.equals("") &&  !correctA.equals("") &&  !subjectText.equals("") &&  !topicText.equals("") &&  !subtopicText.equals(""))
		{
			message.append("<html><body>");
			message.append("<p>Question: "+ questionString +"</p>");
			message.append("<p>Answer One: "+ a1 +"</p>");
			message.append("<p>Answer Two: "+ a2 +"</p>");
			message.append("<p>Answer Three: "+ a3 +"</p>");
			message.append("<p>Answer Four: "+ a4 +"</p>");
			message.append("<p>Correct Answer: "+ correctA +"</p>");
			message.append("<p>Subject: "+ subjectText +"</p>");
			message.append("<p>Topic: "+ topicText +"</p>");
			message.append("<p>Subtopic: "+ subtopicText +"</p>");
			
			message.append("</body></html>");
		}
		

		return message.toString();		
	}

}
