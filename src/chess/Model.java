package chess;

public class Model {
	private Piece[][] board;
	private Player player1, player2;
	private Player currentPlayer;
	
	public Model() {
		board = new Piece[8][8];
		
		player1 = new Player("player1", Team.WHITE);
		player2 = new Player("player2", Team.BLACK);
		currentPlayer = player1;
		
		//add stuff to populate board
	}
	
	public Player currentPlayer() {
		return currentPlayer;
	}
	
	public void nextTurn() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		}
		else {
			currentPlayer = player1;
		}
	}
	
	//might delete if not used?
	public void wipeBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
	}
	
	public void move(Move move) {
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	
	public boolean isValidMove(Move move) {
		if (board[move.fromRow][move.fromColumn].isValidMove(move, board))
			return true;
		else
			return false;
	}
	
	public Piece pieceAt(int row, int column) {
		if (board[row][column] == null) {
			return null;
		}
		else {
			return board[row][column];
		}
	}
	
	public boolean isWinner() {
		//either keep a list of captured pieces to reference or use flags to determine if kings are on board
		return false;
	}
}