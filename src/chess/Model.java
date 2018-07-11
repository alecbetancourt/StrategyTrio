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
		
		board[0][0] = new Rook(Team.BLACK);
		board[0][1] = new Knight(Team.BLACK);
		board[0][2] = new Bishop(Team.BLACK);
		board[0][3] = new Queen(Team.BLACK);
		board[0][4] = new King(Team.BLACK);
		board[0][5] = new Bishop(Team.BLACK);
		board[0][6] = new Knight(Team.BLACK);
		board[0][7] = new Rook(Team.BLACK);
		board[7][0] = new Rook(Team.WHITE);
		board[7][1] = new Knight(Team.WHITE);
		board[7][2] = new Bishop(Team.WHITE);
		board[7][3] = new King(Team.WHITE);
		board[7][4] = new Queen(Team.WHITE);
		board[7][5] = new Bishop(Team.WHITE);
		board[7][6] = new Knight(Team.WHITE);
		board[7][7] = new Rook(Team.WHITE);
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Team.BLACK);
			board[6][i] = new Pawn(Team.WHITE);
		}
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
		boolean black = false;
		boolean white = false;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] != null && board[row][col].team() == "BLACK" && board[row][col].type() == "King") {
					black = true;
				}
				if (board[row][col] != null && board[row][col].team() == "WHITE" && board[row][col].type() == "King") {
					white = true;
				}
			}
		}
		if ((black == true && white == false) || (black == false && white == true)) {
			return true;
		}
		else {
			return false;
		}
	}
}