package clientRf;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Handles the Client side of our game.
 * @author Pardeep
 *
 */
public class Client {

	private static BufferedReader br;
	private static PrintStream ps;
	private static Socket sock;
	private static String serverResponse;
	private static boolean end = false;
	
	public static int firstInt;
	public static int secondInt;
	
	/**
	 * @uml.property  name="in"
	 */
	private Scanner in;
	
	public Client() throws UnknownHostException{
		
		try {
		//	sock = new Socket("icom4015.ece.uprm.edu", 7996);
			sock = new Socket("localhost", 7889);
			in = new Scanner(System.in);
					
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			ps = new PrintStream(sock.getOutputStream());
			
			run(); // the first run
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void run() throws IOException {
		
		ps.println(firstInt); // passes the value TO the server
		GameViewer.updateUserResponse(String.valueOf(firstInt)); // updates the value on the GUI
		
		serverResponse = br.readLine(); // read the response FROM the server
		GameViewer.updateServerResponse(serverResponse);
		
		if((serverResponse.toString().contains("You lost.")) || (serverResponse.toString().contains("I win.")) || (serverResponse.toString().contains("You win.")) )
		{	
			end = true; // if any of the conditions to end the game are met
			sock.close(); // stop listening, end game
		}
	}
	
	public static void runAgain() throws UnknownHostException, IOException, InterruptedException{
		
		ps.println(secondInt); // passes the value TO the server
		GameViewer.updateUserResponse(String.valueOf(secondInt)); // updates the value on the GUI
		
		serverResponse = br.readLine();
		GameViewer.updateServerResponse(serverResponse);
		
		if(!end){		
			if((serverResponse.toString().contains("You lost.")) || (serverResponse.toString().contains("I win.")) || (serverResponse.toString().contains("You win.")) )
			{
				end = true; // if any of the conditions to end the game are met
				sock.close(); // stop listening, end game
			}
		}		
	}
}
