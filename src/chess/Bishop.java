package chess;

public class Bishop extends Piece{
	public Bishop(Team team) {
		super(team);
	}
	
	public String type() {
		return "Bishop";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		boolean valid = false;
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		//check final space that piece will occupy
		if (board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].team() != team()) {
            valid = true;
		}
		
		//check if moving strictly diagonally
		if (Math.abs(move.toRow - move.fromRow) != Math.abs(move.toColumn - move.fromColumn)) {
			return false;
		}
		
		//check intermediate spaces if moving multiple spaces
		if (Math.abs(move.toRow - move.fromRow) > 1 || Math.abs(move.toColumn - move.fromColumn) > 1) {
			int rowOffset = (move.toRow - move.fromRow) / Math.abs(move.toRow - move.fromRow);
			int colOffset = (move.toColumn - move.fromColumn) / Math.abs(move.toColumn - move.fromColumn);
			for (int i = 1; i < Math.abs(move.fromRow - move.toRow); i++) {
				if (board[move.fromRow + (i * rowOffset)][move.fromColumn + (i * colOffset)] == null) {
                    valid = true;
        		} else {
        			return false;
        		}
			}
		}
		//check that only moving diag
		//check spaces inbetween dual offset
		return valid;
	}
}
