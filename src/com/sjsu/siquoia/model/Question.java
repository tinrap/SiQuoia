package com.sjsu.siquoia.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Parnit Sainion
 * @since 19 November 2013
 * Class is used to create a question object with its question, answer choices, correct answer, and the current status of 
 * whether it was answer or not.
 */
public class Question implements Serializable{	
	
	//variable declared	
	private static final long serialVersionUID = -5638936389786689366L;
	private String title;
	private String text;
	private ArrayList<String> choices = new ArrayList<String>();
	private int correctChoice;
	private int status;
	private int questionId;
	private int rank;
	public static final int UNANSWERED = 0;
	public static final int CORRECT = 1;	
	public static final int INCORRECT = 2;

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
	     * @param title Question's title
	     */
	    public void setTitle(String title)
	    {
	    	this.title = title;
	    }
	    
	    /**
	     * @return title title for this object
	     */
	    public String getTitle()
	    {
	    	return title;
	    }
	    
	    /**
	     * @param text Question text
	     */
	    public void setQuestion(String text)
	    {
	    	this.text = text;
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
	     * 0 = unanswered, 1 = correct, 2 = incorrect
	     * @return status of the question
	     */
	    public int getStatus ()
	    {
	    	return status;
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
	     * sets the ranks of the question
	     * @param rank rank of question
	     */
	    public void setRank(int rank)
	    {
	    	this.rank = rank;
	    }
	    
	    /**
	     * @return rank of question
	     */
	    public int getRank()
	    {
	    	return rank;
	    }
	    
	    
	    /**
	     * @return question text of this object
	     */
	    public String toString()
	    {
	    	return text;
	    }


}
