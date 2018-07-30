package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Frame for the tictactoe game panel. To be called upon by main menu. 
 * @author Parker
 *
 */
public class TicGUI extends JFrame implements ActionListener {

	/**
	 * Tic GUI id.
	 */
	private static final long serialVersionUID = 4569490155608832949L;
	
	/**
	 * Menu bar.
	 */
	private JMenuBar menus;
	/**
	 * Menu option.
	 */
	private JMenu fileMenu;
	/**
	 * Quit game option for menu.
	 */
	private JMenuItem quitGame;
	/**
	 * New game option for menu.
	 */
	private JMenuItem newGame;
	/**
	 * Main menu option for menu.
	 */
	private JMenuItem mainMenu;
	/**
	 * Player names.
	 */
	private String name1, name2;

	/**
	 * Constructor for tictactoe Frame.
	 * @param pName1 player 1's name.
	 * @param pName2 player 2's name.
	 */
	public TicGUI(final String pName1, final String pName2) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		name1 = pName1;
		name2 = pName2;
		
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

		setTitle("Tic-Tac-Toe");
		add(new Game(pName1, pName2));
		pack();
		setSize(800, 800);
		setVisible(true);
	}

	/**
	 * Responds to button or menu clicks.
	 * @param e what was clicked.
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == quitGame) {
			dispose();
		}
		if (e.getSource() == mainMenu) {
			dispose();
			new mainmenu.MenuGUI();
		}
		if (e.getSource() == newGame) {
			dispose();
			new TicGUI(name1, name2);
		}

	}
}
