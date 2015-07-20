package main1;
import game2.Proximity.*;
public class gamelogicAdaptees implements gameLogicAdapter {
	servProxMain adapt_1;
	public gamelogicAdaptees(servProxMain gla){
		adapt_1 = gla;
	}
	public void runServer() {
		System.out.println("Initializing Server..");
	}
	public void playSound() {
		// TODO Auto-generated method stub
		adapt_1.startSound();
	}
	public void runDependents() {
		// TODO Auto-generated method stub
		adapt_1.runClient();
		System.out.println("client are running fine");
	}
	public void launchGame() {
		// TODO Auto-generated method stub
		System.out.println("Server is up");
		adapt_1.runingServer();
	}
	public void runGame() {
		// TODO Auto-generated method stub
		System.out.println("Game is running");
	}

}
