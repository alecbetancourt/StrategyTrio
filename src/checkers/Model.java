package checkers;

import javax.swing.JButton;

/**
 * The model for the checkers game. All moves are executed through 
 * this class.
 * @author Alec
 *
 */
public class Model {
	/**
	 * The checkers board.
	 */
	private Piece[][] board;
	/** 
	 * Player 1.
	 */
	private Player player1;
	/**
	 * Player 2.
	 */
	private Player player2;
	/**
	 * The current player, who's turn it is.
	 */
	private Player currentPlayer;
	
	/**
	 * Model constructor. Creates the pieces and board.
	 */
	public Model() {
		board = new Piece[8][8];
		
		player1 = new Player("player1", Team.RED);
		player2 = new Player("player2", Team.BLACK);
		currentPlayer = player1;
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 8; col++) {
				if ((row + col) % 2 == 1) {
	                board[row][col] = new Piece(Team.BLACK);
	            }
			}
		}
		
		for (int row = 5; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ((row + col) % 2 == 1) {
	                board[row][col] = new Piece(Team.RED);
	            }
			}
		}
	}
	
	/**
	 * Gets the current player, who's turn it is.
	 * @return the current player
	 */
	public Player currentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Determines and switches to the next player.
	 */
	public void nextTurn() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}
	
	/**
	 * Clears the board.
	 */
	public void wipeBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
	}
	
	/**
	 * Moves the chess piece.
	 * @param move the move the piece will make
	 */
	public void move(final Move move) {
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	/**
	 * Determines if a move is valid.
	 * @param move the move the piece made
	 * @return True if the movie is valid, false if not
	 */
	public boolean isValidMove(final Move move) {
		return board[move.fromRow][move.fromColumn].isValidMove(move, board);
	}
	
	/**
	 * Get the piece at a given location.
	 * @param row the piece's row
	 * @param column the piece's column
	 * @return the piece at the row,column or null if none
	 */
	public Piece pieceAt(final int row, final int column) {
		if (board[row][column] == null) {
			return null;
		} else {
			return board[row][column];
		}
	}
	
	/**
	 * Determines if the game has been won.
	 * @return True if it has been won, false if not
	 */
	public boolean isWinner() {
		boolean black = false;
		boolean red = false;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] != null && board[row][col].team() == "RED" 
						&& board[row][col].team() == "RED") {
					black = true;
				}
				if (board[row][col] != null && board[row][col].team() == "BLACK" 
						&& board[row][col].team() == "BLACK") {
					red = true;
				}
			}
		}
		return !(black && red);
	}
}
