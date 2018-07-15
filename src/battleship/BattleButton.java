package battleship;

import java.awt.Color;

//import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class the extends JButton, used as the individual squares for the
 * boards. 
 * 
 * @author Parker
 * @version 0.1
 */
public class BattleButton extends JButton {
	/**
	 * BattleButton ID.
	 */
	private static final long serialVersionUID = 7317966084678253303L;
	/**
	 * Whether or not the Button contains a ship. 
	 */
	private boolean hasShip = false;
	/**
	 * Whether or not the button has been guessed and hit.
	 */
	private boolean hit = false;
	/**
	 * Whether or not the button has been guessed and missed.
	 */
	private boolean miss = false;
	/**
	 * The ship that occupies the button, null if none.
	 */
	private Ship ship;
	//	private ImageIcon hitIcon, missIcon;

	/**
	 * Constructor that sets all buttons background to blue.
	 */
	public BattleButton() {
		setBackground(Color.BLUE);
	}

	/**
	 * If a ship has been placed on the button.
	 * @return True if the button has a ship, False if not
	 */
	public boolean isHasShip() {
		return hasShip;
	}

	/**
	 * Used when a ship has been placed, or for future features if a
	 * ship was removed from a square.
	 * @param hasShip True is a ship was placed, False if not
	 */
	public void setHasShip(final boolean hasShip) {
		this.hasShip = hasShip;
		if (hasShip) {
			setBackground(Color.GRAY);	
		} else {
			setBackground(Color.BLUE);
		}
	}

	/**
	 * If the button was guessed and it was a hit.
	 * @return True if it was hit, false if not
	 */
	public boolean isHit() {
		return hit;
	}

	/**
	 * Set the hit boolean. Disables and colors the button red if
	 * it is true.
	 * @param hit if the ship was hit
	 */
	public void setHit(final boolean hit) {
		this.hit = hit;
		if (hit) {
			setBackground(Color.RED);
			setEnabled(false);
		}
	}

	/**
	 * If the button was guessed and it was a miss.
	 * @return True if it was missed, false if not
	 */
	public boolean isMiss() {
		return miss;
	}

	/**
	 * Set the miss boolean. Disables and colors the button white if
	 * it is true.
	 * @param miss if the shot was missed
	 */
	public void setMiss(final boolean miss) {
		this.miss = miss;
		if (miss) {
			setBackground(Color.WHITE);
			setEnabled(false);
		}
	}

	/**
	 * Set the ship that has been placed on the button.
	 * @param ship type that has been placed
	 */
	public void setShip(final Ship ship) {
		this.ship = ship;
	}

	/**
	 * Get the ship placed on this button.
	 * @return the ship placed or null if none
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * Reverts a buttons color to usual. Used after a button has been
	 * highlighted after selection.
	 */
	public void revertColor() {
		if (miss) {
			setBackground(Color.WHITE);
		} else if (hit) {
			setBackground(Color.RED);
		} else if (hasShip) {
			setBackground(Color.GRAY);
		} else {
			setBackground(Color.BLUE);
		}
	}
}
