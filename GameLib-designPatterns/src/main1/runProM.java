package main1;
import game2.Main;
import game2.Proximity.servProxMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import serverRf.servMain;

public class runProM  {
	private static String text = "";
	public static JFrame jf ;
	public static void setGameInput(String inG){
		text = inG;System.out.println(text);
		
		gameChain g1 = new Main();
		gameChain g2 = new servMain();
		gameChain g3 = new servProxMain();
		g1.setNextChain(g2);
		
		try{
			g2.setNextChain(g3);
		}
		catch(Exception e){System.out.println("No game with this name");}
		System.out.println("you asked for : " + text);
		try {
			g1.youRunIt(getGameInput());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	public static String getGameInput(){return text;}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final JFrame jfM = new JFrame("Enter Your Choice");
		jfM.setSize(400,150);
		jfM.setLocation(400, 50);
		JPanel jp = new JPanel();
		final JTextField htf = new JTextField(30);
		JButton jb = new JButton("Send");
		jb.setActionCommand("Send");
		jb.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
			JButton clickedButton = (JButton) e.getSource();
			if(clickedButton.getActionCommand().equalsIgnoreCase("send")){
				jfM.setVisible(false);
				jfM.dispose();
				String t1 = htf.getText();
				setDecoratorText(t1);
				
			}
		}});
		
		JLabel jl = new JLabel("Tell us how you wanna decorate your welcome screen.......");
		JLabel jl2 = new JLabel("Choices ar: Advanced User, GameAddictBoy, GameAddictGirl");
		jp.add(jl);
		jp.add(jl2);
		jp.add(htf);
		jp.add(jb);
		jfM.add(jp);
		jfM.setDefaultCloseOperation(jfM.EXIT_ON_CLOSE);
		
		jfM.setVisible(true);
		
		
		
		
	
	}
	public static void setDecoratorText(String inDeco){
		welcome_panel wp = new welcome_panel();
		if(inDeco.contains("Advanced")){
			
			wp.drawgui();
			wp.jf.setVisible(true);
		}
		if(inDeco.contains("GameAddictBoy")){
			
			wp = new decorator1(wp);
			wp.drawgui();
			}
		if(inDeco.contains("GameAddictGirl")){
			wp = new decorator2(wp);
			wp.drawgui();
			}
		
		wp.jf.setDefaultCloseOperation(wp.jf.EXIT_ON_CLOSE);
		

	}

}
