package chess;

/**
 * Class for the bishop chess piece.
 * @author Alec
 *
 */
public class Bishop extends Piece {
	/**
	 * Bishop constructor.
	 * @param team which team the piece belongs to.
	 */
	public Bishop(final Team team) {
		super(team);
	}
	
	/**
	 * Returns the name of the chess piece.
	 * @return name of chess piece
	 */
	public String type() {
		return "Bishop";
	}
	
	/**
	 * Whether or not the last move was valid.
	 * @param move the move that was made
	 * @param board the current board
	 * @return True if the move is valid false if not
	 */
	public boolean isValidMove(final Move move, final Piece[][] board) {
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
