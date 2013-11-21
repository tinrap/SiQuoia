/**
 * 
 */
package com.sjsu.siquoia;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * @author parnit
 *
 */
public class NewQuizActivity extends Activity {
	
	//Declare variables
	private ArrayList<String> subjects, topics, subtopics;
	private Spinner subjectSpinner, topicSpinner, subtopicSpinner;
	private ArrayAdapter <String> subjectAdapter, topicAdapter, subtopicAdapter ;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);		
		//set view
		setContentView(R.layout.new_quiz_layout);
		
		subjects = new ArrayList<String>();
		topics = new ArrayList<String>();
		subtopics = new ArrayList<String>();
		
		//get different spinners
		subjectSpinner= (Spinner) findViewById(R.id.subjectSpinner);
		topicSpinner= (Spinner) findViewById(R.id.topicSpinner);
		subtopicSpinner= (Spinner) findViewById(R.id.subtopicSpinner);
		
		//add initial values to arrays
		subjects.add("All");
		subjects.add("none");
		topics.add("All");
		subtopics.add("All");
		
		//set adapters and listeners spinners
		setAdapters();
		setItemChangeListeners();		
	}
	
	/**
	 * Set on adapters for three spinners
	 */
	public void setAdapters()
	{
		//set adapters
		subjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjects);
		subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		subjectSpinner.setAdapter(subjectAdapter);
		
		topicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);
		topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		topicSpinner.setAdapter(topicAdapter);
		
		subtopicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subtopics);
		subtopicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		subtopicSpinner.setAdapter(subtopicAdapter);
	}
	
	/**
	 * Set on ItemChangeListener for three spinners
	 */
	public void setItemChangeListeners()
	{
		subjectSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(!subjectSpinner.getSelectedItem().toString().equalsIgnoreCase("All"))
				{
					topics.clear();
					topics.add("1");
				}			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}	
		});
		
		topicSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}			
		});
		
		subtopicSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}			
		});
	}

}
