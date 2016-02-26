package listeners;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import oldCode.Customer;
import oldCode.CustomerAccount;
import oldCode.Menu;

public class SummaryListener implements ActionListener{
	
	JFrame f;
	Menu menu;
	Customer customer;
	CustomerAccount acc;
	Container content;
	
	public SummaryListener(Menu menu, Customer customer, CustomerAccount acc){
		this.menu=menu;
		this.customer=customer;
		this.acc=acc;
		
	}

	
	public void actionPerformed(ActionEvent ae) {
		//f.dispose();

		f = new JFrame("Summary of Transactions");
		f.setSize(400, 700);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);

		JLabel label1 = new JLabel("Summary of all transactions: ");

		JPanel returnPanel = new JPanel();
		JButton returnButton = new JButton("Return");
		returnPanel.add(returnButton);

		JPanel textPanel = new JPanel();

		textPanel.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(returnButton, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);

		for (int a = 0; a < menu.getCustomerList().size(); a++)// For each
														// customer, for
														// each account,
														// it displays
														// each
														// transaction.
		{
			for (int b = 0; b < menu.getCustomerList().get(a).getAccounts().size(); b++) {
				acc = menu.getCustomerList().get(a).getAccounts().get(b);
				for (int c = 0; c < menu.getCustomerList().get(a).getAccounts().get(b).getTransactionList().size(); c++) {

					textArea.append(acc.getTransactionList().get(c).toString());
					// Int total =
					// acc.getTransactionList().get(c).getAmount(); //I
					// was going to use this to keep a running total but
					// I couldnt get it working.

				}
			}
		}

		textPanel.add(textArea);
		//content.removeAll();

		Container content = f.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		// content.add(label1);
		content.add(textPanel);
		// content.add(returnPanel);

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				menu.admin();
			}
		});
	}

}
