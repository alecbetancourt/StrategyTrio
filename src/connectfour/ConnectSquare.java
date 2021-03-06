package connectfour;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Class that extends Jlabel that represents where user would place 
 * their tokens. 
 * @author Parker
 *
 */
public class ConnectSquare extends JLabel {
	
	/**
	 * ID for connect square class.
	 */
	private static final long serialVersionUID = 5917240010760453358L;
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
	private static final ImageIcon BLACK = new ImageIcon("src/connectfour/blackIcon.png");
	/**
	 * ImageIcon for red token.
	 */
	private static final ImageIcon RED = new ImageIcon("src/connectfour/redIcon.png");
	
	/**
	 * Constructor for ConnectSquare class.
	 */
	public ConnectSquare() {
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
	 * Checks if a token has been placed.
	 * @return True if a token has been placed, false if not.
	 */
	public boolean isPlaced() {
		return placed;
	}
	
}
