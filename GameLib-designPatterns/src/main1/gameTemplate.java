package main1;

/*This is the gameTemplate for the developer so that they can simply develop there game algorithm simply using this template.
 * Which would further ease the process of adding the game in a chain to let the user play it.
 * It defines the set of rules or procedure which should be followed while developing game for our library
 * 
 */
abstract public class gameTemplate implements gameLogicAdapter {
 public final void runGame(){
	if(hasServer() == true){runServer();}
	if(hasSound() == true){playSound();}
	if(hasDependents() == true){runDependents();}
	launchGame();
 }
 abstract public void runServer();
 abstract public void playSound();
 abstract public void runDependents();
 abstract public void launchGame();
 boolean hasServer () {return true;}
 boolean hasSound () {return true;}
 boolean hasDependents(){return true;}
}
