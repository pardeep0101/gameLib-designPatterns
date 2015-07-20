package game2.Proximity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
/**
 * This class defines a multiserver to which many players can connnect
 * 
 *
 */
public class MultiServer{

	static ServerSocket socket;// socket is defined for the server
	static int ID=0;//ID of the player, 0 for blue and 1 for red
	static Socket[] connection;//Array of client sockets
	static boolean nonexit=true;//
	static String player1;
	static String player2;
	public static void startServer(){
		try{

			socket = new ServerSocket(8888);
			System.out.println(" ");
			while(true){
				connection= new Socket[2];//two client sockets are created
				connection[0] = socket.accept();//first socket is accepted by the server
				//first socket reads and prints to the server
				final BufferedReader br1 = new BufferedReader(new InputStreamReader(connection[0].getInputStream()));
				final PrintStream ps1 = new PrintStream(connection[0].getOutputStream());
				ps1.println(0);//message is printed to the server from the first client socket
				//second socket connection is accepted by the server
				connection[1] = socket.accept();
				final BufferedReader br2 = new BufferedReader(new InputStreamReader(connection[1].getInputStream()));
				final PrintStream ps2 = new PrintStream(connection[1].getOutputStream());
				ps2.println(1);//message is printed to server from the second socket
				/**
				 * A thread is initialized and method conversation is called here 
				 */
				Thread t = new Thread(new Runnable() {
					public void run() {
						conversation(br1,br2,ps1,ps2);
						
					}
				});
				t.start();

			}
		}
		catch (IOException e) {}
//		try {//connections to the server are closed
//			connection[0].close();
//			connection[1].close();
//		}
//		catch (IOException e) {}
	}
	public static void main(String[] args) {
		startServer();
	}
	/**
	 * This methods reads values, L (location of the tile) and r (value of the tile) to the server
	 * @param br1 reads from the first socket
	 * @param br2 reads from the second socket
	 * @param ps1 prints to the server from the first socket
	 * @param ps2 prints to the server from the second socket
	 */
	public static void conversation(BufferedReader br1,BufferedReader br2,PrintStream ps1,PrintStream ps2){
		//commence communication
		
		try{
		
			player1=br1.readLine();
			player2=br2.readLine();
			
			ps1.println(player2);
			ps2.println(player1);
			//randomized value between 8 and 12 are generated and passed to the server
			Random dRandom = new Random();
			int d=dRandom.nextInt(4)+8;
			ps1.println(d);
			ps2.println(d);
			
			while(true){
				String l=br1.readLine();
				String r=br1.readLine();
				ps2.println(l);
				ps2.println(r);
				l=br2.readLine();
				r=br2.readLine();
				ps1.println(l);
				ps1.println(r);
			}

		}
		catch (IOException e){}
	}
}



