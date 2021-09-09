package fiveDestinations;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class booking extends JFrame implements ActionListener {
//variables
	String[] optionsToChoose = {"Destination 1", "Destination 2", "Destination 3", "Destination 4", "Destination 5"};
	JPanel menuPanel, uInfo;
	JTextField uName, uAddress, uAddress2, uCity, uState, uZip, uPhone, uEmail;
	JLabel name, address, address2, city, state, zip, phone, email;
	JButton ok; 
	
	public booking() {
		//set Jframe parameters
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		setSize(500, 500); 
		
		//dropdown menu and done button
		JPanel menuPanel = new JPanel();
		JComboBox<String> menu = new JComboBox<>(optionsToChoose);
		menuPanel.add(menu); 
		
		ok = new JButton("Done");
		menuPanel.add(ok);
		add(menuPanel, BorderLayout.SOUTH);
		
		//add action to button
		ok.addActionListener(this);
		
		//user input 
		JPanel uInfo = new JPanel();
		uInfo.setBounds(10, 40, 400, 400);
		add(uInfo); 
		
		//create user input lables
		email = new JLabel("Please enter your email");
		name = new JLabel("Please enter your name"); 
		address = new JLabel("Please enter your address"); 
		address2 = new JLabel("apt/unit");
		city = new JLabel("Please enter your city");
		state = new JLabel("Please enter your state");
		zip = new JLabel("Please enter your zipcode");
		phone = new JLabel("Please enter your phone number");
		 
		//create user input fields
		uName = new JTextField(20); 
		uAddress = new JTextField(50); 
		uAddress2 = new JTextField(10); 
		uCity = new JTextField(30); 
		uState = new JTextField(15); 
		uZip = new JTextField(10); 
		uPhone = new JTextField(10); 
		uEmail = new JTextField(20); 		
		
		//add user text fields and lables to the frame
		uInfo.add(name);
		uInfo.add(uName);
		uInfo.add(address);
		uInfo.add(uAddress);
		uInfo.add(address2);
		uInfo.add(uAddress);
		uInfo.add(city);
		uInfo.add(uCity);
		uInfo.add(state);
		uInfo.add(uState);
		uInfo.add(zip);
		uInfo.add(uZip);
		uInfo.add(phone);
		uInfo.add(uPhone);
		uInfo.add(email);
		uInfo.add(uEmail);
		
		
		
	}
	
		
	
	@Override
	//add action to button
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok) {
			JOptionPane.showMessageDialog(null, "An agent will be in contact");
		}
		
	}

}
