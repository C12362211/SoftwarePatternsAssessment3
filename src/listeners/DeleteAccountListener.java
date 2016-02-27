package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import oldCode.Customer;
import oldCode.Menu;

public class DeleteAccountListener implements ActionListener {

	JFrame f;
	Menu menu;
	Customer customer;

	public DeleteAccountListener(Menu menu, Customer customer) {
		this.menu = menu;
		this.customer = customer;

	}

	public void actionPerformed(ActionEvent ae) {
		boolean found = true;

		{
			Object customerID = JOptionPane.showInputDialog(f,
					"Customer ID of Customer from which you wish to delete an account");

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
				} else if (reply == JOptionPane.NO_OPTION) {
					f.dispose();
					menu.admin();
				}
			} else {
				// Here I would make the user select a an account to
				// delete from a combo box. If the account had a balance
				// of 0 then it would be deleted. (I do not have time to
				// do this)
			}

		}
	}

}
