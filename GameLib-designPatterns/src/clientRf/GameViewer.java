package clientRf;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ServerSocket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import serverRf.Server;

/**
 * Handles and builds the GUI of our Rawfongo game.
 * 
 *
 */
public class GameViewer extends JPanel {
	
	private static String initialServerText = "Let's play a game of Rawfongo.";
	private static String userInput = "Write a number between 60 and 70.";
	private static JButton sendButton = new JButton("Send");	
	private static JTextField input = new JTextField(userInput);
	private static JLabel serverResponse = new JLabel(initialServerText);
	private static JLabel userResponse = new JLabel();
	private static JFrame frame = new JFrame("Rawfongo");	
	private GameListener listener = new GameListener();
	public GameViewer(){
		frame.revalidate();
		frame.setSize(600, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		drawGUI();
		
	}
	/**
	 * Obtains the user input.
	 * @return the current text in the user input text field
	 */
	public static String getUserInput(){
		return input.getText();
	}
	/**
	 * Updates the text shown as the user response.
	 */
	public static void updateUserResponse(String s){
		if(userResponse.getText().equals("")) { //if no previous text
			userResponse.setText(s);
		}
		else {
			String previousResponse = userResponse.getText();
			userResponse.setText(previousResponse + ", " + s);
		}	
	};
	
	/**
	 * Updates the text shown as the server response.
	 */
	public static void updateServerResponse(String s){
		if(serverResponse.getText().equals(initialServerText)) {
			serverResponse.setText(s);
		}
		else {
			String previousResponse = serverResponse.getText();
			serverResponse.setText(previousResponse + ", " + s);
		}		
	}

	/**
	 * Takes care of drawing the GUI.
	 */
	private void drawGUI() {
		// setting the layout of GamePanel
		this.setLayout(new BorderLayout());
		
		// panel that will contain server img and response
		JPanel serverOutputPanel = new JPanel();
		serverOutputPanel.setLayout(new BoxLayout(serverOutputPanel, BoxLayout.X_AXIS));
		
		// label that will show server clip art
		ImageIcon serverIcon = new ImageIcon(GameViewer.class.getResource("/serverClp.png")); 
		JLabel serverImg = new JLabel();
		serverImg.setIcon(serverIcon);
		serverOutputPanel.add(serverImg);
		
		// panel that will contain label "server response" and the bubble with server response
		JPanel serverResponsePanel = new JPanel();
		serverResponsePanel.setLayout(new BoxLayout(serverResponsePanel, BoxLayout.Y_AXIS));
		
		// label that will show server response
		ImageIcon bubbleIcon = new ImageIcon(GameViewer.class.getResource("/bubbleImg.png")); 
		serverResponse.setIcon(bubbleIcon);

		serverResponse.setIconTextGap(-300);
		serverResponse.setOpaque(true);
		serverResponse.setLayout(null);
		
		serverResponsePanel.add(serverResponse);
		serverOutputPanel.add(serverResponsePanel);
		
		this.add(serverOutputPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		
		ImageIcon userBubbleIcon = new ImageIcon(GameViewer.class.getResource("/bubbleImg.png")); 
		userResponse.setIcon(userBubbleIcon);
		
		userResponse.setIconTextGap(-300);
		userResponse.setOpaque(true);
		userResponse.setLayout(null);		
		
		bottomPanel.add(userResponse);
		
		// panel that will contain user input
		JPanel userInputPanel = new JPanel();
		userInputPanel.setLayout(new BoxLayout(userInputPanel, BoxLayout.X_AXIS));
		
		// text field that will obtain user input
        input.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                input.setText(""); // this will delete text when user clicks text field
            }
        });
		userInputPanel.add(input);
		
		// button to submit
		
		sendButton.addActionListener(listener);
		sendButton.setActionCommand("send");
		
		userInputPanel.add(sendButton);
		
		bottomPanel.add(userInputPanel);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		
	}
	
	public static void main (String[] args){			
		new GameViewer();
		
		
	}
}