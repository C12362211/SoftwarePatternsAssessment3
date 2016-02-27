package listeners;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import oldCode.Customer;
import oldCode.CustomerAccount;
import oldCode.Menu;

public class InterestListener implements ActionListener{
	
	JFrame f;
	Menu menu;
	Customer customer;
	CustomerAccount acc;
	
	public InterestListener(Menu menu, Customer customer, CustomerAccount acc){
		this.menu=menu;
		this.customer=customer;
		this.acc=acc;
		
	}
	
	public void actionPerformed(ActionEvent ae) {

		boolean applyInterest = true;

		boolean found = false;

		if (menu.getCustomerList().isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			menu.admin();

		} else {
			while (applyInterest) {
				Object customerID = JOptionPane.showInputDialog(f,
						"Customer ID of Customer You Wish to Apply Interest to:");

				for (Customer aCustomer : menu.getCustomerList()) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						customer = aCustomer;
						applyInterest = false;
					}
				}

				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						applyInterest = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						f.dispose();
						applyInterest = false;

						menu.admin();
					}
				} else {
					f = new JFrame("Administrator Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					f.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < customer.getAccounts().size(); i++) {

						box.addItem(customer.getAccounts().get(i).getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();

					JLabel label = new JLabel("Select an account to apply interest to:");
					boxPanel.add(label);
					boxPanel.add(box);
					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Interest");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);

					if (customer.getAccounts().isEmpty()) {
						JOptionPane.showMessageDialog(f,
								"This customer has no accounts! \n The admin must add acounts to this customer.",
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
						f.dispose();
						menu.admin();
					} else {

						for (int i = 0; i < customer.getAccounts().size(); i++) {
							if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								acc = customer.getAccounts().get(i);
							}
						}

						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";
								double interest = 0;
								boolean applyInterest = true;

								while (applyInterest) {
									String interestString = JOptionPane.showInputDialog(f,
											"Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");
									if (Menu.isNumeric(interestString)) {// the isNumeric method tests to see if the string entered was numeric

										interest = Double.parseDouble(interestString);
										applyInterest = false;

										acc.setBalance(
												acc.getBalance() + (acc.getBalance() * (interest / 100)));

										JOptionPane.showMessageDialog(f,
												interest + "% interest applied. \n new balance = "
														+ acc.getBalance() + euro,
												"Success!", JOptionPane.INFORMATION_MESSAGE);
									}

									else {
										JOptionPane.showMessageDialog(f, "You must enter a numerical value!",
												"Oops!", JOptionPane.INFORMATION_MESSAGE);
									}

								}

								f.dispose();
								menu.admin();
							}
						});

						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								f.dispose();
								menu.menuStart();
							}
						});

					}
				}
			}
		}

	}

}
