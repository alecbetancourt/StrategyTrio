package connect4;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 * GUI for the connect4 game. Adds in game panel and menu items.
 * @author Parker
 *
 */
public class ConnectGUI extends JFrame implements ActionListener {

	/**
	 * ID for connect gui.
	 */
	private static final long serialVersionUID = -2274086287257430306L;
	/**
	 * Array for column selection buttons.
	 */
	private JButton[] select;
	/**
	 * Tracker for whos turn 0 = black, 1 = red.
	 */
	private int turn;
	/**
	 * Connect4 game panel.
	 */
	private ConnectPanel screen;
	/**
	 * Menu bar.
	 */
	private JMenuBar menus;
	/**
	 * Options for menu.
	 */
	private JMenu fileMenu;
	/**
	 * Quit item for menu.
	 */
	private JMenuItem quitGame;
	/**
	 * New game item for menu.
	 */
	private JMenuItem newGame;
	/**
	 * Item to return to main menu.
	 */
	private JMenuItem mainMenu;
	/**
	 * Icon for place here.
	 */
	private ImageIcon placeIcon;

	/**
	 * GUI constructor. Adds menubar and game panel.
	 */
	public ConnectGUI() {
		turn = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fileMenu = new JMenu("File");
		quitGame = new JMenuItem("Quit");
		newGame = new JMenuItem("New Game");
		mainMenu = new JMenuItem("Main Menu");

		fileMenu.add(newGame);
		fileMenu.add(mainMenu);
		fileMenu.add(quitGame);
		mainMenu.addActionListener(this);
		newGame.addActionListener(this);
		quitGame.addActionListener(this);
		menus = new JMenuBar();
		setJMenuBar(menus);
		menus.add(fileMenu);

		placeIcon = new ImageIcon("src/connect4/placeIcon.png");
		select = new JButton[7];
		for (int i = 0; i < 7; i++) {
			select[i] = new JButton();
			select[i].setBackground(Color.BLUE);
			select[i].setIcon(placeIcon);
			select[i].addActionListener(this);
		}
		screen = new ConnectPanel(select);
		add(screen);
		pack();
		setTitle("Connect4");
		setSize(800, 800);
		setVisible(true);
	}

	/**
	 * Fine the corresponding button location given a button click.
	 * @param e the button clicked.
	 * @return int of location of button in array.
	 */
	public int findButton(final ActionEvent e) {
		for (int i = 0; i < 7; i++) {
			if (e.getSource() == select[i]) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * increments turn, resets to 0 if at 1.
	 */
	public void nextTurn() {
		turn++;
		if (turn == 2) {
			turn = 0;
		}
	}
	/**
	 * Checks if a player has won.
	 * @return True if a player won, false if not.
	 */
	public boolean checkWin() {
		return screen.isWinner();
	}

	/**
	 * Displays message saying nobody won and starts a new game.
	 * Called upon when the board is full of tokens with no winner.
	 */
	public void full() {
		JOptionPane.showMessageDialog(null, "Board full!, No winner");
		remove(screen);
		screen = new ConnectPanel(select);
		add(screen);
		revalidate();
		repaint();
		turn = 0;
	}
	/**
	 * Responds to all menu or button clicks.
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		int loc;
		if (e.getSource() instanceof JButton) {
			loc = findButton(e);
			if (loc != -1) {
				if (turn == 0) {
					if (screen.placeToken(loc, 0)) {
						nextTurn();
						if (checkWin()) {
							JOptionPane.showMessageDialog(null, "Black has won");
							remove(screen);
							screen = new ConnectPanel(select);
							add(screen);
							revalidate();
							repaint();
							turn = 0;
						} else if (screen.isFull()) {
							full();
						}
					}
				} else {
					if (screen.placeToken(loc, 1)) {
						nextTurn();
						checkWin();
						if (checkWin()) {
							JOptionPane.showMessageDialog(null, "Red has won");
							remove(screen);
							screen = new ConnectPanel(select);
							add(screen);
							revalidate();
							repaint();
							turn = 0;
						} else if (screen.isFull()) {
							full();
						}
					}
				}
			}
		}
		if (e.getSource() == quitGame) {
			dispose();
		}
		if (e.getSource() == newGame) {
			remove(screen);
			screen = new ConnectPanel(select);
			add(screen);
			revalidate();
			repaint();
		}
		if (e.getSource() == mainMenu) {
			dispose();
			new mainmenu.MenuGUI();
			revalidate();
			repaint();
		}

	}

	/**
	 * driver for gui.
	 * @param args arguements
	 */
	public static void main(final String[] args) {
		new ConnectGUI();
	}
}
