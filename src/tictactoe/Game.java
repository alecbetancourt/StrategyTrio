package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Game extends JPanel {
	char model[][];
	JButton board[][];
	char turn;
	
	public static void main(final String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new Game());
	    frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	public Game() {
		model = new char[3][3];
		board = new JButton[3][3];
		turn = 'X';
		
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
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(final ActionEvent event) {
			Object source = event.getSource();
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (board[row][col] == source) {
						if (model[row][col] == ' ') {
							model[row][col] = turn;
							updateBoard();
							if (isWinner()) {
								JOptionPane.showMessageDialog(null, "Player " + turn + " has won!");
								wipeBoard();
								updateBoard();
							}
							else if (isFull()) {
								JOptionPane.showMessageDialog(null, "Game is a draw.");
							}
							else {
								turn = ((turn == 'X') ? 'O' : 'X');
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "This is not a valid selection");
						}
					}
				}
			}
		}
	}
	
	private void updateBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col].setText(String.valueOf(model[row][col]));
			}
		}
	}
	
	private void wipeBoard() {
		for (char[] row: model)
			   Arrays.fill(row, ' ');
	}
	
	private boolean isWinner() {
		if (model[0][0] == model[0][1] && model[0][1] == model[0][2] && (model[0][0] != ' '))
            return true;
		else if (model[1][0] == model[1][1] && model[1][1] == model[1][2] && (model[1][0] != ' '))
            return true;
		else if (model[2][0] == model[2][1] && model[2][1] == model[2][2] && (model[2][0] != ' '))
            return true;
		else if (model[0][0] == model[1][0] && model[1][0] == model[2][0] && (model[0][0] != ' '))
            return true;
		else if (model[0][1] == model[1][1] && model[1][1] == model[2][1] && (model[0][1] != ' '))
            return true;
		else if (model[0][2] == model[1][2] && model[1][2] == model[2][2] && (model[0][2] != ' '))
            return true;
		else if (model[0][0] == model[1][1] && model[1][1] == model[2][2] && (model[0][0] != ' '))
            return true;
		else if (model[0][2] == model[1][1] && model[1][1] == model[2][0] && (model[0][2] != ' '))
            return true;
		else
            return false;
	}
	
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