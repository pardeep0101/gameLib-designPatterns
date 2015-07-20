package game2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadAllTheCountries {
	/**
	 * @uml.property  name="url"
	 */
	private URL url;
	/**
	 * @uml.property  name="counter"
	 */
	private int counter = 0;
	/**
	 * @uml.property  name="reader"
	 */
	private InputStreamReader reader;
	/**
	 * @uml.property  name="in"
	 */
	private BufferedReader in;
	/**
	 * @uml.property  name="i"
	 */
	private int i = 0;
	/**
	 * @uml.property  name="j"
	 */
	private int j;
	/**
	 * @uml.property  name="m"
	 */
	private int m = 0;
	/**
	 * @uml.property  name="countries" multiplicity="(0 -1)" dimension="1"
	 */
	private String[] countries;
/**
 * This Constructo go to the URL http://en.wikipedia.org/wiki/List_of_countries_by_population and save all the countries in a array.
 * @throws IOException
 */
	ReadAllTheCountries() throws IOException {

		countries = new String[240];

		url = new URL("http://en.wikipedia.org/wiki/List_of_countries_by_population");
		reader = new InputStreamReader(url.openStream());
		in = new BufferedReader(reader);
		
		int numberOfCountries =0;
		String line = null;
		boolean begindFound = false;
		int m = 300;

		while (!begindFound) {
			if ((line = in.readLine()).contains("<td>1</td>")) {
				begindFound = true;
			}
		}
		while(numberOfCountries<240){
			line = in.readLine();
			if(line.contains("<a href=\"/wiki/")&&!line.contains("Ã")){
				
				while(!line.substring(m,m+1).equals("<")||!line.substring(m+1,m+2).equals("/")||!line.substring(m+2, m+3).equals("a")){
					m++;
				}
				j=m;
				while(!line.substring(j-1,j).equals(">")){
					j--;
				}
				if(!line.substring(j, m).contains("(")){
					countries[numberOfCountries] =line.substring(j, m);
					//System.out.println(countries[numberOfCountries] );
					numberOfCountries++;
					
					m=0;
				}
				
			
				
				
			}
		}

		

	}
		
	
/**
 * This method return a country that is in the index that you select.
 * @param index the number in the position of the array.
 * @return String
 * @throws IOException
 */
	public String getList(int index) throws IOException {
		return countries[index];
}
}
