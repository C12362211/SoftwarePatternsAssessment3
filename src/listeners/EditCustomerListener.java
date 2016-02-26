package listeners;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import oldCode.Customer;
import oldCode.CustomerAccount;
import oldCode.Menu;

public class EditCustomerListener implements ActionListener{
	
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField;
	JTextField surnameTextField;
    JTextField pPSTextField;
	JTextField dOBTextField;
	JLabel customerIDLabel, passwordLabel;
	JTextField customerIDTextField, passwordTextField;
	
	JFrame f;
	Menu menu;
	Customer customer;
	CustomerAccount acc;
	
	public EditCustomerListener(Menu menu, Customer customer, CustomerAccount acc){
		this.menu=menu;
		this.customer=customer;
		this.acc=acc;
		
	}

	public void actionPerformed(ActionEvent ae) {

		boolean loop = true;

		boolean found = false;

		if (menu.getCustomerList().isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			menu.admin();

		} else {

			while (loop) {
				Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

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
				}

			}

			//f.dispose();

			//f.dispose();
			f = new JFrame("Administrator Menu");
			f.setSize(400, 300);
			f.setLocation(200, 200);
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});

			firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
			passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
			firstNameTextField = new JTextField(20);
			surnameTextField = new JTextField(20);
			pPSTextField = new JTextField(20);
			dOBTextField = new JTextField(20);
			customerIDTextField = new JTextField(20);
			passwordTextField = new JTextField(20);

			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JPanel cancelPanel = new JPanel();

			textPanel.add(firstNameLabel);
			textPanel.add(firstNameTextField);
			textPanel.add(surnameLabel);
			textPanel.add(surnameTextField);
			textPanel.add(pPPSLabel);
			textPanel.add(pPSTextField);
			textPanel.add(dOBLabel);
			textPanel.add(dOBTextField);
			textPanel.add(customerIDLabel);
			textPanel.add(customerIDTextField);
			textPanel.add(passwordLabel);
			textPanel.add(passwordTextField);

			firstNameTextField.setText(customer.getFirstName());
			surnameTextField.setText(customer.getSurname());
			pPSTextField.setText(customer.getPPS());
			dOBTextField.setText(customer.getDOB());
			customerIDTextField.setText(customer.getCustomerID());
			passwordTextField.setText(customer.getPassword());

			// JLabel label1 = new JLabel("Edit customer details below.
			// The save");

			JButton saveButton = new JButton("Save");
			JButton cancelButton = new JButton("Exit");

			cancelPanel.add(cancelButton, BorderLayout.SOUTH);
			cancelPanel.add(saveButton, BorderLayout.SOUTH);
			// content.removeAll();
			Container content = f.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(textPanel, BorderLayout.NORTH);
			content.add(cancelPanel, BorderLayout.SOUTH);

			f.setContentPane(content);
			f.setSize(340, 350);
			f.setLocation(200, 200);
			f.setVisible(true);
			f.setResizable(false);

			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					customer.setFirstName(firstNameTextField.getText());
					customer.setSurname(surnameTextField.getText());
					customer.setPPS(pPSTextField.getText());
					customer.setDOB(dOBTextField.getText());
					customer.setCustomerID(customerIDTextField.getText());
					customer.setPassword(passwordTextField.getText());

					JOptionPane.showMessageDialog(null, "Changes Saved.");
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					f.dispose();

					menu.admin();
				}
			});
		}
	}
}
