package game2.Proximity;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * It assigns the proper number of armies to the position where the mouse is clicked
 * @author Ramineh Y. Lopez
 *
 */

public class ProximityMouseListener implements MouseListener{
	public static GamePanel gamePanel;
	@Override
	public void mouseClicked(MouseEvent e) {
		int messageFlag=0;//deals with the situations when an already taken tile is clicked or an invalid part of the board is clicked
		int winLoseFlag=0;// when it turns 1 the game is over and the winner is determined
//		Point mousePoint = e.getLocationOnScreen();// cursor location is saved in the variable mousePoint
		Point mousePoint = e.getPoint();// cursor location is saved in the variable mousePoint
		
		int xdiam=(int)(2.0*GamePanel.component.getRadius()*Math.sin(2*Math.PI/6));//the width of the rectangle that approximates a hexagon
		int ydiam=(int) (GamePanel.component.getRadius()+GamePanel.component.getRadius()*(Math.cos(2*Math.PI/6))); //the height of the rectangle that approximates a hexagon
		
		int x=mousePoint.x;//  x coordinate of the cursor
		int y=mousePoint.y;//  y coordinate of the cursor
		int xl, yl;
		int l=1;//number of the hexagon clicked on the hexlist
	//	
		yl=(y-30)/ydiam;//calculates row number. 60 is absolute offset from the top of screen
		
		if(yl%2==0)
			xl=(x-30)/xdiam;//calculates column number.  30 is absolute offset from the top of the screen
		else
			xl=(x-30-xdiam/2)/xdiam;
		
		if(xl>=Logistics.getD()||xl<0||yl>=Logistics.getD()||yl<0)
			messageFlag=2;
		else l=yl*Logistics.getD()+xl+1;
		
		if (Logistics.getHex(l)>0 || Logistics.getHex(l)<0)//if the tile is already taken the values are either positive or negative
			messageFlag=1;// flag changes from 0 to one
		
		if(messageFlag==0){		
			int rr= Logistics.getR();
			//The next line is only place that updates the board
			if (Logistics.nextTurn()==0)rr=rr*(-1);	//Next turn is up and the sign of the random number will be changed		
			Logistics.setHex(l, rr);//The tile is placed on the board in position l and value rr
			ProximityMain.ps.println(l);
			ProximityMain.ps.println(rr);
			

		}
		/**
		 * if the sum of the total count for red and blue tiles is equal to total number of hexagons, game will be over
		 */
		//if (Logistics.getCounter(0)+Logistics.getCounter(1)==(Logistics.getD()*Logistics.getD()))
		//	winLoseFlag=1;// this flag is turned to 1 when game is over
		ProximityMain.frame.remove(gamePanel.bot);// bottom panel is removed from the frame
		ProximityMain.frame.remove(gamePanel.gpanel);//graphic panel is removed from the frame
		if(messageFlag==0){
			Logistics.updatePlay(l);//if flag is zero played gets updated and  a new game panel is created
		}
		
		gamePanel=new GamePanel();
		
		if (messageFlag==1) gamePanel.message.setText("Position Taken");// if you click
		if (messageFlag==2) gamePanel.message.setText("Invalid Position");//if you click outside the board
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
