package main1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import clientRf.Client;
import clientRf.GameViewer;

public class welcome_listener implements ActionListener{

	welcome_listener(){
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedButton = (JButton) e.getSource();
		
		if(clickedButton.getActionCommand().equalsIgnoreCase("send")){
			try {
				
				runProM.setGameInput(welcome_panel.getUserInput());
				}
				
			 catch ( Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	}	

	}
}


