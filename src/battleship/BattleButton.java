package battleship;

import java.awt.Color;

import javax.swing.ImageIcon;
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
	 * Icon for water image.
	 */
	private ImageIcon waterLogo;
	
	/**
	 * Logo for hit ship.
	 */
	private ImageIcon hitLogo;
	
	/**
	 * Logo for hit used on oppboard.
	 */
	private ImageIcon hitWaterLogo;
	/**
	 * Logo for miss.
	 */
	private ImageIcon missWater;
	/**
	 * Constructor that sets all buttons background to blue.
	 */
	public BattleButton() {
		waterLogo = new ImageIcon("src/battleship/waterLogo.png");
		hitWaterLogo = new ImageIcon("src/battleship/hitWater.png");
		hitLogo = new ImageIcon("src/battleship/hit.png");
		missWater = new ImageIcon("src/battleship/missWater.png");
		setIcon(waterLogo);
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
			setIcon(null);
			setBackground(Color.GRAY);	
		} else {
			setIcon(waterLogo);
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
		if (hit && hasShip) {
			revertColor();
		} else if (hit) {
			setIcon(hitWaterLogo);
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
			setIcon(missWater);
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
	 * Returns if a square has been hit or missed. Used by OppBoard class
	 * to prevent double selections.
	 * @return True if the square hasnt been guessed, false if not.
	 */
	public boolean isEmpty() {
		return (!miss && !hit);
	}

	/**
	 * Reverts a buttons color to usual. Used after a button has been
	 * highlighted after selection.
	 */
	public void revertColor() {
		if (miss) {
			setIcon(missWater);
		} else if (hit) {
			if (hasShip) {
			setIcon(hitLogo);
			} else {
				setIcon(hitWaterLogo);
			}
		} else if (hasShip) {
			setIcon(null);
			setBackground(Color.GRAY);
		} else {
			setIcon(waterLogo);
		}
	}
}
