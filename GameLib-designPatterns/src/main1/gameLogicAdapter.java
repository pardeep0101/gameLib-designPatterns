package main1;

/*this is the basic interface for both our Template and adapter are implementing this *common interface and play with it as required.
*/

public interface gameLogicAdapter {
	
public void runServer();
public void playSound();
public void runDependents();
public void launchGame();
public void runGame();

}
