/* Author: SER 316, Damien Raske II, Adam Gaetano
 * Date: February 20 2017
 *
 * Description: Contains the data stored in a Checking account and manages what can be done with that data.
 */

package banking.primitive.core;

/**
  Class: Checking

  Description: Account class for Checking account implementation
*/
public class Checking extends Account {

	private static final long _serialVersionUID = 11L;
	private int _numWithdraws = 0;

	private Checking(String name) {
		super(name);
	}

    public static Checking createChecking(String name) {
        return new Checking(name);
    }

	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	 * A deposit may be made unless the Checking account is closed
	 * @param float is the deposit amount
	 */
	public boolean deposit(float amount) {
		if (_getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount;
			if (balance >= 0.0f) {
				_setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * Withdrawal. After 10 withdrawals a fee of $2 is charged per transaction You may
	 * continue to withdraw an overdrawn account until the balance is below -$100
	 */
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {
			// KG: incorrect, last balance check should be >=
			if (_getState() == State.OPEN || (_getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				_numWithdraws++;
				if (_numWithdraws > 10)
					balance = balance - 2.0f;
				if (balance < 0.0f) {
					_setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	public String getType() { return "Checking"; }

	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
}
