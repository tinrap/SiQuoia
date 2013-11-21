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
 * @since 20 November 2013
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
	 * parses JSON into User object
	 * @param json JSON contain user information
	 */
	public static User parseUser(String json)
	{
		User user = new User();
		JSONObject jsonObj = new JSONObject();
		
		System.out.println(json);
		try {		
			//convert JSON string to JSONArrya
			JSONArray jsonArray = new JSONArray(json);
			
			//get User JSON Object
			jsonObj = jsonArray.getJSONObject(0);
			
			//set user information
			user.setEmail(jsonObj.get("email").toString());
			user.setCurrentQuiz(jsonObj.getString("currentQuiz").toString());
			user.setAnswers(jsonObj.get("currentAns").toString());
			user.setSequoiaBucks(Integer.parseInt(jsonObj.getString("siquoiaPoints").toString()));
			user.setPacketsBought(Integer.parseInt(jsonObj.getString("packetsBought").toString()));
			user.setMemorabiliaBought(Integer.parseInt(jsonObj.getString("memorabilia").toString()));
			user.setTotalPointsSpent(Integer.parseInt(jsonObj.getString("totalPointsSpent").toString()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
