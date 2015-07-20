package serverRf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Handles the Server side of our Game. Allows server to start listening.
 * 
 * @author Pardeep
 * 
 */
public class Server implements Subject {

	private ArrayList<ObserveServer> obServ = new ArrayList<ObserveServer>();
	
	private ArrayList<Socket> inc = new ArrayList<Socket>();
	
	private Socket incoming;
	
	private int port = 7889;
	
	private boolean keepRuning = true;
	
	private String ServerStartTime = "0";
	
	private String ServerStopTime = "0";
	
	public Server(String n) {
		System.out.println(n);
	}

	public Server() throws IOException {

	}

	public int getServerPort() {return port;}

	public void setServerPort(int Port) {this.port = Port;System.out.println("Port changed");}
	
	public void killServer(){this.ServerStopTime();notifyObserver();this.keepRuning = false;}
	public void getServerStatus() {	if (keepRuning == true) {System.out.println("Server is up and running");} else {System.out.println("Server is not running");}
	}

	public void ServerStartTime() {	java.util.Date date = new java.util.Date();	Timestamp t = new Timestamp(date.getTime());
		ServerStartTime = t.toString();	System.out.println(ServerStartTime);
		}
	public void ServerStopTime() {java.util.Date date = new java.util.Date();Timestamp t = new Timestamp(date.getTime());
		ServerStopTime = t.toString();System.out.println(ServerStopTime) ;
	}
	
	
	public String getServerStartTime(){return ServerStartTime;}
	
	public String getServerStopTime() {return ServerStopTime;}
	
	public void runServer() throws IOException {
		ServerSocket servSoc = new ServerSocket(port);
		System.out.println("Server is Up");
		ServerStartTime();
		while (keepRuning == true){
			incoming = servSoc.accept();
			System.out.println("Listening...");
			inc.add(incoming);
			notifyObserver();

			Thread t = new ServerThread(incoming, this);
			t.start();

		}
		ServerStopTime();
		for(int i=1;i<2;i++){System.out.println("Mayday..Maydayy..Server is shuting down, this is the last update..");
		notifyObserver();}
	}

	public static void main(String args[]) throws IOException {

		Server s = new Server();

	}

	@Override
	public void register(ObserveServer o) {
		// TODO Auto-generated method stub
		obServ.add(o);
		System.out.println("new observer added");

	}

	@Override
	public void unregister(ObserveServer o) {
		// TODO Auto-generated method stub
		int index = obServ.indexOf(o);
		obServ.remove(index + 1);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for (ObserveServer ob : obServ) {
			ob.update(inc,port,keepRuning ,ServerStartTime, ServerStopTime );
		}
	}
}
