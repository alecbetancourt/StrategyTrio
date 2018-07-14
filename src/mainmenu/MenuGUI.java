package mainmenu;

//import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


//import battleship.*;
//import chess.*;

/**
 * Main menu class.
 * @author Parker
 *
 */
public class MenuGUI extends JFrame implements ActionListener {

	/**
	 * Screen.
	 */
	private JPanel screen;
	/**
	 * Message for menu.
	 */
	private JLabel message;
	/**
	 * button for battleship game.
	 */
	private JButton bShip;
	/**
	 * button for chess.
	 */
	private JButton chess;
	/**
	 * button for help menu.
	 */
	private JButton help;
	/**
	 * button for exiting.
	 */
	private JButton exit;
	/**
	 * constraints for gridbag formatting.
	 */
	private GridBagConstraints con;

	/**
	 * Menu bar.
	 */
	private JMenuBar menus;
	/**
	 * Options menu.
	 */
	private JMenu option;
	/**
	 * Close menu option.
	 */
	private JMenuItem closeItem;

	/** 
	 * Panel to display help messages.
	 */
	private HelpPanel hPanel;

	/**
	 * Logo for arcade.
	 */
	private ImageIcon logo;
	/**
	 * battleship logo.
	 */
	private ImageIcon battleLogo;
	/**
	 * chess logo.
	 */
	private ImageIcon chessLogo;
	/**
	 * help button logo.
	 */
	private ImageIcon helpLogo;
	/**
	 * exit button logo.
	 */
	private ImageIcon exitLogo;

	
	//private battleship.GUI battleGUI;

	/**
	 * Menu constructor.
	 */
	public MenuGUI() {
		screen = new JPanel();
		screen.setLayout(new GridBagLayout());
		con = new GridBagConstraints();
		logo = new ImageIcon("src/MainMenu/logo.png");
		battleLogo = new ImageIcon("src/MainMenu/bShipLogo.png");
		chessLogo = new ImageIcon("src/MainMenu/chessLogo.png");
		helpLogo = new ImageIcon("src/MainMenu/helpLogo.png");
		exitLogo = new ImageIcon("src/MainMenu/exitLogo.png");

		con.gridx = 0;
		con.gridy = 0;
		con.ipady = 100;
		message = new JLabel();
		message.setIcon(logo);
		screen.add(message, con);

		addButtons();
		add(screen);
		addMenus();

		setTitle("Strategy Arcade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
		setJMenuBar(menus);
		setSize(1500, 1000);
		setVisible(true);
	}

	/**
	 * Add the buttons to the screen.
	 */
	public void addButtons() {
		con.ipadx = 0;
		con.ipady = 0;
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridy++;

		bShip = new JButton();
		bShip.setIcon(battleLogo);
		bShip.addActionListener(this);
		screen.add(bShip, con);

		con.gridy++;
		chess = new JButton();
		chess.setIcon(chessLogo);
		chess.addActionListener(this);
		screen.add(chess, con);

		con.gridy++;
		help = new JButton();
		help.setIcon(helpLogo);
		help.addActionListener(this);
		screen.add(help, con);

		con.gridy++;
		exit = new JButton();
		exit.setIcon(exitLogo);
		exit.addActionListener(this);
		screen.add(exit, con);
	}

	/**
	 * Add menus to the frame.
	 */
	public void addMenus() {
		option = new JMenu("Menu");
		closeItem = new JMenuItem("Quit");
		closeItem.addActionListener(this);

		menus = new JMenuBar();
		menus.add(option);
		option.add(closeItem);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == exit || e.getSource() == closeItem) {
			dispose();
		}
		//doesnt work,gui wont update, probably an issue with package/import
		if (e.getSource() == bShip) {
			dispose();
			//new battleship.GUI().main(null);
			revalidate();
			repaint();


		}
		//Doesnt work, gui wont update, probably an issue with package/import
		if (e.getSource() == chess) {
			dispose();
			//works for now, will have to make minor change to chess to get rid of warning

			//new chess.View().main(null);	
			Thread.yield();


			revalidate();
			repaint();
		}
		if (e.getSource() == help) {
			this.remove(screen);
			hPanel = new HelpPanel(battleLogo, chessLogo);
			add(hPanel);
			revalidate();
			repaint();

			//for exit status, right now doesnt work 
			//			while(!hPanel.getExit()) {
			//				//Googled this solution because loop was not updating
			//				try {
			//					Thread.yield();
			//				} catch (Exception interruptedEx) {
			//					// 
			//				}
			//			}

		}
	}
	/**
	 * drive for GUI().
	 * @param args for main
	 */
	public static void main(final String[] args) {
		new MenuGUI();
	}
}


