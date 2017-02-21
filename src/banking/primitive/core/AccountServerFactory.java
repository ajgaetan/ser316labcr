/*
   File: AccountServerFactory.java
   Author: SER 316, Damien Raske II, Adam Gaetano
   Date: February 20 2017

    Description: Creates the serverSolution object which manages the banks accounts.
 */

package banking.primitive.core;

/**
  Class: AccountServerFactory

  Description: Creates and manages the AccountServer implementations
*/
public class AccountServerFactory {

	/**
	   Method:	AccountServerFactory
	   Inputs:	void
	   Returns:	void

	   Description: Default constructor that does nothing
	 */
	protected AccountServerFactory() {

	}

	/**
	   Method:	AccountServerFactory
	   Inputs:	void
	   Returns:	AccountServerFactory

	   Description: Returns the current instance
	 */
	public static AccountServerFactory getMe() {
		if (_singleton == null) {
			_singleton = new AccountServerFactory();
		}

		return _singleton;
	}

	/**
	   Method:	lookup
	   Inputs:	void
	   Returns:	ServerSolution

	   Description: Returns an instance of the server
	 */
	public AccountServer lookup() {
		return new ServerSolution();
	}

	protected static AccountServerFactory _singleton = null;

}
