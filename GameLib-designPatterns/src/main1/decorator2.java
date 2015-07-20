package main1;

import java.awt.Color;

public class decorator2 extends decorateGUI{
	
	welcome_panel wp1;
	decorator2(welcome_panel wp1){
		this.wp1 = wp1;
	}
	public void drawgui() {
		// TODO Auto-generated method stub
		wp1.drawgui();
		wp1.jpLeft.setBackground(Color.PINK);
		wp1.jpRight.setBackground(Color.PINK);
		wp1.jpCenter.setBackground(Color.pink);
		wp1.jpBottom.setBackground(Color.pink);
		wp1.jf.setVisible(true);
		System.out.println("add decor 2");
	}

}
