package battleship;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Frame for the battleship game, consisting of a pair of panels
 * data between boards is transfered through this class. The game is
 * largely ran through this class.
 * 
 * @author Parker
 * @version 0.1
 */
public class GUI extends JFrame implements ActionListener {

	/**
	 * ID for GUI.
	 */
	private static final long serialVersionUID = -763859861758304376L;
	/**
	 * Player 1's user board.
	 */
	private Board p1;
	/**
	 * Player 1's opponent board.
	 */
	private OppBoard p1O;
	/**
	 * Player 2's user board.
	 */
	private Board p2;
	/**
	 * Player 2's opponent board.
	 */
	private OppBoard p2O;

	/**
	 * Menu bar for option.
	 */
	private JMenuBar menus;
	/**
	 * Menu for options.
	 */
	private JMenu option;
	/**
	 * New Game option for menu.
	 */
	//private JMenuItem newGameItem;
	/**
	 * close option for menu.
	 */
	private JMenuItem closeItem;
	//private JMenuItem mainMenuItem;

	/**
	 * Holder for 2 panels/boards.
	 */
	private JPanel[] holder;

	/**
	 * Switch panel for  before player 1's turn.
	 */
	private SwitchPanel p1Switch;
	/**
	 * Switch panel for  before player 2's turn.
	 */
	private SwitchPanel p2Switch;

	/**
	 * Panel for the winner.
	 */
	private WinPanel end;

	/**
	 * Whether or not the game is ongoing.
	 */
	private boolean hasWon;
	/**
	 * Player 1' name.
	 */
	private String name1;
	/**
	 * Player 2's name.
	 */
	private String name2;

	/**
	 * the name of the game winner.
	 */
	private String winner;
	

	/**
	 * Constructor that calls the createMenus and NewGame methods.
	 * @param rematch if it is a rematch or not
	 */
	public GUI(final boolean rematch) {
		createMenus();		
		newGame(rematch);
	}

	/**
	 * Creates Menu and adds actionlistener to each item.
	 */
	public void createMenus() {

		option = new JMenu("Menu");
		//newGameItem = new JMenuItem("New Game");
		//newGameItem.addActionListener(this);
//		mainMenuItem = new JMenuItem("Main Menu");
//		mainMenuItem.addActionListener(this);
		closeItem = new JMenuItem("Quit");
		closeItem.addActionListener(this);

		//New game/ main menu for R#2
		menus = new JMenuBar();
		menus.add(option);
		//option.add(newGameItem);
		//option.add(mainMenuItem);
		option.add(closeItem);

	}

	/**
	 * Begins the battleship game. Creates and adds the game panels
	 *  and frame. 
	 * Then the game starts with placeships() and playGame().
	 * @param rematch True if the game is a rematch, false if not
	 */
	public void newGame(final boolean rematch) {	
		if (!rematch) {
			getNames();
			holder = new JPanel[2];
		}

		p1 = new Board(name1);
		p2 = new Board(name2);

		p1O = new OppBoard(name2);
		p2O = new OppBoard(name1);

		p1Switch = new SwitchPanel(name1);
		p2Switch = new SwitchPanel(name2);

		setLayout(new GridLayout(2, 1));

		if (!rematch) {
			for (int i = 0; i < 2; i++) {
				holder[i] = new JPanel();
				add(holder[i]);
				holder[i].setLayout(new GridLayout(1, 1));
			}
		}
		holder[1].add(p1);
		holder[0].add(p1O);

		//for testing switch/win panels
//						holder[1].add(p1Switch);
//						end = new WinPanel("pork");
//						holder[0].add(end);


		if (!rematch) {
			setTitle("Battleship");
			this.setDefaultCloseOperation(JFrame.
					EXIT_ON_CLOSE);      
			setJMenuBar(menus);
			setSize(1500, 1000);
			setVisible(true);
		}
		revalidate();
		repaint();
		hasWon = false;
		placeShips();
		playGame();
	}

	/**
	 * Takes user input for player names, set to default Player X if
	 * no response is given.
	 */
	public void getNames() {
		name1 = JOptionPane.showInputDialog(null,
				"What is Player 1's name?");
		try {
			if (name1.equals("")) {
				name1 = "Player 1";
			}
		} catch (NullPointerException ex) {
			name1 = "Player 1";
		}

		name2 = JOptionPane.showInputDialog(null, 
				"What is Player 2's name?");
		try {
			if (name2.equals("")) {
				name2 = "Player 2";
			}
		} catch (NullPointerException ex) {
			name2 = "Player 2";
		}
	}

	/**
	 * Loop of players selecting a target and firing until a player
	 * has won.
	 */
	public void playGame() {
		int x, y;
		boolean hit;
		hasWon = false;

		while (!hasWon) {

			p1O.enableFire();
			while (!p1O.isFired()) {
				try {
					Thread.yield();
				} catch (Exception interruptedEx) {
					// 
				}
			}
			//get Targeted coordinates
			x = p1O.getTargetX();
			y = p1O.getTargetY();

			//check user board for hit
			hit = p2.hitMiss(x, y);

			//mark square on opponent board
			p1O.markSquare(hit, x, y);
			p1O.updateStatus(p2.getStatus());
			hasWon = p2.isLost();
			if (!hasWon) {
				switchP2();
				p2O.enableFire();
				while (!p2O.isFired()) {
					try {
						Thread.yield();
					} catch (Exception interruptedEx) {
						// do nothing?
					}
				}

				x = p2O.getTargetX();
				y = p2O.getTargetY();
				hit = p1.hitMiss(x, y);
				p2O.markSquare(hit, x, y);
				p2O.updateStatus(p1.getStatus());
				hasWon = p1.isLost();
				if (!hasWon) {
					switchP1();
				}

				//IDEA: add in timer and have it exit the 
				//game when it hits zero for r #2 to solve issue
				try {
					Thread.yield();
				} catch (Exception interruptedEx) {
					// Log the interruption somewhere.
				}


			}
		}
		p1O.disableFire();
		p2O.disableFire();
		if (p1.isLost()) {
			winner = name2;
		} else {
			winner = name1;
		}
		winScreen(winner);
	}

	/**
	 * Displays a win screen on the top panel for the winning player.
	 * @param winner the winner's name
	 */
	public void winScreen(final String winner) {
		end = new WinPanel(winner);
		holder[0].removeAll();
		holder[0].add(end);
		revalidate();
		repaint();
		while (!end.isRematch()) {
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// Log the interruption somewhere.
			}
		}
		holder[0].removeAll();
		holder[1].removeAll();
		newGame(true);
		revalidate();
		repaint();

	}

	/**
	 * Return player board.
	 * @param i player number
	 * @return player board i
	 */
	public Board getBoard(final int i) {
		if (i == 1) {
			return p1;
		} else {
			return p2;
		}
	}
	
	/**
	 * Allows players to place their ships, starting with player 1.
	 */
	public void placeShips() {
		//player 1 places ships
		
		while (!p1.isReady()) {
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// Log the interruption somewhere.
			}
		}
		

		switchP2();
		while (!p2.isReady()) {
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// 
			}
		}
		switchP1();
	}

	/**
	 * Displays a switch screen for player 1 after 
	 * player 2's turn until they select ready.
	 */
	public void switchP2() {
		holder[1].removeAll();
		holder[1].add(p2Switch);
		revalidate();
		repaint();

		while (!p2Switch.isReady()) {
			//Googled this solution because loop was not updating
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// 
			}
		}
		p2Switch.setReady(false);
		holder[0].removeAll();
		holder[1].removeAll();
		holder[0].add(p2O);
		holder[1].add(p2);
		revalidate();
		repaint();
	}

	/**
	 * Displays a switch screen for player 1 after player 2's 
	 * turn until they select ready.
	 */
	public void switchP1() {
		holder[1].removeAll();
		holder[1].add(p1Switch);
		revalidate();
		repaint();

		while (!p1Switch.isReady()) {
			//Googled this solution because loop was not updating
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// 
			}
		}
		p1Switch.setReady(false);
		holder[0].removeAll();
		holder[1].removeAll();
		holder[0].add(p1O);
		holder[1].add(p1);
		revalidate();
		repaint();
	}

	/**
	 * Responding to an action.
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		//Does not work right now
//		if (e.getSource() == newGameItem) {
//			holder[0].removeAll();
//			holder[1].removeAll();
//			newGame(true);
//			revalidate();
//			repaint();
//		}

		if (e.getSource() == closeItem) {
			this.dispose();
			revalidate();
			repaint();
		}
	}


	/**
	 * Driver for the battleship project.
	 * @param args arguments for main
	 */
	public static void main(final String[] args) {
		new GUI(false);
	}
}

