
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
	
		//Menu menu = new Menu();
		JFrame f;
		String pps;
		String firstName;
		String surname;
		String dob;
		String password;
		Menu menu;

		
		
		public AddListener(String pps, String firstName, String surname, String dob, Menu menu){
			this.pps=pps;
			this.firstName=firstName;
			this.surname=surname;
			this.dob=dob;
			this.menu=menu;
			
		}
		
		String CustomerID = "ID" + pps;

			public void actionPerformed(ActionEvent e) {
				//f1.dispose();

				boolean loop = true;
				while (loop) {
					password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");

					if (password.length() != 7) // Making
												// sure
												// password
												// is 7
												// characters
					{
						JOptionPane.showMessageDialog(null, null,
								"Password must be 7 charatcers long", JOptionPane.OK_OPTION);
					} else {
						loop = false;
					}
				}

				ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
				Customer customer = new Customer(pps, surname, firstName, dob, CustomerID, password,
						accounts);

				Menu.getCustomerList().add(customer);

				JOptionPane.showMessageDialog(f,
						"CustomerID = " + CustomerID + "\n Password = " + password,
						"Customer created.", JOptionPane.INFORMATION_MESSAGE);
				menu.menuStart();
				//f.dispose();
			}
		;
	}
