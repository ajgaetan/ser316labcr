/* 
   File: Savings.java
   Author: SER 316, Damien Raske II, Adam Gaetano
   Date: February 20 2017
   
   Description: Contains the data stored in a Savings account and manages what can be done with that data.
 */

package banking.primitive.core;

/**
  Class: Savings

  Description: Account class for Savings account implementation
*/
public class Savings extends Account {
	private static final long serialVersionUID = 111L;
	private int numWithdraws = 0;

	/**
	   Method:	Savings
	   Inputs:	String
	   Returns:	void
	   
	   Description: Calls the Account(String) constructor.
	 */
	public Savings(String name) {
		super(name);
	}

	/**
	   Method:	Savings
	   Inputs:	String, float
	   Returns:	void
	   
	   Description: Calls the Account(String, float) constructor.
	 */
	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	/**
	   Method:	deposit
	   Inputs:	float
	   Returns:	boolean
	   
	   Description: If the state is not CLOSED and the amount being deposited it greater than 0, then the amount is deposited. 50 cents 
	   is charged for each deposit. If the deposit brings the account balance over 0 then the account state is set to OPEN.
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount - 0.50F;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
		}
		return false;
	}

	/**
	   Method:	withdraw
	   Inputs:	float
	   Returns:	boolean
	   
	   Description: If the account is open and the amount is greater than 0, then the amount is withdrawn. After 3 withdrawals a $1 fee is 
	   charged per withdrawal. If the withdrawal being the balance below zero the account is changed to OVERDRAWN.
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > 3)
				balance = balance - 1.0f;
			// KG BVA: should be < 0
			if (balance <= 0.0f) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}

	public String getType() { return "Checking"; }

	/**
	   Method:	toString
	   Inputs:	void
	   Returns:	String
	   
	   Description: Returns a String representation of the Savings account.
	 */
	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
}
