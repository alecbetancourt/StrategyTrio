package chess;

public class Move {
	public int fromRow, fromColumn, toRow, toColumn;

	public Move() {
		fromRow = -1;
		fromColumn = -1;
		toRow = -1;
		toColumn = -1;
	}

	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
				+ "]";
	}
}