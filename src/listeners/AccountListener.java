package listeners;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import oldCode.ATMCard;
import oldCode.AccountTransaction;
import oldCode.Customer;
import oldCode.CustomerAccount;
import oldCode.CustomerCurrentAccount;
import oldCode.CustomerDepositAccount;
import oldCode.Menu;

public class AccountListener implements ActionListener{
	
	JFrame f;
	Menu menu;
	Customer customer;
	CustomerAccount acc;
	Container content;
	
	public AccountListener(Menu menu, Customer customer, CustomerAccount acc){
		this.menu=menu;
		this.customer=customer;
		this.acc=acc;
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		//f.dispose();

		if (menu.getCustomerList().isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			menu.admin();
		} else {
			boolean loop = true;

			boolean found = false;

			while (loop) {
				Object customerID = JOptionPane.showInputDialog(f,
						"Customer ID of Customer You Wish to Add an Account to:");

				for (Customer aCustomer : menu.getCustomerList()) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						customer = aCustomer;
					}
				}

				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						f.dispose();
						loop = false;

						menu.admin();
					}
				} else {
					loop = false;
					// a combo box in an dialog box that asks the admin
					// what type of account they wish to create
					// (deposit/current)
					String[] choices = { "Current Account", "Deposit Account" };
					String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
							"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

					if (account.equals("Current Account")) {
						// create current account
						boolean valid = true;
						double balance = 0;
						String number = String.valueOf("C" + (menu.getCustomerList().indexOf(customer) + 1) * 10
								+ (customer.getAccounts().size() + 1));// this
																		// simple
																		// algorithm
																		// generates
																		// the
																		// account
																		// number
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
						int randomPIN = (int) (Math.random() * 9000) + 1000;
						String pin = String.valueOf(randomPIN);

						ATMCard atm = new ATMCard(randomPIN, valid);

						CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance,
								transactionList);

						System.out.println("cust namme " + customer.getFirstName());
						customer.getAccounts().add(current);
						menu.setCustomer(customer);
						
						JOptionPane.showMessageDialog(f, "Account number = " + number + "\n PIN = " + pin,
								"Account created.", JOptionPane.INFORMATION_MESSAGE);
						System.out.println("pin " + pin);
						//f.dispose();
						menu.admin();
					}

					if (account.equals("Deposit Account")) {
						// create deposit account

						double balance = 0, interest = 0;
						String number = String.valueOf("D" + (menu.getCustomerList().indexOf(customer) + 1) * 10
								+ (customer.getAccounts().size() + 1));// this
																		// simple
																		// algorithm
																		// generates
																		// the
																		// account
																		// number
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

						CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance,
								transactionList);
						
						customer.getAccounts().add(deposit);
						JOptionPane.showMessageDialog(f, "Account number = " + number, "Account created.",
								JOptionPane.INFORMATION_MESSAGE);

						//f.dispose();
						menu.admin();
					}

				}
			}
		}
	}

}
