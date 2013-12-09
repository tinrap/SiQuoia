package com.sjsu.siquoia.model;

import java.io.Serializable;

/**
 * @author Parnit Sainion
 * @since 8 December 2013
 * Description: Class create a user object and store his information.
 */
public class User implements Serializable{

	//variables declared
	private static final long serialVersionUID = 397625286527811769L;
	private String email;	
	private int userId;
	private int siquoiaBucks;
	private String currentQuiz;
	private String currentAnswers;
	private int packetsBought;
	private int memorabiliaBought;
	private int totalPointsSpent;
	private String packetType;
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Create a empty user object
	 */
	public User()
	{
		
	}
	
	public User(String email)
	{
		this.email = email;
		currentQuiz = "";
		currentAnswers = "";
		packetsBought = 0 ;
		memorabiliaBought = 0;
		siquoiaBucks = 20;
		totalPointsSpent = 0;
	}
	
	/**
	 * @return user's name
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * sets user's name
	 * @param name new name 
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * @return user's id
	 */
	public int getUserId()
	{
		return userId;
	}
	
	/**
	 * sets user's id
	 * @param userId new user id
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	/**
	 * @return user's siquoia bucks value
	 */
	public int getSiquoiaBucks()
	{
		return siquoiaBucks;
	}
	
	/**
	 * sets user's siquoia bucks value
	 * @param siquoiaBucks
	 */
	public void setSequoiaBucks(int siquoiaBucks)
	{
		this.siquoiaBucks = siquoiaBucks;
	}
	
	
	/**
	 * @return a string of answers the users has already answered for the quiz
	 */
	public String getAnswers()
	{
		return currentAnswers;
	}
	
	/**
	 * Store current quiz user is taking. 
	 * @param quiz current quiz in JSON format
	 */
	public void setCurrentQuiz(String quiz)
	{
		this.currentQuiz = quiz;
	}
	
	/**
	 * @return current quiz user is taking
	 */
	public String getCurrentQuiz()
	{
		return currentQuiz;
	}
	
	/**
	 *  set the number of packets bought by user
	 * @param packetBought number of packets
	 */
	public void setPacketsBought( int packetsBought)
	{
		this.packetsBought = packetsBought;
	}
	
	/**
	 * @return the number of packets bought by user
	 */
	public int getPacketsBougth()
	{
		return packetsBought;
	}
	
	/**
	 *  set the number of packets bought by user
	 * @param packetBought number of packets
	 */
	public void setMemorabiliaBought( int memorabiliaBought)
	{
		this.memorabiliaBought = memorabiliaBought;
	}
	
	/**
	 * @return the number of packets bought by user
	 */
	public int getMemorabiliaBougth()
	{
		return memorabiliaBought;
	}
	
	/**
	 * sets number of points spent by user
	 * @param pointsSpent points spent by users
	 */
	public void setTotalPointsSpent(int pointsSpent)
	{
		this.totalPointsSpent = pointsSpent;
	}
	
	/**
	 * @return total number of points spent by user
	 */
	public int getTotalPointsSpent()
	{
		return totalPointsSpent;
	}
	
	
	/**
	 * sets the answers the  user has already answered for the quiz
	 * @param answers
	 */
	public void setAnswers(String currentAnswers)
	{
		this.currentAnswers = currentAnswers;
	}
	
	

	/**
	 * @return true if user has enough points to buys a quiz, else false
	 */	
	public boolean canBuyPacket()
	{
		if(siquoiaBucks <5)
			return false;
		else
			return true;
	}
	
	/**
	 * Users buys a packets using 5 of their points. 
	 */
	public void buyPacket()
	{
		siquoiaBucks -= 5;
		packetsBought++;		
	}
	
	/**
	 * set the packet type for current quiz
	 * @param packetType type of packet, branded or normal
	 */
	public void setPacketType(String packetType)
	{
		this.packetType = packetType;
	}
	
	/**
	 * @return packet type for user's current quiz
	 */
	public String getPacketType()
	{
		return packetType;
	}
}
