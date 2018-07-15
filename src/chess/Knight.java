package chess;

/**
 * Class that handles logic and attributes for an instance of the Knight class.
 * @author William
 *
 */
public class Knight extends Piece {
	/**
	 * Constructor that calls the Piece() method in the extended class Piece.
	 *
	 * @param team	the team that the piece is to be assigned to
	 */
	public Knight(final Team team) {
		super(team);
	}
	
	/**
	 * Returns a string of the name of the piece it is called on.
	 *
	 * @return "Knight"
	 */
	public String type() {
		return "Knight";
	}
	
	/**
	 * Checks to see if move is valid using logic specific to the Knight.
	 * 
	 * @param move	the move to be checked
	 * @param board the board of pieces to check against
	 * @return <code>true</code> if the move is valid
	 * 		   <code>false</code> otherwise
	 *
	 */
	public boolean isValidMove(final Move move, final Piece[][] board) {
		if (!super.isValidMove(move, board)) {
			return false;
		}
		//is abs dif row ==1 & col == 2 or row == 2 and col == 1
		return false;
	}
}
