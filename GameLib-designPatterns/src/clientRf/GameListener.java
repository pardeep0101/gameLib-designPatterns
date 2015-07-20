package clientRf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Handles the events that will be triggered upon interaction with the GUI.
 * @author Katya
 *
 */
public class GameListener implements ActionListener {
	
	/**
	 * @uml.property  name="once"
	 */
	private boolean once = false;
	
	public GameListener() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		
		if(clickedButton.getActionCommand().equalsIgnoreCase("send")){
			try {
				Client.firstInt = Integer.parseInt(GameViewer.getUserInput());
				Client.secondInt = Integer.parseInt(GameViewer.getUserInput());
				
				if(once){
					Client.runAgain(); // triggers the beginning of a second turn
				}
				
				else{
					Client c = new Client();
					once = true; // runs for a second time
				}
				
			} catch ( Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	}	

	}
}
