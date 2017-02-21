/* 
   File: Account.java
   Author: SER 316, Damien Raske II, Adam Gaetano
   Date: February 20 2017
   
   Description: Abstract class to be implemented by classes of specific account types.
 */

package banking.primitive.core;


/**
  Class: Account
  
  Description: Abstract class for Account
*/
public abstract class Account implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float balance = 0.0F;
    protected String name;
    private State state;

    /**
	   Method:	Account
	   Inputs:	String
	   Returns:	void
	   
	   Description: Sets the variable name to a given name and sets the State to OPEN.
	 */
    protected Account(String n) {
        name = n;
        state = State.OPEN;
    }
    
    /**
	   Method:	Account
	   Inputs:	String, float
	   Returns:	void
	   
	   Description: Calls the Account(String n) constructor and sets the balance equal to b.
	 */
    protected Account(String n, float b) {
        this(n);
        balance = b;
    }

    public final String getName() {
        return name;
    }

    public final float getBalance() {
        return balance;
    }

    /**
	   Method:	deposit
	   Inputs:	float
	   Returns:	boolean
	   
	   Description: Declares an abstract method.
	 */
    public abstract boolean deposit(float amount);

    /**
	   Method:	withdraw
	   Inputs:	float
	   Returns:	boolean
	   
	   Description: Declares an abstract method.
	 */
    public abstract boolean withdraw(float amount);

    public abstract String getType();

    protected final State getState() {
        return state;
    }

    protected final void setState(State s) {
        state = s;
    }

    /**
	   Method:	toString
	   Inputs:	void
	   Returns:	String
	   
	   Description: Returns a String describing the account.
	 */
    public String toString() {
        return "Account " + name + " has $" + balance + "and is " + getState()
                + "\n";
    }
}
