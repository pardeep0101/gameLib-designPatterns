package serverRf;
import java.io.BufferedReader;
//import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class Player {
	/**
	 * @uml.property  name="req"
	 */
	private BufferedReader req;
	/**
	 * @uml.property  name="res"
	 */
	private PrintStream res;
	
	
	public Player(Socket c) throws IOException {
		req= new BufferedReader(new InputStreamReader(c.getInputStream()));
        res = new PrintStream(c.getOutputStream());
	}
	
	public boolean ready() throws IOException{
		return req.ready();
	}
	
	public int request() throws IOException
	{
		return Integer.parseInt(req.readLine());
	}
	
	public void respond(int r) throws IOException
	{
		res.println(r);
	}
}
