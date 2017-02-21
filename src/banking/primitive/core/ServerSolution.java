/* 
   File: ServerSolution.java
   Author: SER 316, Damien Raske II, Adam Gaetano
   Date: February 20 2017
   
   Description: Reads in all existing accounts from a file. Manages what a bank can do with its accounts by implements the AccountServer class interface.
 */

package banking.primitive.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

import banking.primitive.core.Account.State;

/**
  Class: ServerSolution

  Description: Manages the savings and checking accounts
*/
class ServerSolution implements AccountServer {

	static String fileName = "accounts.ser";

	Map<String,Account> accountMap = null;

	/**
	   Method:	ServerSolution
	   Inputs:	void
	   Returns:	void
	   
	   Description: Reads in the existing account from a file.
	 */
	public ServerSolution() {
		accountMap = new HashMap<String,Account>();
		File file = new File(fileName);
		ObjectInputStream in = null;
		try {
			if (file.exists()) {
				System.out.println("Reading from file " + fileName + "...");
				in = new ObjectInputStream(new FileInputStream(file));

				Integer sizeI = (Integer) in.readObject();
				int size = sizeI.intValue();
				for (int i=0; i < size; i++) {
					Account acc = (Account) in.readObject();
					if (acc != null)
						accountMap.put(acc.getName(), acc);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}

	/**
	   Method:	newAccountFactory
	   Inputs:	String, String, float
	   Returns:	boolean
	   
	   Description: Creates a new Account of the given type and adds it to the accountMap.
	 */
	private boolean newAccountFactory(String type, String name, float balance)
		throws IllegalArgumentException {

		if (accountMap.get(name) != null) return false;

		Account acc;
		if ("Checking".equals(type)) {
			acc = new Checking(name, balance);

		} else if ("Savings".equals(type)) {
			acc = new Savings(name, balance);

		} else {
			throw new IllegalArgumentException("Bad account type:" + type);
		}
		try {
			accountMap.put(acc.getName(), acc);
		} catch (Exception exc) {
			return false;
		}
		return true;
	}

	/**
	   Method:	newAccount
	   Inputs:	String, String, float
	   Returns:	boolean
	   
	   Description: Checks that the given balance is greater than 0. It then calls newAccountFactory(String, String, float).
	 */
	public boolean newAccount(String type, String name, float balance)
		throws IllegalArgumentException {

		if (balance < 0.0f) throw new IllegalArgumentException("New account may not be started with a negative balance");

		return newAccountFactory(type, name, balance);
	}

	/**
	   Method:	closeAccount
	   Inputs:	String
	   Returns:	boolean
	   
	   Description: Sets the state of an account with the given name to CLOSED.
	 */
	public boolean closeAccount(String name) {
		Account acc = accountMap.get(name);
		if (acc == null) {
			return false;
		}
		acc.setState(State.CLOSED);
		return true;
	}

	public Account getAccount(String name) {
		return accountMap.get(name);
	}

	public List<Account> getAllAccounts() {
		return new ArrayList<Account>(accountMap.values());
	}

	public List<Account> getActiveAccounts() {
		List<Account> result = new ArrayList<Account>();

		for (Account acc : accountMap.values()) {
			if (acc.getState() != State.CLOSED) {
				result.add(acc);
			}
		}
		return result;
	}

	/**
	   Method:	saveAccounts
	   Inputs:	void
	   Returns:	void
	   
	   Description: Saves all existing accounts to a file.
	 */
	public void saveAccounts() throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));

			out.writeObject(Integer.valueOf(accountMap.size()));
			for (int i=0; i < accountMap.size(); i++) {
				out.writeObject(accountMap.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Could not write file:" + fileName);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}

}
