/* Author: SER 316, Damien Raske II, Adam Gaetano
 * Date: February 20 2017
 * 
 * Description: Creates the serverSolution object which manages the banks accounts.
 */

package banking.primitive.core;


public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	public AccountServer lookup() {
		return new ServerSolution();
	}
}
