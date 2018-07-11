package battleship;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;


//USER's BOARD
//need a way to keep track of which ship is which on the board
public class Board extends JPanel {

	private BattleButton[][] user;
	private ButtonListener listen;

	private Ship carrier;
	private Ship battleship;
	private Ship submarine;
	private Ship cruiser;
	private Ship destroyer;

	private JLabel status;
	private JLabel pName;

	private boolean ready;
	private boolean lost;
	private int shipCount;
	
	private GridBagConstraints con;

	public Board(String name) {
		user = new BattleButton[10][10];
		listen = new ButtonListener();

		setReady(false);
		shipCount = 5;
		setBackground(Color.CYAN);
		setLayout(new GridLayout(12,11));
//		setLayout(new GridBagLayout());
//		con = new GridBagConstraints();
		
		
		status = new JLabel("Place Your Battleships", SwingConstants.CENTER);
		pName = new JLabel(name, SwingConstants.CENTER);
		createBoard();		
		createShips();
		
//		con.gridx = 11;
//		con.gridy = 6;
		add(status);
//
//
//		//Need a better way to format
//
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(pName);
	}

	public void createBoard() {
		for(int i =0; i< 11; i++) {
			for(int j =0; j <11; j++) {
//				con.gridx = i;
//				con.gridy = j;
//				con.fill = GridBagConstraints.HORIZONTAL;
				
				//blank square at 0,0
				if(j==0 && i== 0) {
					add(new JLabel(""));
				}
				//formatting x-axis
				else if(i ==0 ) {
					add(new JLabel("" + (j), SwingConstants.CENTER));
				}
				//formatting y-axis
				else if(j== 0) {
					int c = 64+i;
					String s = Character.toString((char)c);
					add(new JLabel(s,SwingConstants.CENTER));
				}
				else {
					user[i-1][j-1] = new BattleButton();
					user[i-1][j-1].addActionListener(listen);
					add(user[i-1][j-1]);

				}
			}
		}
	}
	
	
	public String getStatus() {
		return status.getText();
	}
	//girdbag fomatting commented out for now, may not use
	public void createShips() {
//		int i = 0;
//		int j = 11;
		
		carrier = new Ship("Aircraft Carrier", 5);
		battleship = new Ship("Battleship", 4);
		submarine = new Ship("Submarine", 3);
		cruiser = new Ship("Cruiser", 3);
		destroyer = new Ship("Destroyer", 2);
//		con.gridx = i;
//		con.gridy = j;
//		con.gridwidth =4;
//		con.weightx = 0.0;
//		con.fill = GridBagConstraints.HORIZONTAL;
		
		//Will change setLabel to some sort of icon
		carrier.addActionListener(listen);
		carrier.setBackground(Color.GRAY);
		carrier.setLabel("Carrier (5)");
		add(carrier);
		
//		i += 4;
//		con.gridx = i;
//		con.gridy = j;
//		con.gridwidth =3;
//		con.weightx = 0.0;
		//con.fill = GridBagConstraints.HORIZONTAL;
		
		
		battleship.addActionListener(listen);
		battleship.setBackground(Color.GRAY);
		battleship.setLabel("Battleship (4)");
		add(battleship);
		
//		i += 3;
//		con.gridx = i;
//		con.gridy = j;
//		con.gridwidth =1;

		submarine.addActionListener(listen);
		submarine.setBackground(Color.GRAY);
		submarine.setLabel("Submarine (3)");
		add(submarine,con);
		
//		con.gridwidth =1;
//		i+= 1;
//		con.gridx = i;
//		con.gridy = j;
//		
		cruiser.addActionListener(listen);
		cruiser.setBackground(Color.GRAY);
		cruiser.setLabel("Cruiser (3)");
		add(cruiser);

//		con.gridwidth =1;
//		i+=1;
//		con.gridx = i;
//		con.gridy = j;
		
		destroyer.addActionListener(listen);
		destroyer.setBackground(Color.GRAY);
		destroyer.setLabel("Destroyer (2)");
		add(destroyer);
		
//		con.gridwidth = 1;
//		i+=1;
//		con.gridx = i;
//		con.gridy = j;
//		add(status,con);
	}
	public void fillHor(int x1, int x2, int y, Ship tShip) {
		int tX1, tX2;
		if(x1 > x2) {
			tX1 =x2;
			tX2 = x1;
		}
		else {
			tX1 = x1;
			tX2 = x2;
		}

		for(int i=tX1; i<(tX2+1); i++) {
			if(user[i][y].isHasShip()) {
				throw new IllegalArgumentException();
			}
		}
		for(int i=tX1; i<(tX2+1); i++) {
			
			//outlines the ship on the user board
			if(i== tX1) {
				user[i][y].setBorder(new MatteBorder(1,1,0,1, Color.BLACK));
			}
			else if(i == tX2) {
				user[i][y].setBorder(new MatteBorder(0,1,1,1, Color.BLACK));
			}
			else {
				user[i][y].setBorder(new MatteBorder(0,1,0,1, Color.BLACK));
			}
			user[i][y].setBackground(Color.GRAY);
			user[i][y].setHasShip(true);
			user[i][y].setEnabled(false);
			user[i][y].setShip(tShip);
		}
	}

	public void fillVert(int y1, int y2, int x, Ship tShip) {
		int tY1, tY2;
		if(y1 > y2) {
			tY1 =y2;
			tY2 = y1;
		}
		else {
			tY1 = y1;
			tY2 = y2;
		}
		for(int i=tY1; i<(tY2+1); i++) {
			if(user[x][i].isHasShip()) {
				throw new IllegalArgumentException();
			}
		}
		for(int i=tY1; i<(tY2+1); i++) {
			
			//outlines the ship on the user board
			if(i== tY1) {
				user[x][i].setBorder(new MatteBorder(1,1,1,0, Color.BLACK));
			}
			else if(i == tY2) {
				user[x][i].setBorder(new MatteBorder(1,0,1,1, Color.BLACK));
			}
			else {
				user[x][i].setBorder(new MatteBorder(1,0,1,0, Color.BLACK));
			}
			
			user[x][i].setBackground(Color.GRAY);
			user[x][i].setHasShip(true);
			user[x][i].setEnabled(false);
			user[x][i].setShip(tShip);


		}
	}

	public boolean placeShip(int x1, int x2, int y1, int y2, Ship tShip) {
		if(x1 == x2) {
			if(y1 == y2)
				return false;
			else if(Math.abs((y1-y2)) != (tShip.getSSize()-1))
				return false;
			else {
				try{
					fillVert(y1,y2,x1,tShip);
				}
				catch (Exception e) {
					status.setText("Invalid Placement");
					return false;
				}

				return true;
			}
		}
		else if(y1 == y2) {
			if(x1 == x2)
				return false;
			else if(Math.abs((x1-x2)) != (tShip.getSSize()-1))
				return false;
			else {
				try{
					fillHor(x1,x2,y1,tShip);
				}
				catch (Exception e) {
					status.setText("Invalid Placement");
					return false;
				}
				return true;
			}
		}
		else
			return false;
	}



	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public void disableButtons() {
		for(int i =0; i< 10; i++) {
			for(int j =0; j <10; j++) {
				user[i][j].setEnabled(false);
			}
		}
	}


	//False if miss true if hit
	public boolean hitMiss(int x, int y) {
			if(user[x][y].isHasShip()) {	
				user[x][y].setHit(true);
				//temporary will make icons later
				user[x][y].setBackground(Color.RED);
				//user[x][y].setHitIcon(true);
				user[x][y].setEnabled(false);
				user[x][y].getShip().hit();
				status.setText(user[x][y].getShip().getName() + " hit!");
				
				if(user[x][y].getShip().isDestroyed()) {
					status.setText(user[x][y].getShip().getName() + " sunk!");
					shipCount--;
					if(shipCount == 0) {
						lost = true;
						status.setText("You lose");
					}
				}
				return true;
			}
			user[x][y].setMiss(true);
			user[x][y].setBackground(Color.WHITE);
			user[x][y].setEnabled(false);
			status.setText("Miss!");
			return false;
				
	}



	public int getShipCount() {
		return shipCount;
	}

	public void setShipCount(int shipCount) {
		this.shipCount = shipCount;
	}


	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}



	private class ButtonListener implements ActionListener {
		//tracking variables for location and ship type
		Ship tShip = null;
		int x1 =-1;
		int y1 =-1;
		int x2 =-1;
		int y2 =-1;
		int shipCount = 5;
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() instanceof Ship) {
				if(tShip != null) {
					tShip.setBackground(Color.GRAY);
				}
				
				//highlights ship when selected
				tShip = (Ship)e.getSource();
				tShip.setBackground(Color.DARK_GRAY);

				//reset location on ship selection
				x1 = x2 = y1 = y2 = -1;
			}

			if(e.getSource() instanceof BattleButton) {
				if(tShip != null) {
					for(int i =0; i< 10; i++) {
						for(int j =0; j <10; j++) {
							if(e.getSource() == user[i][j]) {
								if(x1 == -1 && y1 == -1) {
									x1 = i;
									y1 = j;
								}
								else {
									x2 = i;
									y2 =j;

									if(placeShip(x1,x2,y2,y1,tShip)) {
										status.setText(tShip.getName() + " placed");
										tShip.setBackground(Color.GRAY);
										tShip.setEnabled(false);
										shipCount --;
										if(shipCount == 0) {
											disableButtons();
											setReady(true);
										}
									}
									else {
										tShip.setBackground(Color.GRAY);
										status.setText("Invalid placement!");
									}
									x1=x2=y1=y2=-1;
									tShip = null;
								}
							}
						}
					}
				}
			}
			else {
				status.setText("Select a Ship!");
			}
		}

	}

}











