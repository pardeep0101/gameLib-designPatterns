package serverRf;
import java.net.*;
import java.util.ArrayList;
public interface ObserveServer {
	
	public void update(ArrayList<Socket> incomingSocket, int ServerPortNo, boolean RunningStatus, String ServerStartTime,String ServerStopTime);
	
	
}
