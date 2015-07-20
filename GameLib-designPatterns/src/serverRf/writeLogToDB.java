package serverRf;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class writeLogToDB implements ObserveServer {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/forO";

	static final String USER = "root";
	static final String PASS = "";
	
	private Socket s;
	
	private ArrayList<Socket> incomingSocket = new ArrayList<Socket>();
	
	private String name = null;
	
	int ServerPortNo;
	
	boolean RunningStatus;
	
	String ServerStartTime = null;
	
	String ServerStopTime = null;
	
	private Subject sub1;
	
	private String timeStamp = null;

	public writeLogToDB(Subject subject, String name) {
		sub1 = subject;
		sub1.register(this);
		this.name = name;
	}

	public String getObjectName() {
		return this.name;
	}

	public void update(ArrayList<Socket> incomingSocket, int ServerPortNo,
			boolean RunningStatus, String ServerStartTime, String ServerStopTime) {
		// TODO Auto-generated method stub
		this.incomingSocket = incomingSocket;
		System.out.println(" ");
		System.out.println("In update method of observer Log Server: "
				+ this.getObjectName());
		this.ServerPortNo = ServerPortNo;
		this.RunningStatus = RunningStatus;
		this.ServerStartTime = ServerStartTime;
		this.ServerStopTime = ServerStopTime;
		writedb();
	}

	
	private int c1;

	public void writedb() {
		for (Socket s : incomingSocket) {
			// System.out.println("Incoming Ip Address:Port number ");
			// System.out.print(s.getRemoteSocketAddress().toString());
			// System.out.print("  Port Number Open: " + ServerPortNo);
			// System.out.print("  Server Status: " + RunningStatus);
			// System.out.print("  Started At: " + ServerStartTime);
			// System.out.print("  Stopped At: " + ServerStopTime);
			// System.out.println("/n");
			writeTodb(s);
		}
	}

	public void writeTodb(Socket s) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = null;

			Statement stmt1 = conn.createStatement();
			sql = "select count(*) from alldatavalues;";
			ResultSet rs5 = stmt1.executeQuery(sql);
			if (rs5.next()) {
				
				c1 = rs5.getInt("count(*)");
				System.out.println("Previous count from Db : "+c1);
				
				//System.out.println("");
			}

			sql = "insert into alldatavalues (incomingSocket,name, ServerPortNo, RunningStatus, ServerStartTime,ServerStopTime,timeStamp)"
					+ "values ('"
					+ s.getRemoteSocketAddress().toString()
					+ "','"
					+ getObjectName()
					+ "','"
					+ ServerPortNo
					+ "','"
					+ RunningStatus
					+ "','"
					+ ServerStartTime
					+ "' "
					+ ",'"
					+ ServerStopTime + "','" + GetTimestamp() + "');";
			stmt1.executeUpdate(sql);
			sql = "select count(*) from alldatavalues;";
			ResultSet rs6 = stmt1.executeQuery(sql);
			if (rs6.next())

			{
				//System.out.println("");
				
				c1 = rs6.getInt("count(*)");
				System.out.println("New count from Db : " + c1);
				
				System.out.println("Log updated...");
				System.out.println(" ");
			}

		} catch (Exception e) {
			System.out.println("This is an exception" + e);
		}

	}

	public String GetTimestamp() {
		java.util.Date date = new java.util.Date();
		Timestamp t = new Timestamp(date.getTime());
		timeStamp = t.toString();
		return timeStamp;
	}
}
