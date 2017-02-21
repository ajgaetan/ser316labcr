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
    private static final long _serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float _balance = 0.0F;
    protected String _name;
    private State _state;

    /**
	   Method:	Account
	   Inputs:	String
	   Returns:	void
	   
	   Description: Sets the variable name to a given name and sets the State to OPEN.
	 */
    protected Account(String n) {
        _name = n;
        _state = State.OPEN;
    }
    
    /**
	   Method:	Account
	   Inputs:	String, float
	   Returns:	void
	   
	   Description: Calls the Account(String n) constructor and sets the balance equal to b.
	 */
    protected Account(String n, float b) {
        this(n);
        _balance = b;
    }

    public final String getName() {
        return _name;
    }

    public final float getBalance() {
        return _balance;
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

    protected final State _getState() {
        return _state;
    }

    protected final void _setState(State s) {
        _state = s;
    }

    /**
	   Method:	toString
	   Inputs:	void
	   Returns:	String
	   
	   Description: Returns a String describing the account.
	 */
    public String toString() {
        return "Account " + _name + " has $" + _balance + "and is " + _getState()
                + "\n";
    }
}
