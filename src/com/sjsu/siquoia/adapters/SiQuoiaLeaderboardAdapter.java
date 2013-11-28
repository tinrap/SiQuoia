package com.sjsu.siquoia.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sjsu.siquoia.R;
import com.sjsu.siquoia.model.Question;

/** 
 * @author Parnit Sainion
 * @since 28 November 2013
 * Description: This adapter takes an arraylist of questions and sets them to the listview
 */
public class SiQuoiaLeaderboardAdapter extends ArrayAdapter<Question>
	{	
		//dec'are variable
		private ArrayList<Question> questions;
		
		public SiQuoiaLeaderboardAdapter(Context context, int textViewResourceId, ArrayList<Question> questions)
		{
			super(context,textViewResourceId, questions);
			this.questions = questions; 		
		}
		
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View v = convertView;
			
			
			if(v == null)
			{
				//inflate new question row
				LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.leaderboard_row, null, false);
			}  		 		
			
			//get Question object
			Question question = questions.get(position);
			
			if(question!= null)
			{
				//sets the question number
				TextView number = (TextView) v.findViewById(R.id.questionNumber);
				number.setText(question.getTitle());
				
				//sets the question text
				TextView text = (TextView) v.findViewById(R.id.questionText);
				text.setText(question.getQuestion());
				
				//sets the question rank
				TextView rank = (TextView) v.findViewById(R.id.rankText);
				rank.setText(question.getRank());
			}
			
			return v;
		}
	}