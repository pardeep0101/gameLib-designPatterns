package game2.Proximity;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * This class sets up the very first panel for this game
 * 
 *
 */
public class ProximityPanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	private static JPanel top;//top panel that holds the name of the game
	private static JPanel bottom;//bottom panel that holds the name of the player and start button
	public static JLabel welcome; //Label that holds the name of the game
	public static JLabel player;//label that prints player name
	
	public static JTextField playerT;// textField that holds the name of the player

	public static JButton start;//button that starts the game
	/**
	 * @uml.property  name="listener"
	 */
	public ActionListener listener;//listener object from class actionListener
	
	/**
	 * constructs an object for class ProximityPanel.
	 */
	
	public ProximityPanel(){
		super();
		drawGUI();
	}	
	/**
	 * It draws the GUI in this panel
	 */
	
		private void drawGUI() {
			// TODO Auto-generated method stub
			
		//Layout for the JPanel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));// it sets the super panel to a y axis orientation
	
		top=new JPanel();//top panel that holds the welcome message
		
		welcome=new JLabel("Welcome to Game Get&Match"); //creates a welcome JLable
		
		top.add(welcome);
		bottom=new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
		player=new JLabel("Player Name");
		playerT=new JTextField();
		playerT.setColumns(10);
		start=new JButton("Start");
	
		JPanel rules=new JPanel();// Holds the rules of the game
		rules.add(new JLabel("The objective of the game is to have the most armies when all of the tiles have been placed on the")); 
				        
		rules.add(new JLabel(" board. On each turn, you are given a numbered tile which you must place on a position that is n't"));		                
	
		rules.add(new JLabel( " already occupied If it is adjacent to ally tiles, it will strengthen their defenses. If any enemy tiles are"));		            
	
		rules.add(new JLabel("adjacent to it it will take control of them if its number is higher than the enemy tile.The grid will be "));		
		rules.add(new JLabel("n x n where n is random number between 8 and 12, inclusively."));
	
		bottom.add(player);	//player Jlabel is added here
		bottom.add(playerT);//player textfield is added here
		bottom.add(start);//start button is added here
	
		this.add(top);//top panel is added to JFrame
		this.add(bottom);//bottom panel is added to the JFrame
		this.add(rules);// label that holds the rules of the game is added here
		
		listener=new ProximityActionListener();//listener object from class proximityActionListener
		start.setActionCommand("start");// and action command is assigned to the button "Start"
		start.addActionListener(listener);// action listener is added to the button
		
	
		

}




}

