package game2;
import java.util.Random;


public class ComputerAsk {
	/**
	 * @uml.property  name="countries" multiplicity="(0 -1)" dimension="1"
	 */
	String countries[] = new String[240];
	/**
	 * @uml.property  name="numberOfCountries"
	 */
	int numberOfCountries = 240;
	/**
	 * ComputerAsk Constructor, receive an array of the Strings with the name of the countries.
	 * @param countries
	 */
	ComputerAsk(String[] countries){
		this.countries = countries;
	}
	/**
	 * This method give you a country that the computer ask.
	 * @return String with the name of the country.
	 */
	public String getCountry(){
		Random random = new Random();
		 int rand = random.nextInt(numberOfCountries);
		 String country = countries[rand];
		 eliminateCountry(country);
		return country;
	}
	/**
	 * This method eliminate a country that was selected from the user or from de computer.
	 * @param country String
	 */
	public void eliminateCountry(String country){
		int i =0;
		boolean countryFound = false;
		while(i<numberOfCountries&&!countryFound){
			
			if(country.equalsIgnoreCase(this.countries[i])){
				
				for(int j = i;j<numberOfCountries-1;j++){
					countries[j]=countries[j+1];
				}
				
				countryFound=true;
				numberOfCountries--;
			}
			else{
				i++;
			}
		}
	}
}
