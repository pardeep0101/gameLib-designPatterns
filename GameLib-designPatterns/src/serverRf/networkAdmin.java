package serverRf;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class networkAdmin implements ObserveServer {
	
	private ArrayList<Socket> incomingSocket = new ArrayList<Socket>();
	
	private String name = null;
	
	int ServerPortNo; 
	 
	boolean RunningStatus; 
	String ServerStartTime = null;
	String ServerStopTime = null;
	
	private Subject sub1;
	 
	 public networkAdmin(Subject subject, String name) {
		 sub1 = subject;
		 sub1.register(this);
		 this.name = name;
		}
	public String getObjectName(){return this.name;}
	
	
	@Override
	public void update(ArrayList<Socket> incomingSocket, int ServerPortNo, boolean RunningStatus, String ServerStartTime,String ServerStopTime) {
		// TODO Auto-generated method stub
		this.incomingSocket = incomingSocket;
		System.out.println("----------------------------------");
		System.out.println("In update method of observer " + this.getObjectName());
		this.ServerPortNo = ServerPortNo;
		this.RunningStatus = RunningStatus;
		this.ServerStartTime = ServerStartTime;
		this.ServerStopTime = ServerStopTime;
		printIP();
	}

	public void printIP() {
		System.out.println("In printIP method of object  "+this.getObjectName());
		for (Socket s : incomingSocket) {
			System.out.println("Incoming Ip Address:Port number ");
			System.out.print(s.getRemoteSocketAddress().toString());
			System.out.print("  Port Number Open: " + ServerPortNo);
			System.out.print("  Server Status: " + RunningStatus);
			System.out.print("  Started At: " + ServerStartTime);
			System.out.println("  Stopped At: " + ServerStopTime);
			System.out.println("----------------------------------");
		}
	}
			
}