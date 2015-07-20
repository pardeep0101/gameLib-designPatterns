package serverRf;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

/**
 * Takes care of the Server Threads that will be running.
 * @author Pardeep
 *
 */
public class ServerThread extends Thread {
	
	Server s1;
	
	private Socket socket;
	private static Integer clientInt = new Integer(0);
	private static Integer serverInt = new Integer(0);
	
	private static boolean runOnce ;
	
	public ServerThread(Socket s, Server s1){
		this.socket = s; 
		runOnce = false;
		this.s1 = s1;
	}
	
	public void run(){
		
		try{
			
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps = new PrintStream(socket.getOutputStream());

		boolean end = false;
		
		clientInt = Integer.parseInt(br.readLine()); // read the value that the client sent
		
			if((clientInt >= 60) && (clientInt <= 70))
			{
				while(!end){
	
					//automated server
					serverInt = setServerValue(clientInt); // passing number entered by client so that server chooses its number
					
					if((serverInt >= 30 && serverInt <= 600) && ((serverInt == clientInt * 3) || (serverInt == clientInt - 19))) {	
						ps.println(serverInt);
						
						clientInt = Integer.parseInt(br.readLine()); // expects next value from client
						
						if((clientInt >= 30 && clientInt <= 600) && ((clientInt == serverInt * 3) || (clientInt == serverInt - 19))) {
							continue;
						}
						
						else{
							ps.println("I win. :-)");
							end = true;
							s1.killServer();
						}			
						
					continue;		
					
					}
					
					else{
						ps.println(serverInt + "... You win. :-(");
						end = true;
						s1.killServer();
					}
				}
			
			}
			else{
				ps.println("You lost.");
			}	

			socket.close();
		
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * Calculates a random value based on the number entered by the user.
	 * @param i the value entered by the user/client
	 * @return a random value chosen by the server
	 */
	private int setServerValue(int i){
		Random rand = new Random();
		int rightAns1 = i*3;
		int rightAns2 = (i - 19);
		int arrRet[];
		arrRet = new int[3];
		
		if((i >= 60 && i<= 70 && !runOnce)){
			
			arrRet[0] = rightAns1;
			arrRet[1] = rightAns2;
			arrRet[2] = 1;
			int selectRand = rand.nextInt(1);
			//System.out.println(selectRand);
			runOnce = true;
			return arrRet[selectRand];
		}
		else{
			int randIn = rand.nextInt(100);
			arrRet[0] = rightAns1;
			arrRet[1] = randIn;
			arrRet[2] = rightAns2;
			int selectRand = rand.nextInt(2);
			//System.out.println(selectRand);
			return arrRet[selectRand];
		}		
		
	}

}
