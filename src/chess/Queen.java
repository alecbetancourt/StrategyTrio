package chess;

public class Queen extends Piece {
	public Queen(Team team) {
		super(team);
	}
	
	public String type() {
		return "Queen";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		return false;
	}
}
