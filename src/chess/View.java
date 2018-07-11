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
	private static final Color BROWN = new Color(205,133,63);
	private static final Color TAN = new Color(210,180,140);
	
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
		
		setLayout(new GridLayout(8, 8));
		ButtonListener listener = new ButtonListener();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board[row][col] = new JButton();
				if ((row + col) % 2 == 0) {
	                board[row][col].setBackground(BROWN);
	            }
				else {
	                board[row][col].setBackground(TAN);
	            }
				board[row][col].addActionListener(listener);
				add(board[row][col]);
			}
		}
		updateBoard();
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					Object source = event.getSource();
					if (board[row][col] == source) {
						if (move.fromRow == -1 && model.pieceAt(row, col) != null &&
							model.pieceAt(row, col).team() == model.currentPlayer().team()) {
							System.out.print("Piece selected: " + model.pieceAt(row, col).team() + " ");
							System.out.println(model.pieceAt(row, col).type());
							move.fromRow = row;
							move.fromColumn = col;
						}
						else if (move.fromRow != -1) {
							move.toRow = row;
							move.toColumn = col;
							System.out.println(move);
							if (move.toRow == move.fromRow && move.toColumn == move.fromColumn) {
								move = new Move();
							}
							else if (model.isValidMove(move) == true) {
								model.move(move);
								move = new Move();
								System.out.println("Valid " + move);
								if (model.isWinner()) {
									model.wipeBoard();
									JOptionPane.showMessageDialog(null, model.currentPlayer().name() + "has won!");
								}
								model.nextTurn();
							}
							else {
								JOptionPane.showMessageDialog(null, "This is not a valid move");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "This is not a valid selection");
						}
						//if choosing
						//if moving
							//click self to deselect
							//attempting move
							//else bad move
						//else bad selection
					}
				}
			}
			updateBoard();
		}
	}
	
	private void updateBoard() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (model.pieceAt(row, col) != null && model.pieceAt(row, col).team() == "BLACK") {
					switch (model.pieceAt(row, col).type()) {
					case "Pawn":
						board[row][col].setIcon(pawnIconB);
						break;
					case "Rook":
						board[row][col].setIcon(rookIconB);
						break;
					case "Knight":
						board[row][col].setIcon(knightIconB);
						break;
					case "Bishop":
						board[row][col].setIcon(bishopIconB);
						break;
					case "Queen":
						board[row][col].setIcon(queenIconB);
						break;
					case "King":
						board[row][col].setIcon(kingIconB);
						break;
					}
				}
				else if (model.pieceAt(row, col) != null && model.pieceAt(row, col).team() == "WHITE") {
					switch (model.pieceAt(row, col).type()) {
					case "Pawn":
						board[row][col].setIcon(pawnIconW);
						break;
					case "Rook":
						board[row][col].setIcon(rookIconW);
						break;
					case "Knight":
						board[row][col].setIcon(knightIconW);
						break;
					case "Bishop":
						board[row][col].setIcon(bishopIconW);
						break;
					case "Queen":
						board[row][col].setIcon(queenIconW);
						break;
					case "King":
						board[row][col].setIcon(kingIconW);
						break;
					}
				}
				else {
					board[row][col].setIcon(null);
				}
			}
		}		
	}
}