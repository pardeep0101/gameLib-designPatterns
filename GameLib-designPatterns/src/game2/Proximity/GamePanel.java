package game2.Proximity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * 
 * @author Ramineh Lopez
 *	GamePanel class extends JPanel and presents the main game board 
 */

public class GamePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static ProximityComponent component;//object of class proximityComponent where the hexagons are created
	public static JPanel gpanel;//Top panel where the game board is placed
	public static JPanel bot;//Bottom panel where the rest of the panel is placed
	public static JLabel redN;//Button where the random number is placed for the second player
	public static JLabel blueN;//Button where the random number is placed for the first player
	/**
	 * @uml.property  name="message"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	public JLabel message;
  
	public static JLabel redTotal;// Total number of armies for the second player
	public static JLabel blueTotal;//Total number of armies for the first player
	/**
	 * it constructs game Panel
	 */
	public GamePanel(){
	
		component = new ProximityComponent();//the object component from class Proximitycomponnent is initialized here
		gpanel = new JPanel();// panel where the polygons appear

		gpanel.setLayout(new BorderLayout());// Layout of the gpanel is set here
		gpanel.add(component,BorderLayout.CENTER);
		gpanel.setBounds(0, 0, 400, 700);
		
		bot = new JPanel();//bottom panel where the buttons and text fields are

		bot.setLayout(new GridLayout(4,5));//sets the layout of this panel

		JTextField red = new JTextField(Logistics.getPlayer(1));//Holds the name of the player 2
		red.setEditable(false);
		red.setBackground(Color.RED);
		red.setForeground(Color.WHITE);
		red.setFont(new Font("Arial", Font.BOLD, 16));

		
		redN = new JLabel();//Here the random numbers to be played are presented in color Red
		redN.setHorizontalAlignment(SwingConstants.CENTER);
		redN.setForeground(Color.RED);
		redN.setFont(new Font("Arial", Font.BOLD, 20));
		

		
		message=new JLabel("click hex number");//Message label that gives instructions
		message.setForeground(Color.GRAY);
		message.setHorizontalAlignment(SwingConstants.CENTER);

		JTextField blue = new JTextField(Logistics.getPlayer(0));//Holds the name of the player 1
		blue.setBackground(Color.BLUE);
		blue.setEditable(false);
		blue.setForeground(Color.WHITE);
		blue.setFont(new Font("Arial", Font.BOLD, 16));
			
		blueN = new JLabel();//Here the random numbers to be played are presented in color blue
		blueN.setHorizontalAlignment(SwingConstants.CENTER);
		blueN.setForeground(Color.BLUE);
		blueN.setFont(new Font("Arial", Font.BOLD, 20));
		
		//Total number of armies and number of tiles taken are presented here for blue and red
		
		redTotal=new JLabel("Tot "+String.valueOf(Logistics.getTotal(0))+" Cnt "+String.valueOf(Logistics.getCounter(1)));
		redTotal.setBackground(Color.BLUE);
	
		blueTotal=new JLabel("Tot "+String.valueOf(Logistics.getTotal(1))+" Cnt "+String.valueOf(Logistics.getCounter(0)));
		
		
		int id=Logistics.getID();//local variabble that hold the ID of the player that could be zero or 1.  Zero means blue and 1 means red
		int turn=Logistics.getTurn();//local variable that holds the value of 0, your turn, 1, the other player's turn
	    // if it is blue's turn and it is your turn.
		if (turn==0){
			if(id==0){
				gpanel.addMouseListener(new ProximityMouseListener());//Mouselistner is added to the panel
			
				blueN.setText(String.valueOf(Logistics.getR()));//The random value between 1 and twenty is placed in the blue JLabel
			}
			else {
				blueN.setText("waiting");// if turn is zero, it means that blue is playing in this turn
				message.setText("waiting");//waiting for the other player to finish playing
			}
			redN.setText("");//red JLabel is blank while blue is playing its turn
		}
		else{//if turn 1 and player number two is playing
			if(id==1){
				gpanel.addMouseListener(new ProximityMouseListener());//mouselistener is activated on this gPanel
				redN.setText(String.valueOf(Logistics.getR()));//red Jlabel receives a random number between 1 and twenty.
			}
			else {
				redN.setText("waiting");// if turn is 0, it means that blue is playing in this turn
				message.setText("waiting");// if turn 
			}
			blueN.setText("");//your blue JLabel is empty
		}
		
		
		//all the Jlabels are added to the bottom JPanel		
	
		bot.add(blue);
		bot.add(blueN);
		bot.add(message);
		bot.add(redN);
		bot.add(red);
		bot.add(blueTotal);
		bot.add(new JLabel(" "));
		bot.add(new JLabel(" "));
		bot.add(new JLabel(" "));
		bot.add(redTotal);
		//blanks are added to fill the space
		for(int ii=0;ii<10;ii++)bot.add(new JLabel(" "));
		
		//If method end returns 1, it indicates that blue has won
		if(Logistics.end()==1)message.setText("Blue won!");
		//If method end returns 2, it indicates that red has won
		if(Logistics.end()==2)message.setText("Red won!");
		if(Logistics.end()==1||Logistics.end()==2){
			message.setFont(new Font("Arial", Font.BOLD, 20));
			blueN.setText("");
			redN.setText("");
		}
		//graphic panel and bottom panels are added to JFrame 
		ProximityMain.frame.add(gpanel, BorderLayout.CENTER);
		ProximityMain.frame.add(bot, BorderLayout.SOUTH);
		
		//JFrame is updated and packed
		ProximityMain.frame.revalidate();
		ProximityMain.frame.pack();
		
		//size and the position of the JFrame is presented here, for the first player
		ProximityMain.frame.setBounds(10,10,500,500);
		//the position and the size of the Jframe is presented here for the second player
		if (Logistics.getID()==1) ProximityMain.frame.setBounds(550, 10, 500, 500);
	
		ProximityMain.frame.setVisible(true);

		//if it is not your turn, a messaage "waiting" is printed 
		//and waits for reply from other player
		if(turn!=id&&Logistics.end()==0){
			message.setText("waiting");
			//if ServerListener method which waits for the response from the server
			//is not put in a new thread, the program pauses and will not update the 
			//graphics in GamePanel properly.
			Thread t = new Thread(new Runnable() {
			    public void run() {
					Logistics.serverListener();
			    } 
			});
			t.start();
		}



	}

}
