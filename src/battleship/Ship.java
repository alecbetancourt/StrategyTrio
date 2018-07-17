package battleship;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Ship class that extends JButton. To be displayed at the bottom of the panel
 * and used when placing ships.
 *  
 * @author Parker
 * @version 0.1
 */
public class Ship extends JButton {
	
	/**
	 * Ship ID.
	 */
	private static final long serialVersionUID = -546938238859432123L;
	/**
	 * The name of the ship.
	 */
	private String name;
	/**
	 * The size of the ship.
	 */
	private int size;
	/**
	 * How many times the ship has been hit.
	 */
	private int hits;
	/**
	 * Whether or not the ship has been placed.
	 */
	private boolean used;	
	
	/**
	 * Constructor for Ship class.
	 * @param name the name of the ship
	 * @param size how many hits the ship can take before it sinks
	 */
	public Ship(final String name, final int size) {
		this.name = name;
		this.size = size;
		used = false;
	}
	
	/**
	 * Get the name of the ship.
	 * @return String for ship name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the ship.
	 * @param name the name for the ship
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * How many hits the ship can take.
	 * @return int of how many hits remaining.
	 */
	public int getSSize() {
		return size;
	}

	/**
	 * Set the amount of shots the ship can take before sinking.
	 * @param size the number of shots to set to.
	 */
	public void setSSize(final int size) {
		this.size = size;
	}

	/**
	 * Whether or not the ship has been placed yet.
	 * @return true if added false if removed
	 */
	public boolean isUsed() {
		return used;
	}

	/**
	 * To indicate whether the ship has been placed or possible removed.
	 * @param used true if added false if removed
	 */
	public void setUsed(final boolean used) {
		this.used = used;
	}

	/**
	 * Whether or not a ship can still be hit.
	 * @return True if it was sunk false if not.
	 */
	public boolean isDestroyed() {
		if (hits == size) {
			setBackground(Color.RED);
			return true;
		}
		return false;
		
	}
	
	/**
	 * Increase the amount of hits and update the color of the ship.
	 */
	public void hit() {
		hits++;
		//switch to orange to alert player 
		if (size - hits == 1) {
			setBackground(Color.ORANGE);
		} else {
			setBackground(Color.YELLOW);
		}
	}

}
