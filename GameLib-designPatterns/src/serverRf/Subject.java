package serverRf;

import java.net.Socket;

public interface Subject {

	public void register(ObserveServer o);
	public void unregister(ObserveServer o);
	public void notifyObserver();
	
}
