package chess;

/**
 * Class for rook chess piece.
 * @author Alec
 *
 */
public class Rook extends Piece {
	/**
	 * Rook constructor.
	 * @param team the team the piece belongs to
	 */
	public Rook(final Team team) {
		super(team);
	}
	
	/**
	 * Returns the name of the chess piece.
	 * @return name of chess piece
	 */
	public String type() {
		return "Rook";
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
		if (board[move.toRow][move.toColumn] == null 
				|| !(board[move.toRow][move.toColumn].team().equals(team()))) {
            valid = true;
		}
		
		//check if you're not moving vertically or horizontally
        if (move.toRow != move.fromRow && move.toColumn != move.fromColumn) {
        	return false;
        }
        
        //check intermediate spaces if moving multiple spaces
        if (Math.abs(move.toRow - move.fromRow) > 1) {
        	int offset = (move.toRow - move.fromRow) / Math.abs(move.toRow - move.fromRow);
        	for (int i = move.fromRow + offset; i != move.toRow; i += offset) {
        		if (board[i][move.fromColumn] == null) {
                    valid = true;
        		} else {
        			return false;
        		}
        	}
        } else if (Math.abs(move.toColumn - move.fromColumn) > 1) {
        	int offset = (move.toColumn - move.fromColumn) / Math.abs(move.toColumn - move.fromColumn);
        	for (int i = move.fromRow + offset; i != move.toRow; i += offset) {
        		if (board[i][move.fromColumn] == null) {
                    valid = true;
        		} else {
        			return false;
        		}
        	}
        }
        
		return valid;
	}
}
