package game2.Proximity;
import java.io.IOException;
import java.util.Random;


public class Logistics {

	private static String[] player=new String[2];// Name of players .
	//=private static String player2;// Name of player 2.
	private static int[] hexList=new int[144];// Holds the number of armies in each hexagon.
	private static int turn=1;//first play is after next turn is called
	private static int d;// The size of the board is determined by this number.  The size of the board is (d*d).
	private static int r;//random number dealt [1-20]
	private static int ID;//blue=0, red=1



	public Logistics(){

	}                                           

	public static String getPlayer(int i) {
		return player[i%2];
	}
	public static void setPlayer(int i,String player) {
		Logistics.player[i%2] = player;
	}
	/**
	 * this method prints the turns 
	 * @returns mod either values 0 and 1. 0 and 1 indicate the turns for player 1 and 2, respectively.
	 **/
	public static int nextTurn(){
		int t=++turn;
		int mod=t%2;
		setR(ProximityMain.random.nextInt(20)+1); 
		return(mod);

	}
	/**
	 * This method determines the turn of the players
	 * @return 0 for first turn and 1 for the second turn
	 */
	public static int getTurn(){
		return turn%2;

	}
	/**
	 * This method determines the value of the tile on each hexagon
	 * @param location of the tile is passed unto this methos
	 * @return it returns the value of hexagon at position (i-1).
	 */

	public static int getHex(int l) {
		return hexList[l-1];
	}
	/**
	 * 
	 * @param l location of the hexagon where tile is placed
	 * @param n  it sets the value of hexagon at position (i-1)to the generated random number n.
	 */
	public static void setHex(int l,int n ) {

		hexList[l-1]=n;		
	}
	/**
	 * 
	 * @param c is code for team color
	 * @return sum of tiles placed for each color
	 */
	public static int getTotal(int c){
		int sum=0;
		for (int k=1;k<=144;++k){
			int gh=getHex(k);
			if (c==0 && gh<0)	sum-=gh; 
			if (c==1 && gh>0)	sum+=gh; 

		}
		return sum;
	}
	/**
	 * This method counts the number of tiles placed for each player
	 * @param p is 0 for blue or 1 for red.  
	 * @return it counts how many tiles have been played by each player
	 */
	public static int getCounter(int p){
		int counter=0;
		for (int k=1;k<=144;++k){
			int gh=getHex(k);
			if (p==0 && gh>0)	counter+=1; 
			if (p==1 && gh<0)	counter+=1; 

		}
		return counter;
	}
	/**
	 * it updates the tiles surrounding the newly placed tile by the player
	 * @param l hexagon location where tile is placed
	 */
	public static void updatePlay(int l){
		//adjust to array which starts with zero
		int[] x=new int[6];
		int[] o=new int[6];
		o[2]=-1;
		o[3]=+1;

		if (((l-1)/d)%2==0){

			o[0]=-d-1;
			o[1]=-d;
			o[4]=d-1;
			o[5]=d;
		}
		else
		{
			o[0]=-d;
			o[1]=-d+1;
			o[4]=d;
			o[5]=d+1;
		}
		for(int i=0;i<6;i++)x[i]=l+o[i];

		if(x[0]<1 || l%(2*d)==1) x[0]=-1;
		if(x[1]<1 || l%(2*d)==((2*d))) x[1]=-1;
		if(l%d==1)x[2]=-1;
		if (l%d==d) x[3]=-1;
		if (x[4]>d*d || l%(2*d)==1) x[4]=-1;
		if (x[5]>d*d || l%(2*d)==((2*d))) x[5]=-1;



		for (int i=0;i<6;++i){
			if (x[i]!=-1){
				adjustArmies(l,x[i]);
				//setHex(x[i],i+1);
			}
		}

	}
	/**
	 * 
	 * @param l is the position number for the tile placed on the board.
	 * @param x is the position of the surrounding hexagons to l position
	 */
	private static  void adjustArmies(int l, int x){


		int	ghx=getHex(x); // value of hexagons surrounding the placed tile.   This could be a negative value (blue tile) or a positive value for (red tile)
		int ghl=getHex(l); // value of the tile placed.  This could be a negative value (blue tile) or a positive value for (red tile)

		//value of the surrounding haxagons are muliplied by (-1) if taken over by a placed tile 

		if (getHex(l)*getHex(x)<0 && Math.abs(getHex(l))>Math.abs(getHex(x))){
			ghx=ghx*(-1);
			setHex(x,ghx);
			return;
		}
		//if (getHex(l)*getHex(x)<0 && getHex(l)<=getHex(x));
		//Nothing happens

		if (getHex(l)*getHex(x)>0){
			if (ghx<0) ghx-=1;
			if (ghx>0) ghx+=1;
			if (ghx>20)	ghx=20;
			if (ghx<-20)ghx=-20;

			setHex(x,ghx);

		}



	}

	/**
	 * 
	 * @return value d which is either 8,9,10,11 and 12.  d indicates the size of the board,d*d);
	 */
	public static int getD() {
		return d;
	}
	/**
	 * this method generates a random number 0,1,2,3,4 and adds
	 *  integer 8 to them random numbers 8,9,10,11 and 12.  d is the width and height of the board
	 */
	public static void setD(int dd) {
		d=dd;
	}
	/**
	 * 
	 * @returns r random number of armies for each turn 
	 */
	public static int getR() {
		return r;
	}
	/**
	 * 
	 * @param r stores the random number of new armies
	 */
	public static void setR(int r) {
		Logistics.r = r;
	}
	/**
	 * this method is used to determine whose turn it is
	 * @return ID value that could be zero or 1
	 */
	public static int getID() {
		return ID;
	}
	/**
	 * sets the ID of the players
	 * @param id determines whose turn it is, blue for zero and red for one
	 */
	public static void setID(int id) {
		ID = id;
	}
	/**
	 * This method determines who won.
	 * @return 1 if blue won, 2 if red won and zero if nobody has won yet
	 */

	public static int end(){
		if(getCounter(0)+getCounter(1)==getD()*getD()){
			if(Math.abs(getTotal(1))>Math.abs(getTotal(0)))return 1;//blue won
			else return 2;//red won
		}
		else return 0;

	}
	/**
	 * This method prints and read location of the tile played and its value to the server
	 */
	public static void serverListener(){// updates similar to MouseListener
		int l=0;
		try {
			l = Integer.parseInt(ProximityMain.br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rr=0;
		try {
			rr = Integer.parseInt(ProximityMain.br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//The next line is only place that updates the board

		nextTurn();
		setHex(l, rr);//The tile is placed on the board in position l and value rr
		updatePlay(l);//if flag is zero played gets updated and  a new game panel is created
		if(l==0)System.out.println("Did not receive correct play");
		ProximityMain.frame.remove(ProximityMouseListener.gamePanel.bot);// bottom panel is removed from the frame
		ProximityMain.frame.remove(ProximityMouseListener.gamePanel.gpanel);//graphic panel is removed from the frame
		//New GamePanel is created
		ProximityMouseListener.gamePanel=new GamePanel();

	}

}
