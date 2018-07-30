package checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * JFrame for the Checkers game, called upon by main menu. Adds the game
 * panel to the screen.
 * @author Parker
 *
 */
public class CheckersGUI extends JFrame implements ActionListener {
	/**
	 * ID for checkers GUI.
	 */
	private static final long serialVersionUID = 6226262006795409252L;
	/**
	 * Menu item for returning to main menu.
	 */
	private JMenuItem mainMenu;
	/**
	 * Player names.
	 */
	private String name1, name2;
	/**
	 * Constructor for checkers frame.
	 * @param n1 Player 1's name
	 * @param n2 Player 2's name.
	 */
	public CheckersGUI(final String n1, final String n2) {
		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitGame;
		JMenuItem newGame;
		name1 = n1;
		name2 = n2;
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creates and adds menu options to file menu which is then added to menu bar
		fileMenu = new JMenu("File");
		quitGame = new JMenuItem("Quit");
		newGame = new JMenuItem("New Game");
		mainMenu = new JMenuItem("Main Menu");

		fileMenu.add(newGame);
		fileMenu.add(mainMenu);
		fileMenu.add(quitGame);
		mainMenu.addActionListener(this);
		menus = new JMenuBar();
		setJMenuBar(menus);
		menus.add(fileMenu);

		setTitle("Checkers");
		add(new View(quitGame, newGame, name1, name2));
		pack();
		setSize(800, 800);
		setVisible(true);
	}

	@Override
	public void actionPerformed(final ActionEvent arg0) {

		if (arg0.getSource() == mainMenu) {
			dispose();
			new mainmenu.MenuGUI();
			revalidate();
			repaint();
		}
		
	}
}
