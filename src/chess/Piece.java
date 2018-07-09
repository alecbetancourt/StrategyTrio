package chess;

public abstract class Piece {
	//private Player player;
	private Team team;

	public Piece(Team team) {
		this.team = team;
	}

	public abstract String type();

	public String team() {
		return team.toString();
	}

	public boolean isValidMove(Move move, Piece[][] board) {
		//check if moving to friendly piece
		if (board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].team() == team.toString()) {
			return false;
		}
		//check if not moving
		else if (move.fromRow == move.toRow && move.fromColumn == move.toColumn) {
			return false;
		}
		else {
			return true;
		}
	}
}