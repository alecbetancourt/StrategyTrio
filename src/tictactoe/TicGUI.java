package tictactoe;

import javax.swing.JFrame;

public class TicGUI {
	public TicGUI() {
		   JFrame frame = new JFrame("Tic Tac Toe");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			frame.add(new Game());
		    frame.pack();
			frame.setSize(800, 800);
			frame.setVisible(true);
	}
}
