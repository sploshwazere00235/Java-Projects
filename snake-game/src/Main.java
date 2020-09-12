
import javax.swing.JFrame;


public class Main {

	public Main() {
		JFrame frame = new JFrame();
		Gamepanel panel = new Gamepanel();
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake game");

		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
	
	public static void main(String[] args) {
		new Main();

	}

	

}
