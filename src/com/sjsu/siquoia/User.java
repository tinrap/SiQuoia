package com.sjsu.siquoia;

/**
 * @author Parnit Sainion
 * @since 19 September 2013
 * Class create a user object and store his information.
 */
public class User {

	private String name;
	private int userId;
	private int siquoiaBucks;
	private String answers;
	
	
	
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
	public String getName()
	{
		return name;
	}
	
	/**
	 * sets user's name
	 * @param name new name 
	 */
	public void setName(String name)
	{
		this.name = name;
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
		return answers;
	}
	
	/**
	 * sets the answers the  user has already answered for the quiz
	 * @param answers
	 */
	public void setAnswers(String answers)
	{
		this.answers = answers;
	}
}
