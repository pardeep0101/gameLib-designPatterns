package main1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class welcome_panel extends JPanel{
public  JFrame jf = new JFrame();
public  JPanel jpLeft;
public JPanel jpRight;
public JPanel jpTop;
public JPanel jpBottom;
public JPanel jpCenter;
public JPanel cpane = new JPanel();
public JButton button ;
public JButton sendButton;
public JTextArea jtf = new JTextArea(20,20);
public static String userInput = "Enter the name of game you want to play";
private static JTextField input = new JTextField(userInput);
private static JLabel jl1 = new JLabel("Enter Country Game or Server Game or Proximity");
	
welcome_panel(){

			}
	
	public void drawgui(){	
		this.setLayout(new BorderLayout());
		//cpane.setLayout(new BorderLayout());
		//cpane.setBackground(Color.BLUE);
		jf.add(this);
		jf.setTitle("Welcome screen");
		jf.setSize(600, 600);
		jpTop();
		jpBottom();
		jpLeft();
		jpRight();
		jpCenter();
		
		System.out.println("run orignale");
		jf.setResizable(false);
		jf.setLocation(400,30);
		
	}
	public void jpLeft(){

		jpLeft = new JPanel();
		jpLeft.setLayout((new BoxLayout(jpLeft, BoxLayout.Y_AXIS)));
		jpLeft.setBackground(Color.orange);
		jpLeft.setSize(100, 100);
		//jp.add(new JButton("Load Games"));
		JLabel jlLeft = new JLabel("Games Available:");
		JLabel jlLeft1 = new JLabel("Country Game");
		JLabel jlLeft2 = new JLabel("Server Game");
		JLabel jlLeft3 = new JLabel("Proximity");
		jpLeft.add(jlLeft);
		jpLeft.add(jlLeft1);
		jpLeft.add(jlLeft2);
		jpLeft.add(jlLeft3);
		this.add(jpLeft, BorderLayout.LINE_START);
		
	}
	public static String getUserInput(){
		return input.getText();
	}
	public void setUserLabel(String inJ){
		jl1.setText(inJ);
	}
	public void jpRight(){

		jpRight = new JPanel();
		jpRight.setBackground(Color.RED);
		jpRight.add(new JButton("Refresh Library"));
		this.add(jpRight, BorderLayout.LINE_END);
		
	}
	public void jpCenter(){


		jpCenter = new JPanel();
		//jp.setSize(400, 400);
		
		jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.PAGE_AXIS));
		jpCenter.setBackground(Color.GREEN);
		jpCenter.setBounds(0,0, 20, 20);
		JPanel userInputPanel = new JPanel();
		userInputPanel.setLayout(new BoxLayout(userInputPanel, BoxLayout.PAGE_AXIS));
		System.out.println(input.getSize());
		
		
		input.setMaximumSize(new Dimension(Integer.MAX_VALUE, input.getMinimumSize().height));
        input.setLocation(1, 1);
			input.addMouseListener(new MouseAdapter(){
	            @Override
	            public void mouseClicked(MouseEvent e){
	                input.setText(""); // this will delete text when user clicks text field
	            }
	        });
			userInputPanel.add(jl1);
			userInputPanel.add(input);
			sendButton = new JButton("Send");
			sendButton.setActionCommand("send1");
			sendButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
				JButton clickedButton = (JButton) e.getSource();
				if(clickedButton.getActionCommand().equalsIgnoreCase("send1")){
					jf.setVisible(false);
					jf.dispose();
					String s1 = welcome_panel.getUserInput();
					runProM.setGameInput(s1);
				
					
				}
			}});
			
			
			userInputPanel.add(sendButton);
		
		jpCenter.add(userInputPanel);
		//jp.setLayout(new BoxLayout(),);
		//jp.add(new JButton("Fooetr and registration info"));
		//jp.setBackground(Color.DARK_GRAY);
		jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.X_AXIS));
		//System.out.println(jpCenter.getSize());
		//jpCenter.add(jtf);
		this.add(jpCenter, BorderLayout.CENTER);
		
		
	}
	public void jpTop(){
		jpTop = new JPanel();
		jpTop.add(new JButton("Logo & Header"));
		jpTop.setBackground(Color.DARK_GRAY);
		this.add(jpTop, BorderLayout.PAGE_START);
		
	}
	public void jpBottom(){

		jpBottom = new JPanel();
		jpBottom.add(new JButton("Fooetr and registration info"));
		jpBottom.setBackground(Color.DARK_GRAY);
		this.add(jpBottom, BorderLayout.PAGE_END);
		
	}
	public static void main(String args[]){
		welcome_panel wp = new welcome_panel();
		//wp = new decorator1(wp);
		wp.drawgui();
		
	}
}

