/*
   File: MainFrame.java
   Author: SER 316, Damien Raske II, Adam Gaetano
   Date: February 20 2017

   Description: Manages the appearance and components of the JFrame.
 */

package banking.gui;

import banking.primitive.core.Account;
import banking.primitive.core.AccountServer;
import banking.primitive.core.AccountServerFactory;

import java.io.*;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

/**
  Class: MainFrame

  Description: Creates the GUI and holds the handler classes
*/
@SuppressWarnings("serial")
class MainFrame extends JFrame {

	/**
	   Method:	MainFrame
	   Inputs:	String
	   Returns:	void

	   Description: Initializes the server. Reads properties from the propertyFile String. Initializes construction of the JFrame.
	 */
	public MainFrame(String propertyFile) throws IOException {

		//** initialize myServer
		myServer = AccountServerFactory.getMe().lookup();

		props = new Properties();

		FileInputStream fis = null;
		try {
			fis =  new FileInputStream(propertyFile);
			props.load(fis);
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		_constructForm();
	}

	/**
	   Method:	constructForm
	   Inputs:	void
	   Returns:	void

	   Description: Sets up the JFrame components.
	 */
	private void _constructForm() {
		//*** Make these read from properties
		typeLabel		= new JLabel(props.getProperty("TypeLabel"));
		nameLabel		= new JLabel(props.getProperty("NameLabel"));
		balanceLabel	= new JLabel(props.getProperty("BalanceLabel"));

		Object[] accountTypes = {"Savings", "Checking"};
		typeOptions = new JComboBox(accountTypes);
		nameField = new JTextField(20);
		balanceField = new JTextField(20);

		newAccountButton = new JButton("New Account");
		JButton depositButton = new JButton("Deposit");
		JButton withdrawButton = new JButton("Withdraw");
		JButton saveButton = new JButton("Save Accounts");
		displayAccountsButton = new JButton("List Accounts");
		JButton displayAllAccountsButton = new JButton("All Accounts");

		this.addWindowListener(new FrameHandler());
		newAccountButton.addActionListener(new NewAccountHandler());
		displayAccountsButton.addActionListener(new DisplayHandler());
		displayAllAccountsButton.addActionListener(new DisplayHandler());
		depositButton.addActionListener(new DepositHandler());
		withdrawButton.addActionListener(new WithdrawHandler());
		saveButton.addActionListener(new SaveAccountsHandler());

		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());

		JPanel panel1 = new JPanel();
		panel1.add(typeLabel);
		panel1.add(typeOptions);

		JPanel panel2 = new JPanel();
		panel2.add(displayAccountsButton);
		panel2.add(displayAllAccountsButton);
		panel2.add(saveButton);

		JPanel panel3 = new JPanel();
		panel3.add(nameLabel);
		panel3.add(nameField);

		JPanel panel4 = new JPanel();
		panel4.add(balanceLabel);
		panel4.add(balanceField);

		JPanel panel5 = new JPanel();
		panel5.add(newAccountButton);
		panel5.add(depositButton);
		panel5.add(withdrawButton);

		pane.add(panel1);
		pane.add(panel2);
		pane.add(panel3);
		pane.add(panel4);
		pane.add(panel5);

		setSize(400, 250);
	}

	/**
	  Class: DisplayHandler

	  Description: Handler class for display button
	*/
	class DisplayHandler implements ActionListener {
		/**
		   Method:	actionPerformed
		   Inputs:	ActionEvent
		   Returns:	void

		   Description: If the JButton displayAccountsButton is pressed the active accounts are shown. Otherwise, all accounts are shown.
		 */
		public void actionPerformed(ActionEvent e) {
			List<Account> accounts = null;
			if (e.getSource() == displayAccountsButton) {
				accounts = myServer.getActiveAccounts();
			} else {
				accounts = myServer.getAllAccounts();
			}
			final StringBuffer SB = new StringBuffer();
			Account thisAcct = null;
			for (Iterator<Account> li = accounts.iterator(); li.hasNext();) {
				thisAcct = (Account)li.next();
				SB.append(thisAcct.toString()+"\n");
			}

			JOptionPane.showMessageDialog(null, SB.toString());
		}
	}

	/**
	  Class: NewAccountHandler

	  Description: Handler class for New Account button
	*/
	class NewAccountHandler implements ActionListener {
		/**
		   Method:	actionPerformed
		   Inputs:	ActionEvent
		   Returns:	void

		   Description: Creates a new account with the given name, balance, and type.
		 */
		public void actionPerformed(ActionEvent e) {
			final String TYPE = typeOptions.getSelectedItem().toString();
			final String NAME = nameField.getText();
			final String BALANCE = balanceField.getText();

			if (myServer.newAccount(TYPE, NAME, Float.parseFloat(BALANCE))) {
				JOptionPane.showMessageDialog(null, "Account created successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Account not created!");
			}
		}
	}

	/**
	  Class: SaveAccountsHandler

	  Description: Handler for Save Accounts button
	*/
	class SaveAccountsHandler implements ActionListener {
		/**
		   Method:	actionPerformed
		   Inputs:	ActionEvent
		   Returns:	void

		   Description: Saves the accounts.
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				myServer.saveAccounts();
				JOptionPane.showMessageDialog(null, "Accounts saved");
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(null, "Error saving accounts");
			}
		}
	}

	/**
	  Class: DepositHandler

	  Description: Handler for Deposit button
	*/
	class DepositHandler implements ActionListener {
		/**
		   Method:	actionPerformed
		   Inputs:	ActionEvent
		   Returns:	void

		   Description: Deposits a given amount into the account of a person with a given name.
		 */
		public void actionPerformed(ActionEvent e) {
			final String NAME = nameField.getText();
			final String BALANCE = balanceField.getText();
			final Account ACC = myServer.getAccount(NAME);
			if (ACC != null && ACC.deposit(Float.parseFloat(BALANCE))) {
				JOptionPane.showMessageDialog(null, "Deposit successful");
			} else {
				JOptionPane.showMessageDialog(null, "Deposit unsuccessful");
			}
		}
	}

	/**
	  Class: WithdrawHandler

	  Description: Handler for Withdraw button
	*/
	class WithdrawHandler implements ActionListener {
		/**
		   Method:	actionPerformed
		   Inputs:	ActionEvent
		   Returns:	void

		   Description: Withdraws a given amount from the account of a person with a given name.
		 */
		public void actionPerformed(ActionEvent e) {
			final String NAME = nameField.getText();
			final String BALANCE = balanceField.getText();
			final Account ACC = myServer.getAccount(NAME);
			if (ACC != null && ACC.withdraw(Float.parseFloat(BALANCE))) {
				JOptionPane.showMessageDialog(null, "Withdrawal successful");
			} else {
				JOptionPane.showMessageDialog(null, "Withdrawal unsuccessful");
			}
		}
	}

	/**
	  Class: FrameHandler

	  Description: Handler for Window Closing event
	*/
	static class FrameHandler extends WindowAdapter {
		/**
		   Method:	windowClosing
		   Inputs:	WindowEvent
		   Returns:	void

		   Description: Closes the program.
		 */
		public void windowClosing(WindowEvent e) {

			System.exit(0);
		}
	}

	private AccountServer	myServer;
	private Properties		props;
	private JLabel			typeLabel;
	private JLabel			nameLabel;
	private JLabel			balanceLabel;
	private JComboBox		typeOptions;
	private JTextField		nameField;
	private JTextField		balanceField;
	private JButton 		depositButton;
	private JButton 		withdrawButton;
	private JButton			newAccountButton;
	private JButton			displayAccountsButton;
	private JButton			displayODAccountsButton;

}
