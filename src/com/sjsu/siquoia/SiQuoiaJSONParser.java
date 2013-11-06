/**
 * 
 */
package com.sjsu.siquoia;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Parnit Sainion
 * @since 5 November 2013
 * Description: This class obtains the JSON and parsers it accordingly for subjects,topics,subtopics or the quiz itself.
 */
public class SiQuoiaJSONParser {

	/**
	 * 
	 */
	public SiQuoiaJSONParser() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * parses JSON into quiz
	 * @param json JSON contain quiz
	 */
	public void parseQuiz(String json, ArrayList<Question> quiz)
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
