package chess;

public class Pawn extends Piece {
	public Pawn(Team team) {
		super(team);
	}
	
	public String type() {
		return "Pawn";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		return false;
	}
}
