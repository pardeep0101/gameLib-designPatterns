package game2.Proximity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
/**
 * This class is the client class which extends superclass JFrame.
 * @author Ramineh Y Lopez
 *
 */

public class ProximityMain extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ProximityPanel introduction;
	public static JFrame frame;
	public static Random random;
	public static PrintStream ps;
	public static BufferedReader br;
	/**
	 * @param args it passes args from class String to the main function. It runs the program
	 */
	public static void runClient(){
		try{
			Socket connection = new Socket("localhost", 8888);//client socket is created on a local host
			ps = new PrintStream(connection.getOutputStream());//object of class PrintStream is generated here to print to the server from client
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));// this reads from the server
		}
		catch (Exception g) {
			System.out.println(g);
		}
	
		introduction = new ProximityPanel();//object of class ProximityPanel where player's information exists
	
		random = new Random();//the method nextInt is invoked on this object and the value is added to redN JButton in GamePanel class
		Logistics.nextTurn();//method is called and next turn is prepared
		frame = new JFrame("Get&Match");//frame is Generated
		try {
			//reads the player order from the server. 
			//ID=0 for the first player and ID=1 for the second player.
			Logistics.setID(Integer.parseInt(ProximityMain.br.readLine())); 
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//frame size and location are set here for the first player
		frame.setBounds(10, 100, 600, 600);
		// frame size and location are set for the second player
		if (Logistics.getID()==1)frame.setBounds(500, 100, 600, 600);
		//ProximityPanel is added to the frame
		frame.add(introduction);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	public static void main(String[] args) {
		runClient();
	}
}


