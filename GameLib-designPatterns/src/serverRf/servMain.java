package serverRf;

import java.io.IOException;
import java.util.Scanner;

import clientRf.GameViewer;

import main1.gameChain;
import main1.gameTemplate;

public class servMain extends gameTemplate implements gameChain {
	
	private gameChain nextInChain;
	
	Server s1;
	
	Scanner in = new Scanner(System.in);
	public void setNextChain(gameChain nextChain) {
		// TODO Auto-generated method stub
		nextInChain = nextChain;
	}

	
	public void youRunIt(String gameType) throws IOException {
		// TODO Auto-generated method stub
		if (gameType.contains("Server")) {
			runGame();
		} else {
			nextInChain.youRunIt(gameType);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		servMain sv = new servMain();
		GameViewer gV = new GameViewer();
//		sv.launchGame();
//		
//		Server s1 = new Server();
//		networkAdmin a1 = new networkAdmin(s1, "Pardeep");
//		networkAdmin soa = new networkAdmin(s1 , "Anshal");
//		Main m = new Main();
//		s1.runServer();
//		
//		
	}


	@Override
	public void runServer() {
		// TODO Auto-generated method stub
		try {
			s1 = new Server();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		System.out.println("Playing sound");
	}


	@Override
	public void runDependents() {
		// TODO Auto-generated method stub
		System.out.println("You want to add observer to your Server, say Y or N");
		String intext = in.nextLine();
		if (intext.contains("Y")) {
			networkAdmin admin1 = new networkAdmin(s1, "Pardeep NW admin");
			networkAdmin admin2 = new networkAdmin(s1, "Anshal NW admin");
			writeLogToDB wtb = new writeLogToDB(s1,"Log database");
		}
		launchGame();
		try {
			s1.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void launchGame() {
		// TODO Auto-generated method stub
		System.out.println("Server for this game is up, say yes if you autmatically run the client: ");
		String intext2 = in.nextLine();
		if (intext2.contains("Y")) {
			
			GameViewer gV = new GameViewer();
			//GameViewer gv1 = new GameViewer();
			
		}
		
	}
	boolean hasSound() {return true;}
	


}
