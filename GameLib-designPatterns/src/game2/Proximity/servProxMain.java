package game2.Proximity;

import java.io.IOException;

import main1.gameChain;
import main1.gameLogicAdapter;
import main1.gamelogicAdaptees;

public class servProxMain   implements gameChain{
	private gameChain nextInChain;
	public void runingServer(){
		MultiServer mS = new MultiServer();
		System.out.println(" ==============>>> Please run the Client now for Proximity Game");
		mS.startServer();
	}
	public void setNextChain(gameChain nextChain) {
		// TODO Auto-generated method stub
		nextInChain = nextChain;
	}
	public void youRunIt(String gameType) throws IOException {
		// TODO Auto-generated method stub
		if (gameType.contains("Proximity")) {
			servProxMain a = new servProxMain();
			gameLogicAdapter aa = new gamelogicAdaptees(a);
			aa.runServer();
			aa.playSound();
			aa.runDependents();
			aa.launchGame();
			aa.runGame();		
		} else {
			nextInChain.youRunIt(gameType);
		}
	}
	public void startSound(){
		System.out.println("Playing Sound");
	}
	public void runClient(){
		ProximityMain pm = new ProximityMain();	
	}
	public static void main (String args[]){
		
	}

}
