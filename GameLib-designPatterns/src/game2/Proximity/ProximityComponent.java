package game2.Proximity;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JComponent;
/**
 * 
 * @author Ramineh Y Lopez
 *	This class 
 */

public class ProximityComponent extends JComponent{


	private static final long serialVersionUID = 1L;

	static double radius;// distance between the center of each hexagon to each vortex
	
	/**
	 * This method paints all the  hexagons on the game board
	 */

	public void paintComponent(Graphics g){
		int d=Logistics.getD();// game board dimension is stored in local variable d
		int y=0;//y coordinate for the center of each hexagon
		int x=0;//x coordinate for the center of each hexagon
		int cc=0;//number of hexagons
	
		Graphics2D g2 = (Graphics2D) g;// it casts the object of class Graphics to g2 of class Graphics2D
		/**
		 * determines the coordinates of the center of each hexagon
		 */
		for (int iy=0;iy<d;++iy){

			y+=(int)(radius+radius*Math.cos(2*Math.PI/6));
			x=0;
			if (iy%2==1){
				x+=(int)(radius*Math.sin(2*Math.PI/6));
			}

			for (int ix=0;ix<d;++ix){

				x+=(int)(2*radius*Math.sin(2*Math.PI/6));
				drawHex(x+15,y+15,g2,++cc);
			}
		}
	}
	/**
	 * 
	 * @param x x coordinate for the center of each hexagon
	 * @param y y coordinate for the center of each hexagon
	 * @param gg arguement that passes a gGaphics2D object to the method (in this case g2)
	 * @param c number of the hexagon
	 */
	public void drawHex(int x,int y,Graphics2D gg,int c ){	

		int i;

		Polygon p = new Polygon();// hexagon is drawn here
		for (i = 0; i < 6; i++) {
			p.addPoint(
					(int)(x +radius*Math.sin(i * 2 * Math.PI / 6)),
					(int)(y +radius*Math.cos(i * 2 * Math.PI / 6)));
		}  

		gg.setColor(Color.WHITE); // color of the hexagon is set to be white
		/**
		 * if the value of the hexagon is positive, therefore, the tile turns blue
		 */
		if (Logistics.getHex(c)>0){
			gg.setColor(Color.BLUE);
		}
		/**
		 * if the value of the hexagon is negative, therefore, the tile turns red
		 */
		if (Logistics.getHex(c)<0){
			gg.setColor(Color.RED);
		}
		/**
		 * each hexagon is filled with the corresponding color
		 */
		gg.fillPolygon(p);
		gg.setColor(Color.BLACK);
		gg.drawPolygon(p);
		/**
		 * if the value of an hexagon is zero, this means that no tile has been placed on this hexagon position
		 * the number of each hexagon is printed in each hexagon
		 */
		if (Logistics.getHex(c)==0){
			gg.setColor(Color.BLACK);
			gg.setFont(new Font("Arial", Font.PLAIN, 10));
			gg.drawString(String.valueOf(c), x-8, y+5);	
		}
		else {
			int armies=Logistics.getHex(c);//local variable armies saves the value of the tile places in a hexagon
			if (armies<0) armies*=(-1);// calculates the absolute value of the armies

			gg.setColor(Color.WHITE);
			gg.setFont(new Font("Arial", Font.BOLD, 16));
			gg.drawString(String.valueOf(armies), x-8, y+5);
		}
	}
	/**
	 * 
	 * @return value of the radius from the center to the vertexes of each hexagon
	 */
	public double getRadius(){
		return radius;
	}
	/**
	 * This method sets the size of the hexagons based on the variale D, which is the dimension of the board.
	 */
	public static void setRadius(){
		radius=17*12/Logistics.getD();
	}


}
		
	

		
	
