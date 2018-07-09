package chess;

public class King extends Piece {
	public King(Team team) {
		super(team);
	}
	
	public String type() {
		return "King";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		return false;
	}
}
