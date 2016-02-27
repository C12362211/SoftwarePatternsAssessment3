package listeners;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
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

public class ContinueListener implements ActionListener{
	
	JLabel firstNameLabel, surnameLabel, ppsLabel, dobLabel;
	JTextField firstNameTextField;
	JTextField surnameTextField;
    JTextField ppsTextField;
	JTextField dobTextField;
	JLabel customerIDLabel, passwordLabel;
	JTextField customerIDTextField, passwordTextField;
	JPanel panel2;
	JButton add;
	final ButtonGroup userType;
	
	JFrame f,f1;
	Menu menu;
	Customer e;
	CustomerAccount acc;
	Container content;
	
	public ContinueListener(Menu menu, CustomerAccount acc, Customer e,ButtonGroup userType, JFrame f){
		this.acc=acc;
		this.e=e;
		this.menu=menu;
		this.userType=userType;
		this.f=f;
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		String user = userType.getSelection().getActionCommand();

		// if user selects NEW CUSTOMER
		if (user.equals("New Customer")) {
			f.dispose();
			f1 = new JFrame("Create New Customer");
			f1.setSize(400, 300);
			f1.setLocation(200, 200);
			f1.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});
			Container content = f1.getContentPane();
			content.setLayout(new BorderLayout());

			firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
			surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
			ppsLabel = new JLabel("PPS num:", SwingConstants.RIGHT);
			dobLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
			firstNameTextField = new JTextField(20);
			surnameTextField = new JTextField(20);
			ppsTextField = new JTextField(20);
			dobTextField = new JTextField(20);
			JPanel panel = new JPanel(new GridLayout(6, 2));
			panel.add(firstNameLabel);
			panel.add(firstNameTextField);
			panel.add(surnameLabel);
			panel.add(surnameTextField);
			panel.add(ppsLabel);
			panel.add(ppsTextField);
			panel.add(dobLabel);
			panel.add(dobTextField);

			panel2 = new JPanel();
			add = new JButton("Add");
			add.addActionListener(new AddListener(menu, this));
			
			
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.dispose();
					menu.menuStart();
				}
			});

			panel2.add(add);
			panel2.add(cancel);

			content.add(panel, BorderLayout.CENTER);
			content.add(panel2, BorderLayout.SOUTH);

			f1.setVisible(true);

		}

		// if user select ADMIN
		if (user.equals("Administrator")) {
			boolean adminName = true, adminPWord = true;
			boolean nameAndPass = false;
			while (adminName) {
				Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

				if (!adminUsername.equals("admin")) // search admin list for admin with matching admin username
													
				{
					int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						adminName = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						f1.dispose();
						adminName = false;
						adminPWord = false;
						menu.menuStart();
					}
				} else {
					adminName = false;
				}
			}

			while (adminPWord) {
				Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");

				if (!adminPassword.equals("admin11")) // search admin list for admin with matching admin password
				{
					int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {

					} else if (reply == JOptionPane.NO_OPTION) {
						f1.dispose();
						adminPWord = false;
						menu.menuStart();
					}
				} else {
					adminPWord = false;
					nameAndPass = true;
				}
			}

			if (nameAndPass) {
				
				adminName = false;
				menu.admin();
			}
		}
		// if user selects CUSTOMER
		if (user.equals("Customer")) {
			boolean custID = true, custPWord = true;
			boolean IdAndPass = false;
			boolean found = false;
			Customer customer = null;
			while (custID) {
				Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

				for (Customer aCustomer : menu.getCustomerList()) {

					if (aCustomer.getCustomerID().equals(customerID)) // search customer list for matching customer ID
					{
						found = true;
						customer = aCustomer;
					}
				}

				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						custID = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						f.dispose();
						custID = false;
						custPWord = false;
						menu.menuStart();
					}
				} else {
					custID = false;
				}

			}

			while (custPWord) {
				Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

				if (!customer.getPassword().equals(customerPassword)) // check if customer password is correct
				{
					int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {

					} else if (reply == JOptionPane.NO_OPTION) {
						f.dispose();
						custPWord = false;
						menu.menuStart();
					}
				} else {
					custPWord = false;
					IdAndPass = true;
				}
			}

			if (IdAndPass) {
				custID = false;
				menu.customer(customer);
			}
		}
	}

	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public void setFirstNameTextField(JTextField firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	public JTextField getSurnameTextField() {
		return surnameTextField;
	}

	public void setSurnameTextField(JTextField surnameTextField) {
		this.surnameTextField = surnameTextField;
	}

	public JTextField getDobTextField() {
		return dobTextField;
	}

	public void setDobTextField(JTextField dobTextField) {
		this.dobTextField = dobTextField;
	}

	public JTextField getPpsTextField() {
		return ppsTextField;
	}

	public void setPpsTextField(JTextField ppsTextField) {
		this.ppsTextField = ppsTextField;
	}
	
	

}
