package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import mainmenu.MenuGUI;


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
	private JMenuItem newGameItem;
	/**
	 * close option for menu.
	 */
	private JMenuItem closeItem;
	/**
	 * MainMenu Item for menubar.
	 */
	private JMenuItem mainMenuItem;

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
	 * Player 1' name.
	 */
	private String name1;
	/**
	 * Player 2's name.
	 */
	private String name2;

	/**
	 * Player 1's fire button.
	 */
	private JButton fire1;
	/**
	 * Player 2s fire button.
	 */
	private JButton fire2;
	/**
	 * Player 1's ready button for switch panel.
	 */
	private JButton ready1;
	/**
	 * Player 2's ready button for switch panel.
	 */
	private JButton ready2;
	/**
	 * Player 1's begin button for user board.
	 */
	private JButton begin1;
	/**
	 * Player 2's begin button for user board.
	 */
	private JButton begin2;
	/**
	 * Button for rematch screen.
	 */
	private JButton rematch1;
	/**
	 * Ints for co-oordinates of firing.
	 */
	private int targetX, targetY;

	/**
	 * ImageIcon for begin button.
	 */
	private ImageIcon beginIcon;

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
		newGameItem = new JMenuItem("New Game");
		newGameItem.addActionListener(this);
		mainMenuItem = new JMenuItem("Main Menu");
		mainMenuItem.addActionListener(this);
		closeItem = new JMenuItem("Quit");
		closeItem.addActionListener(this);

		// main menu for R#2
		menus = new JMenuBar();
		menus.add(option);
		option.add(newGameItem);
		option.add(mainMenuItem);
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

		beginIcon = new ImageIcon("src/battleship/beginLogo.png");
		begin1 = new JButton();
		begin1.setIcon(beginIcon);
		begin1.setBackground(Color.BLUE);
		begin1.addActionListener(this);

		begin2 = new JButton();
		begin2.setIcon(beginIcon);
		begin2.setBackground(Color.BLUE);
		begin2.addActionListener(this);

		p1 = new Board(name1, begin1);
		p2 = new Board(name2, begin2);

		fire1 = new JButton();
		ImageIcon fireLogo = new ImageIcon("src/battleship/fireLogo.png");
		fire1.setIcon(fireLogo);
		fire1.setBackground(Color.RED);

		fire2 = new JButton();
		fireLogo = new ImageIcon("src/battleship/fireLogo.png");
		fire2.setIcon(fireLogo);
		fire2.setBackground(Color.RED);


		fire1.addActionListener(this);
		fire2.addActionListener(this);

		p1O = new OppBoard(name2, fire1);
		p2O = new OppBoard(name1, fire2);

		ready1 = new JButton();
		ImageIcon rdyIcon = new ImageIcon("src/battleship/rdyLogo.png");
		ready1.setIcon(rdyIcon);
		ready1.setBackground(Color.BLUE);
		ready1.setBorder(new MatteBorder(3, 3, 3, 3, Color.BLACK));
		ready1.addActionListener(this);

		ready2 = new JButton();		
		rdyIcon = new ImageIcon("src/battleship/rdyLogo.png");
		ready2.setIcon(rdyIcon);
		ready2.setBackground(Color.BLUE);
		ready2.setBorder(new MatteBorder(3, 3, 3, 3, Color.BLACK));
		ready2.addActionListener(this);


		p1Switch = new SwitchPanel(name1, ready1);
		p2Switch = new SwitchPanel(name2, ready2);

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

		if (!rematch) {
			setTitle("Battleship");
			this.setDefaultCloseOperation(JFrame.
					EXIT_ON_CLOSE);      
			setJMenuBar(menus);
			setSize(1450, 1000);
			setVisible(true);
		}
		revalidate();
		repaint();
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
	 * Displays a win screen on the top panel for the winning player.
	 * @param winner the winner's name
	 */
	public void winScreen(final String winner) {
		rematch1 = new JButton("Rematch?");
		rematch1.addActionListener(this);
		rematch1.setBackground(Color.BLUE);
		end = new WinPanel(winner, rematch1);
		holder[0].removeAll();
		holder[0].add(end);
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
	 * Displays a switch screen for player 1 after 
	 * player 2's turn until they select ready.
	 */
	public void switchP2() {
		p1O.disableFire();
		holder[1].removeAll();
		holder[1].add(p2Switch);
		revalidate();
		repaint();
	}

	/**
	 * Displays a switch screen for player 1 after player 2's 
	 * turn until they select ready.
	 */
	public void switchP1() {
		p2O.disableFire();
		holder[1].removeAll();
		holder[1].add(p1Switch);
		revalidate();
		repaint();
	}

	/**
	 * Responding to an action.
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {

		if (e.getSource() == newGameItem) {
			holder[0].removeAll();
			holder[1].removeAll();
			newGame(true);
			revalidate();
			repaint();
		}

		if (e.getSource() == closeItem) {
			this.dispose();
			revalidate();
			repaint();
		}

		boolean hit;
		if (e.getSource() == fire1) {
			//if(p1O.isFired()) {
			targetX = p1O.getTargetX();
			targetY = p1O.getTargetY();
			if (targetX != -1 && targetY != -1) {
				hit = p2.hitMiss(targetX, targetY);

				//mark square on opponent board
				p1O.markSquare(hit, targetX, targetY);
				p1O.updateStatus(p2.getStatus());

				revalidate();
				repaint();

				if (!p2.isLost()) {
					switchP2();
				} else {
					winScreen(name1);
				}
			}
			//}
		}
		if (e.getSource() == fire2) {

			targetX = p2O.getTargetX();
			targetY = p2O.getTargetY();
			if (targetX != -1 && targetY != -1) {
				hit = p1.hitMiss(targetX, targetY);

				//mark square on opponent board
				p2O.markSquare(hit, targetX, targetY);
				p2O.updateStatus(p1.getStatus());


				if (!p1.isLost()) {
					switchP1();
				} else {
					winScreen(name2);
				}
			}

		}
		if (e.getSource() == ready1) {
			p1Switch.setReady(false);
			holder[0].removeAll();
			holder[1].removeAll();
			holder[0].add(p1O);
			holder[1].add(p1);
			revalidate();
			repaint();
			p1O.enableFire();

		}
		if (e.getSource() == ready2) {
			p2Switch.setReady(false);
			holder[0].removeAll();
			holder[1].removeAll();
			holder[0].add(p2O);
			holder[1].add(p2);
			revalidate();
			repaint();
			if (p2.isReady()) {
				p2O.enableFire();
			}

		}
		if (e.getSource() == begin1) {
			if (p1.isReady()) {
				switchP2();
				begin1.setEnabled(false);
			}
		}
		if (e.getSource() == begin2) {
			if (p2.isReady()) {
				switchP1();
				begin2.setEnabled(false);
			}
		}
		if (e.getSource() == rematch1) {
			holder[0].removeAll();
			holder[1].removeAll();
			newGame(true);
			revalidate();
			repaint();
		}
		if (e.getSource() == mainMenuItem) {
			dispose();
			new MenuGUI();
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

