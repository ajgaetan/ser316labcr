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

	protected static AccountServerFactory _singleton = null;

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (_singleton == null) {
			_singleton = new AccountServerFactory();
		}

		return _singleton;
	}

	public AccountServer lookup() {
		return new ServerSolution();
	}
}
