package com.sjsu.siquoia;

import java.util.ArrayList;

/**
 * @author Parnit Sainion
 * @since 28 September 2013
 * Class is used to create a question object with its question, answer choices, correct answer, and the current status of 
 * whether it was answer or not.
 *
 */
public class Question {
	
	private String text;
	private ArrayList<String> choices = new ArrayList<String>();
	private int correctChoice;
	private int status;
	private int questionId;
	protected static final int UNANSWERED = 0;
	protected static final int CORRECT = 1;	
	protected static final int INCORRECT = 2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * create an empty question object
	 */
	 public Question()
	    {
	    	//sets status to unanswered
	    	status = UNANSWERED;
	    }
	 
	 /**
	     * @return question text for this object
	     */
	    public String getQuestion()
	    {
	    	return text;
	    }
	    
	    /**
	     * Adds a possible answer to the list of choices
	     * @param choice possible answer to the question
	     */
	    public void addChoice(String choice)
	    {
	    	choices.add(choice);
	    }
	    
	    /**
	     * @param position position of choice required
	     * @return the answer choice for the desired position
	     */
	    public String getChoice(int position)
	    {
	    	return choices.get(position);
	    }
	    
	    /**
	     * @return number of choices possible for this question
	     */
	    public int numberOfChoices()
	    {
	    	return choices.size();
	    }
	    /**
	     * @return the list of choices for this question object
	     */
	    public ArrayList<String> getChoices()
	    {
	    	return choices;
	    }
	    
	    /**
	     * @return the position of the correct answer to this question
	     */
	    public int getCorrectChoice()
	    {
	    	return correctChoice;
	    }
	    
	    /**
	     * sets the correct answers position
	     * @param correctChoice position of the correct answer
	     */
	    public void setCorrectChoice(int correctChoice)
	    {
	    	this.correctChoice = correctChoice;
	    }
	   
	    /**
	     * sets whether the question has been answered. 
	     * 0 = unanswered, 1 = correct, 2 = incorrect
	     * @param num number indicating status of the question
	     */
	    public void setStatus(int num)
	    {
	    	
	    	status = num;
	    }
	    
	    /**
	     * @return status of question
	     */
	    public int getAnswered()
	    {
	    	return status;
	    }
	    
	    /**
	     * sets  id of the question
	     * @param id id of question
	     */
	    public void setQuestionId(int id)
	    {
	    	questionId = id;
	    }
	    
	    
	    /**
	     * @return question id
	     */
	    public int getQuestionId()
	    {
	    	return questionId;
	    }
	    
	    
	    /**
	     * @return question text of this object
	     */
	    public String toString()
	    {
	    	return text;
	    }


}
