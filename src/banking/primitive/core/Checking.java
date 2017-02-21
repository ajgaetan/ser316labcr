/* 
   File: Checking.java
   Author: SER 316, Damien Raske II, Adam Gaetano
   Date: February 20 2017
  
   Description: Contains the data stored in a Checking account and manages what can be done with that data.
 */

package banking.primitive.core;

/**
  Class: Checking

  Description: Account class for Checking account implementation
*/
public class Checking extends Account {

	private static final long serialVersionUID = 11L;
	private int numWithdraws = 0;

	/**
	   Method:	Checking
	   Inputs:	String
	   Returns:	void
	   
	   Description: Calls the Account(String) constructor.
	 */
	private Checking(String name) {
		super(name);
	}

	/**
	   Method:	createChecking
	   Inputs:	String
	   Returns:	Checking
	   
	   Description: Returns a new Checking instance with the same name.
	 */
    public static Checking createChecking(String name) {
        return new Checking(name);
    }

    /**
	   Method:	Checking
	   Inputs:	String, float
	   Returns:	void
	   
	   Description: Calls the Account(String, float) constructor.
	 */
	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	   Method:	deposit
	   Inputs:	float
	   Returns:	boolean
	   
	   Description: If the state is not CLOSED and the amount being deposited is greater than 0, then the amount is deposited into the 
	   account. If the deposit brings the account balance over 0 then the state is changed to OPEN.
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	   Method:	withdraw
	   Inputs:	float
	   Returns:	boolean
	   
	   Description: If the state is OPEN or OVERDRAWN with a balance greater than -100, then the amount is withdrawn. After 
	   10 withdrawals a $2 fee is added to each future withdrawal.
	 */
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > 10)
					balance = balance - 2.0f;
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	public String getType() { return "Checking"; }

	/**
	   Method:	toString
	   Inputs:	void
	   Returns:	String
	   
	   Description: Returns a String describing the Checking account.
	 */
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
}
