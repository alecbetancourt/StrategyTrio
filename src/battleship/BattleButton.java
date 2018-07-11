package battleship;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

//Individual square on board
public class BattleButton extends JButton {
	private boolean hasShip = false;
	private boolean hit = false;
	private boolean miss = false;
	private Ship ship;
	private ImageIcon hitIcon, missIcon;
	
	public BattleButton() {
		setBackground(Color.BLUE);
		hitIcon = new ImageIcon("src/battleship/hit.png");
	}
	
	public void setHitIcon(boolean hit) {
		if(hit)
			setIcon(hitIcon);
	}

	public boolean isHasShip() {
		return hasShip;
	}

	public void setHasShip(boolean hasShip) {
		this.hasShip = hasShip;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
		if(hit) {
			setBackground(Color.RED);
			setEnabled(false);
		}
	}

	public boolean isMiss() {
		return miss;
	}

	public void setMiss(boolean miss) {
		this.miss = miss;
		if(miss) {
			setBackground(Color.WHITE);
			setEnabled(false);
		}
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public void revertColor() {
		if(miss)
			setBackground(Color.WHITE);
		else if(hit)
			setBackground(Color.RED);
		else
			setBackground(Color.BLUE);
	}
}
