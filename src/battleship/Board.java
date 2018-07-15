package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;


/**
 * Class for the User board where they place their ships and keep track
 * of the ship's status. The board is made up of button type's and uses
 * a 12x11 gridlayout.
 * 
 * @author Parker
 * @version 0.1
 */
public class Board extends JPanel {

	/**
	 *  ID for board.
	 */
	private static final long serialVersionUID = 2015116678863509370L;
	/**
	 * 2-D array for the user board.
	 */
	private BattleButton[][] user;
	/**
	 * Listener for ships and board buttons.
	 */
	private ButtonListener listen;

	/**
	 * Ship type carrier, hits 5.
	 */
	private Ship carrier;
	/**
	 * Ship type battleship, hits 4.
	 */
	private Ship battleship;
	/**
	 * Ship type submarine, hits 3.
	 */
	private Ship submarine;
	/**
	 * Ship type cruiser, hits 3.
	 */
	private Ship cruiser;
	/**
	 * Ship type destroyer, hits 2.
	 */
	private Ship destroyer;

	/**
	 * String for the current status of the game.
	 */
	private JLabel status;
	/**
	 * Name of player who's board it is.
	 */
	private JLabel pName;

	/**
	 * Whether or not all the ships have been placed.
	 */
	private boolean ready;
	/**
	 * If all the users ships have been sunk or not.
	 */
	private boolean lost;
	/**
	 * The number of ships the user has left.
	 */
	private int shipCount;

	/**
	 * Constructor for the board, initializes the board creation and 
	 * ship placement. 
	 * 
	 * @param name for the player name
	 */
	public Board(final String name) {
		user = new BattleButton[10][10];
		listen = new ButtonListener();

		setReady(false);
		shipCount = 5;
		setBackground(Color.CYAN);
		setLayout(new GridLayout(12, 11)); 

		status = new JLabel("Place Your Battleships", 
				SwingConstants.CENTER);
		pName = new JLabel(name, SwingConstants.CENTER);
		createBoard();		
		createShips();

		add(status);
		//		//Need a better way to format
		//
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(pName);
	}

	/**
	 * Creates and formats the user board where ships are placed.
	 */
	public void createBoard() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				//blank square at 0,0
				if (j == 0 && i == 0) {
					add(new JLabel(""));
				} else if (i == 0) {
					add(new JLabel("" + (j), 
							SwingConstants.CENTER));
				} else if (j == 0) {
					int c = (64 + i);
					String s = Character.toString((char) c);
					add(new JLabel(s,
							SwingConstants.CENTER));
				} else {
					user[i - 1][j - 1] = new BattleButton();
					user[i - 1][j - 1].addActionListener(listen);
					add(user[i - 1][j - 1]);

				}
			}
		}
	}

	/**
	 * Get the current game status message.
	 * @return the game message as a String
	 */
	public String getStatus() {
		return status.getText();
	}

	/**
	 * Creates and formats the ship buttons used for placing ships
	 * and tracking their status.
	 */
	public void createShips() {
		carrier = new Ship("Aircraft Carrier", 5);
		battleship = new Ship("Battleship", 4);
		submarine = new Ship("Submarine", 3);
		cruiser = new Ship("Cruiser", 3);
		destroyer = new Ship("Destroyer", 2);

		//Will change setLabel to some sort of icon
		carrier.addActionListener(listen);
		carrier.setBackground(Color.GRAY);
		carrier.setLabel("Carrier (5)");
		add(carrier);

		battleship.addActionListener(listen);
		battleship.setBackground(Color.GRAY);
		battleship.setLabel("Battleship (4)");
		add(battleship);

		submarine.addActionListener(listen);
		submarine.setBackground(Color.GRAY);
		submarine.setLabel("Submarine (3)");
		add(submarine);

		cruiser.addActionListener(listen);
		cruiser.setBackground(Color.GRAY);
		cruiser.setLabel("Cruiser (3)");
		add(cruiser);

		destroyer.addActionListener(listen);
		destroyer.setBackground(Color.GRAY);
		destroyer.setLabel("Destroyer (2)");
		add(destroyer);

	}

	/**
	 * Colors and marks the battlebuttons where a ship has been placed
	 * horizontally. Also checks for possible ship overlap.
	 * 
	 * @param x1 the first x-coordniate 
	 * @param x2 the second x-coordniate 
	 * @param y the y-coordniate 
	 * @param tShip the ship that was placed
	 * @throws Exception for overlapping ship placement
	 */
	public void fillHor(final int x1, final int x2, final int y, final Ship tShip) {
		int tX1, tX2;
		//finds lower coordinate for loop
		if (x1 > x2) {
			tX1 = x2;
			tX2 = x1;
		} else {
			tX1 = x1;
			tX2 = x2;
		}

		//Checks for ship overlap
		for (int i = tX1; i < (tX2 + 1); i++) {
			if (user[i][y].isHasShip()) {
				throw new IllegalArgumentException();
			}
		}
		for (int i = tX1; i < (tX2 + 1); i++) {

			//outlines the ship on the user board
			if (i == tX1) {
				user[i][y].setBorder(new MatteBorder(1, 1, 0, 1, Color.BLACK));
			} else if (i == tX2) {
				user[i][y].setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
			} else {
				user[i][y].setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));
			}
			user[i][y].setHasShip(true);
			user[i][y].setEnabled(false);
			user[i][y].setShip(tShip);
		}
	}

	/**
	 * Colors and marks the battlebuttons where a ship has been placed
	 * vertically. Also checks for possible ship overlap.
	 * 
	 * @param y1 the first y-coordniate 
	 * @param y2 the second y-coordniate 
	 * @param x the x-coordniate 
	 * @param tShip the ship that was placed
	 * @throws Exception for overlapping ship placement
	 */
	public void fillVert(final int y1, final int y2, final int x, final Ship tShip) {
		int tY1, tY2;
		//finds lower coordinate for loop
		if (y1 > y2) {
			tY1 = y2;
			tY2 = y1;
		} else {
			tY1 = y1;
			tY2 = y2;
		}
		//Checks for ship overlap
		for (int i = tY1; i < (tY2 + 1); i++) {
			if (user[x][i].isHasShip()) {
				throw new IllegalArgumentException();
			}
		}
		for (int i = tY1; i < (tY2 + 1); i++) {
			//outlines the ship on the user board
			if (i == tY1) {
				user[x][i].setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
			} else if (i == tY2) {
				user[x][i].setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
			} else {
				user[x][i].setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));
			}
			//Colors and disables button
			user[x][i].setHasShip(true);
			user[x][i].setEnabled(false);
			user[x][i].setShip(tShip);
		}
	}

	/**
	 * Method that determine whether the ship placement is valid and
	 * whether it was placed horizontally or vertically and directs
	 * it to the respective fill method.
	 * 
	 * @param x1 the first x-coordinate
	 * @param x2 the second x-coordinate
	 * @param y1 the first y-coordinate
	 * @param y2 the second y-coordinate
	 * @param tShip the ship placed
	 * @return true if ship was placed, false if not
	 */
	public boolean placeShip(final int x1, final int x2, final int y1, final int y2, final Ship tShip) {
		//if ship is placed vertically
		if (x1 == x2) {
			if (y1 == y2) {
				return false;
			} else if (Math.abs((y1 - y2)) != (tShip.getSSize() - 1)) {
				return false;
			} else {
				try {
					fillVert(y1, y2, x1, tShip); 
				} catch (Exception e) {
					status.setText("Invalid Placement");
					return false;
				}
				return true;
			}
		} else if (y1 == y2) {
			if (x1 == x2) {
				return false;
			} else if (Math.abs((x1 - x2)) != (tShip.getSSize() - 1)) {
				return false;
			} else {
				try {
					fillHor(x1, x2, y1, tShip); 
				} catch (Exception e) {
					status.setText("Invalid Placement");
					return false;
				}
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Returns if player has placed their ships.
	 * @return boolean if the player is ready or not
	 */
	public boolean isReady() {
		return ready;
	}

	/**
	 * Set the ready boolean.
	 * @param ready if the player is ready or not
	 */
	public void setReady(final boolean ready) {
		this.ready = ready;
	}

	/**
	 * disables all the buttons on the board, called after all ships
	 * have been placed.
	 */
	public void disableButtons() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				user[i][j].setEnabled(false);
			}
		}
	}

	/**
	 * Checks if an opponent's guess is a hit or miss. Called by the 
	 * GUI which gives the coordinates of the opponent's guess. Also
	 * checks if a ship has been sunk and if all ships are sunk. If
	 * all ships are sunk it sets lost to true.
	 * 
	 * @param x x-coordinate guessed by opponent
	 * @param y y-coordinate guessed by opponent
	 * @return true if the guess was a hit, false if it was a miss
	 */
	public boolean hitMiss(final int x, final int y) {
		if (user[x][y].isHasShip()) {	
			user[x][y].setHit(true);
			user[x][y].getShip().hit();
			status.setText(user[x][y].getShip().getName()
					+ " hit!");
			if (user[x][y].getShip().isDestroyed()) {
				status.setText(user[x][y].getShip().
						getName() + " sunk!");
				shipCount--;
				if (shipCount == 0) {
					lost = true;
					status.setText("You lose");
				}
			}
			return true;
		}
		user[x][y].setMiss(true);
		status.setText("Miss!");
		return false;

	}

	/**
	 * Returns the current number of ships remaining.
	 * @return number of user ships left
	 */
	public int getShipCount() {
		return shipCount;
	}

	/**
	 * Set the number of ships the user has remaining.
	 * @param shipCount the number of user ships left.
	 */
	public void setShipCount(final int shipCount) {
		this.shipCount = shipCount;
	}

	/**
	 * Whether or not the User has ships remaining.
	 * @return true is all ships are destroyed, false if not
	 */
	public boolean isLost() {
		return lost;
	}

	/**
	 * Set the lost boolean.
	 * @param lost True if lost, false if not
	 */
	public void setLost(final boolean lost) {
		this.lost = lost;
	}

	/**
	 * Button listener used for the board and ship buttons. Is used
	 * to determine where the user wants to place their ship and
	 * what ship they want to place. A user places a ship by selecting
	 * the ship button they want, then selecting a two squares where
	 * they want they ship placed.
	 * 
	 * @author Parker
	 */
	private class ButtonListener implements ActionListener {
		//tracking variables for location and ship type
		/**
		 * The ship selected by the player.
		 */
		private Ship tShip = null;
		/**
		 * the first x-coordinate.
		 */
		private int x1 = -1;
		/**
		 * the first y-coordinate.
		 */
		private int y1 = -1;
		/**
		 * the second x-coordinate.
		 */
		private int x2 = -1;
		/**
		 * the second y-coordinate.
		 */
		private int y2 = -1;
		/**
		 * the number of ships left to place.
		 */
		private int shipCount = 5;
		/**
		 * the selected battlebutton.
		 */
		private BattleButton select = null;
		@Override
		public void actionPerformed(final ActionEvent e) {

			if (e.getSource() instanceof Ship) {
				if (tShip != null) {
					//unhighlights selected ship
					tShip.setBackground(Color.GRAY);
					if (select != null) {
						//unhighlights selected square
						select.setBackground(Color.BLUE);
					}
				}

				//highlights ship when selected
				tShip = (Ship) e.getSource();
				tShip.setBackground(Color.DARK_GRAY);

				//reset location on ship selection
				x1 = -1;
				x2 = -1;
				y1 = -1;
				y2 = -1;
			}

			if (e.getSource() instanceof BattleButton) {
				//if a ship has been selected
				if (tShip != null) {
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							if (e.getSource() == user[i][j]) {
								//if this is the first square selected
								if (x1 == -1 && y1 == -1) {
									x1 = i;
									y1 = j;
									select = user[x1][y1];
									select.setBackground(Color.GRAY);
								} else {
									select.setBackground(Color.BLUE);
									x2 = i;
									y2 = j;
									//attempts to place the ship
									if (placeShip(x1, x2, y2, y1, tShip)) {
										status.setText(tShip.getName() + " "
												+ "placed");
										tShip.setBackground(Color.GRAY);
										tShip.setEnabled(false);
										shipCount--;
										select = null;
										//if all ships have been placed
										if (shipCount == 0) {
											disableButtons();
											setReady(true);
										}
									} else {
										tShip.setBackground(Color.GRAY);
										status.setText("Invalid placement!");
									}
									x1 = -1;
									x2 = -1;
									y1 = -1;
									y2 = -1;
									tShip = null;
								}
							}
						}
					}
				}
			} else {
				status.setText("Select a Ship!");
			}
		}

	}

}











