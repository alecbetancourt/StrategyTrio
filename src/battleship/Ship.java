package battleship;

import java.awt.Color;

import javax.swing.JButton;

public class Ship extends JButton{
	private String name;
	private int size, hits;
	private boolean used;	
	
	public Ship(String name, int size) {
		this.name = name;
		this.size = size;
		used = false;
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public boolean isDestroyed() {
		if(hits == size) {
			setBackground(Color.RED);
			return true;
		}
		return false;
		
	}
	
	public void hit() {
		hits++;
		if(size - hits == 1)
			setBackground(Color.ORANGE);
		else
			setBackground(Color.YELLOW);
	}

}
