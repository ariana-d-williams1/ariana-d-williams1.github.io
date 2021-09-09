package fiveDestinations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FiveDestinationsFrame extends JFrame implements ActionListener {
	ImageIcon i[]; 
	JButton b1, b2, b3, b4; 
	JLabel l, l1, l2; 
	int s = 0; 
	JTextField[] fields; 
	JPanel listModel, moreButton, listModel2;
	
public FiveDestinationsFrame() {
	super("Top Destination List"); 
	//set the parameters of the JFrame
	setLayout(new BorderLayout()); 
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
	setSize(1000, 700); 
	
	//create panels to add the buttons and slide shows to the frame
	JPanel listModel = new JPanel(new FlowLayout());
	JPanel listModel2 = new JPanel(new FlowLayout());
	JPanel moreButton = new JPanel(new FlowLayout()); 
	
	//initiate buttons
	b1 = new JButton("previous"); 
	b1.setBounds(50, 50, 100, 100); 
	b1.setForeground(Color.BLACK); 
	b1.setBackground(Color.BLUE);
	b2 = new JButton("next"); 
	b2.setBounds(50, 50, 100, 100);
	b2.setForeground(Color.BLACK); 
	b2.setBackground(Color.BLUE);
	b3 = new JButton("more information");
	b4 = new JButton("BOOK NOW");
	
	//add buttons to panels
	listModel.add(b1); 
	listModel2.add(b2);
	moreButton.add(b3);
	moreButton.add(b4);
	
	//add panels to frame
	add(listModel, BorderLayout.WEST);
	add(listModel2, BorderLayout.EAST); 
	add(moreButton, BorderLayout.SOUTH);
	
	//add action to buttons
	b1.addActionListener(this); 
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	 
	 //array for images surrounded in try/catch so if an image is not found it will show an understandable error.
	 try {
		i = new ImageIcon[5]; 
		 i[0] =new ImageIcon(getClass().getResource("/resources/city.jpg"));
			
		 i[1] = new ImageIcon(getClass().getResource("/resources/japan.jpg"));
		 
		 i[2] = new ImageIcon(getClass().getResource("/resources/mountains.jpg"));
			
		 i[3] = new ImageIcon(getClass().getResource("/resources/surf.jpg"));
		 
		 i[4] = new ImageIcon(getClass().getResource("/resources/waterfall.jpg"));
			
		 l = new JLabel("", JLabel.CENTER);
		 add(l, BorderLayout.CENTER);
		 l.setIcon(i[0]);
	} catch (Exception e) {
		// print out error
		e.printStackTrace();
		System.out.println(e + " image can not be found");
	} 
	
	 //more button array and initiate text fields
     fields = new JTextField[5]; 
     fields[0] = new JTextField("1. Top destination for sight seeing.");
     fields[1] = new JTextField("2. Top destination for exploring architecture.");
     fields[2] = new JTextField("3. Top destination for outdoor activites and great views.");
     fields[3] = new JTextField("4. Top destination for relaxing on the beach or get adventurous and surf.");
     fields[4] = new JTextField("5. Top destination to enjoy nature.");
     
}
	@Override
	//action logic for buttons
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1)
    	{
    		if(s==0) {
    			JOptionPane.showMessageDialog(null, "First picture");
    		}
    		else
    		{
    			s=s-1; 
    			l.setIcon(i[s]);
    		}
    	}
    	if(e.getSource()==b2) {
    		if(s==i.length-1) {
    			JOptionPane.showMessageDialog(null, "Last Picture");
    		}
    		else
    		{
    			s=s+1;
    			l.setIcon(i[s]);
    		}
    	}
    	if(e.getSource()==b3) {
    		if(s==0) {
    			
    		JOptionPane.showMessageDialog(null, fields[0]);
    	}
    		else if(s==1)
    		{
    			s=s+1;
    			JOptionPane.showMessageDialog(null, fields[1]);
    		}
    		else if(s==2) {
    			s=s+1;
    			JOptionPane.showMessageDialog(null, fields[2]);
    		}
    		else if(s==3) {
    			s=s+1;
        	JOptionPane.showMessageDialog(null, fields[3]);
        	}
        		else
        		{
        			JOptionPane.showMessageDialog(null, fields[4]);
        		}
    }
    	if(e.getSource()==b4) {
    		//add event
    			booking book = new booking();
    			book.setTitle("Book Now"); 
    			book.setVisible(true); 
    	}
	}
	};
