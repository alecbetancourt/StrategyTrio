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
		return false;
	}
}
