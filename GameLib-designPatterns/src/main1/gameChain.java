package main1;

import java.io.IOException;

/*
 * This is the gameChain interface which itself is a inclusive part of Chain of Responsibility Pattern.
 * It provides the chain of different games available in library
 * It help in rotating the turn to each of its implementing class according to input form client
 */
public interface gameChain {

	public void setNextChain( gameChain nextChain);
	
	public void youRunIt(String gameType) throws IOException;
	
	
}
