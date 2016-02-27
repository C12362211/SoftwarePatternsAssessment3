
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import oldCode.Customer;
import oldCode.CustomerAccount;
import oldCode.Menu;


public class AddListener implements ActionListener{
	
		JFrame f;
		String password;
		Menu menu;

		private ContinueListener cl;
		
		public AddListener(Menu menu, ContinueListener cl){
			this.menu=menu;
			this.cl = cl;
		}
		

			public void actionPerformed(ActionEvent e) {
				String customerID = "ID" + cl.getPpsTextField().getText();
				String pps = cl.getPpsTextField().getText();
				String firstName = cl.getFirstNameTextField().getText();
				String surname = cl.getSurnameTextField().getText();
				String dob = cl.getDobTextField().getText();
				System.out.println(customerID + "£££");
				boolean enterPassword = true;
				while (enterPassword) {
					password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");

					if (password.length() != 7) // Making sure password is 7 characters
					{
						JOptionPane.showMessageDialog(null, null,
								"Password must be 7 charatcers long", JOptionPane.OK_OPTION);
					} else {
						enterPassword = false;
					}
				}
				customerID = "ID" + pps;
				ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
				Customer customer = new Customer(pps, surname, firstName, dob, customerID, password,
						accounts);

				menu.getCustomerList().add(customer);

				JOptionPane.showMessageDialog(f,
						"CustomerID = " + customerID + "\n Password = " + password,
						"Customer created.", JOptionPane.INFORMATION_MESSAGE);
				menu.menuStart();
			}
		;
	}
