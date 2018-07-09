package chess;

public class Knight extends Piece {
	public Knight(Team team) {
		super(team);
	}
	
	public String type() {
		return "Knight";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		return false;
	}
}
