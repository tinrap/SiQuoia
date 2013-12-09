package com.sjsu.siquoia.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.sjsu.siquoia.R;
import com.sjsu.siquoia.model.Question;

/** 
 * @author Parnit Sainion
 * @since 8 December 2013
 * Description: This adapter takes an arraylist of questions and sets them to the listview for the quiz
 */
public class SiQuoiaQuizAdapter extends ArrayAdapter<Question>
{
	private ArrayList<Question> quiz;
	
	public SiQuoiaQuizAdapter(Context context, int textViewResourceId, ArrayList<Question> quiz)
	{
		super(context,textViewResourceId, quiz);
		this.quiz = quiz; 		
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		
		
		if(v == null)
		{
			//inflate new question row
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.quiz_row, null, false);
		}  		 		
		
		//get Question object
		Question question = quiz.get(position);
		
		if(question!= null)
		{
			//sets the question text
			TextView text = (TextView) v.findViewById(R.id.questionTitle);
			text.setText(question.getTitle());
			
			//gets the question's answered status
			int answered = question.getAnswered();
			
			//sets rows background based on question's status
			switch(answered){
				case Question.CORRECT:
					v.setBackgroundColor(Color.GREEN);
					break;
				case Question.INCORRECT:
					v.setBackgroundColor(Color.RED);
					break;
				default:
					v.setBackgroundColor(Color.WHITE);
					break;
			}
		}
		
		return v;
	}
}
