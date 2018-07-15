package chess;

/**
 * Class for queen chess piece.
 * @author Alec
 *
 */
public class Queen extends Piece {
	/**
	 * Queen constructor.
	 * @param team the team the piece belongs to.
	 */
	public Queen(final Team team) {
		super(team);
	}
	
	/**
	 * Returns the name of the chess piece.
	 * @return name of chess piece
	 */
	public String type() {
		return "Queen";
	}
	
	/**
	 * Whether or not the last move was valid.
	 * @param move the move that was made
	 * @param board the current board
	 * @return True if the move is valid false if not
	 */
	public boolean isValidMove(final Move move, final Piece[][] board) {
		if (!super.isValidMove(move, board)) {
			return false;
		}
		//check if moving like rook or bishop
		return false;
	}
}
