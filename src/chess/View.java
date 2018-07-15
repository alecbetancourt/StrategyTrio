package chess;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * JPanel for the chess game.
 * @author Parker
 *
 */
public class View extends JPanel {
	/**
	 * View ID.
	 */
	private static final long serialVersionUID = -2223695223616731728L;
	/**
	 * The model for the chess game.
	 */
	private Model model;
	/**
	 * The board for the game.
	 */
	private JButton[][] board;
	/**
	 * The current move.
	 */
	private Move move;
	/**
	 * All of the chess piece icons.
	 */
	private ImageIcon pawnIconW, pawnIconB, rookIconW, 
	rookIconB, knightIconW, knightIconB, bishopIconW, bishopIconB,
	queenIconW, queenIconB, kingIconW, kingIconB;
	/**
	 * Brown used for background tiles.
	 */
	private static final Color BROWN = new Color(205, 133, 63);
	/**
	 * Tan used for background tiles.
	 */
	private static final Color TAN = new Color(210, 180, 140);
	
	//Will likely be deleted in favor of master UI in release 2
	//add new game/quit function in menu options
	/**
	 * Creats the Jframe and chess panel.
	 * @param args for main
	 */
	public static void main(final String[] args) {
		JFrame frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new View());
	    frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	/**
	 * Creates the board and all pieces/icons.
	 */
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
	            } else {
	                board[row][col].setBackground(TAN);
	            }
				board[row][col].addActionListener(listener);
				add(board[row][col]);
			}
		}
		updateBoard();
	}
	
	/**
	 * Listener for board buttons.
	 * @author Alec
	 *
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Respond to button clicks.
		 * @param event what was pressed.
		 */
		public void actionPerformed(final ActionEvent event) {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					Object source = event.getSource();
					if (board[row][col] == source) {
						if (move.fromRow == -1 && model.pieceAt(row, col) != null
							&& model.pieceAt(row, col).team() == model.currentPlayer().team()) {
							System.out.print("Piece selected: " + model.pieceAt(row, col).team() + " ");
							System.out.println(model.pieceAt(row, col).type());
							move.fromRow = row;
							move.fromColumn = col;
						} else if (move.fromRow != -1) {
							move.toRow = row;
							move.toColumn = col;
							System.out.println(move);
							if (move.toRow == move.fromRow && move.toColumn 
									== move.fromColumn) {
								move = new Move();
							} else if (model.isValidMove(move)) {
								model.move(move);
								move = new Move();
								System.out.println("Valid " + move);
								if (model.isWinner()) {
									model.wipeBoard();
									JOptionPane.showMessageDialog(null, model.currentPlayer().name() + " has won!");
								}
								model.nextTurn();
							} else {
								JOptionPane.showMessageDialog(null, "This is not a valid move");
							}
						} else {
							JOptionPane.showMessageDialog(null, "This is not a valid selection");
						}
					}
				}
			}
			updateBoard();
		}
	}
	
	/**
	 * Update the look of the board after a move.
	 */
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
					default:
						break;
					}
				} else if (model.pieceAt(row, col) != null && model.pieceAt(row, col).team() == "WHITE") {
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
					default:
						break;
					}
				} else {
					board[row][col].setIcon(null);
				}
			}
		}		
	}
}
