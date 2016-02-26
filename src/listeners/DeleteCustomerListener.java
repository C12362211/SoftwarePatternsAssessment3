package listeners;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import oldCode.Customer;
import oldCode.CustomerAccount;
import oldCode.Menu;

public class DeleteCustomerListener implements ActionListener{
	
	JFrame f;
	Menu menu;
	Customer customer;
	
	public DeleteCustomerListener(Menu menu, Customer customer){
		this.menu=menu;
		this.customer=customer;
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		boolean found = true;

		if (menu.getCustomerList().isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
			menu.dispose();
			menu.admin();
		} else {
			{
				Object customerID = JOptionPane.showInputDialog(f,
						"Customer ID of Customer You Wish to Delete:");

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
					if (customer.getAccounts().size() > 0) {
						JOptionPane.showMessageDialog(f,
								"This customer has accounts. \n You must delete a customer's accounts before deleting a customer ",
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						menu.getCustomerList().remove(customer);
						JOptionPane.showMessageDialog(f, "Customer Deleted ", "Success.",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		}
	}

}
