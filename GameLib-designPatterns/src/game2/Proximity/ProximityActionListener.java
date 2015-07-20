package game2.Proximity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author Ramineh Y Lopez
 *	This class brings up game panel after start button in proximity panel is pressed.
 */

public class ProximityActionListener implements ActionListener{


	/**
	 * This method saves the names of the players and brings up game panel if "start" is pressed.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand()=="start"){

			Logistics.setPlayer(Logistics.getID(),ProximityPanel.playerT.getText());//
			ProximityMain.ps.println(Logistics.getPlayer(Logistics.getID()));

			try {
				Logistics.setPlayer(Logistics.getID()+1,ProximityMain.br.readLine());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Logistics.setD(Integer.parseInt(ProximityMain.br.readLine()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			ProximityComponent.setRadius();//method calculates radius based on board size
			//Clears the frame to bring up an updated GamePanel
			ProximityMain.frame.remove(ProximityMain.introduction);
			ProximityMain.frame.revalidate();
			ProximityMouseListener.gamePanel=new GamePanel();


		}


	}

}

