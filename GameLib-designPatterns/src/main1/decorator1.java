package main1;

import java.awt.Color;

import javax.swing.JButton;



public class decorator1 extends decorateGUI{
	welcome_panel wp1;
	decorator1(welcome_panel wp1){
		this.wp1 = wp1;
	}
	public void drawgui() {
		// TODO Auto-generated method stub
		wp1.drawgui();
		wp1.jpLeft.setBackground(Color.GRAY);
		wp1.jpRight.setBackground(Color.GRAY);
		wp1.jpCenter.setBackground(Color.DARK_GRAY);
		wp1.jpBottom.setBackground(Color.DARK_GRAY);
		wp1.jf.setVisible(true);
		System.out.println("add decor 1");
	}

}
