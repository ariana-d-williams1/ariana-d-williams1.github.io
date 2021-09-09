package fiveDestinations;

import javax.swing.*;

/**
 * @author Ariana Williams
 *
 */
public class FiveDestinations {

	/**
	 * Main
	 */
	public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			FiveDestinationsFrame fiveDestinationFrame = new FiveDestinationsFrame();
			fiveDestinationFrame.setTitle("List of Top Destinations"); 
			fiveDestinationFrame.setVisible(true); 
		}
	});
	}
	}

