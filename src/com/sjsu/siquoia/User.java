package com.sjsu.siquoia;

/**
 * @author Parnit Sainion
 * @since 25 October 2013
 * Class create a user object and store his information.
 */
public class User {

	private String email;	
	private int userId;
	private int siquoiaBucks;
	private String currentQuiz;
	private String currentAnswers;
	private int packetsBought;
	private int memorabiliaBought;
	private int totalPointsSpent;
	
	
	
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
	public void setSequoiaBUcks(int siquoiaBucks)
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
}
