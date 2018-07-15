package chess;

public class King extends Piece {
	public King(Team team) {
		super(team);
	}
	
	public String type() {
		return "King";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		//check that abs(rowdiff or col diff) = 1
		if (Math.abs(move.toRow - move.fromRow) <= 1 && Math.abs(move.toColumn - move.fromColumn) <= 1
			&& (board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].team() != team())) {
			return true;
		}
		return false;
	}
}
