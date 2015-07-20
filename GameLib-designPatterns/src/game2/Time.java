package game2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
/**
 * 
 * @author Wilson X. Velez Santana
 *This class create a timer.
 */
public class Time implements ActionListener {
	
	

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Time out! You lost. Game over.");
		Main.timeOut = true;
		Main.answer =true;
		Main.timer.stop();

	}
}
