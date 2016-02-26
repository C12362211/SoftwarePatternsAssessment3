package listeners;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import oldCode.AccountTransaction;
import oldCode.Customer;
import oldCode.CustomerAccount;
import oldCode.CustomerCurrentAccount;
import oldCode.Menu;

public class WithdrawListener implements ActionListener{
	
	JFrame f;
	Menu menu;
	Customer e;
	CustomerAccount acc;
	Container content;
	
	public WithdrawListener(Menu menu, CustomerAccount acc, Customer e){
		this.acc=acc;
		this.e=e;
		this.menu=menu;
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		boolean loop = true;
		boolean on = true;
		double withdraw = 0;

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(f,
							"Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					menu.customer(e);
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						count--;
						JOptionPane.showMessageDialog(f,
								"Incorrect pin. " + count + " attempts remaining.", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					}

				}
			}

		}
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(f,
					"Enter amount you wish to withdraw (max 500):");// the
																	// isNumeric
																	// method
																	// tests
																	// to
																	// see
																	// if
																	// the
																	// string
																	// entered
																	// was
																	// numeric.
			if (menu.isNumeric(balanceTest)) {

				withdraw = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (withdraw > 500) {
				JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time.",
						"Oops!", JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}
			if (withdraw > acc.getBalance()) {
				JOptionPane.showMessageDialog(f, "Insufficient funds.", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}

			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() - withdraw);
			// recording transaction:
			// String date = new
			// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Date date = new Date();
			String date2 = date.toString();

			String type = "Withdraw";
			double amount = withdraw;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn.", "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
