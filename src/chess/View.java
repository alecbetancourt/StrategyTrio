package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JPanel {
	private Model model;
	private JButton[][] board;
	private Move move;
	private ImageIcon pawnIconW, pawnIconB, rookIconW, rookIconB, knightIconW, knightIconB, bishopIconW, bishopIconB,
			queenIconW, queenIconB, kingIconW, kingIconB;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new View());
	    frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	public View() {
		model = new Model();
		board = new JButton[8][8];
		move = new Move();
		pawnIconW = new ImageIcon("src/chess/pawnIconW.png");
		pawnIconB = new ImageIcon("src/chess/pawnIconB.png");
		rookIconW = new ImageIcon("src/chess/rookIconW.png");
		rookIconB = new ImageIcon("src/chess/rookIconB.png");
		knightIconW = new ImageIcon("src/chess/knightIconW.png");
		knightIconB = new ImageIcon("src/chess/KnightIconB.png");
		bishopIconW = new ImageIcon("src/chess/bishopIconW.png");
		bishopIconB = new ImageIcon("src/chess/bishopIconB.png");
		queenIconW = new ImageIcon("src/chess/queenIconW.png");
		queenIconB = new ImageIcon("src/chess/queenIconB.png");
		kingIconW = new ImageIcon("src/chess/kingIconW.png");
		kingIconB = new ImageIcon("src/chess/kingIconB.png");
		
		//add stuff to setup buttons and listeners
		//update board
		//how to color board?
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//if choosing
			//if moving
				//click self to deselect
				//attempt move
				//else bad move
			//else bad selection
			//update board
		}
	}
	
	private void updateBoard() {
		//use some combination of if statements and switch statements?
	}
}