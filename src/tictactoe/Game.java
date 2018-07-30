package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Game panel for the tic-tac-toe game.
 * @author Alec
 *
 */
public class Game extends JPanel {
	/**
	 * ID for game Panel.
	 */
	private static final long serialVersionUID = 8577256205770245886L;
	/**
	 * Model representing game.
	 */
	private char[][] model;
	/**
	 * Visual of game board.
	 */
	private JButton[][] board;
	/**
	 * Tracker for which players turn it is.
	 */
	private char turn;
	/**
	 * Names for each player.
	 */
	private String name1, name2;
	
	/**
	 * driver for tic tac toe game.
	 * @param args arguements.
	 */
	public static void main(final String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new Game("Player 1", "Player 2"));
	    frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	/**
	 * Constructs and initializes game board and model.
	 * @param pName1 player 1's name.
	 * @param pName2 player 2's name.
	 */
	public Game(final String pName1, final String pName2) {
		model = new char[3][3];
		board = new JButton[3][3];
		turn = 'X';
		name1 = pName1;
		name2 = pName2;
		wipeBoard();
		
		setLayout(new GridLayout(3, 3));
		ButtonListener listener = new ButtonListener();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = new JButton();
				board[row][col].setBackground(Color.WHITE);
				board[row][col].setFont(new Font("Arial", Font.PLAIN, 150));
				board[row][col].addActionListener(listener);
				add(board[row][col]);
			}
		}
	}
	
	/**
	 * Listener to respond to button clicks.
	 * @author Alec
	 *
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Respond to button clicks.
		 * @param event what was clicked.
		 */
		public void actionPerformed(final ActionEvent event) {
			Object source = event.getSource();
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (board[row][col] == source) {
						if (model[row][col] == ' ') {
							model[row][col] = turn;
							updateBoard();
							if (isWinner()) {
								String str;
								if (turn == 'X') {
									str = name1 + " has won!";
								} else {
									str = name2 + " has won!";
								}
								JOptionPane.showMessageDialog(null, str);
								wipeBoard();
								updateBoard();
							} else if (isFull()) {
								JOptionPane.showMessageDialog(null, "Game is a draw.");
							} else {
								turn = ((turn == 'X') ? 'O' : 'X');
							}
						} else {
							JOptionPane.showMessageDialog(null, 
									"This is not a valid selection");
						}
					}
				}
			}
		}
	}
	/**
	 * updates visuals of the board.
	 */
	private void updateBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col].setText(String.valueOf(model[row][col]));
			}
		}
	}
	
	/**
	 * Clears the game board.
	 */
	private void wipeBoard() {
		for (char[] row: model) {
			   Arrays.fill(row, ' ');
		}
	}
	
	/**
	 * Checks for 3 symbols in a row.
	 * @return True if a winner is found, false if noy.
	 */
	private boolean isWinner() {
		if (model[0][0] == model[0][1] && model[0][1] == model[0][2] && (model[0][0] != ' ')) {
            return true;
		} else if (model[1][0] == model[1][1] && model[1][1] == model[1][2] && (model[1][0] != ' ')) {
            return true;
		} else if (model[2][0] == model[2][1] && model[2][1] == model[2][2] && (model[2][0] != ' ')) {
            return true;
		} else if (model[0][0] == model[1][0] && model[1][0] == model[2][0] && (model[0][0] != ' ')) {
            return true;
		} else if (model[0][1] == model[1][1] && model[1][1] == model[2][1] && (model[0][1] != ' ')) {
            return true;
		} else if (model[0][2] == model[1][2] && model[1][2] == model[2][2] && (model[0][2] != ' ')) {
            return true;
		} else if (model[0][0] == model[1][1] && model[1][1] == model[2][2] && (model[0][0] != ' ')) {
            return true;
		} else if (model[0][2] == model[1][1] && model[1][1] == model[2][0] && (model[0][2] != ' ')) {
            return true;
		} else {
            return false;
		}
	}
	
	/**
	 * Checks if board is full or not without a winner.
	 * @return True if the board is full, false if not.
	 */
	public boolean isFull() {
        boolean flag = true;
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (model[row][col] == ' ') {
                    flag = false;
                }
            }
        }
        return flag;
    }
}
