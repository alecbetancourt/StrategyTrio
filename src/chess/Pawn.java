package chess;

public class Pawn extends Piece {
	public Pawn(Team team) {
		super(team);
	}
	
	public String type() {
		return "Pawn";
	}
	
	public boolean isValidMove(Move move, Piece[][] board) {
		boolean valid = false;
		if (super.isValidMove(move, board) == false) {
			return false;
		}
		
		//move vertically
		if (move.fromColumn == move.toColumn && board[move.toRow][move.toColumn] == null) {
			//move one space
			if ((board[move.fromRow][move.fromColumn].team() == "BLACK" && move.fromRow + 1 == move.toRow) ||
				(board[move.fromRow][move.fromColumn].team() == "WHITE" && move.fromRow - 1 == move.toRow)) {
				valid = true;
			}
			//move 2 spaces if first move
			if ((board[move.fromRow][move.fromColumn].team() == "BLACK" && move.fromRow == 1 && move.toRow == 3) ||
				(board[move.fromRow][move.fromColumn].team() == "WHITE" && move.fromRow == 6 && move.toRow == 4)) {
				if (board[(move.fromRow+move.toRow)/2][move.fromColumn] == null) {
					valid = true;
				}
			}
		}
		//move diagonally if and only if capture
		if (Math.abs(move.toRow-move.fromRow) == 1 && Math.abs(move.toColumn-move.fromColumn) == 1 &&
			board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].team() != team()) {
			if (board[move.fromRow][move.fromColumn].team() == "BLACK" && move.fromRow + 1 == move.toRow) {
				valid = true;
			}
			if (board[move.fromRow][move.fromColumn].team() == "WHITE" && move.fromRow - 1 == move.toRow) {
				valid = true;
			}
		}
		return valid;
	}
}
