package chess;

public class Bishop extends Piece{
	public Bishop(Team team) {
		super(team);
	}
	
	public String type() {
		return "Bishop";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		return false;
	}
}
