package connect4;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ConnectSquare extends JLabel {
	
	/**
	 * The player who's token has been placed. 0 = black, 1 = red, -1 = none.
	 */
	private int player = -1;
	/**
	 * Whether or not a token has been placed in the square.
	 */
	private boolean placed;
	/**
	 * ImageIcon for black token.
	 */
	private static final ImageIcon BLACK = new ImageIcon("src/connect4/blackIcon.png");
	/**
	 * ImageIcon for red token.
	 */
	private static final ImageIcon RED = new ImageIcon("src/connect4/redIcon.png");
	
	/**
	 * Constructor for ConnectSquare class.
	 */
	public ConnectSquare() {
		//not sure how to center icons
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.CENTER);
	}
	/**
	 * Place a token in the square.
	 * @param player which player is placing the square.
	 */
	public void place(final int player) {
		this.player = player;
		if (player == 0) {
			setIcon(BLACK);			
		} else if (player == 1) {
			setIcon(RED);
		}
		placed = true;
	}
	/**
	 * The player who's token is in the square if there is one.
	 * @return 0 for black, 1 for red, or -1 for none.
	 */
	public int getPlayer() {
		return player;
	}
	/**
	 * Set the player who's token is in the square.
	 * @param player 0 for black, 1 for red, -1 for none/
	 */
	public void setPlayer(final int player) {
		this.player = player;
	}

	/**
	 * Checks if a token has been placed.
	 * @return True if a token has been placed, false if not.
	 */
	public boolean isPlaced() {
		return placed;
	}

	/**
	 * Sets if a token was placed.
	 * @param placed True if a token has been placed, false if not.
	 */
	public void setPlaced(final boolean placed) {
		this.placed = placed;
	}
	
}
