package connectfour;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 * Panel for the connect four game, consisting of a 2-d array of ConnectSquares and
 * JButtons for token placement at the bottom of the screen.
 * @author Parker
 *
 */
public class ConnectPanel extends JPanel {
	/**
	 * ID for connect Panel.
	 */
	private static final long serialVersionUID = 8164836195650249765L;
	/**
	 * Board for placing tokens.
	 */
	private ConnectSquare[][] board;
	/**
	 * Tracker for how many total tokens have been placed.
	 */
	private int tracker;

	/**
	 * Constructor for ConnectPanel. Creates and formats board.	
	 * @param select Jbuttons for column selection at bottom of screen.
	 */
	public ConnectPanel(final JButton[] select) {
		setLayout(new GridLayout(7, 7));
		board = new ConnectSquare[6][7];
		tracker = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				board[i][j] = new ConnectSquare();
				board[i][j].setBorder(new MatteBorder(8, 8, 8, 8, Color.YELLOW));
				add(board[i][j]);

			}
		}
		for (int k = 0; k < select.length; k++) {
			add(select[k]);
		}
	}

	/**
	 * Place a token on the next availible square in a column. Doesnt place if
	 * the column is full.
	 * @param col the column to place the token in
	 * @param player the player placing the token.
	 * @return True if token was placed false if not.
	 */
	public boolean placeToken(final int col, final int player) {
		for (int i = 5; i >= 0; i--) {
			if (!board[i][col].isPlaced()) {
				board[i][col].place(player);
				tracker++;
				return true;
			}
		}
		return false;
	}
	/**
	 * Check from a starting point x,y if there are 4 tokens of the same color in a row.
	 * Checks vertically, horizontally and diagonally.
	 * @param x starting x coordinate.
	 * @param y starting y coordinate.
	 * @return True if 4 in a row is found, false if not.
	 */
	public boolean check(final int x, final int y) {
		int player = board[x][y].getPlayer();
		int xTrack = x;
		int yTrack = y;
		int vert = 0;
		int horz = 0;
		int diag = 0;
		while ((xTrack != -1 && yTrack != -1) && (xTrack != 6 && yTrack != 7)
				&& (board[xTrack][yTrack].getPlayer() == player)) {
			vert++;
			xTrack--;
			if (vert == 4) {
				return true;
			}
		}
		xTrack = x + 1;
		while ((xTrack != -1 && yTrack != -1) && (xTrack != 6 && yTrack != 7)
				&& (board[xTrack][yTrack].getPlayer() == player)) {
			vert++;
			xTrack++;
			if (vert == 4) {
				return true;
			}
		}
		xTrack = x;
		while ((xTrack != -1 && yTrack != -1) && (xTrack != 6 && yTrack != 7)
				&& (board[xTrack][yTrack].getPlayer() == player)) {
			horz++;
			yTrack--;
			if (horz == 4) {
				return true;
			}
		}
		yTrack = y + 1;

		while ((xTrack != -1 && yTrack != -1) && (xTrack != 6 && yTrack != 7)
				&& (board[xTrack][yTrack].getPlayer() == player)) {
			horz++;
			yTrack++;
			if (horz == 4) {
				return true;
			}
		}
		xTrack = x;
		yTrack = y;
		while ((xTrack != -1 && yTrack != -1) && (xTrack != 6 && yTrack != 7)
				&& (board[xTrack][yTrack].getPlayer() == player)) {
			diag++;
			yTrack++;
			xTrack++;
			if (diag == 4) {
				return true;
			}
		}
		xTrack = x - 1;
		yTrack = y - 1;
		while ((xTrack != -1 && yTrack != -1) && (xTrack != 6 && yTrack != 7)
				&& (board[xTrack][yTrack].getPlayer() == player)) {
			diag++;
			xTrack--;
			yTrack--;
			if (diag == 4) {
				return true;
			}
		}
		xTrack = x;
		yTrack = y;
		diag = 0;
		while ((xTrack != -1 && yTrack != -1) && (xTrack != 6 && yTrack != 7)
				&& (board[xTrack][yTrack].getPlayer() == player)) {
			diag++;
			yTrack++;
			xTrack--;
			if (diag == 4) {
				return true;
			}
		}
		xTrack = x + 1;
		yTrack = y - 1;
		while ((xTrack != -1 && yTrack != -1) && (xTrack != 6 && yTrack != 7)
				&& (board[xTrack][yTrack].getPlayer() == player)) {
			diag++;
			xTrack++;
			yTrack--;
			if (diag == 4) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * checks if the board is full.
	 * @return True if board is full, false if not.
	 */
	public boolean isFull() {
		if (tracker == 42) {
			return true;
		}
		return false;
	}
	/**
	 * Checks if a square contains a token and forawds to check if it does.
	 * @return True if a player has 4 in a row, false if not.
	 */
	public boolean isWinner() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j].isPlaced()) {

					if (check(i, j)) {
						return true;
					}

				}
			}

		}
		return false;
	}
}
