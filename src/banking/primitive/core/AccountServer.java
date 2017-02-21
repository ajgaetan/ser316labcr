/* 
   File: AccountServer.java
   Author: SER 316, Damien Raske II, Adam Gaetano
   Date: February 20 2017
   
   Description: Interface that details what a bank can do with its accounts.
 */

package banking.primitive.core;

import java.io.IOException;
import java.util.List;

/**
  Class: AccountServer

  Description: Interface to manage the Accounts in ServerSolution
*/
public interface AccountServer {
	/**
	   Method:	newAccount
	   Inputs:	String, String, float
	   Returns:	boolean
	   
	   Description: Declares a newAccount(String, String, float) method that throws and IllegalArgumentException.
	 */
	public boolean	newAccount(String type, String name, float balance) throws IllegalArgumentException;

	/**
	   Method:	closeAccount
	   Inputs:	String
	   Returns:	boolean
	   
	   Description: Declares a closeAccount(String) method.
	 */
	public boolean	closeAccount(String name);

	public Account	getAccount(String name);

	public List<Account> getAllAccounts();

	public List<Account> getActiveAccounts();

	/**
	   Method:	saveAccounts
	   Inputs:	void
	   Returns:	void
	   
	   Description: Declares a saveAccounts() method.
	 */
	public void	saveAccounts() throws IOException;
}
