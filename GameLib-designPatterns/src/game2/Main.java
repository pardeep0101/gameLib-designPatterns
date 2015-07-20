package game2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.Timer;

import serverRf.Server;
import serverRf.networkAdmin;

import main1.gameChain;
import main1.gameTemplate;

public class Main extends gameTemplate implements gameChain{
	public static boolean answer = false;
	public static boolean timeOut = false;
	static String country1;
	static ActionListener listener= new Time();
	final static int DELAY = 15000;
	public static Timer timer = new Timer(DELAY,listener);
	private gameChain nextInchain;
	public void setNextChain(gameChain nextChain) {
		// TODO Auto-generated method stub
		nextInchain = nextChain;
	}

	public void youRunIt(String gameType) throws IOException {
		// TODO Auto-generated method stub
		if(gameType.contains("Country")){
			super.runGame();
			}
			else{
				nextInchain.youRunIt(gameType);
			}
		}
		
	public static void main(String[] args) throws IOException {
		
		try{
			new Main();
		}
		catch(Exception e){System.out.println(e);}
	}
	
	public Main(){	
		
}
	public void runMainGame()  throws IOException{
		String countries[] = new String[240];
		ComputerAsk computer = new ComputerAsk(countries);
		URL url;
		Scanner scanner = new Scanner(System.in);
		String country;
		int i = 0, m = 0, askedIndex = 0;
		boolean badInput = false, countryFound = false,end = false;
		
		String asked[] = new String[19];
		
		ReadAllTheCountries countriesList = new ReadAllTheCountries();
		for (int j = 0; j < 240; j++) {
			countries[j] = countriesList.getList(j);
			
		}

		System.out.println("This is a game to practice and learn the capital, "
				+ "population and area of differents countries");

		while (i < 19 && !badInput&&!end) {
			

			if (i % 2 == 0) {
				i++;
				System.out.println("Enter the country:");
				country = scanner.nextLine();
				for (int index1 = 0; index1 <= askedIndex; index1++){
					if(country.equalsIgnoreCase(asked[index1])){
						badInput = true;
						
				}
					
				}
					if(!badInput&&!end){
						while (!countryFound && m < 235) {
							
							if (country.equalsIgnoreCase(countries[m])) {
								
								countryFound = true;
								asked[askedIndex] = country;
								askedIndex++;
							} else {
								m++;
							}

						}
						country = country.replace(" ","_");
						if (countryFound) {
							try {
								url = new URL("http://en.wikipedia.org/wiki/"
										+ country);
								CapitalCountry capital = new CapitalCountry(url);
								Population p = new Population(url);
								Area a = new Area(url);
								

								try {if(i==18){
									System.out.println("Game over. You won. By the way, this is the answer to your question:");
									System.out.println("Capital: "
											+ capital.getCapital());
									System.out.println("Population: ~ "
											+ p.populationRoundWithComma()+" million");
									System.out.println("Area: ~ "
											+ a.areaRoundWithComma()
											+ "x10000 km^2");
								}else{
									System.out.println("Capital: "
											+ capital.getCapital());
									System.out.println("Population: ~ "
											+ p.populationRoundWithComma()+ " million");
									System.out.println("Area: ~ "
											+ a.areaRoundWithComma()
											+ "x10000 km^2");
									}
								} catch (IOException e) {
									System.out.println("Bad spelling");
									badInput = true;
								} catch (NullPointerException event1) {
									System.out.println(event1);
									System.out
											.println("Wrong input! You lost. Game over.");
									badInput = true;
								}
							} catch (MalformedURLException e) {

							}
							countryFound = false;
						} else {
							System.out.println("Wrong input! You lost. Game over.");
							badInput=true;
						}
			}
					
					computer.eliminateCountry(country);
			

			}else if(!badInput&&!end){
				boolean space = false, pass = false;
				String countryInfo = null;
				String capital = null;
				String population = null;
				String area = null;
				country1 = computer.getCountry();
				country = country1;
				System.out.println("\n"+country);
				country = country.replace(" ", "_");
				
				timer.restart();
				timer.start();
				while(!answer ){
					if(!timeOut){
				countryInfo = scanner.nextLine();
				answer = true;
					}
					else{
					answer =true;
					}
				}
				timer.stop();
				try {
					if(timeOut){
						end = true;
					}
					
					url = new URL("http://en.wikipedia.org/wiki/"
							+ country);
					CapitalCountry c = new CapitalCountry(url);
					Population p = new Population(url);
					Area a = new Area(url);
					countryInfo = countryInfo.trim();
					
					int length = countryInfo.length();
					int index=0;
						while (!Character.isDigit(countryInfo.charAt(index))){
							index++;
						}
						capital= countryInfo.substring(0, index).trim();
						if(capital.equalsIgnoreCase(c.getCapital())){
							countryInfo = countryInfo.substring(index).trim();
							space=false;
							index=0;
							while(index<length-1&&!space){
								
								if(countryInfo.substring(index, index+1).equals(" ")){
									population = countryInfo.substring(0, index);
									space = true;
									}
								else{
									index++;
								}
							}
							pass = true;
						}
					
						if(Integer.parseInt(population)/1000000==p.populationRound()&&pass){
							
								area = countryInfo.substring(index).trim();
								pass = false;
									
							}
						if(Integer.parseInt(area)/10000==a.areaRound()){
							pass=true;
						}
						if(pass==true){
							i++;
						}
						else{
							

						}
						
						
					}
				catch (MalformedURLException e) {

				}
				catch(NumberFormatException event){
					URL url2 = new URL("http://en.wikipedia.org/wiki/"+country1);
					CapitalCountry c = new CapitalCountry(url2);
					Population p = new Population(url2);
					Area a = new Area(url2);
					System.out.println("Wrong input! You lost. Game over. By the way, the right answer is"); 
					System.out.println("Capital: " + c.getCapital()); 
					System.out.println("Population: ~ "+p.populationRoundWithComma()+" million");
					System.out.println("Area: ~ "+a.areaRoundWithComma()+" km^2");
					end = true;
				}
				catch(NullPointerException event){
					if(end)
						System.out.println("The program has already finnished");
					else{
						System.out.println("Wrong input, you lost!");
						end = true;
					}
					
				}
				catch(StringIndexOutOfBoundsException event){
					System.out.println("Wrong input, you lost!");
					end = true;
				}
				if(badInput&&!end){
					System.out.println("This country was already discussed. You lost. Game over.");

				}
			}
		}
	}

	@Override
	public void runServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runDependents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launchGame() {
		// TODO Auto-generated method stub
		try {
			runMainGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	boolean hassound(){return false;}
	boolean hasDependents(){return false;}
	boolean hasServer () {return false;}
	}
	
	
	