package game2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Population {
	/**
	 * @uml.property  name="reader"
	 */
	InputStreamReader reader;
	/**
	 * @uml.property  name="in"
	 */
	BufferedReader in;
	/**
	 * @uml.property  name="url"
	 */
	URL url;
	/**
	 * @uml.property  name="i"
	 */
	int i = 0;
	/**
	 * @uml.property  name="j"
	 */
	int j;
/** 
 * This is the population constructor that receive an URL of the country that we want to know the population.
 * @param url
 */
	Population(URL url) {
		this.url = url;
	}
/**
 * This method give you the line where is the population
 * @return String ( line fo the population)
 * @throws IOException
 * @throws NullPointerException
 */
	private String populationLine() throws IOException, NullPointerException {
		reader = new InputStreamReader(url.openStream());
		in = new BufferedReader(reader);
		String populationLine = null;

		boolean pLineFound = false;
		
		

		while (!pLineFound) {
			if ((populationLine = in.readLine()).contains("Population")) {
			
				while (!pLineFound) {
					if ((populationLine = in.readLine()).contains("<td>")
							&& populationLine.contains("</td>")) {
						pLineFound = true;
					}
				}
			}

		
		}

		return populationLine;
	}
/**
 * This method give you the population.
 * @return String population.
 * @throws NullPointerException
 * @throws IOException
 */
	private String cleanPopulationLine() throws NullPointerException,
			IOException {
		String population = populationLine();
		boolean populationFound= false;
		int k = 4, m = k;
		
		while (!populationFound){
			if(population.substring(m, m + 1).equals("<")||population.substring(m, m + 1).equals(" ")){
				populationFound = true;
			}else{
				m++;
			}
		}
		population = population.substring(k, m);
		return population;

	}
/**
 * This method give you the population rounded.
 * @return int (number of the population rounded).
 * @throws NullPointerException 
 * @throws IOException
 */
	public int populationRound() throws NullPointerException, IOException {
		String cleanLine = cleanPopulationLine();
		String numberRemoveComa = cleanLine.replaceAll(",", "");
		int number = Integer.parseInt(numberRemoveComa);
		double roundNumber = number / 1000000.0;
		int numberRound;
		if ((roundNumber - (int) roundNumber) > .5) {
			roundNumber = (int) roundNumber + 1;
		}
		numberRound = (int) roundNumber;
		
		return numberRound;

	}
	/**
	 * This method give you the String of population rounded with comma.
	 * @return String
	 * @throws NullPointerException
	 * @throws IOException
	 */
	public String populationRoundWithComma() throws NullPointerException, IOException{
		String populationRound = Integer.toString(populationRound());
		if (populationRound.length() > 3) {
						
				populationRound = populationRound.substring(0, 1)+","+populationRound.substring(populationRound.length()-3);
			
		}
		return populationRound;
	}
}