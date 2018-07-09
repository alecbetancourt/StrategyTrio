package chess;

public class Rook extends Piece {
	public Rook(Team team) {
		super(team);
	}
	
	public String type() {
		return "Rook";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		return false;
	}
}
