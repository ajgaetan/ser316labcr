/*
   File: Main.java 
   Author: SER 316, Damien Raske II, Adam Gaetano
   Date: February 20 2017
  
   Description: Contains the main method. Initializes construction of the JFrame.
 */

package banking.gui;

import javax.swing.JFrame;

/**
  Class: Main

  Description: Class for main method
*/
final class Main {
	/**
	   Method:	Main
	   Inputs:	void
	   Returns:	void
	   
	   Description: Who knows?
	 */
	private Main() {
	}

	/**
	   Method:	main
	   Inputs:	String[]
	   Returns:	void
	   
	   Description: Programs main method. Program exits if no argument is given. Initializes MainFrame with the given arg as a parameter.
	 */
	public static void main(final String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java FormMain <property file>");
			System.exit(1);
		}

		final String propertyFile = args[0];
		final JFrame frame = new MainFrame(propertyFile);
		frame.setVisible(true);

	}
}
