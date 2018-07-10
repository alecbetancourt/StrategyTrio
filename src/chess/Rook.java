package chess;

public class Rook extends Piece {
	public Rook(Team team) {
		super(team);
	}
	
	public String type() {
		return "Rook";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		boolean valid = false;
		if (super.isValidMove(move, board) == false) {
			return false;
		}
		
		//check final space that piece will occupy
		if (board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].team() != team()) {
            valid = true;
		}
		
		//check if you're not moving vertically or horizontally
        if (move.toRow != move.fromRow && move.toColumn != move.fromColumn) {
        	return false;
        }
        
        //check intermediate spaces if moving multiple spaces
        if (Math.abs(move.toRow-move.fromRow) > 1) {
        	int offset = (move.toRow-move.fromRow)/Math.abs(move.toRow-move.fromRow);
        	for (int i = move.fromRow + offset; i != move.toRow; i += offset) {
        		if (board[i][move.fromColumn] == null)
                    valid = true;
        		else
        			return false;
        	}
        }
        else if (Math.abs(move.toColumn-move.fromColumn) > 1) {
        	int offset = (move.toColumn-move.fromColumn)/Math.abs(move.toColumn-move.fromColumn);
        	for (int i = move.fromRow + offset; i != move.toRow; i += offset) {
        		if (board[i][move.fromColumn] == null)
                    valid = true;
        		else
        			return false;
        	}
        }
        
		return valid;
	}
}
